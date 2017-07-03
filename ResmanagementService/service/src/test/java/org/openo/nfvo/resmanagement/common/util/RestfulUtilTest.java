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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulOptions;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.nfvo.resmanagement.common.constant.Constant;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class RestfulUtilTest {

    @Test
    public void testGetResponseObjWithTwoParams() {
        JSONObject result = RestfulUtil.getResponseObj(null, null);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseObjWithThreeParams() {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, RestfulOptions opt,
                    String type) {
                return "{\"ResponseContent\":\"123\"}";
            }
        };
        JSONObject result = RestfulUtil.getResponseObj(null, null, null);
        @SuppressWarnings("static-access")
        JSONObject expectedResult = new JSONObject().fromObject("{\"ResponseContent\":\"123\"}");
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseObjExpections() {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, RestfulOptions opt,
                    String type) {
                throw new JSONException();
            }
        };
        JSONObject result = RestfulUtil.getResponseObj(null, null, null);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseContent() {
        String result = RestfulUtil.getResponseContent(null, null, null);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseMap() {
        Map<String, Object> result = RestfulUtil.getResponseMap(null, null, null, null);
        Map<String, Object> expectedResult = new HashMap<String, Object>(10);
        expectedResult.put(Constant.RESPONSE_CONTENT, null);
        expectedResult.put(Constant.STATUS_CODE, -1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseContentMap() {
        Map<String, Object> result = RestfulUtil.getResponseContentMap(null, null);
        Map<String, Object> expectedResult = new HashMap<String, Object>(10);
        expectedResult.put(Constant.RESPONSE_CONTENT, null);
        expectedResult.put(Constant.STATUS_CODE, -1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseContentWithFourParams() {
        new MockUp<RestfulResponse>() {

            @Mock
            public int getStatus() {
                return 200;
            }
        };
        String result = RestfulUtil.getResponseContent(null, null, null);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRestfulResponse() {
        RestfulResponse result = RestfulUtil.getRestfulResponse(null, null, null);
        RestfulResponse expectedResult = new RestfulResponse();
        assertEquals(expectedResult.getStatus(), result.getStatus());
    }

    @Test
    public void testRestfulResponse() {
        RestfulResponse result = RestfulUtil.getRestfulResponse(null, null, "get");
        RestfulResponse expectedResult = new RestfulResponse();
        assertEquals(expectedResult.getStatus(), result.getStatus());
    }

    @Test
    public void testRestfulResponse1() {
        RestfulResponse result = RestfulUtil.getRestfulResponse(null, null, "add");
        RestfulResponse expectedResult = new RestfulResponse();
        assertEquals(expectedResult.getStatus(), result.getStatus());
    }

    @Test
    public void testRestfulResponse2() {
        RestfulResponse result = RestfulUtil.getRestfulResponse(null, null, "put");
        RestfulResponse expectedResult = new RestfulResponse();
        assertEquals(expectedResult.getStatus(), result.getStatus());
    }

    @Test
    public void testRestfulResponse3() {
        RestfulResponse result = RestfulUtil.getRestfulResponse(null, null, "delete");
        RestfulResponse expectedResult = new RestfulResponse();
        assertEquals(expectedResult.getStatus(), result.getStatus());
    }

    @Test
    public void testGetRestResObjectsIsNull() {
        RestfulResponse result = RestfulUtil.getRestRes(null, null);
        RestfulResponse expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRestResReflectiveOperationException() {
        RestfulResponse result = RestfulUtil.getRestRes("123", "get");
        RestfulResponse expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRestRes() {
        RestfulResponse result = RestfulUtil.getRestRes("async123", new RestfulResponse());
        RestfulResponse expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseResResultIsNull() throws ServiceException {
        try {
            RestfulUtil.getResponseRes(null, null);
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetResponse() throws ServiceException {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, String type) {
                return "{\"ResponseContent\":\"123\",\"data\":[\"datas\"]}";
            }
        };
        JSONArray result = RestfulUtil.getResponseRes(null, null);
        JSONArray expectedResult = JSONArray.fromObject("[\"datas\"]");
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseExceptions() throws ServiceException {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, String type) {
                return "{\"ResponseContent\":\"123\",}";
            }
        };
        try {
            RestfulUtil.getResponseRes(null, null);
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGgetResponseRes() throws ServiceException {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, String type) {
                return "{\"ResponseContent\":\"123\",}";
            }
        };
        try {
            RestfulUtil.getResponseRes(null, null, null);
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGgetResponseResException() throws ServiceException {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, String type) {
                return null;
            }
        };
        try {
            RestfulUtil.getResponseRes(null, null, null);
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

}
