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
import org.openo.nfvo.resmanagement.common.util.StringUtil;

import net.sf.json.JSONObject;

/**
 *
 * Sites entity class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class SitesEntity {

    private String id;

    private String name;

    private String location;

    private String country;

    private String vimId;

    private String vimName;

    private String status;

    private String totalCPU = "0";

    private String totalMemory = "0";

    private String totalDisk = "0";

    private String usedCPU = "0";

    private String usedMemory = "0";

    private String usedDisk = "0";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getVimId() {
        return vimId;
    }

    public void setVimId(String siteVimId) {
        this.vimId = siteVimId == null ? null : siteVimId.trim();
    }

    public String getVimName() {
        return vimName;
    }

    public void setVimName(String siteVimName) {
        this.vimName = siteVimName == null ? null : siteVimName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String siteStatus) {
        this.status = siteStatus == null ? null : siteStatus.trim().toLowerCase();
    }

    /**
     * @return Returns the totalCPU.
     */
    public String getTotalCPU() {
        return totalCPU;
    }

    /**
     * @param totalCPU The totalCPU to set.
     */
    public void setTotalCPU(String totalCPU) {
        this.totalCPU = totalCPU == null ? null : totalCPU.trim();
    }

    /**
     * @return Returns the totalMemory.
     */
    public String getTotalMemory() {
        return totalMemory;
    }

    /**
     * @param totalMemory The totalMemory to set.
     */
    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory == null ? null : totalMemory.trim();
    }

    /**
     * @return Returns the totalDisk.
     */
    public String getTotalDisk() {
        return totalDisk;
    }

    /**
     * @param totalDisk The totalDisk to set.
     */
    public void setTotalDisk(String totalDisk) {
        this.totalDisk = totalDisk == null ? null : totalDisk.trim();
    }

    /**
     * @return Returns the usedCPU.
     */
    public String getUsedCPU() {
        return usedCPU;
    }

    /**
     * @param usedCPU The usedCPU to set.
     */
    public void setUsedCPU(String usedCPU) {
        this.usedCPU = usedCPU == null ? null : usedCPU.trim();
    }

    /**
     * @return Returns the usedMemory.
     */
    public String getUsedMemory() {
        return usedMemory;
    }

    /**
     * @param usedMemory The usedMemory to set.
     */
    public void setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory == null ? null : usedMemory.trim();
    }

    /**
     * @return Returns the usedDisk.
     */
    public String getUsedDisk() {
        return usedDisk;
    }

    /**
     * @param usedDisk The usedDisk to set.
     */
    public void setUsedDisk(String usedDisk) {
        this.usedDisk = usedDisk == null ? null : usedDisk.trim();
    }

    /**
     *
     * To Entity.<br>
     *
     * @param jsonObject
     * @return
     * @since  NFVO 0.5
     */
    public static SitesEntity toEntity(JSONObject jsonObject) {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "id"));
        sitesEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        sitesEntity.setStatus(JsonUtil.getJsonFieldStr(jsonObject, "status"));
        sitesEntity.setLocation(JsonUtil.getJsonFieldStr(jsonObject, "location"));
        sitesEntity.setCountry(JsonUtil.getJsonFieldStr(jsonObject, "country"));
        sitesEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        sitesEntity.setVimName(JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        sitesEntity.setTotalCPU(JsonUtil.getJsonFieldStr(jsonObject, "totalCPU"));
        sitesEntity.setTotalMemory(JsonUtil.getJsonFieldStr(jsonObject, "totalMemory"));
        sitesEntity.setTotalDisk(JsonUtil.getJsonFieldStr(jsonObject, "totalDisk"));
        sitesEntity.setUsedCPU(JsonUtil.getJsonFieldStr(jsonObject, "usedCPU"));
        sitesEntity.setUsedMemory(JsonUtil.getJsonFieldStr(jsonObject, "usedMemory"));
        sitesEntity.setUsedDisk(JsonUtil.getJsonFieldStr(jsonObject, "usedDisk"));
        return sitesEntity;
    }

    @Override
    public String toString() {
        JSONObject siteResJson = new JSONObject();
        siteResJson.put("id", StringUtils.trimToEmpty(this.getId()));
        siteResJson.put("name", StringUtils.trimToEmpty(this.getName()));
        siteResJson.put("status", StringUtils.trimToEmpty(this.getStatus()));
        siteResJson.put("location", StringUtils.trimToEmpty(this.getLocation()));
        siteResJson.put("country", StringUtils.trimToEmpty(this.getCountry()));
        siteResJson.put("vimId", StringUtils.trimToEmpty(this.getVimId()));
        siteResJson.put("vimName", StringUtils.trimToEmpty(this.getVimName()));
        siteResJson.put("totalCPU", StringUtils.trimToEmpty(this.getTotalCPU()));
        siteResJson.put("totalMemory", StringUtils.trimToEmpty(this.getTotalMemory()));
        siteResJson.put("totalDisk", StringUtils.trimToEmpty(this.getTotalDisk()));
        siteResJson.put("usedCPU", StringUtils.trimToEmpty(this.getUsedCPU()));
        siteResJson.put("usedMemory", StringUtils.trimToEmpty(this.getUsedMemory()));
        siteResJson.put("usedDisk", StringUtils.trimToEmpty(this.getUsedDisk()));

        return siteResJson.toString();
    }

    /**
     *
     * Check whther the resource is zero.<br>
     *
     * @param siteEntity
     * @return
     * @since  NFVO 0.5
     */
    public static boolean checkResourceIsZero(SitesEntity siteEntity) {

        if(StringUtil.isAnyLargeThanZero(siteEntity.getUsedCPU(), siteEntity.getUsedMemory(),
                siteEntity.getUsedDisk())) {
            return false;
        }

        return true;
    }

    /**
     *
     * Check resource.<br>
     *
     * @param siteEntity
     * @return
     * @since  NFVO 0.5
     */
    public static boolean checkResource(SitesEntity siteEntity) {
        String cpu = siteEntity.getTotalCPU();
        String memory = siteEntity.getTotalMemory();
        String disk = siteEntity.getTotalDisk();
        String cpuUsed = siteEntity.getUsedCPU();
        String memoryUsed = siteEntity.getUsedMemory();
        String diskUsed = siteEntity.getUsedDisk();

        if(!StringUtil.isInteger(cpu, cpuUsed)) {
            return false;
        }

        if(!StringUtil.isNumeric(memory, disk, memoryUsed, diskUsed)) {
            return false;
        }
        return true;
    }

    /**
     *
     * Format data.<br>
     *
     * @param siteEntity
     * @since  NFVO 0.5
     */
    public static void dataFramat(SitesEntity siteEntity) {
        siteEntity.setTotalCPU(StringUtil.numFormat(siteEntity.getTotalCPU()));
        siteEntity.setTotalMemory(StringUtil.numFormat(siteEntity.getTotalMemory()));
        siteEntity.setTotalDisk(StringUtil.numFormat(siteEntity.getTotalDisk()));
        siteEntity.setUsedCPU(StringUtil.numFormat(siteEntity.getUsedCPU()));
        siteEntity.setUsedMemory(StringUtil.numFormat(siteEntity.getUsedMemory()));
        siteEntity.setUsedDisk(StringUtil.numFormat(siteEntity.getUsedDisk()));

    }

}
