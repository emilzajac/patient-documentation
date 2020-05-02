package com.patient.treatment.documentation.gui.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Log4j2
@Component
public class AttributeCipher implements AttributeConverter<String, String> {

    private static final String AES = "AES";
    private static final String SECRET = "yG3oRG**EJ47w1ab4&5VDytQ";
    private static final String SECRET_IV = "94e3Q78#E*FmaAk%";
    private static final String AES_TRANSFORMATION_MODE = "AES/CBC/PKCS5PADDING";

    private final Key key;

    public AttributeCipher() {
        key = new SecretKeySpec(SECRET.getBytes(), AES);
    }

    @Override
    public String convertToDatabaseColumn(String data) {
        String encryptedText = StringUtils.EMPTY;

        if (data == null || key == null)
            return encryptedText;

        try {
            Cipher encryptCipher = Cipher.getInstance(AES_TRANSFORMATION_MODE);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(SECRET_IV.getBytes()));//new IvParameterSpec(getIV()) - if you want custom IV

            //encrypted data:
            byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            //take IV from this cipher
            byte[] iv = encryptCipher.getIV();

            //append Initiation Vector as a prefix to use it during decryption:
            byte[] combinedPayload = new byte[iv.length + encryptedBytes.length];

            //populate payload with prefix IV and encrypted data
            System.arraycopy(iv, 0, combinedPayload, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combinedPayload, iv.length, encryptedBytes.length);

            encryptedText = Base64.getEncoder().encodeToString(combinedPayload);

        } catch (NoSuchAlgorithmException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException | InvalidKeyException | InvalidAlgorithmParameterException exception) {
            log.error("Cannot encrypt data", exception);
        }
        return encryptedText;
    }

    @Override
    public String convertToEntityAttribute(String encryptedString) {
        String decryptedText = StringUtils.EMPTY;

        if (encryptedString == null || key == null)
            return decryptedText;

        try {
            //separate prefix with IV from the rest of encrypted data
            byte[] encryptedPayload = Base64.getDecoder().decode(encryptedString);
            byte[] iv = SECRET_IV.getBytes();
            byte[] encryptedBytes = new byte[encryptedPayload.length - iv.length];

            //populate iv with bytes:
            System.arraycopy(encryptedPayload, 0, iv, 0, iv.length);

            //populate encryptedBytes with bytes:
            System.arraycopy(encryptedPayload, iv.length, encryptedBytes, 0, encryptedBytes.length);

            Cipher decryptCipher = Cipher.getInstance(AES_TRANSFORMATION_MODE);
            decryptCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

            byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
            decryptedText = new String(decryptedBytes);

        } catch (NoSuchAlgorithmException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | InvalidKeyException exception) {
            log.error("Cannot decrypt data", exception);
        }

        return decryptedText;
    }
}
