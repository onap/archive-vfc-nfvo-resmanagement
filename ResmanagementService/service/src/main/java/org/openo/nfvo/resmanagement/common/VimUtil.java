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

package org.openo.nfvo.resmanagement.common;

import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.constant.UrlConstant;
import org.openo.nfvo.resmanagement.common.util.RestfulUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Oct 30, 2016
 */
public class VimUtil {

    private static final Logger LOG = LoggerFactory.getLogger(VimUtil.class);

    private VimUtil() {

    }

    /**
     * Get VIMs.
     * 
     * @return
     */
    public static JSONArray getVims() {
        String esrResponse =
                RestfulUtil.getResponseContent(UrlConstant.ESR_GET_VIMS_URL, new RestfulParametes(), "get");
        LOG.info("Get vims from ESR! EsrResponse:{}", esrResponse);
        if(null == esrResponse) {
            LOG.error("ESR return fail.");
            return null;
        } else {
            return JSONArray.fromObject(esrResponse);
        }

    }

    /**
     * Get vimId by vimName.
     * 
     * @return
     */
    public static String getVimIdByName(String name) {
        JSONArray vims = getVims();
        for(int i = 0; i < vims.size(); i++) {
            JSONObject vim = vims.getJSONObject(i);
            LOG.info("vimInfo: " + vim);
            String vimName = vim.getString("name");
            String vimId = vim.getString("vimId");
            LOG.info("name:" + name + ", vimName:" + vimName + ", vimId:" + vimId);
            if(name.trim().equalsIgnoreCase(vimName)) {
                return vimId;
            }
        }
        LOG.error("No vim with this vimName");
        return "";

    }

    /**
     * Get VIM.
     * 
     * @param vimId
     * @return
     */
    public static JSONObject getVimById(String vimId) {
        if(vimId == null || "".equals(vimId.trim())) {
            LOG.error("Get vim ERROR, VimId is null. ");
            return null;
        }
        JSONObject esrResponse = RestfulUtil.getResponseObj(String.format(UrlConstant.ESR_GET_VIM_URL, vimId), "get");
        LOG.info("Get vims from ESR! EsrResponse:{}", esrResponse);
        if(null == esrResponse) {
            LOG.error("ESR return fail.");
            return null;
        } else {
            return esrResponse;
        }
    }

    /**
     * Get tenants.
     * 
     * @param vimId
     * @return
     */
    public static JSONArray getTenants(String vimId) {
        String url = String.format(UrlConstant.GET_TENANT_URL, vimId);
        JSONObject resultObj = RestfulUtil.getResponseObj(url, ParamConstant.PARAM_GET);
        JSONArray tenants = resultObj.getJSONArray("tenants");
        LOG.info("Get tenants from multivim! tenants:{}", tenants);
        return tenants;
    }

    /**
     * <br>
     *
     * @param tenant
     * @param vimId
     * @return
     * @since NFVO 0.5
     */
    public static String getTenantIdByName(String tenant, String vimId) {
        JSONArray tenants = VimUtil.getTenants(vimId);
        String tenantId = "";
        for(int i = 0; i < tenants.size(); i++) {
            JSONObject obj = tenants.getJSONObject(i);
            String name = obj.getString("name");
            if(name.equalsIgnoreCase(tenant)) {
                tenantId = obj.getString("id");
            }
        }
        LOG.info("GetTenantIdByName tenantId:{}", tenantId);
        return tenantId;
    }
}
