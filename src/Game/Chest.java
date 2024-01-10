package Game;
public class Chest extends Feature{
	Equipment equipment;
	//boolean isOpen = false;
	String name_old; //save the old name

	public Chest(String name,Equipment equipment){
		super(name+" - Contains [" + equipment.name + "]");
		this.equipment = equipment;
		this.name_old = name;
		}


	public void onInteractBy(Chara caller){
		//annouce this somehow (maybe via a method in game??)
		//isOpen = false;
		if(equipment == null){
			if(caller.equipment != null){
				this.equipment = caller.unequip();
				//place the currently used Equipment in the chest
				Core.narrate(caller.name + " place their Equipment inside the chest!");
				name = name_old + " - Contains [" + equipment.name+"]";
				}
			return;
			} //if nothing is in chest, done
		if(caller.getClass().equals(NPC.class)){//caller is an NPC
			//take chest content in xchange for their current equipment
			Equipment etemp = caller.unequip();
			caller.equip(equipment);
			equipment=etemp;
			Core.narrate(caller.name + " exchanged their Item with the one in the chest!");
			//should be OK even if etemp==null, (maybe?)
			if(etemp!=null)
				name = name_old + " - Contains [" + equipment.name+"]";

			}
			else if (caller.getClass().equals(Player.class)) {
				((Player)caller).inventory.add(equipment);

				equipment=null;
				name = name_old;
				}
		}
	// public Equipment loot(){
	// 	if(!isOpen){
	// 		//annouce that player has not open the chest
	// 		//Core.annouce(caller.name + " tried to take from an empty chest.");
	// 		return null;
	// 		}
	// 	Equipment e = equipment;//make a refference to the equipment
	// 	equipment=null; //remove refference from chest
	// 	name=name_old;
	// 	return e;
	// 	}
	// public void store(Equipment equipment){
	// 	if(isOpen){
	// 		//annouce that player has not open the chest
	// 		return;
	// 		} else if(this.equipment != null) {
	// 			//annouce that there's already an Item inside
	// 			return;
	// 			} else {
	// 				this.equipment=equipment;
 //
	// 				}
 //
		// }
	}
