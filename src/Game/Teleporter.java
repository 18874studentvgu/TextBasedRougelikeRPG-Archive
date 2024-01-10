package Game;
public class Teleporter extends Feature{
	Room destination;
	public Teleporter(Room destination){
		super("[Teleporter]");
		this.destination=destination;
		}
	public Teleporter(String name,Room destination){
		super(/*"[Teleporter] " +*/ name);
		this.destination=destination;
		}

	public void onInteractBy(Chara caller){
		Core.narrate(caller.name + " has teleported to " + destination.name);
		if(caller.getClass().toString().equals(NPC.class)){
			destination.add(caller);
			Core.dungeon.currentRoom.remove(caller);
			} else
		if(caller.getClass().equals(Player.class)){
			Core.dungeon.setCurrentRoom(destination);
			}
		}
	}
