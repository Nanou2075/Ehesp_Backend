package com.elearning.elearning.security.rsaKey;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKayPair {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        var keyPair=keyPairGenerator.generateKeyPair();
        byte[] publicKey = keyPair.getPublic().getEncoded();
        byte[] privateKey = keyPair.getPrivate().getEncoded();
                                /*
                                Generate a private Key
                                 */
        PemWriter pemWriterPrivateKey = new PemWriter(new OutputStreamWriter(new FileOutputStream("privateKey.pem")));
        PemObject pemPrivateKey=new PemObject("PRIVATE KEY",privateKey);
        pemWriterPrivateKey.writeObject(pemPrivateKey);
        pemWriterPrivateKey.close();
                                        /*
                                Generate a public Key
                                 */

        PemWriter pemWriterPublicKey = new PemWriter(new OutputStreamWriter(new FileOutputStream("publicKey.pem")));
        PemObject pemPublicKey=new PemObject("PUBLIC KEY",publicKey);
        pemWriterPublicKey.writeObject(pemPublicKey);
        pemWriterPublicKey.close();

    }
}
