package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase
{
	public static String url;
	public static String User;
	public static String Password;

	public static Connection con;
	// Çý¶¯Ãû
	public static String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static void connect(String url, String User, String Password)
	{
		ConnectionDataBase.url = url;
		ConnectionDataBase.User = User;
		ConnectionDataBase.Password = Password;

		try
		{

			Class.forName(Driver);
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			con = DriverManager.getConnection(url, User, Password);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void close()
	{
		try
		{
			ConnectionDataBase.con.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
