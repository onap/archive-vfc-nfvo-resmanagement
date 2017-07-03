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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.service.base.openstack.impl.PortImpl;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Port;
import org.openo.nfvo.resmanagement.service.entity.PortEntity;
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
public class PortRoaTest {

    private PortRoa roa;

    private Port port;

    @Before
    public void setUp() {
        roa = new PortRoa();
        port = new PortImpl();
        roa.setPort(port);
    }

    @Test
    public void testGetPorts() throws ServiceException {
        new MockUp<PortImpl>() {

            @Mock
            public List<PortEntity> getList(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<PortEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getPorts(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetPort() throws ServiceException {
        new MockUp<PortImpl>() {

            @Mock
            public List<PortEntity> getList(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<PortEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getPort(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddPort() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<PortEntity>() {

            @Mock
            public PortEntity toEntity(JSONObject jsonObject) {
                return new PortEntity();
            }
        };
        new MockUp<PortImpl>() {

            @Mock
            public int add(PortEntity portEntity) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addPort(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddPortByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<PortEntity>() {

            @Mock
            public PortEntity toEntity(JSONObject jsonObject) {
                return new PortEntity();
            }
        };
        new MockUp<PortImpl>() {

            @Mock
            public int add(PortEntity portEntity) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addPort(mock);
        assertNotNull(result);
    }

    @Test
    public void testDeletePort() throws ServiceException {
        new MockUp<PortImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deletePort(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeletePortByException() throws ServiceException {
        new MockUp<PortImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deletePort(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testUpdatePort() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<PortImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updatePort(mock);
        assertNotNull(result);
    }

    @Test
    public void testUpdatePortByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<PortImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updatePort(mock);
        assertNotNull(result);
    }
}
