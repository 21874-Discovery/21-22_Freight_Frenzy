/*
 * Copyright 2020 the original author or authors.
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

package org.gradle.jvm.toolchain;

import org.gradle.internal.jvm.inspection.JvmVendor.KnownJvmVendor;
import org.gradle.jvm.toolchain.internal.DefaultJvmVendorSpec;

/**
 * Represents a filter for a vendor of a Java Virtual Machine implementation.
 *
 * @since 6.8
 */
public abstract class JvmVendorSpec {

    public static final JvmVendorSpec ADOPTOPENJDK = matching(KnownJvmVendor.ADOPTOPENJDK);
    public static final JvmVendorSpec AMAZON = matching(KnownJvmVendor.AMAZON);
    public static final JvmVendorSpec APPLE = matching(KnownJvmVendor.APPLE);
    public static final JvmVendorSpec AZUL = matching(KnownJvmVendor.AZUL);
    public static final JvmVendorSpec BELLSOFT = matching(KnownJvmVendor.BELLSOFT);
    public static final JvmVendorSpec HEWLETT_PACKARD = matching(KnownJvmVendor.HEWLETT_PACKARD);
    public static final JvmVendorSpec IBM = matching(KnownJvmVendor.IBM);
    public static final JvmVendorSpec ORACLE = matching(KnownJvmVendor.ORACLE);
    public static final JvmVendorSpec SAP = matching(KnownJvmVendor.SAP);

    /**
     * Returns a vendor spec that matches a VM by its vendor.
     * <p>
     * A VM is determined eligible if the system property <code>java.vendor</code> contains
     * the given match string. The comparison is done case-insensitive.
     * </p>
     * @param match the sequence to search for
     * @return a new filter object
     */
    public static JvmVendorSpec matching(String match) {
        return DefaultJvmVendorSpec.matching(match);
    }

    private static JvmVendorSpec matching(KnownJvmVendor vendor) {
        return DefaultJvmVendorSpec.of(vendor);
    }

}
