package rsgl;

import java.awt.* ;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public  class A extends JFrame
{
	protected JPanel p = new JPanel();
	protected JPanel p1 = new JPanel();
	protected JPanel p2 = new JPanel();
	protected JPanel p3= new JPanel();
	JMenuBar M =new JMenuBar();
	JMenu m1 = new JMenu("选项");
	JMenu m2 = new JMenu("关于");
	JMenuItem mm2 = new JMenuItem("退出");
	JMenuItem mm3 = new JMenuItem("开发者信息");
	protected JLabel l1 = new JLabel("员工编号：");
	protected JLabel l2 = new JLabel("姓       名：");
	protected JLabel l3 = new JLabel("职       务：");
	protected JLabel l4 = new JLabel("工       资：");
	protected JTextField t1 = new JTextField(10);
	protected JTextField t2 = new JTextField(10);
	protected JTextField t3 = new JTextField(10);
	protected JTextField t4 = new JTextField(10);
	protected JTextArea TA = new JTextArea(20,40);  
	
	private JButton b0 = new JButton("查询");
	private JButton b1 = new JButton("修改");
	private JButton b2 = new JButton("新增");
	private JButton b3 = new JButton("删除");
	private JButton b4 = new JButton("保存");
	private JButton b5 = new JButton("刷新显示");
	
	public A()throws Exception
	{
		super("工资表单");
		getContentPane().add(p);
		setJMenuBar(M);
		M.add(m1);
		M.add(m2);
		m1.add(mm2);
		m2.add(mm3);
		
		p.add(p1,BorderLayout.NORTH);
		p.add(p2,BorderLayout.CENTER);
		p.add(p3,BorderLayout.SOUTH);
		
		p1.setLayout(new GridLayout(4,2,1,3));
		p1.add(l1);p1.add(t1);
		p1.add(l2);p1.add(t2);
		p1.add(l3);p1.add(t3);
		p1.add(l4);p1.add(t4);

		p2.setLayout(new GridLayout(3,2,1,3));
		p2.add(b0);p2.add(b1);p2.add(b2);p2.add(b3);p2.add(b4);p2.add(b5);
		
		p3.setLayout(new GridLayout(1,1));
		p3.add(TA);
		
		t1.setText("");t2.setText("");t3.setText("");t4.setText("");TA.setText("");
		setSize(500,500);setVisible(true);
		
		ArrayList<Employee> staff =new ArrayList<>();
		
		File file = new File("DATA.txt");
		BufferedReader reader = null;
		try 
		{
			reader = new BufferedReader(new FileReader(file));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		} 
		
		
        String tempString = null;
        while ( (tempString = reader.readLine()) != null)
        {
        	staff.add(new Employee(tempString,"","",""));
        	tempString = reader.readLine();
        	staff.get(staff.size()-1).setName(tempString);
        	tempString = reader.readLine();
        	staff.get(staff.size()-1).setGrade(tempString);
        	tempString = reader.readLine();
        	staff.get(staff.size()-1).setSalary(tempString);
        }

		addWindowListener( new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				System.exit(0);
			}
    	});
		
		b0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int flag=0;
				for (int j=0;j<=staff.size()-1;j++) 
				{
					if(staff.get(j).getId().equals(t1.getText()))
					{
						t1.setText(staff.get(j).getId());
						t2.setText(staff.get(j).getName());
						t3.setText(staff.get(j).getGrade());
						t4.setText(staff.get(j).getSalary());
						flag=1;
						break;
					}
				}
				if(flag==1)
				{
					JOptionPane.showMessageDialog(null,"查询成功！","查询操作",JOptionPane.ERROR_MESSAGE);
					flag=0;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"查询失败！","查询操作",JOptionPane.ERROR_MESSAGE);
					flag=0;
				} 
			}
		});
		
    	b1.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent event)
    		{
    			String v1,v2,v3,v4;
    			v1=t1.getText();
    			v2=t2.getText();
    			v3=t3.getText();
    			v4=t4.getText();
    			if((!v1.equals( "" ))&&(!v2.equals(""))&&(!v3.equals(""))&&(!v4.equals("")))
    			{
    				int flag=0;
					for (int j=0;j<=staff.size()-1;j++) 
					{
						if(staff.get(j).getId().equals(t1.getText()))
						{
							staff.get(j).setId(v1);
							staff.get(j).setName(v2);
							staff.get(j).setGrade(v3);
							staff.get(j).setSalary(v4);
							flag=1;
							break;
						}
					}
					if(flag==1)
					{
						JOptionPane.showMessageDialog(null,"修改成功！","修改操作",JOptionPane.ERROR_MESSAGE);
						flag=0;
					}
					else
					{
						JOptionPane.showMessageDialog(null,"没有指定ID！","修改操作",JOptionPane.ERROR_MESSAGE);
						flag=0;
					} 
    			}
    		}
    	});
    	
    	b2.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent event)
    		{
    			String v1,v2,v3,v4;
    			v1=t1.getText();
    			v2=t2.getText();
    			v3=t3.getText();
    			v4=t4.getText();
    			if((!v1.equals( "" ))&&(!v2.equals(""))&&(!v3.equals(""))&&(!v4.equals("")))
    			{
    				int flag=0;
					for (int j=0;j<=staff.size()-1;j++) 
					{
						if(staff.get(j).getId().equals(t1.getText()))
						{
							flag=1;
							break;
						}
					}
					if(flag==1)
					{
						JOptionPane.showMessageDialog(null,"ID已存在！","新增操作",JOptionPane.ERROR_MESSAGE);
						flag=0;
					}
					else
					{
						staff.add(new Employee(v1,v2,v3,v4));
						JOptionPane.showMessageDialog(null,"新增成功！","新增操作",JOptionPane.ERROR_MESSAGE);
						flag=0;
					} 
    			}
    		}
    	});
    	
    	b3.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent event)
    		{
				int flag=0;
				for (int j=0;j<=staff.size()-1;j++) 
				{
					if(staff.get(j).getId().equals(t1.getText()))
					{
						staff.remove(j);
						flag=1;
						break;
					}
				}
				if(flag==1)
				{
					JOptionPane.showMessageDialog(null,"删除成功！","删除操作",JOptionPane.ERROR_MESSAGE);
					flag=0;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"没有指定ID！","删除操作",JOptionPane.ERROR_MESSAGE);
					flag=0;
				} 
			}
		});
    	
    	b4.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent event)
    		{
    			BufferedWriter writer = null;
    			try 
    			{
    				writer = new BufferedWriter(new FileWriter(file));
    			} 
    			catch (IOException e1) 
    			{
    				e1.printStackTrace();
    			} 
    			try
    			{
    				for (int j=0;j<=staff.size()-1;j++) 
		            {
		            	writer.write(staff.get(j).getId());
		            	writer.newLine();
		            	writer.write(staff.get(j).getName());
		            	writer.newLine();
		            	writer.write(staff.get(j).getGrade());
		            	writer.newLine();
		            	writer.write(staff.get(j).getSalary());
		            	writer.newLine();
		            }
    				writer.close();
    			}
    			catch (IOException e) 
				{
		            e.printStackTrace();
		        } 
    		}
    	});
    	
    	b5.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent event)
    		{
    			TA.setText("  ID  \t 姓名 \t级别\t  工资  \n");
				for (int j=0;j<=staff.size()-1;j++) 
				{
					TA.append(staff.get(j).getId()+"\t"+staff.get(j).getName()+"\t"+staff.get(j).getGrade()+"\t"+staff.get(j).getSalary()+"\n");
				} 
    		}
    	});
    	
    	mm2.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent event)
    		{
    			System.exit(0);
    		}
    	});
    	
    	mm3.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent event)
    		{
    			JOptionPane.showMessageDialog(null,"作业完成自1120310507的李超凡PANZER","作者信息",JOptionPane.ERROR_MESSAGE);
    		}
    	});
    }
	
	public static void main(String args[])
	{
		try 
		{
			new A();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
    	
 


