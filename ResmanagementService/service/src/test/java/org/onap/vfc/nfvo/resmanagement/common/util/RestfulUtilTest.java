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

package org.onap.vfc.nfvo.resmanagement.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulOptions;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulResponse;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class RestfulUtilTest {

    @Test
    public void testGetResponseObjWithTwoParams() {
        mockGetResponseContent();
        JSONObject result = RestfulUtil.getResponseObj(null, null);
        JSONObject expectedResult = new JSONObject().fromObject("{\"ResponseContent\":\"123\"}");
        assertEquals(expectedResult, result);
    }

    private void mockGetResponseContent() {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, RestfulOptions opt,
                    String type) {
                return "{\"ResponseContent\":\"123\"}";
            }
        };
    }

    private void mockGetResponseContentReturnNull() {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, RestfulOptions opt,
                    String type) {
                return null;
            }
        };
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
    public void testGetRestResObjectsIsNull() {
        mockGetResponseContentReturnNull();
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
        } catch(ServiceException e) {
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
        } catch(ServiceException e) {
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
        } catch(ServiceException e) {
            assertTrue(true);
        }
    }

}
