import java.lang.String;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.io.IOException;
import java.net.UnknownHostException;


public class Network_Server
{		
	ServerSocket serverSocket;
	Socket clientSocket;
	private PrintWriter messagePrint;
	private BufferedReader messageReceive;

	Network_Server(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	

	public void startServer()
	{
		try
		{
			clientSocket = serverSocket.accept();
			messagePrint = new PrintWriter(clientSocket.getOutputStream(), true);
			messageReceive = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()) );
			
			String message;

			while( (message = messageReceive.readLine()) != null)
			{
				if(message.equals("exit"))
				{
					messagePrint.println("exit initiated");
					break;
				}
				messagePrint.println(message);
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

	}
	

	public void stopServer()
	{
		try
		{
			messagePrint.close();
			messageReceive.close();
			clientSocket.close();
			serverSocket.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
