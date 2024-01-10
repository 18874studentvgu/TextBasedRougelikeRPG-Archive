package Game;

import java.lang.Math;
import java.util.ArrayList;

public class NPC extends Chara {
	protected int aggression;

	public NPC(String name, int hp, int atk, int def, int coin, int aggression, Equipment equipment) {
		super(name, hp, atk, def, coin, equipment);
		this.aggression = aggression;
	}
	public String getInfo() {
		return super.getInfo() +","+ aggression + "";
	}

	public void update_aggression(int bin/*only 1 or -1 */){
		aggression += bin;
		while(aggression > 1){
			aggression -= 1;
		}
		while(aggression < 1){
			aggression += 1;
		}
	}
	public void doAction() {

		int ran = (int) (Math.random() * 100);
		ArrayList<Integer> choice = new ArrayList<Integer>();

		// Those variable store % for action:
		int not_doing_anything = 0;
		int interact = 0;
		int actack = 0;

		// Set % based on aggesion
		switch (aggression) {
			case -1:
				not_doing_anything = 100;
				break;
			case 0:
				not_doing_anything = 50;
				interact = 25;
				actack = 25;
				break;
			case 1:
				interact = 50;
				actack = 50;
				break;
		}
		while (choice.size() <= 100 & not_doing_anything != 0 | interact != 0 | actack != 0) {
			if (not_doing_anything > 0) {
				choice.add(-1);
				not_doing_anything -= 1;
			}
			if (interact > 0) {
				choice.add(0);
				interact -= 1;
			}
			if (actack > 0) {
				choice.add(1);
				actack -= 1;
			}
		}

		// Choose and Do action
		switch (choice.get(ran)) {
			default:
				// not doing anything
				Core.narrate(this.name + " just stand there, menacingly");
				break;
			case 0:
				// interact
				Room r = Core.dungeon.getCurrentRoom();
				ArrayList<Feature> featList = r.getFeatures();
				int Feat_ran = (int) (Math.random() * featList.size());
				this.interact(featList.get(Feat_ran));
				break;
			case 1:
				// actack
				this.attack(Core.dungeon.player);
				break;

		}
	}

	// public void interact(Feature feat) {
	// 	super.interact(feat);
		//NOTE: has been handled by Chest.interact method

		// if (feat.getClass() == Chest.class) {
		// 	// take from chest and put into inventory
		// 	Equipment e = ((Chest) feat).loot();
		// 	if (e != null) {
		// 		if (this.equipment != null) {
		// 			// replace with the equipment they are holding
		// 			Equipment e1 = this.unequip();
		// 			((Chest) feat).store(e1);
		// 		}
		// 		this.equip(e);
		// 	}
	// 	}
	// }

}
