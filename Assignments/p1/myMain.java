public class myMain
{
	public static void cout(Object x){
		System.out.println(x);
	}

	public static void firstKey(SkipList<Integer> skiplist)
	{
		if (skiplist.isEmpty())
			System.out.println("List is empty");
		else
			System.out.println("First key : " + skiplist.first());
	}

	public static void lastKey(SkipList<Integer> skiplist)
	{
		if (skiplist.isEmpty())
			System.out.println("List is empty");
		else
			System.out.println("Last key : " + skiplist.last());
	}

	public static void searchKey(SkipList<Integer> skiplist, Integer key)
	{
		if (skiplist.isEmpty())
			System.out.println("List is empty");
		else
		{
			Integer result = skiplist.search(key);
			if (result != null)
				System.out.println("Found key " + result);
			else
				System.out.println("Key " + key + " not found");	
		}
	}

	public static void printList(SkipList<Integer> skiplist) 
	{ 
    		System.out.println();
		System.out.println("Skip List Content:"); 
    		for (int i = 0; i < skiplist.maxLevel; i++) 
    		{ 
        		SkipListNode<Integer> node = skiplist.root[i]; 
        		System.out.print("Level " + i + ": "); 
        		while (node != null) 
        		{ 
            			System.out.print(node.key + " "); 
            			node = node.next[i]; 
        		} 
        		System.out.println(); 
    		}
		System.out.println();
	} 

	public static void main(String[] args)
	{
		//Practical 1	

		SkipList<Integer> slist = new SkipList<Integer>(10);

		slist.insert(20);
		slist.insert(-6);
		slist.insert(-15);
		slist.insert(22);
		slist.insert(0);
		slist.insert(9);
		slist.insert(43);
		slist.insert(0); //wont show up
		slist.insert(8);
		slist.insert(5);
		slist.insert(-1);

		printList(slist);

		// Object[] sArr = slist.asArray();

		// cout(My.arrayToString(sArr));

		// firstKey(slist);
		// lastKey(slist);

		searchKey(slist, 20); //found
		searchKey(slist, 0); //found
		searchKey(slist, -15); //found
		searchKey(slist, -1); //found
		searchKey(slist, 43); //found
		searchKey(slist, -30); //not

		// int x = (int) My.log(10, 15);
		// cout(x);

		// SkipList<Integer> skiplist = new SkipList<Integer>();
		
		// skiplist.insert(8);

		// skiplist.insert(5);

		// skiplist.insert(12);

		// firstKey(skiplist);
		// lastKey(skiplist);

		// printList(skiplist);
		
		// searchKey(skiplist, 10);

		// skiplist.insert(10);

		// printList(skiplist);

		// searchKey(skiplist, 10);
           
		/* Expected Output:
		First key : 5
		Last key : 12

		Skip List Content:
		Level 0: 5 8 12
		Level 1: 5 12
		Level 2: 5
		Level 3:

		Key 10 not found

		Skip List Content:
		Level 0: 5 8 10 12
		Level 1: 5 12
		Level 2: 5
		Level 3:

		Found key 10
		*/


	}
}
