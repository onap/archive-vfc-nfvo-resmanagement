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
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Port;
import org.openo.nfvo.resmanagement.service.entity.PortEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 *
 * Port ROA Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
@Path(UrlConstant.PORT_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortRoa.class);

    private Port port;

    /**
     *
     * Get details of Ports.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @GET
    public JSONObject getPorts(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> map = new HashMap<>(10);
        List<PortEntity> ports = port.getList(map);

        JSONObject result = new JSONObject();
        result.put("ports", ports);
        return result;
    }

    /**
     *
     * Get port details.<br>
     *
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @GET
    @Path("/{portId}")
    public JSONObject getPort(@Context HttpServletRequest context, @PathParam("portId") String id)
            throws ServiceException {
        LOGGER.info("PortRoa::getPort id:{}", id);
        Map<String, Object> map = new HashMap<>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<PortEntity> ports = port.getList(map);

        JSONObject result = new JSONObject();
        result.put("ports", ports);
        return result;
    }

    /**
     *
     * Add port.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @POST
    public JSONObject addPort(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);

        LOGGER.info("PortRoa::addPort:{}", object.toString());
        try {
            int result = port.add(PortEntity.toEntity(object));
            return RoaResponseUtil.add(result);
        } catch(ServiceException se) {
            LOGGER.error("PortRoa::addPort error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     *
     * Delete port.<br>
     *
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @DELETE
    public JSONObject deletePort(@Context HttpServletRequest context, @QueryParam(ParamConstant.PARAM_ID) String id)
            throws ServiceException {
        LOGGER.info("PortRoa::deletePort id:{}", id);
        try {
            int result = port.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("PortRoa::deletePort error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     *
     * Update port.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @PUT
    public JSONObject updatePort(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);

        LOGGER.info("PortRoa::updatePort:{}", object.toString());
        try {
            int result = port.update(object);
            return RoaResponseUtil.update(result);
        } catch(ServiceException se) {
            LOGGER.error("PortRoa::updatePort error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setPort(Port port) {
        this.port = port;
    }
}
