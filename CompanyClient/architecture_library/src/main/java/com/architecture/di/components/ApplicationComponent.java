/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.architecture.di.components;

import android.content.Context;

import com.architecture.application.AndroidApplication;
import com.architecture.di.modules.ApplicationModule;
import com.tamic.novate.Novate;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 * 可以在使用了依赖此Component的Component所注入的类中，通过获取ApplicationComponent对象，调用context方法获取ApplicationContext
 * 同时我们可以声明其他方法用于提供全局对象（需在ApplicationModule中提供依赖），给依赖此Component的Component
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AndroidApplication application);

    //Exposed to sub-graphs.
    Context context();

    // 若此处没有此方法返回Builder，那么无法提供依赖给使用此Component的module
    Novate.Builder getBuilder();
}
