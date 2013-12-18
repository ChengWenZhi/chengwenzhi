package com.zsj.test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TeacherEntryed extends JFrame
{
	//���ڴ���ʦ��Ϣ
	private String userID;
	private JLabel jlabel;
	private JButton jb1 =new JButton("��ʦ��Ϣ");
	private JButton jb2 =new  JButton("�鿴�Լ���ѧ��");
	private JButton jb3=new JButton("�޸�ѧ���ɼ�");
	private JPanel jpanel_left=new JPanel();
	private JPanel jpl1,jpl2,jpl3,jpl4;
	private JPanel jpanel_right=new JPanel();
	JTextArea jtext=new JTextArea();
	//jrp1������ʾѧ����jrp2���ڸķ�����
	private JLabel jcno=new JLabel();
	private JLabel jsno=new JLabel();
	private JLabel jgno=new JLabel();
	private JTextField tcno =new JTextField();
	private JTextField tsno =new JTextField();
	private JTextField tgno=new JTextField();
	//-----------
	private JPanel jrp1=new JPanel();
	private JPanel jrp2=new JPanel();
	//���û���Ϣ
	//���ڴ��ʦ��Ϣ
	private String Tno,Tname,Tage,Tsex;
	//���ݿ��ѯ�������
	private Vector rowData, columnNames;
	private JTable jta1;
	//�ú���������ʾ����ѧ��
	@SuppressWarnings("unchecked")
	public JTable getMessageOfStudent()
	{
		columnNames = new Vector<String>();
		// ����
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("ϵ��");
		columnNames.add("����");
		

		rowData = new Vector();
		try
		{
			Statement st = ConnectionDataBase.con.createStatement();
			//����sql����д���Ϊ���û������**********************************************************************************************8
			ResultSet rs = st.executeQuery("select Student.Sno,Sname,Sage,Ssex,Professiona,Department,Grade from Student ,SC where SC.Sno in(select Sno from SC  where Tno in ( Select" +"'"+userID+"'" +" from Teacher)");
			while(rs.next())
			{
				Vector hang = new Vector();
				hang.add(rs.getString("Sno"));
				hang.add(rs.getInt("Sname"));
				hang.add(rs.getString("Sage"));
				hang.add(rs.getInt("Ssex"));
				hang.add(rs.getString("Professiona"));
				hang.add(rs.getString("Department"));
				hang.add(rs.getString("Grade"));

				rowData.add(hang);

			}
			rs.close();
			st.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel dftm = new DefaultTableModel(rowData, columnNames);
		@SuppressWarnings("serial")
		JTable table = new JTable(dftm)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		return table;
	}
	//�޸ĳɼ�
	public void alterGrade(String cno,String sno,String grade)
	{
		try {
			Statement st = ConnectionDataBase.con.createStatement();
			ResultSet rs = st.executeQuery("select Grade from SC where Tno="+"'"+this.Tno+"'"+"Cno="+"'"+cno+"'"+"Sno="+"'"+sno+"'");
			String tem=rs.getString("Grade");
			if(Integer.parseInt(tem)!=0)
			{
				return;
				
			}
			
			st.executeUpdate("update SC set Grade="+"'"+grade+"'");
			st.close();
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//--------------------------------
	public TeacherEntryed(String userID)
	{
		this.userID=userID;
//		���ӵ����ݿ���ʾ����ʦ��Ϣ
		try
		{
			//������������������������������������������������������������������������������������������Ҫ�ġ���������������������������������������������������
			Statement st = ConnectionDataBase.con.createStatement();
			ResultSet rs = st.executeQuery("select * from Teacher where Tno = "
					+ "'" + userID + "'");
			rs.next();
			//������Ϣ
			this.Tname = rs.getString("Tname");
			this.Tage = rs.getString("Tage");
			this.Tsex = rs.getString("Tsex");
			
			rs.close();
			st.close();
		}
		catch (SQLException e)
  	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��������
		this.GUI();
		
	}
	private void GUI()
	{
		//����jabel
		this.jlabel=new JLabel("��ӭ"+Tname+"��ʦ�ĵ���");
		this.setVisible(true);
		this.setSize(500, 500);
		this.add(jlabel,BorderLayout.NORTH);
		GridLayout grid=new GridLayout(5, 1);
		this.jpanel_left.setLayout(grid);
		jpl1=new JPanel();
		jpl2=new JPanel();
		jpl3=new JPanel();
		jpl1.add(jb1,BorderLayout.CENTER);
		jpl2.add(jb2,BorderLayout.CENTER);
		jpl3.add(jb3,BorderLayout.CENTER);
		jpanel_left.add(jpl1);
		jpanel_left.add(jpl2);
		jpanel_left.add(jpl3);
		this.add(jpanel_left,BorderLayout.WEST);
		this.jpanel_right.setBorder(BorderFactory.createTitledBorder("������"));
		this.add(jpanel_right);
		jpl1.setBorder(BorderFactory.createTitledBorder("����һ"));
		jpl2.setBorder(BorderFactory.createTitledBorder("���ܶ�"));
		jpl3.setBorder(BorderFactory.createTitledBorder("������"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpl4=new JPanel();
		jpanel_left.add(jpl4);
		jpl4.add(jtext);
		GridLayout grid2=new GridLayout(2,1);
		jpanel_right.setLayout(grid2);
		jpanel_right.add(jrp1);
		jpanel_right.add(jrp2);
		//jrp2���ڴ��޸ĳɼ�
		jrp2.add(jsno);
		jrp2.add(tsno);
		jrp2.add(jcno);
		jrp2.add(tsno);
		jrp2.add(jgno);
		jrp2.add(tgno);
		this.jsno.setText("ѧ��");
		this.jcno.setText("�γ̺�");
		this.jgno.setText("����");
		
		 

		//�Թ���һ��Ӽ�����
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TeacherEntryed.this.action(e);
				
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e2) {
				// TODO Auto-generated method stub
				TeacherEntryed.this.action2(e2);
				
			}
		});
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e3) {
				// TODO Auto-generated method stub
				TeacherEntryed.this.action3(e3);
			}
		});
		
	}
	//��ʾ��ʦ��Ϣ----------------------------------------
	private void action(ActionEvent e)
	{
//		System.out.print("111");
		//����
		this.jtext.append("��ʦ��Ϣ��ʾ���£�\n");
		this.jtext.append("����Ϊ��"+Tname+"\n");
		this.jtext.append("����Ϊ��"+Tage+"\n");
		this.jtext.append("�Ա�Ϊ��"+Tsex+"\n");
		this.jb1.setEnabled(false);
		
		
	}
	
	
	//�鿴�Լ���ѧ��---------------------------------------
	private void action2(ActionEvent e2)
	{
//		System.out.print("222");
		this.jta1=getMessageOfStudent();
		//�ɼ�ѡ����������
		this.jrp1.add(jta1);
	}
	//�޸ĳɼ�------------------------------------------
	private void action3(ActionEvent e3)
	{
//		System.out.print("333");
		String tem1,tem2,tem3;
		tem1=tsno.getText();
		tem2=tcno.getText();
		tem3=tgno.getText();
		this.alterGrade(tem2, tem1,tem3);
		
	}
	
	//����
//	public static void main(String[] args) 
//	{
//		//�����������ݿ�
//		ConnectionDataBase.connect(
//				"jdbc:sqlserver://localhost:1433;DatabaseName=table2", "sa3",
//				"123");
//		TeacherEntryed s=new TeacherEntryed("112");
//	}

}
