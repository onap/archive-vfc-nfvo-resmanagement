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

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import org.openo.nfvo.resmanagement.service.group.inf.ResOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * Resource Operate ROA Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
@Path(UrlConstant.RESOPERATE_URL)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResOperateRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResOperateRoa.class);

    private ResOperateService resOperateService;

    /**
     * Update iResource pool.<br>
     *
     * @param context
     * @param tenantId
     * @param vimId
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @PUT
    @Path(UrlConstant.MODRES_URL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject updateIResPool(@Context HttpServletRequest context,
            @QueryParam(ParamConstant.PARAM_VIMID) String vimId) throws ServiceException {
        JSONObject json = RequestUtil.getAllJsonRequestBody(context);
        LOGGER.warn("ResPoolRoa::modVimId :{}", vimId);
        JSONObject vimInfo = VimUtil.getVimById(vimId);
        String tenant = vimInfo.getString("tenant");
        String tenantId = VimUtil.getTenantIdByName(tenant, vimId);
        try {
            resOperateService.updateIRes(tenantId, vimId, json);
            resOperateService.sendMsgMonitor("update", vimId);
            return RoaResponseUtil.update(HttpConstant.OK_CODE);
        } catch(ServiceException se) {
            LOGGER.error("ResOperateRoa::updateIResPool error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * Add all resource pool.<br>
     *
     * @param context
     * @param tenantId
     * @param vimId
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @POST
    @Path(UrlConstant.ADDRES_URL)
    @Consumes(UrlConstant.APPLICATION_TYPE)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject addAllResPool(@Context HttpServletRequest context,
            @QueryParam(ParamConstant.PARAM_TENANTID) String tenantId,
            @QueryParam(ParamConstant.PARAM_VIMID) String vimId) throws ServiceException {
        JSONObject json = RequestUtil.getAllJsonRequestBody(context);
        LOGGER.warn("ResOperateRoa::vimId :{}", vimId);

        try {
            resOperateService.addIRes(tenantId, vimId, json);
            resOperateService.sendMsgMonitor("create", vimId);
            return RoaResponseUtil.add(HttpConstant.OK_CODE);
        } catch(ServiceException se) {
            LOGGER.error("ResOperateRoa::addAllResPool error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    /**
     * Delete iResource.<br>
     *
     * @param context
     * @param vimId
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject deleteIRes(@Context HttpServletRequest context,
            @QueryParam(ParamConstant.PARAM_VIMID) String vimId) throws ServiceException {
        LOGGER.warn("ResOperateRoa::deleteIResource vimId:{}", vimId);

        try {
            resOperateService.sendMsgMonitor("delete", vimId);
            int result = resOperateService.deleteIRes(vimId);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("ResOperateRoa::deleteIRes error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setResOperateService(ResOperateService resOperateService) {
        this.resOperateService = resOperateService;
    }
}
