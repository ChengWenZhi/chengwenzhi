package com.Manager.stumanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlHelper
{
	// ����������ݿ���Ҫ�Ķ���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String url = "jdbc:microsoft:sqlserver://127.0.0.1;1433;databaseName=table2";
	String user = "sa";
	String passwd = "123";
	String dirver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private String sql;

	// �ر����ݿ���Դ
	public void close()
	{
		// �ر�
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
			// 1��������
			Class.forName(dirver);
			// 2�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			// 3����ps
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// �ر���Դ
		}
		return rs;
	}

	// ��ѯ���ݿ����
	public ResultSet queryExectue(String sql, String[] paras)
	{
		try
		{
			// 1��������
			Class.forName(dirver);
			// 2�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			// 3����ps
			ps = ct.prepareStatement(sql);
			// ��ps���ʺŸ�ֵ
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
			// �ر���Դ
		}
		return rs;
	}

	// ����ɾ����һ��
	public boolean updExecute(String sql, String[] paras)
	{
		boolean b = true;
		try
		{
			// 1��������
			Class.forName(dirver);
			// 2�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			// 3����ps
			ps = ct.prepareStatement(sql);
			// ��ps���ʺŸ�ֵ
			for (int i = 0; i < paras.length; i++)
			{
				ps.setString(i + 1, paras[i]);
			}
			// 4ִ�в���
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
