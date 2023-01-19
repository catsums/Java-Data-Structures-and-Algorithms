///Cassim Chifamba (u19024895)
public class SplayTree<T extends Comparable<T>> {

    public TreeNode<T> root;

    public SplayTree() {
        // Your code here...
        this.root = null;
    }

    /**
     * Returns true if the key exists in the tree, otherwise false
     */
    public Boolean contains(T key) {
        // Your code here...
        TreeNode<T> node = this.getNode(key);
        if(node!=null){
            return true;
        }
        return false;
    }

    /**
     * Insert the given key into the tree.
     * Duplicate keys should be ignored.
     * No Splaying should take place.
     */
    public void insert(T key) {
        // Your code here...
        if(root == null){
            root = new TreeNode<>(key);
        }else{
            this.recInsert(root, key);
        }
    }

    /**
     * Return the Predecessor of the given key.
     * If the given key does not exist return null.
     * If the given key does not have a Predecessor, return null.
     */
    public T findPredecessor(T key) {
        // Your code here...
        if(this.contains(key)){
            if(root != null){
                TreeNode<T> parent = this.preorderParentFind(root, key);
                if(parent != null){
                    return parent.key;
                }
            }
        }
        return null;
    }

    // /**
    //  * Move the accessed key closer to the root using the splaying strategy.
    //  * If the key does not exist, insert it without splaying
    //  */
    public void access(T key) {
        // Your code here...
        if(this.contains(key)){
            TreeNode<T> node = this.getNode(key);

            boolean splayGo = true;
            while(splayGo){
                splayGo = this.splay(node);
            }
            this.root = node;
        }
    }

    /////////////My custom functions//////////////////
    public TreeNode<T> getParent(TreeNode<T> node){
        if(node != null){
            TreeNode<T> parent = this.preorderParentFind(root, node.key);
            return parent;
        }
        return null;
    }
    public TreeNode<T> getParent(T key){
        if(key != null){
            TreeNode<T> parent = this.preorderParentFind(root, key);
            return parent;
        }
        return null;
    }
    public TreeNode<T> getNode(T key){
        if(key != null && root != null){
            TreeNode<T> node = this.preorderFind(this.root, key); 
            return node;
        }
        return null;
    }

    public Object[] toArray(){
        Object[] arr = this.inorderChilds(this.root);
        return arr;
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

            TreeNode<T> node = (TreeNode<T>) arr[i];
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
                TreeNode<T> node = (TreeNode<T>) arr[j];
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

    protected boolean rotateUp(TreeNode<T> node){
        if(node != null){
           TreeNode<T> parent = this.getParent(node);
            if(parent == null){
                return false;
            }

            boolean isRight = false;
            int comp = node.key.compareTo(parent.key);
            if(comp > 0){
                isRight = true;
            }else if(comp < 0){
                isRight = false;
            }else{
                return false;
            }
            TreeNode<T> grandParent = this.getParent(parent);

            if(isRight){
                TreeNode<T> temp = node.left;
                node.left = parent;
                parent.right = temp;
            }else{
                TreeNode<T> temp = node.right;
                node.right = parent;
                parent.left = temp;
            }

            if(grandParent!=null){
                int compG = node.key.compareTo(grandParent.key);
                if(compG > 0){
                    grandParent.right = node;
                }else if(compG < 0){
                    grandParent.left = node;
                }
            }else{
                //assume that parent was the root
                this.root = node;
            }

            return true;

        }else{
            return false;
        }
    }

    protected boolean splay(TreeNode<T> node){
        //assume node is not null
        TreeNode<T> parent = this.getParent(node);
        if(parent == null){
            return false;
        }

        //move parent up first
        this.rotateUp(parent);
        
        //move currNode up
        boolean succ = this.rotateUp(node);

        return succ;

    }
    protected boolean semi_splay(TreeNode<T> node){
        //assume node is not null
        TreeNode<T> parent = this.getParent(node);
        if(parent == null){
            return false;
        }

        //move parent up first
        boolean succ = this.rotateUp(parent);

        return succ;

    }

    protected boolean recInsert(TreeNode<T> node, T key){
        int comp = key.compareTo(node.key);
        if(comp == 0){
            return false;
        }

        if(comp < 0){
            if(node.left == null){
                node.left = new TreeNode<>(key);
                return true;
            }else{
                return this.recInsert(node.left ,key);
            }
        }else{
            if(node.right == null){
                node.right = new TreeNode<>(key);
                return true;
            }else{
                return this.recInsert(node.right ,key);
            }
        }
    }

    public Object[] array(){
        Object[] arr = inorderChilds(this.root);
        return arr;
    }
    protected TreeNode<T> preorderFind(TreeNode<T> node, T key){
        if(node!=null){
            int comp = node.key.compareTo(key);
            if(comp>0){
                return preorderFind(node.left, key);
            }else if(comp<0){
                return preorderFind(node.right, key);
            }else if(comp==0){
                return node;
            }
        }
        return null;
    }

    protected TreeNode<T> preorderParentFind(TreeNode<T> node, T key){
        if(node!=null){
            TreeNode<T> left = node.left;
            TreeNode<T> right = node.right;

            int comp = node.key.compareTo(key);

            if(comp==0){
                //assume theres no predecessor
                return null;
            }else if(comp > 0){
                //check left
                if(left != null){
                    int compL = left.key.compareTo(key);
                    if(compL == 0){
                        return node;
                    }else{
                        return preorderParentFind(left, key);
                    }
                } 
            }else if(comp < 0){
                //check right
                if(right != null){
                    int compR = right.key.compareTo(key);
                    if(compR == 0){
                        return node;
                    }else{
                        return preorderParentFind(right, key);
                    }
                }
            }
        }

        return null;
    }

    protected Object[] inorderChilds(TreeNode<T> node){
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

    protected int postorderDepth(TreeNode<T> node, int initDepth){
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
    protected Object[] inorderDepthCollect(TreeNode<T> node, int height, int depth){

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

    protected Object[] resizeArray(Object[] arr, int resize){
        Object[] newArr = new Object[arr.length+resize];

        for(int i=0;i<arr.length;i++){
            if(i >= newArr.length){
                break;
            }
            newArr[i] = arr[i];
        }
        return newArr;
    }

}