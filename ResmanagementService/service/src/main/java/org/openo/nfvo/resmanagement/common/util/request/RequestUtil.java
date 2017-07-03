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

package org.openo.nfvo.resmanagement.common.util.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * <br/>
 * <p>
 * Utility used for request
 * </p>
 *
 * @author
 * @version NFVO 0.5 2016-3-17
 */
public final class RequestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * Constructor<br/>
     * <p>
     * </p>
     *
     * @since NFVO 0.5
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
     * @since NFVO 0.5
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
     * @since NFVO 0.5
     */
    @SuppressWarnings("unchecked")
    private static Map<String, String> getContextHeader(HttpServletRequest context) {
        Map<String, String> header = new HashMap<String, String>();
        Enumeration<String> headerNames = context.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String value = context.getHeader(headerName);
            header.put(headerName, value);
        }
        return header;
    }
}
