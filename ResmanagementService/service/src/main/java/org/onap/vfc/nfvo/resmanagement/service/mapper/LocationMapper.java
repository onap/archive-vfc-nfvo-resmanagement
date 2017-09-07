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

package org.onap.vfc.nfvo.resmanagement.service.mapper;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.LocationEntity;

/**
 *
 * Location Mapper Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     VFC 1.0  Sep 10, 2016
 */
public interface LocationMapper {

    /**
     *
     * Get location.<br>
     *
     * @param id
     * @return
     * @since  VFC 1.0
     */
    LocationEntity getLocation(String id);

    /**
     *
     * Get Country.<br>
     *
     * @return
     * @since  VFC 1.0
     */
    List<String> getCountry();

    /**
     *
     * Get location by country.<br>
     *
     * @param condition
     * @return
     * @since  VFC 1.0
     */
    List<String> getLocationByCountry(Map<String, Object> condition);

    /**
     *
     * get locations.<br>
     *
     * @param condition
     * @return
     * @since  VFC 1.0
     */
    List<LocationEntity> getLocations(Map<String, Object> condition);

    /**
     *
     * Delete location.<br>
     *
     * @param location
     * @return
     * @since  VFC 1.0
     */
    int deleteLocation(String location);

    /**
     *
     * Add location.<br>
     *
     * @param locationEntity
     * @return
     * @since  VFC 1.0
     */
    int addLocation(LocationEntity locationEntity);

    /**
     *
     * Add selective location.<br>
     *
     * @param locationEntity
     * @return
     * @since  VFC 1.0
     */
    int addLocationSelective(LocationEntity locationEntity);

    /**
     *
     * Update selective location.<br>
     *
     * @param locationEntity
     * @return
     * @since  VFC 1.0
     */
    int updateLocationSelective(LocationEntity locationEntity);

    /**
     *
     * Update location.<br>
     *
     * @param locationEntity
     * @return
     * @since  VFC 1.0
     */
    int updateLocation(LocationEntity locationEntity);
}
