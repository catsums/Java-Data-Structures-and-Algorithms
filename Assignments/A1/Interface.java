import java.text.DecimalFormat;

public class Interface {

	private Node origin;

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below

	public Interface() {
		this.origin = new Node(new Origin(),0,0);

	}

	public Interface(Node[] arr) {
		this.origin = new Node(new Origin(),0,0);

		for(int c=0;c<arr.length;c++){
			if(arr[c]==null) continue;
			this.addPoint(arr[c]);
		}
	}

	public Node getOrigin() {
		return this.origin;
	}

	public float addPoint(Function function, int v1, int v2) {
		float val = Float.NaN;
		if(v1==0 || v2==0){
			return val;
		}
		Node newNode = new Node(function,v1,v2);

		//create axies
		Node v1ax = new Node(new V1Axis(), v1, 0);
		Node v2ax = new Node(new V2Axis(), 0, v2);
		//create potential directors
		Node _left = null;
		Node _right = null;
		Node _up = null;
		Node _down = null;

		Node ptr1 = this.origin;
		Node prev1 = null;
		Node ptr2 = this.origin;
		Node prev2 = null;

		boolean[] neg = new boolean[2];
		neg[0] = (v1>0)?false:true;
		neg[1] = (v2>0)?false:true;

		boolean branched = false;

		// My.cout("\nv1: "+v1+"\t v2: "+v2+"\n");

		//check for v1 axis
		while(ptr1!=null){
			int[] vars = ptr1.getVariables();
			//if v1 of ptr1 is the same as newNode, a v1 axis exists, replace it with an existing one
			if(vars[0] == v1){
				// My.cout("v1ax already exists");
				v1ax = ptr1;
				break;
			}else if( (vars[0] > v1 && !neg[0])||(vars[0] < v1 && neg[0]) ){
				// My.cout("new v1ax must be in between");
				if(!neg[0]){
					prev1.right = v1ax;
					ptr1.left = v1ax;

					v1ax.left = prev1;
					v1ax.right = ptr1;
				}else{
					prev1.left = v1ax;
					ptr1.right = v1ax;

					v1ax.right = prev1;
					v1ax.left = ptr1;
				}
				break;
			}
			prev1 = ptr1;
			if(!neg[0]){
				ptr1 = ptr1.right;
			}else{
				ptr1 = ptr1.left;
			}
		}
		if(ptr1==null && prev1!=null){
			// My.cout("prev1 isnt null");
			if(!neg[0]){
				prev1.right = v1ax;

				v1ax.left = prev1;
			}else{
				prev1.left = v1ax;

				v1ax.right = prev1;
			}
		}
		//for chosen axis check for Up/Down nodes
		prev1 = null;
		ptr1 = v1ax;

		while(ptr1!=null){
			int[] vars = ptr1.getVariables();
			//if v2 of ptr1 is the same as newNode, here it has the same position as newNode
			if(vars[1] == v2){
				//add using linked list
				branched = true;

				this.linkedListInsert(ptr1, newNode);
				// My.cout("Linked List insert");
				// //axis will not point to new node so set new node stuff to 0
				// newNode.up = null;
				// newNode.left = null;
				// newNode.right = null;
				// newNode.down = null;
				break;
			}else if( (vars[1]>v2 && !neg[1])||(vars[1]<v2 && neg[1]) ){
				// if(branched){
				// 	My.cout("Crazyyy");
				// }
				if(!neg[1]){
					prev1.up = newNode;
					ptr1.down = newNode;

					_down = prev1;
					_up = ptr1;
				}else{
					prev1.down = newNode;
					ptr1.up = newNode;

					_down = ptr1;
					_up = prev1;
				}
				break;
			}

			prev1 = ptr1;
			if(!neg[1]){
				ptr1 = ptr1.up;
			}else{
				ptr1 = ptr1.down;
			}
		}
		if(ptr1==null && prev1!=null){
			// if(branched){
			// 		My.cout("Crazyyyer");
			// 	}
			if(!neg[1]){
				prev1.up = newNode;

				_down = prev1;
			}else{
				prev1.down = newNode;

				_up = prev1;
			}
		}

		//check for v2 axis
		while(ptr2!=null){
			int[] vars = ptr2.getVariables();
			//if v1 of ptr2 is the same as newNode, a v2 axis exists, replace it with an existing one
			if(vars[1] == v2){
				v2ax = ptr2;
				break;
			}else if( (vars[1] > v2 && !neg[1])||(vars[1] < v2 && neg[1]) ){
				if(!neg[1]){
					prev2.up = v2ax;
					ptr2.down = v2ax;

					v2ax.down = prev2;
					v2ax.up = ptr2;
				}else{
					prev2.down = v2ax;
					ptr2.up = v2ax;

					v2ax.up = prev2;
					v2ax.down = ptr2;
				}
				break;
			}
			prev2 = ptr2;
			if(!neg[1]){
				ptr2 = ptr2.up;
			}else{
				ptr2 = ptr2.down;
			}
		}
		if(ptr1==null && prev2!=null){
			if(!neg[1]){
				prev2.up = v2ax;

				v2ax.down = prev2;
			}else{
				prev2.down = v2ax;

				v2ax.up = prev2;
			}
		}
		//for chosen axis check for Up/Down nodes
		prev2 = null;
		ptr2 = v2ax;

		while(ptr2!=null){
			int[] vars = ptr2.getVariables();
			//if v1 of ptr2 is the same as newNode, here it has the same position as newNode
			if(vars[0] == v1){
				//add using linked list
				if(!branched){
					branched = true;

					this.linkedListInsert(ptr2, newNode);
					// My.cout("Linked List insert");
					//axis will not point to new node so set new node stuff to 0
					// newNode.up = null;
					// newNode.left = null;
					// newNode.right = null;
					// newNode.down = null;
				}
				
				break;
			}else if( (vars[0]>v1 && !neg[0])||(vars[0]<v1 && neg[0]) ){
				// if(branched){
				// 	My.cout("Crazyyy");
				// }
				if(!neg[0]){
					prev2.right = newNode;
					ptr2.left = newNode;

					_left = prev2;
					_right = ptr2;
				}else{
					prev2.left = newNode;
					ptr2.right = newNode;

					_left = ptr2;
					_right = prev2;
				}
				break;
			}

			prev2 = ptr2;
			if(!neg[0]){
				ptr2 = ptr2.right;
			}else{
				ptr2 = ptr2.left;
			}
		}
		if(ptr2==null && prev2!=null){
			// if(branched){
			// 	My.cout("Crazyyyer");
			// }
			if(!neg[0]){
				prev2.right = newNode;

				_left = prev2;
			}else{
				prev2.left = newNode;

				_right = prev2;
			}
		}

		//set redirectors to the newNode
		if(!branched){
			newNode.up = _up;
			newNode.down = _down;
			newNode.left = _left;
			newNode.right = _right;
		}
		// else{
		// 	My.cout("Uncrazy");
		// }

		//print out optional
		// My.cout("newNode:\t "+newNode);
		// for(String link:newNode.getNodeLinks()){My.cout(">>\t "+link);}

		// My.cout("origin:\t "+origin);
		// for(String link:origin.getNodeLinks()){My.cout(">>\t "+link);}

		// My.cout("v1ax:\t "+v1ax);
		// for(String link:v1ax.getNodeLinks()){My.cout(">>\t "+link);}

		// My.cout("v2ax:\t "+v2ax);
		// for(String link:v2ax.getNodeLinks()){My.cout(">>\t "+link);}

		//assume function is never null but yknow just in case
		if(function!=null){
			val = function.calculate(v1,v2);
		}

		return val;

	}

	public Node removePoint(int v1, int v2) {
		if(v1==0 || v2==0){
			return null;
		}

		Node ptr = this.origin;
		Node removed = null;
		boolean[] neg = new boolean[2];
		neg[0] = (v1>0)?false:true;
		neg[1] = (v2>0)?false:true;

		while(ptr!=null && removed==null){
			int[] vars = ptr.getVariables();

			if(vars[0] == v1){
				Node ptr2 = ptr;
				//find node from v1axis
				while(ptr2!=null && removed==null){
					int[] vars2 = ptr2.getVariables();
					if(vars2[1] == v2){
						//asume its the of that coord
						removed = ptr2;
						break;
					}
					if(!neg[1]){
						ptr2 = ptr2.up;
					}else{
						ptr2 = ptr2.down;
					}
				}
			}

			if(!neg[0]){
				ptr = ptr.right;
			}else{
				ptr = ptr.left;
			}

		}
		if(removed!=null){
			if(removed.nextVal!=null){
				this.linkedListRemove(removed);
			}else{
				Node rem_up = removed.up;
				Node rem_down = removed.down;
				Node rem_left = removed.left;
				Node rem_right = removed.right;

				if(rem_up!=null){
					rem_up.down = rem_down;
				}
				if(rem_down!=null){
					rem_down.up = rem_up;
				}
				if(rem_left!=null){
					rem_left.right = rem_right;
				}
				if(rem_right!=null){
					rem_right.left = rem_left;
				}
			}
			
		}

		return removed;
	}

	public Node getPoint(int v1, int v2) {
		if(v1==0 || v2==0){
			return null;
		}

		Node ptr = this.origin;
		Node point = null;
		boolean[] neg = new boolean[2];
		neg[0] = (v1>0)?false:true;
		neg[1] = (v2>0)?false:true;

		while(ptr!=null && point==null){
			int[] vars = ptr.getVariables();

			if(vars[0] == v1){
				Node ptr2 = ptr;
				//find node from v1axis
				while(ptr2!=null && point==null){
					int[] vars2 = ptr2.getVariables();
					if(vars2[1] == v2){
						//asume its the of that coord
						point = ptr2;
						break;
					}
					if(!neg[1]){
						ptr2 = ptr2.up;
					}else{
						ptr2 = ptr2.down;
					}
				}
			}

			if(!neg[0]){
				ptr = ptr.right;
			}else{
				ptr = ptr.left;
			}

		}

		return point;
	}

	public Node[] toArray() {
		//start from v1 right most then for each v1 axis, count nodes upper most to lower post

		Node[] arr = new Node[0];

		Node ptr = this.origin;
		Node v1Most = null; //right most axisnode
		Node v2Most = null;	//upper most node (exclude axis)

		//get rightmost axisnode
		v1Most = ptr;
		while(ptr!=null){
			v1Most = ptr;
			ptr = ptr.right;
		}
		// My.cout("v1Most:\t"+v1Most);

		//for each v1Axis node
		while(v1Most!=null){
			int[] vars1 = v1Most.getVariables();
			//if v1 is not equal 0, otherwise its just the origin
			if(vars1[0] != 0){
				//get the uppermost axis node
				ptr = v1Most;
				v2Most = ptr;
				while(ptr!=null){
					v2Most = ptr;
					ptr = ptr.up;
				}
				// My.cout("v2Most:\t"+v2Most);

				//for current node, add it to the array
				while(v2Most!=null){
					int[] vars2 = v2Most.getVariables();
					//if v2 is not equal 0, othewise its an axis
					if(vars2[1] != 0){
						//increase array and then push node into it
						arr = this.incrementArray(arr);
						arr[arr.length-1] = v2Most;
					}
					//if theres nextVal, use linkedlist traversal
					Node nextPtr = v2Most.nextVal;
					while(nextPtr!=null){
						//increment array and add next node
						arr = this.incrementArray(arr);
						arr[arr.length-1] = nextPtr;
						//traverse
						nextPtr = nextPtr.nextVal;
					}
					v2Most = v2Most.down;
				}
			}
			v1Most = v1Most.left;
		}

		return arr;
	}

	public float calculateValue(Function function, int v1, int v2) {
		if(function==null){
			return Float.NaN;
		}
		return function.calculate(v1,v2);
	}

	public float findMaxValue() {
		Node maxNode = this.findMax();
		float val = Float.NaN;

		if(maxNode!=null){
			val = maxNode.getValue();
		}

		return val;
	}

	public Node findMax() {
		Node[] arr = this.toArray();

		Node _max = null;

		for(Node node:arr){
			if(_max!=null){
				float maxVal = _max.getValue();
				float nodeVal = node.getValue();
				int[] nodeVars = node.getVariables();
				int[] maxVars = _max.getVariables();
				Function maxFunc = _max.getFunction();
				Function nodeFunc = node.getFunction();

				if(maxVal < nodeVal){
					_max = node;

				//if nodes have same val
				}else if(nodeVal == maxVal){
					//check if v1 is lower than max
					if(maxVars[0] > nodeVars[0]){
						_max = node;
					//if nodes have the same v1
					}else if(maxVars[0] == nodeVars[0]){
						//check if v2 is higher than max
						if(maxVars[1] < nodeVars[1]){
							_max = node;
						//if nodes have same v2
						}else if(maxVars[1] == nodeVars[1]){
							if(maxFunc==null){
								_max = node;
							}else if(nodeFunc!=null){
								String maxFuncName = maxFunc.getFunctionName();
								String nodeFuncName = nodeFunc.getFunctionName();

								int comp = maxFuncName.compareTo(nodeFuncName);
								if(comp < 0){
									_max = node;
								}
							}
						}
					}
				}
			}else{
				_max = node;
			}
		}

		return _max;
	}

	public float findMinValue() {
		Node minNode = this.findMin();
		float val = Float.NaN;

		if(minNode!=null){
			val = minNode.getValue();
		}

		return val;
	}

	public Node findMin() {
		Node[] arr = this.toArray();

		Node _min = null;

		for(Node node:arr){
			if(_min!=null){
				float minVal = _min.getValue();
				float nodeVal = node.getValue();
				int[] nodeVars = node.getVariables();
				int[] minVars = _min.getVariables();
				Function minFunc = _min.getFunction();
				Function nodeFunc = node.getFunction();

				if(minVal > nodeVal){
					_min = node;

				//if nodes have same val
				}else if(nodeVal == minVal){
					//check if v1 is lower than min
					if(minVars[0] < nodeVars[0]){
						_min = node;
					//if nodes have the same v1
					}else if(minVars[0] == nodeVars[0]){
						//check if v2 is lower than min
						if(minVars[1] > nodeVars[1]){
							_min = node;
						//if nodes have same v2
						}else if(minVars[1] == nodeVars[1]){
							if(minFunc==null){
								_min = node;
							}else if(nodeFunc!=null){
								String minFuncName = minFunc.getFunctionName();
								String nodeFuncName = nodeFunc.getFunctionName();

								int comp = minFuncName.compareTo(nodeFuncName);
								if(comp > 0){
									_min = node;
								}
							}
						}
					}
				}
			}else{
				_min = node;
			}
		}

		return _min;
	}

	public String printFunctionValues(String functionName) {
		Node[] arr = new Node[0];

		Node[] nodeArr = this.toArray();

		for(Node node:nodeArr){
			Function func = node.getFunction();

			if(func!=null && func.getFunctionName().equals(functionName)){
				arr = this.incrementArray(arr);
				arr[arr.length - 1] = node;
			}
		}

		String out = "";

		for(int n=arr.length-1;n>=0;n--){
			out += this.floatFormatter(arr[n].getValue());
			if(n>0){
				out += ";";
			}
		}

		return out;
	}

	public int removeAllFunctionPoints(String functionName){
		Node[] arr = new Node[0];

		Node[] nodeArr = this.toArray();

		for(Node node:nodeArr){
			Function func = node.getFunction();

			if(func!=null && func.getFunctionName().equals(functionName)){
				arr = this.incrementArray(arr);
				arr[arr.length - 1] = node;
			}

		}

		int removed = arr.length;

		for(int n=0;n<arr.length;n++){
			int[] vars = arr[n].getVariables();
			this.removePoint(vars[0], vars[1]);
		}

		return removed;
	}

	public int countNumberOfPoints(){
		Node[] arr = this.toArray();

		return arr.length;
	}

	public int[] numPointsPerQuadrant(){
		Node[][] quadrants = new Node[4][0];

		Node[] nodes = this.toArray();

		for(Node node:nodes){
			int[] vars = node.getVariables();
			if(vars[0]>0 && vars[1]>0){
				quadrants[0] = this.incrementArray(quadrants[0]);
				quadrants[0][quadrants[0].length-1] = node;
			}else if(vars[0]<0 && vars[1]>0){
				quadrants[1] = this.incrementArray(quadrants[1]);
				quadrants[1][quadrants[1].length-1] = node;
			}else if(vars[0]<0 && vars[1]<0){
				quadrants[2] = this.incrementArray(quadrants[2]);
				quadrants[2][quadrants[2].length-1] = node;
			}else if(vars[0]>0 && vars[1]<0){
				quadrants[3] = this.incrementArray(quadrants[3]);
				quadrants[3][quadrants[3].length-1] = node;
			}
		}

		int[] numPoints = new int[4];
		for(int i=0; i<quadrants.length; i++){
			numPoints[i] = quadrants[i].length;
		}

		return numPoints;

	}

	public void clearAllData(){
		//remove all directions of origin
		this.origin.left = null;
		this.origin.right = null;
		this.origin.up = null;
		this.origin.down = null;
		this.origin.prevVal = null;
		this.origin.nextVal = null;
	}

	//ADD HELPER FUNCTIONS BELOW

	//custom function to resize array
	private Node[] incrementArray(Node[] arr){
		Node[] newArr = new Node[arr.length + 1];
		for(int n=0;n<arr.length;n++){
			newArr[n] = arr[n];
		}
		return newArr;
	}
	//my custom override for addPoint
	public float addPoint(Node newNode){
		float val = Float.NaN;
		if(newNode!=null){
			int[] vars = newNode.getVariables();
			val = this.addPoint(newNode.getFunction(), vars[0],vars[1]);
		}
		return val;
	}
	//my custom func addPoints
	public float[] addPoints(Node[] newNodes){
		float[] vals = new float[newNodes.length];
		for(int c=0;c<newNodes.length;c++){
			vals[c] = this.addPoint(newNodes[c]);
		}
		return vals;
	}
	//////////////////////////////////////////

	public float XaddPoint(Function function, int v1, int v2) {
		Node newNode = new Node(function,v1,v2);
		float val = Float.NaN;

		//v1 and v2 are set to 0 :. no new node added
		if(v1==0 || v2==0){
			return Float.NaN;
		}
		Node v1ax = new Node(new V1Axis(), v1, 0);
		Node v2ax = new Node(new V2Axis(), 0, v2);

		//select v1 axis node
		Node ptr1 = this.origin;
		Node prev1 = null;
		boolean neg1 = (v1>0)?false:true;
		while(ptr1!=null){
			int[] vars = ptr1.getVariables();
			Node next = null;
			if(vars[0] == v1){
				v1ax = ptr1;
				break;
			}
			if(!neg1){
				if(vars[0]>v1){
					ptr1.left = v1ax;
					v1ax.right = ptr1;
					if(prev1!=null){
						prev1.right = v1ax;
						v1ax.left = prev1;
					}
					break;
				}
				prev1 = ptr1;
				next = ptr1.right;
			}else{
				if(vars[0]<v1){
					ptr1.right = v1ax;
					v1ax.left = ptr1;
					if(prev1!=null){
						prev1.left = v1ax;
						v1ax.right = prev1;
					}
					break;
				}
				prev1 = ptr1;
				next = ptr1.left;
			}
			ptr1 = next;
		}
		//if theres no ptr but theres a prev, point furthest axis node to the newest
		if(ptr1==null){
			if(prev1!=null){
				if(!neg1){
					prev1.right = v1ax;
					v1ax.left = prev1;
				}else{
					prev1.left = v1ax;
					v1ax.right = prev1;
				}
			}
		}

		//select v2 axis node
		Node ptr2 = this.origin;
		Node prev2 = null;
		boolean neg2 = (v2>0)?false:true;
		while(ptr2!=null){
			int[] vars = ptr2.getVariables();
			Node next = null;
			if(vars[1] == v2){
				v2ax = ptr2;
				break;
			}
			if(!neg2){
				if(vars[1]>v2){
					ptr2.down = v2ax;
					
					v2ax.up = ptr2;
					if(prev2!=null){
						prev2.up = v2ax;
						v2ax.down = prev2;
					}
					break;
				}
				prev2 = ptr2;
				next = ptr2.up;
			}else{
				if(vars[1]<v2){
					ptr2.up = v2ax;
					
					v2ax.down = ptr2;
					if(prev2!=null){
						prev2.down = v2ax;
						v2ax.up = prev2;
					}
					break;
				}
				prev2 = ptr2;
				next = ptr2.down;
			}
			ptr2 = next;
		}
		//if theres no ptr but theres a prev, point furthest axis node to the newest
		if(ptr2==null){
			if(prev2!=null){
				if(!neg2){
					prev2.up = v2ax;
					v2ax.down = prev2;
				}else{
					prev2.down = v2ax;
					v2ax.up = prev2;
				}
			}
		}

		//get the nodes pointed by the axies and point the new node to those
		boolean branched = false;

		Node oldNewNode1 = null;
		if(!neg1){
			oldNewNode1 = v2ax.right;

			if(oldNewNode1!=null){
				//check if oldnode has same coords as new node
				int[] oldNewNode1Vars = oldNewNode1.getVariables();
				if(oldNewNode1Vars[0] == v1 && oldNewNode1Vars[1] == v2){
					this.linkedListInsert(oldNewNode1, newNode);
					//axis will not point to new node so set new node stuff to 0
					// newNode.up = null;
					// newNode.left = null;
					// newNode.right = null;
					// newNode.down = null;

					branched = true;
				}else{
					oldNewNode1.left = newNode;
					newNode.right = oldNewNode1;
					//set axis to point to new node
					newNode.left = v2ax;
					v2ax.right = newNode;
				}
			}else{
				//set axis to point to new node
				newNode.left = v2ax;
				v2ax.right = newNode;
			}
			
		}else{
			oldNewNode1 = v2ax.left;

			if(oldNewNode1!=null){
				//check if oldnode has same coords as new node
				int[] oldNewNode1Vars = oldNewNode1.getVariables();
				if(oldNewNode1Vars[0] == v1 && oldNewNode1Vars[1] == v2){
					this.linkedListInsert(oldNewNode1, newNode);
					//axis will not point to new node so set new node stuff to 0
					// newNode.up = null;
					// newNode.left = null;
					// newNode.right = null;
					// newNode.down = null;

					branched = true;
				}else{
					oldNewNode1.right = newNode;
					newNode.left = oldNewNode1;
					//set axis to point to new node
					newNode.right = v2ax;
					v2ax.left = newNode;
				}
			}else{
				//set axis to point to new node
				newNode.right = v2ax;
				v2ax.left = newNode;
			}
		}

		//if there was no linked list branching, continue to check other axis
		Node oldNewNode2 = null;
		if(!branched){
			if(!neg2){
				oldNewNode2 = v1ax.up;

				if(oldNewNode2!=null){
					//check if oldnode has same coords as new node
					int[] oldNewNode2Vars = oldNewNode2.getVariables();
					if(oldNewNode2Vars[1] == v2 && oldNewNode2Vars[0] == v1){
						this.linkedListInsert(oldNewNode2, newNode);
						//axis will not point to new node so set new node stuff to 0
						// newNode.up = null;
						// newNode.left = null;
						// newNode.right = null;
						// newNode.down = null;

						branched = true;
					}else{
						oldNewNode2.down = newNode;
						newNode.up = oldNewNode2;
						//set axis to point to new node
						newNode.down = v1ax;
						v1ax.up = newNode;
					}
					
				}else{
					//set axis to point to new node
					newNode.down = v1ax;
					v1ax.up = newNode;
				}
			}else{
				oldNewNode2 = v1ax.down;

				if(oldNewNode2!=null){
					//check if oldnode has same coords as new node
					int[] oldNewNode2Vars = oldNewNode2.getVariables();
					if(oldNewNode2Vars[0] == v2){
						this.linkedListInsert(oldNewNode2, newNode);
						//axis will not point to new node so set new node stuff to 0
						// newNode.up = null;
						// newNode.left = null;
						// newNode.right = null;
						// newNode.down = null;

						branched = true;
					}else{
						oldNewNode2.up = newNode;
						newNode.down = oldNewNode2;
						//set axis to point to new node
						newNode.up = v1ax;
						v1ax.down = newNode;
					}
				}else{
					//set axis to point to new node
					newNode.up = v1ax;
					v1ax.down = newNode;
				}
			}
		}

		// String[] arrLinks;
		// arrLinks = newNode.getNodeLinks();
		// My.cout(">>\t newNode: \n"+newNode);
  //       for(String link:arrLinks){
  //           My.cout(link);
  //       }
		// My.cout(">>\t oldNewNode1: \n"+oldNewNode1);
		// My.cout(">>\t oldNewNode2: \n"+oldNewNode2);
  //       arrLinks = v1ax.getNodeLinks();
		// My.cout(">>\t v1ax: \n"+v1ax);
  //       for(String link:arrLinks){
  //           My.cout(link);
  //       }
  //       arrLinks = v2ax.getNodeLinks();
		// My.cout(">>\t v2ax: \n"+v2ax);
  //       for(String link:arrLinks){
  //           My.cout(link);
  //       }
  //       arrLinks = origin.getNodeLinks();
		// My.cout(">>\t origin: \n"+origin);
  //       for(String link:arrLinks){
  //           My.cout(link);
  //       }

		//assume function is never null but yknow just in case
		if(function!=null){
			val = function.calculate(v1,v2);
		}

		return val;
	}

	//my own function for nodes
	private void linkedListInsert(Node head, Node newNode){
		if(head!=null && newNode!=null){
			Node ptr = head;
			Node prev = null;

			Function nodeFunc = newNode.getFunction();
			String nodeFuncName = nodeFunc.getFunctionName();

			while(ptr!=null){
				Function ptrFunc = ptr.getFunction();

				if(ptrFunc==null){
					if(prev!=null){
						prev.nextVal = newNode;
					}
					newNode.prevVal = prev;

					ptr.prevVal = newNode;
					newNode.nextVal = ptr;
					break;
				}else{
					String ptrFuncName = ptrFunc.getFunctionName();
					int comp = ptrFuncName.compareTo(nodeFuncName);
					// My.cout("ptrFunc: "+ptrFuncName);
					// My.cout("nodeFunc: "+nodeFuncName);
					if(comp > 0){
						if(prev!=null){
							prev.nextVal = newNode;
						}
						newNode.prevVal = prev;

						ptr.prevVal = newNode;
						newNode.nextVal = ptr;
						// [prev]-[ptr]-[ptr.nextVal]
						// [newNode]

						// [prev]-[newNode]-[ptr]-[ptr.nextVal]
						break;
					}
				}

				prev = ptr;
				ptr = ptr.nextVal;
			}
			if(ptr==null && prev!=null){
				prev.nextVal = newNode;
				newNode.prevVal = prev;
			}

			// My.cout("prev:"+prev);
			// My.cout("ptr:"+prev.nextVal);
			// My.cout("oldHead:"+head);
			
			// My.cout("newHead:"+head.prevVal);
			// My.cout("newNode:"+newNode);
			
			Node _down = head.down;
			Node _up = head.up;
			Node _left = head.left;
			Node _right = head.right;

			if(head.prevVal!=null){
				//head has been replaced
				newNode.down = _down;
				newNode.up = _up;
				newNode.left = _left;
				newNode.right = _right;
				
				//set all the directors to point to new head
				if(_down!=null){
					_down.up = newNode;
					// My.cout(_down.up);
				}
				if(_up!=null){
					_up.down = newNode;
					// My.cout(_up.down);
				}
				if(_left!=null){
					_left.right = newNode;
					// My.cout(_left.right);
				}
				if(_right!=null){
					_right.left = newNode;
					// My.cout(_right.left);
				}
				//reset head directors
				head.up = null;
				head.left = null;
				head.right = null;
				head.down = null;

			}else{
				newNode.up = null;
				newNode.left = null;
				newNode.right = null;
				newNode.down = null;
			}
			// My.cout(My.arrayToString(head.getNodeLinks()));
			// My.cout(My.arrayToString(newNode.getNodeLinks()));
		}
	}
	//my own function for nodes
	private Node linkedListRemove(Node head){
		Node next = null;
		if(head!=null){
			next = head.nextVal;
			if(next!=null){
				next.prevVal = null;
				next.up = head.up;
				next.down = head.down;
				next.right = head.right;
				next.left = head.left;
			}
			if(head.up!=null){
				head.up.down = next;
			}
			if(head.down!=null){
				head.down.up = next;
			}
			if(head.left!=null){
				head.left.right = next;
			}
			if(head.right!=null){
				head.right.left = next;
			}
		}
		return next;
	}

}