package ADTDictionary;

public class DoubleLLDictionary<Key,E> implements ADTDictionary<Key,E> {

	private DList<Key> klist; //declaration of object named klist of type Key
	private DList<E> vlist; //declaration of object named vlist of type E

	
	DoubleLLDictionary(int size){
		this.size();
		}  
	
	@Override
	public void clear() {  //clear list
		// TODO Auto-generated method stub
		klist.clear();
		vlist.clear();
	}
	
	public E find(Key k) { 
		DLink<Key> head = klist.curr; 
		DLink<Key> temp = head;
		int i;
		for (i=0; k != temp; i++) {
			temp = temp.next();}
		vlist.moveToPos(i);
		return vlist.getValue();
		//return null;

	}
	public void insert (Key k, E it) {
		if(find(k)==null) {
			System.out.println("nothing to insert");
		};
		klist.append(k);
		vlist.append(it);
	}

	public E remove (Key k) {
		Object it;
		DLink<E> origin = new DLink(it, null, null);
		E temp = find (k);
		while (temp!=null) {
			int pos = klist.find(k);  // unresolved error
			klist.moveToPos(pos);
			vlist.moveToPos(pos);
			klist.remove();
			vlist.remove();
		}
		return temp;
	}
	
	
	public E removeAny() {
		
		if (size() != 0) {
			klist.remove();
			E temp = vlist.getValue();
			vlist.remove();
			return temp;
		}
		else 
			return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return klist.length();
	}
	
}
	
	