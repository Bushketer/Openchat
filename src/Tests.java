import java.lang.String;



public class Tests
{

	public void messageServer(String ip, int port)
	{
		Network_Client client = new Network_Client();
		client.startConnection(ip, port);
		
		String response;
		response = client.sendMessage("Hello");
		response = client.sendMessage("Hey how are you, server?");
		response = client.sendMessage("Everything works!");
		response = client.sendMessage("exit");
	}
}
