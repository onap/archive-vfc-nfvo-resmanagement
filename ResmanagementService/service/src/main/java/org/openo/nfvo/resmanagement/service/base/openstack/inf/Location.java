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

package org.openo.nfvo.resmanagement.service.base.openstack.inf;

import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;

import net.sf.json.JSONObject;

/**
 * Location details class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public interface Location extends ResManagement {

    /**
     * <br>
     *
     * @param id
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    Map<String, Object> get(String id) throws ServiceException;

    /**
     * Get list of locations.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    List<LocationEntity> get(Map<String, Object> condition) throws ServiceException;

    /**
     * Get location details.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    LocationEntity getLocation(Map<String, Object> condition) throws ServiceException;

    /**
     * Get location info.<br>
     *
     * @param locationInfo
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    List<JSONObject> getLocationInfo(List<LocationEntity> locationInfo) throws ServiceException;

    /**
     * Get Country.<br>
     *
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    List<String> getCountry() throws ServiceException;

    /**
     * Get location by country.<br>
     *
     * @param condition
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    List<String> getLocationByCountry(Map<String, Object> condition) throws ServiceException;

    /**
     * Get cloud services.<br>
     *
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    List<String> getCloudservice() throws ServiceException;
}
