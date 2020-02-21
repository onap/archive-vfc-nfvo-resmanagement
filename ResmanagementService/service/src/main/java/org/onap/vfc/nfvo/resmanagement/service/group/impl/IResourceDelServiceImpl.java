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

import org.onap.vfc.nfvo.resmanagement.common.ResourceUtil;
import org.onap.vfc.nfvo.resmanagement.common.constant.ParamConstant;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.inf.InterfaceResManagement;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.inf.Vim;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * iResource delete service implementation.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     VFC 1.0  Sep 10, 2016
 */
public class IResourceDelServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IResourceDelServiceImpl.class);

    /**
     *
     * Delete iResource.<br>
     *
     * @param vimId
     * @param iResMap
     * @param vim
     * @return
     * @throws ServiceException
     * @since  VFC 1.0
     */
    public int deleteIRes(String vimId, Map<String, InterfaceResManagement> iResMap, Vim vim) throws ServiceException {
        Map<String, Object> map = new HashMap<>(10);
        map.put(ParamConstant.PARAM_VIMID, vimId);

        checkIResDelResult(vimId, iResMap);
        return vim.delete(vimId);
    }

    private void checkIResDelResult(String vimId, Map<String, InterfaceResManagement> iResMap) throws ServiceException {
        for(String keyName : iResMap.keySet()) {
            if(iResMap.get(keyName).deleteResByVimId(vimId) < 0) {
                LOGGER.error("function=checkIResDelResult; msg=delete {} failed,", keyName);
                throw new ServiceException(
                        ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.group.resoperate.add.res.delete.fail")
                                + keyName);
            }
            LOGGER.warn("function=checkIResDelResult; msg=delete {} success", keyName);
        }

    }

}
