package com.patient.treatment.documentation.gui.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Log4j2
@Component
public class EncryptDecryptService {

    private static final String SECRET_KEY_1 = "Le2r6PkY47rQQ48q";
    private static final String SECRET_KEY_2 = "zZ1wFFgWn6oJ7k0y";

    private IvParameterSpec ivParameterSpec;
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;

    public EncryptDecryptService() throws NoSuchPaddingException, NoSuchAlgorithmException {
        ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes(StandardCharsets.UTF_8));
        secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes(StandardCharsets.UTF_8), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public String encrypt(String data) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException exception) {
            log.error("Cannot encrypt data", exception);
        }
        byte[] encryptedBytes = new byte[0];
        try {
            encryptedBytes = cipher.doFinal(data.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException exception) {
            log.error("Cannot encrypt data", exception);
        }
        return Base64.encodeBase64String(encryptedBytes);
    }

    public String decrypt(String data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException exception) {
            log.error("Cannot decrypt data", exception);
        }
        byte[] decryptedBytes = new byte[0];
        try {
            decryptedBytes = cipher.doFinal(Base64.decodeBase64(data));
        } catch (IllegalBlockSizeException | BadPaddingException exception) {
            log.error("Cannot decrypt data", exception);
        }
        return new String(decryptedBytes);
    }

}
