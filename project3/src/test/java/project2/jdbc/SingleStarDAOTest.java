package project2.jdbc;

import java.sql.SQLException;

import project3.jdbc.JDBCPool;
import project3.jdbc.dao.SingleStarDAO;

public class SingleStarDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(SingleStarDAO.starcontent(671105).toString());
		pool.closePool();
	}
}
