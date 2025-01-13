/*
 * Copyright 2020-2023 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ifinalframework.web.annotation.bind;

import org.ifinalframework.core.IQuery;
import org.ifinalframework.core.IView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定查询的Query对象.
 *
 * @author iimik
 * @version 1.5.0
 * @since 1.5.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestQuery {
    /**
     * 返回查询的Query对象
     */
    Class<? extends IQuery> query() default IQuery.class;

    /**
     * 返回资源路径标识符
     */
    String resource() default "resource";

    /**
     * 返回查询视图
     */
    Class<?> view() default IView.class;
}
