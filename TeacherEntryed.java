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
	//用于存老师信息
	private String userID;
	private JLabel jlabel;
	private JButton jb1 =new JButton("教师信息");
	private JButton jb2 =new  JButton("查看自己的学生");
	private JButton jb3=new JButton("修改学生成绩");
	private JPanel jpanel_left=new JPanel();
	private JPanel jpl1,jpl2,jpl3,jpl4;
	private JPanel jpanel_right=new JPanel();
	JTextArea jtext=new JTextArea();
	//jrp1用于显示学生，jrp2用于改分数。
	private JLabel jcno=new JLabel();
	private JLabel jsno=new JLabel();
	private JLabel jgno=new JLabel();
	private JTextField tcno =new JTextField();
	private JTextField tsno =new JTextField();
	private JTextField tgno=new JTextField();
	//-----------
	private JPanel jrp1=new JPanel();
	private JPanel jrp2=new JPanel();
	//存用户信息
	//用于存教师信息
	private String Tno,Tname,Tage,Tsex;
	//数据库查询相关主键
	private Vector rowData, columnNames;
	private JTable jta1;
	//该函数用于显示她的学生
	@SuppressWarnings("unchecked")
	public JTable getMessageOfStudent()
	{
		columnNames = new Vector<String>();
		// 列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("年龄");
		columnNames.add("性别");
		columnNames.add("系别");
		columnNames.add("分数");
		

		rowData = new Vector();
		try
		{
			Statement st = ConnectionDataBase.con.createStatement();
			//以下sql语句有错，因为外键没创建好**********************************************************************************************8
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
	//修改成绩
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
//		链接到数据库显示出教师信息
		try
		{
			//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。可能要改。。。。。。。。。。。。。。。。。。。。。。。。。。
			Statement st = ConnectionDataBase.con.createStatement();
			ResultSet rs = st.executeQuery("select * from Teacher where Tno = "
					+ "'" + userID + "'");
			rs.next();
			//导出信息
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
		//开启画面
		this.GUI();
		
	}
	private void GUI()
	{
		//建立jabel
		this.jlabel=new JLabel("欢迎"+Tname+"老师的到来");
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
		this.jpanel_right.setBorder(BorderFactory.createTitledBorder("主界面"));
		this.add(jpanel_right);
		jpl1.setBorder(BorderFactory.createTitledBorder("功能一"));
		jpl2.setBorder(BorderFactory.createTitledBorder("功能二"));
		jpl3.setBorder(BorderFactory.createTitledBorder("功能三"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpl4=new JPanel();
		jpanel_left.add(jpl4);
		jpl4.add(jtext);
		GridLayout grid2=new GridLayout(2,1);
		jpanel_right.setLayout(grid2);
		jpanel_right.add(jrp1);
		jpanel_right.add(jrp2);
		//jrp2用于存修改成绩
		jrp2.add(jsno);
		jrp2.add(tsno);
		jrp2.add(jcno);
		jrp2.add(tsno);
		jrp2.add(jgno);
		jrp2.add(tgno);
		this.jsno.setText("学号");
		this.jcno.setText("课程号");
		this.jgno.setText("分数");
		
		 

		//对功能一添加监听器
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
	//显示教师信息----------------------------------------
	private void action(ActionEvent e)
	{
//		System.out.print("111");
		//测试
		this.jtext.append("教师信息显示如下：\n");
		this.jtext.append("姓名为："+Tname+"\n");
		this.jtext.append("年龄为："+Tage+"\n");
		this.jtext.append("性别为："+Tsex+"\n");
		this.jb1.setEnabled(false);
		
		
	}
	
	
	//查看自己的学生---------------------------------------
	private void action2(ActionEvent e2)
	{
//		System.out.print("222");
		this.jta1=getMessageOfStudent();
		//成绩选出来后如下
		this.jrp1.add(jta1);
	}
	//修改成绩------------------------------------------
	private void action3(ActionEvent e3)
	{
//		System.out.print("333");
		String tem1,tem2,tem3;
		tem1=tsno.getText();
		tem2=tcno.getText();
		tem3=tgno.getText();
		this.alterGrade(tem2, tem1,tem3);
		
	}
	
	//测试
//	public static void main(String[] args) 
//	{
//		//测试链接数据库
//		ConnectionDataBase.connect(
//				"jdbc:sqlserver://localhost:1433;DatabaseName=table2", "sa3",
//				"123");
//		TeacherEntryed s=new TeacherEntryed("112");
//	}

}
