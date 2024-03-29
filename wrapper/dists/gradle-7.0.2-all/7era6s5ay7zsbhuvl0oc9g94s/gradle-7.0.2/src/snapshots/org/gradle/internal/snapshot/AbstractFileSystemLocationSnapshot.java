/*
 * Copyright 2018 the original author or authors.
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

package org.gradle.internal.snapshot;

import org.gradle.internal.file.FileMetadata.AccessType;

import java.util.Optional;

public abstract class AbstractFileSystemLocationSnapshot implements FileSystemLocationSnapshot {
    private final String absolutePath;
    private final String name;
    private final AccessType accessType;

    public AbstractFileSystemLocationSnapshot(String absolutePath, String name, AccessType accessType) {
        this.absolutePath = absolutePath;
        this.name = name;
        this.accessType = accessType;
    }

    protected static MissingFileSnapshot missingSnapshotForAbsolutePath(String filePath) {
        return new MissingFileSnapshot(filePath, AccessType.DIRECT);
    }

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AccessType getAccessType() {
        return accessType;
    }

    public String getPathToParent() {
        return getName();
    }

    @Override
    public FileSystemLocationSnapshot store(VfsRelativePath targetPath, CaseSensitivity caseSensitivity, MetadataSnapshot snapshot, SnapshotHierarchy.NodeDiffListener diffListener) {
        return this;
    }

    @Override
    public void accept(SnapshotHierarchy.SnapshotVisitor snapshotVisitor) {
        snapshotVisitor.visitSnapshotRoot(this);
    }

    @Override
    public boolean hasDescendants() {
        return true;
    }

    @Override
    public FileSystemNode asFileSystemNode() {
        return this;
    }

    @Override
    public Optional<MetadataSnapshot> getSnapshot() {
        return Optional.of(this);
    }

    @Override
    public Optional<MetadataSnapshot> getSnapshot(VfsRelativePath relativePath, CaseSensitivity caseSensitivity) {
        return getChildSnapshot(relativePath, caseSensitivity);
    }

    protected Optional<MetadataSnapshot> getChildSnapshot(VfsRelativePath relativePath, CaseSensitivity caseSensitivity) {
        return Optional.of(missingSnapshotForAbsolutePath(relativePath.getAbsolutePath()));
    }

    @Override
    public ReadOnlyFileSystemNode getNode(VfsRelativePath relativePath, CaseSensitivity caseSensitivity) {
        return getChildNode(relativePath, caseSensitivity);
    }

    protected ReadOnlyFileSystemNode getChildNode(VfsRelativePath relativePath, CaseSensitivity caseSensitivity) {
        return missingSnapshotForAbsolutePath(relativePath.getAbsolutePath());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractFileSystemLocationSnapshot that = (AbstractFileSystemLocationSnapshot) o;

        if (accessType != that.accessType) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        if (!absolutePath.equals(that.absolutePath)) {
            return false;
        }
        return getHash().equals(that.getHash());
    }

    @Override
    public int hashCode() {
        int result = absolutePath.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + accessType.hashCode();
        result = 31 * result + getHash().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
