/*
 * Copyright (c) 2015 Ha Duy Trung
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

package com.siasatanzania.siasatanzania.data;

import android.support.annotation.Nullable;

/**
 * Callback interface for requests
 * @param <T> response type
 */
public interface ResponseListener<T> {
    /**
     * Fired when request is successful
     * @param response result
     */
    void onResponse(@Nullable T response);

    /**
     * Fired when request is failed
     * @param errorMessage error message or null
     */
    void onError(String errorMessage);
}
