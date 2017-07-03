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

package org.openo.nfvo.resmanagement.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * String Utility Class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public final class StringUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    private StringUtil() {
    }

    /**
     *
     * Check whether thestring is valid.<br>
     *
     * @param str
     * @return
     * @since  NFVO 0.5
     */
    public static boolean isValidString(String str) {
        if(null == str || str.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     *
     * Check whether the value is larger than zero.<br>
     *
     * @param strs
     * @return
     * @since  NFVO 0.5
     */
    public static boolean isAnyLargeThanZero(String... strs) {
        for(String str : strs) {
            if(!StringUtils.isEmpty(str) && Float.parseFloat(str) > 0) {
                LOGGER.info("isAnyLargeThanZero : {} is > 0", str);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Check whether the value is Integer.<br>
     *
     * @param strs
     * @return
     * @since  NFVO 0.5
     */
    public static boolean isInteger(String... strs) {
        try {
            for(String str : strs) {
                if(!StringUtils.isEmpty(str)) {
                    int value = Integer.parseInt(str);
                    if(value < 0) {
                        return false;
                    }
                }
            }
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * Check whether the input is Numeric.<br>
     *
     * @param strs
     * @return
     * @since  NFVO 0.5
     */
    public static boolean isNumeric(String... strs) {
        try {
            for(String str : strs) {
                if(!StringUtils.isEmpty(str)) {
                    float value = Float.parseFloat(str);
                    if(value < 0) {
                        return false;
                    }
                }
            }
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * Compare zero by float.<br>
     *
     * @param tatol
     * @param used
     * @param drTotal
     * @return
     * @since  NFVO 0.5
     */
    public static boolean compareZeroByFloat(String tatol, String used, String drTotal) {
        Float ftotal = (float)0;
        Float fused = (float)0;
        Float fdrTotal = (float)0;
        if(!StringUtils.isEmpty(tatol)) {
            ftotal = new Float(tatol);
        }
        if(!StringUtils.isEmpty(used)) {
            fused = new Float(used);
        }
        if(!StringUtils.isEmpty(drTotal)) {
            fdrTotal = new Float(drTotal);
        }
        if(ftotal < fused + fdrTotal) {
            return false;
        }

        return true;
    }

    /**
     *
     * Compare zero by integer.<br>
     *
     * @param tatol
     * @param used
     * @param drTotal
     * @return
     * @since  NFVO 0.5
     */
    public static boolean compareZeroByInteger(String tatol, String used, String drTotal) {
        Integer ftotal = (int)0;
        Integer fused = (int)0;
        Integer fdrTotal = (int)0;
        if(!StringUtils.isEmpty(tatol)) {
            ftotal = Integer.valueOf(tatol);
        }
        if(!StringUtils.isEmpty(used)) {
            fused = Integer.valueOf(used);
        }
        if(!StringUtils.isEmpty(drTotal)) {
            fdrTotal = Integer.valueOf(drTotal);
        }
        if(ftotal < fused + fdrTotal) {
            return false;
        }
        return true;
    }

    /**
     *
     * Number format.<br>
     *
     * @param data
     * @return
     * @since  NFVO 0.5
     */
    public static String numFormat(String data) {
        if(null != data && !("".equals(data))) {
            BigDecimal var = new BigDecimal(data);
            DecimalFormat formatWithoutFraction = new DecimalFormat("############");
            DecimalFormat formatWithFraction = new DecimalFormat("############.############");
            if(new BigDecimal(var.intValue()).compareTo(var) == 0) {
                return formatWithoutFraction.format(var);
            }
            return formatWithFraction.format(var);
        }
        return null;
    }

    /**
     *
     * <br>
     *
     * @param inputStr
     * @return
     * @since  NFVO 0.5
     */
    public static boolean checkXss(String inputStr) {
        return inputStr.matches("[A-Za-z0-9_.']+");
    }

}
