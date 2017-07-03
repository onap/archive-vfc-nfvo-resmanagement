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

package org.openo.nfvo.resmanagement.common.util.response;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

/**
 *
 * Response utility class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public final class ResponseUtil {

    private ResponseUtil() {
    }

    /**
     * Roa request common return function, default return code 200 <br/>
     *
     * @param retCode
     *         The request return code
     * @param msg
     *         The request message
     * @return JSONObject The response for http request
     * @since NFVO 0.5
     */
    public static JSONObject genHttpResponse(int retCode, String msg) {
        return genHttpResponse(null, createCodeMap(-1, retCode), msg, null);
    }

    /**
     * Roa request common return function, default return code 200<br/>
     *
     * @param retCode
     *         The request return code
     * @param msg
     *         The request message
     * @param map
     *         Other request info of this request
     * @return JSONObject The response for http request
     * @since NFVO 0.5
     */
    public static JSONObject genHttpResponse(int retCode, String msg, Map<String, Object> map) {
        return genHttpResponse(null, createCodeMap(-1, retCode), msg, map);
    }

    /**
     * Roa request common return method <br/>
     *
     * @param context
     *         The http request context
     * @param httpStatusCode
     *         The http response code
     * @param retCode
     *         The http request return code
     * @param msg
     *         The message of request
     * @return JSONObject The response for http request
     * @since NFVO 0.5
     */
    public static JSONObject genHttpResponse(HttpServletRequest context, int httpStatusCode, int retCode, String msg) {
        return genHttpResponse(context, createCodeMap(httpStatusCode, retCode), msg, null);
    }

    /**
     *
     * Roa request common return method.<br>
     *
     * @param context, The http request context
     * @param codeMap
     * @param msg, The message of request
     * @param map, Other message of request
     * @return
     * @since  NFVO 0.5
     */
    public static JSONObject genHttpResponse(HttpServletRequest context, Map<String, Integer> codeMap, String msg,
            Map<String, Object> map) {
        JSONObject object = new JSONObject();

        object.put("msg", msg);
        if(null != map) {
            Iterator<Entry<String, Object>> ite = map.entrySet().iterator();
            if(ite.hasNext()) {
                Map.Entry<String, Object> entry = ite.next();
                object.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return object;
    }

    /**
     * Create code map to maintenance the relationship between return code and
     * http status code <br/>
     *
     * @param httpStatusCode
     *         The http response code
     * @param retCode
     *         The http request return code
     * @return Map
     * @since NFVO 0.5
     */
    private static Map<String, Integer> createCodeMap(int httpStatusCode, int retCode) {
        Map<String, Integer> codeMap = new HashMap<>(5);
        codeMap.put("httpStatusCode", httpStatusCode);
        codeMap.put("retCode", retCode);
        return codeMap;
    }
}
