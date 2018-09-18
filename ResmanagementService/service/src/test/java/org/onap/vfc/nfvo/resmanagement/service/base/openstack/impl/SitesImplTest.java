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

package org.onap.vfc.nfvo.resmanagement.service.base.openstack.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.impl.SitesImpl;
import org.onap.vfc.nfvo.resmanagement.service.business.impl.LimitsBusinessImpl;
import org.onap.vfc.nfvo.resmanagement.service.business.impl.SitesBusinessImpl;
import org.onap.vfc.nfvo.resmanagement.service.entity.SitesEntity;
import org.onap.vfc.nfvo.resmanagement.common.VimUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class SitesImplTest {

        @Test
        public void testUpdate1() throws ServiceException {
          SitesImpl sitesImpl = new SitesImpl();
          sitesImpl.setSitesBusiness(new SitesBusinessImpl());
          SitesEntity sitesEntity = new SitesEntity();
          sitesEntity.setId("123");
          new MockUp<SitesBusinessImpl>() {
            @Mock
              public int updateSiteSelective(SitesEntity sitesEntity) throws ServiceException {
              return 1;
            }
          };
          int result = sitesImpl.update(sitesEntity);
          int exceptedResult = 1;
          assertEquals(exceptedResult, result);
        }

    @Test
    public void testUpdateResource() throws ServiceException {
        SitesImpl sitesImpl = new SitesImpl();
        sitesImpl.setSitesBusiness(new SitesBusinessImpl());
        JSONObject json = new JSONObject();
        json.put("id", "123");
        json.put("vimId", "vim123");
        new MockUp<SitesBusinessImpl>() {

            @Mock
            public int updateSiteResource(SitesEntity sitesEntity) throws ServiceException {
                return 1;
            }
        };
        int result = sitesImpl.updateResource(json);
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testUpdateStatusByVimId() throws ServiceException {
        SitesImpl sitesImpl = new SitesImpl();
        sitesImpl.setSitesBusiness(new SitesBusinessImpl());
        JSONObject json = new JSONObject();
        json.put("id", "123");
        json.put("vimId", "vim123");
        new MockUp<SitesBusinessImpl>() {

            @Mock
            public int updateSiteByVimId(SitesEntity sitesEntity) throws ServiceException {
                return 1;
            }
        };
        int result = sitesImpl.updateStatusByVimId(json);
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testGetList() throws ServiceException {
        Map<String, Object> condition = new HashMap<>();
        SitesImpl sitesImpl = new SitesImpl();
        sitesImpl.setSitesBusiness(new SitesBusinessImpl());
        new MockUp<SitesBusinessImpl>() {

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                return null;
            }
        };
        List<SitesEntity> result = sitesImpl.getList(condition);
        List<SitesEntity> exceptedResult = null;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testGetNull() throws ServiceException {
        Map<String, Object> condition = new HashMap<>();
        SitesImpl sitesImpl = new SitesImpl();
        sitesImpl.setSitesBusiness(new SitesBusinessImpl());
        new MockUp<SitesBusinessImpl>() {

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                return null;
            }
        };
        SitesEntity result = sitesImpl.get(condition);
        SitesEntity exceptedResult = null;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testGet() throws ServiceException {
        Map<String, Object> condition = new HashMap<>();
        SitesImpl sitesImpl = new SitesImpl();
        sitesImpl.setSitesBusiness(new SitesBusinessImpl());
        new MockUp<SitesBusinessImpl>() {

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                List<SitesEntity> list = new ArrayList<SitesEntity>();
                return list;
            }
        };
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setId("123");
        sitesImpl.deleteResByVimId("vimId");
        SitesEntity result = sitesImpl.get(condition);
    }

    SitesImpl sitesImpl = new SitesImpl();
    @Test
    public void testGetWithData() throws ServiceException {
        Map<String, Object> condition = new HashMap<>();
        sitesImpl.setSitesBusiness(new SitesBusinessImpl());
        new MockUp<SitesBusinessImpl>() {

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                List<SitesEntity> list = new ArrayList<SitesEntity>();
                SitesEntity se = new SitesEntity();
                se.setId("id_123");
                se.setName("name_123");
                list.add(se);
                return list;
            }
        };
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setId("id_123");
        sitesImpl.deleteResByVimId("vimId");
        SitesEntity result = sitesImpl.get(condition);
        SitesEntity exceptedResult = new SitesEntity();
        exceptedResult.setId("id_123");
        assertEquals(exceptedResult.getId(), result.getId());
    }

    @Test(expected=ServiceException.class)
    public void testUpdate() throws ServiceException{
        this.testGetWithData();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id_123");
        jsonObject.put("name", "name_123");
        jsonObject.put("location", "location_123");
        jsonObject.put("country", "country_123");
        jsonObject.put("vimId", "vimId_123");
        jsonObject.put("vimName", "vimName_123");
        jsonObject.put("status", "success");
        jsonObject.put("totalCPU", "05");
        jsonObject.put("totalMemory", "100");
        jsonObject.put("totalDisk", "03");
        jsonObject.put("usedCPU", "0");
        jsonObject.put("usedMemory", "13");
        jsonObject.put("usedDisk", "01");
        jsonObject.put("action", "Started");
        sitesImpl.update(jsonObject);
    }

    @Test(expected=NullPointerException.class)
    public void testUpdateSiteNull() throws ServiceException{
      this.testGet();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("id", "id_123");
      jsonObject.put("name", "name_123");
      jsonObject.put("location", "location_123");
      jsonObject.put("country", "country_123");
      jsonObject.put("vimId", "vimId_123");
      jsonObject.put("vimName", "vimName_123");
      jsonObject.put("status", "success");
      jsonObject.put("totalCPU", "05");
      jsonObject.put("totalMemory", "1000");
      jsonObject.put("totalDisk", "03");
      jsonObject.put("usedCPU", "0");
      jsonObject.put("usedMemory", "13");
      jsonObject.put("usedDisk", "01");
      jsonObject.put("action", "Started");
      sitesImpl.update(jsonObject);
    }

    @Test(expected=ServiceException.class)
    public void testUpdateAction() throws ServiceException{
      this.testGetWithData();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("id", "id_123");
      jsonObject.put("name", "name_123");
      jsonObject.put("location", "location_123");
      jsonObject.put("country", "country_123");
      jsonObject.put("vimId", "vimId_123");
      jsonObject.put("vimName", "vimName_123");
      jsonObject.put("status", "success");
      jsonObject.put("totalCPU", "05");
      jsonObject.put("totalMemory", "100");
      jsonObject.put("totalDisk", "03");
      jsonObject.put("usedCPU", "0");
      jsonObject.put("usedMemory", "13");
      jsonObject.put("usedDisk", "01");
      jsonObject.put("action", "online");
      sitesImpl.update(jsonObject);
    }

    @Test(expected=NullPointerException.class)
    public void testUpdateActionMemory() throws ServiceException{
      this.testGetWithData();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("id", "id_123");
      jsonObject.put("name", "name_123");
      jsonObject.put("location", "location_123");
      jsonObject.put("country", "country_123");
      jsonObject.put("vimId", "vimId_123");
      jsonObject.put("vimName", "vimName_123");
      jsonObject.put("status", "success");
      jsonObject.put("totalCPU", "05");
      jsonObject.put("totalMemory", "1000");
      jsonObject.put("totalDisk", "03");
      jsonObject.put("usedCPU", "0");
      jsonObject.put("usedMemory", "0");
      jsonObject.put("usedDisk", "0");
      jsonObject.put("action", "online");
      sitesImpl.update(jsonObject);
    }

    @Test(expected=NullPointerException.class)
    public void testUpdateMemory() throws ServiceException{
      this.testGetWithData();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("id", "id_123");
      jsonObject.put("name", "name_123");
      jsonObject.put("location", "location_123");
      jsonObject.put("country", "country_123");
      jsonObject.put("vimId", "vimId_123");
      jsonObject.put("vimName", "vimName_123");
      jsonObject.put("status", "success");
      jsonObject.put("totalCPU", "05");
      jsonObject.put("totalMemory", "1000");
      jsonObject.put("totalDisk", "03");
      jsonObject.put("usedCPU", "0");
      jsonObject.put("usedMemory", "0");
      jsonObject.put("usedDisk", "0");
      jsonObject.put("action", "Started");
      sitesImpl.update(jsonObject);
    }

    @Test(expected=NullPointerException.class)
    public void testAdd() throws ServiceException{
      new MockUp<VimUtil>(){
        @Mock
          public String getVimIdByName(String name) {
          return "Vim_Id_123";
        }
      };
      new MockUp<LimitsBusinessImpl>(){
        @Mock
          public JSONObject getLimits(String vimId) throws ServiceException {
          JSONObject json = new JSONObject();
          json.put("vimId", "vim_id_123");
          json.put("vimName", "vim_name_123");
          json.put("totalCPU", "12");
          json.put("totalMemory", "13");
          json.put("totalDisk", "05");
          json.put("usedCPU", "0");
          json.put("usedMemory", "0");
          json.put("usedDisk", "0");
          return json;
        }
      };
    this.testGetWithData();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", "id_123");
    jsonObject.put("name", "name_123");
    jsonObject.put("location", "location_123");
    jsonObject.put("country", "country_123");
    jsonObject.put("vimId", "vimId_123");
    jsonObject.put("vimName", "vimName_123");
    jsonObject.put("status", "success");
    jsonObject.put("totalCPU", "05");
    jsonObject.put("totalMemory", "1000");
    jsonObject.put("totalDisk", "03");
    jsonObject.put("usedCPU", "0");
    jsonObject.put("usedMemory", "0");
    jsonObject.put("usedDisk", "0");
    jsonObject.put("action", "online");
    sitesImpl.add(jsonObject);
    }
}
