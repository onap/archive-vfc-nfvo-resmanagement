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

package org.openo.nfvo.resmanagement.service.business.inf;

import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;

/**
 *
 * Location Business Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public interface LocationBusiness {

    /**
     *
     * Get Location.<br>
     *
     * @param location
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    LocationEntity getLocation(String location) throws ServiceException;

    /**
     *
     * Get Locations.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException;

    /**
     *
     * Delete Location.<br>
     *
     * @param location
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int deleteLocation(String location) throws ServiceException;

    /**
     *
     * Add Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addLocation(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Add Selective Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int addLocationSelective(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Update Selective Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateLocationSelective(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Update Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    int updateLocation(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Get Country.<br>
     *
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    List<String> getCountry() throws ServiceException;

    /**
     *
     * Get Location By Country.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    List<String> getLocationByCountry(Map<String, Object> condition) throws ServiceException;

}
