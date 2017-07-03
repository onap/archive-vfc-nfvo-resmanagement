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
import java.util.Map;

import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class RoaResponseUtilTest {

    @Test
    public void testGet() {
        new MockUp<ResponseUtil>() {

            @Mock
            public JSONObject genHttpResponse(int retCode, String msg, Map<String, Object> map) {
                return null;
            }
        };
        JSONObject result = RoaResponseUtil.get(null);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() {
        int a = 0;
        new MockUp<ResponseUtil>() {

            @Mock
            public JSONObject genHttpResponse(int retCode, String msg) {
                return null;
            }
        };
        JSONObject result = RoaResponseUtil.add(a);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd1() {
        int a = 2;
        new MockUp<ResponseUtil>() {

            @Mock
            public JSONObject genHttpResponse(int retCode, String msg) {
                return null;
            }
        };
        JSONObject result = RoaResponseUtil.add(a);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdate() {
        int a = 0;
        new MockUp<ResponseUtil>() {

            @Mock
            public JSONObject genHttpResponse(int retCode, String msg) {
                return null;
            }
        };
        JSONObject result = RoaResponseUtil.update(a);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdate1() {
        int a = 2;
        new MockUp<ResponseUtil>() {

            @Mock
            public JSONObject genHttpResponse(int retCode, String msg) {
                return null;
            }
        };
        JSONObject result = RoaResponseUtil.update(a);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDelete() {
        int a = -1;
        new MockUp<ResponseUtil>() {

            @Mock
            public JSONObject genHttpResponse(int retCode, String msg) {
                return null;
            }
        };
        JSONObject result = RoaResponseUtil.delete(a);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDelete1() {
        int a = 0;
        new MockUp<ResponseUtil>() {

            @Mock
            public JSONObject genHttpResponse(int retCode, String msg) {
                return null;
            }
        };
        JSONObject result = RoaResponseUtil.delete(a);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = RoaResponseUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
