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

package org.openo.nfvo.resmanagement.util;

import org.openo.sdno.testframework.checker.DefaultChecker;
import org.openo.sdno.testframework.http.model.HttpResponse;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version VFC 1.0 Sep 21, 2016
 */
public class MyChecker extends DefaultChecker {

    private HttpResponse expectedResponse;

    /**
     * Constructor<br>
     * <p>
     * </p>
     * 
     * @param expectedResponse
     * @since VFC 1.0
     */
    public MyChecker(HttpResponse expectedResponse) {
        super(expectedResponse);
        this.expectedResponse = expectedResponse;
    }

    /**
     * <br>
     * 
     * @param response
     * @return
     * @since VFC 1.0
     */
    @Override
    public boolean check(HttpResponse response) {
        return expectedResponse.equals(response);
    }

}
