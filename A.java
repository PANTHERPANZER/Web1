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
	JMenu m1 = new JMenu("ѡ��");
	JMenu m2 = new JMenu("����");
	JMenuItem mm2 = new JMenuItem("�˳�");
	JMenuItem mm3 = new JMenuItem("��������Ϣ");
	protected JLabel l1 = new JLabel("Ա����ţ�");
	protected JLabel l2 = new JLabel("��       ����");
	protected JLabel l3 = new JLabel("ְ       ��");
	protected JLabel l4 = new JLabel("��       �ʣ�");
	protected JTextField t1 = new JTextField(10);
	protected JTextField t2 = new JTextField(10);
	protected JTextField t3 = new JTextField(10);
	protected JTextField t4 = new JTextField(10);
	protected JTextArea TA = new JTextArea(20,40);  
	
	private JButton b0 = new JButton("��ѯ");
	private JButton b1 = new JButton("�޸�");
	private JButton b2 = new JButton("����");
	private JButton b3 = new JButton("ɾ��");
	private JButton b4 = new JButton("����");
	private JButton b5 = new JButton("ˢ����ʾ");
	
	public A()throws Exception
	{
		super("���ʱ���");
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
					JOptionPane.showMessageDialog(null,"��ѯ�ɹ���","��ѯ����",JOptionPane.ERROR_MESSAGE);
					flag=0;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"��ѯʧ�ܣ�","��ѯ����",JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(null,"�޸ĳɹ���","�޸Ĳ���",JOptionPane.ERROR_MESSAGE);
						flag=0;
					}
					else
					{
						JOptionPane.showMessageDialog(null,"û��ָ��ID��","�޸Ĳ���",JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(null,"ID�Ѵ��ڣ�","��������",JOptionPane.ERROR_MESSAGE);
						flag=0;
					}
					else
					{
						staff.add(new Employee(v1,v2,v3,v4));
						JOptionPane.showMessageDialog(null,"�����ɹ���","��������",JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null,"ɾ���ɹ���","ɾ������",JOptionPane.ERROR_MESSAGE);
					flag=0;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"û��ָ��ID��","ɾ������",JOptionPane.ERROR_MESSAGE);
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
    			TA.setText("  ID  \t ���� \t����\t  ����  \n");
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
    			JOptionPane.showMessageDialog(null,"��ҵ�����1120310507�����PANZER","������Ϣ",JOptionPane.ERROR_MESSAGE);
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
    	
 

