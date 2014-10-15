import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class qos 
{	
	static int selectMax(ArrayList<Node> aList)
	{
         int totalCount = aList.size();
         int ID = 0; 
         if (totalCount >= 1)
         {
        	 double max = aList.get(0).Q;
             for (int i = 0; i < totalCount; i++)
             {
                 double temp = aList.get(i).Q;
                 if (max < temp)
                 {
                     max = temp;
                     ID = i;
                 }
             } 
         }
         return ID;
	}
	
	public static void main(String[] args)throws Exception
	{	
		File fs=new File("SERVICE.txt");
		FileInputStream fins=new FileInputStream(fs);
		InputStreamReader inrs=new InputStreamReader(fins);
		BufferedReader bfrs=new BufferedReader(inrs);
		
		File fr=new File("REQ.txt");
		FileInputStream finr=new FileInputStream(fr);
		InputStreamReader inrr=new InputStreamReader(finr);
		BufferedReader bfrr=new BufferedReader(inrr);
		
		File fp=new File("PROCESS.txt");
		FileInputStream finp=new FileInputStream(fp);
		InputStreamReader inrp=new InputStreamReader(finp);
		BufferedReader bfrp=new BufferedReader(inrp);
		
		FileOutputStream fout=new FileOutputStream("RESULT.txt");
		OutputStreamWriter outw=new OutputStreamWriter(fout);
		BufferedWriter bfw=new BufferedWriter(outw);
		
		int i=0;
		int i4=0;
		String temp="";//临时行数据。
		String tempr="";
		String tempp="";
		String[] tempArr={};
		String tempSafe="";
		String tempPrice="";
		double Req[]=new double[2];
		
		double Safe[]=new double[7000];
		double Price[]=new double[7000];
		
		ArrayList<ArrayList> model = new ArrayList<>();
		
		long t1=System.currentTimeMillis();
		
		while((temp=bfrs.readLine())!=null)
		{
			tempArr=temp.split("[ ]+");
			tempSafe=tempArr[2];
			Double dsafe  = Double.parseDouble(tempSafe);
			Safe[i]=dsafe;
			tempPrice=tempArr[4];
			Double dprice = Double.parseDouble(tempPrice);
			Price[i]=dprice;
			i=i+1;
		}
		for(int i2=0;i2<14;i2++)
		{
			model.add(new ArrayList<Node>());
			ArrayList<Node> m = new ArrayList<>();
			for(int i3=0;i3<500;i3++)
			{
				m.add(new Node(i2+1,i3+1,Price[i4+i3],Safe[i4+i3]));
				model.set(i2, m);
			}
			i4=i4+500;
		}
		
		int count=0;
		while((tempr=bfrr.readLine())!=null && (tempp=bfrp.readLine())!=null)
		{
			count++;
			char out[]=tempp.toCharArray();
			tempp=tempp.replaceAll("\\(","");
			tempp=tempp.replaceAll("\\,","");
			tempp=tempp.replaceAll("\\)","");
		    //去除重复
			tempp = new StringBuffer(new StringBuffer(tempp).reverse().toString().replaceAll( "(.)(?=.*\\1)", "")).reverse().toString(); 
			char[] List1 = tempp.toCharArray();
			ArrayList<Integer> List = new ArrayList<>();
			for(i=0;i<tempp.length();i++)
			{
				List.add((int)List1[i]-64);
			}
			//以字符串Node形式存储节点
	/////////////////////////////////////////////////////////////////////////////////////////////////////////		
			tempr=tempr.replaceAll("\\(","");
			tempr=tempr.replaceAll("\\,"," ");
			tempr=tempr.replaceAll("\\)","");
			tempArr=tempr.split("[ ]+");
			Req[0]= Double.parseDouble(tempArr[0]);
			Req[1]= Double.parseDouble(tempArr[1]);
			//以数组Req形式储存客户qos
	/////////////////////////////////////////////////////////////////////////////////////////////////////////		
			
			//Start
			for(int i1=0;i1<List.size();i1++)
			{
				ArrayList<Node> n = new ArrayList<>();
				n = model.get(List.get(i1)-1);
				for(int i11=0;i11<n.size();i11++)
				{
					if(n.get(i11).sec<Req[0] || n.get(i11).price>Req[1])
					{
						n.remove(i11);
						i11--;
					}
				}
				model.set(List.get(i1)-1, n);
			}
			
			ArrayList<Node> waiting = new ArrayList<>();
			Node S = new Node(0,0,0,1);
			waiting.add(S);
			int pointer = 0;
			double Qup[]=new double[2];
			Qup[0]=0;Qup[1]=0;
			
			while(true)
			{
				int tMax = selectMax(waiting);
				if(waiting.get(tMax).depth==List.get(List.size()-1))
				{
					/*bfw.write();
	            	bfw.newLine();*/
					bfw.write("Route "+count+" :");
					int i2=0;
					for(int i1=0;i1<out.length;i1++)
					{
						if(out[i1]>'Z' || out[i1]<'A')
						{
							bfw.write(out[i1]);
						}
						else
						{
							bfw.write(out[i1]+"-"+waiting.get(tMax).label.get(out[i1]-'A'));
						}
					}
					bfw.newLine();
					bfw.write("Q="+waiting.get(tMax).Q);
					bfw.newLine();
					break;
				}
				else
				{				
					ArrayList<Node> p = new ArrayList<>();
					ArrayList<Node> o = new ArrayList<>();
					pointer=List.get(0);
					for(int i1=0;i1<List.size();i1++)
					{
						if(List.get(i1)==waiting.get(tMax).depth)
						{
							pointer=List.get(i1+1);
						}
					}
					p = model.get(pointer-1);
					
					for(int i1=0;i1<waiting.size();i1++)
					{
						if(i1==tMax)
							continue;
						else
						{
							if(waiting.get(i1).sec<waiting.get(tMax).sec && waiting.get(i1).price>waiting.get(tMax).price)
							{
								for(int i11=0;i11<p.size();i11++)
								{
									if(p.get(i11).sec < (waiting.get(tMax).price-waiting.get(i1).price)/(waiting.get(tMax).sec-waiting.get(i1).sec))
									{
										tMax=i1;
									}
								}
							}
						}
					}
					
					for(int i1=0;i1<p.size();i1++)
					{
						o.add(new Node(waiting.get(tMax),p.get(i1)));
					}
					waiting=o;
				}
			}
		}
		long t2=System.currentTimeMillis();
		bfw.write("Start At: "+t1+"ms");
		bfw.newLine();
		bfw.write("Over At: "+t2+"ms"); 
		bfw.newLine();
		bfw.write("Time for Whole Process: "+(t2-t1)+"ms"); 
		bfw.newLine();
		
		bfw.close();
	}
}


class Node
{
	int depth;
	ArrayList<Integer> label = new ArrayList<Integer>();
	double price;
	double sec;
	double Q;
	
	public Node(int aDepth,int aCode,double aPrice,double aSec)
	{
		depth=aDepth;
		if(aDepth!=0)
		{
			for(int i=1;i<aDepth;i++)
			{
				label.add(-1);
			}
			label.add(aCode);
		}
		price=aPrice;
		sec=aSec;
		Q=aSec-aPrice/100;
	}
	public Node(Node aNode,Node bNode)
	{
		depth = bNode.depth;
		for(int i=1;i<=aNode.depth;i++)
		{
			label.add(aNode.label.get(i-1));
		}
		for(int i=aNode.depth+1;i<bNode.depth;i++)
		{
			label.add(-1);
		}
		label.add(bNode.label.get(bNode.depth-1));
		price = aNode.price+bNode.price;
		sec = aNode.sec*bNode.sec;
		Q=sec-price/100;
	}
}