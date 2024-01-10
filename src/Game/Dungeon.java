package Game;
import java.util.*;
public class Dungeon {
	RandomGeneration randRoom = new RandomGeneration();
	// protected Player player;
	public Player player;
	ArrayList<Room> rooms = new ArrayList<Room>();//Might not needed?
	Room currentRoom;

	boolean getTeleporter(Room room){
		return true;
		}
	Dungeon(String playerName){
		int default_hp=20,
			default_atk=5,
			default_def=3,
			default_coin=-12;
		Equipment default_equipment = new Equipment("The Gerat Sword",1,-1);
		player =  new Player(playerName,default_hp,default_atk,default_def,default_coin,default_equipment);
		Room r = randRoom.newRoomFrom(null);
		rooms.add(r);
		this.setCurrentRoom(r);
		}
	void generateNewRoom(){
		/**Check if currentRoom is connected to any other room
		 (ie has at least one Teleporter). If not, generate a room and a place a
		 teleporter linked to said room in currentRoom.
		 NOTE: see if this alone is enough or do we need to generate for the subsequent rooms as well
		 */
		 Core.narrate("You feel the Dungeon rumbles beneath your feet...");
		 ArrayList<Feature> featlist = currentRoom.getFeatures();
		 ArrayList<Feature> telelist = new ArrayList<Feature>();
		 for(Feature f : featlist){
			//remove anything that isn't a Teleporter
			//Dumb inplementation but featlist shouldn't be too large...
			if(!(f.getClass().equals(Teleporter.class))){
				// System.out.println("DEBUG: Game.Dungeon.generateNewRoom(): remove element class " + f.getClass());
				telelist.remove(f);
				}

			}
		if(telelist.size() < 1){ //no Teleporter?
			// System.out.println("DEBUG: Game.Dungeon.generateNewRoom(): No Teleporter so adding one... ");
			this.rooms.add(randRoom.newRoomFrom(currentRoom));
			//more dumb code, hopefully the over head won't be too bad
			//get last element of featlist (the teleporter that was just added)
			telelist.add(currentRoom.getFeatures().get(currentRoom.getFeatures().size()-1));

			}
			//add a new room for every adjacent room of currentRoom
			//NOTE can be expanded to random amount of rooms in the near future
			for(Feature t:telelist){
				//Try to phrase t as a Teleporter, might get ugly
				try{
					Room newRoom = randRoom.newRoomFrom(((Teleporter)t).destination);
					rooms.add(newRoom);
					} catch (Exception e){
						// System.out.println(e+"\nDEBUG: Game.Dungeon.generateNewRoom(): featlist might not be all Teleporters?");
						}
				}
		}
	public Room getCurrentRoom(){
		return currentRoom;
		}
	public ArrayList<Room> getRooms(){
		return rooms;
		}
	public void add(Room r){
		rooms.add(r);
		}
	public void remove(Chara r){
		currentRoom.remove(r);
		}
	public void setCurrentRoom(Room r){
		this.currentRoom = r;
		this.generateNewRoom();//hopes this works...

		}
	}
