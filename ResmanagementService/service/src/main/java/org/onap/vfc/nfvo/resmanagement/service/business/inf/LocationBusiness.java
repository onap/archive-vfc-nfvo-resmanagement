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

package org.onap.vfc.nfvo.resmanagement.service.business.inf;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.LocationEntity;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

/**
 *
 * Location Business Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     VFC 1.0  Sep 10, 2016
 */
public interface LocationBusiness {

    /**
     *
     * Get Location.<br>
     *
     * @param location
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    LocationEntity getLocation(String location) throws ServiceException;

    /**
     *
     * Get Locations.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException;

    /**
     *
     * Delete Location.<br>
     *
     * @param location
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    int deleteLocation(String location) throws ServiceException;

    /**
     *
     * Add Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    int addLocation(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Add Selective Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    int addLocationSelective(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Update Selective Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    int updateLocationSelective(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Update Location.<br>
     *
     * @param locationEntity
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    int updateLocation(LocationEntity locationEntity) throws ServiceException;

    /**
     *
     * Get Country.<br>
     *
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    List<String> getCountry() throws ServiceException;

    /**
     *
     * Get Location By Country.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    List<String> getLocationByCountry(Map<String, Object> condition) throws ServiceException;

}
