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

package org.onap.vfc.nfvo.resmanagement.service.group.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onap.vfc.nfvo.resmanagement.common.constant.ParamConstant;
import org.onap.vfc.nfvo.resmanagement.common.constant.UrlConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.inf.InterfaceResManagement;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * iResource update service implementation class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Sep 10, 2016
 */
public class IResourceUpdateServiceImpl {

    private static final Logger LOGGER = LogManager.getLogger(IResourceUpdateServiceImpl.class);

    /**
     * Update iResource.<br>
     *
     * @param vimId
     * @param restParametes
     * @param iResMap
     * @param sites
     * @throws ServiceException
     * @since VFC 1.0
     */
    @Transactional(rollbackFor = ServiceException.class)
    public void updateIRes(String vimId, RestfulParametes restParametes, Map<String, InterfaceResManagement> iResMap)
            throws ServiceException {

        updateIResByName(iResMap, createResUrlMap(), restParametes, vimId);
    }

    private void updateIResByName(Map<String, InterfaceResManagement> iResMap, HashMap<String, String> updateUrlMap,
            RestfulParametes restParametes, String vimId) throws ServiceException {
        for(String resName : updateUrlMap.keySet()) {
            if(ParamConstant.PARAM_HOST.equals(resName)) {
                updateHostResource(iResMap, restParametes, String.format(updateUrlMap.get(resName),
                        restParametes.get(ParamConstant.PARAM_VIMID), restParametes.get(ParamConstant.PARAM_TENANTID)),
                        resName);
            } else if(iResMap.get(resName).deleteResByVimId(vimId) >= 0) {
                String url = String.format(updateUrlMap.get(resName), restParametes.get(ParamConstant.PARAM_VIMID),
                        restParametes.get(ParamConstant.PARAM_TENANTID));
                JSONArray iResArray = RestfulUtil.getResponseRes(new RestfulParametes(), url, resName);
                LOGGER.warn("function=addIResources; iResArray={}", iResArray);
                for(Object object : iResArray) {
                    JSONObject iRes = JSONObject.fromObject(object);
                    int result = iResMap.get(resName).add(iRes);
                    LOGGER.warn("function=updateIResByName; msg=iRes name is [{}],result is [{}]", resName, result);

                }
            }
        }
    }

    private void updateHostResource(Map<String, InterfaceResManagement> iResMap, RestfulParametes restParametes,
            String url, String iResName) throws ServiceException {

        JSONArray hostResArray = RestfulUtil.getResponseRes(new RestfulParametes(), url, iResName);
        LOGGER.warn("function=updateHostResource; hostResArray={}", hostResArray);
        for(Object object : hostResArray) {
            JSONObject hostRes = JSONObject.fromObject(object);
            String hostZone = hostRes.getString("zone");
            if("internal".equals(hostZone)) {
                continue;
            }
            String hostName = hostRes.getString("name");
            String hostUrl = String.format(UrlConstant.GET_HOSTDETAIL_URL, restParametes.get(ParamConstant.PARAM_VIMID),
                    restParametes.get(ParamConstant.PARAM_TENANTID), hostName);

            String result = RestfulUtil.getResponseContent(hostUrl, new RestfulParametes(), ParamConstant.PARAM_GET);
            JSONObject hostObj = JSONObject.fromObject(result);
            JSONObject host = IResourceAddServiceImpl.hostDataParse(hostObj, hostName);
            int res = iResMap.get(ParamConstant.PARAM_HOST).update(host);
            LOGGER.warn("function=updateHostResource; result={}, res={}", result, res);
            if(res < 0) {
                LOGGER.error("function=updateHostResource; add into DB fail!");
            }

        }
    }

    private HashMap<String, String> createResUrlMap() {
        HashMap<String, String> updateUrlMap = new HashMap<String, String>(10);
        updateUrlMap.put(ParamConstant.PARAM_NETWORK, UrlConstant.GET_NETWORK_URL);
        updateUrlMap.put(ParamConstant.PARAM_HOST, UrlConstant.GET_HOST_URL);
        updateUrlMap.put(ParamConstant.PARAM_PORT, UrlConstant.GET_PORT_URL);
        return updateUrlMap;
    }
}
