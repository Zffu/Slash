package net.zffu.slash.signature;

import net.zffu.slash.utils.Comparable;

import java.util.ArrayList;
import java.util.List;

/**
 * The signature of a method mapped inside a {@link net.zffu.slash.SlashMapper}.
 * @since 1.0.0
 */
public class Signature implements Comparable<Signature> {

    public final List<SignatureElement> elements;
    public final SignatureElement returnSignature;

    public Signature(List<SignatureElement> elements, SignatureElement returnSignature) {
        this.elements = elements;
        this.returnSignature = returnSignature;
    }

    @Override
    public boolean isEquals(Signature signature) {
        if(!this.returnSignature.isEquals(signature.returnSignature) || this.elements.size() != signature.elements.size()) return false;

        for(int i = 0; i < this.elements.size(); ++i) {
            if(!signature.elements.get(i).isEquals(this.elements.get(i))) return false;
        }

        return true;
    }

    /**
     * Creates a {@link Signature} from an obfuscated format string.
     * @param obfuscated the obfuscated format string.
     * @return
     */
    public static Signature fromObfuscated(String obfuscated) {
        List<SignatureElement> elems = new ArrayList<>();

        String[] signatureSplit = obfuscated.split("\\)");

        signatureSplit[0] = signatureSplit[0].substring(1); // Removes the "(" from the first part

        for(int i = 0; i < signatureSplit[0].length(); ++i) {
            SignatureElement element = SignatureElement.fromObfuscated(signatureSplit[0].substring(i));

            i += element.getObfuscatedLength();
            elems.add(element);
        }

        return new Signature(elems, SignatureElement.fromObfuscated(signatureSplit[1]));
    }


    public static Signature fromClassic(String classic) {
        List<SignatureElement> elems = new ArrayList<>();

        String[] signatureSplit = classic.split("\\)");

        signatureSplit[0] = signatureSplit[0].substring(1); // Removes the "(" from the first part

        for(int i = 0; i < signatureSplit[0].length(); ++i) {
            SignatureElement element = SignatureElement.fromClassic(signatureSplit[0].substring(i));

            i += element.getClassicLength();
            elems.add(element);
        }

        return new Signature(elems, SignatureElement.fromClassic(signatureSplit[1]));
    }

}
