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


	public void startServer(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			messagePrint = new PrintWriter(clientSocket.getOutputStream(), true);
			messageReceive = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()) );
			
			String message;
			System.out.println(serverSocket.toString());
			System.out.println("=============================");
			System.out.println("Client: " + clientSocket.toString());
			System.out.println("catching messages");
			while((message = messageReceive.readLine()) != null)
			{
				System.out.println("Server caught: " + message);
				messagePrint.println(message);

				if(message.equals("exit"))
				{
					messagePrint.println("stopping");
					break;
				}
			}
			
			stopServer();
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
			System.out.println("Stopping server");
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
