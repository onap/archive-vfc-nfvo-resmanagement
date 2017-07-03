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

package org.openo.nfvo.resmanagement.service.entity;

import org.apache.commons.lang3.StringUtils;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * Port Entity Class.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 31, 2016
 */
public class PortEntity {

    private String id;

    private String name;

    private String networkId;

    private String status;

    private String tenantId;

    private String vimId;

    private String vimName;

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the networkId.
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * @param networkId The networkId to set.
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Returns the tenantId.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId The tenantId to set.
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return Returns the vimId.
     */
    public String getVimId() {
        return vimId;
    }

    /**
     * @param vimId The vimId to set.
     */
    public void setVimId(String vimId) {
        this.vimId = vimId;
    }

    /**
     * @return Returns the vimName.
     */
    public String getVimName() {
        return vimName;
    }

    /**
     * @param vimName The vimName to set.
     */
    public void setVimName(String vimName) {
        this.vimName = vimName;
    }

    /**
     *
     * To Entity.<br>
     *
     * @param jsonObject
     * @return
     * @since  NFVO 0.5
     */
    public static PortEntity toEntity(JSONObject jsonObject) {
        PortEntity portEntity = new PortEntity();
        portEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "id"));
        portEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        portEntity.setNetworkId(JsonUtil.getJsonFieldStr(jsonObject, "networkId"));
        portEntity.setStatus(JsonUtil.getJsonFieldStr(jsonObject, "status"));
        portEntity.setTenantId(JsonUtil.getJsonFieldStr(jsonObject, "tenantId"));
        portEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        portEntity.setVimName(JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        return portEntity;
    }

    @Override
    public String toString() {
        JSONObject portResJson = new JSONObject();
        portResJson.put("id", StringUtils.trimToEmpty(this.getId()));
        portResJson.put("name", StringUtils.trimToEmpty(this.getName()));
        portResJson.put("networkId", StringUtils.trimToEmpty(this.getNetworkId()));
        portResJson.put("status", StringUtils.trimToEmpty(this.getStatus()));
        portResJson.put("tenantId", StringUtils.trimToEmpty(this.getTenantId()));
        portResJson.put("vimId", StringUtils.trimToEmpty(this.getVimId()));
        portResJson.put("vimName", StringUtils.trimToEmpty(this.getVimName()));
        return portResJson.toString();
    }

}
