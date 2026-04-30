import java.lang.String;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.io.IOException;


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
			clientSocket = new Socket(ipAddr, port);
			messagePrint = new PrintWriter(clientSocket.getOutputStream());
			messageReceive = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()) );
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
			String response = messageReceive.readLine();
			return response;
		}
		catch(IOException e)
		{
			System.out.println(e);
			return null;
		}
	}
}
