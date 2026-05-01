import java.lang.String;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.io.IOException;
import java.net.InetAddress;


public class NetworkClient
{
	Socket communicationSocket;
	PrintWriter messageSend;
	BufferedReader messageReceive;



	//Created socket immediately starts the connection
	public void startConnection(String ipAddr, int port)
	{
		try
		{
			InetAddress ipNet = InetAddress.getByName(ipAddr);
			communicationSocket = new Socket(ipNet, port);
			messageSend = new PrintWriter(communicationSocket.getOutputStream(), true);
			messageReceive = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()) );
			Utility.toTerminaln("client: " + ipNet.getHostAddress() + ":" + port);
			Utility.toTerminaln(communicationSocket.toString());
		}
		catch(IOException e)
		{
		//TO-DO
		}
	}



	public void stopConnection()
	{
		try
		{
			messageSend.close();
			messageReceive.close();
			communicationSocket.close();
		}
		catch(IOException e)
		{
			//TO-DO
		}
	}



	public String sendMessage(String message)
	{
		try
		{
			messageSend.println(message);
			Utility.toTerminaln("client sent: " + message);
			String response = messageReceive.readLine();
			Utility.toTerminaln("Client received: " + response);
			return response;
		}
		catch(IOException e)
		{
			//TO-DO
		}

		return "";
	}
}
