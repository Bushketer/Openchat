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
    SecretHolder holder = new SecretHolder();


    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inputHandler = new InputHandler();
            Thread t = new Thread(inputHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
		/*
		Packet packet = new Packet();
		packet.setText(inMessage);
		Base64Compressor msg = new Base64Compressor(packet);
		msg.base64_Text_Decode();
		Encryptor crypt = new Encryptor(packet, holder);
		crypt.decrypt();
		*/

		Transmission packet = new DecryptAES(new Base64_Decoder(new Packet(inMessage)), holder);

		//TO-DO create a message formater / parser
		//Output different color text
		//Seperate coloring with " : " 
		//NAME (YELLOW) : MESSAGE (RED)
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
	    System.out.println(e.toString());
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine();

                    if (message.equals("/quit")) {
                        inReader.close();
                        shutdown();
                    } else {
			
                        //byte[] encrypted_text = utils.encrypt(message, holder.getSecret(), holder.getIv());
                        //message = Utils.base64_encode(encrypted_text);
			//packet.setCipher(utils.encrypt(message, holder.getSecret(), holder.getIv()));
			/*
			Encryptor crypt = new Encryptor(packet, holder);
			crypt.encrypt();
			Base64Compressor msg = new Base64Compressor(packet);
			msg.base64_Byte_Encode();
			*/
			
			Transmission packet = new Base64_Encoder(new EncryptAES(new Packet(message), holder));
			

                        out.println(packet.getText());
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
