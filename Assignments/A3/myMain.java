public class myMain {
	

    public static void main(String[] args) 
    {
    	//////////////////////////////

    	cout("------Main start-------\n");

    	cout("\n-----TEST 1-----\n");

    	Object[][] table = {
    		new Object[]{1,2,3,4,5,6,7},
    		new Object[]{"A","B","C","D","E","F","G"},
    		new Object[]{"H","F","P","G","M","T","A"},
    		new Object[]{90,5,100,1,16,-2,4}
    	};

    	int order = 4;

    	BPTree[] trees = new BPTree[table.length];
    	trees[0] = new BPTree<Integer,Integer>(order);
    	trees[1] = new BPTree<String,Integer>(order);
    	trees[2] = new BPTree<String,Integer>(order);
    	trees[3] = new BPTree<Integer,Integer>(order);


    	for(int l=0;l<table.length;l++){
    		for(int c=0;c<table[l].length;c++){
    			trees[l].insert((Comparable) table[l][c], table[0][c]);
    		}
    	}

    	for(BPTree tree:trees){
    		// printLevels(tree);
    		tree.print();
    		My.cout(My.arrayToString(tree.values()));
    	}

    	////////////////////////

    	cout("\n-----TEST 2-----\n");

    	order = 4;

    	Integer[] arr = {1,5,8,7,9,15,11,23,17,20,10}; //order 4
    	// Integer[] arr = {42,40,62,53,39,60,19,41,38,69,28,15,37,96,23,47,90,50,51}; //order 5

    	BPTree<Integer, Integer> tree = new BPTree<Integer, Integer>(order);

    	for(Integer item:arr){
    		cout("-- INSERT "+item+" --");
    		tree.insert(item, item);
    		printLevels(tree);
    	}

    	printLevels(tree);

    	Integer[] sarr = {1,90,15,11,23,17,20,-19,19,41,28,42};

    	for(Integer item:sarr){
    		cout("-- FIND "+item+" --");
    		boolean val = (tree.search(item) != null);
    		cout("Found?: "+val);
    	}

		Integer[] dArr = {11,20,5,9,23,15,17,10,1,8};

    	for(Integer item:dArr){
    		cout("Delete"+item);
    		tree.delete(item);
    		printLevels(tree);
    	}

    	/////////////////////

    	cout("\n-----TEST 3-----\n");

    	order = 5;

    	arr = new Integer[]{42,40,62,53,39,60,19,41,38,69,28,15,37,96,23,47,90,50,51}; //order 5

    	tree = new BPTree<Integer, Integer>(order);

    	for(Integer item:arr){
    		cout("-- INSERT "+item+" --");
    		tree.insert(item, item);
    		printLevels(tree);
    	}

    	printLevels(tree);

    	sarr = new Integer[]{1,90,15,11,23,17,20,-19,19,41,28,42};

    	for(Integer item:sarr){
    		cout("-- FIND "+item+" --");
    		boolean val = (tree.search(item) != null);
    		cout("Found?: "+val);
    	}

		dArr = arr;

    	for(Integer item:dArr){
    		cout("Delete"+item);
    		tree.delete(item);
    		printLevels(tree);
    	}
    	/////////////////////

		cout("------Main end-------\n");
    }

    /*
		------Main start-------

		-----TEST 1-----

		Level 1 [ 3 5]
		Level 2 [ 1 2]
		Level 2 [ 3 4]
		Level 2 [ 5 6 7]

		[1, 2, 3, 4, 5, 6, 7]
		Level 1 [ C E]
		Level 2 [ A B]
		Level 2 [ C D]
		Level 2 [ E F G]

		[1, 2, 3, 4, 5, 6, 7]
		Level 1 [ H P]
		Level 2 [ A F G]
		Level 2 [ H M]
		Level 2 [ P T]

		[7, 2, 4, 1, 5, 3, 6]
		Level 1 [ 5 90]
		Level 2 [ -2 1 4]
		Level 2 [ 5 16]
		Level 2 [ 90 100]

		[6, 4, 7, 2, 5, 1, 3]

		-----TEST 2-----

		-- INSERT 1 --
		[1]
		-- INSERT 5 --
		[1, 5]
		-- INSERT 8 --
		[1, 5, 8]
		-- INSERT 7 --
		[7]
		[1, 5] [7, 8]
		-- INSERT 9 --
		[7]
		[1, 5] [7, 8, 9]
		-- INSERT 15 --
		[7, 9]
		[1, 5] [7, 8] [9, 15]
		-- INSERT 11 --
		[7, 9]
		[1, 5] [7, 8] [9, 11, 15]
		-- INSERT 23 --
		[7, 9, 15]
		[1, 5] [7, 8] [9, 11] [15, 23]
		-- INSERT 17 --
		[7, 9, 15]
		[1, 5] [7, 8] [9, 11] [15, 17, 23]
		-- INSERT 20 --
		[15]
		[7, 9] [20]
		[1, 5] [7, 8] [9, 11] [15, 17] [20, 23]
		-- INSERT 10 --
		[15]
		[7, 9] [20]
		[1, 5] [7, 8] [9, 10, 11] [15, 17] [20, 23]
		[15]
		[7, 9] [20]
		[1, 5] [7, 8] [9, 10, 11] [15, 17] [20, 23]
		-- FIND 1 --
		Found?: true
		-- FIND 90 --
		Found?: false
		-- FIND 15 --
		Found?: true
		-- FIND 11 --
		Found?: true
		-- FIND 23 --
		Found?: true
		-- FIND 17 --
		Found?: true
		-- FIND 20 --
		Found?: true
		-- FIND -19 --
		Found?: false
		-- FIND 19 --
		Found?: false
		-- FIND 41 --
		Found?: false
		-- FIND 28 --
		Found?: false
		-- FIND 42 --
		Found?: false
		Delete11
		[15]
		[7, 9] [20]
		[1, 5] [7, 8] [9, 10] [15, 17] [20, 23]
		Delete20
		[15]
		[7, 9] [20]
		[1, 5] [7, 8] [9, 10] [15, 17] [23]
		Delete5
		[15]
		[7, 9] [20]
		[1] [7, 8] [9, 10] [15, 17] [23]
		Delete9
		[15]
		[7, 9] [20]
		[1] [7, 8] [10] [15, 17] [23]
		Delete23
		[15]
		[7, 9] [17]
		[1] [7, 8] [10] [15] [17]
		Delete15
		[9]
		[7] [15]
		[1] [7, 8] [10] [17]
		Delete17
		[7, 9]
		[1] [7, 8] [10]
		Delete10
		[7, 8]
		[1] [7] [8]
		Delete1
		[8]
		[7] [8]
		Delete8
		[7]

		-----TEST 3-----

		-- INSERT 42 --
		[42]
		-- INSERT 40 --
		[40, 42]
		-- INSERT 62 --
		[40, 42, 62]
		-- INSERT 53 --
		[40, 42, 53, 62]
		-- INSERT 39 --
		[42]
		[39, 40] [42, 53, 62]
		-- INSERT 60 --
		[42]
		[39, 40] [42, 53, 60, 62]
		-- INSERT 19 --
		[42]
		[19, 39, 40] [42, 53, 60, 62]
		-- INSERT 41 --
		[42]
		[19, 39, 40, 41] [42, 53, 60, 62]
		-- INSERT 38 --
		[39, 42]
		[19, 38] [39, 40, 41] [42, 53, 60, 62]
		-- INSERT 69 --
		[39, 42, 60]
		[19, 38] [39, 40, 41] [42, 53] [60, 62, 69]
		-- INSERT 28 --
		[39, 42, 60]
		[19, 28, 38] [39, 40, 41] [42, 53] [60, 62, 69]
		-- INSERT 15 --
		[39, 42, 60]
		[15, 19, 28, 38] [39, 40, 41] [42, 53] [60, 62, 69]
		-- INSERT 37 --
		[28, 39, 42, 60]
		[15, 19] [28, 37, 38] [39, 40, 41] [42, 53] [60, 62, 69]
		-- INSERT 96 --
		[28, 39, 42, 60]
		[15, 19] [28, 37, 38] [39, 40, 41] [42, 53] [60, 62, 69, 96]
		-- INSERT 23 --
		[28, 39, 42, 60]
		[15, 19, 23] [28, 37, 38] [39, 40, 41] [42, 53] [60, 62, 69, 96]
		-- INSERT 47 --
		[28, 39, 42, 60]
		[15, 19, 23] [28, 37, 38] [39, 40, 41] [42, 47, 53] [60, 62, 69, 96]
		-- INSERT 90 --
		[42]
		[28, 39] [60, 69]
		[15, 19, 23] [28, 37, 38] [39, 40, 41] [42, 47, 53] [60, 62] [69, 90, 96]
		-- INSERT 50 --
		[42]
		[28, 39] [60, 69]
		[15, 19, 23] [28, 37, 38] [39, 40, 41] [42, 47, 50, 53] [60, 62] [69, 90, 96]
		-- INSERT 51 --
		[42]
		[28, 39] [50, 60, 69]
		[15, 19, 23] [28, 37, 38] [39, 40, 41] [42, 47] [50, 51, 53] [60, 62] [69, 90, 96]
		[42]
		[28, 39] [50, 60, 69]
		[15, 19, 23] [28, 37, 38] [39, 40, 41] [42, 47] [50, 51, 53] [60, 62] [69, 90, 96]
		-- FIND 1 --
		Found?: false
		-- FIND 90 --
		Found?: true
		-- FIND 15 --
		Found?: true
		-- FIND 11 --
		Found?: false
		-- FIND 23 --
		Found?: true
		-- FIND 17 --
		Found?: false
		-- FIND 20 --
		Found?: false
		-- FIND -19 --
		Found?: false
		-- FIND 19 --
		Found?: true
		-- FIND 41 --
		Found?: true
		-- FIND 28 --
		Found?: true
		-- FIND 42 --
		Found?: true
		Delete42
		[41]
		[28, 39] [50, 60, 69]
		[15, 19, 23] [28, 37, 38] [39, 40] [41, 47] [50, 51, 53] [60, 62] [69, 90, 96]
		Delete40
		[41]
		[28, 38] [50, 60, 69]
		[15, 19, 23] [28, 37] [38, 39] [41, 47] [50, 51, 53] [60, 62] [69, 90, 96]
		Delete62
		[41]
		[28, 38] [50, 53, 69]
		[15, 19, 23] [28, 37] [38, 39] [41, 47] [50, 51] [53, 60] [69, 90, 96]
		Delete53
		[41]
		[28, 38] [50, 53, 90]
		[15, 19, 23] [28, 37] [38, 39] [41, 47] [50, 51] [60, 69] [90, 96]
		Delete39
		[50]
		[28, 41] [53, 90]
		[15, 19, 23] [28, 37, 38] [41, 47] [50, 51] [60, 69] [90, 96]
		Delete60
		[28, 41, 50, 90]
		[15, 19, 23] [28, 37, 38] [41, 47] [50, 51, 69] [90, 96]
		Delete19
		[28, 41, 50, 90]
		[15, 23] [28, 37, 38] [41, 47] [50, 51, 69] [90, 96]
		Delete41
		[28, 38, 50, 90]
		[15, 23] [28, 37] [38, 47] [50, 51, 69] [90, 96]
		Delete38
		[28, 38, 51, 90]
		[15, 23] [28, 37] [47, 50] [51, 69] [90, 96]
		Delete69
		[28, 38, 90]
		[15, 23] [28, 37] [47, 50, 51] [90, 96]
		Delete28
		[28, 50, 90]
		[15, 23] [37, 47] [50, 51] [90, 96]
		Delete15
		[50, 90]
		[23, 37, 47] [50, 51] [90, 96]
		Delete37
		[50, 90]
		[23, 47] [50, 51] [90, 96]
		Delete96
		[50]
		[23, 47] [50, 51, 90]
		Delete23
		[51]
		[47, 50] [51, 90]
		Delete47
		[50, 51, 90]
		Delete90
		[50, 51]
		Delete50
		[51]
		Delete51
		[]
		------Main end-------
    */

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

    //////ARRAY HELPERS////////

    protected static Object shiftArrayRight(Object[] arr, int start){
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

	}protected static Object shiftArrayRight(Object[] arr){
		return shiftArrayRight(arr, 0);
	}

	protected static Object shiftArrayLeft(Object[] arr, int end){
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

	}protected static Object shiftArrayLeft(Object[] arr){
		return shiftArrayLeft(arr, arr.length-1);
	}

	protected static Object innerShiftArrayRight(Object[] arr, int end){
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
	}protected static Object innerShiftArrayRight(Object[] arr){
		return innerShiftArrayLeft(arr, arr.length-1);
	}

	protected static Object innerShiftArrayLeft(Object[] arr, int start){
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
	}protected static Object innerShiftArrayLeft(Object[] arr){
		return innerShiftArrayLeft(arr, 0);
	}

    //////TREE HELPERS///////

    ///get height of node
    public static int getHeight(BPTreeNode node){
    	if(node.isLeaf()){
			return 0;
		}else{
			return getHeight(((BPTreeInnerNode) node).getRef(0)) + 1;
		}
    }

    ///get array of nodes at certain level, similar to BSTree but BFT traversal
    public static Object[] getNodesAtLevel(BPTreeNode node, int level){
    	Object[] arr = new Object[0];
		int _len = 0;
		if(level==0){
			arr = resizeArray(arr,1);
			arr[0] = node;
			_len++;
		}else if(!node.isLeaf()){

			int keyTally = node.getKeyCount();
			for(int c=0;c<=keyTally;c++){
				if(( (BPTreeInnerNode) node ).getRef(c)==null){
					break;
				}

				Object[] _arr = getNodesAtLevel(( (BPTreeInnerNode) node ).getRef(c), level-1);
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

    ///clone an array
    public static Object[] cloneArray(Object[] arr){
    	return resizeArray(arr, 0);
    }

    ///print out the BTree level by level, this uses BFT traversal
    public static void printLevels(BPTree tree){
    	int _height = getHeight(tree.getRoot());

    	for(int h=0;h<=_height;h++){
    		Object[] nodes = getNodesAtLevel(tree.getRoot(), h);
    		for(Object node:nodes){
    			BPTreeNode _node = (BPTreeNode) node;
    			Object[] keys = new Object[_node.getKeyCount()];
    			for(int i=0;i<keys.length;i++){
    				keys[i] = _node.getKey(i);
    			}
    			System.out.print(arrayToString(keys)+" ");
    		}
    		System.out.println("");
    	}

    }
    //array to string
    public static String arrayToString(Object[] arr){
		String out = "[";
		for(int c=0;c<arr.length;c++){
			out += arr[c];
			if(c<arr.length-1){
				out+=", ";
			}
		}
		out+="]";
		return out;
	}



    
}