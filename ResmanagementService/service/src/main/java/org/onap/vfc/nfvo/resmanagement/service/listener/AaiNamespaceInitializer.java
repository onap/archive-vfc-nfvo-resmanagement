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

package org.onap.vfc.nfvo.resmanagement.service.listener;

import static org.onap.vfc.nfvo.resmanagement.common.constant.Constant.VFC_CUSTOMER_ID;
import static org.onap.vfc.nfvo.resmanagement.common.constant.Constant.VFC_SERVICE_SUBSCRIPTION_ID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulResponse;

public class AaiNamespaceInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        createCustomer();
        createServiceSubscription();
    }

    private int createCustomer() {
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        restfulParametes.setRawData("{\"global-customer-id\": \"" + VFC_CUSTOMER_ID + "\"," + "\"subscriber-name\": \""
                + VFC_CUSTOMER_ID + "\"," + "\"subscriber-type\": \"" + VFC_CUSTOMER_ID + "\"}");

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                "https://192.168.17.24:8443/aai/v11/business/customers/customer/" + VFC_CUSTOMER_ID, restfulParametes,
                "put");
        return response.getStatus();
    }

    private int createServiceSubscription() {
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        restfulParametes.setRawData("{\"service-type\": \"" + VFC_SERVICE_SUBSCRIPTION_ID + "\"}");

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                "https://192.168.17.24:8443/aai/v11/business/customers/customer/" + VFC_CUSTOMER_ID
                        + "/service-subscriptions/service-subscription/" + VFC_SERVICE_SUBSCRIPTION_ID,
                restfulParametes, "put");
        return response.getStatus();
    }

}
