import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class Encryptor implements Transmission
{
	Transmission input;
	SecretHolder holder;
	
    	
	Encryptor(Transmission decorate, SecretHolder secret)
	{
		input = decorate;
		holder = secret;
		encryptor_Action();
	}
	

	public abstract void encryptor_Action();

	//SETTERS

	public void setText(String text)
	{
		input.setText(text);
	}

	public void setCipher(byte[] cipher)
	{
		input.setCipher(cipher);
	}

	//GETTERS

	public String getText()
	{
		return input.getText();
	}


	public byte[] getCipher()
	{
		return input.getCipher();
	}
}
