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

package org.onap.vfc.nfvo.res.service.rest;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.onap.vfc.nfvo.res.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.res.service.base.openstack.impl.NetworkImpl;
import org.onap.vfc.nfvo.res.service.base.openstack.inf.Network;
import org.onap.vfc.nfvo.res.service.entity.NetworkEntity;
import org.onap.vfc.nfvo.res.service.rest.NetworkRoa;
import org.openo.baseservice.remoteservice.exception.ServiceException;
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
public class NetworkRoaTest {

    private NetworkRoa roa;

    private Network network;

    @Before
    public void setUp() {
        roa = new NetworkRoa();
        network = new NetworkImpl();
        roa.setNetwork(network);
    }

    @Test
    public void testGetNetworks() throws ServiceException {
        new MockUp<NetworkImpl>() {

            @Mock
            public List<NetworkEntity> getList(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<NetworkEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getNetworks(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetNetwork() throws ServiceException {
        new MockUp<NetworkImpl>() {

            @Mock
            public List<NetworkEntity> getList(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<NetworkEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getNetwork(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddNetwork() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<NetworkEntity>() {

            @Mock
            public NetworkEntity toEntity(JSONObject jsonObject) {
                return new NetworkEntity();
            }
        };
        new MockUp<NetworkImpl>() {

            @Mock
            public int add(NetworkEntity entity) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addNetwork(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddNetworkByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<NetworkEntity>() {

            @Mock
            public NetworkEntity toEntity(JSONObject jsonObject) {
                return new NetworkEntity();
            }
        };
        new MockUp<NetworkImpl>() {

            @Mock
            public int add(NetworkEntity entity) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addNetwork(mock);
        assertNotNull(result);
    }

    @Test
    public void testDeleteNetwork() throws ServiceException {
        new MockUp<NetworkImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteNetwork(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeleteNetworkByException() throws ServiceException {
        new MockUp<NetworkImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteNetwork(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testUpdateNetwork() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<NetworkImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateNetwork(mock);
        assertNotNull(result);
    }

    @Test
    public void testUpdateNetworkByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<NetworkImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateNetwork(mock);
        assertNotNull(result);
    }
}
