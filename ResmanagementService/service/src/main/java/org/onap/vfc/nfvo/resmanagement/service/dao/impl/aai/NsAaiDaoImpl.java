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

package org.onap.vfc.nfvo.resmanagement.service.dao.impl.aai;

import static org.onap.vfc.nfvo.resmanagement.common.constant.Constant.VFC_CUSTOMER_ID;
import static org.onap.vfc.nfvo.resmanagement.common.constant.Constant.VFC_SERVICE_SUBSCRIPTION_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulResponse;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.NsDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.NsEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NsAaiDaoImpl implements NsDao {

    private static int VFC_SUCCESS_RESPONSE = 1;

    private static int VFC_ERROR_RESPONSE = -1;

    @Override
    public NsEntity getNs(String id) {

        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

        RestfulResponse response =
                RestfulUtil.getRestfulResponse("https://192.168.17.24:8443/aai/v11/business/customers/customer/"
                        + VFC_CUSTOMER_ID + "/service-subscriptions/service-subscription/" + VFC_SERVICE_SUBSCRIPTION_ID
                        + "/service-instances/service-instance/" + id, restfulParametes, "get");

        JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
        NsEntity nsEntity = NsEntity.toEntityFromAai(jsonObject);

        return nsEntity;
    }

    @Override
    public List<NsEntity> getAllNs(Map<String, Object> condition) {
        List<NsEntity> nsEntities = new ArrayList<>();
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

        RestfulResponse response =
                RestfulUtil.getRestfulResponse("https://192.168.17.24:8443/aai/v11/business/customers/customer/"
                        + VFC_CUSTOMER_ID + "/service-subscriptions/service-subscription/" + VFC_SERVICE_SUBSCRIPTION_ID
                        + "/service-instances", restfulParametes, "get");

        JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
        JSONArray jsonArray = jsonObject.getJSONArray("service-instance");

        jsonArray.forEach(svcInstance -> nsEntities.add(NsEntity.toEntityFromAai((JSONObject)svcInstance)));
        return nsEntities;
    }

    @Override
    public int addNs(NsEntity nsEntity) {

        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        restfulParametes.setRawData(nsEntity.toStringForAai());

        RestfulResponse response =
                RestfulUtil.getRestfulResponse("https://192.168.17.24:8443/aai/v11/business/customers/customer/"
                        + VFC_CUSTOMER_ID + "/service-subscriptions/service-subscription/" + VFC_SERVICE_SUBSCRIPTION_ID
                        + "/service-instances/service-instance/" + nsEntity.getId(), restfulParametes, "put");

        return response != null ? VFC_SUCCESS_RESPONSE : VFC_ERROR_RESPONSE;
    }

    @Override
    public int updateNs(NsEntity nsEntity) {
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        restfulParametes.put("resource-version", nsEntity.getResourceVersion());

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                "https://192.168.17.24:8443/aai/v11/business/customers/customer/" + VFC_CUSTOMER_ID
                        + "/service-subscriptions/service-subscription/" + VFC_SERVICE_SUBSCRIPTION_ID
                        + "/service-instances/service-instance/" + nsEntity.getResourceVersion(),
                restfulParametes, "put");
        return response != null ? VFC_SUCCESS_RESPONSE : VFC_ERROR_RESPONSE;
    }

    @Override
    public int deleteNsById(String id) {

        NsEntity nsEntity = getNs(id);

        if(nsEntity != null) {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
            restfulParametes.put("resource-version", nsEntity.getResourceVersion());

            RestfulResponse response = RestfulUtil
                    .getRestfulResponse(
                            "https://192.168.17.24:8443/aai/v11/business/customers/customer/" + VFC_CUSTOMER_ID
                                    + "/service-subscriptions/service-subscription/" + VFC_SERVICE_SUBSCRIPTION_ID
                                    + "/service-instances/service-instance/" + nsEntity.getId(),
                            restfulParametes, "delete");
            return response != null ? VFC_SUCCESS_RESPONSE : VFC_ERROR_RESPONSE;
        }
        return VFC_ERROR_RESPONSE;
    }
}
