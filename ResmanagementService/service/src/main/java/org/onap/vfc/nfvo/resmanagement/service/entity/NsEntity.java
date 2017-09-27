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

package org.onap.vfc.nfvo.resmanagement.service.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.onap.vfc.nfvo.resmanagement.common.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Sep 1, 2017
 */
public class NsEntity implements Serializable {

    private String id;

    private String name;

    private String nsdId;

    private String description;

    private String status;

    private String createTime;

    private String lastUpdate;

    private String resourceVersion;

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNsdId() {
        return nsdId;
    }

    public void setNsdId(String nsdId) {
        this.nsdId = nsdId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public static NsEntity toEntity(JSONObject jsonObject) {
        NsEntity nsEntity = new NsEntity();
        nsEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "id"));
        nsEntity.setNsdId(JsonUtil.getJsonFieldStr(jsonObject, "nsdId"));
        nsEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        nsEntity.setDescription(JsonUtil.getJsonFieldStr(jsonObject, "description"));
        nsEntity.setStatus(JsonUtil.getJsonFieldStr(jsonObject, "status"));
        nsEntity.setCreateTime(JsonUtil.getJsonFieldStr(jsonObject, "createTime"));
        nsEntity.setLastUpdate(JsonUtil.getJsonFieldStr(jsonObject, "lastUpdate"));
        return nsEntity;
    }

    @Override
    public String toString() {
        JSONObject nsResJson = new JSONObject();
        nsResJson.put("id", StringUtils.trimToEmpty(this.getId()));
        nsResJson.put("nsdId", StringUtils.trimToEmpty(this.getNsdId()));
        nsResJson.put("name", StringUtils.trimToEmpty(this.getName()));
        nsResJson.put("description", StringUtils.trimToEmpty(this.getDescription()));
        nsResJson.put("status", StringUtils.trimToEmpty(this.getStatus()));
        nsResJson.put("createTime", StringUtils.trimToEmpty(this.getCreateTime()));
        nsResJson.put("lastUpdate", StringUtils.trimToEmpty(this.getLastUpdate()));
        return nsResJson.toString();
    }

    public String toStringForAai() {
        JSONObject nsResJson = new JSONObject();
        nsResJson.put("service-instnace-id", StringUtils.trimToEmpty(this.getId()));
        nsResJson.put("service-instance-name", StringUtils.trimToEmpty(this.getName()));
        nsResJson.put("description", StringUtils.trimToEmpty(this.getDescription()));
        nsResJson.put("orchestration-status", StringUtils.trimToEmpty(this.getStatus()));
        nsResJson.put("created-at", StringUtils.trimToEmpty(this.getCreateTime()));
        nsResJson.put("updated-at", StringUtils.trimToEmpty(this.getLastUpdate()));
        nsResJson.put("resource-version", StringUtils.trimToEmpty(this.getResourceVersion()));
        return nsResJson.toString();
    }

    public static NsEntity toEntityFromAai(JSONObject jsonObject) {
        NsEntity nsEntity = new NsEntity();
        nsEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "service-instance-id"));
        nsEntity.setNsdId(JsonUtil.getJsonFieldStr(jsonObject, "service-instance-id"));
        nsEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "service-instance-name"));
        nsEntity.setDescription(JsonUtil.getJsonFieldStr(jsonObject, "description"));
        nsEntity.setStatus(JsonUtil.getJsonFieldStr(jsonObject, "orchestration-status"));
        nsEntity.setCreateTime(JsonUtil.getJsonFieldStr(jsonObject, "created-at"));
        nsEntity.setLastUpdate(JsonUtil.getJsonFieldStr(jsonObject, "updated-at"));
        nsEntity.setResourceVersion(JsonUtil.getJsonFieldStr(jsonObject, "resource-version"));
        return nsEntity;
    }

}
