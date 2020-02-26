// Bronson Schultz, 11231230, bcs269
// CMPT 280, Assignment 4, Question 1
package lib280.tree;

public class AVLNode280<I extends Comparable<? super I>> {

    private int leftHeight, rightHeight;

    /** Contents of the node. */
    protected I item;

    /** The left node. */
    protected AVLNode280<I> leftNode;

    /** The right node. */
    protected AVLNode280<I> rightNode;



    public AVLNode280(I x){

        item = x;
        leftNode = null;
        rightNode = null;
        leftHeight = 0;
        rightHeight = 0;

    }

    public I getItem() {
        return item;
    }

    public AVLNode280<I> getLeftNode() {
        return leftNode;
    }

    public AVLNode280<I> getRightNode() {
        return rightNode;
    }

    public int getLeftHeight() {
        return leftHeight;
    }

    public void setLeftHeight(int leftHeight) {
        this.leftHeight = leftHeight;
    }

    public int getRightHeight() {
        return rightHeight;
    }

    public void setRightHeight(int rightHeight) {
        this.rightHeight = rightHeight;
    }


    public void setLeftNode(AVLNode280<I> node) {
        leftNode = node;
    }

    public void setRightNode(AVLNode280<I> node) {
        rightNode = node;
    }

    /**
     * Returns a string representation of the item contained within the node.
     */
    public String toString() {
        return item.toString();
    }

    }

