/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.rest.adapter.http.toolbox;

import android.content.Context;
import com.rest.adapter.http.NetworkResponse;
import com.rest.adapter.http.Request;
import java.io.UnsupportedEncodingException;

/**
 * A canned request for retrieving the response body at a given URL as a String.
 */
public class StringRequest extends Request<String> {

    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     */
    public StringRequest(Context context, int method, String url) {
        super(context, method, url);
    }

    /**
     * Creates a new GET request.
     *
     * @param url URL to fetch the string at
     */
    public StringRequest(Context context, String url) {
        this(context, Method.GET, url);
    }

    @Override
    protected String parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return parsed;
    }
}
