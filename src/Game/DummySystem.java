package Game;
import java.util.*;
public class DummySystem implements Controller{
	//Cai nay de tam nham thu GUI thoi, se thay bang Core Class sau.
	public void setPlayerName(String playerName){};
	static String narration;
	//static String
	public void startGame(){
		narration = ">>>>Game has started.<<<<";
		}
	public void endGame(){
		narration = ">>>>Game has ended.<<<<";
		};
	public String[] getCurrentRoomsInfo(){
		String[] testoutput = {
//ID,NAME
			"Room@123,A pink musty Room"
			};
		return testoutput;
		};
	public String[] getPlayersInfo(){
		String[] testoutput = {
//ID,NAME,HP_BASE/HP_CURRENT,HP_BASE/HP_CURRENT,DEF_BASE/DEF_CURRENT,COIN
			"Player@0f1,Dave,3/20,5/5,2/0,99"
			};
		return testoutput;
		};
	public String[] getNPCsInfo(){
		String[] testoutput = {
//ID,NAME,HP_BASE/HP_CURRENT,HP_BASE/HP_CURRENT,DEF_BASE/DEF_CURRENT,COIN,AGRESSION
			"NPC@100,Monster A,13/30,2/2,1/1,10,9",
			"NPC@100,Monster A,13/30,2/2,1/0,10,10",
			"NPC@101,Monster B,21/30,1/1,10/3,10,0"
			};
		return testoutput;
		}
	public String[] getInventorysInfo(){
		//_HP=0,_ATK=1,_DEF=2;
		String[] testoutput = {
//ID,NAME,TYPE,ADD_AMOUNT
			"Equipment@1,Stick A,1,3",
			"Equipment@2,Shield A,2,5",
			"Equipment@3,Lyfe stick B,0,21"
			};
		return testoutput;
		};
	public String[] getFeaturesInfo(){
		String[] testoutput = {
//ID,NAME
			"Teleporter@392,Blue Teleport Pad",
			"Chest@091,Empty chest"
			};
		return testoutput;
		};
	public void select(String id){
		narration = "  * You have chosen to do something with " + id;
//
		};
	public String getNarration(){
		return narration;
		};
	public boolean isOver(){return true;};
	public void save(String object_ID){};
	public void load(String object_ID){};
//
	}
