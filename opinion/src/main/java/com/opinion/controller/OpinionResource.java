package com.opinion.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.opinion.entity.*;

@Path("/poll")
public class OpinionResource {

	OpinionService os = new OpinionService();

	@GET
	public String getAllAuthers() throws SQLException {
		List<Author> list = os.getAllAuthors();
		Gson gson = new Gson();
		String gson_list = gson.toJson(list);

		return gson_list;

	}

	@GET
	@Path("/select")
	public Response addPoll(@QueryParam("choice") String author_Id) throws SQLException, URISyntaxException {
		Opinion_Poll op = new Opinion_Poll();
		op.setAuthor_Id(author_Id);
		op.setPoll(1);
		os.addPoll(op);
		URI uri = new URI("../poll.html");
		return Response.temporaryRedirect(uri).build();

	}
}
