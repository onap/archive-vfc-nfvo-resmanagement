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

package org.openo.nfvo.resmanagement.service.adapter.impl;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.nfvo.resmanagement.common.util.RestfulUtil;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Sep 24, 2016
 */
public class ResmgrAdapter2MSBManagerTest {

    @Test
    public void testRegisterResmgr() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(201);
                rsp.setResponseJson("{\"msg\":\"success!\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.registerResmgr(paramsMap, new JSONObject());
        assertNotNull(resultObj);
    }

    @Test
    public void testRegisterResmgrByInvalid() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(415);
                rsp.setResponseJson("{\"msg\":\"invalid parameters\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.registerResmgr(paramsMap, new JSONObject());
        assertNotNull(resultObj);
    }

    @Test
    public void testRegisterResmgrByInternalError() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(500);
                rsp.setResponseJson("{\"msg\":\"internal system error\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.registerResmgr(paramsMap, new JSONObject());
        assertNotNull(resultObj);
    }

    @Test
    public void testRegisterResmgrByFail() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(404);
                rsp.setResponseJson("{\"msg\":\"not found\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.registerResmgr(paramsMap, new JSONObject());
        assertNotNull(resultObj);
    }

    @Test
    public void testRegisterResmgrByNull() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                return null;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.registerResmgr(paramsMap, new JSONObject());
        assertNotNull(resultObj);
    }

    @Test
    public void testUnRegisterResmgr() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(204);
                rsp.setResponseJson("{\"msg\":\"success!\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.unregisterResmgr(paramsMap);
        assertNotNull(resultObj);
    }

    @Test
    public void testUnRegisterResmgrByNotFound() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(404);
                rsp.setResponseJson("{\"msg\":\"not found!\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.unregisterResmgr(paramsMap);
        assertNotNull(resultObj);
    }

    @Test
    public void testUnRegisterResmgrByInvalid() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(415);
                rsp.setResponseJson("{\"msg\":\"invalid parameters!\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.unregisterResmgr(paramsMap);
        assertNotNull(resultObj);
    }

    @Test
    public void testUnRegisterResmgrByInternalError() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(500);
                rsp.setResponseJson("{\"msg\":\"internal system error!\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.unregisterResmgr(paramsMap);
        assertNotNull(resultObj);
    }

    @Test
    public void testUnRegisterResmgrByFail() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(203);
                rsp.setResponseJson("{\"msg\":\"fail!\"}");
                return rsp;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.unregisterResmgr(paramsMap);
        assertNotNull(resultObj);
    }

    @Test
    public void testUnRegisterResmgrByNull() {
        new MockUp<RestfulUtil>() {

            @Mock
            public RestfulResponse getRemoteResponse(Map<String, String> paramsMap, String params) {
                return null;
            }
        };
        Map<String, String> paramsMap = getHashMap();
        ResmgrAdapter2MSBManager resmgr = new ResmgrAdapter2MSBManager();
        JSONObject resultObj = resmgr.unregisterResmgr(paramsMap);
        assertNotNull(resultObj);
    }

    private Map<String, String> getHashMap() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("url", "/openoapi/microservices/v1/services");
        paramsMap.put("methodType", "post");
        return paramsMap;
    }
}
