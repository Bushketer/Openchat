import java.util.Base64;

public abstract class Base64Compressor implements Transmission
{
	Transmission input;	

	Base64Compressor(Transmission decorate)
	{
		input = decorate;
		base64_Action();
	}


	public abstract void base64_Action();

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
