package com.tdcsupport.cripto;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

@Component
public class CryptoService {

    private static final String CERT_NAME = "cifrado2027.cer";
    private InputStream certIS;

    public CryptoService() {
        try {
            certIS = getClass().getClassLoader().getResourceAsStream("certs/" + CERT_NAME);
        } catch (Exception ex) {
            System.err.println("Error loading certificate: " + ex.getMessage());
        }
    }

    /*
     *  Cifra un texto utilizando el certificado de cifrado certName.
     *  @param plainText El texto plano a cifrar.
     *  @return base64 del texto cifrado.
     */

    public String encryptText(String plainText) throws Exception {
        PublicKey publicKey = loadPublicKey();

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private PublicKey loadPublicKey() throws Exception {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(certIS);

        return cert.getPublicKey();
    }

}
