package BST;

public class BSTNode<T extends Comparable<T>> {
    private T value;
    public BSTNode<T> right;
    public BSTNode<T> left;

    public boolean recInsert(T val){
        if(val.compareTo(value) == 0)
            return false;

        if(val.compareTo(value) < 0)
        {
            if(left == null)
            {
                left = new BSTNode<>(val);
                return true;
            } else {
                return left.recInsert(val);
            }
        } else {
            if(right == null)
            {
                right = new BSTNode<>(val);
                return true;
            } else {
                return right.recInsert(val);
            }
        }
    }

    //Implement the following

    public BSTNode(T val){
        this.value = val;
        this.left = null;
        this.right = null;
    }

    public T getVal(){
        if(this.value == null){
            return null;
        }
        return this.value;
    }

    public String toString(){
        String out = "";
        String leftVal = "";
        String currVal = "";
        String rightVal = "";

        if(this.left!=null){
            leftVal = this.left.getVal().toString();
        }
        if(this.right!=null){
            rightVal = this.right.getVal().toString();
        }
        if(this.value!=null){
            currVal = this.value.toString();
        }

        out += "L[" + leftVal + "]";
        out += "V[" + currVal + "]";
        out += "R[" + rightVal + "]";

        return out;
    }

    //ADD HELPER FUNCTIONS HERE
}
