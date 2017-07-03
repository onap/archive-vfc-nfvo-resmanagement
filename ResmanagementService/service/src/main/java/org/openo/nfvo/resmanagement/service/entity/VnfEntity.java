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
 * @date 2016-10-28
 */
public class VnfEntity implements Serializable {

    /**  */
    private String id;

    /**  */
    private String vnfInstanceId;

    /**  */
    private String vnfInstanceName;

    /**  */
    private String nsId;

    /**  */
    private String nsName;

    /**  */
    private String vnfmId;

    /**  */
    private String vnfmName;

    /**  */
    private String vnfPackageName;

    /**  */
    private String vnfDescriptorName;

    /**  */
    private String vimId;

    /**  */
    private String vimName;

    /**  */
    private String vimTenant;

    /**  */
    private String jobId;

    /**  */
    private String vnfStatus;

    /**  */
    private String vnfType;

    /**  */
    private Integer maxVm;

    /**  */
    private Integer maxCpu;

    /**  */
    private Integer maxDisk;

    /**  */
    private Integer maxRam;

    /**  */
    private Integer maxShd;

    /**  */
    private Integer maxNet;

    /**  */
    private String name;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVnfInstanceId() {
        return vnfInstanceId;
    }

    public void setVnfInstanceId(String vnfInstanceId) {
        this.vnfInstanceId = vnfInstanceId;
    }

    public String getVnfInstanceName() {
        return vnfInstanceName;
    }

    public void setVnfInstanceName(String vnfInstanceName) {
        this.vnfInstanceName = vnfInstanceName;
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

    public String getVnfmId() {
        return vnfmId;
    }

    public void setVnfmId(String vnfmId) {
        this.vnfmId = vnfmId;
    }

    public String getVnfmName() {
        return vnfmName;
    }

    public void setVnfmName(String vnfmName) {
        this.vnfmName = vnfmName;
    }

    public String getVnfPackageName() {
        return vnfPackageName;
    }

    public void setVnfPackageName(String vnfPackageName) {
        this.vnfPackageName = vnfPackageName;
    }

    public String getVnfDescriptorName() {
        return vnfDescriptorName;
    }

    public void setVnfDescriptorName(String vnfDescriptorName) {
        this.vnfDescriptorName = vnfDescriptorName;
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

    public String getVimTenant() {
        return vimTenant;
    }

    public void setVimTenant(String vimTenant) {
        this.vimTenant = vimTenant;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getVnfStatus() {
        return vnfStatus;
    }

    public void setVnfStatus(String vnfStatus) {
        this.vnfStatus = vnfStatus;
    }

    public String getVnfType() {
        return vnfType;
    }

    public void setVnfType(String vnfType) {
        this.vnfType = vnfType;
    }

    public Integer getMaxVm() {
        return maxVm;
    }

    public void setMaxVm(Integer maxVm) {
        this.maxVm = maxVm;
    }

    public Integer getMaxCpu() {
        return maxCpu;
    }

    public void setMaxCpu(Integer maxCpu) {
        this.maxCpu = maxCpu;
    }

    public Integer getMaxDisk() {
        return maxDisk;
    }

    public void setMaxDisk(Integer maxDisk) {
        this.maxDisk = maxDisk;
    }

    public Integer getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(Integer maxRam) {
        this.maxRam = maxRam;
    }

    public Integer getMaxShd() {
        return maxShd;
    }

    public void setMaxShd(Integer maxShd) {
        this.maxShd = maxShd;
    }

    public Integer getMaxNet() {
        return maxNet;
    }

    public void setMaxNet(Integer maxNet) {
        this.maxNet = maxNet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * <br>
     * 
     * @param jsonObject
     * @return
     * @since NFVO 0.5
     */
    public static VnfEntity toEntity(JSONObject jsonObject) {
        VnfEntity vnfEntity = new VnfEntity();
        vnfEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "id"));
        vnfEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        vnfEntity.setVnfInstanceId(JsonUtil.getJsonFieldStr(jsonObject, "vnfInstanceId"));
        vnfEntity.setVnfInstanceName(JsonUtil.getJsonFieldStr(jsonObject, "vnfInstanceName"));
        vnfEntity.setNsId(JsonUtil.getJsonFieldStr(jsonObject, "nsId"));
        vnfEntity.setNsName(JsonUtil.getJsonFieldStr(jsonObject, "nsName"));
        vnfEntity.setVnfmId(JsonUtil.getJsonFieldStr(jsonObject, "vnfmId"));
        vnfEntity.setVnfmName(JsonUtil.getJsonFieldStr(jsonObject, "vnfmName"));
        vnfEntity.setVnfPackageName(JsonUtil.getJsonFieldStr(jsonObject, "vnfPackageName"));
        vnfEntity.setVnfDescriptorName(JsonUtil.getJsonFieldStr(jsonObject, "vnfDescriptorName"));
        vnfEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        vnfEntity.setVimName(JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        vnfEntity.setVimTenant(JsonUtil.getJsonFieldStr(jsonObject, "vimTenant"));
        vnfEntity.setJobId(JsonUtil.getJsonFieldStr(jsonObject, "jobId"));
        vnfEntity.setVnfStatus(JsonUtil.getJsonFieldStr(jsonObject, "vnfStatus"));
        vnfEntity.setVnfType(JsonUtil.getJsonFieldStr(jsonObject, "vnfType"));
        vnfEntity.setMaxVm(JsonUtil.getJsonFieldInt(jsonObject, "maxVm"));
        vnfEntity.setMaxCpu(JsonUtil.getJsonFieldInt(jsonObject, "maxCpu"));
        vnfEntity.setMaxDisk(JsonUtil.getJsonFieldInt(jsonObject, "maxDisk"));
        vnfEntity.setMaxRam(JsonUtil.getJsonFieldInt(jsonObject, "maxRam"));
        vnfEntity.setMaxShd(JsonUtil.getJsonFieldInt(jsonObject, "maxShd"));
        vnfEntity.setMaxNet(JsonUtil.getJsonFieldInt(jsonObject, "maxNet"));
        return vnfEntity;
    }

    @Override
    public String toString() {
        JSONObject vnfResJson = new JSONObject();
        vnfResJson.put("id", StringUtils.trimToEmpty(this.getId()));
        vnfResJson.put("name", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfInstanceId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfInstanceName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("nsId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("nsName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfmId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfmName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfPackageName", StringUtils.trimToEmpty(this.getId()));
        vnfResJson.put("vnfDescriptorName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vimId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vimName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vimTenant", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("jobId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfStatus", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfType", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxVm", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxCpu", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxDisk", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxRam", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxShd", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxNet", StringUtils.trimToEmpty(this.getName()));
        return vnfResJson.toString();
    }
}
