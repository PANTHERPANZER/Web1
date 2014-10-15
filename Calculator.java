import java.util.Scanner;//Change

public class Calculator 
{
	public static void main(String[] args)
	{
		int result=0;
		Scanner in = new Scanner(System.in);
		System.out.print("输入表达式：\n");
		Number ob1= new Number();
		ob1.valueOf(in.nextInt());
		String op=in.next();
		Number ob2= new Number();
		ob2.valueOf(in.nextInt());
		switch (op)
		{
			case "+":
		 		result=ob1.add(ob2.getValue());
		 		break;
		 	case "-":
		 		result=ob1.sub(ob2.getValue());
		 		break;
		 	default:
		 		break;
		 }
		System.out.print("Result is " + result);
	}
}

class Number 
{
	private int ob1=0;
	public int valueOf(int ob)
	{
		ob1=ob;
		return ob1;
	}
	public int getValue()
	{
		return ob1;
	}
	public int add(int ob2)
	{
		return ob1+ob2;
	}
	public int sub(int ob2)
	{
		return ob1-ob2;
	}
}
