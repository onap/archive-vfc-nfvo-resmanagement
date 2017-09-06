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

package org.openo.nfvo.resmanagement.test;

import java.io.File;

import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.openo.nfvo.resmanagement.util.MyTestManager;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Sep 20, 2016
 */
public class ITHostRoaSuccess extends MyTestManager {

    private static final String GET_PATH =
            "src/integration-test/resources/testcase/hostroa/queryhostsbyidsuccess1.json";

    private static final String POST_PATH = "src/integration-test/resources/testcase/hostroa/createhostssuccess1.json";

    private static final String PUT_PATH = "src/integration-test/resources/testcase/hostroa/modhostssuccess1.json";

    private static final String DEL_PATH = "src/integration-test/resources/testcase/hostroa/deletehostssuccess1.json";

    @Test
    public void testOperateSuccess() throws ServiceException {
        execTestCase(new File(POST_PATH));
        execTestCase(new File(GET_PATH));
        execTestCase(new File(PUT_PATH));
        execTestCase(new File(DEL_PATH));
    }
}
