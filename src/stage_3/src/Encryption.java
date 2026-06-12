import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Encryption
{
	//AES encryption
	class EncryptAES extends PacketWrapper
	{

		SecretHolder holder;

		EncryptAES(Transmission decorate, SecretHolder holder)
		{
			super(decorate);
			this.holder = holder;
			transform_Action();
		}
		

		
		@Override
		public void transform_Action()
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

	

	class DecryptAES extends PacketWrapper
	{
		SecretHolder holder;	

		DecryptAES(Transmission decorate, SecretHolder holder)
		{
			super(decorate);
			this.holder = holder;
			transform_Action();
		}
		


		@Override
		public void transform_Action()
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
}
