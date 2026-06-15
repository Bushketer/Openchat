


public class Packet implements Transmission
{
	String text;
	byte[] cipher;


	//Default constructor
	Packet()
	{
		text = "";
		cipher = null;
	}
	

	//Cipher Shortcut constructor
	Packet(byte[] cipher)
	{
		text = "";
		setCipher(cipher);
	}


	//Text Shortcut constructor
	Packet(String text)
	{
		setText(text);
		cipher = null;
	}

	//SETTERS
	
	public void setText(String text)
	{
		this.text = text;	
	}



	public void setCipher(byte[] cipher)
	{
		this.cipher = cipher;	
	}

	//GETTERS

	public String getText()
	{
		return text;
	}



	public byte[] getCipher()
	{
		return cipher;
	}

}
