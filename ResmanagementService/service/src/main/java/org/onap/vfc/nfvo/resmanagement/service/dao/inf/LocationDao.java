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

package org.onap.vfc.nfvo.resmanagement.service.dao.inf;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.LocationEntity;

/**
 *
 * Location DAO Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     VFC 1.0  Sep 10, 2016
 */
public interface LocationDao {

    /**
     *
     * Get Location.<br>
     *
     * @param id
     * @return
     * @since  VFC 1.0
     */
    LocationEntity getLocation(String id);

    /**
     *
     * Get Locations.<br>
     *
     * @param condition
     * @return
     * @since  VFC 1.0
     */
    List<LocationEntity> getLocations(Map<String, Object> condition);

    /**
     *
     * Delete Location.<br>
     *
     * @param location
     * @return
     * @since  VFC 1.0
     */
    int deleteLocation(String location);

    /**
     *
     * Add Location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  VFC 1.0
     */
    int addLocation(LocationEntity sodoResEntity);

    /**
     *
     * Add Selective Location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  VFC 1.0
     */
    int addLocationSelective(LocationEntity sodoResEntity);

    /**
     *
     * Update selective location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  VFC 1.0
     */
    int updateLocationSelective(LocationEntity sodoResEntity);

    /**
     *
     * Update Location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  VFC 1.0
     */
    int updateLocation(LocationEntity sodoResEntity);

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
     * Get Location by Country.<br>
     *
     * @param condition
     * @return
     * @since  VFC 1.0
     */
    List<String> getLocationByCountry(Map<String, Object> condition);
}
