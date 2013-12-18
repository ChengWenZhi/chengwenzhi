package com.Manager.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Manager.stumanage.StuManage;
import com.Manager.teamanage.TeaManage;

public class ChoiceManage extends JFrame implements ActionListener
{
	private JButton jb1;
	
	private JButton jb2;

	private JButton jb3;
	
	private JPanel jp1;
	
	public ChoiceManage()
	{
		jb1 = new JButton("学生");
		jb1.addActionListener(this);
		jb2 = new JButton("教师");
		jb2.addActionListener(this);
		jb3 = new JButton("课程");
		
		jp1 = new JPanel();
		
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		
		this.add(jp1);
	    this.setSize(300,100);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible(true);
	
	}
	public static void main(String[] args)
	{
		new ChoiceManage();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == jb1)
		{
			new StuManage();
		}
		if(arg0.getSource() == jb2)
		{
			new TeaManage();
		}
		if(arg0.getSource() == jb3)
		{
			new StuManage();
		}
		
	}
	
	
	
	
}
