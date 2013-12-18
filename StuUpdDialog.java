package com.Manager.stumanage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StuUpdDialog extends JDialog implements ActionListener
{
	// ��������Ҫ��swing���
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;

	// owne ���ĸ�����
	// rtitle������
	// modelָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	public StuUpdDialog(JFrame owner, String title, boolean model, StuModel sm,
			int rowNums)
	{
		super(owner, title, model);
		jl1 = new JLabel("ѧ��");
		jl2 = new JLabel("����");
		jl3 = new JLabel("�Ա�");
		jl4 = new JLabel("����");
		jl5 = new JLabel("רҵ");
		jl6 = new JLabel("ϵ��");
		jtf1 = new JTextField();
		// ��ʼ������
		jtf1.setText((String) sm.getValueAt(rowNums, 0));
		// ��jtf1�����޸�
		jtf1.setEditable(false);
		jtf2 = new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNums, 1));
		jtf3 = new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNums, 2));
		jtf4 = new JTextField();
		jtf4.setText(sm.getValueAt(rowNums, 3).toString());
		jtf5 = new JTextField();
		jtf5.setText((String) sm.getValueAt(rowNums, 4));
		jtf6 = new JTextField();
		jtf6.setText((String) sm.getValueAt(rowNums, 5));
		jb1 = new JButton("�޸�");
		// ע�����
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		// ���ò���
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));
		// ������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);

		jp3.add(jb1);
		jp3.add(jb2);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		// չ��
		this.setSize(300, 250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
	if(e.getSource()==jb1)
	{
	//��һ��sql
    //�����������
	String str ="update Student set sname=?,ssex=?,sage=?," +
	"jiguan=?,sdept=? where sno=?";
	
	String []paras={jtf2.getText(),jtf3.getText(),jtf4.getText(),
	jtf5.getText(),jtf6.getText(),jtf1.getText()};
	
	StuModel temp=new StuModel();
	
	temp.updStu(str,paras);
	this.dispose();}
	}
}
