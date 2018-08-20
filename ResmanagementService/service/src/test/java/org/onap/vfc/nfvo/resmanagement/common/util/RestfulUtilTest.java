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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;

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

    @Test(expected=NullPointerException.class)
    public void getRemoteResponseTest(){
    	Map<String,String> paramMap = new HashMap<>();
		paramMap.put("url", "https://127.0.0.1:80");
		paramMap.put("methodType", "get");
		paramMap.put("tenantId", "tenant_id_123");
		String params = "This is Params Data";
		RestfulResponse result = RestfulUtil.getRemoteResponse(paramMap, params);
		assertEquals(result.getStatus(),200);
    }
    
    @Test(expected=NullPointerException.class)
    public void getRemoteResponseTestPut(){
    	Map<String,String> paramMap = new HashMap<>();
		paramMap.put("url", "https://127.0.0.1:80");
		paramMap.put("methodType", "put");
		paramMap.put("tenantId", "tenant_id_123");
		String params = "This is Params Data";
		RestfulResponse result = RestfulUtil.getRemoteResponse(paramMap, params);
		assertEquals(result.getStatus(),200);
    }
    
    @Test(expected=NullPointerException.class)
    public void getRemoteResponseTestPost(){
    	Map<String,String> paramMap = new HashMap<>();
		paramMap.put("url", "https://127.0.0.1:80");
		paramMap.put("methodType", "post");
		paramMap.put("tenantId", "tenant_id_123");
		String params = "This is Params Data";
		RestfulResponse result = RestfulUtil.getRemoteResponse(paramMap, params);
		assertEquals(result.getStatus(),200);
    }
    
    @Test(expected=NullPointerException.class)
    public void getRemoteResponseTestDel(){
    	Map<String,String> paramMap = new HashMap<>();
		paramMap.put("url", "https://127.0.0.1:80");
		paramMap.put("methodType", "delete");
		paramMap.put("tenantId", "tenant_id_123");
		String params = "This is Params Data";
		RestfulResponse result = RestfulUtil.getRemoteResponse(paramMap, params);
		assertEquals(result.getStatus(),200);
    }
    
    @Test
    public void testRestfulResponseDel() {
    	
    	RestfulParametes restParametes = new RestfulParametes();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-type", "Application/json");
		headerMap.put("X-FromAppId", "Postman");
		headerMap.put("X-TransactionId", "1234");
		restParametes.setHeaderMap(headerMap);
		String data = "This is Raw Data";
		restParametes.setRawData(data);
		String url = "https://127.0.0.1:80";
        RestfulResponse result = RestfulUtil.getRestfulResponse(url, restParametes, "delete");
        assertEquals(result.getStatus(),-1);
    }
    
    @Test
    public void testRestfulResponsehttp() {
    	
    	RestfulParametes restParametes = new RestfulParametes();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-type", "Application/json");
		headerMap.put("X-FromAppId", "Postman");
		headerMap.put("X-TransactionId", "1234");
		restParametes.setHeaderMap(headerMap);
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("type", "Application");
		paramMap.put("vimId", "vim_id_123");
		paramMap.put("tenantId", "tenant_id_123");
		restParametes.setParamMap(paramMap);
		String data = "This is Raw Data";
		restParametes.setRawData(data);
		String url = "http://127.0.0.1:80";
        RestfulResponse result = RestfulUtil.getRestfulResponse(url, restParametes, "delete");
        assertEquals(result.getStatus(),-1);
    }
    
    @Test
    public void testRestfulResponsenull() {
    	
    	RestfulParametes restParametes = new RestfulParametes();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-type", "Application/json");
		headerMap.put("X-FromAppId", "Postman");
		headerMap.put("X-TransactionId", "1234");
		restParametes.setHeaderMap(headerMap);
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("type", "Application");
		paramMap.put("vimId", "vim_id_123");
		paramMap.put("tenantId", "tenant_id_123");
		restParametes.setParamMap(paramMap);
		String data = "This is Raw Data";
		restParametes.setRawData(data);
		String url = "ftp://127.0.0.1:80";
        RestfulResponse result = RestfulUtil.getRestfulResponse(url, restParametes, "delete");
        assertEquals(result.getStatus(),-1);
    }
    
  
    @Test
    public void testRestfulResponseGet() {
    	
    	RestfulParametes restParametes = new RestfulParametes();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-type", "Application/json");
		headerMap.put("X-FromAppId", "Postman");
		headerMap.put("X-TransactionId", "1234");
		restParametes.setHeaderMap(headerMap);
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("type", "Application");
		paramMap.put("vimId", "vim_id_123");
		paramMap.put("tenantId", "tenant_id_123");
		restParametes.setParamMap(paramMap);
		String data = "This is Raw Data";
		restParametes.setRawData(data);
		String url = "https://127.0.0.1:80";
        RestfulResponse result = RestfulUtil.getRestfulResponse(url, restParametes, "get");
        assertEquals(result.getStatus(),-1);
    }
    
    @Test(expected=NullPointerException.class)
    public void testRestfulResponsePost() {
    	
    	RestfulParametes restParametes = new RestfulParametes();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-type", "Application/json");
		headerMap.put("X-FromAppId", "Postman");
		headerMap.put("X-TransactionId", "1234");
		restParametes.setHeaderMap(headerMap);
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("type", "Application");
		paramMap.put("vimId", "vim_id_123");
		paramMap.put("tenantId", "tenant_id_123");
		restParametes.setParamMap(paramMap);
		String data = "This is Raw Data";
		restParametes.setRawData(data);
		String url = "https://127.0.0.1:80";
        RestfulResponse result = RestfulUtil.getRestfulResponse(url, restParametes, "post");
        assertEquals(result.getStatus(),-1);
    }
    
    @Test
    public void testRestfulResponsePut() {
    	
    	RestfulParametes restParametes = new RestfulParametes();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-type", "Application/json");
		headerMap.put("X-FromAppId", "Postman");
		headerMap.put("X-TransactionId", "1234");
		restParametes.setHeaderMap(headerMap);
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("type", "Application");
		paramMap.put("vimId", "vim_id_123");
		paramMap.put("tenantId", "tenant_id_123");
		restParametes.setParamMap(paramMap);
		String data = "This is Raw Data";
		restParametes.setRawData(data);
		String url = "https://127.0.0.1:80";
        RestfulResponse result = RestfulUtil.getRestfulResponse(url, restParametes, "put");
        assertEquals(result.getStatus(),-1);
    }
    
    @Test
    public void testGetResponseContent() {
    	
    	RestfulParametes restParametes = new RestfulParametes();
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-type", "Application/json");
		headerMap.put("X-FromAppId", "Postman");
		headerMap.put("X-TransactionId", "1234");
		restParametes.setHeaderMap(headerMap);
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("type", "Application");
		paramMap.put("vimId", "vim_id_123");
		paramMap.put("tenantId", "tenant_id_123");
		restParametes.setParamMap(paramMap);
		String data = "This is Raw Data";
		restParametes.setRawData(data);
		String url = "https://127.0.0.1:80";
    	RestfulUtil.getResponseContent(url, restParametes, "GET");
    }
    
    @Test
    public void getResponseContentMapTest() {
    	String url = "https://127.0.0.1:80";
    	RestfulUtil.getResponseContentMap(url, "GET");
    }
    
    @Test
    public void testGetResponseObjWithNull() {
        new MockUp<RestfulUtil>() {

            @Mock
            public String getResponseContent(String url, RestfulParametes restParametes, RestfulOptions opt,
                    String type) {
                return null;
            }
        };
      RestfulUtil.getResponseObj(null, null, null);
    }
    
}
