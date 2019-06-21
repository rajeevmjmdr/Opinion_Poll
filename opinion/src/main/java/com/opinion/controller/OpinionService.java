package com.opinion.controller;

import java.sql.SQLException;
import java.util.List;

import com.opinion.dao.OpinionDao;
import com.opinion.entity.Author;
import com.opinion.entity.Opinion_Poll;

public class OpinionService {
	OpinionDao opiniondao = new OpinionDao();

	public List<Author> getAllAuthors() throws SQLException {

		List<Author> authorlist = opiniondao.getAllAuthor();
		return authorlist;

	}

	public boolean addPoll(Opinion_Poll op) throws SQLException {

		return opiniondao.addPoll(op);

	}

}
