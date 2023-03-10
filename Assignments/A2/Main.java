public class Main {

    public static void main(String[] args) {

        AvlTree<Integer> tree = new AvlTree<>();

        /* Constructing tree given in the above figure */

        // int count = 0;
        // System.out.println("---20");
        // tree.root = tree.insert(tree.root, 20);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---25");
        // tree.root = tree.insert(tree.root, 25);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---35");
        // tree.root = tree.insert(tree.root, 35);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---14");
        // tree.root = tree.insert(tree.root, 14);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---16");
        // tree.root = tree.insert(tree.root, 16);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---16");
        // tree.root = tree.insert(tree.root, 16);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---15");
        // tree.root = tree.insert(tree.root, 15);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---22");
        // tree.root = tree.insert(tree.root, 22);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---36");
        // tree.root = tree.insert(tree.root, 36);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---21");
        // tree.root = tree.insert(tree.root, 21);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-16");
        // tree.root = tree.removeNode(tree.root, 16);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-36");
        // tree.root = tree.removeNode(tree.root, 36);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-35");
        // tree.root = tree.removeNode(tree.root, 35);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-25");
        // tree.root = tree.removeNode(tree.root, 25);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-15");
        // tree.root = tree.removeNode(tree.root, 15);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-22");
        // tree.root = tree.removeNode(tree.root, 22);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-21");
        // tree.root = tree.removeNode(tree.root, 21);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-20");
        // tree.root = tree.removeNode(tree.root, 20);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        // System.out.println("---rem-14");
        // tree.root = tree.removeNode(tree.root, 14);
        // System.out.println("\nInorder traversal" +
        //         " of constructed tree is ["+ ++count + "]: ");
        // tree.print(tree.root);

        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, 35);
        tree.root = tree.insert(tree.root, 14);
        tree.print(tree.root);

        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        tree.root = tree.insert(tree.root, 65);
        tree.root = tree.insert(tree.root, 80);

        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        tree.root = tree.insert(tree.root, 82);

        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));


        tree.root = tree.removeNode(tree.root, 80);
        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);

        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        //threaded Avl tree

        System.out.println("\nThready Kruger: ");

        ThreadedAvlTree<Integer> threadedAvlTree = new ThreadedAvlTree<>();
        threadedAvlTree.convertAVLtoThreaded(tree.root);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        System.out.println("Adding: 83");
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 83);
        System.out.println("Adding: 91");
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 91);
        System.out.println("Adding: 50");
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 50);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 65);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 15);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 25);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 82);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 50);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 20);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 14);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));
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
