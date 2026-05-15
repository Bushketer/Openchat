import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ServerChatRoom
{
	private int MAXCLIENTS;
	private int MAXROOMS;
	
	//For scale a dictionary/map would be better
	//And a createRoom(int) function would be needed
	//A few kilobytes of RAM can be sacrificed for performance and simplicity
	private ArrayList<Integer>[] clientList;
	private Map<Integer, Integer> clientRooms = new HashMap<Integer, Integer>();
	
	ServerChatRoom()
	{
		MAXCLIENTS = 5;
		MAXROOMS = 3;
		clientList = new ArrayList[MAXROOMS];
		createRoom(0);
	}

	ServerChatRoom(int clients, int rooms)
	{
		MAXCLIENTS = clients;
		MAXROOMS = rooms;
		clientList = new ArrayList[MAXROOMS];
		createRoom(0);
	}



	//Asumes that the client and room exists
	private int getRoomOfClient(int clientId)
	{
		if(clientRooms.containsKey(clientId))
			return clientRooms.get(clientId);
		else
			return -1;
	}

	
	
	public ArrayList<Integer> getClientNeighbours(int clientId)
	{
		int room = getRoomOfClient(clientId);
		//System.out.println("Got a room " + room + " for " + clientId);

		if( room != -1)
			return clientList[room];
		else
			return null;
	}



	public void createRoom(int roomId)
	{
		if(roomId < MAXROOMS && clientList[roomId] == null)
			clientList[roomId] = new ArrayList<Integer>();
	}

	//Default room for all clients is 0
	//Transfers to a room if already present
	public Boolean addToRoom(int roomId, int clientId)
	{
		if(roomId >= MAXROOMS && clientList[roomId] != null && clientList[roomId].size() >= MAXCLIENTS)
			return false;

		if(clientRooms.get(clientId) != null)
		{
			removeFromRoom(roomId, clientId);
		}
		clientList[roomId].add(clientId);
		clientRooms.put(clientId, roomId);
		return true;
	}



	public Boolean removeFromRoom(int roomId, int clientId)
	{
		if(roomId == 0 && roomId >= MAXROOMS && clientList[roomId] != null && clientList[roomId].size() == 0)
			return false;
		
		if(clientList[roomId].contains(clientId))
		{
			clientList[roomId].remove(clientId);
			clientRooms.remove(clientId, roomId);
			return true;
		}
		else
			return false;
	}
}
