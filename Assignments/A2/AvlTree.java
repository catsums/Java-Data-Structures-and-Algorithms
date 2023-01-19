public class AvlTree<T extends Comparable<T>> {
    public Node<T> root;

    public AvlTree() {
        this.root = null;
    }


    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    /*Printing AvlTree in inorder*/
    void print(Node<T> node) {
        if (node == null)
            return;

        print(node.left);

        System.out.print(node.data + " ");

        print(node.right);
    }

    /* Do not edit the code above */

    /**
     * Insert the given data into the tree.
     * Duplicate data is not allowed. just return the node.
     */

    Node<T> insert(Node<T> node, T data) {
        if(node==null){
            if(root==null){
                root = new Node(data);
                return this.root;
            }
            return node;
        }

        int comp;
        if(this.root == null){
            this.root = new Node(data);
        }else{
            boolean check = recInsert(node, data);
            if(check){
                update(this.root);
                postorderHeight(this.root);
            }else{
                return node;
            }
        }
        return this.root;
    }


    /**
     * Remove / Delete the node based on the given data
     * Return the node / root if the data do not exist
     */

    Node<T> removeNode(Node<T> root, T data) {

        Node<T> removed = preorderFind(root, data);
        if(removed==null){
            return root;
        }

        Node<T> pred = null;
        if(removed.left!=null){
            pred = rightMost(removed.left);
        }

        Node<T> parent = getParent(removed);

        if(pred!=null){

            parent = getParent(pred);

            T temp = removed.data;
            removed.data = pred.data;
            pred.data = temp;

            Node<T> tempNode = null;

            tempNode = pred;
            pred = removed;
            removed = tempNode;
        }

        if(parent!=null){
            boolean isLeft = false;
            if(parent.left==removed){
                parent.left = null;
                isLeft = true;
            }else if(parent.right==removed){
                parent.right = null;
                isLeft = false;
            }

            if(removed.left!=null){
                if(isLeft){
                    parent.left = removed.left;
                }else{
                    parent.right = removed.left;
                }
            }
            if(removed.right!=null){
                if(isLeft){
                    parent.left = removed.right;
                }else{
                    parent.right = removed.right;
                }
            }
        }else{
            if(removed.left!=null){
                Node<T> farRight = rightMost(removed.left);
                farRight.right = removed.left;
                this.root = removed.left;
            }else if(removed.right!=null){
                this.root = removed.right;
            }else{
                this.root = null;
            }
        }

        

        update(this.root);
        postorderHeight(this.root);

        return this.root;
    }

    ///HELPER FUNCIES///

    // public Node<T>[] BFT(){
    //     Node<T>[] arr = new Node<T>[0];
    //     int c = 0;
    //     int _height = this.root.height;
        
    //     for(int i=0; i<=_height; i++){
    //         Node<T>[] _arr = this.nodesOnHeight(i);
    //         arr = resizeArray(arr, _arr.length);
    //         for(int j=0; j<_arr.length; j++){
    //             arr[c] = _arr[j];
    //             c++;
    //         }
    //     }
    //     return arr;
    // }

    // protected Node<T>[] resizeArray(Node<T>[] arr, int resize){
    //     Node<T>[] newArr = new Node<T>[arr.length+resize];

    //     for(int i=0;i<arr.length;i++){
    //         if(i >= newArr.length){
    //             break;
    //         }
    //         newArr[i] = arr[i];
    //     }
    //     return newArr;
    // }

    //done postOrder
    public void update(Node<T> node){
        if(node!=null){

            if(node.left!=null) update(node.left);
            if(node.right!=null) update(node.right);

            int bF = getBalanceFactor(node);
            if(bF>1){
                int bFC = getBalanceFactor(node.right);
                /// bF(P)>0 && ///bf(C)>0
                if(bFC >= 0){
                    // My.cout("RR rotato");
                    rightTreeRightChildRotation(node);
                }/// bF(P)>0 && ///bf(C)>0
                else{
                    // My.cout("LR rotato");
                    leftTreeRightChildRotation(node);
                }
                // postorderHeight(node);
            }else if(bF<-1){
                int bFC = getBalanceFactor(node.left);
                /// bF(P)<0 && ///bf(C)<0
                if(bFC <= 0){
                    // My.cout("LL rotato");
                    leftTreeLeftChildRotation(node);
                }/// bF(P)<0 && ///bf(C)>0
                else{
                    // My.cout("RL rotato");
                    rightTreeLeftChildRotation(node);
                }
            }
            //update height
            postorderHeight(node);

        }
    }

    public void swap(Node<T> a, Node<T> b){
        int comp;
        Node<T> temp = null;
        //swap parents
        Node<T> parentA = getParent(a);
        Node<T> parentB = getParent(b);

        if(parentA!=null){
            if(parentA.left == a){
                parentA.left = b;
            }
            if(parentA.right == a){
                parentA.right = b;
            }
        }
        if(parentB!=null){
            if(parentB.left == b){
                parentB.left = a;
            }
            if(parentB.right == b){
                parentB.right = a;
            }
        }

        temp = null;

        //swap lefts
        temp = a.left;
        a.left = b.left;
        b.left = temp;

        temp = null;

        //swap rights
        temp = a.right;
        a.right = b.right;
        b.right = temp;
    }

    public int getBalanceFactor(Node<T> node){
        int leftHeight = 0;
        int rightHeight = 0;
        if(node.left!=null){
            leftHeight = postorderHeight(node.left) + 1;
        }
        if(node.right!=null){
            rightHeight = postorderHeight(node.right) + 1;
        }

        int bF = rightHeight - leftHeight;

        return bF;
    }

    public boolean leftTreeLeftChildRotation(Node<T> node){
        return rotateUp(node.left, node);
    }
    public boolean rightTreeLeftChildRotation(Node<T> node){
        Node<T> newRoot = node.left.right;
        boolean succ = this.rotateUp(newRoot, node.left);
        if(succ){
            succ = this.rotateUp(newRoot, node);
        }
        return succ;
    }
    public boolean rightTreeRightChildRotation(Node<T> node){
        return rotateUp(node.right, node);
    }
    public boolean leftTreeRightChildRotation(Node<T> node){
        Node<T> newRoot = node.right.left;
        boolean succ = this.rotateUp(newRoot, node.right);
        if(succ){
            succ = this.rotateUp(newRoot, node);
        }
        return succ;
    }

    public boolean rotateUp(Node<T> node, Node<T> parent){
        if(node != null && parent != null){
            boolean isRight = false;

            int comp = node.data.compareTo(parent.data);
            if(comp > 0){
                isRight = true;
            }else if(comp < 0){
                isRight = false;
            }else{
                return false;
            }

            Node<T> gParent = this.getParent(parent);

            if(isRight){
                Node<T> temp = node.left;
                node.left = parent;
                parent.right = temp;
            }else{
                Node<T> temp = node.right;
                node.right = parent;
                parent.left = temp;
            }

            if(gParent!=null){
                comp = node.data.compareTo(gParent.data);
                if(comp > 0){
                    gParent.right = node;
                }else if(comp<0){
                    gParent.left = node;
                }
            }else{
                this.root = node;
            }

            return true;

        }
        return false;
    }

    public boolean isLeaf(Node<T> node){
        if(node==null) return false;

        return (node.left==null) && (node.right==null);
    }

    public boolean recInsert(Node<T> node, T key){
        int comp = key.compareTo(node.data);
        if(comp < 0){
            if(node.left==null){
                node.left = new Node(key);
            }else{
                return this.recInsert(node.left, key);
            }
        }else if(comp > 0){
            if(node.right==null){
                node.right = new Node(key);
            }else{
                return this.recInsert(node.right, key);
            }
        }else{
            return false;
        }
        return true;
    }

    public Node<T> leftMost(Node<T> root){
        Node<T> curr = root;
        Node<T> left = null;
        if(root != null){
            while(curr!=null){
                left = curr;
                if(curr.leftThread){
                    curr = null;
                }else{
                    curr = curr.left;
                }
            }
        }
        return left;
    }
    public Node<T> rightMost(Node<T> root){
        Node<T> curr = root;
        Node<T> right = null;
        if(root != null){
            while(curr!=null){
                right = curr;
                if(curr.rightThread){
                    curr = null;
                }else{
                    curr = curr.right;
                }
            }
        }
        return right;
    }

    public Node<T>[] toArray(){
        return inorderChilds(this.root);
    }

    public Node<T>[] nodesOnHeight(int h){
        return inorderDepthChilds(this.root,h,0);
    }

    public Node<T>[] inorderChilds(Node<T> node){
        Node<T>[] childs = new Node[0];
        if(node != null){
            Node<T>[] lChilds = new Node[0];
            Node<T>[] rChilds = new Node[0];
            lChilds = inorderChilds(node.left);
            rChilds = inorderChilds(node.right);

            int len = lChilds.length + rChilds.length + 1;
            childs = new Node[len];
            int c = 0;

            for(int i=0;i<lChilds.length;i++){
                childs[c] = lChilds[i];
                c++;
            }

            childs[c] = node; c++;

            for(int i=0;i<rChilds.length;i++){
                childs[c] = rChilds[i];
                c++;
            }
        }
        return childs;
    }

    public Node<T>[] inorderDepthChilds(Node<T> node, int height, int depth){
        Node<T>[] bin = new Node[0];
        if(node!=null){
            Node<T>[] lBin = new Node[0];
            Node<T>[] rBin = new Node[0];

            if(node.left!=null)
                lBin = inorderDepthChilds(node.left,height,depth+1);
            if(node.right!=null)
                rBin = inorderDepthChilds(node.right,height,depth+1);
        
            int len = lBin.length + rBin.length;
            if(depth==height){
                len++;
            }
            bin = new Node[len];
            int c = 0;

            //append left children
            for(int i=0;i<lBin.length;i++){
                bin[c] = lBin[i];
                c++;
            }
            //append curr node if depth is the same as height
            if(depth == height){
               bin[c] = node;
               c++; 
            }
            //append right children
            for(int i=0;i<rBin.length;i++){
                bin[c] = rBin[i];
                c++;
            }

        }
        return bin;
    }

    public int postorderHeight(Node<T> node){
        if(node==null) return 0;

        int height = 0;
        int lHeight = 0;
        int rHeight = 0;

        if(node.left!=null){
            lHeight = 1 + postorderHeight(node.left);
            height = lHeight;
        }
        if(node.right!=null){
            rHeight = 1 + postorderHeight(node.right);
            if(rHeight>lHeight){
                height = rHeight;
            }
        }

        node.height = height;

        return height;
    }

    public Node<T> preorderFind(Node<T> node, T key){
        if(node!=null){
            int comp = node.data.compareTo(key);

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

    public Node<T> preorderParentFind(Node<T> node, T key){
        if(node!=null){
            Node<T> left = node.left;
            Node<T> right = node.right;

            int comp = node.data.compareTo(key);

            if(comp==0){
                return null;
            }else if(comp > 0){
                if(left!=null){
                    comp = left.data.compareTo(key);
                    if(comp==0){
                        return node;
                    }else{
                        return preorderParentFind(left, key);
                    }
                }
            }else if(comp < 0){
                if(right!=null){
                    comp = right.data.compareTo(key);
                    if(comp==0){
                        return node;
                    }else{
                        return preorderParentFind(right, key);
                    } 
                }
            }
        }
        return null;
    }

    public Node<T> getParent(Node<T> node){
        if(node!=null){
            Node<T> parent = this.preorderParentFind(root,node.data);
            return parent;
        }
        return null;
    }

    // public static void printLevels(AvlTree tree){
    //     if(tree==null||tree.root==null) return;
    //     int _height = tree.root.height;

    //     for(int h=0;h<=_height;h++){
    //         My.cout("Lv"+h+": \t"+My.arrayToString(tree.nodesOnHeight(h)));
    //     }
    // }

}

