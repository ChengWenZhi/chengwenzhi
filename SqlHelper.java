package com.Manager.stumanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlHelper
{
	// 定义操作数据库需要的东西
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String url = "jdbc:microsoft:sqlserver://127.0.0.1;1433;databaseName=table2";
	String user = "sa";
	String passwd = "123";
	String dirver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private String sql;

	// 关闭数据库资源
	public void close()
	{
		// 关闭
		try
		{
			if (rs != null)
				rs.close();
			if (ps != null)
				rs.close();
			if (ct != null)
				rs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ResultSet queryExecute(String sql)
	{
		try
		{
			// 1加载驱动
			Class.forName(dirver);
			// 2得到连接
			ct = DriverManager.getConnection(url, user, passwd);
			// 3创建ps
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
		}
		return rs;
	}

	// 查询数据库操作
	public ResultSet queryExectue(String sql, String[] paras)
	{
		try
		{
			// 1加载驱动
			Class.forName(dirver);
			// 2得到连接
			ct = DriverManager.getConnection(url, user, passwd);
			// 3创建ps
			ps = ct.prepareStatement(sql);
			// 给ps的问号赋值
			for (int i = 0; i < paras.length; i++)
			{
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
		}
		return rs;
	}

	// 把增删合在一起
	public boolean updExecute(String sql, String[] paras)
	{
		boolean b = true;
		try
		{
			// 1加载驱动
			Class.forName(dirver);
			// 2得到连接
			ct = DriverManager.getConnection(url, user, passwd);
			// 3创建ps
			ps = ct.prepareStatement(sql);
			// 给ps的问号赋值
			for (int i = 0; i < paras.length; i++)
			{
				ps.setString(i + 1, paras[i]);
			}
			// 4执行操作
			if (ps.executeUpdate() != 1)
			{
				b = false;
			}
		}
		catch (Exception e)
		{
			b = false;
			e.printStackTrace();
		}
		finally
		{
			this.close();
		}
		return b;
	}
}
