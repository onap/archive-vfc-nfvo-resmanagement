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

package org.openo.nfvo.resmanagement.service.dao.impl;

import java.util.List;
import java.util.Map;

import org.openo.nfvo.resmanagement.service.dao.impl.AbstractDao;
import org.openo.nfvo.resmanagement.service.dao.inf.LocationDao;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;
import org.openo.nfvo.resmanagement.service.mapper.LocationMapper;

/**
 *
 * Location DAO implemetation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class LocationDaoImpl extends AbstractDao implements LocationDao {

    @Override
    public LocationEntity getLocation(String id) {
        return getMapperManager(LocationMapper.class).getLocation(id);
    }

    @Override
    public List<String> getCountry() {
        return getMapperManager(LocationMapper.class).getCountry();
    }

    @Override
    public List<String> getLocationByCountry(Map<String, Object> condition) {
        return getMapperManager(LocationMapper.class).getLocationByCountry(condition);
    }

    @Override
    public List<LocationEntity> getLocations(Map<String, Object> condition) {
        return getMapperManager(LocationMapper.class).getLocations(condition);
    }

    @Override
    public int deleteLocation(String id) {
        return getMapperManager(LocationMapper.class).deleteLocation(id);
    }

    @Override
    public int addLocation(LocationEntity locationEntity) {
        return getMapperManager(LocationMapper.class).addLocation(locationEntity);
    }

    @Override
    public int addLocationSelective(LocationEntity locationEntity) {
        return getMapperManager(LocationMapper.class).addLocationSelective(locationEntity);
    }

    @Override
    public int updateLocationSelective(LocationEntity locationEntity) {
        return getMapperManager(LocationMapper.class).updateLocationSelective(locationEntity);
    }

    @Override
    public int updateLocation(LocationEntity locationEntity) {
        return getMapperManager(LocationMapper.class).updateLocation(locationEntity);
    }

}
