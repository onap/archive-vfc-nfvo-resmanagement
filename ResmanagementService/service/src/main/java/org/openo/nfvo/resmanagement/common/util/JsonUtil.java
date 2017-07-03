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

package org.openo.nfvo.resmanagement.common.util;

import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * Json Utility Class.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public final class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

    private static final int TYPE_STRING = 0;

    private static final int TYPE_INT = 1;

    private static final int TYPE_JSONA = 2;

    private static final int TYPE_JSONO = 3;

    private static final int TYPE_LONG = 4;

    private JsonUtil() {
    }

    /**
     *
     * Get Json Field String.<br>
     *
     * @param jsonObj
     * @param fieldName
     * @return
     * @since  NFVO 0.5
     */
    public static String getJsonFieldStr(JSONObject jsonObj, String fieldName) {
        return (String)getJsonFieldObject(jsonObj, fieldName, TYPE_STRING);
    }

    /**
     *
     * Get Json Field Integer.<br>
     *
     * @param jsonObj
     * @param fieldName
     * @return
     * @since  NFVO 0.5
     */
    public static Integer getJsonFieldInt(JSONObject jsonObj, String fieldName) {
        return (Integer)getJsonFieldObject(jsonObj, fieldName, TYPE_INT);
    }

    /**
     *
     * Get Json Field array.<br>
     *
     * @param jsonObj
     * @param fieldName
     * @return
     * @since  NFVO 0.5
     */
    public static JSONArray getJsonFieldArr(JSONObject jsonObj, String fieldName) {
        return (JSONArray)getJsonFieldObject(jsonObj, fieldName, TYPE_JSONA);
    }

    /**
     *
     * Get Json Field Json.<br>
     *
     * @param jsonObj
     * @param fieldName
     * @return
     * @since  NFVO 0.5
     */
    public static JSONObject getJsonFieldJson(JSONObject jsonObj, String fieldName) {
        return (JSONObject)getJsonFieldObject(jsonObj, fieldName, TYPE_JSONO);
    }

    /**
     *
     * Get Json Field Long.<br>
     *
     * @param jsonObj
     * @param fieldName
     * @return
     * @since  NFVO 0.5
     */
    public static Long getJsonFieldLong(JSONObject jsonObj, String fieldName) {
        return (Long)getJsonFieldObject(jsonObj, fieldName, TYPE_LONG);
    }

    /**
     *
     * Get Json Field Object.<br>
     *
     * @param jsonObj
     * @param fieldName
     * @param classType
     * @return
     * @since  NFVO 0.5
     */
    private static Object getJsonFieldObject(JSONObject jsonObj, String fieldName, int classType) {
        try {
            if(null != jsonObj && jsonObj.has(fieldName)) {
                Object result = new Object();
                switch(classType) {
                    case TYPE_STRING:
                        result = "null".equals(jsonObj.getString(fieldName)) ? "" : jsonObj.getString(fieldName);
                        break;
                    case TYPE_INT:
                        result = jsonObj.getInt(fieldName);
                        break;
                    case TYPE_JSONA:
                        result = jsonObj.getJSONArray(fieldName);
                        break;
                    case TYPE_JSONO:
                        result = jsonObj.getJSONObject(fieldName);
                        break;
                    case TYPE_LONG:
                        result = jsonObj.getLong(fieldName);
                        break;
                    default:
                        result = null;
                        break;
                }
                return result;
            }
        } catch(JSONException e) {
            LOG.error("function=getJsonFieldLong, exception: {} ", e);
            return null;
        }
        return null;
    }

    /**
     *
     * Check whether the Json Object is empty.<br>
     *
     * @param jsonObject
     * @return
     * @since  NFVO 0.5
     */
    public static boolean isNullJson(JSONObject jsonObject) {
        if(null == jsonObject || jsonObject.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     *
     * Get String value by Json.<br>
     *
     * @param json
     * @param key
     * @return
     * @since  NFVO 0.5
     */
    public static String getStrValueByjson(JSONObject json, String key) {
        JSONArray names = json.names();
        String result = null;
        for(int i = 0; i < names.size(); i++) {
            String nodeName = names.getString(i);
            if(json.optJSONObject(nodeName) != null) {
                result = getStrValueByjson(json.getJSONObject(nodeName), key);
            }
            if(json.optJSONArray(nodeName) != null) {
                result = getStrValueByJArray(json.getJSONArray(nodeName), key);
            }
            if(nodeName.equals(key)) {
                result = json.getString(nodeName);
                break;
            }
        }
        return result;
    }

    private static String getStrValueByJArray(JSONArray json, String key) {
        String result = null;
        for(int i = 0; i < json.size(); i++) {
            if(json.optJSONObject(i) != null) {
                result = getStrValueByjson(json.getJSONObject(i), key);
            }
            if(json.optJSONArray(i) != null) {
                result = getStrValueByJArray(json.getJSONArray(i), key);
            }
        }
        return result;
    }

    /**
     *
     * Get Json Value by Json object.<br>
     *
     * @param json
     * @param key
     * @return
     * @since  NFVO 0.5
     */
    public static JSONObject getJsonValueByjson(JSONObject json, String key) {
        JSONObject resultJson = new JSONObject();
        String result = getStrValueByjson(json, key);
        if(null == result) {
            return null;
        }
        resultJson.element(key, result);
        return resultJson;

    }

    /**
     *
     * Get String Value by Json object.<br>
     *
     * @param json
     * @param parentKey
     * @param key
     * @return
     * @since  NFVO 0.5
     */
    public static String getStrValueByJson(JSONObject json, String parentKey, String key) {
        if(parentKey.isEmpty()) {
            return getStrValueByjson(json, key);
        }
        JSONObject parentJson = getJsonValueByjson(json, parentKey);
        if(isNullJson(parentJson)) {
            return null;
        }
        return getStrValueByjson(parentJson, key);

    }

    /**
     *
     * Get response Data.<br>
     *
     * @param obj
     * @return
     * @since  NFVO 0.5
     */
    public static JSONObject getResponseData(JSONObject obj) {
        JSONObject result = new JSONObject();

        Integer retCode = getJsonFieldInt(obj, ParamConstant.PARAM_RETCODE);
        if(null == retCode || retCode == -1) {
            return null;
        }

        if(obj.optJSONObject(ParamConstant.PARAM_DATA) != null) {
            result = obj.getJSONObject(ParamConstant.PARAM_DATA);
        } else if(obj.optJSONArray(ParamConstant.PARAM_DATA) != null) {
            result = obj.getJSONArray(ParamConstant.PARAM_DATA).getJSONObject(0);
        }
        if(result.isEmpty()) {
            return null;
        }

        if(result.containsKey(ParamConstant.PARAM_RETCODE)) {
            result = getResponseData(result);
        }
        return result;
    }

}
