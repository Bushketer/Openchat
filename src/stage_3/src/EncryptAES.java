import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class EncryptAES extends Encryptor
{
	
	EncryptAES(Transmission decorate, SecretHolder holder)
	{
		super(decorate, holder);
	}
	

	
	@Override
	public void encryptor_Action()
	{       	
		try {
		    Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
		    cipher.init(Cipher.ENCRYPT_MODE, holder.getSecret(), holder.getIv());
		    setCipher(cipher.doFinal(input.getText().getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e){
		    throw new RuntimeException("Failed to encrypt text", e);
		}
		
	}
}
