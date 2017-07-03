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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.VimUtil;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Location;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Sites;
import org.openo.nfvo.resmanagement.service.business.inf.LocationBusiness;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Location Implementation Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public class LocationImpl implements Location {

    private LocationBusiness locationBusiness;

    private Sites sites;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationImpl.class);

    @Override
    public int add(JSONObject jsonObject) throws ServiceException {
        return locationBusiness.addLocation(LocationEntity.toEntity(jsonObject));
    }

    @Override
    public int update(JSONObject jsonObject) throws ServiceException {
        return locationBusiness.updateLocationSelective(LocationEntity.toEntity(jsonObject));
    }

    @Override
    public int delete(String location) throws ServiceException {
        return locationBusiness.deleteLocation(location);
    }

    @Override
    public Map<String, Object> get(String id) throws ServiceException {
        Map<String, Object> map = new HashMap<>();
        map.put(ParamConstant.PARAM_ID, id);
        List<LocationEntity> sodores = locationBusiness.getLocations(map);
        map.clear();
        map.put(ParamConstant.PARAM_DATA, sodores);
        return map;
    }

    @Override
    public List<LocationEntity> get(Map<String, Object> condition) throws ServiceException {
        return locationBusiness.getLocations(condition);
    }

    @Override
    public List<String> getCountry() throws ServiceException {
        return locationBusiness.getCountry();
    }

    @Override
    public List<String> getCloudservice() throws ServiceException {
        LOGGER.info("get cloud service from external system");
        JSONArray vims = VimUtil.getVims();
        LOGGER.info("vims: " + vims.toString());
        List<String> cloudService = new ArrayList<>();
        for(int i = 0; i < vims.size(); i++) {
            String vimName = vims.getJSONObject(i).getString("name");
            cloudService.add(vimName);
        }
        return cloudService;
    }

    @Override
    public List<String> getLocationByCountry(Map<String, Object> condition) throws ServiceException {
        return locationBusiness.getLocationByCountry(condition);
    }

    @Override
    public LocationEntity getLocation(Map<String, Object> condition) throws ServiceException {
        List<LocationEntity> locationlist = locationBusiness.getLocations(condition);
        if(null == locationlist || locationlist.isEmpty()) {
            return null;
        }
        return locationlist.get(0);
    }

    @Override
    public List<JSONObject> getLocationInfo(List<LocationEntity> locationInfo) throws ServiceException {
        ArrayList<JSONObject> newSites = new ArrayList<>();
        Map<String, Object> condition = new HashMap<>();
        for(int i = 0; i < locationInfo.size(); i++) {
            LocationEntity locationEntity = locationInfo.get(i);
            String latitude = locationEntity.getLatitude();
            String longitude = locationEntity.getLongitude();
            String locatSite = locationEntity.getLocation();
            condition.put(ParamConstant.PARAM_LOCATION, locatSite);
            LOGGER.info("LocationRoa::getLocation condition:{}", condition);
            List<SitesEntity> sitesEntity = sites.getList(condition);
            LOGGER.info("LocationRoa::getLocation sitesEntity:{}", sitesEntity);
            if(null != sitesEntity && !sitesEntity.isEmpty()) {
                for(SitesEntity entity : sitesEntity) {
                    JSONObject site = JSONObject.fromObject(entity.toString());
                    JSONObject resTotalJo = site.getJSONObject("total");
                    JSONObject resUsedlJo = site.getJSONObject("used");
                    JSONObject ressiteJo = computingSite(resTotalJo, resUsedlJo);
                    site.element("latitude", latitude);
                    site.element("longitude", longitude);
                    site.element("siteDetail", ressiteJo);
                    LOGGER.info("LocationRoa::getLocation latitude:{}, longitude:{}", latitude, longitude);
                    LOGGER.info("LocationRoa::getLocation site:{}", site);
                    newSites.add(site);
                }
            }
        }
        return newSites;
    }

    /**
     * Computing site.<br>
     *
     * @param total
     * @param used
     * @return
     * @since NFVO 0.5
     */
    public JSONObject computingSite(JSONObject total, JSONObject used) {
        String vcpus = JsonUtil.getJsonFieldStr(total, ParamConstant.PARAM_VCPUS);
        String memorys = JsonUtil.getJsonFieldStr(total, ParamConstant.PARAM_MEMORY);
        String disks = JsonUtil.getJsonFieldStr(total, ParamConstant.PARAM_DISK);
        String vcpusused = JsonUtil.getJsonFieldStr(used, ParamConstant.PARAM_VCPUS);
        String memoryused = JsonUtil.getJsonFieldStr(used, ParamConstant.PARAM_MEMORY);
        String diskused = JsonUtil.getJsonFieldStr(used, ParamConstant.PARAM_DISK);
        float cpu = Float.parseFloat(vcpusused) / Float.parseFloat(vcpus);
        float memory = Float.parseFloat(memoryused) / Integer.parseInt(memorys);
        float disk = Float.parseFloat(diskused) / Float.parseFloat(disks);
        JSONObject resTotalJo = new JSONObject();
        resTotalJo.put(ParamConstant.PARAM_VCPUS, String.valueOf(Math.round(cpu * 100)) + "%");
        resTotalJo.put(ParamConstant.PARAM_MEMORY, String.valueOf(Math.round(memory * 100)) + "%");
        resTotalJo.put(ParamConstant.PARAM_DISK, String.valueOf(Math.round(disk * 100)) + "%");

        return resTotalJo;
    }

    public LocationBusiness getLocationBusiness() {
        return locationBusiness;
    }

    public void setLocationBusiness(LocationBusiness locationBusiness) {
        this.locationBusiness = locationBusiness;
    }

    public void setSites(Sites sites) {
        this.sites = sites;
    }

}
