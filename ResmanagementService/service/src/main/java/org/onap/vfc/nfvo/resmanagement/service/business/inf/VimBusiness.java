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

package org.onap.vfc.nfvo.resmanagement.service.business.inf;

import java.util.List;

import org.onap.vfc.nfvo.resmanagement.service.entity.VimEntity;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

/**
 * Vim info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 24, 2016
 */
public interface VimBusiness {

    /**
     * It is used to get Vim info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    VimEntity getVim(String id);

    /**
     * It is used to get Vim info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    List<VimEntity> getVims();

    /**
     * It is used to delete Vim info.<br/>
     *
     * @param id
     * @return the delete result
     * @throws ServiceException When delete failed.
     * @since VFC 1.0
     */
    int deleteVim(String id) throws ServiceException;

    /**
     * It is used to add Vim info.<br/>
     *
     * @param id
     * @return the add result
     * @throws ServiceException When add failed.
     * @since VFC 1.0
     */
    int addVim(String id) throws ServiceException;

}
