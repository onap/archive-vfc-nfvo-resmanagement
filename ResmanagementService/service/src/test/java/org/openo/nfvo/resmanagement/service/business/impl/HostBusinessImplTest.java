/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.nfvo.resmanagement.service.business.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.dao.impl.HostDaoImpl;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;

import mockit.Mock;
import mockit.MockUp;

public class HostBusinessImplTest {

    @Test
    public void testGetHost() {
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        assertTrue(hostBussinessImp.getHost("") == null);
    }

    @Test
    public void testGetHostBranch() {
        new MockUp<HostDaoImpl>() {

            @Mock
            public HostEntity getHost(String id) {
                return new HostEntity();
            }

        };
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        hostBussinessImp.setHostDao(new HostDaoImpl());
        assertTrue(hostBussinessImp.getHost("id") != null);
    }

    @Test(expected = ServiceException.class)
    public void testDelete() throws ServiceException {
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        hostBussinessImp.deleteHost("");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateHost() throws ServiceException {
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        hostBussinessImp.updateHost(null);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteHostByVimId() throws ServiceException {
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        hostBussinessImp.deleteHostByVimId("");
    }

    @Test(expected = ServiceException.class)
    public void testUpdateHostByVimId() throws ServiceException {
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        hostBussinessImp.updateHostByVimId(null);
    }

    @Test(expected = ServiceException.class)
    public void testAddHost() throws ServiceException {
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        hostBussinessImp.addHost(null);
    }

    @Test(expected = ServiceException.class)
    public void testAddHostSelective() throws ServiceException {
        HostBusinessImpl hostBussinessImp = new HostBusinessImpl();
        hostBussinessImp.addHostSelective(null);
    }
}
