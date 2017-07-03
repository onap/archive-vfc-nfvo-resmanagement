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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.common.constant.HttpConstant;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.constant.UrlConstant;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.common.util.response.ResponseUtil;
import org.openo.nfvo.resmanagement.common.util.response.RoaResponseUtil;
import org.openo.nfvo.resmanagement.service.entity.VnfStatusEntity;
import org.openo.nfvo.resmanagement.service.group.inf.VnfStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Oct 29, 2016
 */
@Path(UrlConstant.VNFSTATUS_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VnfStatusRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(VnfStatusRoa.class);

    private VnfStatusService vnfStatusService;

    @GET
    public JSONObject getVnfStatuss(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> map = new HashMap<>(10);
        List<VnfStatusEntity> vnfStatus = vnfStatusService.getList(map);
        LOGGER.info("VnfStatusRoa::getVnfStatuss:{}", vnfStatus.toString());
        JSONObject result = new JSONObject();
        result.put("vnfStatus", vnfStatus);
        return result;
    }

    @GET
    @Path("/{id}")
    public JSONObject getVnfStatus(@Context HttpServletRequest context, @PathParam("id") String id)
            throws ServiceException {
        LOGGER.info("VnfStatusRoa::getVnfStatus id:{}", id);
        Map<String, Object> map = new HashMap<>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<VnfStatusEntity> vnfStatus = vnfStatusService.getList(map);
        LOGGER.info("VnfStatusRoa::getVnfStatus:{}", vnfStatus.toString());
        JSONObject result = new JSONObject();
        result.put("vnfStatus", vnfStatus);
        return result;
    }

    @POST
    public JSONObject addVnfStatus(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);
        if(null == object) {
            LOGGER.error("function=addVnfInfo; msg=add error, because vnfStatus is null.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.vnfStatus.add.null"));
        }

        LOGGER.info("VnfStatusRoa::addVnfStatus:{}", object.toString());
        try {
            return vnfStatusService.addVnfStatus(object);
        } catch(ServiceException se) {
            LOGGER.error("VnfStatusRoa::addVnfStatus error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    public JSONObject deleteVnfStatus(@Context HttpServletRequest context, @PathParam(ParamConstant.PARAM_ID) String id)
            throws ServiceException {
        if(id == null) {
            LOGGER.error("function=deleteVnfStatus; msg=delete error, because id is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.vnfStatus.delete.id.null"));
        }
        LOGGER.info("VnfStatusRoa::deleteVnfStatus id:{}", id);
        try {
            int result = vnfStatusService.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("VnfStatusRoa::deleteVnfStatys error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setVnfStatusService(VnfStatusService vnfStatusService) {
        this.vnfStatusService = vnfStatusService;
    }
}
