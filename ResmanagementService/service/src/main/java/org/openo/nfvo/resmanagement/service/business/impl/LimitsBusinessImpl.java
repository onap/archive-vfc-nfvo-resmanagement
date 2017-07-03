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

package org.openo.nfvo.resmanagement.service.business.impl;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.VimUtil;
import org.openo.nfvo.resmanagement.common.constant.ParamConstant;
import org.openo.nfvo.resmanagement.common.constant.UrlConstant;
import org.openo.nfvo.resmanagement.common.util.RestfulUtil;
import org.openo.nfvo.resmanagement.service.business.inf.LimitsBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Sep 10, 2016
 */
public class LimitsBusinessImpl implements LimitsBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(LimitsBusinessImpl.class);

    /**
     * <br>
     *
     * @param vimId
     * @param tenantId
     * @return
     * @since NFVO 0.5
     */
    private JSONObject getResponse(String vimId, String tenantId) {
        LOGGER.info("function=getResponse; vimId={}, tenantId={}", vimId, tenantId);
        String url = String.format(UrlConstant.GET_LIMITS_URL, vimId, tenantId);
        JSONObject result = RestfulUtil.getResponseObj(url, ParamConstant.PARAM_GET);
        LOGGER.warn("function=getResponse; result={}", result);
        if(null == result) {
            JSONObject obj = new JSONObject();
            obj.put("msg", "getLimits fail!");
            return obj;
        }
        return result;
    }

    @Override
    public JSONObject getLimits(String vimId) throws ServiceException {
        JSONObject vimInfo = VimUtil.getVimById(vimId);
        LOGGER.info("GetLimits vimInfo: {}", vimInfo);
        String vimName = vimInfo.getString("name");
        String tenant = vimInfo.getString("tenant");
        String tenantId = VimUtil.getTenantIdByName(tenant, vimId);

        JSONObject limits = getResponse(vimId, tenantId);

        String totalCPU = String.valueOf(limits.get("maxTotalCores"));
        String totalMemory = String.valueOf(limits.get("maxTotalRAMSize"));
        String totalDisk = String.valueOf(limits.get("maxTotalVolumeGigabytes"));
        String usedCPU = String.valueOf(limits.get("totalCoresUsed"));
        String usedMemory = String.valueOf(limits.get("totalRAMUsed"));
        String usedDisk = String.valueOf(limits.get("totalGigabytesUsed"));

        JSONObject result = new JSONObject();
        result.put("vimId", vimId);
        result.put("vimName", vimName);
        result.put("totalCPU", totalCPU);
        result.put("totalMemory", totalMemory);
        result.put("totalDisk", totalDisk);
        result.put("usedCPU", usedCPU);
        result.put("usedMemory", usedMemory);
        result.put("usedDisk", usedDisk);
        LOGGER.info("getLimits result:{}", result);
        return result;
    }

}
