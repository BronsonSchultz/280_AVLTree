// Bronson Schultz, 11231230, bcs269
// CMPT 280, Assignment 4, Question 2
package lib280.tree;


import lib280.exception.ContainerEmpty280Exception;

import lib280.exception.DuplicateItems280Exception;
import lib280.exception.ItemNotFound280Exception;
import lib280.exception.NoCurrentItem280Exception;

import static java.lang.Math.abs;
import static java.lang.Math.max;


/**
 * A AVL Tree class. AVL trees encapsulate binary search trees. AVL trees follow the same properties as a binary search
 * tree. However, AVl trees will balance themselves out after each insertion/deletion as necessary to keep the insertion
 * and deletion methods at a time complexity of O(logn)
 *
 * @param <I> AVL trees can hold any generic object that extends the Comparable interface
 */
public class AVLTree280<I extends Comparable<? super I>> implements Cloneable {

    /**
     * the root of the AVL tree
     */
    protected AVLNode280<I> rootNode;

    /**
     * The current node as set by search.
     */
    protected AVLNode280<I> cur;

    /**
     * The parent node of the current node as set by search.
     */
    protected AVLNode280<I> parent;


    /**
     * constructor to set up a new, empty AVL tree
     *
     * @precond none
     * @postcond none
     * @timing O(1)
     */
    public AVLTree280() {
        rootNode = null;
        cur = null;
        parent = null;
    }


    public static void main(String[] args) {
        AVLTree280<Integer> tree = new AVLTree280<>();

        // isEmpty() should be true
        if (!tree.isEmpty()) {
            System.out.println("Error in isEmpty(): expected the new tree to be empty but it was not");
        }

        if (!tree.above()){
            System.out.println("Error in above(): cursor should be in the above position in a brand new tree");
        }

        System.out.println("tree is currently empty. After insertion, tree should now contain one item. 10 with subtree heights 0 and 0");
        tree.insert(10);
        System.out.println(tree.toString());
        System.out.println("------------\n");

        if (tree.isEmpty()){
            System.out.println("Error in isEmpty(): expected tree to not be empty but isEmpty() returned true");
        }

        tree.insert(11);
        System.out.println(tree.toString());
        System.out.println("Normal BST  insertion of 11, no rotations");
        System.out.println("------------\n");



        System.out.println("insertion of 12 will create an imbalanced tree (RR), tree should become\n " +
                "  11\n" +
                " /   \\ \n" +
                "10    12");

        tree.insert(12);
        System.out.println(tree.toString());
        System.out.println("------------\n");



        tree.insert(14);
        System.out.println(tree.toString());
        System.out.println("Normal BST insertion of 14, no rotations");
        System.out.println("------------\n");




        System.out.println("Insertion of 15 will cause an imbalance (RR), tree should become\n" +
                "  11\n" +
                "  / \\\n" +
                " 10  14\n" +
                "     / \\\n" +
                "    12  15");
        tree.insert(15);
        System.out.println(tree.toString());
        System.out.println("-----------\n");




        System.out.println("Insertion of 13 will cause an imbalanced tree (RL), tree should become\n" +
                "   12\n" +
                "   / \\\n" +
                "  11  14\n" +
                " /   / \\\n" +
                "10  13  15");



        tree.insert(13);
        System.out.println(tree.toString());
        System.out.println("------------\n");




        System.out.println("Insertion of 4 will cause an imbalanced tree (LL), tree should become\n" +
                "   12\n" +
                "   / \\\n" +
                "  10  14\n" +
                " / \\  / \\\n" +
                "4  11 13  15");
        tree.insert(4);
        System.out.println(tree.toString());
        System.out.println("------------\n");


        tree.insert(5);
        System.out.println(tree.toString());
        System.out.println("insertion of 5 will cause normal BST insertion");
        System.out.println("------------\n");


        System.out.println("Insertion of 6 will cause an imbalanced tree (LR), tree should become\n" +
                "     12\n" +
                "     / \\\n" +
                "    10  14\n" +
                "   / \\  / \\\n" +
                "  5  11 13  15\n" +
                " / \\\n" +
                "4   6");
        tree.insert(6);
        System.out.println(tree.toString());
        System.out.println("------------\n");


        System.out.println("Insertion of 7 will cause an imbalanced tree (LR), tree should become\n" +
                "     12\n" +
                "     /  \\\n" +
                "    6    14\n" +
                "   / \\    / \\\n" +
                "  5  10  13  15\n" +
                " /   / \\\n" +
                "4   7   11");
        tree.insert(7);
        System.out.println(tree.toString());
        System.out.println("------------\n");

        System.out.println("Insertion of 8 will cause an imbalanced tree (LR), tree should become\n" +
                "       10\n" +
                "     /   \\\n" +
                "    6     12\n" +
                "   / \\    / \\\n" +
                "  5   7  11  14\n" +
                " /     \\     / \\\n" +
                "4       8   13  15");
        tree.insert(8);
        System.out.println(tree.toString());
        System.out.println("------------\n");





        System.out.println("moving on to deletions");


        tree.delete(4);
        System.out.println(tree.toString());
        System.out.println("deletion of 4 causes normal BST deletion no rotations necessary");
        System.out.println("------------\n");


        System.out.println("deletion of 5 will cause an imbalanced tree (RR), tree should become\n" +
                "       10\n" +
                "     /   \\\n" +
                "    7     12\n" +
                "   / \\    / \\\n" +
                "  6   8  11  14\n" +
                "              / \\\n" +
                "             13  15");
        tree.delete(5);
        System.out.println(tree.toString());
        System.out.println("------------\n");

        tree.delete(15);
        // add some weight to the left for LL set up
        tree.insert(5);
        System.out.println("delete 15, insert 5 to set up LL imbalance");

        System.out.println("deletion of 10 will cause an imbalanced tree (LL), tree should become\n" +
                "       11\n" +
                "     /   \\\n" +
                "    7     13\n" +
                "   / \\     / \\\n" +
                "  6   8    12  14\n" +
                " /            \n" +
                "5            ");
        tree.delete(10);
        System.out.println(tree.toString());
        System.out.println("------------\n");


        System.out.println("Delete 11, 6, 14, and 5 that don't cause anything new");
        tree.delete(11);
        tree.delete(6);
        tree.delete(14);
        tree.delete(5);
        System.out.println(tree.toString());
        System.out.println("------------\n");


        System.out.println("deletion of 13 will cause an imbalanced tree (LR), tree should become\n" +
                "       8\n" +
                "     /   \\\n" +
                "    7     12");
        tree.delete(13);
        System.out.println(tree.toString());
        System.out.println("------------\n");

        System.out.println("insert 14 then 13 and then delete 14 to set up tree for RL imbalance");
        tree.insert(14);
        tree.insert(13);
        tree.delete(14);

        System.out.println("deletion of 7 will cause an imbalanced tree (RL), tree should become\n" +
                "       12\n" +
                "     /   \\\n" +
                "    8     13");
        tree.delete(7);
        System.out.println(tree.toString());
        System.out.println("------------\n");



        tree.clear();
        if (!tree.isEmpty()){
            System.out.println("Error in clear(), tree should be empty but it's not");
        }


        // create a new tree that is set up for deletion that will cause two critical nodes

        System.out.println("Creating a new tree that is ready for a deletion that causes two critical nodes\n" +
                "          8          \n" +
                "        /    \\      \n" +
                "       5       11    \n" +
                "      / \\     / \\  \n" +
                "     3    7  10  12  \n" +
                "    / \\   /  /      \n" +
                "   2   4  6  9       \n" +
                "  /                  \n" +
                " 1");
        tree.insert(8);
        tree.insert(5);
        tree.insert(11);
        tree.insert(3);
        tree.insert(7);
        tree.insert(10);
        tree.insert(12);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(1);
        System.out.println(tree.toString());

         System.out.println("deletion of 12 will cause a rotation, then the rotation will cause a new critical node at the root so " +
          "RestoreAVLProperty will have to be called again");
        System.out.println("so the tree will become\n" +
                "          5           \n" +
                "        /    \\       \n" +
                "       3       8      \n" +
                "      / \\     / \\   \n" +
                "     2   4   7   10   \n" +
                "    /       /   / \\  \n" +
                "   1       6   9   11 \n");
        tree.delete(12);
        System.out.println(tree.toString());


        tree.search(1);
        if (!tree.itemExists() || tree.item() != 1){
            System.out.println("Error in search: expected cursor to be positioned at 1 but got " + tree.item());
        }


        tree.search(12);
        if (tree.itemExists() || !tree.below()){
            System.out.println("Error in search(): expected cursor to move to the below position and have no current item but got" + tree.item());
        }


        try {
            tree.insert(1);
        }
        catch (DuplicateItems280Exception e){

        }
        catch (RuntimeException e){
            System.out.println("Error in insert(): expected DuplicateItems280Exception when adding a duplicate item but " +
                    "got a different runtime exception");
        }

        try{
            tree.delete(12);
        }
        catch (ItemNotFound280Exception e){

        }
        catch (RuntimeException e){
            System.out.println("Error in delete(): expected ItemNotFound280Exception when deleting an item that is not " +
                    " in the tree but got a different runtime exception");
        }

        if (!tree.has(2)){
            System.out.println("Error in has(): expected true when searching for 2 but got false");
        }

        try{
            tree.insert(2);
        }
        catch (DuplicateItems280Exception e){

        }
        catch (RuntimeException e){
            System.out.println("Error in insert(): Expected DuplicateItem280Exception from inserting an item that " +
                    "in the tree already exists but got a different one");
        }

        if (tree.has(44)){
            System.out.println("Error in has(): expected false when searching for 44 but got true");
        }

        tree.clear();

        if (!tree.above()){
            System.out.println("Error in above(): cursor should be in the above position after being cleared but it is not");
        }


        System.out.println("regression test complete");

    }

    /**
     * checks whether the AVL tree is empty or not
     *
     * @return true if the AVL tree is empty, false otherwise
     * @precond none
     * @postcond none
     * @timing O(1)
     */
    public boolean isEmpty() {
        return rootNode == null;
    }


    /**
     * create a new AVL Node to be used in the tree
     *
     * @param x some generic item to be inserted into the new node
     * @return a new AVL Node with item x
     * @precond none
     * @postcond none
     */
    public AVLNode280<I> createNewNode(I x) {
        return new AVLNode280<>(x);
    }


    /**
     * method calculates the height of the a (sub)tree
     *
     * @param node root of the (sub)tree
     * @return int value for how many levels the (sub)tree contains, including itself
     * @precond none
     * @postcond none
     * @timing O(1)
     */
    protected int height(AVLNode280<I> node) {
        // a none existent node has no height
        if (node == null) {
            return 0;
        } else {
            // return the height of the node's biggest subtree plus one for itself
            return 1 + max(node.getLeftHeight(), node.getRightHeight());
        }
    }


    /**
     * public method to insert a value into the AVL tree
     *
     * @param x the generic object to inserted into the AVL tree
     * @precond x cannot already exist in the AVL tree
     * @postcond x will be inserted into the AVL tree in the appropriate place
     * @timing O(logn) where n is the number of nodes in the tree
     */
    public void insert(I x) throws DuplicateItems280Exception {
        rootNode = insert(x, rootNode);
    }

    /**
     * protected insert to abstract away the fact that insert takes a node as a parameter, returns an AVL node
     * and that the tree rearranges itself as needed
     *
     * @param x    the item to be inserted into the AVL tree
     * @param node the root of the (sub)tree that the item should be inserted into
     * @return a reference to the new root of the (sub)tree after insertion
     * @throws DuplicateItems280Exception if the item to be inserted is already in the tree
     * @precond x cannot already appear in the tree
     * @postcond x will be inserted into the AVL tree in the appropriate place. subtree heights will be changed
     * accordingly
     * @timing O(logn) where n is the number of nodes in the tree
     */
    protected AVLNode280<I> insert(I x, AVLNode280<I> node) throws DuplicateItems280Exception {
        // if the tree is empty just create a new node for the root
        if (isEmpty()) {
            return createNewNode(x);
        } else {
            // if x is less than the current item...
            if ((x.compareTo(node.item)) < 0) {

                // if the left child is empty, insert here
                if (node.leftNode == null) {
                    node.leftNode = createNewNode(x);

                } else {
                    // else walk left until to find the right spot
                    node.setLeftNode(insert(x, node.leftNode));

                    //
                    //node.setLeftHeight(height(node.leftNode));
                }
                // if x is greater than the current item...
            } else if ((x.compareTo(node.item)) > 0) {

                // if the right child is empty, insert here
                if (node.rightNode == null) {
                    node.rightNode = createNewNode(x);


                } else {
                    // else walk right until you find the right spot
                    node.setRightNode(insert(x, node.rightNode));
                    //node.setRightHeight(height(node.rightNode));
                }
            } else {
                // duplicates are not allowed in AVL trees, so throw an exception
                throw new DuplicateItems280Exception("Duplicates are not allowed in AVL trees");
            }

            // recalculate the left and right subtree heights after the insertion
            node.setLeftHeight(height(node.getLeftNode()));
            node.setRightHeight(height(node.getRightNode()));

            // restore the AVL property after insertion and return a reference to the root after everything is fixed
            return restoreAVLProperty(node);
        }
    }


    /**
     * public method to delete a specific item from the AVl tree
     *
     * @param x the item to be deleted from the tree
     * @precond x has to appear in the AVL tree
     * @postcond x will be deleted from the AVL tree
     * @timing O(logn) where n is the number of nodes in the tree
     * @throws ItemNotFound280Exception if x does not appear in the tree
     */
    public void delete(I x) throws ItemNotFound280Exception {
        rootNode = delete(x, rootNode);
    }


    /**
     * protected version of delete to abstract away the fact that delete takes a node as a parameter, returns an AVL
     * node and will rotate the tree appropriately
     *
     * @param x    the item to be deleted from the tree
     * @param node the root of the (sub)tree to delete x from
     * @return reference to the root of the (sub)tree after deletion and rotations
     * @timing O(logn) where n is the number of nodes in the tree
     * @throws ItemNotFound280Exception if x does not appear in the tree
     */
    protected AVLNode280<I> delete(I x, AVLNode280<I> node) throws ItemNotFound280Exception {

        // if you've reached the end of a tree/ the root of the tree is empty, then the item is not in the tree
        if (node == null) {
            throw new ItemNotFound280Exception(x + "does not appear in this AVL tree");
        }

        // if the item is found in the current node
        if (x == node.getItem()) {

            // if the node has no children..
            if (node.getLeftNode() == null && node.getRightNode() == null) {
                // the root of the (sub)tree is null
                return null;

                // if the node has a right child..
            } else if (node.getLeftNode() == null) {
                // the right child becomes the root
                return node.getRightNode();

                // if the node has a left child..
            } else if (node.getRightNode() == null) {
                // the root becomes that left child
                return node.getLeftNode();

            } else {
                // the current node must have two children

                // find the number that would come right after the item in In-Order traversal
                AVLNode280<I> inOrderSuccessor = getMinNode(node.getRightNode());

                // copy the inOrderSuccessor into the node that currently holds the item to be deleted
                node.item = inOrderSuccessor.getItem();

                // delete the original inOrderSuccessor from the tree
                node.setRightNode(delete(inOrderSuccessor.getItem(), node.getRightNode()));

                // this swap will keep the BST property satisfied
            }
        } else {
            // you haven't found the item to delete yet..

            // if the item is less than the current node
            if (x.compareTo(node.getItem()) < 0) {
                // walk left until you find the item
                node.setLeftNode(delete(x, node.getLeftNode()));
                // recalculate left subtree height
                node.setLeftHeight(height(node.getLeftNode()));

            } else {
                // else the item is greater than the current node

                // walk right until you find the item
                node.setRightNode(delete(x, node.getRightNode()));
                node.setRightHeight(height(node.getRightNode()));

                // recalculate subtree height
            }

        }


        // after deletion, make the necessary rotations and return a reference to the root of the (sub)tree afterwards
        return restoreAVLProperty(node);
    }

    /**
     * get the node with the smallest value in a tree
     *
     * @param node the root of the (sub)tree to operate on
     * @return reference to the node with the smallest value in the tree
     * @precond none
     * @postcond none
     * @timing O(h) where h is the height of the tree
     */
    public AVLNode280<I> getMinNode(AVLNode280<I> node) {
        // continuously walk left until you find the last node is the left subtree
        while (node.getLeftNode() != null) {
            node = node.getLeftNode();
        }
        return node;
    }

    /**
     * protected method that finds whether a (sub)tree is right-heavy, left-heavy or neither
     *
     * @param node the root of the (sub)tree to operate on
     * @return int value from -2 to 2 representing which side of the tree is heaviest
     * @precond node has to appear in the AVL tree
     * @postcond none
     * @timing O(logn) where n is the number of nodes in the tree
     */
    protected int signedImbalance(AVLNode280<I> node) {
        // height of the leftsubtree - height of rightsubtree
        return height(node.getLeftNode()) - height(node.getRightNode());
    }


    /**
     * rotate a (sub)tree right to either balance a degenerate tree or set up a tree to be balanced properly
     *
     * @param node the root of the (sub)tree
     * @return a reference to the new root of the (sub)tree
     * @precond none
     * @postcond the value in the root and it's children will likely be rotated right
     * @timing O(1)
     */
    protected AVLNode280<I> rotateRight(AVLNode280<I> node) {
        /*
                x                   y
               /                   / \
              y  --- becomes -->  z   x
             /
            z

         */
        // the new root of the tree will be the left child of the root
        AVLNode280<I> newRoot = node.getLeftNode();

        // the intermediate value of the tree will move sides the become the left child of the now left child
        AVLNode280<I> newLeft = newRoot.getRightNode();

        // make the rotations
        newRoot.setRightNode(node);
        node.setLeftNode(newLeft);

        // recalculate the heights of the node after moving it and the newRoot after moving it
        node.setRightHeight(height(node.rightNode));
        node.setLeftHeight(height(node.leftNode));
        newRoot.setRightHeight(height(newRoot.rightNode));
        newRoot.setLeftHeight(height(newRoot.leftNode));

        // return a reference of the new root of the tree
        return newRoot;


    }

    /**
     * rotate a (sub)tree left to either balance a degenerate tree or set up a tree to be balanced properly
     *
     * @param node the root of the (sub)tree
     * @return a reference to the new root of the (sub)tree
     * @precond none
     * @postcond the value in the root and it's children will likely be rotated left
     * @timing O(1)
     */
    protected AVLNode280<I> rotateLeft(AVLNode280<I> node) {

        /*
              x                       y
               \                     / \
                y  --- becomes -->  z   x
                 \
                  z
         */
        // same thing as rotateRight, just opposite
        AVLNode280<I> newRoot = node.getRightNode();
        AVLNode280<I> newRight = newRoot.getLeftNode();

        newRoot.setLeftNode(node);
        node.setRightNode(newRight);

        node.setRightHeight(height(node.rightNode));
        node.setLeftHeight(height(node.leftNode));
        newRoot.setRightHeight(height(newRoot.rightNode));
        newRoot.setLeftHeight(height(newRoot.leftNode));

        return newRoot;


    }

    /**
     * method evaluates a (sub)tree's current balance and will make left or right rotations appropriately to restore the
     * balanced tree property ( no level imbalance > 1)
     *
     * @param node the root of the (sub)tree the balance
     * @return reference to the new root of the (sub)tree
     * @precond none
     * @postcond the (sub)tree will be rotated and balanced, the values at the root and it's children will likely change
     * @timing O(logn) where n is the number of nodes in the tree
     */
    protected AVLNode280<I> restoreAVLProperty(AVLNode280<I> node) {
        int imbalance = signedImbalance(node);

        // if the tree is balanced, do nothing
        if (abs(imbalance) <= 1) {
            return node;
        }

        // if the tree is left heavy..
        if (imbalance == 2) {

            // if the subtree of the left tree is left heavy.. (LL imbalance)
            if (signedImbalance(node.getLeftNode()) >= 0) {
                // only one rotation to the right is necessary
                return rotateRight(node);
            } else {
                // the subtree of the left tree is RIGHT heavy (LR imbalance)

                // a left rotation of the subtree is necessary, and then a right rotation of the root
                node.setLeftNode(rotateLeft(node.getLeftNode()));
                return rotateRight(node);
            }
        } else {
            // the right subtree must be heavy..

            // if the right subtree of the right subtree is right heavy.. (RR imbalance)
            if (signedImbalance(node.getRightNode()) <= 0) {
                // only one rotation to the left is necessary
                return rotateLeft(node);
            } else {
                // the right subtree of the right subtree is LEFT heavy (RL imbalance)

                // a left rotation of the subtree is necessary, and then a right rotation of the root
                node.setRightNode(rotateRight(node.getRightNode()));
                return rotateLeft(node);
            }
        }

    }


    /**
     * if the cursor is at an item in the tree, return it
     *
     * taken from lib280
     * @return the item in the node at the cursor
     * @throws NoCurrentItem280Exception if the cursor is not a valid position
     * @precond cur != null
     * @postcond none
     * @timing O(1)
     */
    public I item() throws NoCurrentItem280Exception {
        if (!itemExists())
            throw new NoCurrentItem280Exception("Cannot access item when cursor isn't in a valid position");

        return cur.getItem();
    }


    /**
     * Does the AVL tree contain x?
     *
     * taken from lib280
     * @return True if the item appears in the tree, false otherwise
     * @precond none
     * @postcond none
     * @timing O(logn) where n is the number of nodes
     */
    public boolean has(I x) {

        // Always start at the root.
        parent = null;
        cur = this.rootNode;

        // Search
        search(x);

        return itemExists();
    }


    /**
     * protected method to abstract away the fact that a AVL node is used as parameter
     * to make a string of the AVL tree in the In-Order traversal pattern
     *
     * taken from lib280
     * @param N the root of the tree to print
     * @return a string representation of the items in the tree
     * @precond none
     * @postcond none
     * @timing O(n) where n is the number of nodes in the tree
     */
    protected String toStringInorder(AVLNode280<I> N) {
        String result = "";
        if (N.getLeftNode() != null) result += toStringInorder(N.getLeftNode());
        result += N.toString() + "\n";
        if (N.getRightNode() != null) result += toStringInorder(N.getRightNode());
        return result;
    }

    /**
     * public method to make a string of the items of the tree in the In-Order pattern
     *
     * taken from lib280
     * @return a string of the items in the tree in the In-Order pattern (child - parent - child)
     * @precond none
     * @postcond none
     * @timing O(n) where n is the number of nodes in the tree
     */
    public String toStringInorder() {
        if (!this.isEmpty())
            return toStringInorder(rootNode);
        else {
            return "<Empty>";
        }
    }

    //////////////////////////// methods for toStringByLevel only ///////////////////////
    // these methods are from lib280 and should not be called by the user

    /**
     * Set root node to new node.
     *
     * taken from lib280
     * @param newNode node to become the new root node
     * @precond none
     * @postcond rootNode = newNode
     * @timing O(1)
     */
    private void setRootNode(AVLNode280<I> newNode) {
        rootNode = newNode;
    }

    /**
     * get the contents of the root item.
     *
     * taken from lib280
     * @return a reference to the item in the root
     * @precond !isEmpty()
     * @postcond none
     * @timing O(1)
     */
    private I rootItem() throws ContainerEmpty280Exception {
        if (isEmpty())
            throw new ContainerEmpty280Exception("Cannot access the root of an empty lib280.tree.");

        return rootNode.getItem();
    }


    /**
     * get the left subtree of a root as a AVL tree object
     *
     * taken from lib280
     * @return a reference the the left subtree of the root
     * @throws ContainerEmpty280Exception if the root is empty
     * @precond !isEmpty()
     * @postcond none
     * @timing O(1)
     */
    private AVLTree280<I> rootLeftSubtree() throws ContainerEmpty280Exception {
        if (isEmpty())
            throw new ContainerEmpty280Exception("Cannot return a subtree of an empty AVL tree.");

        AVLTree280<I> result = this.clone();
        result.clear();
        result.setRootNode(rootNode.getLeftNode());
        return result;
    }

    /**
     * get the right subtree of a root as a AVL tree object
     *
     * taken from lib280
     * @return a reference the the right subtree of the root
     * @throws ContainerEmpty280Exception if the root is empty
     * @precond !isEmpty()
     * @postcond none
     * @timing O(1)
     */
    private AVLTree280<I> rootRightSubtree() throws ContainerEmpty280Exception {
        if (isEmpty())
            throw new ContainerEmpty280Exception("Cannot return a subtree of an empty AVL tree.");

        AVLTree280<I> result = this.clone();
        result.clear();
        result.setRootNode(rootNode.getRightNode());
        return result;
    }


    /**
     * String representation of the AVL tree, level by level.
     *
     * taken from lib280
     * @return a string containing the items of the tree in breadth-first order
     * @precond none
     * @postcond none
     * @timing O(n), where n = number of items in the lib280.tree
     */
    public String toStringByLevel() {
        return toStringByLevel(1);
    }

    /**
     * Form a string representation that includes level numbers.
     *
     * taken from lib280
     * @param i the level for the root of this (sub)lib280.tree
     * @return a string containing the items of the tree in breadth-first order
     * @precond none
     * @postcond none
     * @timing O(n), where n = number of items in the (sub)lib280.tree
     */
    protected String toStringByLevel(int i) {
        StringBuffer blanks = new StringBuffer((i - 1) * 5);
        for (int j = 0; j < i - 1; j++)
            blanks.append("     ");

        String result = new String();
        if (!isEmpty() && (!rootLeftSubtree().isEmpty() || !rootRightSubtree().isEmpty()))
            result += rootRightSubtree().toStringByLevel(i + 1);

        result += "\n" + blanks + i + ": ";
        if (isEmpty())
            result += "-";
        else {
            result += rootItem();
        if (!rootLeftSubtree().isEmpty() || !rootRightSubtree().isEmpty())
                result += rootLeftSubtree().toStringByLevel(i + 1);
        }
        return result;
    }

    //////////////////////////// end of methods for toStringByLevel only ///////////////////////


    /**
     * Is the current position below the bottom of the AVL tree?
     *
     * taken from lib280
     * @return true if the cursor is below the tree, false otherwise
     * @precond none
     * @postcond none
     * @timing O(1)
     */
    protected boolean below() {
        return (cur == null) && (parent != null || isEmpty());
    }

    /**
     * Is the current position above the root of the AVL tree?
     *
     * taken from lib280
     * @return true if the cursor is above the tree, false otherwise
     * @precond none
     * @postcond none
     * @timing O(1)
     */
    protected boolean above() {
        return (parent == null) && (cur == null);
    }

    /**
     * String representation via by level traversal.
     *
     * taken from lib280
     * @return String of all the items in the tree, and their level in the tree
     * @precond none
     * @postcond none
     * @timing O(n), where n = number of items
     */
    public String toString() {
        return this.toStringByLevel();
    }

    /**
     * Delete all items from the AVL tree.
     *
     * taken from lib280
     * @precond none
     * @postcond the root of the tree will be set to null and the cursor will be set in the above position
     * @timing O(1)
     */
    public void clear() {
        rootNode = null;
        parent = null;
        cur = null;
    }

    /**
     * Is the cursor at a node?
     *
     * @return true if the cursor is at a node, false otherwise
     * @precond none
     * @postcond none
     * @timing O(1)
     */
    public boolean itemExists() {
        return cur != null;
    }

    /**
     * Go to item x, if it is in the AVL tree.
     *
     * @precond none
     * @postcond the cursor will be positioned on the queried item if it is found, or in the below position if not found
     * @timing O(logn), where n is the number of node in the AVL tree
     */
    public void search(I x) {
        boolean found = false;

        parent = null;
        cur = rootNode;

        // walk continuously until you find x, and leave the cursor at x once found
        while (!found && itemExists()) {
            if (x.compareTo(item()) < 0) {
                parent = cur;
                cur = parent.getLeftNode();
            } else if (x.compareTo(item()) > 0) {
                parent = cur;
                cur = parent.getRightNode();
            } else
                found = true;
        }
    }

    /**
     * create a shallow clone of the AVL tree
     *
     * @return reference the shallow clone
     * @precond none
     * @postcond none
     * @timing O(1)
     */
    public AVLTree280<I> clone() {
        try {
            return (AVLTree280<I>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
