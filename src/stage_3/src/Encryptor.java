import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryptor
{
	Transmission input;
	SecretHolder holder;
	
    	
	Encryptor(Transmission decorate, SecretHolder secret)
	{
		input = decorate;
		holder = secret;
	}

	
	//Return a cipher	
	public void encrypt()
	{
		try {
		    Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
		    cipher.init(Cipher.ENCRYPT_MODE, holder.getSecret(), holder.getIv());
		    setCipher(cipher.doFinal(input.getText().getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e){
		    throw new RuntimeException("Failed to encrypt text", e);
		}
	}
		

	//Return Text	
	public void decrypt()
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



	public void setText(String text)
	{
		input.setText(text);
	}

	public void setCipher(byte[] cipher)
	{
		input.setCipher(cipher);
	}


	public String getText()
	{
		return input.getText();
	}


	public byte[] getCipher()
	{
		return input.getCipher();
	}
}
