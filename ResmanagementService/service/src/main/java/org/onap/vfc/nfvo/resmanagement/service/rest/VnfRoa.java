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

package org.onap.vfc.nfvo.resmanagement.service.rest;

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

import org.onap.vfc.nfvo.resmanagement.common.ResourceUtil;
import org.onap.vfc.nfvo.resmanagement.common.constant.HttpConstant;
import org.onap.vfc.nfvo.resmanagement.common.constant.ParamConstant;
import org.onap.vfc.nfvo.resmanagement.common.constant.UrlConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.response.ResponseUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.response.RoaResponseUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.onap.vfc.nfvo.resmanagement.service.entity.VnfEntity;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.VnfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version VFC 1.0 Oct 28, 2016
 */
@Path(UrlConstant.VNF_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VnfRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualLinkRoa.class);

    private VnfService vnfService;

    @GET
    public JSONObject getVnfs(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> map = new HashMap<>(10);
        List<VnfEntity> vnfs = vnfService.getList(map);
        LOGGER.info("VnfRoa::getVnfs:{}", vnfs.toString());
        JSONObject result = new JSONObject();
        result.put("vnfs", vnfs);
        return result;
    }

    @GET
    @Path("/{vnfId}")
    public JSONObject getVnf(@Context HttpServletRequest context, @PathParam("vnfId") String id)
            throws ServiceException {
        LOGGER.info("VnfRoa::getVnf id:{}", id);
        Map<String, Object> map = new HashMap<>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<VnfEntity> vnfs = vnfService.getList(map);
        LOGGER.info("VnfRoa::getVnf:{}", vnfs.toString());
        JSONObject result = new JSONObject();
        result.put("vnfs", vnfs);
        return result;
    }

    @POST
    public JSONObject addVnf(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);
        if(null == object) {
            LOGGER.error("function=addVnf; msg=add error, because vnf is null.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.vnf.add.null"));
        }

        LOGGER.info("VnfRoa::addVnf:{}", object.toString());
        try {
            return vnfService.addVnf(VnfEntity.toEntity(object));
        } catch(ServiceException se) {
            LOGGER.error("VnfRoa::addVnf error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    public JSONObject deleteVnf(@Context HttpServletRequest context, @PathParam(ParamConstant.PARAM_ID) String id)
            throws ServiceException {
        if(id == null) {
            LOGGER.error("function=deleteVnf; msg=delete error, because id is null.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.vnf.delete.id.null"));
        }
        LOGGER.info("VnfRoa::deleteVnf id:{}", id);
        try {
            int result = vnfService.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("VnfRoa::deleteVnf error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setVnfService(VnfService vnfService) {
        this.vnfService = vnfService;
    }
}
