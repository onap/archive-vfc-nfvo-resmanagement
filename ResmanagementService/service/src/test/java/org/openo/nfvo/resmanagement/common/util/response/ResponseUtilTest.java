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

package org.openo.nfvo.resmanagement.common.util.response;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;

public class ResponseUtilTest {

    @Test
    public void TestGenHttpResponseWithTwoParam() {
        int retCode1 = -1;
        String msg1 = "123";
        JSONObject result = ResponseUtil.genHttpResponse(retCode1, msg1);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("msg", "123");
        assertEquals(result.toString(), expectedResult.toString());
        ;
    }

    @Test
    public void TestGenHttpResponseWithThreeParam() {
        int retCode1 = -1;
        String msg1 = "123";
        JSONObject result = ResponseUtil.genHttpResponse(retCode1, msg1, null);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("msg", "123");
        assertEquals(result.toString(), expectedResult.toString());
        ;
    }

    @Test
    public void TestGenHttpResponseWithFourParam1() {
        int httpStatusCode = -1;
        int retCode1 = -1;
        String msg1 = "123";
        JSONObject result = ResponseUtil.genHttpResponse(null, httpStatusCode, retCode1, msg1);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("msg", "123");
        assertEquals(result.toString(), expectedResult.toString());
        ;
    }

    @Test
    public void TestGenHttpResponseWithFourParam2() {
        Map<String, Integer> codeMap = new HashMap<String, Integer>(5);
        codeMap.put("httpStatusCode", -1);
        codeMap.put("retCode", 1);
        Map<String, Object> map = new HashMap<String, Object>(5);
        map.put("a", -1);
        map.put("b", 1);
        String msg1 = "123";
        JSONObject result = ResponseUtil.genHttpResponse(null, codeMap, msg1, map);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("msg", "123");
        expectedResult.put("a", "-1");
        assertEquals(result.toString(), expectedResult.toString());
        ;
    }
    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = ResponseUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
