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

package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;
import org.openo.nfvo.resmanagement.service.business.impl.PortBusinessImpl;
import org.openo.nfvo.resmanagement.service.dao.impl.PortDaoImpl;
import org.openo.nfvo.resmanagement.service.entity.PortEntity;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class PortImplTest {

    @Test
    public void testAddBranch() throws ServiceException {
        new MockUp<PortDaoImpl>() {

            @Mock
            public PortEntity getPort(String id) {
                return null;
            }

            @Mock
            public int addPort(PortEntity portEntity) {
                return 1;
            }
        };
        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);
        JSONObject json = new JSONObject();
        json.put("id", "");
        json.put("name", "name");
        json.put("status", "status");
        json.put("tenant_id", "tenant_id");
        json.put("vimId", "vimId");
        json.put("vimName", "vimName");
        json.put("network_id", "network_id");
        assertTrue(portImpl.add(json) == 1);
    }

    @Test(expected = ServiceException.class)
    public void testAddBranch1() throws ServiceException {

        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);
        PortEntity portEntity = null;
        portImpl.add(portEntity);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateException() throws ServiceException {

        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);
        PortEntity portEntity = null;
        portImpl.update(portEntity);
    }

    @Test
    public void testUpdate() throws ServiceException {
        new MockUp<PortDaoImpl>() {

            @Mock
            public int updatePortSelective(PortEntity portEntity) {
                return 1;
            }

        };
        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);
        assertTrue(portImpl.update(new JSONObject()) == 1);
    }

    @Test
    public void testdelete() throws ServiceException {
        new MockUp<PortDaoImpl>() {

            @Mock
            public int deletePort(String id) {
                return 1;
            }

        };
        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);

    }

    @Test
    public void testDeleteResByVimId() throws ServiceException {
        new MockUp<PortDaoImpl>() {

            @Mock
            public int deletePortByVimId(String vimId) {
                return 1;
            }

        };
        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);
        assertTrue(portImpl.deleteResByVimId("vimId") == 1);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteResByVimIdException() throws ServiceException {
        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);
        portImpl.deleteResByVimId("");
    }

    @Test(expected = ServiceException.class)
    public void testDelete() throws ServiceException {
        PortImpl portImpl = new PortImpl();
        PortBusinessImpl portBusiness = new PortBusinessImpl();
        portBusiness.setPortDao(new PortDaoImpl());
        portImpl.setPortBusiness(portBusiness);
        portImpl.delete("");
    }
}
