import java.lang.String;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.io.IOException;
import java.net.UnknownHostException;


public class NetworkServer
{		
	ServerSocket serverSocket;
	Socket clientSocket;
	private PrintWriter messageSend;
	private BufferedReader messageReceive;


	public void startServer(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			messageSend = new PrintWriter(clientSocket.getOutputStream(), true);
			messageReceive = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()) );
			
			String message;
			Utility.toTerminaln(serverSocket.toString());
			Utility.toTerminaln("=============================");
			Utility.toTerminaln("Client: " + clientSocket.toString());
			Utility.toTerminaln("catching messages");
			while((message = messageReceive.readLine()) != null)
			{
				Utility.toTerminaln("Server caught: " + message);

				if(message.equals("exit"))
				{
					messageSend.println("stopping");
					break;
				}

				messageSend.println(message);
			}
			
			stopServer();
		}
		catch(IOException e)
		{
			//TO-DO
		}

	}
	

	public void stopServer()
	{
		try
		{
			Utility.toTerminaln("Stopping server");
			messageSend.close();
			messageReceive.close();
			clientSocket.close();
			serverSocket.close();
		}
		catch(IOException e)
		{
			//TO-DO
		}
	}
}
