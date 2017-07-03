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
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Network;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 *
 * Network ROA Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
@Path(UrlConstant.NETWORKS_URL)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class NetworkRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkRoa.class);

    private Network network;

    /**
     *
     * Get details of networks.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @GET
    public JSONObject getNetworks(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> map = new HashMap<>(10);
        List<NetworkEntity> networks = network.getList(map);

        JSONObject result = new JSONObject();
        result.put("networks", networks.toString());
        return result;
    }

    /**
     *
     * Get network details.<br>
     *
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @GET
    @Path("/{networkId}")
    public JSONObject getNetwork(@Context HttpServletRequest context, @PathParam("networkId") String id)
            throws ServiceException {
        LOGGER.info("NetworkRoa::getNetwork id:{}", id);
        Map<String, Object> map = new HashMap<>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<NetworkEntity> networks = network.getList(map);

        JSONObject result = new JSONObject();
        result.put("networks", networks.toString());
        return result;
    }

    /**
     *
     * Add network.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject addNetwork(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);

        LOGGER.info("NetworkRoa::addNetwork:{}", object.toString());
        try {
            int result = network.add(NetworkEntity.toEntity(object));
            return RoaResponseUtil.add(result);
        } catch(ServiceException se) {
            LOGGER.error("NetworkRoa::addNetwork error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     *
     * Delete network.<br>
     *
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject deleteNetwork(@Context HttpServletRequest context, @QueryParam(ParamConstant.PARAM_ID) String id)
            throws ServiceException {
        LOGGER.info("NetworkRoa::deleteNetwork id:{}", id);
        try {
            int result = network.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("NetworkRoa::deleteNetwork error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     *
     * Update network.<br>
     *
     * @param context
     * @return
     * @throws ServiceException
     * @since  NFVO 0.5
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject updateNetwork(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);

        LOGGER.info("NetworkRoa::updateNetwork:{}", object.toString());
        try {
            int result = network.update(object);
            return RoaResponseUtil.update(result);
        } catch(ServiceException se) {
            LOGGER.error("NetworkRoa::updateNetwork error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
