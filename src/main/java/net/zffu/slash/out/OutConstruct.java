package net.zffu.slash.out;

import net.zffu.slash.SlashMapper;

import java.io.File;

@FunctionalInterface
public interface OutConstruct<K> {

    K construct(SlashMapper mapper, File... outFiles);

}
