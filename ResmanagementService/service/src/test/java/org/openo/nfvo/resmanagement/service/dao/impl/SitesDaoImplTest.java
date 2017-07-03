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
import org.openo.nfvo.resmanagement.service.entity.NetworkEntity;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;
import org.openo.nfvo.resmanagement.service.mapper.NetworkMapper;
import org.openo.nfvo.resmanagement.service.mapper.SitesMapper;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class SitesDaoImplTest {
    @Mocked
    SitesMapper mapper;
    @Test
    public void testdeleteSite() throws ServiceException {

         new Expectations() {{
             mapper.deleteSite("123");
           }};

        new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        SitesDaoImpl impl = new SitesDaoImpl();
        assertEquals(0, impl.deleteSite("123"));
    }
    @Test
    public void testaddSite() throws ServiceException {
            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        SitesDaoImpl impl = new SitesDaoImpl();
        SitesEntity sitesEntity =new SitesEntity();
        sitesEntity.setId("1");
        assertEquals(0, impl.addSite(sitesEntity));
    }
    @Test
    public void testaddSiteSelective() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        SitesDaoImpl impl = new SitesDaoImpl();
        SitesEntity sitesEntity =new SitesEntity();
        sitesEntity.setId("1");
        assertEquals(0, impl.addSiteSelective(sitesEntity));
    }
    @Test
    public void testupdateSiteSelective() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        SitesDaoImpl impl = new SitesDaoImpl();
        SitesEntity sitesEntity =new SitesEntity();
        sitesEntity.setId("1");
        assertEquals(0, impl.updateSiteSelective(sitesEntity));
    }
    @Test
    public void testupdateNetwork() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        SitesDaoImpl impl = new SitesDaoImpl();
        SitesEntity sitesEntity =new SitesEntity();
        sitesEntity.setId("1");
        assertEquals(0, impl.updateSite(sitesEntity));
    }
    @Test
    public void testupdateSiteByVimId() throws ServiceException {

            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        SitesDaoImpl impl = new SitesDaoImpl();
        SitesEntity sitesEntity =new SitesEntity();
        sitesEntity.setId("1");
        assertEquals(0, impl.updateSiteByVimId(sitesEntity));
    }
}
