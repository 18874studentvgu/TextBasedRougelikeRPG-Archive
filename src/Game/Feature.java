package Game;

abstract class Feature implements Const{
	String name;
	Feature(String name){
		this.name=name;
		}
	public String getInfo(){
		return this.toString() + "," + name;
		}
	abstract void onInteractBy(Chara caller);
	}
