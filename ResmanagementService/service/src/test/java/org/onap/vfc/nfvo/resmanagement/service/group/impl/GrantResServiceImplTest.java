/*
 * Copyright 2018 Huawei Technologies Co., Ltd.
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

package org.onap.vfc.nfvo.resmanagement.service.group.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.common.VimUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.onap.vfc.nfvo.resmanagement.service.business.impl.LimitsBusinessImpl;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GrantResServiceImplTest {

    @Test
    public void testGrantResource() throws ServiceException {
        GrantResServiceImpl impl = new GrantResServiceImpl();
        JSONObject obj = new JSONObject();
        JSONObject vimObj = new JSONObject();
        vimObj.put("vimId", "vimId");
        obj.put("additionalParam", vimObj);
        new MockUp<VimUtil>() {

            @Mock
            public JSONObject getVimById(String vimId) {
                JSONObject vim = new JSONObject();
                vim.put("tenant", "huawei");
                return vim;
            }
        };
        JSONObject result = impl.grantResource(obj);
        assertNotNull(result);
    }

    /**
     * <br>
     * {
     * "vnfInstanceId": 1,
     * "addResource": [],
     * "vimId": "1111",
     * "removeResource": [],
     * "additionalParam": {
     * }
     * }
     * 
     * @throws ServiceException
     * @since VFC 1.0
     */
    @Test
    public void testGrantResourceReal() throws ServiceException {
        GrantResServiceImpl impl = new GrantResServiceImpl();
        JSONObject obj = new JSONObject();
        JSONObject vimObj = getVimObj();
        obj.put("additionalParam", vimObj);
        obj.put("vimId", "vimId");
        JSONArray addResource = new JSONArray();
        addResource.add(getAddResObj());
        obj.put("addResource", addResource);
        new MockUp<VimUtil>() {

            @Mock
            public JSONObject getVimById(String vimId) {
                return getVimObj();
            }
        };
        new MockUp<LimitsBusinessImpl>() {

            @Mock
            public JSONObject getLimits(String vimId) throws ServiceException {
                JSONObject result = new JSONObject();
                result.put("vimId", "vimId");
                result.put("vimName", "vimName");
                result.put("totalCPU", "10");
                result.put("totalMemory", "10");
                result.put("totalDisk", "10");
                result.put("usedCPU", "1");
                result.put("usedMemory", "1");
                result.put("usedDisk", "1");
                return result;
            }
        };

        JSONObject result = impl.grantResourceReal(obj);
        assertNotNull(result);
    }

    /**
     * {
     * "vimId": "57674786-5b2e-4c92-bb68-578dbd79e2f5",
     * "name": "vim",
     * "url": "http://10.74.151.13:5000/v2.0",
     * "userName": "admin",
     * "password": "admin",
     * "tenant": "admin",
     * "vendor": "HW",
     * "version": "v1.0",
     * "description": "",
     * "domain": "",
     * "type": "openstack",
     * "createTime": "2016-07-18 12:22:53"
     * }
     * <br>
     * 
     * @return
     * @since VFC 1.0
     */
    private JSONObject getVimObj() {
        JSONObject vimObj = new JSONObject();
        vimObj.put("vimId", "57674786-5b2e-4c92-bb68-578dbd79e2f5");
        vimObj.put("name", "vim");
        vimObj.put("url", "http://10.74.151.13:5000/v2.0");
        vimObj.put("userName", "admin");
        vimObj.put("password", "admin");
        vimObj.put("tenant", "admin");
        vimObj.put("vendor", "HW");
        vimObj.put("version", "v1.0");
        vimObj.put("description", "");
        vimObj.put("domain", "");
        vimObj.put("type", "openstack");
        vimObj.put("createTime", "");
        return vimObj;
    }

    /**
     * {
     * "resourceDefinitionId": "11111",
     * "resourceTemplate": {
     * "virtualComputeDescriptor": {
     * "virtualCpu": {
     * "numVirtualCpu": 1
     * },
     * "virtualMemory": {
     * "virtualMemSize": 1//meminMB
     * }
     * },
     * "virtualStorageDescriptor": {
     * "typeOfStorage": "",
     * "sizeOfStorage": 111,
     * //diskinGB"swImageDescriptor": ""
     * }
     * },
     * "type": "vdu",
     * "vdu": "vdu_name"
     * }
     * <br>
     * 
     * @return
     * @since VFC 1.0
     */
    private JSONObject getAddResObj() {
        JSONObject addResObj = new JSONObject();
        JSONObject virtualCpu = new JSONObject();
        virtualCpu.put("numVirtualCpu", 1);
        JSONObject virtualMemory = new JSONObject();
        virtualMemory.put("virtualMemSize", 1);
        JSONObject virtualComputeDescriptor = new JSONObject();
        virtualComputeDescriptor.put("virtualCpu", virtualCpu);
        virtualComputeDescriptor.put("virtualMemory", virtualMemory);
        JSONObject virtualStorageDescriptor = new JSONObject();
        virtualStorageDescriptor.put("typeOfStorage", "");
        virtualStorageDescriptor.put("sizeOfStorage", "1");
        JSONObject resourceTemplate = new JSONObject();
        resourceTemplate.put("virtualComputeDescriptor", virtualComputeDescriptor);
        resourceTemplate.put("virtualStorageDescriptor", virtualStorageDescriptor);
        addResObj.put("resourceDefinitionId", "1");
        addResObj.put("resourceTemplate", resourceTemplate);

        return addResObj;
    }

}
