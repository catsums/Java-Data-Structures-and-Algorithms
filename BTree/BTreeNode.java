
package BTree;

@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally;
	Comparable<T> keys[];
	BTreeNode<T> references[];
	int m;
	static int level = 0;
	// Constructor for BTreeNode class
	public BTreeNode(int order, boolean leaf1)
	{
    		// Copy the given order and leaf property
		m = order;
    		leaf = leaf1;
 
    		// Allocate memory for maximum number of possible keys
    		// and child pointers
    		keys =  new Comparable[2*m-1];
    		references = new BTreeNode[2*m];
 
    		// Initialize the number of keys as 0
    		keyTally = 0;
	}

	// Function to print all nodes in a subtree rooted with this node
	public void print()
	{
		level++;
		if (this != null) {
			System.out.print("Level " + level + " ");
			this.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!this.leaf)
			{	
				for (int j = 0; j < 2*m; j++)
    				{
        				if (this.references[j] != null)
						this.references[j].print();
    				}
			}
		}
		level--;
	}

	// A utility function to print all the keys in this node
	private void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.keyTally; i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}

	// A utility function to give a string representation of this node
	public String toString() {
		String out = "[";
		for (int i = 1; i <= (this.keyTally-1); i++)
			out += keys[i-1] + ",";
		out += keys[keyTally-1] + "] ";
		return out;
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key)
	{
        	// Your code goes here
		if(key==null) return null;

		BTreeNode<T> node = null;
		BTreeNode<T> root = this;

		if(this.keyTally<1){
			this.keys[this.keyTally] = key;
		}else{
			//Preemtive split, we spread the legs first before we insert
			
			if(this.keyTally >= this.keys.length){
				//we splitting bois

				int mid = (int) this.keys.length/2;
				BTreeNode<T> newNode = new BTreeNode<>(m, leaf);

				int _i = 0;
				for(int i=mid+1;i<=this.keys.length;i++){
					if(i < this.keys.length){
						newNode.keys[_i] = this.keys[i];
						this.keys[i] = null;
					}
					newNode.references[_i] = this.references[i];
					this.references[i] = null;
					_i++;
				}
				
				BTreeNode<T> newParent = new BTreeNode<>(m, false);
				newParent.keys[0] = this.keys[mid];
				this.keys[mid] = null;
				newParent.references[0] = this;
				newParent.references[1] = newNode;

				newParent.updateTally();
				newNode.updateTally();
				this.updateTally();

				root = newParent;
				newParent.insert(key);
			}else{
				int comp;
				for(int c=0; c<=this.keyTally; c++){
					if(c < this.keyTally){
						comp = ((T) key).compareTo( (T) this.keys[c]);
						if(comp <= 0){
							if(this.leaf){
								shiftArrayRight(this.keys, c);
								this.keys[c] = key;
							}else{
								node = this.references[c].insert(key);
								if(node != this.references[c]){
									shiftArrayRight(this.keys, c);
									shiftArrayRight(this.references, c+1);

									this.keys[c] = node.keys[0];
									this.references[c+1] = node.references[1];
								}
							}
							break;
						}
					}else{
						if(this.leaf){
							if(this.keyTally < this.keys.length){
								this.keys[c] = key;
							}
						}else{
							node = this.references[c].insert(key);
							if(node != this.references[c]){
								if(this.keyTally < this.keys.length){
									shiftArrayRight(this.keys, c);
									shiftArrayRight(this.references, c+1);

									this.keys[c] = node.keys[0];
									this.references[c+1] = node.references[1];
								}
								
							}
						}
						break;
					}
				}
			}

		}

		this.updateTally();
		return root;

	}

	////NonPreemptive////
	// public BTreeNode<T> insert(T key)
	// {
 //        	// Your code goes here
	// 	if(key==null) return null;

	// 	T excess = null;
	// 	BTreeNode<T> excessNode = null;
	// 	BTreeNode<T> node = null;
	// 	BTreeNode<T> root = this;

	// 	if(this.keyTally<1){
	// 		this.keys[this.keyTally] = key;
	// 	}else{
	// 		int comp;
	// 		for(int c=0; c<=this.keyTally; c++){
	// 			if(c < this.keyTally){
	// 				comp = ((T) key).compareTo( (T) this.keys[c]);
	// 				if(comp <= 0){
	// 					if(this.leaf){
	// 						excess = (T) shiftArrayRight(this.keys, c);
	// 						this.keys[c] = key;
	// 						// shiftArrayRight(this.keys, c);
	// 						// this.keys[c] = key;
	// 					}else{
	// 						node = this.references[c].insert(key);
	// 						if(node != this.references[c]){
	// 							excess = (T) shiftArrayRight(this.keys, c);
	// 							excessNode = (BTreeNode<T>) shiftArrayRight(this.references, c+1);
	// 							// (T) shiftArrayRight(this.keys, c);
	// 							// (BTreeNode<T>) shiftArrayRight(this.references, c+1);

	// 							this.keys[c] = node.keys[0];
	// 							this.references[c+1] = node.references[1];

	// 							// excess = this.keys[this.keys.length-1];
	// 							// excessNode = this.references[this.references.length-1];
	// 						}
	// 					}
	// 					break;
	// 				}
	// 			}else{
	// 				if(this.leaf){
	// 					if(this.keyTally < this.keys.length){
	// 						excess = null;
	// 						this.keys[c] = key;
	// 					}else{
	// 						excess = (T) key;
	// 						//excess is key
	// 					}
	// 				}else{
	// 					node = this.references[c].insert(key);
	// 					if(node != this.references[c]){
	// 						if(this.keyTally < this.keys.length){
	// 							excess = (T) shiftArrayRight(this.keys, c);
	// 							excessNode = (BTreeNode<T>) shiftArrayRight(this.references, c+1);
	// 							// (T) shiftArrayRight(this.keys, c);
	// 							// (BTreeNode<T>) shiftArrayRight(this.references, c+1);

	// 							this.keys[c] = node.keys[0];
	// 							this.references[c+1] = node.references[1];

	// 							// excess = this.keys[this.keys.length-1];
	// 							// excessNode = this.references[this.references.length-1];
	// 						}else{
	// 							excess = (T) node.keys[0];
	// 							excessNode = node.references[1];
	// 						}
							
	// 					}
	// 				}
	// 				break;
	// 			}
	// 		}

	// 		// if(this.keys[this.keys.length-1]!=null || this.keys[this.keys.length-1]!=null){
	// 		if(excess!=null || excessNode!=null){
	// 			//we splitting bois

	// 			// excess = this.keys[this.keys.length-1];
	// 			// excessNode = this.references[this.references.length-1];

	// 			int mid = (int) this.keys.length/2;
	// 			BTreeNode<T> newNode = new BTreeNode<>(m, leaf);

	// 			int _i = 0;
	// 			for(int i=mid+1;i<=this.keys.length;i++){
	// 				if(i < this.keys.length){
	// 					newNode.keys[_i] = this.keys[i];
	// 					this.keys[i] = null;
	// 				}else{
	// 					newNode.keys[_i] = excess;
	// 					newNode.references[_i+1] = excessNode;
	// 				}
	// 				newNode.references[_i] = this.references[i];
	// 				this.references[i] = null;
	// 				_i++;
	// 			}
				
				
	// 			BTreeNode<T> newParent = new BTreeNode<>(m, false);
	// 			newParent.keys[0] = this.keys[mid];
	// 			this.keys[mid] = null;
	// 			newParent.references[0] = this;
	// 			newParent.references[1] = newNode;

	// 			newParent.updateTally();
	// 			newNode.updateTally();
	// 			this.updateTally();

	// 			root = newParent;
	// 		}

	// 	}

	// 	this.updateTally();
	// 	return root;

	// }

	// Function to search a key in a subtree rooted with this node
    	public BTreeNode<T> search(T key)
    	{  
		// Your code goes here 
    		if(key==null) return null;
    		int comp;
    		for(int c=0;c<=this.keyTally;c++){
    			if(c<this.keyTally){
    				if(this.keys[c]!=null){
    					comp = ((T) key).compareTo( (T) this.keys[c]);
    					if(comp == 0){
    						//found
	    					return this;
	    				}else if(comp < 0){
	    					if(!this.leaf) return this.references[c].search(key);
	    					return null;
	    				}
    				}else{
    					return null;
    				}
    				//if comp > 0, continue
    			}else{
    				if(!this.leaf) return this.references[c].search(key);
    				return null;
    			}
    		}
    		return null;
	}

	// Function to traverse all nodes in a subtree rooted with this node
	public void traverse()
	{
    		// Your code goes here
		for(int c=0;c<=this.keyTally;c++){
			if(!this.leaf){
				if(this.references[c]!=null){
					this.references[c].traverse();
				}
			}
			if(c<this.keyTally){
				if(this.keys[c]!=null)
					System.out.print(" "+this.keys[c]);
				else{
					break;
				}
			}
		}
	}

	protected void updateTally(){
		int c = 0;
		for(Comparable<T> key:this.keys){
			if(key!=null){
				c++;
			}else{
				break;
			}
		}
		this.keyTally = c;
	}

	protected Object shiftArrayRight(Object[] arr, int start){
		if(arr.length<1) return null;
		if(start<0 || start>=arr.length) return null;

		Object excess = null;

		for(int c=arr.length-1;c>=start;c--){
			if(c == arr.length-1){
				excess = arr[c];
			}else{
				arr[c+1] = arr[c];
			}
			arr[c] = null;
		}

		return excess;

	}protected Object shiftArrayRight(Object[] arr){
		return shiftArrayRight(arr, 0);
	}

	protected Object shiftArrayLeft(Object[] arr, int end){
		if(arr.length<1) return null;
		if(end<0 || end>=arr.length) return null;

		Object excess = null;

		for(int c=0;c<=end;c++){
			if(c == 0){
				excess = arr[c];
			}else{
				arr[c-1] = arr[c];
			}
			arr[c] = null;
		}

		return excess;

	}protected Object shiftArrayLeft(Object[] arr){
		return shiftArrayLeft(arr, arr.length-1);
	}
}