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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.business.impl.SitesBusinessImpl;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;

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

}
