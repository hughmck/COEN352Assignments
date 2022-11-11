package ADTDictionary;

public class LinkLDictionary<Key, E> implements ADTDictionary<Key, E> {
	
	
	private LList<Key> klist; // declaring list with key
	private LList<E> vlist; //declaring list V
	
	//constructor
	LinkLDictionary(int size){ // set both to equal size
		klist = new LList<Key>(size);
		vlist = new LList<E>(size);
	}
	
	@Override
	public void clear() { // clear list
		// TODO Auto-generated method stub
		klist.clear();
		vlist.clear();
	}

	@Override
	public void insert(Key k, E e) { // insert called from LList
		// TODO Auto-generated method stub
		klist.insert(k); 
		vlist.insert(e); 
		
		}

	@Override
	public E remove(Key k) { // initialise curr to 0 and count to 0
		// TODO Auto-generated method stub
		klist.moveToStart(); 
		vlist.moveToStart();
		 
		if (klist.curr != null) { //as long as curr does not point to a null object, remove the element
				klist.remove(); 
			return null; 
			}
		if (vlist.curr != null) {
			vlist.remove();
		 return null;
		}

		return null;
	}

	@Override
	public E find(Key k) {
		// TODO Auto-generated method stub
		klist.moveToStart(); 
		vlist.moveToStart(); 
		
		while(klist.curr != null)
		{
			if(klist.curr.element() == k) {
				int index = klist.currPos(); 
				
				for(int i = 0; i < index; i++) {
					vlist.next(); 
					}
				return vlist.getValue(); 
			}
			klist.next(); 
		}

		while(vlist.curr != null)
		{
			if(vlist.curr.element() == k) {
				int index = vlist.currPos(); 
				
				for(int i = 0; i < index; i++) {
					klist.next(); 
					}
			}
			klist.next(); 
		}
		
		return null; 
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return klist.length();
	}
}
