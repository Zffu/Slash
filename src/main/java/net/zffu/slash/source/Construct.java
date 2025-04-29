package net.zffu.slash.source;

import java.io.File;

@FunctionalInterface
public interface Construct<K> {

    K construct(File[] files);

}
