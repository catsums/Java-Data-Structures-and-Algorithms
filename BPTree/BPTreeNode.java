/**
 * A B+ tree generic node
 * Abstract class with common methods and data. Each kind of node implements this class.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */

 package BPTree;

abstract class BPTreeNode<TKey extends Comparable<TKey>, TValue> {
	
	protected Object[] keys;
	protected int keyTally;
	protected int m;
	protected BPTreeNode<TKey, TValue> parentNode;
	protected BPTreeNode<TKey, TValue> leftSibling;
	protected BPTreeNode<TKey, TValue> rightSibling;
	protected static int level = 0; // do not modify this variable's value as it is used for printing purposes. You can create another variable with a different name if you need to store the level.
	

	protected BPTreeNode() 
	{
		this.keyTally = 0;
		this.parentNode = null;
		this.leftSibling = null;
		this.rightSibling = null;
	}

	public int getKeyCount() 
	{
		return this.keyTally;
	}
	
	@SuppressWarnings("unchecked")
	public TKey getKey(int index) 
	{
		return (TKey)this.keys[index];
	}

	public void setKey(int index, TKey key) 
	{
		this.keys[index] = key;
	}

	public BPTreeNode<TKey, TValue> getParent() 
	{
		return this.parentNode;
	}

	public void setParent(BPTreeNode<TKey, TValue> parent) 
	{
		this.parentNode = parent;
	}	
	
	public abstract boolean isLeaf();
	
	/**
	 * Print all nodes in a subtree rooted with this node
	 */
	@SuppressWarnings("unchecked")
	public void print(BPTreeNode<TKey, TValue> node)
	{
		level++;
		if (node != null) {
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!node.isLeaf())
			{	BPTreeInnerNode inner = (BPTreeInnerNode<TKey, TValue>)node;
				for (int j = 0; j < (node.m); j++)
    				{
        				this.print((BPTreeNode<TKey, TValue>)inner.references[j]);
    				}
			}
		}
		level--;
	}

	/**
	 * Print all the keys in this node
	 */
	protected void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.getKeyCount(); i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}


	////// You may not change any code above this line. You may add extra variables if need be //////

	////// Implement the functions below this line //////
	
	
	
	/**
	 * Search a key on the B+ tree and return its associated value using the index set. If the given key 
	 * is not found, null should be returned.
	 */
	public TValue search(TKey key) 
	{
		// Your code goes here
		if(key==null) return null;
		int comp;
		for(int c=0; c<=this.keyTally; c++){
			//if index is less than the number of keys
			if(c < this.keyTally){
				if(this.keys[c]!=null){
					comp = ((TKey) key).compareTo((TKey) this.keys[c]);
					if(comp == 0){
						//if this is a leafnode, return the value cuz its found
						if(this.isLeaf()){
							return (TValue) this.asLeafNode().getVal(c);
						}
						//else, try to check for the next key and enter the child node using the next index
					}else if(comp < 0){
						//if the key is less than the key in this slot, it might be in the child
						if(!this.isLeaf()){
							return (TValue) this.asInnerNode().getRef(c).search(key);
						}
						//otherwise if this is a leaf node, the sorted keys show that the next nodes wont be it :. its not found
						return null;
					}
				}
			}else{
				//if index is the same as tally, that means it has to be inside an InnerNode
				if(!this.isLeaf()){
					return (TValue) this.asInnerNode().getRef(c).search(key);
				}
				//otherwise return null if its a leaf
				return null;
			}
		}
		return null;
	}



	/**
	 * Insert a new key and its associated value into the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) 
	{
		// Your code goes here
		//if theres no key or value, return deez nuts in ur mouth
		if(key==null || value==null) return this;

		BPTreeNode<TKey, TValue> node = null;
		BPTreeNode<TKey, TValue> root = this;

		if(this.keyTally<1){
			//if this node is empty and its a leaf, just throw it in there
			this.keys[this.keyTally] = key;
			if(this.isLeaf()){
				this.asLeafNode().setVal(this.keyTally, value);
			}
		}else{
			/////////HELP ME FIND A BETTER WAY TO TYYPE CAST OMG////////////
			int comp; //used to compare
			for(int c=0; c<=this.keyTally; c++){
				if(c < this.keyTally){
					comp = ((TKey) key).compareTo((TKey) this.keys[c]);
					if(comp < 0){
						//if key is less than the current key in this node and its a leaf,
						// shift keys array and values array to the right at that index and insert the key and value into the open slots
						if(this.isLeaf()){
							shiftArrayRight(this.keys, c);
							shiftArrayRight(this.asLeafNode().getVals(), c);
							this.keys[c] = key;
							this.asLeafNode().setVal(c, value);

						}else{
						//if this is an innerNode, insert into the child node and check the returned node
							node = this.asInnerNode().getRef(c).insert(key, value);
							//if the returned node is not the same as the current child node, assume that a split happened
							//note that the returned node will either be a dummy parent with the split nodes or the child
							if(node != this.asInnerNode().getRef(c)){
								//SPLIT HAPPENED
								//shift the keys and references by the index, leaving it open for the new node
								shiftArrayRight(this.keys, c);
								shiftArrayRight(this.asInnerNode().getRefs(), c+1);

								this.keys[c] = node.getKey(0);
								//update the children so that they remember the parent
								//because they are still connected to the dummy parent
								this.asInnerNode().setRef(c, node.asInnerNode().getRef(0));
								this.asInnerNode().setRef(c+1, node.asInnerNode().getRef(1));
							}
						}
						break;
					}else if(comp == 0){
						//if this is a leaf, get outta here because we dont want dupes
						if(this.isLeaf()) return root; //no duplicates allowed
					}
				}else{
					//if the key is larger than all of them
					//if this is a leafnode, add it at the end of the tally along with the value too
					if(this.isLeaf()){
						this.keys[this.keyTally] = key;
						this.asLeafNode().setVal(this.keyTally, value);
					}else{
					//if this is an inner node, add the key and value into the last child
						node = this.asInnerNode().getRef(c).insert(key, value);
						if(node != this.asInnerNode().getRef(c)){
							//SPLIT HAPPENED
							//shift the keys and references by the index, leaving it open for the new node
							shiftArrayRight(this.keys, c);
							shiftArrayRight(this.asInnerNode().getRefs(), c+1);

							this.keys[c] = node.getKey(0);
							//update the children so that they remember the parent
							//because they are still connected to the dummy parent
							this.asInnerNode().setRef(c, node.asInnerNode().getRef(0));
							this.asInnerNode().setRef(c+1, node.asInnerNode().getRef(1));
							//sorry for undry code
						}
					}
				}
			}

			//update the tally for this node
			this.updateTally();

			//Note that the last array slot is for checking for excess nodes

			if(this.keyTally >= this.keys.length){
				//if the tally is the same as the array length, this is an OVERFLOW
				int mid = (int) (this.keys.length)/2; //this code is mid tbh

				//create new node
				BPTreeNode<TKey,TValue> newNode;
				if(this.isLeaf()){
					newNode = new BPTreeLeafNode<>(m);
				}else{
					newNode = new BPTreeInnerNode<>(m);
				}

				int _i = 0;
				//if this is a leaf, add the mid position key to the newNode
				if(this.isLeaf()){
					newNode.setKey(_i, this.getKey(mid));
					newNode.asLeafNode().setVal(_i, this.asLeafNode().getVal(mid));
					_i++; //update the key counter
				}

				for(int i=mid+1;i<=this.keys.length;i++){
					//remove the keys after the mid value and add them to the newNode
					if(i < this.keys.length){
						newNode.setKey(_i, this.getKey(i));
						this.keys[i] = null;
					}
					//move the values too if this is a leafNode
					if(!this.isLeaf()){
						newNode.asInnerNode().setRef(_i, this.asInnerNode().getRef(i));
						this.asInnerNode().setRef(i, null);
					}
					//or move the references if this is an InnerNode
					else{
						newNode.asLeafNode().setVal(_i, this.asLeafNode().getVal(i));
						this.asLeafNode().setVal(i, null);
					}
					_i++; //update the key counter to keep track
				}

				//create a dummy parent that will hold the two nodes
				BPTreeInnerNode<TKey,TValue> newParent = new BPTreeInnerNode<>(m);
				
				//put the mid position key into the newParent and remove it from this node
				//if this is a leaf, remove the value too
				newParent.setKey(0, this.getKey(mid));
				this.setKey(mid, null);
				if(this.isLeaf()) this.asLeafNode().setVal(mid, null);

				//add this node and the newNode to the dummy parent
				newParent.setRef(0, this);
				newParent.setRef(1, newNode);

				//update the left and right pointers for the nodes. this wont matter if its an InnerNode
				if(this.rightSibling!=null){
					this.rightSibling.leftSibling = newNode;
					newNode.rightSibling = this.rightSibling;
				}
				newNode.leftSibling = this;
				this.rightSibling = newNode;

				//update the tallies of all nodes involved
				newParent.updateTally();
				newNode.updateTally();
				this.updateTally();

				//set the return node to the dummy parent so that it may be the new root of the tree or get harvested for its new borns
				root = newParent;

			}

		}

		this.updateTally();
		return root;

	}



	/**
	 * Delete a key and its associated value from the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> delete(TKey key) 
	{
		// Your code goes here
		if(key==null) return this;

		BPTreeNode<TKey,TValue> node = null;
		BPTreeNode<TKey,TValue> root = this;

		if(this.keyTally<1){
			//if theres no node here, return
			return root;
		}else{
			int comp;
			for(int c=0; c<=this.keyTally;c++){
				if(c < this.keyTally){
					if(this.keys[c]!=null){
						comp = ((TKey) key).compareTo((TKey) this.keys[c]);
						if(comp == 0){
							//if its equal, the key has been found if its in a leafnode
							if(this.isLeaf()){
								//delete from the leaf node by shifting keys internally to the left from a certain index
								//:. move keys on the right side into the position by shifting them left
								innerShiftArrayLeft(this.keys, c);
								innerShiftArrayLeft(this.asLeafNode().getVals(), c);
								//update the tally
								this.updateTally();
								break;
							}
							//otherwise if this is an innernode, move to the next key and check for the child underneath
						}else if(comp < 0){
							if(!this.isLeaf()){
								//if this is an innernode, check for changes in the left and right siblings of the current child
								//if there are changes in those tallies,
								//assume that keys have been shared and you need to update the keys to match keys for those children
								BPTreeNode<TKey,TValue> curr = this.asInnerNode().getRef(c);

								BPTreeNode<TKey,TValue> _left = curr.leftSibling;
								BPTreeNode<TKey,TValue> _right = curr.rightSibling;

								int initLeftTally = (_left==null)?0:_left.getKeyCount();
								int initRightTally = (_right==null)?0:_right.getKeyCount();

								node = curr.delete(key);

								int leftTally = (_left==null)?0:_left.getKeyCount();
								int rightTally = (_right==null)?0:_right.getKeyCount();

								//if the returned node is not the same as the child, assume a merge happened
								//this will always receive either the node merged with the child or the child itself
								if(node != this.asInnerNode().getRef(c)){
									//MERGE HAPPENED
									//shift keys left internally to remove the old key and old reference since they are node needed anymore
									innerShiftArrayLeft(this.keys, c);
									innerShiftArrayLeft(this.asInnerNode().getRefs(), c);

									//if the index is over 0, update the keys for the merged node with new seperators
									if(c>0){
										if(this.asInnerNode().getRef(c).isLeaf()){
											this.setKey(c-1, this.asInnerNode().getRef(c).getKey(0));
										}
									}
									//update this tally
									this.updateTally();
								}else{
									if(!curr.isLeaf()){
										//if the child node is not a leaf, assume that things are fixed already
									}
									else if(leftTally<initLeftTally){
										//Left sibling shared its keys to child
										TKey _key = curr.getKey(0); //first key of child
										BPTreeNode<TKey,TValue> _child = curr;
										BPTreeNode<TKey,TValue> _parent = curr.parentNode; //technically THIS node

										//in this case we find the highest ancestor if the node is the first child of this node
										if(c<1){
											while(_parent.parentNode!=null){
												_child = _parent;
												_parent = _parent.parentNode;
											}
										}

										int index = _parent.asInnerNode().getIndexForChild(_child);
										//we get the index of the highest ancestor's child node so we can update it using the child's first key
										if(index>0){
											_parent.setKey(index-1, _key);
										}
									}else if(rightTally<initRightTally){
										//Right sibling shared its keys to child
										TKey _key = _right.getKey(0);
										BPTreeNode<TKey,TValue> _child = _right;
										BPTreeNode<TKey,TValue> _parent = _right.parentNode;

										//we dont need to find the highest ancestor because only the parent of rightsibling needs to be updated
										// while(_parent.parentNode!=null){
										// 	_child = _parent;
										// 	_parent = _parent.parentNode;
										// }

										int index = _parent.asInnerNode().getIndexForChild(_child);
										//we get the index of the highest ancestor's child node so we can update it using the child's first key
										if(index>0){
											_parent.setKey(index-1, _key);
										}
									}
								}

								break;
							}
						}
					}
				}else{
					if(!this.isLeaf()){
						//if the index is the same as the tally and this node is an Innernode, we check the last child
						BPTreeNode<TKey,TValue> curr = this.asInnerNode().getRef(c);

						BPTreeNode<TKey,TValue> _left = curr.leftSibling;
						BPTreeNode<TKey,TValue> _right = curr.rightSibling;

						int initLeftTally = (_left==null)?0:_left.getKeyCount();
						int initRightTally = (_right==null)?0:_right.getKeyCount();

						node = curr.delete(key);

						int leftTally = (_left==null)?0:_left.getKeyCount();
						int rightTally = (_right==null)?0:_right.getKeyCount();

						//if the returned node is not the same as the child, assume a merge happened
						//this will always receive either the node merged with the child or the child itself
						if(node != this.asInnerNode().getRef(c)){
							//MERGE HAPPENED
							//shift keys left internally to remove the old key and old reference since they are node needed anymore
							//note that we shift keys internally to the left from the previous index
							innerShiftArrayLeft(this.keys, c-1);
							innerShiftArrayLeft(this.asInnerNode().getRefs(), c);
							this.updateTally();
							//if the index is over 0, update the keys for the merged node with new seperators
							//note the difference with the index (c-1) and not(c)
							if(c>1){
								if(this.asInnerNode().getRef(c-1).isLeaf()){
									this.setKey(c-2, this.asInnerNode().getRef(c-1).getKey(0));
								}
							}
							this.updateTally();
						}else{
							if(!curr.isLeaf()){
								//if the child node is not a leaf, assume that things are fixed already
							}
							else if(leftTally<initLeftTally){
								//Left sibling shared its keys to child
								TKey _key = curr.getKey(0);
								BPTreeNode<TKey,TValue> _child = curr;
								BPTreeNode<TKey,TValue> _parent = curr.parentNode;

								//we dont need to find the highest ancestor because only the parent of this child needs to be updated
								// if(c<1){
								// 	while(_parent.parentNode!=null){
								// 		_child = _parent;
								// 		_parent = _parent.parentNode;
								// 	}
								// }
								

								int index = _parent.asInnerNode().getIndexForChild(_child);
								//we get the index of the highest ancestor's child node so we can update it using the child's first key
								if(index>0){
									_parent.setKey(index-1, _key);
								}
							}else if(rightTally<initRightTally){
								//Right sibling shared its keys to child
								TKey _key = _right.getKey(0);
								BPTreeNode<TKey,TValue> _child = _right;
								BPTreeNode<TKey,TValue> _parent = _right.parentNode;

								//we know the index is the last, so we update the keys for the merged node with new seperators
								while(_parent.parentNode!=null){
									_child = _parent;
									_parent = _parent.parentNode;
								}

								int index = _parent.asInnerNode().getIndexForChild(_child);
								//we get the index of the highest ancestor's child node so we can update it using the child's first key
								if(index>0){
									_parent.setKey(index-1, _key);
								}
							}
						}
						break;
					}
				}
			}
		}

		int underFlow = (int) ((this.keys.length-1)/2);
		//underflow is the mid part of the keys length excluding the overflow

		if(this.keyTally < underFlow){
			//UNDERFLOW
			BPTreeNode<TKey,TValue> leftNode = null;
			BPTreeNode<TKey,TValue> rightNode = null;

			if(this.isLeaf()){
				//If this is a leaf, we check if the left sibling has keys to share provided has more keys than underflow
				//otherwise check the right sibling
				//if neither have any keys to share, we merge with the left sibling. if the left sibling does not exist, merge with the right
				// if neither sibling exists, expect to be laughed at you single child, shoulda asked ur parents to have sex more
				if(this.leftSibling!=null && this.leftSibling.getKeyCount() > underFlow){
					//share keys with left
					leftNode = this.leftSibling;

					//shift keys to the right to open up the first slot for a shared key
					shiftArrayRight(this.keys, 0);
					shiftArrayRight(this.asLeafNode().getVals(), 0);

					//shift the leftsibling's keys internally to the left to pop out the key it wants to share and insert it into this node
					//do the same for the values
					this.setKey(0, (TKey) innerShiftArrayLeft(leftNode.keys, leftNode.getKeyCount()-1));
					this.asLeafNode().setVal(0, (TValue) innerShiftArrayLeft(leftNode.asLeafNode().getVals(), leftNode.getKeyCount()-1));
					//update the tallies for both nodes
					leftNode.updateTally();
					this.updateTally();

				}else if(this.rightSibling!=null && this.rightSibling.getKeyCount() > underFlow){
					//share keys right
					rightNode = this.rightSibling;

					//no need to shift the keys for an opening
					// shiftArrayRight(this.keys, 0);
					// shiftArrayRight(this.asLeafNode().getVals(), 0);

					//shift the leftsibling's keys internally to the left to pop out the key it wants to share and insert it into this node
					//do the same for the values
					this.setKey(this.keyTally, (TKey) innerShiftArrayLeft(rightNode.keys, 0));
					this.asLeafNode().setVal(this.keyTally, (TValue) innerShiftArrayLeft(rightNode.asLeafNode().getVals(), 0));
					//update the tallies for both nodes
					rightNode.updateTally();
					this.updateTally();

				}else{
					//MERGING NODES
					if(this.leftSibling!=null){
						//MERGING WITH LEFT NODE
						leftNode = this.leftSibling;

						//make new arrays for keys and values
						Object[] newKeys = new Object[m];
						Object[] newVals = new Object[m];

						//left node inserts its keys first and then the current one
						int _i = 0;
						for(int c=0;c<leftNode.getKeyCount();c++){
							newKeys[_i] = leftNode.getKey(c);
							newVals[_i] = leftNode.asLeafNode().getVal(c);
							_i++;
						}
						for(int c=0;c<this.keyTally;c++){
							newKeys[_i] = this.getKey(c);
							newVals[_i] = this.asLeafNode().getVal(c);
							_i++;
						}

						//use the leftNode as the merged result by changing its keys array to the new array and the same for values
						leftNode.keys = newKeys;
						leftNode.asLeafNode().setVals(newVals);

						//update the left and right pointers to point away from the discarded node :. THIS
						if(this.rightSibling!=null){
							this.rightSibling.leftSibling = leftNode;
							leftNode.rightSibling = this.rightSibling;
						}else{
							leftNode.rightSibling = null;
						}
						this.rightSibling = null;
						this.leftSibling = null;

						//make it return the leftNode
						root = leftNode;

						//update the leftNode's tally
						leftNode.updateTally();

					}else if(this.rightSibling!=null){
						//MERGING WITH RIGHT NODE
						rightNode = this.rightSibling;

						//make new arrays for keys and values
						Object[] newKeys = new Object[m];
						Object[] newVals = new Object[m];

						//current node inserts first and then the right node
						int _i = 0;
						for(int c=0;c<this.keyTally;c++){
							newKeys[_i] = this.getKey(c);
							newVals[_i] = this.asLeafNode().getVal(c);
							_i++;
						}
						for(int c=0;c<rightNode.getKeyCount();c++){
							newKeys[_i] = rightNode.getKey(c);
							newVals[_i] = rightNode.asLeafNode().getVal(c);
							_i++;
						}

						//use the rightNode as the merged result by chnaging its keys and values arrays
						rightNode.keys = newKeys;
						rightNode.asLeafNode().setVals(newVals);

						//update the left and right pointers
						if(this.leftSibling!=null){
							this.leftSibling.rightSibling = rightNode;
							rightNode.leftSibling = this.leftSibling;
						}else{
							rightNode.leftSibling = null;
						}
						this.rightSibling = null;
						this.leftSibling = null;

						//make it return the rightNode and update its tallies
						root = rightNode;

						rightNode.updateTally();
					}
					this.updateTally();
				}
			}else{
				//if this is an inner Node, we check for the parentNode
				BPTreeNode<TKey,TValue> parent = this.parentNode;
				if(parent != null){
					//if the parent Node exists, we assume that the leftNode and rightNode are part of the same parent
					int c = parent.asInnerNode().getIndexForChild(this);

					//if the index is not -1, the child exists in the parent
					if(c>=0){
						leftNode = parent.asInnerNode().getRef(c-1);
						rightNode = parent.asInnerNode().getRef(c+1);

						//if the LeftNode exists, make a slot for accepting the new key and the new reference as a child
						//we dont take the key from the leftNode, but from the parent
						//this is done by moving the parent's key down to the current child and moving the latest child from the left node up to the parent
						//:. we are rotating the keys from left to parent and from parent to current child
						if(leftNode!=null && leftNode.getKeyCount() > underFlow){
							//shift keys right and refs to make a slot
							shiftArrayRight(this.keys, 0);
							shiftArrayRight(this.asInnerNode().getRefs(), 0);

							//shift out the leftNode's last child and insert it into the current child
							this.asInnerNode().setRef(0, (BPTreeNode) innerShiftArrayLeft(leftNode.asInnerNode().getRefs(), leftNode.getKeyCount()));
							//set the open slot key for the current child to the parent's key at that index
							this.setKey(0, parent.getKey(c-1));
							//shift out the leftNode's last key and insert it into the parent
							parent.setKey(c-1, (TKey) innerShiftArrayLeft(leftNode.keys, leftNode.getKeyCount()-1));
						
							//update the tally of the leftNode
							leftNode.updateTally();
						}else if(rightNode!=null && rightNode.getKeyCount() > underFlow){
							//no need to shift keys right and refs to make a slot
							// My.cout("-rightNode steal");
							// shiftArrayRight(this.keys, 0);
							// shiftArrayRight(this.asInnerNode().getRefs(), 0);

							//set the open slot key for the current child to the parent's key at that index
							this.setKey(this.keyTally, parent.getKey(c));
							//shift out the rightNode's last child and insert it into the current child
							this.asInnerNode().setRef(this.keyTally+1, (BPTreeNode) innerShiftArrayLeft(rightNode.asInnerNode().getRefs(), 0));
							//shift out the rightNode's last key and insert it into the parent
							parent.setKey(c, (TKey) innerShiftArrayLeft(rightNode.keys, 0));
							//update the tally of the rightNode
							rightNode.updateTally();
						}else{
							//MERGING INNERNODES
							if(leftNode!=null){
								//MERGING WITH LEFTNODE

								//create new array for keys and refs

								Object[] newKeys = new Object[m];
								Object[] newRefs = new Object[m+1];

								//add the leftNode's keys and refs to the keys and refs array
								int _i = 0;
								for(int k=0;k<=leftNode.getKeyCount();k++){
									if(k<leftNode.getKeyCount()){
										newKeys[_i] = leftNode.getKey(k);
									}
									newRefs[_i] = leftNode.asInnerNode().getRef(k);
									_i++;
								}
								//add the key of the parent at that index
								newKeys[_i-1] = (TKey) innerShiftArrayLeft(parent.keys, c-1);

								//add the current Nodes's keys and refs to the keys and refs array
								for(int k=0;k<=this.keyTally;k++){
									if(k<this.keyTally){
										newKeys[_i] = this.getKey(k);
									}
									newRefs[_i] = this.asInnerNode().getRef(k);
									_i++;
								}

								//change the keys and refs array of the leftNode so it acts as the merged node

								leftNode.keys = newKeys;
								leftNode.asInnerNode().setRefs(newRefs);
								//return the leftNode and update its tally
								root = leftNode;
								leftNode.updateTally();

							}else if(rightNode!=null){
								//MERGING WITH RIGHTNODE

								//create new array for keys and refs

								Object[] newKeys = new Object[m];
								Object[] newRefs = new Object[m+1];

								//add the current Node's keys and refs to the keys and refs array
								int _i = 0;
								
								for(int k=0;k<=this.keyTally;k++){
									if(k<this.keyTally){
										newKeys[_i] = this.getKey(k);
									}
									newRefs[_i] = this.asInnerNode().getRef(k);
									_i++;
								}
								//add the key of the parent at that index
								newKeys[_i-1] = (TKey) innerShiftArrayLeft(parent.keys, c);

								//add the rightNodes's keys and refs to the keys and refs array
								for(int k=0;k<=rightNode.getKeyCount();k++){
									if(k<rightNode.getKeyCount()){
										newKeys[_i] = rightNode.getKey(k);
									}
									newRefs[_i] = rightNode.asInnerNode().getRef(k);
									_i++;
								}
								//change the keys and refs array of the rightNode so it acts as the merged node

								rightNode.keys = newKeys;
								rightNode.asInnerNode().setRefs(newRefs);
								//return the rightNode and update its tally
								root = rightNode;
								rightNode.updateTally();
							}else{
								// nothing happens except pain cuz this is invalid
							}
						}

						this.updateTally();
						parent.updateTally();
					}else{
						//Node is not in parent so how tf did this happen
					}
				}else{
					//Assume this is the root of the tree
					//if there are no keys or there is only 1 child, make the new root of the tree into the first child
					if(this.keyTally<1 || this.asInnerNode().getRef(1)==null){
						root = this.asInnerNode().getRef(0);
						root.parentNode = null;
					}
				}
			}
			
		}

		return root;
	}



	/**
	 * Return all associated key values on the B+ tree in ascending key order using the sequence set. An array
	 * of the key values should be returned.
	 */
	@SuppressWarnings("unchecked")
	public TValue[] values() 
	{
		// Your code goes here
		//this is the trash bin to store all the values in order
		Object[] arr = new Object[0];
		if(this.isLeaf()){
			//if this is a leaf, one by one push the values into the array
			Object[] vals = this.asLeafNode().getVals();
			for(int c=0;c<this.keys.length;c++){
				if(vals[c]!=null){
					arr = arrayPush(arr, vals[c]);
				}
			}
		}else{
			// if this is a leaf, check each child in the references and for each child, use recursion to get the values
			//then for each value in the returned array, push it into the bin array
			Object[] refs = this.asInnerNode().getRefs();
			for(int c=0;c<refs.length;c++){
				if(this.asInnerNode().getRef(c)!=null){
					TValue[] vals = this.asInnerNode().getRef(c).values();
					for(int d=0;d<vals.length;d++){
						if(vals[d]!=null){
							arr = arrayPush(arr, vals[d]);
						}
					}
				}
			}
		}
		//typecast the array as you return it
		return (TValue[]) arr;
	}

	//////HELPER/////////
	//updates the tally by counting the number of keys
	protected void updateTally(){
		int c = 0;
		for(Object key:this.keys){
			if(key!=null){
				c++;
			}else{
				break;
			}
		}
		this.keyTally = c;
	}

	//increment array dynamically by adding to end of array :. array[array.length - 1]. returns a new copy
	protected static Object[] arrayPush(Object[] arr, Object item){
        Object[] newArr = resizeArray(arr,1);
        newArr[newArr.length-1] = item;
        return newArr;
    }
    //decrement array dynamically by removing the end of array :. array[array.length - 1]. returns a new copy
    protected static Object[] arrayPop(Object[] arr){
        Object[] newArr = resizeArray(arr,-1);
        return newArr;
    }
    //increment array dynamically by adding to the front of array :. array[0]. returns a new copy
    protected static Object[] arrayUnshift(Object[] arr, Object item){
        Object[] newArr = new Object[arr.length+1];

        for(int i=1;i<=arr.length;i++){
            newArr[i] = arr[i-1];
        }

        newArr[0] = item;

        return newArr;
    }
    //returns a copy of the array in reverse order
    protected static Object[] reverseArray(Object[] arr){
        Object[] newArr = new Object[arr.length];

        int c = arr.length-1;
        for(int i=0; i<arr.length; i++){
            newArr[i] = arr[c];
            c--;
        }
        return newArr;
    }
    //returns a copy of the array but resized by an amount
    protected static Object[] resizeArray(Object[] arr, int resize){
        int len = arr.length + resize;
        Object[] newArr = new Object[len];
        for(int i=0;i<arr.length;i++){
            if(i>=len) break;
            newArr[i] = arr[i];
        }
        return newArr;
    }
    //linear searches array for an item. this is by reference and it returns true/false
    public static boolean isInArray(Object[] arr, Object item){
        if(item==null) return false;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == item){
                return true;
            }
        }
        return false;
    }
    //shifts array to the right once at a specific position and returns the item that is pushed out of bounds
    //given an array of [a b c x y z] and shiftRight at index 2, the resulting array will be [a b _ c x y] and it returns 'z'
    //this changes the original array
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
	//shifts array to the left once at a specific position and returns the item that is pushed out of bounds
	//given an array of [a b c x y z] and shiftLeft at index 2, the resulting array will be [b c _ x y z] and it returns 'a'
	//this changes the original array
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
	//shifts array to the right internally once :. moving items towards the index at a specific position and returns the item that's popped out
	//given an array of [a b c x y z] and innerShiftRight at index 2, the resulting array will be [_ a b x y z] and it returns 'c'
	//this changes the original array
	protected Object innerShiftArrayRight(Object[] arr, int end){
		if(arr.length<1) return null;
		if(end<0 || end>=arr.length) return null;

		Object excess = null;

		for(int c=end;c>=0;c--){
			if(c == end){
				excess = arr[c];
			}else{
				arr[c+1] = arr[c];
			}
			arr[c] = null;
		}
		return excess;
	}protected Object innerShiftArrayRight(Object[] arr){
		return innerShiftArrayLeft(arr, arr.length-1);
	}
	//shifts array to the left internally once :. moving items towards the index at a specific position and returns the item that's popped out
	//given an array of [a b c x y z] and innerShiftLeft at index 2, the resulting array will be [a b x y z _] and it returns 'c'
	//this changes the original array
	protected Object innerShiftArrayLeft(Object[] arr, int start){
		if(arr.length<1) return null;
		if(start<0 || start>=arr.length) return null;

		Object excess = null;

		for(int c=start;c<arr.length;c++){
			if(c == start){
				excess = arr[c];
			}else{
				arr[c-1] = arr[c];
			}
			arr[c] = null;
		}
		return excess;
	}protected Object innerShiftArrayLeft(Object[] arr){
		return innerShiftArrayLeft(arr, 0);
	}

	//returns the node typecasted as a LeafNode (only works if it was created as a BPTreeLeafNode)
	public BPTreeLeafNode<TKey,TValue> asLeafNode(){
		return (BPTreeLeafNode<TKey,TValue>) this;
	}
	//returns the node typecasted as an InnerNode (only works if it was created as a BPTreeInnerNode)
	public BPTreeInnerNode<TKey,TValue> asInnerNode(){
		return (BPTreeInnerNode<TKey,TValue>) this;
	}
	//returns the node typecasted as a normie TreeNode
	public BPTreeNode<TKey,TValue> asNode(){
		return (BPTreeNode<TKey,TValue>) this;
	}

	///remove later////
	//just to output as string in a format
	// @Override
	// public String toString(){
	// 	String out = "[";
	// 	for(int c=0;c<keys.length;c++){
	// 		if(keys[c]==null) out += "_";
	// 		else out += keys[c];
	// 		if(c<keys.length-2) out += "|";
	// 		else if(c<keys.length-1) out += "||";
	// 	}
	// 	out += "]";

	// 	if(this.isLeaf()){
	// 		String rOut = "(";
	// 		if(this.rightSibling!=null) rOut += this.rightSibling.getKey(0);
	// 		rOut += ")";

	// 		String lOut = "(";
	// 		if(this.leftSibling!=null) lOut += this.leftSibling.getKey(0);
	// 		lOut += ")";

	// 		out = lOut + out + rOut;
	// 	}
	// 	if(this.parentNode!=null){
	// 		String pOut = "<" + this.parentNode.getKey(0) + ">";
	// 		out = out + pOut;
	// 	}

	// 	return out;
	// }


}