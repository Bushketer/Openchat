import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class client implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done = false;
    private SecretHolder holder = new SecretHolder();
	
    private Encryption crypt = new Encryption();
    private BaseEncoding base = new BaseEncoding();

    private Terminal term;


    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inputHandler = new InputHandler();
            Thread t = new Thread(inputHandler);
            t.start();
	    term = new Terminal();

	    //Show and initialize fonts
	    FontThemes.initialize();
	    FontThemes.showcase();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {

		Transmission packet = crypt.new DecryptAES( base.new Decoder64(new Packet(inMessage)), holder);

		//NAME (YELLOW) : MESSAGE (RED)
                term.printUserMessage(packet.getText());
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
		int msg_count  = 0;
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine();
			
		    //TO-DO implement a command pattern
                    if (message.equals("/quit")) {
                        inReader.close();
                        shutdown();
	    	    }
		    else if (message.startsWith(FontThemes.FONT_CHANGE_CMD)){

			String index = message.substring(FontThemes.FONT_CHANGE_CMD.length());
			if(index == null)
				return;

			int id = Integer.valueOf(index);
			term.newFont(FontThemes.selectFont(id));
			
			term.println("Hello");
			term.testTheme();
                    } 
		    else {
			
			Transmission packet = base.new Encoder64( crypt.new EncryptAES(new Packet(message), holder));
			
                        out.println(packet.getText());
                    }
                }
            } catch (IOException e) {
		
            }
	    catch(NumberFormatException e) {
		term.println("Fonts are indexed with numbers");
		run();
	    }
        }
    }

    public static void main(String[] args) {
        client client1 = new client();
        client1.run();
    }

}
