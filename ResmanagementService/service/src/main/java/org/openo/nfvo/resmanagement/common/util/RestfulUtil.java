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

package org.openo.nfvo.resmanagement.common.util;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.Restful;
import org.openo.baseservice.roa.util.restclient.RestfulAsyncCallback;
import org.openo.baseservice.roa.util.restclient.RestfulFactory;
import org.openo.baseservice.roa.util.restclient.RestfulOptions;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.nfvo.resmanagement.common.ResourceUtil;
import org.openo.nfvo.resmanagement.common.constant.Constant;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Restful Utility Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public class RestfulUtil {

    public static final String TYPE_GET = "get";

    public static final String TYPE_PUT = "put";

    public static final String TYPE_POST = "post";

    public static final String TYPE_DEL = "delete";

    public static final String CONTENT_TYPE = "Content-type";

    public static final String APPLICATION = "application/json";

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulUtil.class);

    private static final Restful REST_CLIENT = RestfulFactory.getRestInstance(RestfulFactory.PROTO_HTTP);

    private RestfulUtil() {
    }

    /**
     * Get response object.<br>
     *
     * @param url
     * @param type
     * @return
     * @since NFVO 0.5
     */
    public static JSONObject getResponseObj(String url, String type) {
        return getResponseObj(url, new RestfulParametes(), type);
    }

    /**
     * Get response object.<br>
     *
     * @param url
     * @param parametes
     * @param type
     * @return
     * @since NFVO 0.5
     */
    public static JSONObject getResponseObj(String url, RestfulParametes parametes, String type) {
        try {
            String content = RestfulUtil.getResponseContent(url, parametes, null, type);
            LOGGER.error("function=getResponseObj, content : {}", content);
            if(StringUtils.isEmpty(content)) {
                return null;
            }
            return JSONObject.fromObject(content);
        } catch(JSONException e) {
            LOGGER.error("function=getResponseObj, exception : {}", e);
            return null;
        }
    }

    /**
     * Get response content.<br>
     *
     * @param url
     * @param restParametes
     * @param type
     * @return
     * @since NFVO 0.5
     */
    public static String getResponseContent(String url, RestfulParametes restParametes, String type) {
        return getResponseContent(url, restParametes, null, type);
    }

    /**
     * Get response map.<br>
     *
     * @param url
     * @param restParametes
     * @param opt
     * @param type
     * @return
     * @since NFVO 0.5
     */
    public static Map<String, Object> getResponseMap(String url, RestfulParametes restParametes, RestfulOptions opt,
            String type) {
        RestfulResponse response = restfulResponse(url, restParametes, opt, type);
        return getResponseMap(response);
    }

    /**
     * Get response content map.<br>
     *
     * @param url
     * @param type
     * @return
     * @since NFVO 0.5
     */
    public static Map<String, Object> getResponseContentMap(String url, String type) {
        RestfulResponse response = restfulResponse(url, new RestfulParametes(), null, type);
        return getResponseMap(response);
    }

    private static Map<String, Object> getResponseMap(RestfulResponse response) {
        Map<String, Object> resMap = new HashMap<>(10);
        if(null != response) {
            resMap.put(Constant.RESPONSE_CONTENT, response.getResponseContent());
            resMap.put(Constant.STATUS_CODE, response.getStatus());
        }
        return resMap;
    }

    /**
     * Get response content.<br>
     *
     * @param url
     * @param restParametes
     * @param opt
     * @param type
     * @return
     * @since NFVO 0.5
     */
    public static String getResponseContent(String url, RestfulParametes restParametes, RestfulOptions opt,
            String type) {
        String responseContent = null;
        RestfulResponse rsp = restfulResponse(url, restParametes, opt, type);
        if(rsp != null) {
            int httpStatus = rsp.getStatus();
            LOGGER.warn("function=getResponseContent, get response httpStatusCode : {} ", httpStatus);
            if(httpStatus < HttpServletResponse.SC_BAD_REQUEST && httpStatus > 0) {
                responseContent = rsp.getResponseContent();
                LOGGER.warn("function=getResponseContent, get response data success!responseContent={}",
                        responseContent);
            }
        }
        return responseContent;
    }

    /**
     * Get restful response.<br>
     *
     * @param url
     * @param restParametes
     * @param type
     * @return
     * @since NFVO 0.5
     */
    public static RestfulResponse getRestfulResponse(String url, RestfulParametes restParametes, String type) {
        return restfulResponse(url, restParametes, null, type);
    }

    private static RestfulResponse restfulResponse(String url, RestfulParametes restParametes, RestfulOptions opt,
            String type) {
        RestfulResponse rsp = new RestfulResponse();
        try {

            if(REST_CLIENT != null) {
                if(TYPE_GET.equals(type)) {
                    rsp = REST_CLIENT.get(url, restParametes, opt);
                } else if(TYPE_POST.equals(type)) {
                    rsp = REST_CLIENT.post(url, restParametes, opt);
                } else if(TYPE_PUT.equals(type)) {
                    rsp = REST_CLIENT.put(url, restParametes, opt);
                } else if(TYPE_DEL.equals(type)) {
                    rsp = REST_CLIENT.delete(url, restParametes, opt);
                }
            }
        } catch(ServiceException e) {
            LOGGER.error("function=restfulResponse, get restful response catch exception {} ", e);
        }
        LOGGER.warn("function=restfulResponse, response status is {} ", rsp.getStatus());
        return rsp;
    }

    /**
     * encapsulate the java reflect exception.<br>
     *
     * @param methodName, Restful's method.
     * @param objects, method param array.
     * @return
     * @since NFVO 0.5
     */
    public static RestfulResponse getRestRes(String methodName, Object... objects) {
        try {
            if(objects == null || REST_CLIENT == null) {
                return null;
            }

            Class<?>[] classes = new Class[objects.length];
            for(int i = 0; i < objects.length; i++) {
                classes[i] = objects[i].getClass();
            }
            if(methodName.startsWith("async")) {
                classes[classes.length - 1] = RestfulAsyncCallback.class;
            }

            Class<?> rtType = methodName.startsWith("async") ? void.class : RestfulResponse.class;
            MethodType mt = MethodType.methodType(rtType, classes);
            Object result = MethodHandles.lookup().findVirtual(REST_CLIENT.getClass(), methodName, mt)
                    .bindTo(REST_CLIENT).invokeWithArguments(objects);
            if(result != null) {
                return (RestfulResponse)result;
            }
            LOGGER.warn("function=getRestRes, msg: invoke Restful async {} method which return type is Void.",
                    methodName);
            return null;
        } catch(ReflectiveOperationException e) {
            LOGGER.error("function=getRestRes, msg=error occurs, e={}.", e);
        } catch(Throwable e) {// NOSONAR
            LOGGER.error("function=getRestRes, msg=Throwable, e={}.", e);
            try {
                throw (ServiceException)new ServiceException().initCause(e.getCause());
            } catch(ServiceException se) {
                LOGGER.error("function=getRestRes, msg=ServiceException occurs, e={}.", se);
            }
        }
        return null;
    }

    /**
     * Get response.<br>
     *
     * @param restParametes
     * @param url
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    public static JSONArray getResponseRes(RestfulParametes restParametes, String url) throws ServiceException {
        String result = getResponseContent(url, restParametes, RestfulUtil.TYPE_GET);
        if(null == result || result.isEmpty()) {
            LOGGER.error("result from  url:" + url + " result:" + result);
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.group.resoperate.add.res.no.result"));
        }

        JSONArray rsArray = null;
        try {
            JSONObject rsJson = JSONObject.fromObject(result);
            rsArray = rsJson.getJSONArray(ParamConstant.PARAM_DATA);
        } catch(JSONException e) {
            LOGGER.error("getResources error:" + e);
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.group.resoperate.add.res.no.result"));
        }
        return rsArray;
    }

    /**
     * Get response.<br>
     *
     * @param restParametes
     * @param url
     * @param iResName
     * @return
     * @throws ServiceException
     * @since NFVO 0.5
     */
    public static JSONArray getResponseRes(RestfulParametes restParametes, String url, String iResName)
            throws ServiceException {
        String result = getResponseContent(url, restParametes, RestfulUtil.TYPE_GET);
        if(null == result || result.isEmpty()) {
            LOGGER.error("result from  url:" + url + " result:" + result);
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.group.resoperate.add.res.no.result"));
        }

        JSONArray rsArray = null;
        try {
            JSONObject rsJson = JSONObject.fromObject(result);
            rsArray = rsJson.getJSONArray(iResName);
            String vimId = rsJson.getString(ParamConstant.PARAM_VIMID);
            String vimName = rsJson.getString(ParamConstant.PARAM_VIMNAME);
            for(int i = 0; i < rsArray.size(); i++) {
                JSONObject jsonObj = rsArray.getJSONObject(i);
                jsonObj.put(ParamConstant.PARAM_VIMID, vimId);
                jsonObj.put(ParamConstant.PARAM_VIMNAME, vimName);
            }
        } catch(JSONException e) {
            LOGGER.error("getResources error:" + e);
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.group.resoperate.add.res.no.result"));
        }
        return rsArray;
    }

    /**
     * <br>
     * 
     * @param paramsMap
     * @param params
     * @return
     * @since NFVO 0.5
     */
    public static RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
        String url = paramsMap.get("url");
        String methodType = paramsMap.get("methodType");

        RestfulResponse rsp = null;
        Restful rest = RestfulFactory.getRestInstance(RestfulFactory.PROTO_HTTP);
        try {

            RestfulParametes restfulParametes = new RestfulParametes();
            Map<String, String> headerMap = new HashMap<>(3);
            headerMap.put(CONTENT_TYPE, APPLICATION);
            restfulParametes.setHeaderMap(headerMap);
            restfulParametes.setRawData(params);

            if(rest != null) {
                if(TYPE_GET.equalsIgnoreCase(methodType)) {
                    rsp = rest.get(url, restfulParametes);
                } else if(TYPE_POST.equalsIgnoreCase(methodType)) {
                    rsp = rest.post(url, restfulParametes);
                } else if(TYPE_PUT.equalsIgnoreCase(methodType)) {
                    rsp = rest.put(url, restfulParametes);
                } else if(TYPE_DEL.equalsIgnoreCase(methodType)) {
                    rsp = rest.delete(url, restfulParametes);
                }
            }
        } catch(ServiceException e) {
            LOGGER.error("function=getRemoteResponse, get restful response catch exception {}", e);
        }
        return rsp;
    }
}
