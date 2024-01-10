package Game;
import java.util.Random;

class RandomGeneration implements Const{
	Random random = new Random();
	String[][] EquipmentNames = {
								  {"Styk af Lieph","Gem Stone"}, //_HP type (0)
								  {"Stoine ef Strenph","gun.","Stab Stab Styk"}, //_ATK type (1)
								  {"Shield","Youegh Muomua","Armour","Metal Plates"} //_DEF type (2)
								};
	String[] NPCNames = {"Monster","Slime","duck","Skeleton","Redditor","C\'thyrdgir The Destroyer of Dreams"};

	String[] observationAdjectives = {"","Dusty ","Delicious ","Gogeous "};
	String[] sizeAdjectives = {"","Tiny ","Small ","Medium ","Chonky "};
	String[] ageAdjectives = {"","New ","Old ","Anticque "};
	String[] shapeAdjectives = {"","Round ","Square "};
	String[] colorAdjectives = {"","Dark ","Crimson ","yello ","Teal ","Pink UwU ","Maroon "};

	String newAdjective(){
		return observationAdjectives[random.nextInt(observationAdjectives.length)]
				+ sizeAdjectives[random.nextInt(sizeAdjectives.length)]
				+ ageAdjectives[random.nextInt(ageAdjectives.length)]
				+ shapeAdjectives[random.nextInt(shapeAdjectives.length)]
				+ colorAdjectives[random.nextInt(colorAdjectives.length)];
		}

	Equipment newEquipment(){
		int equipmentType = random.nextInt(3); //Random int [0..2]
		String[] equipmentNameList = EquipmentNames[equipmentType]; //go from 2d array to 1d array
		String equipmentName = this.newAdjective()
								+ equipmentNameList[random.nextInt(equipmentNameList.length)];
		int equipmentAddAmount =  random.nextInt(20)+1; //number in [1..20]
		return new Equipment(equipmentName,equipmentType,equipmentAddAmount);
		}

	NPC newNPC(){
		int HP =  (random.nextInt(4)+1)*10;
		int ATK =  random.nextInt(11);
		int DEF =  random.nextInt(3);
		int coin = HP << DEF;
		int agression = random.nextInt(3) - 1;
		String NPCName = this.newAdjective() + NPCNames[random.nextInt(NPCNames.length)];
		return new NPC(NPCName,HP,ATK,DEF,coin,agression,this.newEquipment());
		}

	Room newRoomFrom(Room originalRoom){
		/**Generate and returns a new room with a random amount of Chests & NPCs.
		 If originalRoom != null said room will be attached to originalRoom via a new teleporter
		 Print a debug message to the console if originalRoom == null
		*/

		String adjectivePrefix = this.newAdjective();
		Room newRoom = new Room(adjectivePrefix + "Room");
		while(random.nextInt(2)!=0){ //NOTE: check to see if this kind of stuff works well (not spawn like 200 NPCs)
			newRoom.add(this.newNPC());
			}
		while(random.nextInt(3)!=0){
			newRoom.add(new Chest("Chest",this.newEquipment()));
			}

		if (originalRoom == null){
			// System.out.println("DEBUG: Game.RandomGeneration.makeNewRoomFrom(): originalRoom == null");
			return newRoom;
			}

		//Link "newRoom" back to "originalRoom" (via a teleporter)
		originalRoom.add(new Teleporter(adjectivePrefix + "Teleporter",newRoom));
		return newRoom;
		}
	int nextInt(int bound){
		/**Passthough so that whatever using this wouldn't have to import Random'*/
		return random.nextInt(bound);
		}
	public String getInfo(){
	/** Example of Bad Code Factoring*/
		return "Dis is What Bad Programing looks like!";
		}
	}
