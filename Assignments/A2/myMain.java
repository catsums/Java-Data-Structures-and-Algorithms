public class myMain {

    public static void printlevels(AvlTree tree){
        if(tree==null||tree.root==null) return;
        int _height = tree.root.height;

        for(int h=0;h<=_height;h++){
            My.cout("Lv"+h+": \t"+My.arrayToString(tree.nodesOnHeight(h)));
        }
    }
    public static void printlevels(ThreadedAvlTree tree){
        if(tree==null||tree.root==null) return;
        int _height = tree.root.height;

        for(int h=0;h<=_height;h++){
            My.cout("Lv"+h+": \t"+My.arrayToString(tree.nodesOnHeight(h)));
        }
    }

    public static void main(String[] args) {

        ////////////////////


        ThreadedAvlTree<Integer> tree = new ThreadedAvlTree<>();

        Integer[] arr = {5,8,2,15,25,4,16,7};

        for(Integer item:arr){
            tree.insert(tree.root, item);
            // printlevels(tree);
            // My.cout("");
        }

        int _height = tree.postorderHeight(tree.root);

        My.cout("Root of Tree:\t"+tree.root);
        My.cout("Height of Tree:\t"+_height);
        My.cout("Root height:\t"+tree.root.height);

        printlevels(tree);My.cout("");

        tree.removeNode(tree.root, 8);

        printlevels(tree);My.cout("");

        // printlevels(tree);
        // My.cout("");
        // AvlTree<Integer> _tree = tree.toAVLTree(tree.root);
        // printlevels(_tree);
        // My.cout("");
        // _tree.insert(_tree.root,30);
        // _tree.insert(_tree.root,1);
        // printlevels(_tree);
        // My.cout("");
        // tree.convertAVLtoThreaded(_tree.root);
        // printlevels(tree);
        // My.cout("");

        //////////////////

        // AvlTree<Character> avl = new AvlTree<>();

        // Character[] arr = {'K','E','S','B','H','T','A','G','J'};

        // for(Character item:arr){
        //     avl.insert(avl.root, item);
        //     // printlevels(avl);
        //     // My.cout("");
        // }

        // int _height = avl.postorderHeight(avl.root);

        // My.cout("Height of Tree:\t"+_height);
        // My.cout("Root height:\t"+avl.root.height);

        // for(int h=0;h<=_height;h++){
        //     My.cout(My.arrayToString(avl.nodesOnHeight(h)));
        // }

        // ThreadedAvlTree<Character> tTree = new ThreadedAvlTree();

        // tTree.convertAVLtoThreaded(avl.root);

        // avl.insert(avl.root, 'I');
        // // avl.removeNode(avl.root, 'T');

        // _height = avl.postorderHeight(avl.root);

        // My.cout("Height of Tree:\t"+_height);
        // My.cout("Root height:\t"+avl.root.height);

        // for(int h=0;h<=_height;h++){
        //     My.cout(My.arrayToString(avl.nodesOnHeight(h)));
        // }

        //////////////////

        // AvlTree<Character> avl = new AvlTree();

        // Character[] arr = {'H','E','L','C','G','J','N','B','D','F','K','M','P'};

        // for(Character item:arr){
        //     Node<Character> out = avl.insert(avl.root, item);
        //     My.cout("insert: "+item+" out: "+out+" root: "+avl.root);
        // }

        // int height = avl.root.height;
        // for(int h=0;h<=height;h++){
        //     My.cout(My.arrayToString(avl.nodesOnHeight(h)));
        // }

        // My.cout("root: "+avl.root);
        // My.cout("BF(root): "+avl.getBalanceFactor(avl.root));

        // My.cout("new root after remove: "+avl.removeNode(avl.root, 'H'));
        // My.cout("new root after remove: "+avl.removeNode(avl.root, 'L'));

        // height = avl.root.height;
        // for(int h=0;h<=height;h++){
        //     My.cout(My.arrayToString(avl.nodesOnHeight(h)));
        // }

        // My.cout("root: "+avl.root);
        // My.cout("BF(root): "+avl.getBalanceFactor(avl.root));
        ///////////////////

        // AvlTree<Integer> tree = new AvlTree<>();

        // /* Constructing tree given in the above figure */
        // tree.root = tree.insert(tree.root, 20);
        // // printlevels(tree);
        // tree.root = tree.insert(tree.root, 25);
        // // printlevels(tree);
        // // My.cout("bf of root: "+tree.getBalanceFactor(tree.root));
        
        // tree.root = tree.insert(tree.root, 35);
        // // printlevels(tree);
        // tree.root = tree.insert(tree.root, 14);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        // // printlevels(tree);
        // tree.root = tree.insert(tree.root, 65);
        // // printlevels(tree);
        // tree.root = tree.insert(tree.root, 80);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        // // printlevels(tree);
        // tree.root = tree.insert(tree.root, 82);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        // // printlevels(tree);
        // tree.root = tree.removeNode(tree.root, 80);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        // System.out.println("");
        // // printlevels(tree);
        // //        threaded Avl tree

        // ThreadedAvlTree<Integer> threadedAvlTree = new ThreadedAvlTree<>();
        // threadedAvlTree.convertAVLtoThreaded(tree.root);//printlevels(threadedAvlTree);

        // System.out.println("Inorder traversal" +
        //         " of constructed threaded avl tree is : ");
        // threadedAvlTree.print(threadedAvlTree.root);
        // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 82);//printlevels(threadedAvlTree);
        // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 91);//printlevels(threadedAvlTree);
        // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 50);//printlevels(threadedAvlTree);

        // System.out.println("Inorder traversal" +
        //         " of constructed threaded avl tree is : ");
        // threadedAvlTree.print(threadedAvlTree.root);
        // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        // threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 91);//printlevels(threadedAvlTree);

        // System.out.println("Inorder traversal" +
        //         " of constructed threaded avl tree is : ");
        // threadedAvlTree.print(threadedAvlTree.root);
        // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        // ////////////////
        // AvlTree<Integer> tree = new AvlTree<>();

        // /* Constructing tree given in the above figure */
        // tree.root = tree.insert(tree.root, 20);
        // tree.root = tree.insert(tree.root, 25);
        // tree.root = tree.insert(tree.root, 35);
        // tree.root = tree.insert(tree.root, 14);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // tree.root = tree.insert(tree.root, 65);
        // tree.root = tree.insert(tree.root, 80);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // tree.root = tree.insert(tree.root, 82);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // tree.root = tree.removeNode(tree.root, 80);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // // //        threaded Avl tree

        // // ThreadedAvlTree<Integer> threadedAvlTree = new ThreadedAvlTree<>();
        // // threadedAvlTree.convertAVLtoThreaded(tree.root);

        // // System.out.println("Inorder traversal" +
        // //         " of constructed threaded avl tree is : ");
        // // threadedAvlTree.print(threadedAvlTree.root);
        // // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        // // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 82);
        // // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 82);

        // // System.out.println("Inorder traversal" +
        // //         " of constructed threaded avl tree is : ");
        // // threadedAvlTree.print(threadedAvlTree.root);
        // // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));
    }
}

/*
Expected output
---------------------------
Inorder traversal of constructed tree is :
14 20 25 35
Tree Height is: 2
Inorder traversal of constructed tree is :
14 20 25 35 65 80
Tree Height is: 2
Inorder traversal of constructed tree is :
14 20 25 35 65 80 82
Tree Height is: 3
Inorder traversal of constructed tree is :
14 20 25 35 65 82
Tree Height is: 2

Inorder traversal of constructed threaded avl tree is :
14 20 25 35 65 82
Tree Height is: 2
Inorder traversal of constructed threaded avl tree is :
14 20 25 35 50 65 82 91
Tree Height is: 3
Inorder traversal of constructed threaded avl tree is :
14 20 25 35 50 65 82
Tree Height is: 3


 */

/*
Expected output
---------------------------
Inorder traversal of constructed tree is :
14 20 25 35
Tree Height is: 2
Inorder traversal of constructed tree is :
14 20 25 35 65 80
Tree Height is: 3
Inorder traversal of constructed tree is :
14 20 25 35 65 80 82
Tree Height is: 3
Inorder traversal of constructed tree is :
14 20 25 35 65 82
Tree Height is: 3

Inorder traversal of constructed threaded avl tree is :
14 20 25 35 65 82
Tree Height is: 3
Inorder traversal of constructed threaded avl tree is :
14 20 25 35 50 65 82 91
Tree Height is: 4
 */
