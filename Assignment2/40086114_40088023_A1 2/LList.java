package ADTDictionary;

/** Linked list implementation */
public class LList<E> implements ADTList<E> {
	
	private Link<E> head; // Pointer to list header
	
	private Link<E> tail; // Pointer to last element
	
	protected Link<E> curr; // Access to current element
	
	private int cnt; // length of list
	
	/** Constructors */
	public LList(int size) { this(); } // Constructor -- Ignore size
	
	public LList() {
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}
	
	/** Remove all elements */
	public void clear() {
		head.setNext(null); // Drop access to links
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}

	
//
	/** Insert "it" at current position */
	public void insert(E it) {
		if(this.length() == 0) // if the list is empty
		{
			Link<E> newNode = new Link<E>(it, null); // create a node where its next element is null
			head = newNode; //set head to the new node 
			curr = head; //set curr to head 
			tail = curr.next(); //set tail to be the next node of curr, which is null for this case 
		}
		
		else
		{
			if(curr.next() == null) //if we wanna append (insert at the end of a list)
			{
				curr.setNext(new Link<E>(it,null)); //set curr to point to a new node and that node is pointing to null
				tail = curr.next(); //set tail to be that new element
				this.next(); // move curr 
			}
			else
			{
				Link<E> temp = curr; //create a copy of pointer curr called temp and set that to the node where curr is pointing at
				this.next(); // move curr to the next node (but temp is not moved)
				Link<E> newNode = new Link<E>(it,curr); //create a new node and its next element is curr
				temp.setNext(newNode);//set temp to point to that new node
			}
			
		}
		if (tail == curr) 
			tail = curr.next(); // New tail
		cnt++;
		//Link<E> tempLink = new Link<E>(it, curr.next()); 
		//curr.setNext(tempLink);
		
		 
		
		// Using freelist support
		//curr.setNext(Link.get(it, curr.next()));
		
		
	}
	
	/** Append "it" to list */
	public void append(E it) {
		tail = tail.setNext(new Link<E>(it, null));
		cnt++;
	}
	
	/** Remove and return current element */
	
//	
	public E remove() {
		if (curr == null) 
			return null; // Nothing to remove
		
		E it = curr.element(); // Remember value
		
		if(curr == head) // if we want to remove the first element
		{
			head = head.next(); //set head to the next element
			curr.release(); // doesnt matter, just for the safety of memory leaks 
			curr = head; // set curr to head
		}
		
		else 
		{
			Link <E> temp = head; //set temp = head 
			while(temp.next() != curr)// if the next element of temp is not curr
			{
				temp = temp.next(); //move temp by 1 index
			}
			temp.setNext(curr.next()); // set temp to point to 1 index behind where curr is pointing at. this is the removal of the node where curr is at 
			curr.release(); // just for the safety sake of memory leak
			curr = temp; //move curr back to where temp is 
		}
		
		cnt--; // Decrement count
		return it; // Return value
		
		
		//if (tail == curr) 
			//tail = curr; // Removed last
		
	    //Using freelist
		// Link<E> tempptr = curr.next();
		//Link<E> templink = curr.next().next(); 
		//curr.setNext(templink); // Remove from list
		// tempptr.release(); 
		
		
	}
	
	/** Set curr at list start */
	public void moveToStart()
	{ curr = head; }
	
	/** Set curr at list end */
	public void moveToEnd()
	{ curr = tail; }
	
	/** Move curr one step left; no change if now at front */
	public void prev() {
		if (curr == head) return; // No previous element
		Link<E> temp = head;
		// March down list until we find the previous element
		while (temp.next() != curr) 
			temp = temp.next();
		curr = temp;
	}
	
	/** Move curr one step right; no change if now at end */
	public void next(){ 
		if (curr != tail) curr = curr.next(); 
	}
	
	/** @return List length */
	public int length() { return cnt; }
	
	/** @return The position of the current element */
	public int currPos() {
		Link<E> temp = head;
		int i;
		for (i=0; curr != temp; i++)
		temp = temp.next();
		return i;
	}
	
	/** Move down list to "pos" position */
	public void moveToPos(int pos) {
		assert (pos>=0) && (pos<=cnt) : "Position out of range";
		curr = head;
		for(int i=0; i<pos; i++) curr = curr.next();
	}
	
	/** @return Current element value */
	public E getValue() {
		
		if(curr == null) return null;
		return curr.element();
	}
	
	// Extra stuff not printed in the book.

	  /**
	   * Generate a human-readable representation of this list's contents
	   * that looks something like this: < 1 2 3 | 4 5 6 >.  The vertical
	   * bar represents the current location of the fence.  This method
	   * uses toString() on the individual elements.
	   * @return The string representation of this list
	   */
	  public String toString()
	  {
	    // Save the current position of the list
	    int oldPos = currPos();
	    int length = length();
	    StringBuffer out = new StringBuffer((length() + 1) * 4);

	    moveToStart();
	    out.append("< ");
	    for (int i = 0; i < oldPos; i++) {
	      out.append(getValue());
	      out.append(" ");
	      next();
	    }
	    out.append("| ");
	    for (int i = oldPos; i < length; i++) {
	      out.append(getValue());
	      out.append(" ");
	      next();
	    }
	    out.append(">");
	    moveToPos(oldPos); // Reset the fence to its original position
	    return out.toString();
	  }

}
