package com.opinion.dao;

import java.sql.SQLException;
import java.util.List;

public interface OpinionDaoInterface<Author, Opinion_Poll> {

	List<Author> getAllAuthor() throws SQLException;

	boolean addPoll(Opinion_Poll op) throws SQLException;

}
