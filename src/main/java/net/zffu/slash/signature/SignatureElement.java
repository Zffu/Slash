package net.zffu.slash.signature;

/**
 * An element inside a {@link Signature}. Also represents a method's return signature.
 * @since 1.0.0
 * @see {@link Signature}
 */
public class SignatureElement {

    public final ElementType element;
    public final String className;
    public final int arrayDepth;

    public SignatureElement(ElementType element, String className, int arrayDepth) {
        this.element = element;
        this.className = className;
        this.arrayDepth = arrayDepth;
    }

    public SignatureElement(ElementType element, String className) {
        this.element = element;
        this.className = className;
        this.arrayDepth = 0;
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

        if(this.element == ElementType.CLASS) builder.append(this.className);

        return builder.toString();
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
    }


}
