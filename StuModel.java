package com.Manager.stumanage;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//һ��student���ģ��
public class StuModel extends AbstractTableModel
{
	// columnNames���������rowData���������
	Vector rowData, columnNames;

	// ���ݿ���Ҫ�Ķ���
	public boolean updStu(String sql, String[] paras)
	{
		// ����SqlHelper
		SqlHelper SqlHelper = new SqlHelper();
		return SqlHelper.updExecute(sql, paras);
	}

	// ��ѯ
	public void queryStu(String sql, String[] paras)
	{
		SqlHelper SqlHelper = null;
		// �м�
		columnNames = new Vector();
		// ��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("רҵ");
		columnNames.add("ϵ��");
		rowData = new Vector();
		try
		{
			SqlHelper = new SqlHelper();
			ResultSet rs = SqlHelper.queryExectue(sql, paras);
			while (rs.next())
			{
				// rowdata ���Դ�Ŷ���
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));

				// ���뵽rowData
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
	// �õ�ĳ�����е�����
	public Object getValueAt(int row, int column)
	{
			// TODO Auto-generated method stub
			return ((Vector) this.rowData.get(row)).get(column);
	}

}
