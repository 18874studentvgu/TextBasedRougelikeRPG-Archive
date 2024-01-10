import Game.*;
import java.util.*;

public class TextUserInterface{
	static Controller core = new Core();
	static Scanner input = new Scanner(System.in);
	static int lines = 0;//keep track of the number of lines to be deleted by clearLines()
	static void clearLines(int noOfPreviousLines){
		System.out.print("\033["+noOfPreviousLines+"F"+"\033[0J");
		}
	static boolean useEquipmentFromInventory(){
		/**Returns true if the player has chosen an Equipment*/
		int lines_ = 0; //keep track of lines made inside this function
		String[] inventoryData = core.getInventorysInfo();
		String[][] inventoryArray = new String[inventoryData.length][];

		lines_+=2;
		System.out.println("  You look inside your Inventory. You can: ");

		for(int i=0;i<inventoryData.length;i++){
			inventoryArray[i] = inventoryData[i].split(",");

			switch(Integer.valueOf(inventoryArray[i][2])){//Convert Equipment type (0=HP;1=ATK;2=DEF)
				case 0:
					inventoryArray[i][2] = "HP";
					break;
				case 1:
					inventoryArray[i][2] = "ATK";
					break;
				case 2:
					inventoryArray[i][2] = "DEF";
					break;
				}
			System.out.println("   --> ("+ (i) +") Equip " + inventoryArray[i][1]+
					" | Type: "+inventoryArray[i][2]+" | Type: "+inventoryArray[i][3]);
			}
		int count = inventoryData.length; //lucky coinsidence
		System.out.println("   --> ("+ (count) +") ***Close Your Inventory***"); //last choice
		//get player's choice
		int choice_ =-1;
		while(choice_<0 || choice_ > count){
			System.out.print(choice_ + "  Enter your Choice (0~" +count+ "): ");
			try{
				choice_ = Integer.valueOf(input.nextLine());
				lines_++;
			} catch(NumberFormatException nfe){
				continue;
				}
			}
		lines_+=count;
		//handle choices
		if(choice_<inventoryData.length){
			//player has Chosen to equip 1 of the equipments
			core.select(inventoryArray[choice_][0]);
			lines += lines_;//remember, var lines are meant to keep track of lines written
			return true;
			} else if(choice_==inventoryData.length){
				//player has Chosen to close the inventory
				//clear lines upto when the inventory was open
				clearLines(lines_);
				}
		return false;
		}
	// static String praseCharaInfo(String)
	// String getPlayerAction(){}
	public static void main(String[] args){
		boolean notDone = false;
		lines = 0;


		System.out.print("Name, NOW: \n> ");
		String playerName = input.nextLine();
		core.setPlayerName(playerName);

		//start
		System.out.print("Game, NOW:");
		core.startGame();
		//clear screen
		System.out.print("\033[H\033[J");
		System.out.println(">>>>>>>>>>>>NARRATION<<<<<<<<<<<");

		//main game loop
		do{
			lines = 0;
			System.out.print(core.getNarration());
			String[] player = core.getPlayersInfo();
			String[] NPCs = core.getNPCsInfo();
			String[] Feats = core.getFeaturesInfo();



			String[] playerInfo =  player[0].split(",");
			String[] currentEquipment =  player[1].split(",");
			// System.out.println("Debug: currentEquipment[0] is " + currentEquipment[0]);
			if(currentEquipment.length>0){
				switch(Integer.valueOf(currentEquipment[2])){//Convert Equipment type (0=HP;1=ATK;2=DEF)
					case 0:
						currentEquipment[2] = "HP";
						break;
					case 1:
						currentEquipment[2] = "ATK";
						break;
					case 2:
						currentEquipment[2] = "DEF";
						break;
					default:
						break;
					}
				}
			System.out.println(">>>>>>>>>>>>NARRATION<<<<<<<<<<<");
			System.out.println("\n    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("  | You are: " + playerInfo[1]+
					 " | HP: "+playerInfo[2]+" | ATK: "+playerInfo[3]+" | DEF: "+playerInfo[4]+" | Coins: "+playerInfo[5]);
			System.out.println("  | Current Equipment: " + currentEquipment[1]+
					" | Type: "+currentEquipment[2]+" | Amount: "+currentEquipment[3]);
			System.out.println("    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(" Your options are: ");
			lines+=6;

			int count = 0;

			lines += NPCs.length;
			String[][] NPCsArray = new String[NPCs.length][];
			for(int i=0; i<NPCs.length;i++){
				NPCsArray[i]=NPCs[i].split(",");
				//print info as well
				System.out.println(" --> ("+ (count++) +") Attack "+NPCsArray[i][1]+
					" | HP: "+NPCsArray[i][2]+" | ATK: "+NPCsArray[i][3]+" | DEF: "+NPCsArray[i][4]+" | Coins: "+NPCsArray[i][5]);
				}


			lines += Feats.length;
			String[][] FeatsArray = new String[Feats.length][];
			for(int i=0; i<Feats.length;i++){
				FeatsArray[i]=Feats[i].split(",");
				//print info as well
				System.out.println(" --> ("+ (count++) +") Interact with: "+FeatsArray[i][1]);
				}

			lines+=3;
			System.out.println(" --> ("+ (count++) +") ***Open Your Inventory***");
			System.out.println(" --> ("+ (count) +") >>>Stop Playing<<<");//last option
			while(true){//loop till player made their choice
				int choice=-1;
				while(choice<0 || choice > count){
					System.out.print("Enter your Choice (0~" +count+ "): ");
					try{
						choice = Integer.valueOf(input.nextLine());
						lines++;
					} catch(NumberFormatException nfe){
						continue;
						}
					}
				//handle the choice:
				if(choice<NPCs.length){
					// player has chosen to attck NPC
					core.select(NPCsArray[choice][0]);//sending core the object id
					break;//break the while loop
					} else if(choice<(NPCs.length+Feats.length)){
						// player has chosen to interact with featire
						core.select(FeatsArray[choice-NPCs.length][0]);//sending core the object id
						break;//break the while loop
						}
						else if(choice == (NPCs.length+Feats.length)){
							// player has chosen to open their inventory with featire
							boolean hasEquipOne = useEquipmentFromInventory();
							if(hasEquipOne)
								break;//break the while loop
								else continue;//Take the player back to the previous choices
							}
							else{
								// player has chosen to stop their game
								core.endGame();
								break;
								}

				}

			// notDone=false;
			clearLines(lines);//clear lines up to the last narration
			} while (!core.isOver());
			System.out.println(">>>>>>>>>>>>>>>>>>>>GAME OVER<<<<<<<<<<<<<<<<<<<<");
			String playerCoin = (core.getPlayersInfo())[0].split(",")[5];
			int pCoin = Integer.valueOf(playerCoin);
			if(pCoin<0){
				System.out.println("After you conquest, you are " + -pCoin + " Coins in dept. Better luck next time.");
				} else if(pCoin<0){
					System.out.println("After you conquest, you have only just managed to break even. It could be worst...");
					} else
						System.out.println("After you conquest, you\'ve gained: " + pCoin + " Coins. Good job!");


			System.out.println("Thank for playing our game. Have a nice day!");











		}
	}
