import java.util.*;
// Walker Christensen

public class HuffmanTree{
	// private inner class
	private class Node{
		private Node left; 
		private char data; 
		private Node right;
		private Node(Node l, char d, Node r){
			left = l;
			data = d;
			right = r;
		}
		public boolean isLeaf() {
			return this.left == null && this.right == null;
		}
	}
	// references to the root of object and position in tree when decoding
		private Node root;
		private Node current;
		
		public HuffmanTree(){
			root = null;
			current = null;
		}
		// makes a new huffman tree with one character
		public HuffmanTree(char d){
			Node mew = new Node(null, d, null);
			root = mew;
		}
		
		public HuffmanTree(String t, char nonLeaf){
			// Assume t represents a post order representation of the tree
			// where a node is either a leaf or has two children.
			// nonLeaf is the char value of the data in the non-leaf nodes
			root = buildTree(t, nonLeaf);
		}
		// Builds a huffman tree from priority queue information
		private Node buildTree(String t, char nonLeaf){
			Stack<HuffmanTree> stack = new Stack<>();
			stack.push(new HuffmanTree(t.charAt(0)));
			stack.push(new HuffmanTree(t.charAt(1)));
			int i = 2;
			while(i < t.length()){
				char c = t.charAt(i);
				if(c == nonLeaf){
					HuffmanTree h1 = stack.pop();
					HuffmanTree h2 = stack.pop();
					stack.push(new HuffmanTree(h1, h2, nonLeaf));
				}else{
					stack.push(new HuffmanTree(c));
				}
				i++;
			}
			return stack.pop().root;
		}
		// makes new huffman tree with two existing trees
		public HuffmanTree(HuffmanTree b1, HuffmanTree b2, char d){
			Node mew = new Node(b2.root, d, b1.root);
			root = mew;
		}
		// the following methods allow a user object to follow a path in
		// the tree. Each method except atLeaf and current changes the value of current
		// atLeaf returns true of the current postition is a leaf, otherwise it 
		// returns false
		// current returns the data value in the current Node
		public void moveRoot(){current = root;}
		public void moveLeft(){current = current.left;}
		public void moveRight(){current = current.right;}
		public boolean atLeaf(){return current.isLeaf();}
		public char current(){return current.data;}
		
		// Inner class to create an iterator. The iterator allows the user
		// class to find a path from the root to a leaf. The paths are 
		// sequences of 0s and 1s. 0 means left and 1 means right. You will find 
		// it easier to find all the paths when the iterator is created.
		public Iterator<String> iterator(){
			// retrun a PathIterator object'
			return new PathIterator();
		}
		public String toString(){
			String string="";
			return toStringAux(root, string);
		}
		// prints post order traversal string representation of tree
		private String toStringAux(Node r, String string){
			if(r.isLeaf()){
				return string+r.data;
			}
			if(r.left != null && r.right != null){
				return toStringAux(r.left, string) + toStringAux(r.right, string) + r.data;
			}else if(r.left != null){
				return toStringAux(r.left, string) + r.data;
			}else{
				return toStringAux(r.right, string) + r.data;
			}
		}
		// creates encodings for each character
		public class PathIterator implements Iterator<String>{
			Node iter;
			String string;
			ArrayList<String> aList;
			public PathIterator(){
				iter = root;
				aList = new ArrayList<>();
				string = "";
				makeEncodings(iter, string);
			}
			public boolean hasNext(){
			 return !aList.isEmpty();
			}
			public String next(){
				return aList.remove(0);
			}
			// this is most of the work
			private void makeEncodings(Node r, String string){
				if(r.isLeaf()){
					aList.add(string + r.data);
				}else{
				    makeEncodings(r.left, string+"0");
					makeEncodings(r.right, string+"1");
				}
			}
			public void remove(){
				throw new UnsupportedOperationException("Not an available feature");
			}
			
		}
	}

