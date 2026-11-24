import java.util.*;
public class Key extends InventoryItem {
	
	
	private ArrayList<String> nameOfRoomIWorkIn;
	private ArrayList<String> directionToOpen;
	
	public Key(String n, String d, String roomName, String direction) {
		super(n, d, .1);
		nameOfRoomIWorkIn = new ArrayList<String>();
		nameOfRoomIWorkIn.add(roomName);
		directionToOpen = new ArrayList<String>();
		directionToOpen.add(direction);
	}
	
	public Key(String n, String d, ArrayList<String> roomNames, ArrayList<String> directions) {
		super(n, d, .1);
		nameOfRoomIWorkIn = new ArrayList<String>();
		nameOfRoomIWorkIn = roomNames;
		directionToOpen = new ArrayList<String>();
		directionToOpen = directions;
	}
	
	public String toString() {
		return super.toString() + " " + nameOfRoomIWorkIn + " " + directionToOpen;
	}
	
	public String getDirection1() {
		String returnStat = "";
		for (int d = 0; d < 1; d++) {
			returnStat = returnStat + directionToOpen.get(d);
		}
		return returnStat;
	}
	
	public String getDirection2() {
		String returnStat = "";
		for (int d = 1; d < 2; d++) {
			returnStat = returnStat + directionToOpen.get(d);
		}
		return returnStat;
	}
	
	public String getRoomName1() {
		String returnStat = "";
		for (int r = 0; r < 1; r++) {
			returnStat = returnStat + nameOfRoomIWorkIn.get(r);
		}
		return returnStat;
	}
	
	public String getRoomName2() {
		String returnStat = "";
		for (int r = 1; r < 2; r++) {
			returnStat = returnStat + nameOfRoomIWorkIn.get(r);
		}
		return returnStat;
	}

}
	
