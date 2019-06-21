package com.opinion.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opinion.connect.OpinionDBConnection;
import com.opinion.entity.Author;
import com.opinion.entity.Opinion_Poll;

public class OpinionDao implements OpinionDaoInterface<Author, Opinion_Poll> {

	private final static Logger logger = Logger.getLogger(OpinionDao.class.getName());

	OpinionDBConnection opinionDBConnection = new OpinionDBConnection();

	public List<Author> getAllAuthor() throws SQLException {

		List<Author> list = new ArrayList<Author>();
		Connection con = null;
		try {
			con = opinionDBConnection.getConnection();
		} catch (ClassNotFoundException | IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		logger.log(Level.INFO, "----Query Started----");
		String query = "SELECT * FROM author";
		PreparedStatement st = con.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			logger.log(Level.INFO, "----Result set executed properly----");
			String id = rs.getString("author_Id");
			String name = rs.getString("author_Name");
			int vote = rs.getInt("author_votes");

			Author author = new Author();
			author.setAuthor_Id(id);
			author.setAuthor_Name(name);
			author.setAuthor_votes(vote);
			list.add(author);

			logger.log(Level.INFO, "----GetallAuthor method executed properly----");

		}
		rs.close();
		con.close();
		logger.log(Level.INFO, "----Resultset close + connection close----");
		return list;

	}

	@Override
	public boolean addPoll(Opinion_Poll op) throws SQLException {

		Connection con = null;
		try {
			con = opinionDBConnection.getConnection();
		} catch (ClassNotFoundException | IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		logger.log(Level.INFO, "---- Insert Query Started----");
		String query = "INSERT INTO OPINION_POLL(AUTHOR_ID,POLL) VALUES (?,?)";
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, op.getAuthor_Id());
		st.setInt(2, op.getPoll());
		int x = st.executeUpdate();

		logger.log(Level.INFO, "----Update Query Started----");
		String updateQuery = "UPDATE  AUTHOR SET AUTHOR_VOTES = (SELECT COUNT(POLL) FROM OPINION_POLL WHERE OPINION_POLL.AUTHOR_ID=?) WHERE AUTHOR.AUTHOR_ID =?";
		PreparedStatement st_update = con.prepareStatement(updateQuery);
		st_update.setString(1, op.getAuthor_Id());
		st_update.setString(2, op.getAuthor_Id());
		int y = st_update.executeUpdate();

		st.close();
		st_update.close();
		con.close();
		logger.log(Level.INFO, "---PreparedStatement close + connection close----");
		if (x == 1 && y == 1) {
			logger.log(Level.INFO, "----Add poll method executed properly----");
			return true;
		} else {
			logger.log(Level.SEVERE, "----INSERT + UPDATE query not executed properly----");
			return false;
		}
	}

}
