package net.zffu.slash.source;

import net.zffu.slash.MappedClass;
import net.zffu.slash.MappingKey;

import java.util.HashMap;

/**
 * A provider of data for an {@link net.zffu.slash.SlashMapper}.
 * @since 1.0.0
 */
public interface SlashSourceProvider {

    /**
     * Constructs the source HashMap.
     * @return the map.
     */
    HashMap<MappingKey, MappedClass> provideData();

}
