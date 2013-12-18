package com.Manager.stumanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StuManage extends JFrame implements ActionListener
{
	private JPanel jp1, jp2;
	
	private JLabel jl1;
	
	JButton jb1,jb2,jb3;
	
	JTable jt;
	
	JScrollPane jsp;
	
	JTextField jtf;
	
	StuModel sm;
		
	public StuManage()
	{
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1 = new JLabel("����������");
		
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2 = new JPanel();
		
		jb2 = new JButton("���");
		jb2.addActionListener(this);
		
		jb3 = new JButton("ɾ��");
		jb3.addActionListener(this);
		
		jp2.add(jb2);
		jp2.add(jb3);
		 
		
		//����һ��ģ�Ͷ���
	    sm= new StuModel();
		String[] paras={"1"};
		sm.queryStu("select * from Student where 1=?",paras);
        
		//��ʼ��JTable
		jt = new  JTable (sm);
		
		//��ʼ��jsp
		jsp = new JScrollPane(jt);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		this.add(jsp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
				
	}
	
	public static void main(String[] args)
	{
		StuManage stum = new StuManage();
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == jb1)
		{
			String name = this.jtf.getText().trim();
			
			String sql = "select * from Student where sname = ?";
			String paras[]={name};
			
			StuModel sm = new StuModel();
			sm.queryStu(sql,paras);
			
			jt.setModel(sm);
		}
		else if (e.getSource()==jb2)
		{
			StuAddDialog sa =new StuAddDialog(this,"���ѧ��",true);
		    sm=new StuModel();
		    String [] paras2={"1"};
		    sm.queryStu("select * from Student where 1=?",paras2);
		
		    jt.setModel(sm);
		}  
		else if(e.getSource()==jb3)
		{
			 //�û�ϣ���޸�
			 int rowNum=this.jt.getSelectedRow();
				if (rowNum==-1)
				{
				//��ʾ
			    JOptionPane.showMessageDialog(this,"��ѡ��һ��");
				return ;
				}
				//��ʾ�޸ĶԻ���
				new StuUpdDialog(this,"�޸�ѧ��",true,sm,rowNum);
	
				sm=new StuModel();
			    String [] paras2={"1"};
			    sm.queryStu("select * from Student where 1=?",paras2);
		
				jt.setModel(sm);	
		}	
	}	
}
