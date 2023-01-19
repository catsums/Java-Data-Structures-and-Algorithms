//Cassim Chifamba u19024895
public class BST<T extends Comparable<T>> {
    protected BSTNode<T> root;

    public BST(){};

    public boolean insert(T val){
        if(root == null){
            root = new BSTNode<>(val);
            return true;
        } else {
            return root.recInsert(val);
        }
    }

    //Place code below

    //override
    public boolean insert(BSTNode<T> node){
        if(root == null){
            root = new BSTNode<>(node.getVal());
            return true;
        }else{
            return root.recInsert(node.getVal());
        }
    }

    public int numNodes(){
        Object[] arr = this.toArray();
        return arr.length;
    }

    public Object[] toArray(){
        Object[] arr = this.inorderChilds(this.root);
        arr = this.bubbleSortNodes(arr);
        return arr;
    }

    public boolean contains(T val){
        BSTNode<T> node = this.find(val);
        if(node != null){
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        if(this.root == null){
            return true;
        }
        return false;
    }

    public BSTNode<T> find(T val){
        BSTNode<T> node = this.preorderFind(this.root, val);
        return node;
    }

    public int height(){
        int height = this.postorderDepth(this.root, 0);
        return height;
    }

    public Object[] nodesOnHeight(int h){
        Object[] arr = inorderDepthCollect(this.root, h, 0);
        return arr;
    }

    public String DFT(){
        String out = "";

        Object[] arr = this.inorderChilds(this.root);
        for(int i=0;i<arr.length;i++){

            BSTNode<T> node = (BSTNode<T>) arr[i];
            out += node;
            if(i<arr.length-1){
                out += ";";
            }
        }
        return out;
    }

    public String BFT(){
        String out = "";
        int h = this.height();
        
        for(int i=0; i<=h; i++){
            Object[] arr = this.nodesOnHeight(i);
            for(int j=0; j<arr.length; j++){
                BSTNode<T> node = (BSTNode<T>) arr[j];
                out += node;

                if(j<arr.length-1){
                    out += ";";
                }
            }
            if(i<h){
                out += ";";
            }
        }
        return out;
    }

    public T maxVal(){
        Object[] arr = this.toArray();
        BSTNode<T> maxNode = null;
        if(arr!=null){
            if(arr.length>0){
                maxNode = (BSTNode<T>) arr[arr.length-1];
            }
        }
        if(maxNode!=null){
            return (T) maxNode.getVal();
        }
        return null;
    }

    public T minVal(){
        Object[] arr = this.toArray();
        BSTNode<T> minNode = null;
        if(arr!=null){
            if(arr.length>0){
                minNode = (BSTNode<T>) arr[0];
            }
        }
        if(minNode!=null){
            return (T) minNode.getVal();
        }
        return null;
    }

    //ADD HELPER FUNCTIONS HERE
    protected Object[] array(){
        Object[] arr = inorderChilds(this.root);
        return arr;
    }
    protected Object[] inorderChilds(BSTNode<T> node){
        Object[] nodeChilds = new Object[0];
        
        if(node!=null){
            Object[] leftChilds = new Object[0];
            Object[] rightChilds = new Object[0];
            //collect left and right childs
            leftChilds = inorderChilds(node.left);
            rightChilds = inorderChilds(node.right);

            int len = leftChilds.length + rightChilds.length + 1;
            nodeChilds = new Object[len];
            int c = 0;

            //append left children
            for(int i=0;i<leftChilds.length;i++){
                nodeChilds[c] = leftChilds[i];
                c++;
            }
            //append curr node
            nodeChilds[c] = node;
            c++;
            //append right children
            for(int i=0;i<rightChilds.length;i++){
                nodeChilds[c] = rightChilds[i];
                c++;
            }
        }

        return nodeChilds;
    }
    protected BSTNode<T> preorderFind(BSTNode<T> node, T val){
        
        if(node!=null){

            int comp = node.getVal().compareTo(val);

            if(comp>0){
                return preorderFind(node.left, val);
            }else if(comp<0){
                return preorderFind(node.right, val);
            }else{
                return node;
            }
            
        }

        return null;
    }
    protected int postorderDepth(BSTNode<T> node, int initDepth){
        int depth = initDepth;
        if(node!=null){
        
            //check left
            int left = postorderDepth(node.left, initDepth+1);

            if(left>depth){
                depth = left;
            }

            //check right
            int right = postorderDepth(node.right, initDepth+1);
            
            if(right>depth){
                depth = right;
            } 
        }else{
            depth--;
        }

        return depth;
    }
    protected Object[] inorderDepthCollect(BSTNode<T> node, int height, int depth){

        Object[] bin = new Object[0];
        if(node!=null){
            Object[] leftBin = new Object[0];
            Object[] rightBin = new Object[0];
            //collect left and right childs
            leftBin = inorderDepthCollect(node.left, height, depth+1);
            rightBin = inorderDepthCollect(node.right, height, depth+1);

            int len = leftBin.length + rightBin.length;
            if(depth == height){
                len++; 
            }
            bin = new Object[len];
            int c = 0;

            //append left children
            for(int i=0;i<leftBin.length;i++){
                bin[c] = leftBin[i];
                c++;
            }
            //append curr node if depth is the same as height
            if(depth == height){
               bin[c] = node;
               c++; 
            }
            //append right children
            for(int i=0;i<rightBin.length;i++){
                bin[c] = rightBin[i];
                c++;
            }
            
            
        }

        return bin;
    }
    protected Object[] bubbleSortNodes(Object[] nodes){

        //shallow copy array
        Object[] newArr = new Object[nodes.length];
        for(int i=0;i<nodes.length;i++){
            newArr[i] = nodes[i];
        }

        boolean isComplete = false;
        while(!isComplete){
            isComplete = true;

            for(int i=0;i<newArr.length;i++){
                BSTNode<T> nodeA = null;
                BSTNode<T> nodeB = null;
                T valA = null;
                T valB = null;

                nodeA = (BSTNode<T>) newArr[i];
                valA = nodeA.getVal();
                if( (i+1)>newArr.length){
                    nodeB = (BSTNode<T>) newArr[i+1];
                    valB = nodeB.getVal();
                }else{
                    continue;
                }

                int comp = valA.compareTo(valB);

                if(comp > 0){
                    isComplete = false;

                    Object temp = null;

                    temp = newArr[i];
                    newArr[i] = newArr[i+1];
                    newArr[i+1] = temp;
                }

            }
        }

        return newArr;

    }
}
