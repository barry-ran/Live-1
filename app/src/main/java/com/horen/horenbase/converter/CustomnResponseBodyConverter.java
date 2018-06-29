/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.horen.horenbase.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.horen.horenbase.api.Constant;
import com.horen.horenbase.utils.AesEncryptionUtil;
import com.horen.horenbase.utils.UniCodeUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class CustomnResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomnResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        String response;
        if (UniCodeUtils.isJson(string)) {
            response = JsonFilter(string);
        } else {
            response = AesEncryptionUtil.decrypt(string, Constant.AES_PWD, Constant.AES_IV);
        }
        try {
            return adapter.fromJson(response);
        } finally {
            value.close();
        }
    }

    /**
     * 将JSON数据中的 /r/n 替换 为 /n
     */
    public String JsonFilter(String jsonstr) {
        jsonstr = jsonstr.replace("\\", "\\\\");//对斜线的转义
        jsonstr = jsonstr.replace("\n", "\\n");  //注意php中替换的时候只能用双引号"\n"
        jsonstr = jsonstr.replace("\r", "\\r");
        jsonstr = jsonstr.replace("\\r\\n", "");
        return jsonstr;
    }
}
