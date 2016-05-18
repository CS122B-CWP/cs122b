package project3.jdbc;

import java.sql.SQLException;

import project4.jdbc.JDBCPool;
import project4.jdbc.dao.SingleStarDAO;

public class SingleStarDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(SingleStarDAO.starcontent(671105).toString());
		pool.closePool();

		String str = "k";
		String strs[] = str.split(",");

		System.out.println(strs.length);
		for (int i = 0; i < strs.length; i++)
			System.out.println(strs[i]);
	}
}
