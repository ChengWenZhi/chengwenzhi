package database;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentEntryed extends MouseAdapter
{
	private final int CRDIT = 10;
	private JFrame jframe;
	private JSplitPane jsp;
	private JScrollPane jsp2, jsp3;
	private JLabel jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8;
	private JPanel jpanel_left, jpanel_right, jpanel_right_1, jpanel_right_2,
			jpanel_right_3, jpanel_up;
	private CardLayout cl = new CardLayout();
	private JButton jbt1, jbt2, jbt3;

	private JTable jtb1, jtb2;
	// 学生信息显示
	private JLabel sid, sname, ssex, sage, sprofessiona, sdepartment, stitle;
	private JLabel sid2, sname2, ssex2, sage2, sprofessiona2, sdepartment2;
	// 学生信息
	private String id, name, sex, age, professiona, department;
	private int credit = 0;
	private int gettedCredit = 0;

	@SuppressWarnings("rawtypes")
	private Vector rowData, columnNames;

	// 学生选课信息
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JTable getmessageofxuanke()
	{
		columnNames = new Vector<String>();
		// 列名
		columnNames.add("课程名");
		columnNames.add("课程学分");
		columnNames.add("任课老师");
		columnNames.add("教师编号");
		columnNames.add("成绩");
		columnNames.add("课程号");

		rowData = new Vector();
		try
		{
			Statement st = ConnectionDataBase.con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from Course,SC,Teacher where SC.Cno=Course.Cno and SC.Tno = Teacher.Tno and SC.Sno ="
							+ "'" + id + "'");
			while (rs.next())
			{
				Vector hang = new Vector();
				hang.add(rs.getString("Cname"));
				hang.add(rs.getInt("Ccredit"));
				hang.add(rs.getString("Tname"));
				hang.add(rs.getString("Tno"));
				hang.add(rs.getInt("Grade"));
				hang.add(rs.getString("cno"));

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

	// 选课
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JTable xuanke()
	{
		columnNames = new Vector<String>();
		// 列名
		columnNames.add("课程名");
		columnNames.add("课程学分");
		columnNames.add("任课老师");
		columnNames.add("教师编号");
		columnNames.add("容量");
		columnNames.add("余量");
		columnNames.add("课程号");

		rowData = new Vector();
		try
		{
			Statement st = ConnectionDataBase.con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from Course,Teacher where Course.Cno not in (select Course.Cno from SC,Course where SC.Cno = Course.Cno and SC.Sno = "
							+ "'"
							+ id
							+ "')"
							+ "and Course.Tno not in(select Course.Tno from SC,Course where SC.Cno = Course.Cno and  Sno = '"
							+ id + "') and Course.Tno = Teacher.Tno");
			while (rs.next())
			{
				int stContent = rs.getInt("StContent");
				int CurrentNumber = rs.getInt("CurrentNumber");
				int a = stContent - CurrentNumber;
				if (a > 0)
				{
					Vector hang = new Vector();
					hang.add(rs.getString("Cname"));
					hang.add(rs.getInt("Ccredit"));
					hang.add(rs.getString("Tname"));
					hang.add(rs.getString("Tno"));
					hang.add(stContent);
					hang.add(a);
					hang.add(rs.getString("Cno"));

					rowData.add(hang);
				}
				else
				{

				}
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

	// 初始化“学生信息”界面
	private void inTable1()
	{
		sid = new JLabel("学号：");
		sname = new JLabel("姓名：");
		ssex = new JLabel("性别：");
		sage = new JLabel("年龄：");
		sprofessiona = new JLabel("专业：");
		sdepartment = new JLabel("所在系：");
		stitle = new JLabel("学生个人资料：");
		stitle.setSize(20, 20);

		sid2 = new JLabel(id);
		sname2 = new JLabel(name);
		ssex2 = new JLabel(sex);
		sage2 = new JLabel(age);
		sprofessiona2 = new JLabel(professiona);
		sdepartment2 = new JLabel(department);

		jbt3 = new JButton("修改密码");
		{

			class A implements ActionListener
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane jop = new JOptionPane();
					@SuppressWarnings("static-access")
					String s = jop.showInputDialog(null, "请输入密码");
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(s);
					if (isNum.matches())
					{
						@SuppressWarnings("static-access")
						String s2 = jop.showInputDialog(null, "请确认密码");
						if( !s.equals(s2))
						{
							JOptionPane.showMessageDialog(null, "两次输入的密码不一致！！！", "错误",
									JOptionPane.ERROR_MESSAGE);
						}
						else{
						try
						{
							Statement st = ConnectionDataBase.con.createStatement();
							st.executeUpdate("update Sadmin set Password = "+s+" where Susername = '"+id+"'");
							JOptionPane.showMessageDialog(null, "密码修改成功！！！",
									"恭喜", JOptionPane.DEFAULT_OPTION);
							
						}
						catch (SQLException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "密码只能由数字组成", "错误",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
			jbt3.addActionListener(new A());
		}

	}

	// 初始界面
	private void ininconments()
	{
		jframe = new JFrame("学生选课系统");

		// 左边
		jpanel_left = new JPanel(new GridLayout(5, 1));
		jpanel_left.setBorder(BorderFactory.createEtchedBorder());

		jb1 = new JLabel("个人信息", JLabel.CENTER);
		jb2 = new JLabel("选课情况", JLabel.CENTER);
		jb3 = new JLabel("选课", JLabel.CENTER);
		jb1.addMouseListener(this);
		jb2.addMouseListener(this);
		jb3.addMouseListener(this);

		jpanel_left.add(jb1);
		jpanel_left.add(jb2);
		jpanel_left.add(jb3);

		// 右边
		jpanel_right = new JPanel(cl);
		// 个人信息
		jpanel_right_1 = new JPanel();
		inTable1();
		JPanel jpanel_right_1_1 = new JPanel();
		jpanel_right_1_1.add(stitle, JLabel.CENTER);
		jpanel_right_1.add(jpanel_right_1_1, BorderLayout.NORTH);

		JPanel jpanel_right_1_2 = new JPanel();
		JPanel jpanel_right_1_3 = new JPanel();
		JPanel jpanel_right_1_4 = new JPanel();
		JPanel jpanel_right_1_5 = new JPanel();
		JPanel jpanel_right_1_6 = new JPanel();
		JPanel jpanel_right_1_7 = new JPanel();
		jpanel_right_1_2.add(sname, Label.LEFT);
		jpanel_right_1_2.add(sname2, Label.CENTER);

		jpanel_right_1_3.add(sid);
		jpanel_right_1_3.add(sid2);

		jpanel_right_1_4.add(ssex);
		jpanel_right_1_4.add(ssex2);

		jpanel_right_1_5.add(sage);
		jpanel_right_1_5.add(sage2);

		jpanel_right_1_6.add(sdepartment);
		jpanel_right_1_6.add(sdepartment2);

		jpanel_right_1_7.add(sprofessiona);
		jpanel_right_1_7.add(sprofessiona2);

		jpanel_right_1.add(jpanel_right_1_3);
		jpanel_right_1.add(jpanel_right_1_4);
		jpanel_right_1.add(jpanel_right_1_5);
		jpanel_right_1.add(jpanel_right_1_6);
		jpanel_right_1.add(jpanel_right_1_7);
		jpanel_right_1.add(jbt3, BorderLayout.SOUTH);

		jpanel_right_1_1.setBorder(BorderFactory.createEtchedBorder());
		jpanel_right_1_2.setBorder(BorderFactory.createEtchedBorder());
		jpanel_right_1_3.setBorder(BorderFactory.createEtchedBorder());
		jpanel_right_1_4.setBorder(BorderFactory.createEtchedBorder());
		jpanel_right_1_5.setBorder(BorderFactory.createEtchedBorder());
		jpanel_right_1_6.setBorder(BorderFactory.createEtchedBorder());
		jpanel_right_1_7.setBorder(BorderFactory.createEtchedBorder());

		// 学生选课信息
		jbt1 = tuiXuan();
		jtb1 = getmessageofxuanke();
		jb5 = new JLabel("您已选的学分：");
		String s = Integer.toString(credit);
		jb6 = new JLabel(s);

		jb7 = new JLabel("您已获得的学分:");
		int count = jtb1.getRowCount();
		for (int i = 0; i < count; i++)
		{
			Integer temp = (Integer) jtb1.getValueAt(i, 4);
			if (0 != temp)
			{
				this.gettedCredit = this.gettedCredit
						+ ((Integer) jtb1.getValueAt(i, 1));
			}
		}

		jb8 = new JLabel(Integer.toString(gettedCredit));

		jpanel_right_2 = new JPanel();
		jpanel_right_2.setBorder(BorderFactory.createTitledBorder("学生选课信息"));

		jsp2 = new JScrollPane(jtb1);

		JPanel jpanel_right_2_1 = new JPanel();
		JPanel jpanel_right_2_2 = new JPanel();

		jpanel_right_2_1.add(jb5, Label.LEFT);
		jpanel_right_2_1.add(jb6, Label.CENTER);

		jpanel_right_2_2.add(jb7, Label.LEFT);
		jpanel_right_2_2.add(jb8, Label.CENTER);

		jpanel_right_2.add(jsp2);
		jpanel_right_2.add(jbt1);
		jpanel_right_2.add(jpanel_right_2_1);
		jpanel_right_2.add(jpanel_right_2_2);

		// 学生选课
		jbt2 = xuanKe();
		jpanel_right_3 = new JPanel();
		jpanel_right_3.setBorder(BorderFactory.createTitledBorder("可选课程"));

		jtb2 = xuanke();
		jsp3 = new JScrollPane(jtb2);

		jpanel_right_3.add(jsp3);
		jpanel_right_3.add(jbt2);

		jpanel_right.add("1", jpanel_right_1);
		jpanel_right.add("2", jpanel_right_2);
		jpanel_right.add("3", jpanel_right_3);
		// 默认显示
		cl.show(jpanel_right, "1");

		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpanel_left,
				jpanel_right);
		jsp.setDividerLocation(140);
		jsp.setDividerSize(0);
		jframe.add(jsp);

		// 上边
		jpanel_up = new JPanel();
		jb4 = new JLabel(name + "同学，欢迎您！！！", JLabel.CENTER);
		jpanel_up.add(jb4);
		jpanel_up.setBorder(BorderFactory.createEtchedBorder());
		jframe.add(jpanel_up, BorderLayout.NORTH);

		jframe.setSize(800, 600);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	StudentEntryed(String id)
	{
		this.id = id;
		try
		{
			Statement st = ConnectionDataBase.con.createStatement();
			ResultSet rs = st.executeQuery("select * from Student where Sno = "
					+ "'" + id + "'");
			rs.next();
			this.name = rs.getString("Sname");
			this.age = rs.getString("Sage");
			this.professiona = rs.getString("Professiona");
			this.department = rs.getString("Department");
			this.sex = rs.getString("Ssex");
			rs = st.executeQuery("select Ccredit from Course,SC where Course.Cno = SC.Cno and SC.Cno in (select Cno from SC where SC.Sno = '"
					+ this.id + "')");
			while (rs.next())
			{
				this.credit = this.credit + rs.getInt("Ccredit");
			}

			rs.close();
			st.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ininconments();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getClickCount() == 1)
		{
			if (e.getSource() == jb1)
			{
				cl.show(jpanel_right, "1");
			}
			else if (e.getSource() == jb2)
			{
				cl.show(jpanel_right, "2");
			}
			else if (e.getSource() == jb3)
			{
				cl.show(jpanel_right, "3");
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

		((JLabel) e.getSource()).setForeground(Color.RED);
		((JLabel) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		((JLabel) e.getSource()).setForeground(Color.BLACK);
		((JLabel) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	// 为“退选”按钮添加监听器
	public JButton tuiXuan()
	{
		JButton jb = new JButton("退选");
		// 监听器
		{
			class A implements ActionListener
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					int index = jtb1.getSelectedRow();
					if (index == -1)
					{
						JOptionPane.showMessageDialog(null, "请选择您要退选的课程", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
					else if (!((Integer) jtb1.getValueAt(index, 4)).equals(0))
					{
						JOptionPane.showMessageDialog(null,
								"当前课程已经给予成绩，所以不能退选！！！", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try
						{
							// 往数据库里删除数据
							String Cno = (String) jtb1.getValueAt(index, 5);
							String Tno = (String) jtb1.getValueAt(index, 3);
							Statement st = ConnectionDataBase.con
									.createStatement();
							st.executeUpdate("delete from SC where SC.Sno = '"
									+ id + "' and SC.Cno" + "='" + Cno
									+ "' and SC.Tno = '" + Tno + "'");
							JOptionPane.showMessageDialog(null, "退选成功！！！",
									"恭喜", JOptionPane.DEFAULT_OPTION);
							st.close();
							Integer in = (Integer)jtb1.getValueAt(index,1);
							credit = credit - in;
							// 修改面板
							jframe.setVisible(false);
							StudentEntryed s = new StudentEntryed(id);
						}
						catch (SQLException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			}
			jb.addActionListener(new A());
		}
		return jb;
	}

	// 为“选课”按钮添加监听器
	public JButton xuanKe()
	{
		JButton jb = new JButton("选课");
		// 监听器
		{
			class A implements ActionListener
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					int index = jtb2.getSelectedRow();
					if (index == -1)
					{
						JOptionPane.showMessageDialog(null, "请选择您要选择的课程", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try
						{
							int in = (Integer) jtb2.getValueAt(index, 1);
							if ((in + credit) > CRDIT)
							{
								JOptionPane.showMessageDialog(null,
										"您的学分已经地超过学校所规定的学分！！！", "警告",
										JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								// 往数据库里添加数据
								String Cno = (String) jtb2.getValueAt(index, 6);
								String Tno = (String) jtb2.getValueAt(index, 3);
								Statement st = ConnectionDataBase.con
										.createStatement();
								st.executeUpdate("insert into SC(Sno,Cno,Tno) values ('"
										+ id
										+ "',"
										+ "'"
										+ Cno
										+ "',"
										+ "'"
										+ Tno + "')");
								JOptionPane.showMessageDialog(null, "选课成功！！！",
										"恭喜", JOptionPane.DEFAULT_OPTION);
								st.close();
								credit = credit + in;
								// 修改面板
								jframe.setVisible(false);
								StudentEntryed s = new StudentEntryed(id);
							}
						}
						catch (SQLException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			}
			jb.addActionListener(new A());
		}
		return jb;
	}

	public static void main(String[] args)
	{
		ConnectionDataBase.connect(
				"jdbc:sqlserver://localhost:1434;DatabaseName=table2", "sa",
				"123");
		StudentEntryed s = new StudentEntryed("110004");

	}
}
