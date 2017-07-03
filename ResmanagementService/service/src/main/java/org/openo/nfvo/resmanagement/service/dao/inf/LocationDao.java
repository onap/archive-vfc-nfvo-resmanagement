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

package org.openo.nfvo.resmanagement.service.dao.inf;

import java.util.List;
import java.util.Map;

import org.openo.nfvo.resmanagement.service.entity.LocationEntity;

/**
 *
 * Location DAO Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public interface LocationDao {

    /**
     *
     * Get Location.<br>
     *
     * @param id
     * @return
     * @since  NFVO 0.5
     */
    LocationEntity getLocation(String id);

    /**
     *
     * Get Locations.<br>
     *
     * @param condition
     * @return
     * @since  NFVO 0.5
     */
    List<LocationEntity> getLocations(Map<String, Object> condition);

    /**
     *
     * Delete Location.<br>
     *
     * @param location
     * @return
     * @since  NFVO 0.5
     */
    int deleteLocation(String location);

    /**
     *
     * Add Location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  NFVO 0.5
     */
    int addLocation(LocationEntity sodoResEntity);

    /**
     *
     * Add Selective Location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  NFVO 0.5
     */
    int addLocationSelective(LocationEntity sodoResEntity);

    /**
     *
     * Update selective location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  NFVO 0.5
     */
    int updateLocationSelective(LocationEntity sodoResEntity);

    /**
     *
     * Update Location.<br>
     *
     * @param sodoResEntity
     * @return
     * @since  NFVO 0.5
     */
    int updateLocation(LocationEntity sodoResEntity);

    /**
     *
     * Get Country.<br>
     *
     * @return
     * @since  NFVO 0.5
     */
    List<String> getCountry();

    /**
     *
     * Get Location by Country.<br>
     *
     * @param condition
     * @return
     * @since  NFVO 0.5
     */
    List<String> getLocationByCountry(Map<String, Object> condition);
}
