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

package org.onap.vfc.nfvo.resmanagement.common.util.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulClientConst;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * <br/>
 * <p>
 * Utility used for request
 * </p>
 *
 * @author
 * @version VFC 1.0 2016-3-17
 */
public final class RequestUtil {

    private static final Logger LOGGER = LogManager.getLogger(RequestUtil.class);

    /**
     * Constructor<br/>
     * <p>
     * </p>
     *
     * @since VFC 1.0
     */
    private RequestUtil() {
    }

    /**
     * Get context string from http context
     *
     * @param context
     *         http context
     * @return the needed string in http context
     */
    public static String getStringRequestBody(HttpServletRequest context) {
        try {
            InputStream input = context.getInputStream();
            return IOUtils.toString(input);
        } catch(IOException e) {
            LOGGER.error("function=getStringRequestBody, get httpservletrequest body exception: {}", e);
            return null;
        }
    }

    /**
     * Get json parameter from http context
     *
     * @param context
     *         http context
     * @return JSONObject
     */
    public static JSONObject getJsonRequestBody(HttpServletRequest context) {
        try {
            String bodyStr = getStringRequestBody(context);
            return JSONObject.fromObject(bodyStr);
        } catch(JSONException e) {
            LOGGER.error("function=getJsonRequestBody, maybe request param is not a jsonobject exception: {}", e);
            return null;
        }
    }

    /**
     * Get the body of all request in json format<br/>
     *
     * @param context
     *         The http context
     * @return JSONObject The body of all request in json format
     * @since VFC 1.0
     */
    public static JSONObject getAllJsonRequestBody(HttpServletRequest context) {
        JSONObject requestBody = getJsonRequestBody(context);
        if(null == requestBody) {
            LOGGER.error("get httpservletrequest body exception");
            requestBody = new JSONObject();
        }
        LOGGER.warn("function=getAllJsonRequestBody; msg=get request data is:[{}]", requestBody.toString());
        requestBody.put("header", getContextHeader(context));
        return requestBody;
    }

    /**
     * Get the context header<br/>
     *
     * @param context
     *         The http context
     * @return Map context header
     * @since VFC 1.0
     */
    @SuppressWarnings("unchecked")
    private static Map<String, String> getContextHeader(HttpServletRequest context) {
        Map<String, String> header = new HashMap<>();
        Enumeration<String> headerNames = context.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String value = context.getHeader(headerName);
            header.put(headerName, value);
        }
        return header;
    }

    public static String encodeParams(final RestfulParametes restParametes) throws ServiceException {
        final Map<String, String> parm = restParametes.getParamMap();
        String value = null;
        boolean bHasParma = false;
        final StringBuilder builder = new StringBuilder();
        try {
            for(final String key : parm.keySet()) {
                value = parm.get(key);
                if(value == null) {
                    value = "";
                }
                String str;
                if(bHasParma) {
                    str = String.format("&%s=%s", URLEncoder.encode(key, RestfulClientConst.ENCODING),
                            URLEncoder.encode(value, RestfulClientConst.ENCODING));
                } else {
                    bHasParma = true;
                    str = String.format("%s=%s", URLEncoder.encode(key, RestfulClientConst.ENCODING),
                            URLEncoder.encode(value, RestfulClientConst.ENCODING));
                }
                builder.append(str);
            }
        } catch(final UnsupportedEncodingException ex) {
            LOGGER.error("unsupported encoding: ", ex);
            throw new ServiceException("Broken VM does not support UTF-8");
        }
        return builder.toString();
    }

    public static Map<String, String> getAAIHeaderMap() {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("X-TransactionId", "9999");
        headerMap.put("X-FromAppId", "jimmy");
        headerMap.put("Real-Time", "true");
        headerMap.put("Authorization", "Basic QUFJOkFBSQ==");
        headerMap.put("Accept", "application/json");
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }
}
