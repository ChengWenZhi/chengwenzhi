package com.Manager.stumanage;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//一个student表的模型
public class StuModel extends AbstractTableModel
{
	// columnNames存放列名；rowData存放行数据
	Vector rowData, columnNames;

	// 数据库需要的定义
	public boolean updStu(String sql, String[] paras)
	{
		// 创建SqlHelper
		SqlHelper SqlHelper = new SqlHelper();
		return SqlHelper.updExecute(sql, paras);
	}

	// 查询
	public void queryStu(String sql, String[] paras)
	{
		SqlHelper SqlHelper = null;
		// 中间
		columnNames = new Vector();
		// 设置列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("专业");
		columnNames.add("系别");
		rowData = new Vector();
		try
		{
			SqlHelper = new SqlHelper();
			ResultSet rs = SqlHelper.queryExectue(sql, paras);
			while (rs.next())
			{
				// rowdata 可以存放多行
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));

				// 加入到rowData
				rowData.add(hang);
			}

		}
		catch (Exception e)
		{

			e.printStackTrace();

		}
	}

	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}
	
	@Override
	public String getColumnName(int arg0)
	{
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(arg0);
	}
	// 得到某行名列的数据
	public Object getValueAt(int row, int column)
	{
			// TODO Auto-generated method stub
			return ((Vector) this.rowData.get(row)).get(column);
	}

}
