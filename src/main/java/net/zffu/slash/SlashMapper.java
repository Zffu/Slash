package net.zffu.slash;

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

}
