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

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * @author l00345485
 * @date 2016-10-27
 */
public class VirtualLinkEntity implements Serializable {

    /**  */
    private String id;

    /**  */
    private String name;

    /**  */
    private String backendId;

    /**  */
    private String isPublic;

    /**  */
    private String dcName;

    /**  */
    private String vimId;

    /**  */
    private String vimName;

    /**  */
    private String physicialNet;

    /**  */
    private String nsId;

    /**  */
    private String nsName;

    /**  */
    private String description;

    /**  */
    private String networkType;

    /**  */
    private String segmentation;

    /**  */
    private String mtu;

    /**  */
    private String vlanTransparent;

    /**  */
    private String routerExternal;

    /**  */
    private String resourceProviderType;

    /**  */
    private String resourceProviderId;

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

    public String getBackendId() {
        return backendId;
    }

    public void setBackendId(String backendId) {
        this.backendId = backendId;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
    }

    public String getVimId() {
        return vimId;
    }

    public void setVimId(String vimId) {
        this.vimId = vimId;
    }

    public String getVimName() {
        return vimName;
    }

    public void setVimName(String vimName) {
        this.vimName = vimName;
    }

    public String getPhysicialNet() {
        return physicialNet;
    }

    public void setPhysicialNet(String physicialNet) {
        this.physicialNet = physicialNet;
    }

    public String getNsId() {
        return nsId;
    }

    public void setNsId(String nsId) {
        this.nsId = nsId;
    }

    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getMtu() {
        return mtu;
    }

    public void setMtu(String mtu) {
        this.mtu = mtu;
    }

    public String getVlanTransparent() {
        return vlanTransparent;
    }

    public void setVlanTransparent(String vlanTransparent) {
        this.vlanTransparent = vlanTransparent;
    }

    public String getRouterExternal() {
        return routerExternal;
    }

    public void setRouterExternal(String routerExternal) {
        this.routerExternal = routerExternal;
    }

    public String getResourceProviderType() {
        return resourceProviderType;
    }

    public void setResourceProviderType(String resourceProviderType) {
        this.resourceProviderType = resourceProviderType;
    }

    public String getResourceProviderId() {
        return resourceProviderId;
    }

    public void setResourceProviderId(String resourceProviderId) {
        this.resourceProviderId = resourceProviderId;
    }

    /**
     * <br>
     * 
     * @param jsonObject
     * @return
     * @since NFVO 0.5
     */
    public static VirtualLinkEntity toEntity(JSONObject jsonObject) {
        VirtualLinkEntity virtualLinkEntity = new VirtualLinkEntity();
        virtualLinkEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "vlInstanceId"));
        virtualLinkEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        virtualLinkEntity.setBackendId(JsonUtil.getJsonFieldStr(jsonObject, "backendId"));
        virtualLinkEntity.setIsPublic(JsonUtil.getJsonFieldStr(jsonObject, "isPublic"));
        virtualLinkEntity.setDcName(JsonUtil.getJsonFieldStr(jsonObject, "dcName"));
        virtualLinkEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        virtualLinkEntity.setVimName(JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        virtualLinkEntity.setPhysicialNet(JsonUtil.getJsonFieldStr(jsonObject, "physicialNet"));
        virtualLinkEntity.setNsId(JsonUtil.getJsonFieldStr(jsonObject, "nsId"));
        virtualLinkEntity.setNsName(JsonUtil.getJsonFieldStr(jsonObject, "nsName"));
        virtualLinkEntity.setDescription(JsonUtil.getJsonFieldStr(jsonObject, "description"));
        virtualLinkEntity.setNetworkType(JsonUtil.getJsonFieldStr(jsonObject, "networkType"));
        virtualLinkEntity.setSegmentation(JsonUtil.getJsonFieldStr(jsonObject, "segmentation"));
        virtualLinkEntity.setMtu(JsonUtil.getJsonFieldStr(jsonObject, "mtu"));
        virtualLinkEntity.setVlanTransparent(JsonUtil.getJsonFieldStr(jsonObject, "vlanTransparent"));
        virtualLinkEntity.setRouterExternal(JsonUtil.getJsonFieldStr(jsonObject, "routerExternal"));
        virtualLinkEntity.setResourceProviderId(JsonUtil.getJsonFieldStr(jsonObject, "resourceProviderId"));
        virtualLinkEntity.setResourceProviderType(JsonUtil.getJsonFieldStr(jsonObject, "resourceProviderType"));
        return virtualLinkEntity;
    }

    @Override
    public String toString() {
        JSONObject virtualLinkResJson = new JSONObject();
        virtualLinkResJson.put("id", StringUtils.trimToEmpty(this.getId()));
        virtualLinkResJson.put("name", StringUtils.trimToEmpty(this.getName()));
        virtualLinkResJson.put("backendId", StringUtils.trimToEmpty(this.getBackendId()));
        virtualLinkResJson.put("isPublic", StringUtils.trimToEmpty(this.getIsPublic()));
        virtualLinkResJson.put("dcName", StringUtils.trimToEmpty(this.getDcName()));
        virtualLinkResJson.put("vimId", StringUtils.trimToEmpty(this.getVimId()));
        virtualLinkResJson.put("vimName", StringUtils.trimToEmpty(this.getVimName()));
        virtualLinkResJson.put("physicialNet", StringUtils.trimToEmpty(this.getPhysicialNet()));
        virtualLinkResJson.put("nsId", StringUtils.trimToEmpty(this.getNsId()));
        virtualLinkResJson.put("nsName", StringUtils.trimToEmpty(this.getNsName()));
        virtualLinkResJson.put("description", StringUtils.trimToEmpty(this.getDescription()));
        virtualLinkResJson.put("networkType", StringUtils.trimToEmpty(this.getNetworkType()));
        virtualLinkResJson.put("segmentation", StringUtils.trimToEmpty(this.getSegmentation()));
        virtualLinkResJson.put("mtu", StringUtils.trimToEmpty(this.getMtu()));
        virtualLinkResJson.put("vlanTransparent", StringUtils.trimToEmpty(this.getVlanTransparent()));
        virtualLinkResJson.put("routerExternal", StringUtils.trimToEmpty(this.getRouterExternal()));
        virtualLinkResJson.put("resourceProviderId", StringUtils.trimToEmpty(this.getResourceProviderId()));
        virtualLinkResJson.put("resourceProviderType", StringUtils.trimToEmpty(this.getResourceProviderType()));
        return virtualLinkResJson.toString();
    }
}
