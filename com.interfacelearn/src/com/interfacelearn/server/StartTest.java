package com.interfacelearn.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StartTest extends RemoteServiceServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String answer = "a";
		DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
		String id = req.getParameter("id");
		Key k = KeyFactory.createKey("id",id );
		String sel = req.getParameter("selection");
        Entity entity;
		try {
			entity = datastore.get(k);
			 entity.setProperty("selection", sel);
			 
		        datastore.put(entity);
		        if("leaderboard".equals(sel))
		        {
		        	//fetch top 10
		        	Query query = new Query("id");
		        	query.addSort("age", SortDirection.DESCENDING);
		        	 List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(50));
		        	 
		        	 System.out.println(greetings);
		        	 int count = 1;
		        	 if (!greetings.isEmpty()) {
		        	   
		        	        for (Entity greeting : greetings) {
		        	        	if(count >10)
		        	        	{
		        	        		break;
		        	        	}
		        	        	req.setAttribute("name"+count, greeting.getProperty("name"));
		        	        	req.setAttribute("score"+count, greeting.getProperty("age"));
		        	        	count++;
		        	        }
		        	    }
		        	 req.setAttribute("count", count-1);
		        }
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("id", id); 
		req.setAttribute("selection", sel);
        try {
			req.getRequestDispatcher("test2.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
	}
}
