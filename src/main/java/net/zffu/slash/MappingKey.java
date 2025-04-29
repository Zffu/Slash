package net.zffu.slash;

import net.zffu.slash.utils.Comparable;

/**
 * The "key" of a mapping element. Can represent class names, method names and more! The use of this object is to allow multiple formats of key (example dot keys and slash keys)
 * @since 1.0.0
 */
public class MappingKey implements Comparable<MappingKey> {

    public final String[] keyElements;

    /**
     * Creates a mapping key with the provided key elements.
     * @param keyElements the key elements.
     */
    public MappingKey(String[] keyElements) {
        this.keyElements = keyElements;
    }

    /**
     * Gives the string length of the {@link MappingKey}
     * @return
     */
    public int length() {
        int length = (this.keyElements.length > 0 ? this.keyElements.length - 1 : 0);

        for(String element : this.keyElements) {
            length += element.length();
        }

        return length;
    }

    @Override
    public boolean isEquals(MappingKey mappingKey) {
        if(mappingKey.keyElements.length != this.keyElements.length) return false;

        for(int i = 0; i < this.keyElements.length; ++i) {
            if(!this.keyElements[i].equals(mappingKey.keyElements[i])) return false;
        }

        return true;
    }

    /**
     * Converts the provided string into a {@link MappingKey}.
     * If the string is in either the dot or slash format, it will return with the correct format.
     * If it isn't, the function will guess the format by finding the first non-alphabetic character.
     * @param s the string.
     * @return the mapping key.
     */
    public static MappingKey of(String s) {
        if(s.contains(".")) return new MappingKey(s.split("\\."));
        if(s.contains("/")) return new MappingKey(s.split("/"));

        // If the key isn't on the dot or slash format, try to guess it.

        for(char c : s.toCharArray()) {
            if(!Character.isAlphabetic(c)) return new MappingKey(s.split(String.valueOf(c)));
        }

        return null;
    }

}
