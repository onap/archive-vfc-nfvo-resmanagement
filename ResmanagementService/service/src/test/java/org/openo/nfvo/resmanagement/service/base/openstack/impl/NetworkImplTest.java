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

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.business.impl.NetworkBusinessImpl;
import org.openo.nfvo.resmanagement.service.dao.impl.NetworkDaoImpl;
import org.openo.nfvo.resmanagement.service.dao.inf.NetworkDao;
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class NetworkImplTest {


    @Test
    public void testAddBranch() throws ServiceException {
        new MockUp<NetworkDaoImpl>() {

            @Mock
            public NetworkEntity getNetwork(String id) {
                return null;
            }

            @Mock
            public int addNetwork(NetworkEntity networkEntity) {
                return 1;
            }
        };
        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
        JSONObject json = new JSONObject();
        json.put("id", "id");
        json.put("name", "name");
        json.put("status", "status");
        json.put("tenant_id", "tenant_id");
        json.put("vimId", "vimId");
        json.put("vimName", "vimName");
        json.put("provider:physical_network", "provider:physical_network");
        json.put("provider:network_type", "provider:network_type");
        json.put("provider:segmentation_id", "provider:segmentation_id");
        assertTrue(networkImpl.add(json) == 1);
    }

    @Test
    public void testAddBranch1() throws ServiceException {
        new MockUp<NetworkDaoImpl>() {

            @Mock
            public NetworkEntity getNetwork(String id) {
                return null;
            }

            @Mock
            public int addNetwork(NetworkEntity networkEntity) {
                return 1;
            }
        };
        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
        JSONObject json = new JSONObject();
        json.put("id", "");
        json.put("name", "name");
        json.put("status", "status");
        json.put("tenant_id", "tenant_id");
        json.put("vimId", "vimId");
        json.put("vimName", "vimName");
        json.put("provider:physical_network", "provider:physical_network");
        json.put("provider:network_type", "provider:network_type");
        json.put("provider:segmentation_id", "provider:segmentation_id");
        assertTrue(networkImpl.add(json) == 1);
    }

    @Test(expected = ServiceException.class)
    public void testAddBranch2() throws ServiceException {
         new MockUp<NetworkDaoImpl>() {
         @Mock
         public NetworkEntity getNetwork(String id) {
             return null;
         }

         @Mock
         public int addNetwork(NetworkEntity networkEntity) {
             return 1;
         }
     };
        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
        NetworkEntity entity = null;
        networkImpl.add(entity);

    }


    @Test
    public void testDelete() throws ServiceException {
        new MockUp<NetworkDaoImpl>() {

            @Mock
            public int deleteNetwork(String id) {
                return 1;
            }
        };
        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
        assertTrue(networkImpl.delete("id") == 1);
    }

    @Test(expected = ServiceException.class)
    public void testDelete1() throws ServiceException {

        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
        networkImpl.delete("");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteVimByIdException() throws ServiceException {

        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
    networkImpl.deleteResByVimId("");
    }

    @Test
    public void testDeleteVimById() throws ServiceException {
        new MockUp<NetworkDaoImpl>() {

            @Mock
            public int deleteNetworkByVimId(String vimId) {
                return 1;
            }
        };
        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
        assertTrue(networkImpl.deleteResByVimId("vimId") == 1);
    }


    @Test
    public void testUpdate() throws ServiceException {
        new MockUp<NetworkDaoImpl>() {

            @Mock
            public int updateNetworkSelective(NetworkEntity networkEntity) {
                return 1;
            }
        };
        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);

        JSONObject json = new JSONObject();
        json.put("id", "");
        json.put("name", "name");
        json.put("status", "status");
        json.put("tenant_id", "tenant_id");
        json.put("vimId", "vimId");
        json.put("vimName", "vimName");
        json.put("provider:physical_network", "provider:physical_network");
        json.put("provider:network_type", "provider:network_type");
        json.put("provider:segmentation_id", "provider:segmentation_id");
        assertTrue(networkImpl.update(json)==1);
    }



    @Test
    public void testUpdateVimById() throws ServiceException {
        new MockUp<NetworkDaoImpl>() {

            @Mock
            public int updateNetworkByVimId(NetworkEntity NetworkEntity) {
                return 1;
            }
        };
        NetworkImpl networkImpl = new NetworkImpl();
        NetworkBusinessImpl networkBusiness = new NetworkBusinessImpl();
        NetworkDao networkDao = new NetworkDaoImpl();
        networkBusiness.setNetworkDao(networkDao);
        networkImpl.setNetworkBusiness(networkBusiness);
        JSONObject json = new JSONObject();
        json.put("id", "");
        json.put("name", "name");
        json.put("status", "status");
        json.put("tenant_id", "tenant_id");
        json.put("vimId", "vimId");
        json.put("vimName", "vimName");
        json.put("provider:physical_network", "provider:physical_network");
        json.put("provider:network_type", "provider:network_type");
        json.put("provider:segmentation_id", "provider:segmentation_id");
        assertTrue(networkImpl.updateStatusByVimId(json)==1);

    }

    @Test
    public void testgetList() throws ServiceException {
        Map<String, Object> condition = new HashMap<>();
        NetworkImpl networkImpl = new NetworkImpl();
        networkImpl.setNetworkBusiness(new NetworkBusinessImpl());
        new MockUp<NetworkBusinessImpl>() {

            @Mock
            public List<NetworkEntity> getNetworks(Map<String, Object> condition) {
                return null;
            }
        };
        List<NetworkEntity> result = networkImpl.getList(condition);
        List<NetworkEntity> exceptedResult = null;
        assertEquals(exceptedResult, result);
    }
    @Test
    public void testadd() throws ServiceException {
        NetworkImpl networkImpl = new NetworkImpl();
        networkImpl.setNetworkBusiness(new NetworkBusinessImpl());
        NetworkEntity networkEntity =new NetworkEntity();
        networkEntity.setId("1");
        new MockUp<NetworkBusinessImpl>() {

            @Mock
            public int addNetwork(NetworkEntity networkEntity ) {
                return 1;
            }
        };
         int result = networkImpl.add(networkEntity);
            int exceptedResult = 1;
            assertEquals(exceptedResult, result);
        }
    }
