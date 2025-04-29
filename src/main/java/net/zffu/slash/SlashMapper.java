package net.zffu.slash;

import net.zffu.slash.out.SlashOutProvider;
import net.zffu.slash.out.SlashWriteProvider;
import net.zffu.slash.source.SlashProvider;
import net.zffu.slash.source.SlashSourceProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * The root of the Slash mapping system.
 * @since 1.0.0
 */
public class SlashMapper {

    public final HashMap<MappingKey, MappedClass> classes;

    public SlashMapper(HashMap<MappingKey, MappedClass> classes) {
        this.classes = classes;
    }

    public MappedClass getObfuscatedClass(MappingKey className) {
        return this.classes.get(className);
    }

    public MappedClass getUnobfuscatedClass(MappingKey obfuscatedClassName) {
        for(Map.Entry<MappingKey, MappedClass> entry : this.classes.entrySet()) {
            if(entry.getValue().obfuscatedClassName.isEquals(obfuscatedClassName)) return entry.getValue();
        }

        return null;
    }

    /**
     * Writes the content of the {@link SlashMapper} into the provided {@link SlashOutProvider}.
     * @param provider the provider.
     * @param files the output files.
     */
    public void writeOut(SlashOutProvider provider, File... files) {
        SlashWriteProvider p = provider.provider.construct(this, files);

        if(p == null) return;
        p.writeData();
    }

    /**
     * Creates a {@link SlashMapper} based on the provided {@link SlashProvider}.
     * @param provider the provider.
     * @param files the input files.
     * @return the {@link SlashMapper} instance.
     */
    public static SlashMapper wrap(SlashProvider provider, File... files) {
        SlashSourceProvider slashSourceProvider = provider.provider.construct(files);

        if(slashSourceProvider == null) return null;
        return new SlashMapper(slashSourceProvider.provideData());
    }

}
