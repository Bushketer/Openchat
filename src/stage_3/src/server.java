import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server implements Runnable {

    //KEEP THIS SAFE PLS!!!
    private Utils utils = new Utils();
    private SecretKey secret;
    //private IvParameterSpec iv;
    private ArrayList<ConnectionHandler> connections;
    private Map<Integer, ConnectionHandler> clientIndexes;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;
    private ServerChatRoom chatRoom;
    private static final String SHARED_IV_STRING = "1234567890123456";
    private IvParameterSpec iv = new IvParameterSpec(SHARED_IV_STRING.getBytes(StandardCharsets.UTF_8));
    //private Utils utils = new Utils();

    public server() {
        connections = new ArrayList<>();
        clientIndexes = new HashMap<Integer, ConnectionHandler>();
        chatRoom = new ServerChatRoom();
        done = false;
        //utils = new Utils();
        secret = utils.stringToKey("V0qLGapbAHw9Fbyh5yWgwA==");
        //iv = utils.generateIv();
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            pool = Executors.newCachedThreadPool();
            System.out.println("Server is running!");
            System.out.println("Secret key: " + utils.keyToString(secret));
            while (!done) {
                Socket client = server.accept();

                //TO-DO Update with authentication
                int newId = client.getPort();
                ConnectionHandler handler = new ConnectionHandler(client, newId);

                clientIndexes.put(newId, handler);
                if (chatRoom.addToRoom(0, newId) == false)
                    System.out.println(newId + " failed");

                connections.add(handler);
                pool.execute(handler);
            }
        } catch (IOException e) {
            shutdown();
        }
    }


    public void broadcast(String message) {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMessage(message);
            }
        }
    }


    public void broadcast(String message, int clientId) {
        ArrayList<Integer> indexes = chatRoom.getClientNeighbours(clientId);

        if (indexes == null)
            System.out.println(clientId + " not found in rooms");

        for (int i = 0; i < indexes.size(); i++) {
            int id = indexes.get(i);
            if (clientIndexes.get(id) != null)
                clientIndexes.get(id).sendMessage(message);
        }
    }


    public void shutdown() {
        try {
            done = true;
            if (!server.isClosed()) {
                server.close();
            }
            for (ConnectionHandler ch : connections) {
                ch.shutdown();
            }
        } catch (IOException e) {

        }


    }

    class ConnectionHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;
        private int clientId;

        public ConnectionHandler(Socket client, int id) {

            this.client = client;
            this.clientId = id;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                sendMessage("Please enter a nickname: ");
                //nickname = in.readLine();
                byte[] secret_nickname = utils.base64_decode(in.readLine());
                nickname = utils.decrypt(secret_nickname, secret, iv);
                System.out.println(nickname + " connected!");
                broadcast(nickname + " joined the chat!");
                String message;
                while ((message = in.readLine()) != null) {
                    byte[] secret_message = utils.base64_decode(message);
                    message = utils.decrypt(secret_message, secret, iv);
                    if (message.startsWith("/nick")) {
                        String[] messageSplit = message.split(" ", 2);
                        if (messageSplit.length == 2) {
                            broadcast(nickname + " renamed themselves to " + messageSplit[1]);
                            System.out.println(nickname + " renamed themselves to " + messageSplit[1]);
                            nickname = messageSplit[1];
                            out.println("Successfully changed nickname to " + nickname);
                        } else {
                            out.println("No nickname provided");
                        }
                    } else if (message.startsWith("/join")) {
                        try {
                            String messageSplit[] = message.split(" ", 2);
                            if (messageSplit.length == 2) {
                                int newRoom = Integer.valueOf(messageSplit[1]);
                                chatRoom.createRoom(newRoom); //creates if not present
                                if (chatRoom.addToRoom(newRoom, clientId)) {
                                    System.out.println(client + " joined " + newRoom);
                                    out.println("Successfully joined room " + newRoom);
                                }
                            } else
                                out.println("Incorrect room id");

                        }
                        //Could add another exception for failed join room
                        catch (NumberFormatException e) {
                            System.out.println(e.toString());
                            out.println("Incorrect room id");
                        }
                    } else if (message.startsWith("/quit")) {
                        broadcast(nickname + " left the chat!");
                        shutdown();
                    } else {
                        System.out.println(nickname + " : " + message);
                        broadcast(nickname + " : " + message, clientId);
                    }
                }
            } catch (IOException e) {
                shutdown();
            }

        }

        public void sendMessage(String message) {
            byte[] encrypted_text = utils.encrypt(message, secret, iv);
            message = Utils.base64_encode(encrypted_text);
            out.println(message);


        }

        public void shutdown() {
            try {
                in.close();
                out.close();

                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {

            }
        }
    }

    public static void main(String[] args) {
        server server = new server();
        server.run();
    }
}
