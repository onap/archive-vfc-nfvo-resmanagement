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

package org.openo.nfvo.resmanagement.service.adapter.impl;

import java.util.Map;

import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.nfvo.resmanagement.common.constant.Constant;
import org.openo.nfvo.resmanagement.common.constant.HttpConstant;
import org.openo.nfvo.resmanagement.common.util.RestfulUtil;
import org.openo.nfvo.resmanagement.service.adapter.inf.IResmgrAdapter2MSBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Sep 22, 2016
 */
public class ResmgrAdapter2MSBManager implements IResmgrAdapter2MSBManager {

    private static final Logger LOG = LoggerFactory.getLogger(ResmgrAdapter2MSBManager.class);

    @Override
    public JSONObject registerResmgr(Map<String, String> paramsMap, JSONObject driverInfo) {
        JSONObject resultObj = new JSONObject();

        RestfulResponse rsp = RestfulUtil.getRemoteResponse(paramsMap, driverInfo.toString());
        if(null == rsp) {
            LOG.error("function=registerResmgr,  RestfulResponse is null");
            resultObj.put("reason", "RestfulResponse is null.");
            resultObj.put("retCode", Constant.ERROR_CODE);
            return resultObj;
        }
        LOG.warn("function=registerResmgr, status={}, content={}.", rsp.getStatus(), rsp.getResponseContent());
        String resultCreate = rsp.getResponseContent();

        if(rsp.getStatus() == HttpConstant.HTTP_CREATED) {
            LOG.warn("function=registerResmgr, msg= status={}, result={}.", rsp.getStatus(), resultCreate);
            resultObj = JSONObject.fromObject(resultCreate);
            resultObj.put("retCode", HttpConstant.HTTP_CREATED);
            return resultObj;
        } else if(rsp.getStatus() == HttpConstant.HTTP_INVALID_PARAMETERS) {
            LOG.error("function=registerResmgr, msg=MSB return fail,invalid parameters,status={}, result={}.",
                    rsp.getStatus(), resultCreate);
            resultObj.put("reason", "MSB return fail,invalid parameters.");
        } else if(rsp.getStatus() == HttpConstant.HTTP_INNERERROR_CODE) {
            LOG.error("function=registerResmgr, msg=MSB return fail,internal system error,status={}, result={}.",
                    rsp.getStatus(), resultCreate);
            resultObj.put("reason", "MSB return fail,internal system error.");
        }
        resultObj.put("retCode", Constant.ERROR_CODE);
        return resultObj;
    }

    @Override
    public JSONObject unregisterResmgr(Map<String, String> paramsMap) {
        JSONObject resultObj = new JSONObject();

        RestfulResponse rsp = RestfulUtil.getRemoteResponse(paramsMap, "");
        if(null == rsp) {
            LOG.error("function=unregisterResmgr,  RestfulResponse is null");
            resultObj.put("reason", "RestfulResponse is null.");
            resultObj.put("retCode", Constant.ERROR_CODE);
            return resultObj;
        }
        String resultCreate = rsp.getResponseContent();

        if(rsp.getStatus() == HttpConstant.HTTP_NOCONTENT) {
            LOG.warn("function=unregisterResmgr, msg= status={}, result={}.", rsp.getStatus(), resultCreate);
            resultObj = JSONObject.fromObject(resultCreate);
            resultObj.put("retCode", HttpConstant.HTTP_NOCONTENT);
            return resultObj;
        } else if(rsp.getStatus() == HttpConstant.HTTP_NOTFOUND_CODE) {
            LOG.error(
                    "function=unregisterResmgr, msg=MSB return fail,can't find the service instance.status={}, result={}.",
                    rsp.getStatus(), resultCreate);
            resultObj.put("reason", "MSB return fail,can't find the service instance.");
        } else if(rsp.getStatus() == HttpConstant.HTTP_INVALID_PARAMETERS) {
            LOG.error("function=unregisterResmgr, msg=MSB return fail,invalid parameters,status={}, result={}.",
                    rsp.getStatus(), resultCreate);
            resultObj.put("reason", "MSB return fail,invalid parameters.");
        } else if(rsp.getStatus() == HttpConstant.HTTP_INNERERROR_CODE) {
            LOG.error("function=unregisterResmgr, msg=MSB return fail,internal system error,status={}, result={}.",
                    rsp.getStatus(), resultCreate);
            resultObj.put("reason", "MSB return fail,internal system error.");
        }
        resultObj.put("retCode", Constant.ERROR_CODE);
        return resultObj;
    }

}
