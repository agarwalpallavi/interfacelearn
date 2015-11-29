package com.interfacelearn.server;

import java.io.IOException;
import java.util.Date;
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
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SelectionImpl extends RemoteServiceServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long d1 = new Date().getTime();
		Long d2 = Long.parseLong(req.getParameter("time"));
		
		String answers[] = {"c","b","b","d","c","c","a","b","c","a"};
		int score = 0;
		for(Integer i=1; i<11;i++) 
		{
			if(answers[i-1].equals(req.getParameter("q"+i.toString())))
			{
				score++;
			}
		}
		
		int total= score*50 ;
		int difference = (int) ((d1-d2)/1000);
		
		if(difference < 300)
			total += 300 - difference;
		
		DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
		String id = req.getParameter("id");
		Key k = KeyFactory.createKey("id",id );
        Entity entity;
		try {
			entity = datastore.get(k);
			 entity.setProperty("score1", score);
			 entity.setProperty("total1", total);
			 datastore.put(entity);
			 Query query = new Query("id").addFilter("selection", Query.FilterOperator.EQUAL, "leaderboard");
	        	query.addSort("total1", SortDirection.DESCENDING);
	        	 List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));
	        	System.out.println(total);
	        	 if (!greetings.isEmpty()) {
	        	   
	        	        for (Entity greeting : greetings) {
	        	        	System.out.println(greeting.toString());
	        	        	Long i = (Long) greeting.getProperty("total1");
	        	        	
	        	        	if(i<1)
	        	        		i=(long) 1;
	        	        	total = (int) ((total *100)/i);
	        	        	if(total>100)
	        	        		total = 100;
	        	        }
	        	    }
	        	 else
	        		 total = 100;
	        	 System.out.println(total);
	        	 
	        	 
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("id", id); 
		req.setAttribute("total", total); 
		req.setAttribute("score", score); 
	
        try {
			req.getRequestDispatcher("selectionscreen.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
	}
}
