import java.util.*;
import java.io.*;


class data{
	ArrayList<String> list;
	data()
	{
		list = new ArrayList<String>();
	}
	public String toString()
	{
		String s = "";
		for (int i = 0;i<list.size();i++)
			s = s+list.get(i)+" ";
		return s;
	}
}

 class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		BSTFilesBuilder f = new BSTFilesBuilder();
		int numTrees = scan.nextInt();
		int numStudents = scan.nextInt();
		f.createBSTFiles(numStudents, numTrees);
		solve(numStudents,numTrees);

	}
	
	public static void solve(int ns,int nt) throws IOException
	{
		boolean checker[] = new boolean[ns+1];
		data list[] = new data[ns+1];
		for (int i = 0;i<=ns;i++)
		{
			list[i] = new data();
			list[i].list.add((i)+" ");
		}
		for (int i = 1;i<=nt;i++)
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File("T"+i+".txt")));
			String line;
			ArrayList<String> contents = new ArrayList<String>();
			while((line = reader.readLine())!=null)
			{
				contents.add(line);
			}
			int num;
			num = Integer.parseInt(contents.get(1));
			String arr[] = contents.get(2).split(" ");
			if (contents.get(0).equals("Integer"))
			{
				Integer intarr[] = new Integer[num];
				for (int j = 0;j<num;j++)
					intarr[j] = Integer.parseInt(arr[j]);
				BST<Integer> bst = new BST<Integer>();
				for (int j = 0;j<num;j++)
				{
					bst.root = bst.insert(intarr[j],bst.root);
				}
				bst.count(bst.root.getLeft());
				int roll = bst.number+1;
				Intcase ob = new Intcase();
				bst.counter = new Integer(0);
				 bst.inOrder(ob,bst.root);
				 int answer = (int)bst.counter;
				 list[roll].list.add(answer+" ");
				 checker[roll] = true;
			}
			if (contents.get(0).equals("Float"))
			{
				Float floatarr[] = new Float[num];
				for (int j = 0;j<num;j++)
					floatarr[j] = Float.parseFloat(arr[j]);
				BST<Float> bst = new BST<Float>();
				for (int j = 0;j<num;j++)
				{
					bst.root = bst.insert(floatarr[j],bst.root);
				}
				bst.count(bst.root.getLeft());
				int roll = bst.number+1;
				Floatcase ob = new Floatcase();
				bst.counter = new Float(0);
				 bst.inOrder(ob,bst.root);
				 float answer = bst.counter;
				 list[roll].list.add(answer+" ");
				 checker[roll] = true;
			}
			if (contents.get(0).equals("String"))
			{
				BST<String> bst = new BST<String>();
				for (int j = 0;j<num;j++)
				{
					bst.root = bst.insert(arr[j],bst.root);
				}
				bst.count(bst.root.getLeft());
				int roll = bst.number+1;
				Stringcase ob = new Stringcase();
				bst.counter = new String();
				 bst.inOrder(ob,bst.root);
				 String answer = bst.counter;
				 list[roll].list.add(answer+" ");
				 checker[roll] = true;

			}	
		}
		int chocos = 0;
		String output="";
		PrintWriter out = new PrintWriter("filename.txt");
		out.println(list[1].list.get(0));
		for (int i = 1;i<list.length;i++)
			{
				if (list[i].list.size()>1)
					{
						output+=list[i].toString();
						output+="\n";
					}
				else chocos++;
			}
		output+=chocos+"\n";
		System.out.print(output);
			OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream("output.txt"));
			br.write(output);
			br.close();
		
	}

}

 
class BST<T extends Comparable<T>>{
	Node<T> root;
	int number = 0;
	T counter;
	public Node insert(T val,Node<T> root)
	{
		if (root == null)
			{
				root = new Node<T>(val);
				return root;
			}
		Node<T> a = new Node<T>(val);
		if (root.compareTo(a) > 0)
			root.setLeft(insert(val,root.getLeft()));
		else
			root.setRight(insert(val,root.getRight()));
		return root;
	}
	public int findRootIndex(Node<T> node)
	{
		 count(node.getLeft());
		 return number;
	}
	public void count(Node<T> node)
	{
		if (node!=null)
		{
			count(node.getLeft());
			number++;
			count(node.getRight());
		}
	}
	
	public void inOrder(calculate<T> c,Node<T> node)
	{
		if (node!=null)
		{
			inOrder(c,node.getLeft());
			counter = c.findans(counter,node.getData());
			inOrder(c,node.getRight());
		}
	}
}

interface calculate<T>
{
	T findans(T x,T y);
}
class Stringcase implements calculate<String>
{
	public String findans(String x,String y)
	{
		return x+y;
	}
}
class Intcase implements calculate<Integer>
{
	
	public Integer findans(Integer x,Integer y)
	{
		return x+y;
	}
}
class Floatcase implements calculate<Float>
{
	public Float findans(Float x,Float y)
	{
		return x+y;
	}
}


class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
	private T data;
	private Node left;
	private Node right;
	Node(T data)
	{
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
@Override
public int compareTo(Node<T> arg0) {
	return this.getData().compareTo(arg0.getData());
}
}



 class BSTFilesBuilder {

	public void createBSTFiles(int numStudents, int numTrees) {
		Random rand = new Random();
		for (int i = 1; i <= numTrees; i++) {
		    try {
				PrintWriter w = new PrintWriter(new File(("T"+i+".txt")));
				int type = rand.nextInt(3) + 1;
				if(type == 1) {
					w.println("Integer");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextInt(1000));
						w.print(" ");
					}
				}
				else if(type == 2) {
					w.println("Float");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextFloat()*1000);
						w.print(" ");
					}
				}
				else {
					w.println("String");
					w.println(numStudents);
					String alphabet = "abcdefghijklmnopqrstuvwxyz";
					for (int j = 1; j <= numStudents; j++) {
						int len = rand.nextInt(10)+1;
						for (int k = 0; k < len; k++)
							w.print(alphabet.charAt(rand.nextInt(alphabet.length())));
						w.print(" ");
					}
				}
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

	



