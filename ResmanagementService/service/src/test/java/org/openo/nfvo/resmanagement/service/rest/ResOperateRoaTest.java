/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.openo.nfvo.resmanagement.service.rest;

import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.VimUtil;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.service.group.impl.ResOperateServiceImpl;
import org.openo.nfvo.resmanagement.service.group.inf.ResOperateService;
import org.springframework.mock.web.MockHttpServletRequest;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Mar 16, 2017
 */
public class ResOperateRoaTest {

    private ResOperateRoa roa;

    private ResOperateService resOperateService;

    @Before
    public void setUp() {
        roa = new ResOperateRoa();
        resOperateService = new ResOperateServiceImpl();
        roa.setResOperateService(resOperateService);
    }

    @Test
    public void testUpdateIResPool() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VimUtil>() {

            @Mock
            public JSONObject getVimById(String vimId) {
                JSONObject vimInfo = new JSONObject();
                vimInfo.put("tenant", "tenant");
                return vimInfo;
            }

            @Mock
            public String getTenantIdByName(String tenant, String vimId) {
                return "";
            }
        };
        new MockUp<ResOperateServiceImpl>() {

            @Mock
            public void updateIRes(String tenantId, String vimId, JSONObject header) throws ServiceException {
            }

            @Mock
            public void sendMsgMonitor(String operateType, String vimId) throws ServiceException {
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateIResPool(mock, "vimId");
        assertNotNull(result);
    }

    @Test
    public void testUpdateIResPoolFail() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VimUtil>() {

            @Mock
            public JSONObject getVimById(String vimId) {
                JSONObject vimInfo = new JSONObject();
                vimInfo.put("tenant", "tenant");
                return vimInfo;
            }

            @Mock
            public String getTenantIdByName(String tenant, String vimId) {
                return "";
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateIResPool(mock, null);
        assertNotNull(result);
    }

    @Test
    public void testAddAllResPool() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<ResOperateServiceImpl>() {

            @Mock
            public void addIRes(String tenantId, String vimId, JSONObject header) throws ServiceException {
            }

            @Mock
            public void sendMsgMonitor(String operateType, String vimId) throws ServiceException {
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addAllResPool(mock, "tenantId", "vimId");
        assertNotNull(result);
    }

    @Test
    public void testAddAllResPoolFail() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addAllResPool(mock, null, null);
        assertNotNull(result);
    }

    @Test
    public void testDeleteIRes() throws ServiceException {
        new MockUp<ResOperateServiceImpl>() {

            @Mock
            public int deleteIRes(String vimId) throws ServiceException {
                return 1;
            }

            @Mock
            public void sendMsgMonitor(String operateType, String vimId) throws ServiceException {
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteIRes(mock, "vimId");
        assertNotNull(result);
    }

    @Test
    public void testDeleteIResFail() throws ServiceException {
        new MockUp<ResOperateServiceImpl>() {

            @Mock
            public int deleteIRes(String vimId) throws ServiceException {
                throw new ServiceException();
            }

            @Mock
            public void sendMsgMonitor(String operateType, String vimId) throws ServiceException {
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteIRes(mock, null);
        assertNotNull(result);
    }

}
