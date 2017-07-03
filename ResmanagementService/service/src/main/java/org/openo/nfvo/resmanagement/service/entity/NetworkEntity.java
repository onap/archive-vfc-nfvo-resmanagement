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
 *
 * Network entity class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class NetworkEntity {

    /**
     * This field corresponds to the database column network.ID
     */
    private String id;

    /**
     * This field corresponds to the database column network.NAME
     */
    private String name;

    /**
     * This field corresponds to the database column network.TENANT_ID
     */
    private String tenantId;

    /**
     * This field corresponds to the database column network.VIM_ID
     */
    private String vimId;

    /**
     * This field corresponds to the database column ivm.VIM_NAME
     */
    private String vimName;

    /**
     * This field corresponds to the database column network.STATUS
     */
    private String status;

    /**
     * This field corresponds to the database column network.PHYSICAL_NETWORK
     */

    private String physicalNetwork;

    /**
     * This field corresponds to the database column network.NETWORK_TYPE
     */
    private String networkType;

    /**
     * This field corresponds to the database column network.SEGMENTATION_ID
     */
    private String segmentationId;

    /**
     * return the value of network.ID
     */
    public String getId() {
        return id;
    }

    /**
     * This method sets the value of the database column network.ID
     *
     * @param id
     *            the value for network.ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * return the value of network.NAME
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the value of the database column network.NAME
     *
     * @param name
     *            the value for network.NAME
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * return the value of network.TENANT_ID
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method sets the value of the database column network.TENANT_ID
     *
     * @param tenantId
     *            the value for network.TENANT_ID
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    /**
     * return the value of network.VIM_ID
     */
    public String getVimId() {
        return vimId;
    }

    /**
     * This method sets the value of the database column network.VIM_ID
     *
     * @param networkVimId
     *            the value for network.VIM_ID
     */
    public void setVimId(String networkVimId) {
        this.vimId = networkVimId == null ? null : networkVimId.trim();
    }

    /**
     * return the value of ivm.VIM_NAME
     */
    public String getVimName() {
        return vimName;
    }

    /**
     * This method sets the value of the database column ivm.VIM_NAME
     *
     * @param networkVimName
     *            the value for ivm.VIM_NAME
     */
    public void setVimName(String networkVimName) {
        this.vimName = networkVimName == null ? null : networkVimName.trim();
    }

    /**
     * return the value of network.STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method sets the value of the database column network.STATUS
     *
     * @param networkStatus
     *            the value for network.STATUS
     */
    public void setStatus(String networkStatus) {
        this.status = networkStatus == null ? null : networkStatus.trim().toLowerCase();
    }

    /**
     * return the value of network.PHYSICAL_NETWORK
     */
    public String getPhysicalNetwork() {
        return physicalNetwork;
    }

    /**
     * This method sets the value of the database column
     * network.PHYSICAL_NETWORK
     *
     * @param physicalNetwork
     *            the value for network.PHYSICAL_NETWORK
     */
    public void setPhysicalNetwork(String physicalNetwork) {
        this.physicalNetwork = physicalNetwork == null ? null : physicalNetwork.trim();
    }

    /**
     * return the value of network.NETWORK_TYPE
     */
    public String getNetworkType() {
        return networkType;
    }

    /**
     * This method sets the value of the database column network.NETWORK_TYPE
     *
     * @param networkType
     *            the value for network.NETWORK_TYPE
     */
    public void setNetworkType(String networkType) {
        this.networkType = networkType == null ? null : networkType.trim();
    }

    /**
     * return the value of network.SEGMENTATION_ID
     */
    public String getSegmentationId() {
        return segmentationId;
    }

    /**
     * This method sets the value of the database column network.SEGMENTATION_ID
     *
     * @param segmentationId
     *            the value for network.SEGMENTATION_ID
     */
    public void setSegmentationId(String segmentationId) {
        this.segmentationId = segmentationId == null ? null : segmentationId.trim();
    }

    /**
     *
     * To Entity.<br>
     *
     * @param jsonObject
     * @return
     * @since  NFVO 0.5
     */
    public static NetworkEntity toEntity(JSONObject jsonObject) {
        NetworkEntity sitesEntity = new NetworkEntity();
        sitesEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "id"));
        sitesEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        sitesEntity.setTenantId(JsonUtil.getJsonFieldStr(jsonObject, "tenantId"));
        sitesEntity.setStatus(JsonUtil.getJsonFieldStr(jsonObject, "status"));
        sitesEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        sitesEntity.setVimName(JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        sitesEntity.setPhysicalNetwork(JsonUtil.getJsonFieldStr(jsonObject, "physicalNetwork"));
        sitesEntity.setNetworkType(JsonUtil.getJsonFieldStr(jsonObject, "networkType"));
        sitesEntity.setSegmentationId(JsonUtil.getJsonFieldStr(jsonObject, "segmentationId"));
        return sitesEntity;
    }

    @Override
    public String toString() {
        JSONObject networkJson = new JSONObject();
        networkJson.put("id", StringUtils.trimToEmpty(this.getId()));
        networkJson.put("name", StringUtils.trimToEmpty(this.getName()));
        networkJson.put("tenant_id", StringUtils.trimToEmpty(this.getTenantId()));
        networkJson.put("status", StringUtils.trimToEmpty(this.getStatus()));
        networkJson.put("vimId", StringUtils.trimToEmpty(this.getVimId()));
        networkJson.put("vimName", StringUtils.trimToEmpty(this.getVimName()));
        networkJson.put("provider:physical_network", StringUtils.trimToEmpty(this.getPhysicalNetwork()));
        networkJson.put("provider:network_type", StringUtils.trimToEmpty(this.getNetworkType()));
        networkJson.put("provider:segmentation_id", StringUtils.trimToEmpty(this.getSegmentationId()));
        return networkJson.toString();
    }
}
