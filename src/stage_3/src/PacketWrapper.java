import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class PacketWrapper implements Transmission
{
	Transmission input;
	


	PacketWrapper(Transmission input)
	{
		this.input = input;
	}



	public abstract void transform_Action();

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
