public class ThreadedAvlTree<T extends Comparable<T>> {
    public Node<T> root;

    public ThreadedAvlTree() {
        this.root = null;
    }


    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    static Node getLeftMost(Node node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }

    // Inorder traversal of a threaded avl tree
    void print(Node<T> node) {
        if (node == null)
            return;

        Node<T> cur = getLeftMost(node);

        while (cur != null) {
            System.out.print(" " + cur.data + " ");


            if (cur.rightThread == true)
                cur = cur.right;
            else 
                cur = getLeftMost(cur.right);
        }
    }

    /* Do not edit the code above */


    void convertAVLtoThreaded(Node<T> node) {

        ///deep copy
        if(node != null){
            Object[] nodes = new Object[0];
            int c = 0;
            int _height = node.height;

            
            for(int h=0;h<=_height;h++){
                Object[] arr = inorderDepthChilds(node,h,0);
                nodes = resizeArray(nodes, arr.length);
                for(int i=0;i<arr.length;i++){
                    nodes[c] = arr[i];
                    c++;
                }
            }

            for(int k=0;k<nodes.length;k++){
                
                Node<T> _node = (Node<T>) nodes[k];
                if(_node==null) continue;
                if(k==0){
                    this.root = new Node<T>(_node.data);
                    continue;
                }
                if(_node!=null){
                    boolean check = recInsert(this.root, _node.data);
                    if(check){
                        postorderHeight(this.root);
                    }
                }  
            }
            updateThreads(this.root);
        }
    }

    /**
     * Insert the given data into the tree.
     * Duplicate data is not allowed. just return the node.
     */


    Node<T> insert(Node<T> node, T data) {
        if(node==null){
            if(this.root==null){
                this.root = new Node(data);
                return this.root;
            }
            return node;
        }
        else{
            Node<T> existing = preorderFind(node, data);
            if(existing!=null) return node;

            AvlTree<T> _tree = toAVLTree(this.root);

            _tree.insert(_tree.root, data);
            convertAVLtoThreaded(_tree.root);
        }
        return this.root;

        // int comp;
        // if(this.root == null){
        //     this.root = new Node(data);
        // }else{
        //     boolean check = recInsert(node, data);
        //     if(check){
        //         update(this.root);
        //         postorderHeight(this.root);
        //         updateThreads(this.root);
        //     }else{
        //         return node;
        //     }
        // }
        // return this.root;
    }

    /**
     * Delete the given element \texttt{data} from the tree.  Re-balance the tree after deletion.
     * If the data is not in the tree, return the given node / root.
     */
    Node<T> removeNode(Node<T> root, T data) {
        if(root==null){
            return root;
        }else{
            Node<T> existing = preorderFind(root, data);
            if(existing==null){
                return root;
            }

            AvlTree<T> _tree = toAVLTree(this.root);

            _tree.removeNode(_tree.root, data);
            convertAVLtoThreaded(_tree.root);

        }
        return this.root;
        // Node<T> node = root;
        // Node<T> removed = preorderFind(node, data);
        // if(removed==null){
        //     return node;
        // }

        // Node<T> pred = null;
        // if(removed.left!=null && !removed.leftThread){
        //     pred = rightMost(removed.left);
        // }

        // // My.cout("pred:\t"+pred);
        // // My.cout("rem:\t"+removed);

        // Node<T> parent = getParent(removed);

        // if(pred!=null){

        //     parent = getParent(pred);

        //     T temp = removed.data;
        //     removed.data = pred.data;
        //     pred.data = temp;

        //     Node<T> tempNode = null;

        //     tempNode = pred;
        //     pred = removed;
        //     removed = tempNode;
        // }

        // // My.cout("pred:\t"+pred);
        // // My.cout("rem:\t"+removed);
        // // My.cout("parent:\t"+parent);

        // if(parent!=null){
        //     boolean isLeft = false;
        //     if(parent.left==removed && !parent.leftThread){
        //         parent.left = null;
        //         isLeft = true;
        //     }else if(parent.right==removed && !parent.rightThread){
        //         parent.right = null;
        //         isLeft = false;
        //     }

        //     if(removed.left!=null && !removed.leftThread){
        //         if(isLeft){
        //             parent.left = removed.left;
        //         }else{
        //             parent.right = removed.left;
        //         }
        //     }
        //     if(removed.right!=null && !removed.rightThread){
        //         if(isLeft){
        //             parent.left = removed.right;
        //         }else{
        //             parent.right = removed.right;
        //         }
        //     }
        // }else{
        //     if(removed.left!=null && !removed.leftThread){
        //         Node<T> farRight = rightMost(removed.left);
        //         farRight.right = removed.left;
        //         this.root = removed.left;
        //     }else if(removed.right!=null && !removed.rightThread){
        //         this.root = removed.right;
        //     }else{
        //         this.root = null;
        //     }
        // }

        

        // update(this.root);
        // postorderHeight(this.root);
        // updateThreads(this.root);

        // return this.root;
    }

    ///HELPER FUNCIES///

    public AvlTree<T> toAVLTree(Node<T> node){
        AvlTree<T> _tree = new AvlTree();

        _tree.root = node;
        postorderDethread(node);

        return _tree;
    }

    protected void postorderDethread(Node<T> node){
        if(node.leftThread){
            node.leftThread = false;
            node.left = null;
        }
        if(node.rightThread){
            node.rightThread = false;
            node.right = null;
        }
        if(node.left!=null){
            postorderDethread(node.left);
        }
        if(node.right!=null){
            postorderDethread(node.right);
        }
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
    public void update(Node<T> node){
        if(node!=null){

            if(node.left!=null && !node.leftThread){
                update(node.left);
            }
            if(node.right!=null && !node.rightThread){
                update(node.right);
            }

            if((node.left!=null || !node.leftThread)&&(node.right!=null || !node.rightThread)){
                int bF = getBalanceFactor(node);
                if(bF>1){
                    int bFC = getBalanceFactor(node.right);
                    /// bF(P)>0 && ///bf(C)>0
                    if(bFC >= 0){
                        // My.cout("RR rotato");
                        // My.cout(node+""+node.right);
                        rightTreeRightChildRotation(node);
                    }/// bF(P)>0 && ///bf(C)>0
                    else{
                        // My.cout("LR rotato");
                        // My.cout(node+""+node.right);
                        leftTreeRightChildRotation(node);
                    }
                }else if(bF<-1){
                    int bFC = getBalanceFactor(node.left);
                    /// bF(P)<0 && ///bf(C)<0
                    if(bFC <= 0){
                        leftTreeLeftChildRotation(node);
                    }/// bF(P)<0 && ///bf(C)>0
                    else{
                        rightTreeLeftChildRotation(node);
                    }
                }
                //update height
                postorderHeight(node);
                //update threads
                updateThreads(node);
            }

        }
    }

    public void updateThreads(Node<T> node){
        //get predessor
        if(node!=null){
            Node<T> pred = rightMost(node.left);

            if(pred!=null){
                if(pred.right==node){
                    pred.rightThread = true;
                }else if(pred.right==null){
                    pred.rightThread = true;
                    pred.right = node;
                }else{
                    pred.rightThread = false;
                }
            }else{
                if(node.right!=null){
                    if(rightMost(node.right.left)==node){
                        node.rightThread = true;
                    }else{
                        node.rightThread = false;
                    }
                }else{
                    node.rightThread = false;
                }
            }

            if(node.left!=null && !node.leftThread){
                updateThreads(node.left);
            }
            if(node.right!=null && !node.rightThread){
                updateThreads(node.right);
            }
        }
        
    }

    public int getBalanceFactor(Node<T> node){
        int leftHeight = 0;
        int rightHeight = 0;
        if(node.left!=null && !node.leftThread){
            leftHeight = postorderHeight(node.left) + 1;
        }
        if(node.right!=null && !node.rightThread){
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

    public boolean recInsert(Node<T> node, T key){
        int comp = key.compareTo(node.data);
        if(comp < 0){
            if(node.left==null || node.leftThread){
                node.leftThread = false;
                node.left = new Node(key);
            }else{
                return this.recInsert(node.left, key);
            }
        }else if(comp > 0){
            if(node.right==null || node.rightThread){
                node.rightThread = false;
                node.right = new Node(key);
            }else{
                return this.recInsert(node.right, key);
            }
        }else{
            return false;
        }
        return true;
    }

    // static <T extends Comparable<T>> Node<T> leftMost(Node<T> root){
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
    // static <T extends Comparable<T>> Node<T> rightMost(Node<T> root){
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

    public boolean isLeaf(Node<T> node){
        if(node==null) return false;

        return (node.left==null || node.leftThread) && (node.right==null || node.rightThread);
    }

    public Node<T>[] inorderChilds(Node<T> node){
        Node<T>[] childs = new Node[0];
        if(node != null){
            Node<T>[] lChilds = new Node[0];
            Node<T>[] rChilds = new Node[0];
            if(node.left!=null && !node.leftThread){
                lChilds = inorderChilds(node.left);
            }
            if(node.right!=null && !node.rightThread){
                rChilds = inorderChilds(node.right);
            }

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

            if(node.left!=null && !node.leftThread)
                lBin = inorderDepthChilds(node.left,height,depth+1);
            if(node.right!=null && !node.rightThread)
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

        if(node.left!=null && !node.leftThread){
            lHeight = 1 + postorderHeight(node.left);
            height = lHeight;
        }
        if(node.right!=null && !node.rightThread){
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

            if(comp == 0){
                return node;
            }else if(comp>0){
                if(node.left!=null && !node.leftThread)
                    return preorderFind(node.left, key);
            }else if(comp<0){
                if(node.right!=null && !node.rightThread)
                    return preorderFind(node.right, key);
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
                if(left!=null && !node.leftThread){
                    comp = left.data.compareTo(key);
                    if(comp==0){
                        return node;
                    }else{
                        return preorderParentFind(left, key);
                    }
                }
            }else if(comp < 0){
                if(right!=null && !node.rightThread){
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

    // public static void printLevels(ThreadedAvlTree tree){
    //     if(tree==null||tree.root==null) return;
    //     int _height = tree.root.height;

    //     for(int h=0;h<=_height;h++){
    //         My.cout("Lv"+h+": \t"+My.arrayToString(tree.nodesOnHeight(h)));
    //     }
    // }
}
