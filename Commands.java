 //TODO: Add the custom commands you made in Checkpoint 2 and your Execute commands if needed

import java.util.*;

/*
 * 
 * All green comments are tasks for these checkpoints
 * Note that this code will be used for both checkpoints 4 and 5.
 * Be sure to focus only on the checkpoint you are currently assigned to work on.
 * 
 */


/**
 * Creates the class Commands, 
 * There will only be one instance used by the game
 * therefore all of its methods are static to prevent multiple
 */

public class Commands {



/*
 * TODO: checkpoint 4 Tasks
 * 
 * add class level variables
 * Declare a two dimensional array called "map" it should be of type "Room"
 * Declare two variables of type int called "row" and "column"
 * Declare a Room object called currentRoom
 * 
 * note that these variables should be "static" because there should only be one world
 */
	private static Room[][] map;
	private static int row;
	private static int column;
	private static Room currentRoom;
	private static ArrayList <InventoryItem> inventory;
	private static String playerAction = "";
	private static boolean inTigerRoom = false;
	private static boolean survivedTiger = true;
	private static boolean tigerDead = false;
	private static ArrayList<String> correctCombo = new ArrayList<String>();
	private static ArrayList<String> vineCombo = new ArrayList<String>();
	private static ArrayList<String> playerCombo = new ArrayList<String>();
	private static int turnCount = 1;
	private static int tortureAttempts = 1;

 
	
	
	
	
	

/**
 * runGame method
 * method responsible for running a console version of the game
 * this method returns nothing and has no parameters
 * it contains the while loop that is used to run the game
 */
	
	public static void runGame(){


/*
 * TODO: checkpoint 4 Tasks
 * 
 * Make a call to the initializeMap method, then complete that method below 
 * 
 */

	initializeMap();
	inventory = new ArrayList<InventoryItem>();
	Tool musket = new Tool("musket", "A reliable but time consuming weapon", 15, "Tiger Room");
	Tool gunpowder = new Tool("gunpowder", "Can be used to fill your musket or for some other purpose", .5, "Tiger Room");
	Tool musketball = new Tool("musketball", "Is used to fire the musket", 1, "Tiger Room");
	Food bandage = new Food("bandage", "Can heal minor wounds", .5, 5);
	inventory.add(musket);
	inventory.add(gunpowder);
	inventory.add(musketball);
	inventory.add(bandage);
	correctCombo.add("gunpowder");
	correctCombo.add("musketball");
	correctCombo.add("musket");
	vineCombo.add("vine");
	vineCombo.add("vine");
	vineCombo.add("vine");
	
 








/*
 * TODO: checkpoint 4 Tasks
 * 
 * initialize the values for your variables "row" and "column"
 * to represent the starting location of your game, 
 * then set the currentRoom to the starting location
 * 
 */
		row = 9;
		column = 4;
		currentRoom = map[row][column];
		
    
    
    
    
    
    
    

		
		String userInput = "";
		String command = "";
		String item = "";
		Scanner s = new Scanner(System.in);
		
//TODO: When you have made your game work in the console
//change done to true to play your game in the JFrame		
		boolean done = false;
		String output ="";
		if(!done){
			output = gameIntro();
			System.out.println(output);
		}
		
		while(!done){
			System.out.print("\n\n>>");      
			userInput = s.nextLine().toLowerCase();
			command = getFirstWord(userInput);
			item = getRestOfSentence(userInput);
			output = executeCommand(command, item);
			updateRoom();

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the currentRoom to the new location on the map
 * Hint: there is a new method for this, go to the bottom and make it
 * using the currentRoom object. 
 */


			
			System.out.println(output +"\n"); 

			if(command.equals("quit")) {
				done = true;
			}
			if(currentRoom.equals(map[7][3])) {
				System.out.println("You step forward into the dust covered room and see before you the treasure that was promised. \nIt is more immense than you could have imagined, and exactly what the expedition was looking for. \nYou don't know if you'll be able to find the captain and get back to your home, but with this much \ntreasure you could do just about anything.");
				done = true;
			}
			if(currentRoom.equals(map[10][5])) {
				System.out.println("You unlock the door and slowly step into the pitch black room, but your foot doesn't hit the ground and you fall into a pit filled with spikes.");
				done = true;
			}
			if(currentRoom.equals(map[9][5])) {
				if(!currentRoom.hasObject("key")) {
					currentRoom.setDescription("An empty pedastal sits in the center of the room where the key once laid.");
					currentRoom.setName("Old Key Room");
				}
			}
			if(currentRoom.equals(map[9][2])) {
				if (!tigerDead) {
					
					tigerRoom();
				}
				if (survivedTiger == true && tigerDead == true) {
					if (currentRoom.hasObject("dynamite")) {
						System.out.println("You were able to save yourself from the tiger and can see that \nthere's some dynamite on the ground where it was.");
					} else {
						System.out.println("Maybe this dynamite could blow up the door at the Main Hall...");
					}
					
					currentRoom.unlock("north");
				}
				if (survivedTiger == false) {
					System.out.println("You failed to load your musket in time and fumbled, getting you \nmauled by the hungry tigerin an extremely violent manner.");
					done = true;
				}
			}
			if (tortureAttempts > 3) {
				done = true;
			}
		}
		
		s.close();	
	}
	
/**
 * gameIntro method 
 * used to set the scene to the opening of your game 
 * @return: a String that is the opening description to your game, 
 *          be sure to note the help option 
 *          (i.e. "type help for a list of commands").  
 */

//TODO: Update this to your Intro (copy and paste it here)	
	public static String gameIntro(){
	    String output = "You break through the treeline and bush and find yourself standing before a massive and overgrown temple. \n'This is it', your captain says. \nHe steps forward in front of you and the rest of your team. \n'This temple can hold the riches promised to us by the native people. If we can find the gold, \nwe can bring it back home and the Kingdom will be able to fund and win their war. Remember, we are doing \nthis for our king and for God, and both will know of the good work we do here.' \nHe turns to you, \n'Will you go on in first and make sure it is clear?' \nYou nod your head and step up over the rubble and through a crack where you can jump down into the room. \nIt's dark, but there's no sign of danger. You turn around and shout 'clear!', but the noise causes \nthe temple's walls and roof to collapse. You hear your comrades calling for you from the outside but \nthere is nothing to do. You have to find a way out, and the only way there is to go is north. \n(Type \"help\" to see commands)";
	    return output;
	  }
	
	
/**
 * getFirstWord method
 * pulls the first word out of any phrase (1 or more words)
 * @param a string that contains the commands from the user
 * @return a string that is only the first word, 
 *         returns a blank string "" if nothing entered, 
 *         do not use print or println
 */
	
	public static String getFirstWord(String phrase){
	    int findSpace = phrase.indexOf(" ");
	    String firstWord;
	    if (findSpace > -1){
	      firstWord = phrase.substring(0, findSpace);
	      return firstWord;      
	    } else {
	      return phrase;
	    }

	  }

/**
 * getRestOfSentence 
 * pulls everything after the first word and the space behind it
 * @param a string that contains the commands from the user
 * @return a string that returns everything after the first word 
 *         and the space behind it, returns a blank string "" if 1 word entry 
 *         or nothing entered. 
 *         Do not use print or println
 */
		
	public static String getRestOfSentence(String phrase){
	    String rest;
	    if(phrase.indexOf(" ") > 0){
	      int findSpace = phrase.indexOf(" ");      
	      rest = phrase.substring(findSpace + 1);
	    } else {
	      rest = "";
	    }
	    return rest;
	  }

	
/**
 * north method
 * Method to state that you moved north, you will add movement functionality
 * in checkpoint 4
 * @return a string that states you moved North, do not use print or println	
 */

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "north" method to allow the player to move North only if possible
 * Be sure to both move the player north and state that the player moved North
 * If you cannot move North, then state that you cannot move North
 */

	public static String north(){
		getCurrentRoom();
		boolean able = currentRoom.getCanGoNorth();
		if(able) {
			row--;
			return "You moved North.";
		} else {
			return "Can't go North";
		}
	}



/**
 * south method
 * Method to state that you moved south, you will add movement functionality
 * in checkpoint 4
 * @return a string that states you moved South, do not use print or println
 */		

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "south" method to allow the player to move South only if possible
 * Be sure to move the player South and state that the player moved South
 * If you cannot move South, then state that you cannot move South
 */
	public static String south(){
		getCurrentRoom();
		boolean able = currentRoom.getCanGoSouth();
		if(able) {
			row++;
			return "You moved South.";
		} else {
			return "Can't go South";
		}
	}

/**
 * east method
 * Method to state the you moved East,  you will add movement functionality in 
 * checkpoint 4
 * @return a string that states you moved East, do not use print or println
 */
	
/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "east" method to allow the player to move East only if possible
 * Be sure to both move the player East and state that the player moved East
 * If you cannot move East, then state that you cannot move East
 */
	public static String east(){
		getCurrentRoom();
		boolean able = currentRoom.getCanGoEast();
		if(able) {
			column++;
			return "You moved East.";
		} else {
			return "Can't go East";
		}
	}

/**
 * west method
 * Method to state the you moved West, you will add movement functionality in 
 * checkpoint 4
 * @return a string that states you moved West, do not use print or println
 */
	
/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "west" method to allow the player to move West only if possible
 * Be sure to move the player West and state that the player moved West
 * If you cannot move West, then state that you cannot move West
 */
	
	public static String west(){
		getCurrentRoom();
		boolean able = currentRoom.getCanGoWest();
		if(able) {
			column--;
			return "You moved West.";
		} else {
			return "Can't go West";
		}
	}
	
/**
 * take method
 * Method to allow you to take an item, for now you will just state that 
 * "You took the <itemName>." where <itemName> is the actual name of the item.
 * 
 * example return statement:
 * "You took the apple." 
 * 
 * You will actually pick up items in checkpoint 5
 * 
 * @param takes in a String to determine what object was picked up
 * @return a String that states "You took the <itemName>", do not use print or println
 */
	
	public static String take(String item){
		String takeMessage;
		if (currentRoom.hasObject(item)){
			InventoryItem roomItem = currentRoom.removeObject(item);
			inventory.add(roomItem);
			takeMessage = "You took the " + item +".";
		}else {
			takeMessage = "That item isn't in this room.";
		}
		return takeMessage;
	}
	
/** 
 * drop method
 * Method to allow you to drop an item, for now you will just state that 
 * "You dropped the <itemName>." where <itemName> is the actual name of the item.
 * 
 * example return statement:
 * "You dropped the apple."
 * 
 * You will actually drop items in checkpoint 5
 * @param takes in a string to determine what object was dropped
 * @return a string that states "You dropped the <itemName>", do not use print or println 
 */
	
	public static String drop(String objectName){
		String dropMessage;
		if (hasItem(objectName)) {
			InventoryItem droppedItem = getItem(objectName);
			inventory.remove(droppedItem);
			currentRoom.addObject(droppedItem);
			dropMessage = "You dropped the " + objectName +".";
		} else {
			dropMessage = "You can't drop that item";
		}
		return dropMessage;
	}
	
/** 
 * look method
 * Method to allow you to look around the room
 * In checkpoint3, this will change to the room description
 *  @return a string that states "You looked around the room", do not use print or println
 */	
 
/*  
 * TODO: checkpoint 4 task
 * 
 * update the "look" method to return the Name of the Room, 
 * then a new line, and then the room's description instead of its current output.
 */
 
	public static String look(){
		getCurrentRoom();
		return currentRoom.getName() + "\n" + currentRoom.getDescription() + "\n" + currentRoom.getNamesOfObjectsInRoom();
	}
	
/** 
 * inventory method
 * Method to display the items in your inventory, for now state
 * "The current items in your inventory are:"
 * 
 * In checkpoint 5 you will create a String that consists of all of the 
 * items in your inventory
 *  @return the "The current items in your inventory are:", do not use print or println
 */
 
	public static String inventory(){
	  String output = "";
	  //TODO add checkpoint inventory logic here
	  for (InventoryItem item : inventory) {
		  output = output + item.getName() + "\n";
	  }
		return "The current items in your inventory are: " + output;
	}	


	
/**
 * examine method
 * Method that returns the description on an item in your inventory, for now state:
 * "Description of <itemName>" where <itemName> is the itemName
 * 
 * example return statement:
 * "Description of apple"
 * 
 * In checkpoint 5 you will return the description of the object you are examining
 * @param takes in a string to determine what item you want the description of
 * @return a string that is the description of the item, do not use print or println
 */


	public static String examine(String item){
		String returnThing = "You can't examine that item";
		for (InventoryItem thingy : inventory) {
			if (thingy.getName().equals(item)){
				returnThing = thingy.getDescription();
			}
		}
		return returnThing;
	}	

/**
 * eat method
 * Method that allows you to eat a Food object, for now state:
 * "You ate the <itemName>" where <itemName> is the name of the itemName
 * 
 * example return statement:
 * "You ate the apple."
 * 
 * In checkpoint 5, you will update this method to remove it from your inventory, 
 * but not drop it in the room.  Advanced: this could be useful for a health system
 * 
 * 
 * @param takes in a string to determine what item you want to eat
 * @return a string that states you ate the item, do not use print or println
 */	
 
	public static String eat(String item){
		String returnThing = "You can't eat that!";
		for (int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(item)) {
				if(inventory.get(i) instanceof Food) {
					returnThing = "You consumed the " + item;
					inventory.remove(i);
					}
				}
			}
		
		return returnThing;
	}		
	

/**  
 * use method
 * Method that allows you to use an item it, for now state:
 * "You used the <itemName>", where <itemName> is the name of the item.
 * 
 * example return statement:
 * "You used the apple."
 * 
 * In checkpoint 5 this will be updated to only allow the player to use a Key
 * in the room where the key can open a door
 * 
 * @param takes in a string to determine what item you want to use
 * @return a string that You used the item, do not use print or println
 */

	public static String use(String item){
		String returnStat = "You can't use that";
		for(int i = 0; i < inventory.size(); i++) {
			String itemName = inventory.get(i).getName();
			if(item.equals(itemName)){
				if(inventory.get(i) instanceof Key) {
					Key k = (Key) inventory.get(i);
					String roomName1 = k.getRoomName1();
					String roomName2 = k.getRoomName2();
					if(currentRoom.getName().equals(roomName1)){
						currentRoom.unlock(k.getDirection1());
						returnStat = "You unlocked the way " + k.getDirection1();
						inventory.remove(i);
					} else if(currentRoom.getName().equals(roomName2)) {
						currentRoom.unlock(k.getDirection2());
						returnStat = "You unlocked the way " + k.getDirection2();
						inventory.remove(i);
					} else {
						returnStat = "You can't unlock anything in here";
					}
					
				}else if (inventory.get(i) instanceof Tool) {
					if (item.equals("musket")) {
						
						musketFunction();
						returnStat = "You used the musket";
					} else if (item.equals("gunpowder")) {
						gunpowderFunction();
						returnStat = "You used the gunpowder";
					} else if (item.equals("musketball")) {
						musketballFunction();
						returnStat = "You used the musketball";
					} else if (item.equals("vine")) {
						vineFunction();
						returnStat = "You crack the vine whip";
					} else if (item.equals("torture device")) {
						returnStat = tortureFunction();
					} else {
						returnStat = "That's not an item that can be used here";
				}
					
				}
			}
		
			
		}
		return returnStat;
	}
	
	
/**
 * help method
 * Method that returns all of the commands and what they do
 * 
 * example return output:
 * "Command      Example      Description
 *  take         take apple   used to pick up objects"
 * 
 * @return a String that contains all the command names, an example of use, and a description of what they do.  Be sure to consider readability of the player.  Do not use print or println.
 */
		
	public static String help(){
		return "The following commands are used to play the game\n"
				+ "Command    Example      Description\n"
				+ "North      North        Allows you to move North\n"
				+ "South      south        Allows you to move South\n"
				+ "East       east         allows you to move East\n"
				+ "West       west         allows you to move West\n"
				+ "Look       Look         provides a detailed descriptoin of the room\n"
				+ "Take       take key     adds an item in a room to your inventory\n"
				+ "Drop       drop key     removes an item from your inventory and leaves it in the room\n"
				+ "Inventory  inventory    displays the names of items in your inventory\n"
				+ "Examine    examine key  provides a detailed description of the specified item, it must be in your inventory\n"
				+ "Eat        eat apple    Allows you to eat an edible item\n"
				+ "Use        use key      Allows you to use the item\n"
				+ "Help       help         displays the help menu\n"
				+ "Quit       quit         player initiated end of game\n"
				+ "Map        map          prints out the map's layout\n";
	}

	
/**
 * quit Method
 * Method that states "You have decided to quit the game"
 * @return a message saying You have decided to quit the game, do not use print or println
 */
	
	public static String quit(){
		return "You have decided to quit the game.";
	}
	
/**
 * invalid method
 * Method called when a player tries a command that is not used in the game
 * @return message "I do not understand", do not use print or println
 */
	
	public static String invalid(){
		return "I do not understand.";
	}
	
/**
 * executeCommand method
 * This method uses the first word to call the appropriate method that you
 * made above.  Basically, this method manages complexity by hosting the 
 * logic to detemine which method should be used.
 * (for example, if the command is north, call the north() method). 
 * @param the String command used to check which method to call
 * @param the String item used for methods that interact with objects 
 * @return a String that is passed back from the appropriate method, 
 *         do not use print or println
 */
		
	public static String executeCommand(String command, String item){
		if(command.equals("north"))
			return north();
		if(command.equals("south"))
			return south();
		if(command.equals("east"))
			return east();
		if(command.equals("west"))
			return west();
		if(command.equals("inventory"))
			return inventory();
		if(command.equals("look"))
			return look();
		if(command.equals("take"))
			return take(item);
		if(command.equals("drop"))
			return drop(item);
		if(command.equals("examine"))
			return examine(item);
		if(command.equals("eat"))
			return eat(item);
		if(command.equals("use"))
			return use(item);
		if(command.equals("help"))
			return help();
		if(command.equals("quit"))
			return quit();
		if(command.equals("map"))
			return printMap();
		return invalid();
				
	}
	

/**  
 * initializeMap method
 * Method to create the amp for your game
 * 
 * this method initializes the 2D array
 * then it constructs all of the Rooms found in your world. 
 * be sure they match the correct locations of you map and have the appropriate 
 * passages set to true and false
 * Any spot in your map that is not used should be built with the default constructor
 * 
 * HINT: The upper left is row 0 and column 0
 * no parameters or return values
 */
/*
 * TODO: checkpoint 4 Tasks
 * 
 * make the initializeMap method
 * 
 */	
	
	public static void initializeMap() {
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.add("Open Grand Hall");
		keyList.add("Old Key Room");
		ArrayList<String> keyDirectList = new ArrayList<String>();
		keyDirectList.add("north");
		keyDirectList.add("south");
		ArrayList<String> dynamiteRoom = new ArrayList<String>();
		dynamiteRoom.add("Open Grand Hall");
		dynamiteRoom.add("Temple Entrance");
		ArrayList<String> dynamiteDirection = new ArrayList<String>();
		dynamiteDirection.add("north");
		dynamiteDirection.add("north");
		InventoryItem dynamite = new Key("dynamite", "Maybe with a little gunpower, this can be used to blast through that large door in the Grand Hall", dynamiteRoom, dynamiteDirection);
		InventoryItem sign = new InventoryItem("sign", "Has some sort of tribal writing on it pointing toward a little drawing of a tiger, probably not meaning anything. The sign is oddly sturdy though", 10);
		InventoryItem key = new Key("key", "This fits perfectly into the keyhole before you, but also looks like it might unlock that big door in the Grand Hall", keyList, keyDirectList);
		InventoryItem treasure = new InventoryItem("treasure", "A bunch of gold coins and gems and amulets and more! This could be super valuable", 40);
		InventoryItem voodoo = new InventoryItem("voodoo doll", "You aren't really sure why you grabbed this, but when you grip it you feel like \nyou're being held, so that's nice", .5);
		InventoryItem vine = new Tool("vine", "This is a long vine that you could use as a whip!", 5, "Tiger Room");
		InventoryItem tortureDevice = new Tool("torture device", "It's something that can fit over your head, you shouldn't use it, unless you persist I guess.", 15, "Torture Chamber");
		
		
		
		
		map = new Room[11][8];
		map[7][3] = new Room(false, false, false, false, "Exit of the Temple", "You've made it to the exit of the temple, \nand glorious riches lie before you!", treasure);
		map[8][2] = new Room(false, true, true, false, "Warning Room", "Small room with warning signs scattered about pointing to another room south.", sign);
		map[8][3] = new Room(false, false, true, true, "Open Grand Hall", "An open room with stairs leading up towards the exit, a grand bronze door with a keyhole in the center. \nThere also seems to be rust forming around the doorframe.");
		map[8][4] = new Room(false, false, true, true, "Hallway", "A long hallway that leads either left or right throughout the whole temple.");
		map[8][5] = new Room(true, true, true, true, "Cracked Room", "A tiny room that is crumbled and dirty with hallways leading in all directions, \nincluding a crack in the south wall you could squeeze through.");
		map[9][2] = new Room(true, false, false, false, "Tiger Room", "A hungry tiger starts to approach you with hatred in its eyes. It looks like it might be guarding something.", dynamite);
		map[9][4] = new Room(true, false, false, false, "Temple Entrance", "A small dark room with only one path ahead: forward.");
		map[9][5] = new Room(true, false, false, false, "Key Room", "Room with a pedestal in the center that has a golden key upon it. A stream of light is peeking \nthrough a hole in the roof, revealing a locked door to the south.", key);
		map[10][5] = new Room(false, false, false, false, "Pit Room", "You unlock the door and slowly step into the pitch black room, but your foot doesn't hit the ground and you fall into a pit filled with spikes.");
		map [7][5] = new Room(false, true, true, false, "Effigy Room", "The room is completely covered in these weird dolls. One of them kind of looks like you!", voodoo);
		map [7][6] = new Room(false, false, false, true, "Morgue", "The room is supposed to be a morgue, but its completely empty of anything \nalive or dead, except a dead end that is.");
		map [8][6] = new Room(false, false, true, true, "Overgrown Room", "This room is completely covered in plantlife. One of the vines looks like it could be a cool weapon!", vine);
		map [8][7] = new Room(false, true, false, true, "Open Air Room", "This room's roof is completely gone. The wall's too high to climb out. That didn't \nstop you from trying and falling, though.");
		map [9][7] = new Room(true, false, false, true, "Torture Chamber", "There's a devilish looking device in the middle of the room, that you really shouldn't take.", tortureDevice);
		map [9][6] = new Room(false, false, true, false, "Altar Room", "This room looks like that it once held sacrifices, but it's empty now.");
		
	}
	
	
	
	
	
/**  
 * updateRoom method
 * Method to update the room you are in (currentRoom)
 * this method uses the row and column values to 
 * update the currentRoom location
 * no parameters or return values
 */
/*
 * TODO: checkpoint 4 Task
 * 
 * make the updateRoom method
 */
	public static void updateRoom() {
		currentRoom = map[row][column];
	}
	
	
	
			
/**
 * 	getCurrentRoom method
 *  In order to work with the JFrame, we need a way to get the room
 *  create an accessor method that returns the currentRoom called getCurrentRoom
 *  @return the current room, but sure to update the room first
 */

/* 
 * TODO: checkpoint 4 Task
 * 
 * make the getCurrentRoom method
 */
	public static Room getCurrentRoom() {
		updateRoom();
		return currentRoom;
	}
	
	public static String printMap() {
		String printMap = "";
		for(int row = 0; row < 10; row++) {
			for(int column = 0; column < 8; column++) {
				if(map[row][column] != null) {
					printMap += map[row][column].getName();
					printMap = printMap + "        ";
				} else {
					printMap = printMap + "                  ";
				}
			}
			printMap = printMap + "\n";
		}
		return printMap;
	}
	public static Boolean hasItem(String item) {
		boolean hasOrNot = false;
		for (InventoryItem thing : inventory) {
			if(thing.getName().equals(item)) {
				hasOrNot = true;
			}
		}
		return hasOrNot;
	}
	public static InventoryItem getItem(String item) {
		InventoryItem searchItem = null;
		for (InventoryItem thing : inventory) {
			if(thing.getName().equals(item)) {
				searchItem = thing;
			}
		}
		return searchItem;
	}
	
	public static String musketFunction() {
		playerAction = "musket";
		return "You used the musket";
	}
	
	public static String gunpowderFunction() {
		playerAction = "gunpowder";
		if(hasItem("gunpowder")) {
			for(int i = 0; i < inventory.size(); i++) {
				if(inventory.get(i).getName().equals("gunpowder")) {
					inventory.remove(i);
				}
			}
		}
		return "You used the gunpowder";
	}
	
	public static String musketballFunction() {
		playerAction = "musketball";
		if(hasItem("musketball")) {
			for(int i = 0; i < inventory.size(); i++) {
				if(inventory.get(i).getName().equals("musketball")) {
					inventory.remove(i);
				}
			}
		}
		return "You used the gunpowder";
	}
	
	public static String vineFunction() {
		playerAction = "vine";
		return "You fling the vine like a whip and it even makes the crack sound on the ground. Sick!";
	}
	
	public static String tortureFunction() {
		String returnStat = "";
		if (tortureAttempts == 1) {
			returnStat = "Wait, you actually are trying this? I really wouldn't, don't";
			tortureAttempts++;
		} else if (tortureAttempts == 2) {
			returnStat = "Uhh.. if you insist. You put the device over your head. Last chance to back out!";
			tortureAttempts++;
		} else if (tortureAttempts == 3) {
			System.out.println("You activate the device, and yeah, it kills you. Surprising");
			tortureAttempts++;
		}
		return returnStat;
	}

	
	public static void tigerRoom() {
		inTigerRoom = true;
		currentRoom.lock("north");
		System.out.println("You've stepped into the room, where bodies and spears lie at your feet, \nand a ravenous tiger is growling at you. \nLoad your musket using the correct items in the correct order or \nsome other weapon within 3 turns and take it out, or die.");
		
		if(turnCount <= 3) {
			if(playerAction.equals("musket")) {
				playerCombo.add(playerAction);
				turnCount++;
				playerAction = "";
			} else if (playerAction.equals("gunpowder")){
				playerCombo.add(playerAction);
				turnCount++;
				playerAction = "";
			} else if (playerAction.equals("musketball")) {
				playerCombo.add(playerAction);
				turnCount++;
				playerAction = "";
			} else if (playerAction.equals("vine")) {
				playerCombo.add(playerAction);
				turnCount++;
				playerAction = "";
			}
		}
		if ((turnCount > 3)) {
			if (playerCombo.equals(correctCombo) || playerCombo.equals(vineCombo)) {
				survivedTiger = true;
				tigerDead = true;
			} else {
				survivedTiger = false;
			}
		}
	}
	
	


	
	//DO NOT TOUCH
	public static void main(String[] args){
		runGame();
	}
}

