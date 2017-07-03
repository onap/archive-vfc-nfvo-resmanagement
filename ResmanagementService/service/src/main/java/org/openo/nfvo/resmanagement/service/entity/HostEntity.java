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
 * Host entity class.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 31, 2016
 */
public class HostEntity {

    private String id;

    private String name;

    private String cpu;

    private String memory;

    private String disk;

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
        this.id = id;
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
     * @return Returns the cpu.
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * @param cpu The cpu to set.
     */
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    /**
     * @return Returns the memory.
     */
    public String getMemory() {
        return memory;
    }

    /**
     * @param memory The memory to set.
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }

    /**
     * @return Returns the disk.
     */
    public String getDisk() {
        return disk;
    }

    /**
     * @param disk The disk to set.
     */
    public void setDisk(String disk) {
        this.disk = disk;
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
     * To entity.<br>
     *
     * @param jsonObject
     * @return
     * @since  NFVO 0.5
     */
    public static HostEntity toEntity(JSONObject jsonObject) {
        HostEntity hostEntity = new HostEntity();
        hostEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "id"));
        hostEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        hostEntity.setCpu(JsonUtil.getJsonFieldStr(jsonObject, "cpu"));
        hostEntity.setMemory(JsonUtil.getJsonFieldStr(jsonObject, "memory"));
        hostEntity.setDisk(JsonUtil.getJsonFieldStr(jsonObject, "disk"));
        hostEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        hostEntity.setVimName(JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        return hostEntity;
    }

    @Override
    public String toString() {
        JSONObject hostResJson = new JSONObject();
        hostResJson.put("id", StringUtils.trimToEmpty(this.getId()));
        hostResJson.put("name", StringUtils.trimToEmpty(this.getName()));
        hostResJson.put("cpu", StringUtils.trimToEmpty(this.getCpu()));
        hostResJson.put("memory", StringUtils.trimToEmpty(this.getMemory()));
        hostResJson.put("disk", StringUtils.trimToEmpty(this.getDisk()));
        hostResJson.put("vimId", StringUtils.trimToEmpty(this.getVimId()));
        hostResJson.put("vimName", StringUtils.trimToEmpty(this.getVimName()));
        return hostResJson.toString();
    }

}
