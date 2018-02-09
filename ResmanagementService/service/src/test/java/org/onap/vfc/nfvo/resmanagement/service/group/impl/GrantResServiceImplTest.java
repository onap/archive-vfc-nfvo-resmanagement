
package org.onap.vfc.nfvo.resmanagement.service.group.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.common.VimUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

import mockit.Mock;
import mockit.MockUp;
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

}
