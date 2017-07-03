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
package org.openo.nfvo.resmanagement.service.dao.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;
import org.openo.nfvo.resmanagement.service.mapper.NetworkMapper;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class NetworkDaoImplTest {
    @Mocked
    NetworkMapper mapper;
    @Test
    public void testdeleteNetwork() throws ServiceException {

         new Expectations() {{
             mapper.deleteNetwork("123");
           }};

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        NetworkDaoImpl impl = new NetworkDaoImpl();
        assertEquals(0, impl.deleteNetwork("123"));

    }
    @Test
    public void testdeleteNetworkByVimId() throws ServiceException {
            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        NetworkDaoImpl impl = new NetworkDaoImpl();
        assertEquals(0, impl.deleteNetworkByVimId("123"));

    }
    @Test
    public void testaddNetwork() throws ServiceException {
            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        NetworkDaoImpl impl = new NetworkDaoImpl();
        NetworkEntity networkEntity =new NetworkEntity();
        networkEntity.setId("1");
        assertEquals(0, impl.addNetwork(networkEntity));

    }

    @Test
    public void testaddNetworkSelective() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        NetworkDaoImpl impl = new NetworkDaoImpl();
        NetworkEntity networkEntity =new NetworkEntity();
        networkEntity.setId("1");
        assertEquals(0, impl.addNetworkSelective(networkEntity));
    }
    @Test
    public void testupdateNetworkSelective() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        NetworkDaoImpl impl = new NetworkDaoImpl();
        NetworkEntity networkEntity =new NetworkEntity();
        networkEntity.setId("1");
        assertEquals(0, impl.updateNetworkSelective(networkEntity));
    }
    @Test
    public void testupdateNetwork() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        NetworkDaoImpl impl = new NetworkDaoImpl();
        NetworkEntity networkEntity =new NetworkEntity();
        networkEntity.setId("1");
        assertEquals(0, impl.updateNetwork(networkEntity));

    }
    @Test
    public void testupdateNetworkByVimId() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        NetworkDaoImpl impl = new NetworkDaoImpl();
        NetworkEntity networkEntity =new NetworkEntity();
        networkEntity.setId("1");
        assertEquals(0, impl.updateNetworkByVimId(networkEntity));

    }
}
