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

package org.openo.nfvo.resmanagement.service.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.util.StringUtil;
import org.openo.nfvo.resmanagement.service.business.inf.LocationBusiness;
import org.openo.nfvo.resmanagement.service.dao.inf.LocationDao;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Location Business implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class LocationBusinessImpl implements LocationBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationBusinessImpl.class);

    private LocationDao locationDao;

    /**
     *
     * Check location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    public boolean checkLocation(LocationEntity locationEntity) throws ServiceException {
        String location = locationEntity.getLocation();
        Map<String, Object> map = new HashMap<>();
        map.put(ParamConstant.PARAM_LOCATION, location);
        List<LocationEntity> locationList = getLocations(map);
        if(locationList == null || locationList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     *
     * Check Latitude.;<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    public boolean checkLatitude(LocationEntity locationEntity) throws ServiceException {
        String latitu = locationEntity.getLatitude();
        String longitu = locationEntity.getLongitude();
        if("-0".equals(locationEntity.getLatitude())) {
            locationEntity.setLatitude("0");
        }
        if("-0".equals(locationEntity.getLongitude())) {
            locationEntity.setLongitude("0");
        }
        float latitude = Float.parseFloat(latitu);
        float longitude = Float.parseFloat(longitu);
        if((latitude >= -90 && latitude <= 90) && (longitude >= -180 && longitude <= 180)) {
            return true;
        }
        return false;
    }

    private boolean checkModified(LocationEntity locationEntity) {
        String newCountry = locationEntity.getCountry();
        String newLocation = locationEntity.getLocation();
        String id = locationEntity.getId();
        LocationEntity selectLocation = locationDao.getLocation(id);
        if(null == selectLocation) {
            return false;
        }
        String oldCountry = selectLocation.getCountry();
        String oldLocation = selectLocation.getLocation();
        if(newCountry.equals(oldCountry) && newLocation.equals(oldLocation)) {
            return true;
        }
        return false;
    }

    @Override
    public LocationEntity getLocation(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=getLocation; msg=get error, because id is empty.");
            return null;
        }
        return locationDao.getLocation(id);
    }

    @Override
    public List<String> getCountry() throws ServiceException {
        return locationDao.getCountry();
    }

    @Override
    public List<String> getLocationByCountry(Map<String, Object> condition) throws ServiceException {
        return locationDao.getLocationByCountry(condition);
    }

    @Override
    public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
        return locationDao.getLocations(condition);
    }

    @Override
    public int deleteLocation(String location) throws ServiceException {
        if(StringUtils.isEmpty(location)) {
            LOGGER.error("function=deleteLocation; msg=delete error, because location is empty.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.delete.base.entity.check"));
        }
        return locationDao.deleteLocation(location);
    }

    @Override
    public int addLocation(LocationEntity locationEntity) throws ServiceException {
        if(null == locationEntity) {
            LOGGER.error("function=addLocation; msg=add error, because locationEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.add.entity.null"));
        }
        if(!StringUtil.checkXss(locationEntity.getCountry()) || !StringUtil.checkXss(locationEntity.getLocation())
                || !StringUtil.checkXss(locationEntity.getLatitude())
                || !StringUtil.checkXss(locationEntity.getLongitude())) {
            LOGGER.error("function=addLocation; msg=add Location error, because XSS injection.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.add.xss.check"));
        }
        if(checkLocation(locationEntity)) {
            LOGGER.error("function=addLocation; msg=add Location error, because location exist.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.add.entity.check"));
        }
        if(!checkLatitude(locationEntity)) {
            LOGGER.error("function=addLocation; msg=add Location error, because latitude or longitude illegal.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.add.latitude.check"));
        }
        if(null == locationEntity.getId() || locationEntity.getId().isEmpty()) {
            locationEntity.setId(UUID.randomUUID().toString());
        }
        LOGGER.error("function=addLocation; msg=add DO success, : " + locationEntity);
        return locationDao.addLocation(locationEntity);

    }

    @Override
    public int addLocationSelective(LocationEntity locationEntity) throws ServiceException {
        if(null == locationEntity) {
            LOGGER.error("function=addLocationSelective; msg=add error, because locationEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.add.entity.null"));
        }
        if(!StringUtil.checkXss(locationEntity.getCountry()) || !StringUtil.checkXss(locationEntity.getLocation())
                || !StringUtil.checkXss(locationEntity.getLatitude())
                || !StringUtil.checkXss(locationEntity.getLongitude())) {
            LOGGER.error("function=addLocation; msg=add Location error, because XSS injection.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.add.xss.check"));
        }
        if(null == locationEntity.getId() || locationEntity.getId().isEmpty()) {
            locationEntity.setId(UUID.randomUUID().toString());
        }
        return locationDao.addLocationSelective(locationEntity);
    }

    @Override
    public int updateLocationSelective(LocationEntity locationEntity) throws ServiceException {
        if(null == locationEntity) {
            LOGGER.error("function=updateLocationSelective; msg=update error, because locationEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.update.entity.check"));
        }
        if(!checkLatitude(locationEntity)) {
            LOGGER.error("function=updateLocationSelective; msg=update Location error, "
                    + "because latitude or longitude illegal.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.update.latitude.check"));
        }
        if(!checkModified(locationEntity)) {
            LOGGER.error("function=updateLocationSelective; msg=update Location error, "
                    + "because country or location be modified.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.update.modified.check"));
        }
        return locationDao.updateLocationSelective(locationEntity);
    }

    @Override
    public int updateLocation(LocationEntity locationEntity) throws ServiceException {
        if(null == locationEntity) {
            LOGGER.error("function=updateLocation; msg=update error, because locationEntity is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.update.entity.null"));
        }
        if(!checkLatitude(locationEntity)) {
            LOGGER.error("function=updateLocation; msg=update Location error, because latitude or longitude illegal.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.update.latitude.check"));
        }
        return locationDao.updateLocation(locationEntity);
    }

    public LocationDao getLocationDao() {
        return locationDao;
    }

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
}
