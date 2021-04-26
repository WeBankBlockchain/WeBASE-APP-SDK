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

import java.util.Base64;

/**
 * Base64Util.
 */
public class Base64Util {

    /**
     *
     * @param bytes  encode bytes into url safe BASE64.
     * @return  string of BASE64.
     */
    public static String encode(byte[] bytes){
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    /**
     *
     * @param encoded decode bytes into url safe BASE64.
     * @return  bytes.
     */
    public static byte[] decode(String encoded){
        return Base64.getUrlDecoder().decode(encoded);
    }
}