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
package org.onap.vfc.nfvo.resmanagement.service.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.service.dao.impl.AbstractDao;
import org.onap.vfc.nfvo.resmanagement.service.dao.impl.HostDaoImpl;
import org.onap.vfc.nfvo.resmanagement.service.entity.HostEntity;
import org.onap.vfc.nfvo.resmanagement.service.entity.NetworkEntity;
import org.onap.vfc.nfvo.resmanagement.service.mapper.HostMapper;
import org.onap.vfc.nfvo.resmanagement.service.mapper.NetworkMapper;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class HostDaoImplTest {
    @Mocked
    HostMapper mapper;
    @Test
    public void testdeleteHost() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        HostDaoImpl impl = new HostDaoImpl();
        assertEquals(0, impl.deleteHost("123"));
    }

    @Test
    public void testdeleteHostByVimId() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        HostDaoImpl impl = new HostDaoImpl();
        assertEquals(0, impl.deleteHostByVimId("123"));
    }

    @Test
    public void testaddHost() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        HostDaoImpl impl = new HostDaoImpl();
        HostEntity hostEntity =new HostEntity();
        hostEntity.setId("1");
        assertEquals(0, impl.addHost(hostEntity));

    }

    @Test
    public void testaddHostSelective() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        HostDaoImpl impl = new HostDaoImpl();
        HostEntity hostEntity =new HostEntity();
        hostEntity.setId("1");
        assertEquals(0, impl.addHostSelective(hostEntity));

    }

    @Test
    public void testupdateHostSelective() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        HostDaoImpl impl = new HostDaoImpl();
        HostEntity hostEntity =new HostEntity();
        hostEntity.setId("1");
        assertEquals(0, impl.updateHostSelective(hostEntity));

    }

    @Test
    public void testupdateHost() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        HostDaoImpl impl = new HostDaoImpl();
        HostEntity hostEntity =new HostEntity();
        hostEntity.setId("1");
        assertEquals(0, impl.updateHost(hostEntity));

    }

    @Test
    public void testupdateHostByVimId() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        HostDaoImpl impl = new HostDaoImpl();
        HostEntity hostEntity =new HostEntity();
        hostEntity.setId("1");
        assertEquals(0, impl.updateHostByVimId(hostEntity));

    }

}
