public class GivenMain {

    public static void main(String[] args) {

        AvlTree<Integer> tree = new AvlTree<>();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, 35);
        tree.root = tree.insert(tree.root, 14);

        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        printBST(tree.root);

        tree.root = tree.insert(tree.root, 65);
        tree.root = tree.insert(tree.root, 80);

        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        printBST(tree.root);

        tree.root = tree.insert(tree.root, 82);

        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        printBST(tree.root);

        tree.root = tree.removeNode(tree.root, 80);

        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
        printBST(tree.root);

        System.out.println("============================ THREADED AVL TREE ============================\n\n");

        ThreadedAvlTree<Integer> threadedAvlTree = new ThreadedAvlTree<>();
        threadedAvlTree.convertAVLtoThreaded(tree.root);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));
        printBST(threadedAvlTree.root);

        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 82);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 91);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 50);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));
        printBST(threadedAvlTree.root);

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 91);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));
        printBST(threadedAvlTree.root);
    }

    public static void printnode(Node<Integer> x, int h)
    {
        for (int i = 0; i < h; i++)
            System.out.print("        ");

        System.out.println("[" + x.data + "]{"+ getHeight(x) +"}");
    }

    public static void printBST(Node<Integer> node)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        showR(node, 0);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
    }

    public static void showR(Node<Integer> node, int h)
    {
        if (node == null)
            return;

        if (!node.rightThread)
            showR(node.right, h+1);

        printnode(node, h);

        showR(node.left, h+1);
    }

    public static int getHeight(Node<Integer> N) 
    {
        if (N == null)
            return 0;

        return N.height;
    }
}

/*
Expected output
---------------------------
Inorder traversal of constructed tree is : 
14 20 25 35
Tree Height is: 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        [35]{0}
[25]{2}
        [20]{1}
                [14]{0}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Inorder traversal of constructed tree is :
14 20 25 35 65 80
Tree Height is: 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                [80]{0}
        [65]{1}
                [35]{0}
[25]{2}
        [20]{1}
                [14]{0}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Inorder traversal of constructed tree is :
14 20 25 35 65 80 82
Tree Height is: 3
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        [82]{0}
                [80]{1}
        [65]{2}
                [35]{0}
[25]{3}
        [20]{1}
                [14]{0}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Inorder traversal of constructed tree is :
14 20 25 35 65 82
Tree Height is: 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                [82]{0}
        [65]{1}
                [35]{0}
[25]{2}
        [20]{1}
                [14]{0}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


============================ THREADED AVL TREE ============================


Inorder traversal of constructed threaded avl tree is :
 14  20  25  35  65  82
Tree Height is: 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                [82]{0}
        [65]{1}
                [35]{0}
[25]{2}
        [20]{1}
                [14]{0}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Inorder traversal of constructed threaded avl tree is :
 14  20  25  35  50  65  82  91
Tree Height is: 3
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        [91]{0}
                [82]{1}
        [65]{2}
                        [50]{0}
                [35]{1}
[25]{3}
        [20]{1}
                [14]{0}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Inorder traversal of constructed threaded avl tree is :
 14  20  25  35  50  65  82
Tree Height is: 3
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                [82]{0}
        [65]{2}
                        [50]{0}
                [35]{1}
[25]{3}
        [20]{1}
                [14]{0}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
