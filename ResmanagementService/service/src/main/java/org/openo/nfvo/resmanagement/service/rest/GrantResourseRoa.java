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

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.common.constant.HttpConstant;
import org.openo.nfvo.resmanagement.common.constant.UrlConstant;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.common.util.response.ResponseUtil;
import org.openo.nfvo.resmanagement.service.group.inf.GrantResService;
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
@Path(UrlConstant.GRANTRES_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GrantResourseRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrantResourseRoa.class);

    private GrantResService grantResService;

    @PUT
    @Path("/stub")
    public JSONObject grantResource(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);
        if(null == object) {
            LOGGER.error("function=grantResource; msg=grantResource error, because body is null.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.grantResource.null"));
        }

        LOGGER.info("GrantResourseRoa::grantResource:{}", object.toString());
        try {
            return grantResService.grantResource(object);
        } catch(ServiceException se) {
            LOGGER.error("GrantResourseRoa::grantResource error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    @PUT
    public JSONObject grantResourceReal(@Context HttpServletRequest context) throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);
        if(null == object) {
            LOGGER.error("function=grantResource; msg=grantResource error, because body is null.");
            throw new ServiceException(ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.grantResource.null"));
        }

        LOGGER.info("GrantResourseRoa::grantResource:{}", object.toString());
        try {
            return grantResService.grantResourceReal(object);
        } catch(ServiceException se) {
            LOGGER.error("GrantResourseRoa::grantResource error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setGrantResService(GrantResService grantResService) {
        this.grantResService = grantResService;
    }
}
