package Game;
import java.util.*;


public class Core extends Thread implements Controller{
	boolean hasStarted;
	static boolean isOver;
	static volatile String narration="";
	// static volatile Dungeon dungeon;
	public static volatile Dungeon dungeon;

	public Core(){};
	// :(
	static <T> T searchID(ArrayList<T> array, String id){
	//Generic method to be use anywhere.
		for(T element:array){
			// System.out.println("DEBUG: SEARCH: " + element.toString() + " vs " + id);
			if(element.toString().equals(id))
				return element;
			}
		//if ID not found
		return null;
		}
	static void narrate(String event){
		//add a line to the narration
		//use for annoucing an action by a
		//Character
		narration += " * " + event + "\n";
		}
	public String getNarration(){
		String temp = narration;
		narration = "";
		return temp;
		}
	private <T extends Const> String[] compileInfo(ArrayList<T> array){

		//objects.forEach();
		//NOTE: if 0 element is in array, may return string array length 0, BEWARE!
		String []info = new String[array.size()];
		array.forEach( (element) -> info[array.indexOf(element)]=element.getInfo() );
		return info;
		}
	public void setPlayerName(String playerName){
		/**Set the name of the player (Will create one if player or dungeon not exist)*/
		if(Core.dungeon == null || Core.dungeon.player == null){//idk how but it might happended?
			Core.dungeon = new Dungeon(playerName);
			} else {
				Core.dungeon.player.name = playerName;
				}
		}
	public String[] getCurrentRoomsInfo(){
		String[] info = { dungeon.currentRoom.getInfo() };
		return info;
		}
	public String[] getPlayersInfo(){
		String equipmentInfo = "0,0,0,0";
		if(dungeon.player.equipment!=null){
			equipmentInfo = dungeon.player.equipment.getInfo();
			}
		String[] info = {
			dungeon.player.getInfo(),
			equipmentInfo //thank god this was implement as an array
			};
		return info;
		};
	public String[] getInventorysInfo(){
		return dungeon.player.inventory.getInfo();
		};
	public String[] getNPCsInfo(){
		String[] info = compileInfo(dungeon.currentRoom.getCharacters());
		return info;
		};
	public String[] getFeaturesInfo(){
		String[] info = compileInfo(dungeon.currentRoom.getFeatures());
		return info;
		};
	public void select(String id){
		/* Take in an ID of a object and cleverly find and handle said oject

		 An ID of a java object typically looks like this:
		 <object's Class>@<object's Hash>
		 We first Check too see what class this ID belongs to, then look up the
		 object using Core.searchID();

		*/
		String temp = "";
		try{
			temp = id.split("@")[0];
			temp=temp.replace("Game.","");
			} catch(Exception e) {
			// System.out.println(e+"DEBUG: Core.select(): id not properly formated?");
			}
		// System.out.println("DEBUG: Class==" + temp);
		switch(temp){
			case "NPC":
				ArrayList<Chara> charList = Core.dungeon.currentRoom.getCharacters();
				// NPC npc = Core.searchID(charList,id);
				Core.dungeon.player.attack(Core.searchID(charList,id));
				//for some reason,
				//Core.searchID(Core.dungeon.currentRoom.getCharacters(),id) gives an error
				break;
			case "Chest":
			case "Teleporter":
				Feature feat = Core.searchID(Core.dungeon.currentRoom.getFeatures(),id);
				Core.dungeon.player.interact(feat);
				break;
			case "Equipment":
				Equipment eq = Core.dungeon.player.inventory.take(id);
				Core.dungeon.player.equip(eq);
				break;
			case "Unequip":
			case "unequip":
				Core.dungeon.player.unequip();
				break;

			default:
				// System.out.println("**DEBUG: cannot locate object "+id);
				break;

			}
		synchronized(this){
			// System.out.println("DEBUG: Core.select(): State: "+this.getState());
			if(this.getState().equals(Thread.State.WAITING)){
				this.notify(); //start the next itteration of the run() loop;
				return;
				}
			// System.out.println("DEBUG: Core.select(): State not WAITING, do nothing.");
			}
		}
	//TODO QUICK!!
	public void setDungeon(Dungeon dungeon){
		Core.dungeon = dungeon;
		}

	public void run(){
		//the main game loop, running as a thread
		try{
			do{
				synchronized(this){
					// wait until player do action
					// System.out.println("DEBUG: Core.run(): State: "+this.getState());
					try{
						wait();
						} catch(InterruptedException e) {
							// System.out.println(e + "DEBUG: Loop has been broken, player quit the game?");
							isOver=true;
							break;
							}
					}
				//get NPCs of currentRoom to to their actions
				(Core.dungeon.currentRoom.getCharacters()).forEach( (x) -> x.doAction());
				}while(!isOver);
			} catch (Exception e){
			e.printStackTrace();
			isOver=true;//Error, stop the game
			return;
			}

		}
	public void startGame(){
		//preliminay system checks
		if(Core.dungeon == null){//has not been initialized
			//make a new dungeon with a gerneric name
			this.setPlayerName("Player");
			}
		//NOTE: see if this alone isenough

		//atempt to start thread for the run function
		isOver=false;
		synchronized(this){
			this.start();
			// System.out.println("DEBUG: Core.startGame(): State is now: "+this.getState());
			}

		};
	public void endGame(){
		this.interrupt();
		}
	public boolean isOver(){
		//check if game has ended
		return isOver;
		}

	public void save(String savefile_ID){
		/**Do nothing at the moment (this was optional..right?)*/
		};
	public void load(String savefile_ID){
		/**Do nothing at the moment (this was optional..right?)*/
		};

	}
