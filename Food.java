
public class Food extends InventoryItem{
	
	
	private int healthValue;
	
	public Food(String n, String d, double w, int hv) {
		super(n, d, w);
		healthValue = hv;
		
	}
	

	public int getHealthValue() {
		return healthValue;
	}
	
	public void setHealthValue(int newHealthValue) {
		healthValue = newHealthValue;
	}
	
	public String toString() {
		return super.toString() + " " + healthValue;
	}

}
