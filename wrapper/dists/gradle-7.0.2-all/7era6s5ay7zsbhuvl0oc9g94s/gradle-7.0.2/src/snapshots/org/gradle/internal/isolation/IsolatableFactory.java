/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.internal.isolation;

import javax.annotation.Nullable;

public interface IsolatableFactory {
    /**
     * Creates an {@link Isolatable} that reflects the <em>current</em> state of the given value. Any changes made to the value will not be visible to the {@link Isolatable} and vice versa.
     */
    <T> Isolatable<T> isolate(@Nullable T value);
}
