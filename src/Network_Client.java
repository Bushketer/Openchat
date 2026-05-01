import java.lang.String;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.io.IOException;
import java.net.InetAddress;


public class Network_Client
{
	Socket clientSocket;
	PrintWriter messagePrint;
	BufferedReader messageReceive;



	//Created socket immediately starts the connection
	public void startConnection(String ipAddr, int port)
	{
		try
		{
			InetAddress ipNet = InetAddress.getByName(ipAddr);
			clientSocket = new Socket(ipNet, port);
			messagePrint = new PrintWriter(clientSocket.getOutputStream(), true);
			messageReceive = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()) );
			System.out.println("client: " + ipNet.getHostAddress() + ":" + port);
			System.out.println(clientSocket.toString());
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}



	public void stopConnection()
	{
		try
		{
			messagePrint.close();
			messageReceive.close();
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}



	public String sendMessage(String message)
	{
		try
		{
			messagePrint.println(message);
			System.out.println("client sent: " + message);
			String response = messageReceive.readLine();
			System.out.println("Client received: " + response);
			return response;
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

		return "";
	}
}
