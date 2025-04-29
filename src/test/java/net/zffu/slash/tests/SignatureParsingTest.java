package net.zffu.slash.tests;

import net.zffu.slash.signature.Signature;

public class SignatureParsingTest {

    public static void main(String[] args) {
        Signature classicSignature = Signature.fromClassic("(float)int");
        Signature obfuscatedSignature = Signature.fromObfuscated("(F)I");

        if(!classicSignature.isEquals(obfuscatedSignature)) {
            throw new RuntimeException("Both signatures aren't equal!");
        }

    }

}
