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

package org.openo.nfvo.resmanagement.service.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.common.constant.HttpConstant;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.constant.UrlConstant;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.common.util.response.ResponseUtil;
import org.openo.nfvo.resmanagement.common.util.response.RoaResponseUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Location;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Sites;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * Location ROA Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
@Path(UrlConstant.LOCATION_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationRoa.class);

    private Location location;

    private Sites sites;

    /**
     * Get Locations Base.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @GET
    public JSONObject getLocationsbase(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> condition = new HashMap<>();
        List<LocationEntity> locations = location.get(condition);

        JSONObject result = new JSONObject();
        result.put("locations", locations);
        return result;
    }

    /**
     * Get Locations Base.<br>
     *
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @GET
    @Path("/{locationId}")
    public JSONObject getLocationbase(@Context HttpServletRequest context, @PathParam("locationId") String id)
            throws ServiceException {
        LOGGER.info("LocationRoa::getLocationbase id:{}", id);
        Map<String, Object> map = new HashMap<>();
        map.put(ParamConstant.PARAM_ID, id);
        List<LocationEntity> locations = location.get(map);

        JSONObject result = new JSONObject();
        result.put("locations", locations);
        return result;
    }

    /**
     * Get Country.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @GET
    @Path("/country")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getCountry(@Context HttpServletRequest context) throws ServiceException {
        return RoaResponseUtil.get(location.getCountry());
    }

    /**
     * Get Location by Country.<br>
     *
     * @param context
     * @param country
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @GET
    @Path("/locationbycountry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getLocationByCountry(@Context HttpServletRequest context,
            @QueryParam(ParamConstant.PARAM_COUNTRY) String country) throws ServiceException {
        LOGGER.info("LocationRoa::getLocationByCountry country:{}", country);
        Map<String, Object> condition = new HashMap<>();
        condition.put(ParamConstant.PARAM_COUNTRY, country);
        return RoaResponseUtil.get(location.getLocationByCountry(condition));
    }

    /**
     * Get Cloud Service.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @GET
    @Path("/cloudservice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getCloudservice(@Context HttpServletRequest context) throws ServiceException {
        return RoaResponseUtil.get(location.getCloudservice());
    }

    /**
     * Get location details.<br>
     *
     * @param context
     * @param locations
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @GET
    @Path("/site")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getLocation(@Context HttpServletRequest context,
            @QueryParam(ParamConstant.PARAM_LOCATION) String locations) throws ServiceException {
        LOGGER.info("LocationRoa::getLocation locations:{}", locations);
        Map<String, Object> condition = new HashMap<>();
        List<LocationEntity> loca = new ArrayList<LocationEntity>();
        condition.put(ParamConstant.PARAM_LOCATION, locations);
        loca = location.get(condition);
        LOGGER.info("LocationRoa::getLocation loca:{}", loca);
        return RoaResponseUtil.get(location.getLocationInfo(loca));
    }

    /**
     * Add Location.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject addLocation(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);

        LOGGER.info("LocationRoa::addLocation : " + object.toString());
        try {
            int result = location.add(object);
            return RoaResponseUtil.add(result);
        } catch(ServiceException se) {
            LOGGER.error("LocationRoa::addLocation error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * Delete Location Base.<br>
     *
     * @param context
     * @param locations
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @DELETE
    @Path("/{location}")
    public JSONObject deleteLocationbase(@Context HttpServletRequest context,
            @PathParam(ParamConstant.PARAM_LOCATION) String locations) throws ServiceException {
        LOGGER.info("LocationRoa::deleteLocation locations:{}", locations);
        Map<String, Object> condition = new HashMap<>();
        condition.put(ParamConstant.PARAM_LOCATION, locations);
        try {
            int result = location.delete(locations);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("LocationRoa::deleteLocationbase error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * Delete Location.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject deleteLocation(@Context HttpServletRequest context) throws ServiceException {

        JSONObject object = RequestUtil.getJsonRequestBody(context);
        String locations = JsonUtil.getJsonFieldStr(object, ParamConstant.PARAM_LOCATION);
        String id = JsonUtil.getJsonFieldStr(object, ParamConstant.PARAM_ID);
        LOGGER.info("LocationRoa::deleteLocation locations:{}", locations);

        Map<String, Object> condition = new HashMap<>();
        condition.put(ParamConstant.PARAM_LOCATION, locations);
        SitesEntity sitesEntity = sites.get(condition);
        try {
            if(sitesEntity != null) {
                return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE,
                        ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.delete.used.check"));
            }
            int result = location.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("LocationRoa::deleteLocation error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * Update Location.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject updateLocation(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);
        String local = JsonUtil.getJsonFieldStr(object, ParamConstant.PARAM_LOCATION);
        Map<String, Object> localInfo = new HashMap<>();
        localInfo.put(ParamConstant.PARAM_LOCATION, local);
        SitesEntity sitesEntity = sites.get(localInfo);
        if(sitesEntity != null) {
            LOGGER.error("function=updateLocation; msg=update error, because location is used.");
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE,
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.location.update.used.check"));
        }
        LOGGER.info("LocationRoa::updateLocation : " + object.toString());

        try {
            int result = location.update(object);
            return RoaResponseUtil.update(result);
        } catch(ServiceException se) {
            LOGGER.error("LocationRoa::update Location error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSites(Sites sites) {
        this.sites = sites;
    }

}
