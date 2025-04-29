package net.zffu.slash;

import net.zffu.slash.signature.Signature;

/**
 * A method mapped inside an {@link SlashMapper}.
 * @since 1.0.0
 */
public class MappedMethod {

    public final Signature signature;
    public final String obfuscatedName;
    public final String name;

    public MappedMethod(Signature signature, String obfuscatedName, String name) {
        this.signature = signature;
        this.obfuscatedName = obfuscatedName;
        this.name = name;
    }

}
