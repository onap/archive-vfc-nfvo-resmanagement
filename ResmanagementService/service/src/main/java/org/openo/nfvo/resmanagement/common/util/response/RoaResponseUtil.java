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
import java.util.List;
import java.util.Map;

import org.openo.nfvo.resmanagement.common.constant.HttpConstant;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.constant.ResponseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br/>
 * <p>
 * Utility for generate Roa get/add/update/delete method status
 * </p>
 *
 * @author
 * @version NFVO 0.5 2016-3-17
 */
public final class RoaResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoaResponseUtil.class);

    private RoaResponseUtil() {

    }

    /**
     * Generate get method response<br/>
     *
     * @param list
     *            The basic response for get method
     * @return JSONObject The response for http request
     * @since NFVO 0.5
     */
    public static <T> JSONObject get(List<T> list) {
        Map<String, Object> map = new HashMap<String, Object>(10);
        map.put(ParamConstant.PARAM_DATA, list);
        LOGGER.info("function=get; msg=get map:{}", map.toString());
        return ResponseUtil.genHttpResponse(HttpConstant.OK_CODE, ResponseConstant.QUERY_SUCESS_MSG, map);
    }

    /**
     * Generate different response by different parameter for add method <br/>
     *
     * @param result
     *            The result
     * @return JSONObject
     * @since NFVO 0.5
     */
    public static JSONObject add(int result) {
        LOGGER.info("function=add; msg=add result{}", result);
        if(result <= 0) {
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, ResponseConstant.ADD_FAIL_MSG);
        } else {
            return ResponseUtil.genHttpResponse(HttpConstant.OK_CODE, ResponseConstant.ADD_SUCESS_MSG);
        }
    }

    /**
     * Generate different response by different parameter for update method <br/>
     *
     * @param result
     *            The result
     * @return JSONObject The response for http request
     * @since NFVO 0.5
     */
    public static JSONObject update(int result) {
        LOGGER.info("function=update; msg=update result{}", result);
        if(result <= 0) {
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, ResponseConstant.MOD_FAIL_MSG);
        } else {
            return ResponseUtil.genHttpResponse(HttpConstant.OK_CODE, ResponseConstant.MOD_SUCESS_MSG);
        }
    }

    /**
     * Generate different response by different parameter for delete method <br/>
     *
     * @param result
     *            The result
     * @return delete JSONObject of the response for http request
     * @since NFVO 0.5
     */
    public static JSONObject delete(int result) {
        LOGGER.info("function=delete; msg=delete result{}", result);
        if(result <= 0) {
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, ResponseConstant.DEL_FAIL_MSG);
        } else {
            return ResponseUtil.genHttpResponse(HttpConstant.OK_CODE, ResponseConstant.DEL_SUCESS_MSG);
        }
    }
}
