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

package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.base.openstack.impl.VimImpl;
import org.openo.nfvo.resmanagement.service.business.impl.VimBusinessImpl;
import org.openo.nfvo.resmanagement.service.entity.VimEntity;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class VimImplTest {

    @Test
    public void testAdd() throws ServiceException {
        VimImpl vimImpl = new VimImpl();
        vimImpl.setVimBusiness(new VimBusinessImpl());
        new MockUp<VimBusinessImpl>() {

            @Mock
            public int addVim(String id) throws ServiceException {
                return 1;
            }
        };
        int result = vimImpl.add("id");
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testAdd1() throws ServiceException {
        VimImpl vimImpl = new VimImpl();
        vimImpl.setVimBusiness(new VimBusinessImpl());
        JSONObject json = new JSONObject();
        json.put("id", "123");
        new MockUp<VimBusinessImpl>() {

            @Mock
            public int addVim(String id) throws ServiceException {
                return 1;
            }
        };
        int result = vimImpl.add(json);
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testUpdate() throws ServiceException {
        VimImpl vimImpl = new VimImpl();
        vimImpl.setVimBusiness(new VimBusinessImpl());
        JSONObject json = new JSONObject();
        json.put("id", "123");
        json.put("vimId", "vim123");
        int result = vimImpl.update(json);
        int exceptedResult = 0;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testDelete() throws ServiceException {
        VimImpl vimImpl = new VimImpl();
        vimImpl.setVimBusiness(new VimBusinessImpl());
        new MockUp<VimBusinessImpl>() {

            @Mock
            public int deleteVim(String id) throws ServiceException {
                return 1;
            }
        };
        int result = vimImpl.delete("id");
        int exceptedResult = 1;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testGetVim() throws ServiceException {
        VimImpl vimImpl = new VimImpl();
        vimImpl.setVimBusiness(new VimBusinessImpl());
        new MockUp<VimBusinessImpl>() {

            @Mock
            public VimEntity getVim(String id) {
                return null;
            }
        };
        VimEntity result = vimImpl.getVim("id");
        VimEntity exceptedResult = null;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testGetList() throws ServiceException {
        VimImpl vimImpl = new VimImpl();
        vimImpl.setVimBusiness(new VimBusinessImpl());
        new MockUp<VimBusinessImpl>() {

            @Mock
            public List<VimEntity> getVims() {
                return null;
            }
        };
        List<VimEntity> result = vimImpl.getList();
        List<VimEntity> exceptedResult = null;
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testSetVimBusiness() throws ServiceException {
        VimImpl vimImpl = new VimImpl();
        vimImpl.setVimBusiness(new VimBusinessImpl());
    }

}
