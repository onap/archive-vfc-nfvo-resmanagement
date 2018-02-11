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
