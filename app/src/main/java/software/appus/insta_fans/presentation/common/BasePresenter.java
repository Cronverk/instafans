/*
 * Copyright 2016, The Android Open Source Project
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

package software.appus.insta_fans.presentation.common;

import software.appus.insta_fans.domain.common.UseCaseHandler;

public abstract class BasePresenter<T extends BaseView> {
    protected final T mView;
    protected final UseCaseHandler mUseCaseHandler;

    protected BasePresenter(T view, UseCaseHandler useCaseHandler) {
        mView = view;
        mUseCaseHandler = useCaseHandler;
    }
}