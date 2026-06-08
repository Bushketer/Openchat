import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class DecryptAES extends Encryptor
{
	
	DecryptAES(Transmission decorate, SecretHolder holder)
	{
		super(decorate, holder);
	}
	

	
	@Override
	public void encryptor_Action()
	{		
		try {
		    Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
		    cipher.init(Cipher.DECRYPT_MODE, holder.getSecret(), holder.getIv());
		    byte[] plainText = cipher.doFinal(input.getCipher());
		    setText(new String(plainText));
		} catch (Exception e){
		    throw new RuntimeException("Failed to decrypt text", e);
		}
	}
}
