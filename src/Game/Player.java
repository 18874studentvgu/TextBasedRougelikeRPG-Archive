package Game;
import java.util.ArrayList;

interface Lambda<T>{
	T run(T a);
	}

public class Player extends Chara{
	// abstract interface QrapQueue<E> {
	// 	//a disgusting hybrid of queue and array
	// 	public void add(E e);
	// 	public void remove(int indx);
 //
 //
	// 	}
	// class Queue_AD<T> implements QrapQueue<T> {
	// 	//incomplete interface inplementations
	// 	private T[] array;
	// 	int queue_start;
	// 	int queue_end;
	// 	public final int size;
 //
	// 	Lambda<Integer> indexKeepInRange; //= (x) -> ((size + (x % size)) % size);
	// 		/* keeping the index within the range of "array".
	// 			(initialize later)
	// 		  **((size + (x % size)) % size):
	// 			This is a work around of how java handle the modulus operation.
	// 			it should be that (-1 % 3) = 2 //as -1 = 3*-1 + (2)
	// 			but Java and C, it is (-1 % 3) = -(1 % 3) = -1
	// 		*/
 //
	// 	public int size(){
	// 		return size;
	// 		}
	// 	public Queue_AD(int size){
	// 		this.size = size;
	// 		//this gives a warning
	// 		//unsafe type or something
	// 		array = (T[])new Object[size];
	// 		queue_start=queue_end=0;
	// 		//declare lambda here as "size" is now initialized
	// 		indexKeepInRange = (x) -> ((size + (x % size)) % size);
	// 		}
	// 	public T get(int index){
	// 		if(index > (size - 1) )
	// 			return null;
	// 		return array[(queue_start + index)%(size)];
	// 		}
	// 	public void add(T e){
	// 		int max_indx = size - 1;
	// 		if(queue_start==queue_end && array[queue_end] != null){//has collition
	// 			array[queue_end]=null;
	// 			queue_end=indexKeepInRange.run(++queue_end);
 //
	// 			}
 //
	// 		array[queue_start] = e;
	// 		queue_start=indexKeepInRange.run(++queue_start);
	// 		return;
	// 		};
	// 	public void remove(int index) throws IndexOutOfBoundsException{
	// 		//boolean SUCCESS = false;
	// 		if(index > size-1 || index < 0 ) throw new IndexOutOfBoundsException("Qrap Exception");
	// 		//"normalized" index
	// 		index=indexKeepInRange.run(queue_start+index);
	// 		//remove element
	// 		this.array[index] = null;
 //
	// 		//move array left by 1, starting from "index"
	// 		int indx_current = index,
	// 			indx_next = indexKeepInRange.run(index+1);
 //
	// 		while(indx_next!=queue_start){
	// 			array[indx_current]=array[indx_next];
	// 			indx_current=indx_next;
	// 			indx_next=indexKeepInRange.run(++indx_next);
	// 			}
 //
	// 		queue_end=indexKeepInRange.run(--queue_end);
	// 		array[queue_end]=null;
 //
 //
	// 		}
	// 	public T[] asArray(){
	// 		return (T[])array;
	// 		}
	// 	}

	class Inventory /*implements Const*/{
		int size;
		ArrayList<Equipment> holder;
		Inventory(int size){
			this.size = size;
			holder = new ArrayList<Equipment>(size);
			}
		public void add(Equipment e){
			holder.add(e);
			Core.narrate("[" + e.name + "] has been added to your Inventory!");
			if(holder.size() > size){//if over the allowed size...
				//the first Equipment must go
				Core.narrate("Too full! [" + holder.get(0).name + "] has fallen out your Inventory!");
				holder.remove(0);
				}
			}
		public void remove(String equip_ID){
			Equipment e = Core.searchID(holder,equip_ID);
			holder.remove(e);
			}
		public void remove(Equipment e){
			holder.remove(e);
			}
		public Equipment take(String equip_ID){
			Equipment e = Core.searchID(holder,equip_ID);
			holder.remove(e);
			return e;
			}
		public String[] getInfo(){
			String[] info = new String[holder.size()];
			for(int i=0; i < holder.size(); i++){
				info[i]=(holder.get(i)).getInfo();
				}
			return info;
			}
		}

	Inventory inventory = new Inventory(2);
	public Player(String name, int hp, int atk, int def, int coin, Equipment equipment) {
		super(name, hp, atk, def, coin, equipment);
	}


	public String getInfo(){
		return super.getInfo() +  "";
		}
	public void doAction(){};

	// public void interact(Feature feat){
	//NOTE: has been handled by Chest.interact method

	// 	super.interact(feat);
	// 	if(feat.getClass() == Chest.class){
	// 		//take from chest and put into inventory
	// 		Equipment e = ((Chest)feat).loot();
	// 		if(e != null){
	// 			inventory.add(e);
	// 			}
	// 		}
	// 	}
	}
