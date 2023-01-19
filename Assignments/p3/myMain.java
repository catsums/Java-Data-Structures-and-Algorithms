public class myMain
{
	public static void cout(Object x){
		System.out.println(x);
	}

	public static void main(String[] args){
		My.cout("\tMain Start: \n------------\n");

		BST<Integer> bst = new BST();

		My.cout("numNodes:\t"+bst.numNodes());
		My.cout("isEmpty? >"+bst.isEmpty());

		int[] data = new int[] {5,10,16,4,12,45,58,23,31,2,3,-21,-69,-10,-13,1,0};
		// int[] data = new int[] {73,11,7,32,60,72,95,82,87};
		
		for(int dat:data){
			bst.insert(dat);
		}

		My.cout("numNodes:\t"+bst.numNodes());
		My.cout("isEmpty? >"+bst.isEmpty());

		int[] checks = new int[] {3,5,10,4,12,11,60,50,1,-13};

		for(int check:checks){
			My.cout("has "+check+":\t"+bst.contains(check));
		}

		My.cout("height:\t"+bst.height());
		My.cout("DFT:\n"+bst.DFT());
		My.cout("BFT:\n"+bst.BFT());
		My.cout("minVal:\t"+bst.minVal());
		My.cout("maxVal:\t"+bst.maxVal());

		My.cout("\n------------\n\tMain End.");
	}
}
