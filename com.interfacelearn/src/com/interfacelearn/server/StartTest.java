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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StartTest extends RemoteServiceServlet {
	
	public static String[] round2q = {"a","b","c","d","e","f","g"};
	public static String[] round2a = {"a","b","c","d","e","f","g"};
	public static String[] round2b = {"a","b","c","d","e","f","g"};
	public static String[] round2c = {"a","b","c","d","e","f","g"};
	public static String[] round2d = {"a","b","c","d","e","f","g"};
	public static String[] round2ans = {"a","b","c","d","e","f","g"};
	
	public static String[] round3q = {"a","b","c","d","e","f","g"};
	public static String[] round3a = {"a","b","c","d","e","f","g"};
	public static String[] round3b = {"a","b","c","d","e","f","g"};
	public static String[] round3c = {"a","b","c","d","e","f","g"};
	public static String[] round3d = {"a","b","c","d","e","f","g"};
	public static String[] round3ans = {"a","b","c","d","e","f","g"};
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//get selection, round, next question, source, id
		String sel = req.getParameter("selection");
		String source = req.getParameter("source");
		String id = req.getParameter("id");
		Integer round = Integer.parseInt(req.getParameter("round"));
		Integer nq = Integer.parseInt(req.getParameter("nextq"));
		String left = "";
		String right = "";
		int currentqscore = 0;
		left+="<input type=\"hidden\" name=\"selection\" value=\""+sel+"\">";
		left+="<input type=\"hidden\" name=\"round\" value=\""+round+"\">";
		left+="<input type=\"hidden\" name=\"nq\" value=\""+nq+1+"\">";
		left+="<input type=\"hidden\" name=\"id\" value=\""+id+"\">";
		DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
		Key k = KeyFactory.createKey("id",id );
		Entity entity;
		try {
			entity = datastore.get(k);
			entity.setProperty("selection", sel);
			datastore.put(entity);
			
			if (round == 2)
			{
				//compute score for question, check nextq number
				if(nq == 1)
				{}
				
				if(nq>7)
				{
					//add to total2
					//compute total1,2 and store
					//redirect to page before round 3
				}
				else
				{
					left=round2q[nq-1];
					left+="<br/><input type=\"radio\" name=\"question\" value=\"a\"> a. " + round2a[nq-1]+"<br/>";
					left+="<input type=\"radio\" name=\"question\" value=\"b\"> b. " + round2b[nq-1]+"<br/>";
					left+="<input type=\"radio\" name=\"question\" value=\"c\"> c. " + round2c[nq-1]+"<br/>";
					left+="<input type=\"radio\" name=\"question\" value=\"d\"> d. " + round2d[nq-1]+"<br/>";
					//add selection, round, next question, source, id, timestamp
					left+="<input type=\"hidden\" name=\"source\" value=\"test2\">";
					 //check user selction and generate right side
					if("leaderboard".equals(sel))
					{
						//leaderboard
						Query query = new Query("id");
			        	query.addSort("total1", SortDirection.DESCENDING);
			        	 List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));
			        
			        	 right ="<table border=2 cellpadding=2px><tr><th>Score</th><th>Name</th></tr>";
			        	 if (!greetings.isEmpty()) {
			        	        for (Entity greeting : greetings) {
			        	        	right+="<tr><td>"+greeting.getProperty("name")+"</td><td>"+greeting.getProperty("total1")+"</td></tr>";
			        	        }
			        	    }
					}
					else
					{
						//projected
						right = "";
					}
					//add timestamp to left 
					//add left and right to req
					left+="<input type=\"hidden\" name=\"time\" value=\""+(new Date().getTime())+"\">";
					req.setAttribute("left", left);
					req.setAttribute("right", right); 
					//redirect to test2.jsp
					try {
						req.getRequestDispatcher("test2.jsp").forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if(round ==3)
			{
				//compute score for question, check nextq number
				if(nq>7)
				{
					//add to total3
					//compute total1,2,3 and store
					//redirect to last page
				}
				else
				{
					left=round2q[nq-1];
					//add selection, round, next question, source, id
					//redirect to test2.jsp
				}
			}
			
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			//TODO redirect to last page because of error
		}	

	

//        Entity entity;
		
//		        if("leaderboard".equals(sel))
//		        {
//		        	//fetch top 10
		        	
//		        	 message+="</table>";
//		        	 req.setAttribute("table", message);
//		        	 req.setAttribute("count", count-1);
//		        	 req.setAttribute("id", id); 
//		     		req.setAttribute("selection", sel);
//		        }
		
//		
//        try {
//			req.getRequestDispatcher("test2.jsp").forward(req, resp);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
       
	}
}
