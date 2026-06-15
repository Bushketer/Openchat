import java.util.Base64;


public class BaseEncoding
{

	//Base64 classes
	class Decoder64 extends PacketWrapper
	{

		Decoder64(Transmission decorate)
		{
			super(decorate);
			transform_Action();
		}
		
		@Override
		public void transform_Action()
		{
			byte[] decoded = Base64.getDecoder().decode(input.getText());
			setCipher(decoded);
		}
	}



	class Encoder64 extends PacketWrapper 
	{

		Encoder64(Transmission decorate)
		{
			super(decorate);
			transform_Action();
		}
		
		@Override
		public void transform_Action()
		{
			String encoded = Base64.getEncoder().encodeToString(input.getCipher());
			setText(encoded);
		}
	}
}
