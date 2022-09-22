//Declaring the BinarySearch Tree node
class BSTNode
{
    //value field for storing the data
    int value;
    //reference to the left child
    BSTNode left_child;
    //reference to the right child
    BSTNode right_child;
}
public class BinarySearchTree {
    //Declaring the root node for the binary search tree
    private BSTNode root;
    //Binary Search Tree Constructor which initializes the root node to null
    // This constructor is called when the object for the BinarySearchTree class has been created
    public BinarySearchTree()
    {
        root=null;
    }
    // Method to return true if the Binary Search Tree is empty or else false
    public boolean isEmpty() {
        if (root == null)
            return true;
        else
            return false;
    }
    //Method for Inserting the values to the Binary Search Tree
    public void BinarySearchTreeInsertion(int data)
    {
        //Creating a temporary node temporary_node which will later be attached to the Binary Search Tree at correct position
        BSTNode temporary_node=new BSTNode();
        //Declaring a temporary pointer to navigate through the Binary Search tree
        BSTNode temporary_pointer;
        // Inserting the data value into the value field of Binary Search Tree and initialising its left child field and right child field to NULL
        temporary_node.value=data;
        temporary_node.left_child=null;
        temporary_node.right_child=null;
        //If there are no nodes in the Binary search tree then the temporary node will be the root node
        if(root==null)
        {
            root=temporary_node;
        }
        //If there are nodes in the Binary Search Tree then this part will be executed
        else
        {
            //Intialising the temporary pointer to root for navigating through the binary search tree
            temporary_pointer=root;
            //Until the temporary point is not Null
            // Traverse through the tree to find the right place for insertion into the binary Search tree
            while(temporary_pointer!=null)
            {
                //If the new data value is less than the value of temporary pointer
                //Insert the data into left subtree
                if(data<temporary_pointer.value)
                {
                    //If the left child of temporary pointer is not null
                    // then update the temporary pointer to left child of temporary pointer and continue with the loop
                    if(temporary_pointer.left_child!=null)
                    {
                        temporary_pointer=temporary_pointer.left_child;
                    }
                    //If the left child of temporary pointer is null
                    //Insert the temporary node as the left child of the temporary pointer and exit from the loop
                    else
                    {
                        temporary_pointer.left_child=temporary_node;
                        break;
                    }
                }
                //If the new data value is greater than the value of temporary pointer
                //Insert the data into the right subtree
                else if(data>temporary_pointer.value)
                {
                    //If the right child of temporary pointer is not null
                    // then update the temporary pointer to right child of temporary pointer and continue with the loop
                    if(temporary_pointer.right_child!=null)
                    {
                        temporary_pointer=temporary_pointer.right_child;
                    }
                    //If the right child of temporary pointer is null
                    //Insert the temporary node as the right child of the temporary pointer and exit from the loop
                    else
                    {
                        temporary_pointer.right_child = temporary_node;
                        break;
                    }
                }
                //If the data value is already present in the binary search Tree
                // then it displays a message of Duplicate values are not allowed
                else
                {
                    System.out.println("Duplicate values are not allowed"+data);
                    break;
                }
            }
        }
    }
    //Inorder(Left-Root-Right) traversal of Binary Search Tree
    public void inorder_traversal_BST(BSTNode temporary_pointer)
    {
        if(temporary_pointer==null)
            return;
        inorder_traversal_BST(temporary_pointer.left_child);
        System.out.print("\t"+temporary_pointer.value);
        inorder_traversal_BST(temporary_pointer.right_child);
    }
    // Postorder (Left-Right-Root) traversal of Binary Search Tree
    public void postorder_traversal_BST(BSTNode temporary_pointer)
    {
        if(temporary_pointer==null)
            return;
        postorder_traversal_BST(temporary_pointer.left_child);
        postorder_traversal_BST(temporary_pointer.right_child);
        System.out.print("\t"+temporary_pointer.value);

    }
    //Preorder(Root-Left-Right) traversal of Binary Search Tree
    public void preorder_traversal_BST(BSTNode temporary_pointer)
    {
        if(temporary_pointer==null)
            return;
        System.out.print("\t"+temporary_pointer.value);
        preorder_traversal_BST(temporary_pointer.left_child);
        preorder_traversal_BST(temporary_pointer.right_child);
    }
    //Deletion Method which will call the BinarySearchTreeDeletion method to delete the value in the Binary Search Tree
    void Deletion(int key)
    {
        System.out.println("Deleted Node "+key+" is replaced with Successor ");
        root = BinarySearchTreeDeletion(root, key);
    }
    //Method to delete the key value in the tree
    BSTNode BinarySearchTreeDeletion(BSTNode root, int key)
    {
        //Base case to check if the root is null
        if (root == null)
            return root;
        //If key value greater than the root value then recursively call the right subtree
        if (key > root.value)
            root.right_child = BinarySearchTreeDeletion(root.right_child, key);
        //If key value less than the root value then recursively call the left subtree
        else if (key < root.value)
            root.left_child = BinarySearchTreeDeletion(root.left_child, key);
        //If the key value is equal to the root value then replace the root value with its successor value
        else
        {
            if (root.left_child == null)
                return root.right_child;
            else if (root.right_child == null)
                return root.left_child;
            //Replacing the value with its Successor value
            root.value = SuccessorNode(root.right_child);
            root.right_child = BinarySearchTreeDeletion(root.right_child, root.value);
        }
        return root;
    }
    //Finding the successor in the right Sub Tree which is the minimum value in the right subtree
    int SuccessorNode(BSTNode root)
    {
        //Declaring the variable and intialising with the root value
        int minimum_of_right_subtree = root.value;
        //Looping though the right subtree and finding the minimum value which will
        // be the left most child in the right subtree that is the minimum value of right subtree
        while (root.left_child != null)
        {
            minimum_of_right_subtree = root.left_child.value;
            root = root.left_child;
        }
        return minimum_of_right_subtree;
    }
    public static void main(String[] args)
    {
        //Creating an object for the BinarySearchTree class
        BinarySearchTree BST_tree = new BinarySearchTree();
        //Inserting the values into the Binary Search Tree by calling BinarySearchTreeInsertion method
        BST_tree.BinarySearchTreeInsertion(40);
        BST_tree.BinarySearchTreeInsertion(60);
        BST_tree.BinarySearchTreeInsertion(20);
        BST_tree.BinarySearchTreeInsertion(80);
        BST_tree.BinarySearchTreeInsertion(50);
        BST_tree.BinarySearchTreeInsertion(10);
        BST_tree.BinarySearchTreeInsertion(30);
        BST_tree.BinarySearchTreeInsertion(15);
        BST_tree.BinarySearchTreeInsertion(5);
        BST_tree.BinarySearchTreeInsertion(35);
        BST_tree.BinarySearchTreeInsertion(25);
        BST_tree.BinarySearchTreeInsertion(45);
        BST_tree.BinarySearchTreeInsertion(55);
        BST_tree.BinarySearchTreeInsertion(70);
        BST_tree.BinarySearchTreeInsertion(90);
        BST_tree.BinarySearchTreeInsertion(32);
        BST_tree.BinarySearchTreeInsertion(33);
        BST_tree.BinarySearchTreeInsertion(48);
        BST_tree.BinarySearchTreeInsertion(46);
        System.out.println("Binary Search Tree");
        // Traversering the Binary Search Tree in Preorder Traversal
      /*
        System.out.println("\nPreorder Traversal of the Binary Search Tree");
        preorder_traversal_BST(tree.root);
         // Traversering the Binary Search Tree in Postorder Traversal
        System.out.println("\nPostorder Traversal of the Binary Search Tree ");
        postorder_traversal_BST(tree.root);
       */
        //Inorder traversal of the Binary Search Tree without any deletions
        System.out.println("\nInorder Traversal of the Binary Search Tree");
        BST_tree.inorder_traversal_BST(BST_tree.root);
        //Deleting the node with value 40 by calling Deletion method
        System.out.println("\nDeleting the node 40 from the Binary Search Tree");
        BST_tree.Deletion(40);
        //Inorder traversal of the Binary Search Tree after deleting 40
        System.out.println("Inorder traversal of the Binary Search tree after Deletion of Node 40");
        BST_tree.inorder_traversal_BST(BST_tree.root);
        //Deleting the node with value 20 by calling Deletion method
        System.out.println("\nDelete the node 20 from the Binary Search Tree");
        BST_tree.Deletion(20);
        //Inorder traversal of the Binary Search Tree after deleting 20
        System.out.println("Inorder traversal of the Binary Search tree after Deletion of Node 20");
        BST_tree.inorder_traversal_BST(BST_tree.root);
    }
}
