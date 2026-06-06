


public class Packet implements Transmission
{
	String text;
	byte[] cipher;



	Packet()
	{
		text = "";
		cipher = null;
	}



	public void setText(String text)
	{
		this.text = text;	
	}



	public void setCipher(byte[] cipher)
	{
		this.cipher = cipher;	
	}



	public String getText()
	{
		return text;
	}



	public byte[] getCipher()
	{
		return cipher;
	}

}
