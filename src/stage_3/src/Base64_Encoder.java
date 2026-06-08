import java.util.Base64;


public class Base64_Encoder extends Base64Compressor 
{

	Base64_Encoder(Transmission decorate)
	{
		super(decorate);
	}
	
	@Override
	public void base64_Action()
	{
		String encoded = Base64.getEncoder().encodeToString(input.getCipher());
		setText(encoded);
	}


}
