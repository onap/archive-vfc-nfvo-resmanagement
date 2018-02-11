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

package org.onap.vfc.nfvo.resmanagement.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.onap.vfc.nfvo.resmanagement.common.constant.ParamConstant;
import org.onap.vfc.nfvo.resmanagement.common.constant.UrlConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
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
 * @version VFC 1.0 Oct 30, 2016
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
     * Get vimId by vimName.<br>
     * 
     * @param name
     * @return
     * @since VFC 1.0
     */
    public static String getVimIdByName(String name) {
        JSONArray vims = getVims();
        if(vims == null) {
            return "";
        }
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
     * @return vimInfo
     *         {
     *         "vimId": "57674786-5b2e-4c92-bb68-578dbd79e2f5",
     *         "name": "vim",
     *         "url": "http://10.74.151.13:5000/v2.0",
     *         "userName": "admin",
     *         "password": "admin",
     *         "tenant": "admin",
     *         "vendor": "HW",
     *         "version": "v1.0",
     *         "description": "",
     *         "domain": "",
     *         "type": "openstack",
     *         "createTime": "2016-07-18 12:22:53"
     *         }
     */
    public static JSONObject getVimById(String vimId) {
        if(vimId == null || "".equals(vimId.trim())) {
            LOG.error("Get vim ERROR, VimId is null. ");
            return null;
        }
        Map<String, String> headerMap = new HashMap<>(5);
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Accept", "application/json");
        headerMap.put("X-TransactionId", "9999");
        headerMap.put("X-FromAppId", "esr-server");

        Base64 token = new Base64();
        String authen = new String(token.encode(("AAI:AAI").getBytes()));
        headerMap.put("Authorization", "Basic " + authen);
        LOG.info("getVimById headerMap: {}", headerMap.toString());
        RestfulParametes parametes = new RestfulParametes();
        parametes.setHeaderMap(headerMap);
        String[] cloud = vimId.trim().split("_");
        String cloudOwner = cloud[0];
        String cloudRegionId = cloud[1];

        JSONObject esrResponse = RestfulUtil.getResponseObj(
                String.format(UrlConstant.ESR_GET_VIMS_URL, cloudOwner, cloudRegionId), parametes, "get");
        LOG.info("Get vims from ESR! EsrResponse:{}", esrResponse);
        if(null == esrResponse) {
            LOG.error("ESR return fail.");
            return null;
        } else {
            return parseEsrResponse(vimId, esrResponse);
        }
    }

    /**
     * <br>
     * 
     * @param vimId
     * @param esrResponse
     *            http://172.30.3.34:80/aai/v11/cloud-infrastructure/cloud-regions/cloud-region/vmware/fake/esr-system-info-list
     *            {
     *            "esr-system-info": [
     *            {
     *            "esr-system-info-id": "f77da8eb-11c4-46e4-a10b-380c91215cfd",
     *            "service-url": "https://172.30.2.2:5000/v3",
     *            "user-name": "admin",
     *            "password": "admin",
     *            "system-type": "VIM",
     *            "ssl-insecure": true,
     *            "cloud-domain": "default",
     *            "default-tenant": "admin",
     *            "system-status": "active",
     *            "resource-version": "1508909163786"
     *            }
     *            ]
     *            }
     * @return
     * @since VFC 1.0
     */
    private static JSONObject parseEsrResponse(String vimId, JSONObject esr) {
        JSONObject esrResponse = esr.getJSONArray("esr-system-info").getJSONObject(0);
        LOG.info("parseEsrResponse: {}", esrResponse);
        JSONObject vimInfo = new JSONObject();
        vimInfo.put("vimId", vimId);
        vimInfo.put("name", esrResponse.getString("esr-system-info-id"));
        vimInfo.put("url", esrResponse.getString("service-url"));
        vimInfo.put("userName", esrResponse.getString("user-name"));
        vimInfo.put("password", esrResponse.getString("password"));
        vimInfo.put("tenant", esrResponse.getString("default-tenant"));
        vimInfo.put("vendor", "");
        vimInfo.put("version", "");
        vimInfo.put("description", "");
        vimInfo.put("domain", esrResponse.getString("cloud-domain"));
        vimInfo.put("type", esrResponse.getString("system-type"));
        vimInfo.put("createTime", "");
        LOG.info("parseEsrResponse vimInfo: {}", vimInfo);
        return vimInfo;
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
     * @since VFC 1.0
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
