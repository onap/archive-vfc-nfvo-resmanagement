/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.VimUtil;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Sites;
import org.openo.nfvo.resmanagement.service.business.inf.LimitsBusiness;
import org.openo.nfvo.resmanagement.service.business.inf.SitesBusiness;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;
import org.openo.nfvo.resmanagement.service.group.inf.ResOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * DC info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 24, 2016
 */
public class SitesImpl implements Sites {

    private static final Logger LOGGER = LoggerFactory.getLogger(SitesImpl.class);

    private SitesBusiness sitesBusiness;

    private LimitsBusiness limitsBusiness;

    private ResOperateService resOperateService;

    @Override
    public int add(JSONObject jsonObject) throws ServiceException {
        LOGGER.info("Add datacenter jsonObject: {}", jsonObject);
        SitesEntity sitesEntity = SitesEntity.toEntity(jsonObject);
        sitesEntity.setStatus(ParamConstant.PARAM_ACTIVE);
        String vimName = jsonObject.getString(ParamConstant.PARAM_VIMNAME);
        String vimId = VimUtil.getVimIdByName(vimName);
        sitesEntity.setVimId(vimId);
        JSONObject resource = limitsBusiness.getLimits(vimId);
        sitesEntity.setVimName(resource.getString(ParamConstant.PARAM_VIMNAME));
        sitesEntity.setTotalCPU(resource.getString(ParamConstant.TOTAL_CPU));
        sitesEntity.setUsedCPU(resource.getString(ParamConstant.USED_CPU));
        sitesEntity.setTotalMemory(resource.getString(ParamConstant.TOTAL_MEMORY));
        sitesEntity.setUsedMemory(resource.getString(ParamConstant.USED_MEMORY));
        sitesEntity.setTotalDisk(resource.getString(ParamConstant.TOTAL_DISK));
        sitesEntity.setUsedDisk(resource.getString(ParamConstant.USED_DISK));
        if(StringUtils.isEmpty(sitesEntity.getId())) {
            sitesEntity.setId(UUID.randomUUID().toString());
            jsonObject.put(ParamConstant.PARAM_ID, sitesEntity.getId());
        }
        LOGGER.info("Add datacenter sitesEntity: {}", sitesEntity.toString());
        return sitesBusiness.addSite(sitesEntity);
    }

    /**
     * <br>
     * 
     * @param json
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @Override
    public void sendToMonitor(JSONObject jsonObject) throws ServiceException {
        LOGGER.info("SitesImpl sendToMonitor jsonObject: {}", jsonObject);
        String vimName = jsonObject.getString(ParamConstant.PARAM_VIMNAME);
        String vimId = VimUtil.getVimIdByName(vimName);
        JSONObject vimInfo = VimUtil.getVimById(vimId);
        LOGGER.info("SitesImpl sendToMonitor vimInfo: {}", vimInfo);
        String tenant = vimInfo.getString("tenant");
        String tenantId = VimUtil.getTenantIdByName(tenant, vimId);
        JSONObject json = new JSONObject();
        json.put("header", null);
        LOGGER.info("tenantId:{}, vimId:{}", tenantId, vimId);
        resOperateService.addIRes(tenantId, vimId, json);
        resOperateService.sendMsgMonitor("create", vimId);
    }

    @Override
    public int update(SitesEntity sitesEntity) throws ServiceException {
        return sitesBusiness.updateSiteSelective(sitesEntity);
    }

    @Override
    public int update(JSONObject jsonObject) throws ServiceException {
        LOGGER.info("grantResource jsonObject: {}", jsonObject);
        JSONObject sitesObj = dataParse(jsonObject);
        return sitesBusiness.updateSiteSelective(SitesEntity.toEntity(sitesObj));
    }

    private JSONObject dataParse(JSONObject jsonObject) throws ServiceException {
        String vimId = jsonObject.getString(ParamConstant.PARAM_VIMID);
        Map<String, Object> condition = new HashMap<>();
        condition.put("vimId", vimId);
        SitesEntity sitesEntity = get(condition);
        if(null == sitesEntity) {
            LOGGER.error("Get sites null, vimId={}", vimId);
            return null;
        }
        return computeSiteUsed(jsonObject, sitesEntity);
    }

    private JSONObject computeSiteUsed(JSONObject jsonObject, SitesEntity sitesEntity) throws ServiceException {
        String action = JsonUtil.getJsonFieldStr(jsonObject, "action");
        String usedCpu = JsonUtil.getJsonFieldStr(jsonObject, ParamConstant.USED_CPU);
        String usedMemory = JsonUtil.getJsonFieldStr(jsonObject, ParamConstant.USED_MEMORY);
        String usedDisk = JsonUtil.getJsonFieldStr(jsonObject, ParamConstant.USED_DISK);
        String oldCpu = sitesEntity.getUsedCPU();
        String oldMemory = sitesEntity.getUsedMemory();
        String oldDisk = sitesEntity.getUsedDisk();
        String newCpu = accumOrFreeRes(usedCpu, oldCpu, action, sitesEntity.getTotalCPU(), "cpu");
        String newMemory = accumOrFreeRes(usedMemory, oldMemory, action, sitesEntity.getTotalMemory(), "memory");
        String newDisk = accumOrFreeRes(usedDisk, oldDisk, action, sitesEntity.getTotalDisk(), "disk");

        JSONObject resUsed = new JSONObject();
        resUsed.put(ParamConstant.USED_CPU, newCpu);
        resUsed.put(ParamConstant.USED_MEMORY, newMemory);
        resUsed.put(ParamConstant.USED_DISK, newDisk);
        resUsed.put("id", sitesEntity.getId());
        resUsed.put("name", sitesEntity.getName());
        resUsed.put("status", sitesEntity.getStatus());
        resUsed.put("location", sitesEntity.getLocation());
        resUsed.put("country", sitesEntity.getCountry());
        resUsed.put(ParamConstant.PARAM_VIMID, sitesEntity.getVimId());
        resUsed.put(ParamConstant.PARAM_VIMNAME, sitesEntity.getVimName());
        resUsed.put(ParamConstant.TOTAL_CPU, sitesEntity.getTotalCPU());
        resUsed.put(ParamConstant.TOTAL_MEMORY, sitesEntity.getTotalMemory());
        resUsed.put(ParamConstant.TOTAL_DISK, sitesEntity.getTotalDisk());
        return resUsed;
    }

    private String accumOrFreeRes(String resUsed, String resOld, String action, String total, String type)
            throws ServiceException {
        BigDecimal iResUsed = new BigDecimal(resUsed);
        BigDecimal iResOld = new BigDecimal(resOld);
        BigDecimal itotal = new BigDecimal(total);
        if("online".equals(action)) {
            if(iResOld.add(iResUsed).compareTo(itotal) > 0) {
                throw new ServiceException("Grant resource fail! The " + type + " resource not enough.");
            }
            return String.valueOf(iResOld.add(iResUsed));
        } else {
            if(iResOld.subtract(iResUsed).compareTo(BigDecimal.ZERO) < 0) {
                throw new ServiceException("Grant resource fail! The " + type + " resource used below zero.");
            }
            return String.valueOf(iResOld.subtract(iResUsed));
        }
    }

    @Override
    public int updateResource(JSONObject jsonObject) throws ServiceException {
        return sitesBusiness.updateSiteResource(SitesEntity.toEntity(jsonObject));
    }

    @Override
    public int delete(String id) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<SitesEntity> datacenters = getList(map);
        SitesEntity site = datacenters.get(0);
        LOGGER.info("site: {}", site);
        String vimId = site.getVimId();
        LOGGER.info("vimId: {}", vimId);
        resOperateService.sendMsgMonitor("delete", vimId);
        resOperateService.deleteIRes(vimId);
        return sitesBusiness.deleteSite(id);
    }

    @Override
    public int updateStatusByVimId(JSONObject jsonObject) throws ServiceException {
        return sitesBusiness.updateSiteByVimId(SitesEntity.toEntity(jsonObject));
    }

    @Override
    public List<SitesEntity> getList(Map<String, Object> condition) throws ServiceException {
        return sitesBusiness.getSites(condition);
    }

    @Override
    public SitesEntity get(Map<String, Object> condition) throws ServiceException {
        List<SitesEntity> siteList = sitesBusiness.getSites(condition);
        if(null == siteList || siteList.isEmpty()) {
            return null;
        }
        return siteList.get(0);
    }

    @Override
    public int deleteResByVimId(String vimId) throws ServiceException {
        return 0;
    }

    public void setSitesBusiness(SitesBusiness sitesBusiness) {
        this.sitesBusiness = sitesBusiness;
    }

    public void setLimitsBusiness(LimitsBusiness limitsBusiness) {
        this.limitsBusiness = limitsBusiness;
    }

    public void setResOperateService(ResOperateService resOperateService) {
        this.resOperateService = resOperateService;
    }

}
