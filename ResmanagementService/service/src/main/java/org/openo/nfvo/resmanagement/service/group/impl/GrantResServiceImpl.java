/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.nfvo.resmanagement.service.group.impl;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.VimUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Sites;
import org.openo.nfvo.resmanagement.service.group.inf.GrantResService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Oct 29, 2016
 */
public class GrantResServiceImpl implements GrantResService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrantResServiceImpl.class);

    private Sites sites;

    /**
     * <br>
     * 
     * @param object
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @Override
    public JSONObject grantResource(JSONObject object) throws ServiceException {
        LOGGER.info("function=grantResource; object: {}", object.toString());
        JSONObject additionalparam = object.getJSONObject("additionalParam");
        String vimId = additionalparam.getString("vimid");
        JSONObject vimJson = VimUtil.getVimById(vimId);
        String tenant = vimJson.getString("tenant");
        JSONObject accessinfo = new JSONObject();
        accessinfo.put("tenant", tenant);
        JSONObject vim = new JSONObject();
        vim.put("vimid", vimId);
        vim.put("accessinfo", accessinfo);
        LOGGER.info("function=grantResource; vim: {}", vim.toString());
        JSONObject result = new JSONObject();
        result.put("vim", vim);
        return result;
    }

    @Override
    public JSONObject grantResourceReal(JSONObject object) throws ServiceException {
        LOGGER.info("function=grantResource; object: {}", object.toString());
        String vimId = object.getString("vimId");
        JSONObject vimJson = VimUtil.getVimById(vimId);
        JSONObject vim = parseVim(vimJson);
        String resType = "";
        JSONArray resArr = new JSONArray();
        if(object.containsKey("addResource")) {
            resType = "addResource";
            resArr = parseResource(object, resType);
        } else if(object.containsKey("removeResource")) {
            resType = "removeResource";
            resArr = parseResource(object, resType);
        }
        JSONObject resInfo = getResInfo(object, resType);
        resInfo.put("vimId", vimId);
        sites.update(resInfo);

        JSONObject result = new JSONObject();
        result.put("vim", vim);
        result.put("zone", "");
        result.put("zoneGroup", "");
        result.put(resType, resArr);
        result.put("tempResource", "");
        result.put("updateResource", "");
        result.put("vimAssets", new JSONObject());
        result.put("additionalParam", "");
        LOGGER.info("function=grantResource; result: {}", result.toString());
        return result;
    }

    private JSONObject getResInfo(JSONObject object, String type) {
        JSONArray arr = object.getJSONArray(type);
        LOGGER.info("function=getResInfo; arr: {}, type: {}", arr, type);
        JSONObject resourceObj = new JSONObject();
        if("addResource".equals(type)) {
            resourceObj = getGrantResource(arr);
            resourceObj.put("action", "online");
        } else if("removeResource".equals(type)) {
            resourceObj = getGrantResource(arr);
            resourceObj.put("action", "offline");
        }
        LOGGER.info("function=getResInfo; resutl: {}", resourceObj.toString());
        return resourceObj;
    }

    /**
     * <br>
     * 
     * @param addResource
     * @return
     * @since NFVO 0.5
     */
    private JSONObject getGrantResource(JSONArray resource) {
        int cpuNum = 0;
        int memNum = 0;
        int diskNum = 0;
        for(int i = 0; i < resource.size(); i++) {
            JSONObject res = resource.getJSONObject(i);
            JSONObject vCpu = res.getJSONObject("resourceTemplate").getJSONObject("virtualComputeDescriptor")
                    .getJSONObject("virtualCpu");
            int vCpuNum = vCpu.getInt("numVirtualCpu");
            JSONObject vMem = res.getJSONObject("resourceTemplate").getJSONObject("virtualComputeDescriptor")
                    .getJSONObject("virtualMemory");
            int vMemNum = vMem.getInt("virtualMemSize");
            JSONObject vDisk = res.getJSONObject("resourceTemplate").getJSONObject("virtualStorageDescriptor");
            int vDiskNum = vDisk.getInt("sizeOfStorage");
            cpuNum = cpuNum + vCpuNum;
            memNum = memNum + vMemNum;
            diskNum = diskNum + vDiskNum;
        }
        JSONObject obj = new JSONObject();
        obj.put("usedCPU", String.valueOf(cpuNum));
        obj.put("usedMemory", String.valueOf(memNum));
        obj.put("usedDisk", String.valueOf(diskNum));
        return obj;
    }

    /**
     * <br>
     * 
     * @param object
     * @return
     * @since NFVO 0.5
     */
    private JSONArray parseResource(JSONObject object, String key) {
        JSONArray newResources = new JSONArray();
        JSONArray oldResource = object.getJSONArray(key);
        LOGGER.info("function=parseResource; Resource: {}", oldResource.toString());
        for(int i = 0; i < oldResource.size(); i++) {
            JSONObject res = oldResource.getJSONObject(i);
            JSONObject obj = new JSONObject();
            obj.put("reservationId", "");
            obj.put("resourceProviderId", "");
            obj.put("zoneId", "");
            obj.put("vimId", object.getString("vimId"));
            obj.put("resourceDefinitionId", res.getString("resourceDefinitionId"));
            newResources.add(obj);
        }
        LOGGER.info("function=parseResource; Parse Resource result: {}", newResources.toString());
        return newResources;
    }

    /**
     * <br>
     * 
     * @param vimJson
     * @return
     * @since NFVO 0.5
     */
    private JSONObject parseVim(JSONObject vimJson) {
        LOGGER.info("function=grantResource; vimJson: {}", vimJson.toString());
        JSONObject interfaceInfo = new JSONObject();
        interfaceInfo.put("vimType", vimJson.getString("type"));
        interfaceInfo.put("apiVersion", "v2");
        interfaceInfo.put("protocolType", "http");
        JSONObject accessInfo = new JSONObject();
        accessInfo.put("tenant", vimJson.getString("tenant"));
        accessInfo.put("username", vimJson.getString("userName"));
        accessInfo.put("password", vimJson.getString("password"));
        JSONObject vim = new JSONObject();
        vim.put("vimInfoId", vimJson.getString("vimId"));
        vim.put("vimId", vimJson.getString("vimId"));
        vim.put("interfaceInfo", interfaceInfo);
        vim.put("accessInfo", accessInfo);
        vim.put("interfaceEndpoint", vimJson.getString("url"));
        return vim;
    }

    /**
     * @param sites The sites to set.
     */
    public void setSites(Sites sites) {
        this.sites = sites;
    }

}
