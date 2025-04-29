package net.zffu.slash.signature;

import net.zffu.slash.MappingKey;

/**
 * An element inside a {@link Signature}. Also represents a method's return signature.
 * @since 1.0.0
 * @see {@link Signature}
 */
public class SignatureElement {

    public final ElementType element;
    public final MappingKey className;
    public final int arrayDepth;

    public SignatureElement(ElementType element, MappingKey className, int arrayDepth) {
        this.element = element;
        this.className = className;
        this.arrayDepth = arrayDepth;
    }

    public SignatureElement(ElementType element, int arrayDepth) {
        this.element = element;
        this.arrayDepth = arrayDepth;
        this.className = null;
    }

    public SignatureElement(ElementType element) {
        this.element = element;
        this.className = null;
        this.arrayDepth = 0;
    }

    /**
     * Transforms the {@link SignatureElement} into the obfuscated format
     * @return the obfuscated string variant
     */
    public String toObfuscated() {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < this.arrayDepth; ++i) {
            builder.append('[');
        }

        builder.append(this.element.obfuscatedPrefix);

        if(this.element == ElementType.CLASS) {
            builder.append(this.className);
            builder.append(';');
        }

        return builder.toString();
    }

    public int getObfuscatedLength() {
        return this.arrayDepth + (this.className != null ? this.className.length() + 1 : 0) + 1;
    }

    public int getClassicLength() {
        return this.arrayDepth * 2 + (this.className != null ? this.className.length() : 0) + this.element.readable.length();
    }

    /**
     * The type of element.
     */
    public static enum ElementType {

        INT('I', "int"),
        BYTE('B', "byte"),
        CHAR('C', "char"),
        DOUBLE('D', "double"),
        FLOAT('F', "float"),
        LONG('J', "long"),
        SHORT('S', "short"),
        BOOLEAN('Z', "boolean"),
        VOID('V', "void"),
        CLASS('L', "class");

        public final char obfuscatedPrefix;
        public final String readable;

        ElementType(char obfuscatedPrefix, String readable) {
            this.obfuscatedPrefix = obfuscatedPrefix;
            this.readable = readable;
        }

        public static ElementType fromPrefix(char prefix) {
            for(ElementType type : values()) {
                if(type.obfuscatedPrefix == prefix) return type;
            }

            return null;
        }

        public static ElementType fromReadable(String readable) {
            for(ElementType type : values()) {
                if(type.readable.equals(readable)) return type;
            }

            return null;
        }
    }

    /**
     * Creates a {@link SignatureElement} based on an string in the obfuscated format.
     * @param obfuscated the obfuscated.
     * @return the {@link SignatureElement}
     */
    public static SignatureElement fromObfuscated(String obfuscated) {
        int arrayDepth = 0;

        while(obfuscated.charAt(arrayDepth) == '[') {
            ++arrayDepth;
        }

        ElementType type = ElementType.fromPrefix(obfuscated.charAt(arrayDepth));
        if(type != ElementType.CLASS) return new SignatureElement(type, arrayDepth);

        MappingKey className = MappingKey.of(obfuscated.substring(arrayDepth + 1, obfuscated.length() - 1)); // Gets the class name by making a substring without the 'L' prefix and the ';' suffix

        return new SignatureElement(type, className, arrayDepth);
    }

    /**
     * Creates a {@link SignatureElement} based on an string in the classic format.
     * @param classic the classic.
     * @return the {@link SignatureElement}
     */
    public static SignatureElement fromClassic(String classic) {
        int arrayDepth = 0;
        int lastAlphaIndex = 0;


        for(char c : classic.toCharArray()) {

            if(c == '[') ++arrayDepth;

            if(!Character.isAlphabetic(c)) {
                --lastAlphaIndex;
                break;
            }

            ++lastAlphaIndex;
        }

        ElementType type = ElementType.fromReadable(classic.substring(0, lastAlphaIndex));

        if(type != null) return new SignatureElement(type, arrayDepth);

        return new SignatureElement(type, MappingKey.of(classic.substring(0, lastAlphaIndex)), arrayDepth);
    }

}
