package Game;
import java.util.*;
public class Room {
	String name;
	volatile ArrayList<Chara> charaList = new ArrayList<Chara>(); //maybe help ith the bugs?
	ArrayList<Feature> featList = new ArrayList<Feature>();

	public Room(String name){
		this.name=name;
		}

	public String getInfo(){
		return this.toString() + "," + name;
		}
	public ArrayList<Chara> getCharacters(){
		return charaList;
		}
	public ArrayList<Feature> getFeatures(){
		return featList;
		}
	public void add(Chara a){
		charaList.add(a);
		}
	public void add(Feature a){
		featList.add(a);
		}
	public void remove(Chara a){
		charaList.remove(a);
		System.out.println("Debug: Chara has been remove?" + charaList.indexOf(a));
		}
	public void remove(Feature a){
		featList.remove(a);
		}

	}
