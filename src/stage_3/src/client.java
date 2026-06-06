import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class client implements Runnable {

    private Utils utils = new Utils();
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done = false;
    //keep this safe!
    //private SecretKey secret = utils.stringToKey("V0qLGapbAHw9Fbyh5yWgwA==");
    //private IvParameterSpec iv = utils.generateIv();
    //private static final String SHARED_IV_STRING = "1234567890123456";
    //private IvParameterSpec iv = new IvParameterSpec(SHARED_IV_STRING.getBytes(StandardCharsets.UTF_8));
	
    SecretHolder holder = new SecretHolder();


    @Override
    public void run() {
        try {
            Socket client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inputHandler = new InputHandler();
            Thread t = new Thread(inputHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                //byte[] secret_message = utils.base64_decode(inMessage);
                //inMessage = utils.decrypt(secret_message, holder.getSecret(), holder.getIv());
		Packet packet = new Packet();
		packet.setText(inMessage);
		Base64Compressor msg = new Base64Compressor(packet);
		msg.base64_Text_Decode();
		//packet.setText(utils.decrypt(msg.getCipher(), holder.getSecret(), holder.getIv()));
		Encryptor crypt = new Encryptor(packet, holder);
		crypt.decrypt();


                System.out.println(packet.getText());
            }

        } catch (IOException e) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            // ignore
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine();
		    Packet packet = new Packet();
		    packet.setText(message);

                    if (message.equals("/quit")) {
                        System.out.println("quiting???");
                        inReader.close();
                        shutdown();
                    } else {
                        //byte[] encrypted_text = utils.encrypt(message, holder.getSecret(), holder.getIv());
                        //message = Utils.base64_encode(encrypted_text);
			//packet.setCipher(utils.encrypt(message, holder.getSecret(), holder.getIv()));
			Encryptor crypt = new Encryptor(packet, holder);
			crypt.encrypt();
			Base64Compressor msg = new Base64Compressor(packet);
			msg.base64_Byte_Encode();
                        out.println(msg.getText());
                    }
                }
            } catch (IOException e) {

            }
        }
    }

    public static void main(String[] args) {
        client client1 = new client();
        client1.run();
    }

}
