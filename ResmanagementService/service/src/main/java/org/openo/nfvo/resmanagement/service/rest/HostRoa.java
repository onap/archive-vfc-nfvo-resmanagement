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

package org.openo.nfvo.resmanagement.service.rest;

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
import org.openo.nfvo.resmanagement.common.constant.HttpConstant;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.constant.UrlConstant;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.common.util.response.ResponseUtil;
import org.openo.nfvo.resmanagement.common.util.response.RoaResponseUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Host;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 *
 * Host ROA Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
@Path(UrlConstant.HOST_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HostRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostRoa.class);

    private Host host;

    /**
     *
     * Get hosts.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @GET
    public JSONObject getHosts(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> map = new HashMap<>(10);
        List<HostEntity> hosts = host.getList(map);

        JSONObject result = new JSONObject();
        result.put("hosts", hosts);
        return result;
    }

    /**
     *
     * Get host.<br>
     *
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @GET
    @Path("/{hostId}")
    public JSONObject getHost(@Context HttpServletRequest context, @PathParam("hostId") String id)
            throws ServiceException {
        LOGGER.info("HostRoa::getHost id:{}", id);
        Map<String, Object> map = new HashMap<>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<HostEntity> hosts = host.getList(map);

        JSONObject result = new JSONObject();
        result.put("hosts", hosts);
        return result;
    }

    /**
     *
     * Add host.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @POST
    public JSONObject addHost(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);

        LOGGER.info("HostRoa::addHost:{}", object.toString());
        try {
            int result = host.add(object);
            return RoaResponseUtil.add(result);
        } catch(ServiceException se) {
            LOGGER.error("HostRoa::addHost error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     *
     * Delete host.<br>
     *
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @DELETE
    public JSONObject deleteHost(@Context HttpServletRequest context, @QueryParam(ParamConstant.PARAM_ID) String id)
            throws ServiceException {
        LOGGER.info("HostRoa::deleteHost id:{}", id);
        try {
            int result = host.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("HostRoa::deleteHost error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     *
     * Update host.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @PUT
    public JSONObject updateHost(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);

        LOGGER.info("HostRoa::updateHost:{}", object.toString());
        try {
            int result = host.update(object);
            return RoaResponseUtil.update(result);
        } catch(ServiceException se) {
            LOGGER.error("HostRoa::updateHost error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
