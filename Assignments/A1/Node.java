import java.text.DecimalFormat;

public class Node {

	private int v1; // this is the first variable
	private int v2; // this is the second variable
	public Node left; // this is the node left of this node
	public Node right; // this is the node right of this node
	public Node up; // this is the node up of this node
	public Node down; // this is the node down of this node
	public Node nextVal; // this is the next value of the current node
	public Node prevVal; // this is the prev value of the current node
	private Function nodeFunction; // this is the function associated with the current node

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}
	
	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below

	public Node(Function function, int v1, int v2) {
		this.setFunction(function);
		this.v1 = v1;
		this.v2 = v2;

		this.left = null;
		this.up = null;
		this.down = null;
		this.right = null;

		this.nextVal = null;
		this.prevVal = null;
	}
	//copy constructor i was asked to add
	public Node(Node other){
		this.setFunction(other.getFunction());
		int[] vars = other.getVariables();
		this.v1 = vars[0];
		this.v2 = vars[1];

		this.left = other.left;
		this.up = other.up;
		this.down = other.down;
		this.right = other.right;

		this.nextVal = other.nextVal;
		this.prevVal = other.prevVal;
	}

	public Function setFunction(Function function) {
		Function oldFunc = this.nodeFunction;
		this.nodeFunction = function;
		return oldFunc;
	}

	public float getValue() {
		if(this.nodeFunction==null || this.nodeFunction.getFunctionName()=="Unknown function"){
			return Float.NEGATIVE_INFINITY;
		}
		return this.nodeFunction.calculate(this.v1, this.v2);
	}

	public int[] getVariables() {
		int[] vars = new int[2];
		vars[0] = this.v1;
		vars[1] = this.v2;
		return vars;
	}

	public Function getFunction(){
		return this.nodeFunction;
	}

	public String[] getNodeLinks(){
		String[] out = new String[6];
		out[0] = "U";
		out[1] = "D";
		out[2] = "R";
		out[3] = "L";
		out[4] = "P";
		out[5] = "N";

		for(int c=0;c<out.length;c++){
			Node ptr = null;
			int[] vars;
			float val;
			switch(out[c].charAt(0)){
				case 'U':
					ptr = this.up;break;
				case 'D':
					ptr = this.down;break;
				case 'R':
					ptr = this.right;break;
				case 'L':
					ptr = this.left;break;
				case 'P':
					ptr = this.prevVal;break;
				case 'N':
					ptr = this.nextVal;break;
			}
			if(ptr != null){
				vars = ptr.getVariables();
				val = ptr.getValue();
			}else{
				vars = new int[2];
				val = 0;
			}

			out[c] += "[" + ( ptr==null ? "": vars[0] ) + "]";
			out[c] += "[" + ( ptr==null ? "": vars[1] ) + "]";
			out[c] += "{" + ( ptr==null ? "": this.floatFormatter(val) ) + "}";
		}

		return out;
	}

	//my stuff
	@Override
	public String toString(){
		String out = "";
		int[] vars = this.getVariables();
		Function func = this.getFunction();
		float val = this.getValue();
		out += "{ v1: " + vars[0] + ", v2: " + vars[1] + ", func: ";
		if(func==null){
			out += "null";
		}else{
			out += func.getFunctionName();
		}
		out += ", val: " + val;
		out += " }";
		return out;
	}

}