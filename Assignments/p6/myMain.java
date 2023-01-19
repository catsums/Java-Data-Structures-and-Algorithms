public class myMain {
    public static void main(String[] args) 
    {
    	///TEST 1///
    	cout("-----------Test 1--------------\n");
    	int order = 4;
    	int m = (order/2);

    	BTree<Integer> tree = new BTree(m);

    	Integer[] items = new Integer[]{12,41,32,38,50,69,420,14,44,10,8,9,31};

    	for(Integer item:items){
    		tree.insert(item);
    	}

    	printLevels(tree);

    	Integer[] searchItems = new Integer[]{8,9,31,12,41,40,50,69,400,-1,-2,90,10};

    	for(Integer item:searchItems){
    		cout("Node with "+item+": "+tree.search(item));
    	}

    	cout("Traversing:");
		tree.traverse();

		cout();
		cout("-----------Test 2--------------\n");
		///TEST 2///

		order = 6;
		m = order/2;

		tree = new BTree(m);

		items = new Integer[]{-36, -2, 19, 14, 32, 41, 91, 86, 96, 47, 74, 70, 11, 90};

		for(Integer item:items){
    		tree.insert(item);
    	}

    	printLevels(tree);

    	searchItems = new Integer[]{8,9,31,12,41,40,50,69,400,-1,-2,90,10};

    	for(Integer item:searchItems){
    		cout("Node with "+item+": "+tree.search(item));
    	}

    	cout("Traversing:");
		tree.traverse();

		cout();
		cout("-----------Test 3--------------\n");
		///TEST 3///

		order = 4;
		m = order/2;

		tree = new BTree(m);

		items = new Integer[]{-69,-420,69,420,21,-21,21,69,420,21,96,21,-69};

		for(Integer item:items){
    		tree.insert(item);
    	}

    	printLevels(tree);

    	searchItems = new Integer[]{69,420,21};

    	for(Integer item:searchItems){
    		cout("Node with "+item+": "+tree.search(item));
    	}

    	cout("Traversing:");
		tree.traverse();

		///EXPECTED OUTPUT///
		/*
			-----------Test 1--------------

			[41]
			[12,32]  [69]
			[8,9,10]  [14,31]  [38]  [44,50]  [420]
			Node with 8: [8,9,10]
			Node with 9: [8,9,10]
			Node with 31: [14,31]
			Node with 12: [12,32]
			Node with 41: [41]
			Node with 40: null
			Node with 50: [44,50]
			Node with 69: [69]
			Node with 400: null
			Node with -1: null
			Node with -2: null
			Node with 90: null
			Node with 10: [8,9,10]
			Traversing:
			 8 9 10 12 14 31 32 38 41 44 50 69 420

			-----------Test 2--------------

			[14,41,86]
			[-36,-2,11]  [19,32]  [47,70,74]  [90,91,96]
			Node with 8: null
			Node with 9: null
			Node with 31: null
			Node with 12: null
			Node with 41: [14,41,86]
			Node with 40: null
			Node with 50: null
			Node with 69: null
			Node with 400: null
			Node with -1: null
			Node with -2: [-36,-2,11]
			Node with 90: [90,91,96]
			Node with 10: null
			Traversing:
			 -36 -2 11 14 19 32 41 47 70 74 86 90 91 96

			-----------Test 3--------------

			[21]
			[-69]  [69]
			[-420,-69]  [-21,21,21]  [21,69]  [96,420,420]
			Node with 69: [69]
			Node with 420: [96,420,420]
			Node with 21: [21]
			Traversing:
			 -420 -69 -69 -21 21 21 21 21 69 69 96 420 420
		*/
    }

    ///output but lazier
    public static void cout(Object x){
    	System.out.println(x);
    }
    public static void coutf(Object x){
    	System.out.print(x);
    }
    public static void cout(){
    	System.out.println();
    }
    public static void coutf(){
    	System.out.print("");
    }

    ///get height of node
    public static int getHeight(BTreeNode node){
    	if(node.leaf){
			return 0;
		}else{
			return getHeight(node.references[0]) + 1;
		}
    }

    ///get array of nodes at certain level, similar to BSTree but BFT traversal
    public static Object[] getNodesAtLevel(BTreeNode node, int level){
    	Object[] arr = new Object[0];
		int _len = 0;
		if(level==0){
			arr = resizeArray(arr,1);
			arr[0] = node;
			_len++;
		}else if(!node.leaf){
			for(int c=0;c<=node.keyTally;c++){
				if(node.references[c]==null){
					break;
				}

				Object[] _arr = getNodesAtLevel(node.references[c],level-1);
				if(_arr.length>0){
					arr = resizeArray(arr,_arr.length);
					for(int d=0;d<_arr.length;d++){
						arr[_len] = _arr[d];
						_len++;
					}
				}
			}
		}

		return arr;
    }

    ///resize any array by a certain amount
    public static Object[] resizeArray(Object[] arr, int resize){
        Object[] newArr = new Object[arr.length+resize];
        for(int i=0;i<arr.length;i++){
            if(i >= newArr.length){
                break;
            }
            newArr[i] = arr[i];
        }
        return newArr;
    }

    ///print out the BTree level by level, this uses BFT traversal
    public static void printLevels(BTree tree){
    	int _height = getHeight(tree.root);

    	for(int h=0;h<=_height;h++){
    		Object[] nodes = getNodesAtLevel(tree.root, h);
    		for(Object node:nodes){
    			System.out.print(node+" ");
    		}
    		System.out.println("");
    	}

    }

    ///shift array to open a certain index
    public static Object shiftArrayRight(Object[] arr, int start){
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

	}public static Object shiftArrayRight(Object[] arr){
		return shiftArrayRight(arr, 0);
	}

	///shift array to open a certain index
	public static Object shiftArrayLeft(Object[] arr, int end){
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

	}public static Object shiftArrayLeft(Object[] arr){
		return shiftArrayLeft(arr, arr.length-1);
	}
    
}