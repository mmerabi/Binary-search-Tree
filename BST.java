//Michael Merabi CS182
//Lab Exam #3

public class BST {

		public static  Node root;
		public BST(){
			this.root = null;
		}
		
		public boolean find(int id){
			Node current = root;
			while(current!=null){
				if(current.data==id){
					return true;
				}else if(current.data>id){
					current = current.left;
				}else{
					current = current.right;
				}
			}
			return false;
		}
		public boolean delete(int id){
			Node parent = root;
			Node current = root;
			boolean isLeftChild = false;
			while(current.data!=id){
				parent = current;
				if(current.data>id){
					isLeftChild = true;
					current = current.left;
				}else{
					isLeftChild = false;
					current = current.right;
				}
				if(current ==null){
					return false;
				}
			}
			
			if(current.left==null && current.right==null){
				if(current==root){
					root = null;
				}
				if(isLeftChild ==true){
					parent.left = null;
				}else{
					parent.right = null;
				}
			}
		
			else if(current.right==null){
				if(current==root){
					root = current.left;
				}else if(isLeftChild){
					parent.left = current.left;
				}else{
					parent.right = current.left;
				}
			}
			else if(current.left==null){
				if(current==root){
					root = current.right;
				}else if(isLeftChild){
					parent.left = current.right;
				}else{
					parent.right = current.right;
				}
			}else if(current.left!=null && current.right!=null){
				
		
				Node successor	 = getSuccessor(current);
				if(current==root){
					root = successor;
				}else if(isLeftChild){
					parent.left = successor;
				}else{
					parent.right = successor;
				}			
				successor.left = current.left;
			}		
			return true;		
		}
		
		public Node getSuccessor(Node deleleNode){
			Node successsor =null;
			Node successsorParent =null;
			Node current = deleleNode.right;
			while(current!=null){
				successsorParent = successsor;
				successsor = current;
				current = current.left;
			}

			if(successsor!=deleleNode.right){
				successsorParent.left = successsor.right;
				successsor.right = deleleNode.right;
			}
			return successsor;
		}
		public void insert(int id){
			Node newNode = new Node(id);
			if(root==null){
				root = newNode;
				return;
			}
			Node current = root;
			Node parent = null;
			while(true){
				parent = current;
				if(id<current.data){				
					current = current.left;
					if(current==null){
						parent.left = newNode;
						return;
					}
				}else{
					current = current.right;
					if(current==null){
						parent.right = newNode;
						return;
					}
				}
			}
		}
		public void display(){
			display(root);
	      
		}
		private void display(Node root){
			if(root!=null){
				display(root.left);
				System.out.print(" " + root.data);
				display(root.right);
			}
		}
/*===============================================
                         ==================================================
               CODE STARTS HERE
                =========================================================*/
                
                
     // A utility function to do inorder traversal of BST EX: 20, 30, 40, 50
                
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.data);
            inorderRec(root.right);
            }
        }   
    
    //Find the height of the tree
     int treeHeight(Node node) 
    {
        if (node == null)
            return 0;
        else
        {
            
            int lDepth = treeHeight(node.left);
            int rDepth = treeHeight(node.right);
  
          
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }
     
     // method which calculates sum
     void leafSum(Node root){
       int sum = 0;
        if (root == null)
            return;
      
        if (root.left == null && root.right == null){
            sum += root.data;
            System.out.println("The sum of all the leaves are "+sum);}
 
        leafSum(root.left);
        leafSum(root.right);
    }
     
     void printAllLeft(Node node, boolean isleft) 
    {
        if (node == null)
            return;
  
        // Check whether this node is a leaf node and is left.
        if (node.left == null && node.right == null && isleft)
            System.out.println(root.data);
  
        // Pass true for left and false for right
        printAllLeft(node.left, true);
        printAllLeft(node.right, false);
    }
     
     //Comparison statement that goes through and deletes all the larger 
     // components of the tree and then replaces the kids with the parents
     
     public void deleteGreaterThan(int n){
            int comparison = n;
         
       if (root == null)
           return;
       
    Node current = null;
     if (comparison > root.data){
           current = root.right;
         if (comparison > current.data){
               getSuccessor(current);
               deleteGreaterThan(n);
               
           }else{ current = root.left;
                  if (comparison > current.data){
               getSuccessor(current);
               deleteGreaterThan(n);               
           } else { return;}
           
       }
   }
     else{
         System.out.println("The new Tree with greater objects deleted is..");
         inorderRec(root);
         return;
     }
     
}
     
     
     
     //======================== MAIN ===================================
	   
	   public static void main( String [] args ){
	         BST bst = new BST();
	         bst.insert(50);
	         bst.insert(30);
	         bst.insert(68);
	         bst.insert(15);
	         bst.insert(45);
	         bst.insert(75);
	         bst.insert(37);
	         
                 bst.deleteGreaterThan(40);
                 bst.leafSum(root);
                 bst.inorderRec(root);
                 bst.treeHeight(root);
                 bst.deleteGreaterThan(30);
                 
                 
	     }
	}

	class Node{
		int data;
		Node left;
		Node right;	
		public Node(int data){
			this.data = data;
			left = null;
			right = null;
		}
	}


