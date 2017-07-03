/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.dao.impl.VimDaoImpl;
import org.openo.nfvo.resmanagement.service.entity.VimEntity;

import mockit.Mock;
import mockit.MockUp;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 2016年8月18日
 */
public class VimBusinessImplTest {

    @Test
    public void testGetVimIdIsNull() {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        VimEntity result = vimBusinessImpl.getVim(null);
        VimEntity expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetVim() {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        VimEntity vimEntity = new VimEntity();
        vimEntity.setId("123");
        new MockUp<VimDaoImpl>() {

            @Mock
            public VimEntity getVim(String id) {
                VimEntity vimEntity = new VimEntity();
                vimEntity.setId("123");
                return vimEntity;
            }
        };
        VimEntity result = vimBusinessImpl.getVim("id");
        VimEntity expectedResult = vimEntity;
        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void testGetVims() throws ServiceException {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        new MockUp<VimDaoImpl>() {

            @Mock
            public List<VimEntity> getVims() {
                return null;
            }
        };
        List<VimEntity> result = vimBusinessImpl.getVims();
        List<VimEntity> expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteVimEmpty() throws ServiceException {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        try {
            vimBusinessImpl.deleteVim("");
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteVim() throws ServiceException {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        new MockUp<VimDaoImpl>() {

            @Mock
            public int deleteVim(String id) {
                return 1;
            }
        };
        int result = vimBusinessImpl.deleteVim("xian");
        int expectedResult = 1;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddVimExceptions() throws ServiceException {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        try {
            vimBusinessImpl.addVim(null);
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddVimExceptions1() throws ServiceException {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        new MockUp<VimDaoImpl>() {

            @Mock
            public VimEntity getVim(String id) {
                return new VimEntity();
            }
        };
        try {
            vimBusinessImpl.addVim("id");
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddVimExceptions2() throws ServiceException {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        VimEntity vimEntity = new VimEntity();
        vimEntity.setId("123");
        try {
            vimBusinessImpl.addVim("");
        } catch (ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddVim() throws ServiceException {
        VimBusinessImpl vimBusinessImpl = new VimBusinessImpl();
        vimBusinessImpl.setVimDao(new VimDaoImpl());
        new MockUp<VimDaoImpl>() {

            @Mock
            public VimEntity getVim(String id) {
                return null;
            }

            @Mock
            public int addVim(VimEntity vimEntity) {
                return 1;
            }
        };
        int result = vimBusinessImpl.addVim("123");
        int expectedResult = 1;
        assertEquals(expectedResult, result);
    }

}
