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

package org.onap.vfc.nfvo.resmanagement.service.rest;

import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.service.group.impl.GrantResServiceImpl;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.GrantResService;
import org.onap.vfc.nfvo.resmanagement.service.rest.GrantResourseRoa;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
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
public class GrantResourseRoaTest {

    private GrantResourseRoa roa;

    private GrantResService grantResService;

    @Before
    public void setUp() {
        roa = new GrantResourseRoa();
        grantResService = new GrantResServiceImpl();
        roa.setGrantResService(grantResService);
    }

    @Test
    public void testGrantResource() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<GrantResServiceImpl>() {

            @Mock
            public JSONObject grantResource(JSONObject object) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.grantResource(mock);
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testGrantResourceByNull() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.grantResource(mock);
        assertNotNull(result);
    }

    @Test
    public void testGrantResourceFail() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<GrantResServiceImpl>() {

            @Mock
            public JSONObject grantResource(JSONObject object) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.grantResource(mock);
        assertNotNull(result);
    }

    @Test
    public void testGrantResourceReal() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<GrantResServiceImpl>() {

            @Mock
            public JSONObject grantResourceReal(JSONObject object) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.grantResourceReal(mock);
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testGrantResourceRealByNull() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.grantResourceReal(mock);
    }

    @Test
    public void testGrantResourceRealFail() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<GrantResServiceImpl>() {

            @Mock
            public JSONObject grantResourceReal(JSONObject object) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.grantResourceReal(mock);
        assertNotNull(result);
    }
}
