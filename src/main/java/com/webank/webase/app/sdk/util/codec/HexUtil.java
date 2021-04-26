/**
 * Copyright 2014-2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.webase.app.sdk.util.codec;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;

/**
 * HexUtil.
 */
public class HexUtil {

    /**
     * 以16进制打印字节数组
     *
     * @param bytes  bytes to encode.
     * @return  return hex in lower case.
     */
    public static String byteToHexLower(byte[] bytes) {
        return StringUtils.lowerCase(Hex.toHexString(bytes));
    }

    /**
     * 以16进制打印字节数组
     *
     * @param bytes bytes to encode.
     * @return  return upper case.
     */
    public static String byteToHexUpper(byte[] bytes) {
        return StringUtils.upperCase(Hex.toHexString(bytes));
    }

    /**
     *
     * @param hexString  hex string to decode.
     * @return bytes.
     */
    public static byte[] hexToByteArray(String hexString){
        return Hex.decode(hexString);
    }

}