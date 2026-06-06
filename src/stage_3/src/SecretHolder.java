import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class SecretHolder
{

	private static final String SHARED_IV_STRING = "1234567890123456";
	private SecretKey secret;
	private IvParameterSpec iv;

	SecretHolder()
	{
		//Get key from file
		//UNSAFE METHOD
		String key = "V0qLGapbAHw9Fbyh5yWgwA==";
		secret = Utils.stringToKey(key);
		iv = new IvParameterSpec(SHARED_IV_STRING.getBytes(StandardCharsets.UTF_8));

		//TO-DO dynamic keys
		//generateKey();
		//generateIv();
	}


	
	public SecretKey getSecret()
	{
		return secret;
	}



	public IvParameterSpec getIv()
	{
		return iv;
	}


	/*
	public void generateKey()
	{
		try {
		    KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
		    keygenerator.init(128);
		    secret = keygenerator.generateKey();
		} catch (NoSuchAlgorithmException e){
		    throw new RuntimeException("Failed to generate AES key", e);
		}
	}


		
	public void generateIv()
	{
		byte[] initializationVector = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVector);
		iv = new IvParameterSpec(initializationVector);
	}
	*/
}
