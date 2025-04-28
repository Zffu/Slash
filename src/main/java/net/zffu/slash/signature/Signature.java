package net.zffu.slash.signature;

import java.util.List;

/**
 * The signature of a method mapped inside a {@link net.zffu.slash.SlashMapper}.
 * @since 1.0.0
 */
public class Signature {

    public final List<SignatureElement> elements;

    public Signature(List<SignatureElement> elements) {
        this.elements = elements;
    }

}
