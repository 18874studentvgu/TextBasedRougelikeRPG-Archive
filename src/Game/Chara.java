package Game;

public class Chara implements Const{

	protected class Stat{
	/*private to prevent "base" var from being change*/
		private int base;
		private int current;
		Stat(){}
		Stat(int base){
			this.base=this.current=base;
			}
		//protected: unaccessable from outside packages
		protected int getCurrent(){
			return current;
			}
		protected int getBase(){
			return base;
			}
		protected void changeBy(int amount){
			current += amount;
			}
		protected void reset(){
			current = base;
			}
		}

	String name;
	int coin;
	Stat HP;
	Stat ATK;
	Stat DEF;
	Equipment equipment;

	Chara(){};
	public Chara(String name,int hp,int atk, int def, int coin){
		this.name=name;
		this.coin=coin;
		HP=new Stat(hp);
		ATK=new Stat(atk);
		DEF=new Stat(def);
		equipment=new Equipment("The Stick o' life\n HP+5/equip",0,5);
		//default equipment
		}
	public Chara(String name,int hp,int atk, int def, int coin, Equipment equipment){
		this.name=name;
		this.coin=coin;
		HP=new Stat(hp);
		ATK=new Stat(atk);
		DEF=new Stat(def);
		// equip(equipment);
		this.equipment=equipment;
		equipment.onEquipBy(this);
		}

	public String getInfo(){
		return this.toString() + "," + name +","+ HP.getCurrent() + "/" + HP.getBase() + "," +
		   ATK.getCurrent() + "/" + ATK.getBase() + "," + DEF.getCurrent() + "/" + DEF.getBase() + "," + coin;
		   //to be overrided by NPC and Player
		}
	public void attack(Chara target){
		if(target == null){
			System.out.println("DEBUG: Attack: target == null");
			return;//exit
			}
		int damage = this.ATK.getCurrent() - target.DEF.getCurrent() ;
		//if DEF is larger than ATK (a.k.a. damage), ememy is damaged out of pity
		damage = (damage > 0) ? damage : 1;
		target.HP.changeBy( -damage );

		//narrate this
		Core.narrate(this.name + " attacked " + target.name +"!");


		//TODO check to see if this is enough
		if(target.HP.getCurrent() < 1){
			//Death
			this.coin += target.coin;
			Core.narrate(target.name +" has been slayed!");
			if(target.getClass() == NPC.class){
				Core.dungeon.remove(target);
				} else
				if(target.getClass() == Player.class){
					Core.isOver=true;
					//TODO: see if enough
					}
			}
		}
	public void equip(Equipment e){
		equipment = e;
		equipment.onEquipBy(this);
		Core.narrate("??? equips [" + equipment.name + "]");
		return;
		}
	public Equipment unequip(){
		Equipment e = this.equipment;
		ATK.reset();
		DEF.reset();
		this.equipment = null;
		Core.narrate("??? has unequip their [" + e.name + "]");
		return e;
		}
	public void interact(Feature feat){
		if(feat == null){
			System.out.println("DEBUG: Feature: feat== null");
			return;//exit
			}
		feat.onInteractBy(this);

		}
	public /*abstract*/ void doAction(){};

	}
