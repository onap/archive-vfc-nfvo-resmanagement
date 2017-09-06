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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.impl.HostImpl;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.inf.Host;
import org.onap.vfc.nfvo.resmanagement.service.entity.HostEntity;
import org.onap.vfc.nfvo.resmanagement.service.rest.HostRoa;
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
 * @version NFVO 0.5 Feb 9, 2017
 */
public class HostRoaTest {

    private HostRoa roa;

    private Host host;

    @Before
    public void setUp() {
        roa = new HostRoa();
        host = new HostImpl();
        roa.setHost(host);
    }

    @Test
    public void testGetHosts() throws ServiceException {
        new MockUp<HostImpl>() {

            @Mock
            public List<HostEntity> getList(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<HostEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getHosts(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetHost() throws ServiceException {
        new MockUp<HostImpl>() {

            @Mock
            public List<HostEntity> getList(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<HostEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getHost(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddHost() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<HostImpl>() {

            @Mock
            public int add(JSONObject jsonObject) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addHost(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddHostByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<HostImpl>() {

            @Mock
            public int add(JSONObject jsonObject) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addHost(mock);
        assertNotNull(result);
    }

    @Test
    public void testDeleteHost() throws ServiceException {
        new MockUp<HostImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteHost(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeleteHostByException() throws ServiceException {
        new MockUp<HostImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteHost(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testUpdateHost() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<HostImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateHost(mock);
        assertNotNull(result);
    }

    @Test
    public void testUpdateHostByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<HostImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateHost(mock);
        assertNotNull(result);
    }
}
