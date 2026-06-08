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

    //Encodes Cipher
    //Used to encode before sending
    public void base64_Byte_Encode(){
	String encoded = Base64.getEncoder().encodeToString(input.getCipher());
	setText(encoded);
    }
	
    //Decodes received String into bytes
    //Used to decode Cipher
    public void base64_Text_Decode(){
	byte[] decoded = Base64.getDecoder().decode(input.getText());
	setCipher(decoded);
    }

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
