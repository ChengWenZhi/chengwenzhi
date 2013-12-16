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
	// �����Ա����
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

	// �߼���Ա����
	// Ĭ�����ӷ�ʽ
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

	// ��ʼ������
	private void ininconments()
	{

		jframe = new JFrame("ѧ������ϵͳ");
		jpanel1 = new JPanel();
		jpanel2 = new JPanel();
		jpanel3 = new JPanel();
		jtextfield = new JTextField(10);
		jpasswordfield = new JPasswordField(10);
		jradiobutton1 = new JRadioButton("ѧ��");
		jradiobutton2 = new JRadioButton("��ʦ");
		jradiobutton3 = new JRadioButton("����Ա");
		buttongroup = new ButtonGroup();
		jlabel1 = new JLabel("��¼��");
		jlabel2 = new JLabel("����");
		jbutton1 = new JButton("��¼");
		// Ϊ����¼��ť�����ӹ���
		jbutton1.addActionListener(this);

		jbutton2 = new JButton("����");
		// Ϊ�����á���ť��Ӽ�����
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
		// ʵ�ֵ�ѡ����,Ĭ�������ѡ��ѧ����
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

	// ����¼����ť���¼�������
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e)
	{

		String user = jtextfield.getText();
		String passworld = jpasswordfield.getText();
		// �ж��û��Ƿ������ˡ��û������͡����롱
		if (user.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "û���û���,��������ȷ���û���������", "����",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (passworld.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "û������,��������ȷ�����룡����", "����",
					JOptionPane.ERROR_MESSAGE);
		}
		else
		{

			// �жϵ�¼���������Ƿ���ȷ
			// ѧ����¼
			if (jradiobutton1.isSelected())
			{

				try
				{
					ConnectionDataBase.connect(url, "sa", "123");
					st = ConnectionDataBase.con.createStatement();

					rs = st.executeQuery("select password from Sadmin where Susername = "
							+ "'" + user + "'");

					// �û�����֤
					// �û�����ȷ
					if (rs.next())
					{
						this.user = user;
						String s = rs.getString("password");

						// ������֤
						// ������ȷ
						if (s.trim().equals(passworld))
						{
							rs.close();
							st.close();

							jframe.setVisible(false);
							@SuppressWarnings("unused")
							StudentEntryed student = new StudentEntryed(user);
						}
						// �������
						else
						{

							jpasswordfield.setText("");
							JOptionPane.showMessageDialog(null, "������󣡣���",
									"����", JOptionPane.ERROR_MESSAGE);
							rs.close(); 
							st.close();
							ConnectionDataBase.con.close();
						}
					}
					// �û����������
					else
					{
						jpasswordfield.setText("");
						JOptionPane.showMessageDialog(null, "�޸��û�����˶���Ϣ������",
								"����", JOptionPane.ERROR_MESSAGE);
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
			// ��ʦ��¼
			else if (jradiobutton2.isSelected())
			{
				
				

					try
					{
						ConnectionDataBase.connect(url, "sa", "123");
						st = ConnectionDataBase.con.createStatement();

						rs = st.executeQuery("select password from Tadmin where Tusername = "
								+ "'" + user + "'");

						// �û�����֤
						// �û�����ȷ
						if (rs.next())
						{
							this.user = user;
							String s = rs.getString("password");

							// ������֤
							// ������ȷ
							if (s.trim().equals(passworld))
							{
								rs.close();
								st.close();

								jframe.setVisible(false);
							}
							// �������
							else
							{

								jpasswordfield.setText("");
								JOptionPane.showMessageDialog(null, "������󣡣���",
										"����", JOptionPane.ERROR_MESSAGE);
								rs.close(); 
								st.close();
								ConnectionDataBase.con.close();
							}
						}
						// �û����������
						else
						{
							jpasswordfield.setText("");
							JOptionPane.showMessageDialog(null, "�޸��û�����˶���Ϣ������",
									"����", JOptionPane.ERROR_MESSAGE);
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
			//����Ա��¼
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
