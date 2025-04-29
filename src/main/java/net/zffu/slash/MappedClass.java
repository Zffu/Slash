package net.zffu.slash;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that is mapped inside a {@link SlashMapper}.
 * @since 1.0.0
 */
public class MappedClass {

    public final MappingKey obfuscatedClassName;
    public final MappingKey className;

    public final HashMap<String, String> fields;
    public final HashMap<String, MappedMethod> methods;

    public MappedClass(MappingKey obfuscatedClassName, MappingKey className, HashMap<String, String> fields, HashMap<String, MappedMethod> methods) {
        this.obfuscatedClassName = obfuscatedClassName;
        this.className = className;
        this.fields = fields;
        this.methods = methods;
    }

    public String getObfuscatedField(String fieldName) {
        return this.fields.get(fieldName);
    }

    public String getUnobfuscatedField(String obfuscatedFieldName) {
        for(Map.Entry<String, String> entry : this.fields.entrySet()) {
            if(entry.getValue().equals(obfuscatedFieldName)) return entry.getKey();
        }

        return obfuscatedFieldName;
    }

    public MappedMethod getObfuscatedMethod(String methodName) {
        return this.methods.get(methodName);
    }

    public MappedMethod getUnobfuscatedMethod(String obfuscatedMethodName) {
        for(Map.Entry<String, MappedMethod> entry : this.methods.entrySet()) {
            if(entry.getValue().obfuscatedName.equals(obfuscatedMethodName)) return entry.getValue();
        }

        return null;
    }

}
