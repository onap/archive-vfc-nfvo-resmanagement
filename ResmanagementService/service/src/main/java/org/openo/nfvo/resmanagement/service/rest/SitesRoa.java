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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.VimUtil;
import org.openo.nfvo.resmanagement.common.constant.HttpConstant;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.constant.UrlConstant;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.common.util.response.ResponseUtil;
import org.openo.nfvo.resmanagement.common.util.response.RoaResponseUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Sites;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Sites ROA method<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 24, 2016
 */
@Path(UrlConstant.SITES_URL)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SitesRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(SitesRoa.class);

    private Sites sites;

    /**
     * getSites ROA method<br/>
     *
     * @param context
     * @return the get result
     * @throws ServiceException When get failed.
     * @since NFVO 0.5
     */
    @GET
    public JSONObject getSites(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>(10);
        List<SitesEntity> datacenters = sites.getList(map);

        JSONObject result = new JSONObject();
        result.put("datacenters", datacenters);
        return result;
    }

    /**
     * getSite ROA method<br/>
     *
     * @param context
     * @param id
     * @return the get result
     * @throws ServiceException When get failed.
     * @since NFVO 0.5
     */
    @GET
    @Path("/{datacenterId}")
    public JSONObject getSite(@Context HttpServletRequest context, @PathParam("datacenterId") String id)
            throws ServiceException {
        LOGGER.warn("SitesRoa::getSitesById id:{}", id);
        Map<String, Object> map = new HashMap<String, Object>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<SitesEntity> datacenters = sites.getList(map);

        JSONObject result = new JSONObject();
        result.put("datacenters", datacenters);
        return result;
    }

    /**
     * addSites ROA method<br/>
     *
     * @param context
     * @param id
     * @return the add result
     * @throws ServiceException When add failed.
     * @since NFVO 0.5
     */
    @POST
    public JSONObject addSites(@Context HttpServletRequest context) throws ServiceException {
        JSONObject json = RequestUtil.getAllJsonRequestBody(context);

        LOGGER.warn("SitesRoa:: start add Sites");
        try {
            int result = sites.add(json);
            sites.sendToMonitor(json);
            return RoaResponseUtil.add(result);
        } catch(ServiceException se) {
            LOGGER.error("SitesRoa::addSites error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * deleteSites ROA method<br/>
     *
     * @param context
     * @param id
     * @return the delete result
     * @throws ServiceException When delete failed.
     * @since NFVO 0.5
     */
    @DELETE
    @Path("/{datacenterId}")
    public JSONObject deleteSites(@Context HttpServletRequest context, @PathParam("datacenterId") String id)
            throws ServiceException {
        LOGGER.warn("SitesRoa::deleteSites siteId:{}", id);
        try {
            int result = sites.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("SitesRoa::deleteSites error: " + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * updateSites ROA method<br/>
     *
     * @param context
     * @param id
     * @return the update result
     * @throws ServiceException When update failed.
     * @since NFVO 0.5
     */
    @PUT
    public JSONObject updateSites(@Context HttpServletRequest context) throws ServiceException {
        JSONObject json = RequestUtil.getAllJsonRequestBody(context);

        LOGGER.warn("SitesRoa::start update Sites");
        try {
            int result = sites.update(SitesEntity.toEntity(json));
            return RoaResponseUtil.update(result);
        } catch(ServiceException se) {
            LOGGER.error("SitesRoa::updateSites error:" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * grant resource method
     * <br>
     * 
     * @param context
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @PUT
    @Path("/grant")
    public JSONObject grantResource(@Context HttpServletRequest context) throws ServiceException {
        JSONObject json = RequestUtil.getAllJsonRequestBody(context);

        LOGGER.warn("SitesRoa::grant resource");
        try {
            int result = sites.update(json);
            return RoaResponseUtil.update(result);
        } catch(ServiceException se) {
            LOGGER.error("SitesRoa::grant resource:" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    @GET
    @Path("/vims")
    public String getVims(@Context HttpServletRequest context) throws ServiceException {
        LOGGER.info("SitesRoa::get vims");
        JSONArray vims = VimUtil.getVims();
        JSONObject result = new JSONObject();
        result.put("data", vims);
        return result.toString();
    }

    @GET
    @Path("/vims/{vimId}")
    public String getVim(@Context HttpServletRequest context, @PathParam("vimId") String vimId)
            throws ServiceException {
        LOGGER.info("SitesRoa::get vim by id: {}", vimId);
        return VimUtil.getVimById(vimId).toString();
    }

    public void setSites(Sites sites) {
        this.sites = sites;
    }
}
