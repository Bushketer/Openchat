import java.util.Base64;


public class Base64_Decoder extends Base64Compressor 
{

	Base64_Decoder(Transmission decorate)
	{
		super(decorate);
	}
	
	@Override
	public void base64_Action()
	{
		byte[] decoded = Base64.getDecoder().decode(input.getText());
		setCipher(decoded);
	}


}
