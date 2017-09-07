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

package org.onap.vfc.nfvo.resmanagement.service.mapper;

import java.util.List;

import org.onap.vfc.nfvo.resmanagement.service.entity.VimEntity;

/**
 * Vim info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 24, 2016
 */
public interface VimMapper {

    /**
     * It is used to get Vim info.<br/>
     *
     * @param id
     * @return The get result
     * @since VFC 1.0
     */
    VimEntity getVim(String id);

    /**
     * It is used to get Vims info.<br/>
     *
     * @return The get result
     * @since VFC 1.0
     */
    List<VimEntity> getVims();

    /**
     * It is used to delete Vim info.<br/>
     *
     * @param id
     * @return The delete result
     * @since VFC 1.0
     */
    int deleteVim(String id);

    /**
     * It is used to add Vim info.<br/>
     *
     * @param vimEntity
     * @return The add result
     * @since VFC 1.0
     */
    int addVim(VimEntity vimEntity);

}
