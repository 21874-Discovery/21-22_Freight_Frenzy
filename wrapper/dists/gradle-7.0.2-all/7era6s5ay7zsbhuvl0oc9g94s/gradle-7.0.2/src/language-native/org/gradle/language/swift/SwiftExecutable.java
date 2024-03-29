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

package org.gradle.language.swift;

import org.gradle.api.file.RegularFile;
import org.gradle.api.provider.Provider;
import org.gradle.language.ComponentWithOutputs;
import org.gradle.language.nativeplatform.ComponentWithExecutable;
import org.gradle.language.nativeplatform.ComponentWithInstallation;

/**
 * An executable built from Swift source.
 *
 * @since 4.2
 */
public interface SwiftExecutable extends SwiftBinary, ComponentWithExecutable, ComponentWithInstallation, ComponentWithOutputs {
    /**
     * Returns the executable file to use with a debugger for this binary.
     *
     * @since 4.5
     */
    Provider<RegularFile> getDebuggerExecutableFile();
}
