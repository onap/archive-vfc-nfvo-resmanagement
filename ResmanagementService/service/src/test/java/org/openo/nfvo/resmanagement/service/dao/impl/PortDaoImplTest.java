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

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;
import org.openo.nfvo.resmanagement.service.entity.PortEntity;
import org.openo.nfvo.resmanagement.service.mapper.NetworkMapper;
import org.openo.nfvo.resmanagement.service.mapper.PortMapper;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class PortDaoImplTest {
    @Mocked
    PortMapper mapper;


    @Test
    public void testdeletePort() throws ServiceException {

         new Expectations() {{
             mapper.deletePort("123");
           }};

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        PortDaoImpl impl = new PortDaoImpl();
        assertEquals(0, impl.deletePort("123"));


    }
    @Test
    public void testdeletePortByVimId() throws ServiceException {
            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        PortDaoImpl impl = new PortDaoImpl();
        assertEquals(0, impl.deletePortByVimId("123"));

    }

    @Test
    public void testaddPort() throws ServiceException {
            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        PortDaoImpl impl = new PortDaoImpl();
        PortEntity portEntity =new PortEntity();
        portEntity.setId("1");
        assertEquals(0, impl.addPort(portEntity));

    }

    @Test
    public void testaddPortSelective() throws ServiceException {

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        PortDaoImpl impl = new PortDaoImpl();
        PortEntity portEntity =new PortEntity();
        portEntity.setId("1");
        assertEquals(0, impl.addPortSelective(portEntity));

    }

    @Test
    public void testupdatePortSelective() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        PortDaoImpl impl = new PortDaoImpl();
        PortEntity portEntity =new PortEntity();
        portEntity.setId("1");
        assertEquals(0, impl.updatePortSelective(portEntity));
    }
    @Test
    public void testupdatePort() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        PortDaoImpl impl = new PortDaoImpl();
        PortEntity portEntity =new PortEntity();
        portEntity.setId("1");
        assertEquals(0, impl.updatePort(portEntity));

    }
    @Test
    public void testupdatePortByVimId() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        PortDaoImpl impl = new PortDaoImpl();
        PortEntity portEntity =new PortEntity();
        portEntity.setId("1");
        assertEquals(0, impl.updatePortByVimId(portEntity));

    }
}
