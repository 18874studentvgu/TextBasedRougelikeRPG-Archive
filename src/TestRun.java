// /*
//  * *
//  *
//  *  * */
//
// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import Game.*;
//
// public class TestRun{
// 	static void printStrgArr(String[] a){
// 		for(String s:a){
// 			System.out.println(s);
// 			}
// 		}
//
// 	public static void main (String[] args) {
// 		//NPCs
// 		NPC b[] = { new NPC("big BOB",10,2,4,9,0,new Equipment("Chainsaw",1,2)),
// 					new NPC("golden Duck",1,9,0,9999,-1,new Equipment("Chainsaw",1,2))
// 					};
// 		//Chests
// 		Equipment _c = new Equipment("Da Shield",2,10);
// 		Chest c[] = {   new Chest("Blue chest",new Equipment("The Styk of Life",0,20)),
// 						new Chest("Green chest",_c)
// 						};
// 		// rooms
// 		Room a[] = { new Room("Blue Room"),
// 					 new Room("Red Room"),
// 					 new Room("Green Room")
// 					  };
//
// 		//Telporters
// 		Teleporter d[] = {  new Teleporter("Green tele. pad",a[2]),
// 							new Teleporter("Red tele. pad",a[1])
// 							};
// 		Dungeon e = new Dungeon(a);
//
// 		Controller f = new Core();
//
// 		//putit all together:
// 		a[0].add(d[0]);
// 		a[0].add(b[1]);
// 		a[0].add(c[0]);
// 		a[0].add(c[1]);
//
// 		a[2].add(d[1]);
// 		a[2].add(b[0]);
//
// 		e.add(a[0]);
// 		e.add(a[1]);
// 		e.add(a[2]);
//
// 		e.setCurrentRoom(a[0]);
//
// 		((Core)f).setDungeon(e);
//
// 		Core.dungeon.player = new Player("Samualai",20,10,1,0,new Equipment("The Styk of ded",0,-10));
//
//
// 		String ID_npc[] =  {   b[0].toString(),
// 								b[1].toString()
// 								};
// 		String ID_feat[] =  {   c[0].toString(),
// 								c[1].toString(),
// 								d[0].toString(),
// 								d[1].toString()
// 								};
//
// 		// f.
//
// 		//Player actions
// 		printStrgArr(f.getPlayersInfo());
// 		printStrgArr(f.getCurrentRoomsInfo());
// 		f.select(ID_npc[0]);
// 		f.select(ID_npc[1]);
// 		f.select(ID_npc[1]);
// 		f.select(ID_feat[1]);//open green chest
//
//
// 		printStrgArr(f.getNPCsInfo());
// 		printStrgArr(f.getFeaturesInfo());
// 		printStrgArr(f.getInventorysInfo());
// 		System.out.println("\n\n");
//
// 		f.select(ID_feat[2]);//go to Green room
//
// 		printStrgArr(f.getCurrentRoomsInfo());
// 		printStrgArr(f.getNPCsInfo());
// 		printStrgArr(f.getFeaturesInfo());
// 		printStrgArr(f.getInventorysInfo());
//
// 		f.select(_c.toString());
// 		System.out.println("\n\n");
//
// 		printStrgArr(f.getInventorysInfo());
//
// 		//NPC do action test:
// 		b[0].doAction();
// 		b[0].doAction();
// 		b[0].doAction();
//
// 		String narrr = f.getNarration();
// 		narrr += f.getNarration();
// 		// String b = dummy.getPlayersInfo()[0];
// 		System.out.println("\nhii\n" + narrr);
//
// 		// Controller dummy = new Core();
// 	}
// }
//
