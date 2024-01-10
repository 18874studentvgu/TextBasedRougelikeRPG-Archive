package Game;

public class Equipment implements Const{
	String name;
	int type;
	int addAmount;
	
	Equipment(){}
	
	public Equipment(String name, int type, int addAmount){
		this.name=name;
		this.type=type;
		this.addAmount=addAmount;
		}
	public String getInfo(){
		return this.toString() + "," + name + "," + type+ "," + addAmount;
		}
	public void onEquipBy(Chara caller){//prob useless
		caller.ATK.reset();
		caller.DEF.reset();

		//apply new stat buff
		switch(caller.equipment.type){
			case _HP:
				caller.HP.changeBy(caller.equipment.addAmount);
				break;
			case _ATK:
				caller.ATK.changeBy(caller.equipment.addAmount);
				break;
			case _DEF:
				caller.DEF.changeBy(caller.equipment.addAmount);
				break;
			}
		}
	}
