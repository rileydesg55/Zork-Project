
public class Tool extends InventoryItem{
	
	
	private String functionRoom;
	
	public Tool(String n, String d, double w, String r) {
		super(n, d, w);
		functionRoom = r;
	}
	
	public String getRoom() {
		return functionRoom;
	}
}
