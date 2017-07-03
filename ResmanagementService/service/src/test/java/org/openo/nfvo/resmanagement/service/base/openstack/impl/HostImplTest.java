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
package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.business.impl.HostBusinessImpl;
import org.openo.nfvo.resmanagement.service.dao.impl.HostDaoImpl;
import org.openo.nfvo.resmanagement.service.entity.HostEntity;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class HostImplTest {

    @Test
    public void testdeleteHostByVimId() throws ServiceException {
        new MockUp<HostDaoImpl>() {

            @Mock
            public int deleteHostByVimId(String vimId) {
                return 1;
            }

        };
        HostImpl hostImpl = new HostImpl();
        HostBusinessImpl hostBusiness = new HostBusinessImpl();
        hostBusiness.setHostDao(new HostDaoImpl());
        hostImpl.setHostBusiness(hostBusiness);

        assertTrue(hostImpl.deleteResByVimId("vimId") == 1);
    }
    @Test
    public void testupdateStatusByVimId() throws ServiceException {
        HostImpl hostImpl = new HostImpl();
        hostImpl.setHostBusiness(new HostBusinessImpl());
        JSONObject json = new JSONObject();
        json.put("id", "123");
        json.put("vimId", "vim123");
        new MockUp<HostBusinessImpl>() {

            @Mock
            public int updateHostByVimId(HostEntity hostEntity) throws ServiceException {
                return 1;
            }
        };
        int result = hostImpl.updateStatusByVimId(json);
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testDelete() throws ServiceException {
        HostImpl hostImpl = new HostImpl();
        hostImpl.setHostBusiness(new HostBusinessImpl());
        new MockUp<HostBusinessImpl>() {
            @Mock
            public int deleteHost(String id) throws ServiceException {
                return 1;
            }
        };

        int result = hostImpl.delete("id");
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);

    }

    @Test
    public void testAdd1() throws ServiceException {
        HostImpl hostImpl = new HostImpl();
        hostImpl.setHostBusiness(new HostBusinessImpl());
        JSONObject json = new JSONObject();
        json.put("id", "1");
        new MockUp<HostBusinessImpl>() {
            @Mock
            public int addHost(HostEntity hostEntity) throws ServiceException {
                return 1;
            }

        };
        int result = hostImpl.add(json);
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testupdate() throws ServiceException {
        HostImpl hostImpl = new HostImpl();
        hostImpl.setHostBusiness(new HostBusinessImpl());
        HostEntity hostEntity = new HostEntity();
        hostEntity.setId("123");
        new MockUp<HostBusinessImpl>() {
            @Mock
            public int updateHostSelective(HostEntity hostEntity) throws ServiceException {
                return 1;
            }

        };
        int result = hostImpl.update(hostEntity);
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testUpdateResource() throws ServiceException {
        HostImpl hostImpl = new HostImpl();
        hostImpl.setHostBusiness(new HostBusinessImpl());
        JSONObject json = new JSONObject();
        json.put("id", "123");
        json.put("vimId", "vim123");
        new MockUp<HostBusinessImpl>() {

            @Mock
            public int updateHostSelective(HostEntity hostEntity) throws ServiceException {
                return 1;
            }
        };
        int result = hostImpl.update(json);
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testGetList() throws ServiceException {
        Map<String, Object> condition = new HashMap<>();
        HostImpl hostImpl = new HostImpl();
        hostImpl.setHostBusiness(new HostBusinessImpl());
        new MockUp<HostBusinessImpl>() {

            @Mock
            public List<HostEntity> getHosts(Map<String, Object> condition) {
                return null;
            }
        };
        List<HostEntity> result = hostImpl.getList(condition);
        List<HostEntity> exceptedResult = null;
        assertEquals(exceptedResult, result);
    }

}