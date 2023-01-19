import java.util.Random;
//Cassim Chifamba u19024895
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>>
{

	public int maxLevel;
	public SkipListNode<T>[] root;
	private int[] powers;
	private Random rd = new Random();

	SkipList(int i)
	{
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(1230456789);
	}
	//by default the skiplist is built with 4 levels
	SkipList()
	{
		this(4);
	}
	//using math, the powers are split accordingly to determine the levels
	//whenever the skip list reaches maximum nodes, the maxLevel should increment however for this assignment we just leave it be
	public void choosePowers()
	{
		powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i+1] - (2 << j);
	}
	//the level is chosen randomly depending on the powers array
	public int chooseLevel()
	{
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel-1] + 1;
		for (i = 1; i < maxLevel; i++)
		{
			if(r < powers[i])
				return i-1;
		}
		return i-1;
	}

	public boolean isEmpty()
	{
		//Your code goes here
		if(this.root.length>0 && this.root[0]!=null){
			return false;
		}
		return true;
	}

	public void insert(T key)
	{
		//Your code goes here

		int level = this.chooseLevel();
		int selectedLevel = 0;

		SkipListNode<T> newNode = new SkipListNode(key,level+1);
		
		for(int i=0; i<level+1; i++){
			SkipListNode<T> ptr = this.root[i];
			SkipListNode<T> prevPtr = null;
			//if theres no root pointing to that level
			if(this.root[i] == null){
				this.root[i] = newNode;
			}else{
				while(ptr != null){
					int _compared = ptr.key.compareTo(newNode.key);
					if(_compared == 0){
						//do not add dupes
						return;
					}else if(_compared > 0){
						newNode.next[i] = ptr;
						//if theres prev, its around the middle
						if(prevPtr != null){
							prevPtr.next[i] = newNode;
						}
						//if no prev, its first element
						else{
							//root points to newNode
							this.root[i] = newNode;
						}
						break;
					}
					//if its the last element
					else if(ptr.next[i] == null){
						ptr.next[i] = newNode;
						break;
					}

					prevPtr = ptr;
					ptr = ptr.next[i];
				}
			}
		}
		// My.cout("------------");

	}	
	//gets the first key
	public T first()
	{
		//Your code goes 
		if(this.root[0]==null||this.root[0].key==null)
			return null;
		return (T) this.root[0].key;
	}
	//gets the last key
	public T last()
	{
		//Your code goes here
		if(this.root[0]==null||this.root[0].key==null)
			return null;

		SkipListNode<T> ptr = null;
		for(int i=this.maxLevel-1;i>=0;i--){
			if(ptr==null){
				ptr = this.root[i];
			}
			SkipListNode<T> prevPtr = ptr;
			while(ptr!=null){
				prevPtr = ptr;
				ptr = ptr.next[i];
			}
			ptr = prevPtr;
		}
		if(ptr!=null){
			return (T) ptr.key;
		}
		return null;
	}	
	//searches the ket and returns....the key??? when its found
	public T search(T key)
	{
		//Your code goes here
		if(this.root[0]==null||this.root[0].key==null)
			return null;

		SkipListNode<T> ptr = null;
		SkipListNode<T> prevPtr = ptr;
		for(int i=this.maxLevel-1; i>=0; i--){
			//if there is no node from top level, set current node to the root
			if(ptr==null){
				ptr = this.root[i];
			}
			//if this is the root node, there is no previous node
			if(ptr==this.root[i]){
				prevPtr = null;
			}
			while(ptr!=null){
				//compare using compareTo since they are objects
				int _compared = ptr.key.compareTo(key);
				if(_compared == 0){
					//Element is found, return object
					return (T) ptr.key;
				}else if(_compared > 0){
					// Element is not in this level, end while loop
					break;
				}
				//go to next node
				prevPtr = ptr;
				ptr = ptr.next[i];
			}
			// by default, revert back to previous Node
			ptr = prevPtr;
		}
		// if youre here, its not foudn
		return null;
	}
	// //my shit - to output the skiplist as an array
	public Object[] asArray(){
		int numOfNodes = (int) (Math.pow(2,this.maxLevel)-1);
		
		Object[] arr = new Object[numOfNodes];

		SkipListNode<T> ptr = this.root[0];
		int c = 0;
		while(ptr != null){
			arr[c] = ptr.key;

			ptr = ptr.next[0];
			c++;
		}

		return arr;
	}

}