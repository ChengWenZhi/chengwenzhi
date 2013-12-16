package database;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Entry implements ActionListener
{
	// 界面成员变量
	private JFrame jframe;
	private JPanel jpanel1;
	private JPanel jpanel2;
	private JPanel jpanel3;
	private JTextField jtextfield;
	private JPasswordField jpasswordfield;
	private JRadioButton jradiobutton1;
	private JRadioButton jradiobutton2;
	private JRadioButton jradiobutton3;
	private ButtonGroup buttongroup;
	private JLabel jlabel1;
	private JLabel jlabel2;
	private JButton jbutton1;
	private JButton jbutton2;

	// 逻辑成员变量
	// 默认链接方式
	private String url = "jdbc:sqlserver://localhost:1434;DatabaseName=table2";
	private String user;

	private Statement st = null;
	private ResultSet rs = null;

	public String getUser()
	{
		return user;
	}

	public Statement getSt()
	{
		return st;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	// 初始化界面
	private void ininconments()
	{

		jframe = new JFrame("学生管理系统");
		jpanel1 = new JPanel();
		jpanel2 = new JPanel();
		jpanel3 = new JPanel();
		jtextfield = new JTextField(10);
		jpasswordfield = new JPasswordField(10);
		jradiobutton1 = new JRadioButton("学生");
		jradiobutton2 = new JRadioButton("教师");
		jradiobutton3 = new JRadioButton("管理员");
		buttongroup = new ButtonGroup();
		jlabel1 = new JLabel("登录名");
		jlabel2 = new JLabel("密码");
		jbutton1 = new JButton("登录");
		// 为“登录按钮”增加功能
		jbutton1.addActionListener(this);

		jbutton2 = new JButton("重置");
		// 为“重置”按钮添加监听器
		{
			class A implements ActionListener
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					jtextfield.setText("");
					jpasswordfield.setText("");
				}

			}

			jbutton2.addActionListener(new A());
		}

		jframe.setLayout(new BorderLayout());

		jpanel3.add(jbutton1);
		jpanel3.add(jbutton2);
		jframe.add(jpanel3, BorderLayout.SOUTH);

		jpanel1.add(jlabel1);
		jpanel1.add(jtextfield);
		jpanel1.add(jlabel2);
		jpanel1.add(jpasswordfield);

		jframe.add(jpanel1, BorderLayout.NORTH);

		jpanel2.add(jradiobutton1);
		jpanel2.add(jradiobutton2);
		jpanel2.add(jradiobutton3);
		// 实现单选功能,默认情况下选择“学生”
		buttongroup.add(jradiobutton1);
		buttongroup.add(jradiobutton2);
		buttongroup.add(jradiobutton3);
		jradiobutton1.setSelected(true);

		jframe.add(jpanel2, BorderLayout.CENTER);

		jframe.setVisible(true);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);

	}

	// “登录”按钮的事件监听器
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e)
	{

		String user = jtextfield.getText();
		String passworld = jpasswordfield.getText();
		// 判断用户是否输入了“用户名”和“密码”
		if (user.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "没有用户名,请输入正确的用户名！！！", "警告",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (passworld.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "没有密码,请输入正确的密码！！！", "警告",
					JOptionPane.ERROR_MESSAGE);
		}
		else
		{

			// 判断登录名和密码是否正确
			// 学生登录
			if (jradiobutton1.isSelected())
			{

				try
				{
					ConnectionDataBase.connect(url, "sa", "123");
					st = ConnectionDataBase.con.createStatement();

					rs = st.executeQuery("select password from Sadmin where Susername = "
							+ "'" + user + "'");

					// 用户名验证
					// 用户名正确
					if (rs.next())
					{
						this.user = user;
						String s = rs.getString("password");

						// 密码验证
						// 密码正确
						if (s.trim().equals(passworld))
						{
							rs.close();
							st.close();

							jframe.setVisible(false);
							@SuppressWarnings("unused")
							StudentEntryed student = new StudentEntryed(user);
						}
						// 密码错误
						else
						{

							jpasswordfield.setText("");
							JOptionPane.showMessageDialog(null, "密码错误！！！",
									"警告", JOptionPane.ERROR_MESSAGE);
							rs.close(); 
							st.close();
							ConnectionDataBase.con.close();
						}
					}
					// 用户名输入错误
					else
					{
						jpasswordfield.setText("");
						JOptionPane.showMessageDialog(null, "无该用户，请核对信息！！！",
								"警告", JOptionPane.ERROR_MESSAGE);
						rs.close();
						st.close();
						ConnectionDataBase.con.close();
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// 老师登录
			else if (jradiobutton2.isSelected())
			{
				
				

					try
					{
						ConnectionDataBase.connect(url, "sa", "123");
						st = ConnectionDataBase.con.createStatement();

						rs = st.executeQuery("select password from Tadmin where Tusername = "
								+ "'" + user + "'");

						// 用户名验证
						// 用户名正确
						if (rs.next())
						{
							this.user = user;
							String s = rs.getString("password");

							// 密码验证
							// 密码正确
							if (s.trim().equals(passworld))
							{
								rs.close();
								st.close();

								jframe.setVisible(false);
							}
							// 密码错误
							else
							{

								jpasswordfield.setText("");
								JOptionPane.showMessageDialog(null, "密码错误！！！",
										"警告", JOptionPane.ERROR_MESSAGE);
								rs.close(); 
								st.close();
								ConnectionDataBase.con.close();
							}
						}
						// 用户名输入错误
						else
						{
							jpasswordfield.setText("");
							JOptionPane.showMessageDialog(null, "无该用户，请核对信息！！！",
									"警告", JOptionPane.ERROR_MESSAGE);
							rs.close();
							st.close();
							ConnectionDataBase.con.close();
						}}
					
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			//管理员登录
			else if(jradiobutton2.isSelected())
			{
				
			}
		}
	}

	Entry()
	{
		ininconments();
	}

	public static void main(String[] args)
	{
		Entry e = new Entry();
	}

}
