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

package org.gradle.language.nativeplatform;

import org.gradle.api.artifacts.Configuration;
import org.gradle.api.provider.Provider;

/**
 * Represents a native component whose link time file and dependencies are published for consumption by some other project.
 *
 * @since 4.5
 */
public interface ComponentWithLinkUsage extends ComponentWithNativeRuntime {
    /**
     * Returns the outgoing link elements of this component.
     */
    Provider<Configuration> getLinkElements();
}
