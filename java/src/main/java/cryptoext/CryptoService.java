package cryptoext;

import org.bouncycastle.util.io.pem.PemReader;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.StringReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CryptoService {
    private static final String CRYPTO_ALGORITHM = "RSA";
    private static final String CRYPTO_CYPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private final String publicKey;

    static {
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public CryptoService(String publicKey) {
        this.publicKey = publicKey;
    }

    public String encrypt(String msg) {
        try {
            PemReader pemReader = new PemReader(new StringReader(publicKey));
            byte[] content = pemReader.readPemObject().getContent();
            pemReader.close();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(content);
            KeyFactory kf = KeyFactory.getInstance(CRYPTO_ALGORITHM);
            PublicKey publicKeySecret = kf.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(CRYPTO_CYPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKeySecret);
            byte[] encryptedBytes = cipher.doFinal(msg.getBytes());
            return new String(Base64.getEncoder().encode(encryptedBytes));
        } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
