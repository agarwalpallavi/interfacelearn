package com.interfacelearn.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	
	public static String[] round2q = {"1. The memory address of the first element of an array is called :","2. When determining the efficiency of algorithm, the space factor is measured by :","3. Which of the following sorting algorithm is of divide-and-conquer type :","4. The time complexity of searching an element in binary search tree is :","5. Which data structure allows deleting data elements from front and inserting at rear in constant time :","6. The operation of processing each element in the list is known as :","7. When new data are to be inserted into a data structure, but there is no available space; this situation is usually called :"};
	public static String[] round2a = {"Floor address","Counting the maximum memory needed by the algorithm","Bubble sort","O(n)","Stacks","Sorting","Underflow"};
	public static String[] round2b = {"Foundation address","Counting the minimum memory needed by the algorithm","Insertion sort","O(logn)","Queues","Merging","Overflow"};
	public static String[] round2c = {"First address","Counting the average memory needed by the algorithm","Quick sort","O(n*n)","Binary Search Trees","Inserting","Housefull"};
	public static String[] round2d = {"Base address","Counting the maximum disk space needed by the algorithm","All of the above","O(n*logn)","None of the above","Traversing","Saturated"};
	public static String[] round2ans = {"d","a","c","b","b","d","b"};
	public static String[] round3q = {" 1. Two main measures for the efficiency of an algorithm are :","2. The complexity of linear search algorithm is :","3. Which of the following data structures are indexed structures?","4. The average case complexity of Quicksort algorithm is :","5. The in order traversal of tree will yield a sorted listing of elements of tree in :","6. Which data structure allows deleting data elements from front and inserting at front in constant time :","7. Finding the location of the element with a given value is :"};
	public static String[] round3a = {"Processor and memory","O(n)","Arrays","O(n)","Any sort of binary trees","Stacks","Traversal"};
	public static String[] round3b = {"Complexity and capacity","O(logn)","Linked Lists","O(logn)","Only binary search trees","Queues","Search"};
	public static String[] round3c = {"Time and space","O(n*n)","Both A and B","O(n*n)","Min heaps","Binary Search Trees","Sort"};
	public static String[] round3d = {"Data and space","O(n*logn)","Neither A nor B","O(n*logn)","None of the above","Red Black Trees","None of the above"};
	public static String[] round3ans = {"c","a","a","d","b","a","b"};
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//get selection, round, next question, source, id
		long now = new Date().getTime();
		String sel = req.getParameter("selection");
		String source = req.getParameter("source");
		String id = req.getParameter("id");
		Integer round = Integer.parseInt(req.getParameter("round"));
		Integer nq = Integer.parseInt(req.getParameter("nq"));
		String left = "";
		String right = "";
		int currentqscore = 0;
		int next = nq+1;
		left+="<input type=\"hidden\" name=\"selection\" value=\""+sel+"\">";
		if(nq<8)
		left+="<input type=\"hidden\" name=\"round\" value=\""+round+"\">";
		left+="<input type=\"hidden\" name=\"nq\" value=\""+next+"\">";
		left+="<input type=\"hidden\" name=\"id\" value=\""+id+"\">";
		DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
		Key k = KeyFactory.createKey("id",id );
		Entity entity;
		int totalToSave=0;
		try {
			entity = datastore.get(k);
			entity.setProperty("selection", sel);
			datastore.put(entity);
			
			if (round == 2)
			{
				//compute score for question, check nextq number, add to total2
				if(nq >1)
				{
					int points = 0;
					int score2 = 0;
					long time2 = Long.parseLong(req.getParameter("time"));
					int difference = (int) ((now - time2)/1000);
					
					if(difference < 30)
						points += 30 - difference;
					if(round2ans[nq-2].equals(req.getParameter("question")))
					{
						points+=50;
						score2=1;
					}
					Long i =(long)entity.getProperty("total2");
					int t2 = i.intValue()+points;
					entity.setProperty("total2", t2);
					i = (long) entity.getProperty("score2");
					entity.setProperty("score2", i.intValue()+score2);
					req.setAttribute("score", i.intValue()+score2);
					i = (long) entity.getProperty("total1");
					t2 = i.intValue()+t2;
					totalToSave = (int)t2;
					entity.setProperty("total12", totalToSave);
					datastore.put(entity);
				}
				else
				{
					Long i =(long)entity.getProperty("total1");
					totalToSave = i.intValue();
				}
				if(nq>7)
				{
					//redirect to page before round 3
					try {
						req.setAttribute("id", id);
						req.setAttribute("selection", sel);
						
						req.getRequestDispatcher("almost.jsp").forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					left+=round2q[nq-1];
					left+="<br/><br/><br/><input type=\"radio\" name=\"question\" value=\"a\"> a. " + round2a[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"radio\" name=\"question\" value=\"b\"> b. " + round2b[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"radio\" name=\"question\" value=\"c\"> c. " + round2c[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"radio\" name=\"question\" value=\"d\"> d. " + round2d[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"submit\" value=\"submit\">";
					//add selection, round, next question, source, id, timestamp
					left+="<input type=\"hidden\" name=\"source\" value=\"test2\">";
					 //check user selction and generate right side
					if("leaderboard".equals(sel))
					{
						//leaderboard
						Query query = new Query("id");
			        	query.addSort("total12", SortDirection.DESCENDING);
			        	List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
			        
			        	 right ="<table border=2 cellpadding=2px><tr><th>Score</th><th>Name</th></tr>";
			        	 if (!greetings.isEmpty()) {
			        	        for (Entity greeting : greetings) {
			        	        	right+="<tr><td>"+greeting.getProperty("name")+"</td><td>"+greeting.getProperty("total12")+"</td></tr>";
			        	        }
			        	    }
					}
					else
					{
						//projected
						right = "<h3>Your current score is:"+entity.getProperty("total12")+
								"<br/>Your projected score at the end of 7 questions is:";
						
						double p1 =totalToSave*100.0/(800+(nq-1)*80);
						int p2 = (int) (1320*p1/100);
						right+=p2;
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
				
				if(nq >1)
				{
					int points = 0;
					int score3 = 0;
					long time2 = Long.parseLong(req.getParameter("time"));
					int difference = (int) ((now - time2)/1000);
					
					if(difference < 30)
						points += 30 - difference;
					if(round3ans[nq-2].equals(req.getParameter("question")))
					{
						points+=50;
						score3=1;
					}
					Long i =(long)entity.getProperty("total3");
					Long j =(long)entity.getProperty("total2");
					int t2 = i.intValue()+points;
					entity.setProperty("total3", t2);
					i = (long) entity.getProperty("score2");
					entity.setProperty("score3", i.intValue()+score3);
					i = (long) entity.getProperty("total1");
					t2 = i.intValue()+t2+j.intValue();
					totalToSave = (int)t2;
					entity.setProperty("total", totalToSave);
					datastore.put(entity);
				}
				else
				{
					Long i =(long)entity.getProperty("total1");
					Long j =(long)entity.getProperty("total2");
					System.out.println(i);
					System.out.println(j);
					totalToSave = i.intValue()+j.intValue();
				}
				if(nq>7)
				{
					
				
					try {
						req.getRequestDispatcher("done.jsp").forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					left+=round3q[nq-1];
					left+="<br/><br/><br/><input type=\"radio\" name=\"question\" value=\"a\"> a. " + round3a[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"radio\" name=\"question\" value=\"b\"> b. " + round3b[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"radio\" name=\"question\" value=\"c\"> c. " + round3c[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"radio\" name=\"question\" value=\"d\"> d. " + round3d[nq-1]+"<br/>";
					left+="<br/><br/><input type=\"submit\" value=\"submit\">";
					//add selection, round, next question, source, id, timestamp
					left+="<input type=\"hidden\" name=\"source\" value=\"test3\">";
					 //check user selction and generate right side
					if(!"leaderboard".equals(sel))
					{
						//leaderboard
						Query query = new Query("id");
			        	query.addSort("total", SortDirection.DESCENDING);
			        	List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
			        
			        	 right ="<table border=2 cellpadding=2px><tr><th>Score</th><th>Name</th></tr>";
			        	 if (!greetings.isEmpty()) {
			        	        for (Entity greeting : greetings) {
			        	        	right+="<tr><td>"+greeting.getProperty("name")+"</td><td>"+greeting.getProperty("total")+"</td></tr>";
			        	        }
			        	    }
					}
					else
					{
						//projected
						right = "<h3>Your current score is:"+totalToSave+
								"<br/>Your projected score at the end of 7 questions is:";
						
						double p1 =totalToSave*100.0/(1360+(nq-1)*80);
						int p2 = (int) (1920*p1/100);
						right+=p2;
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
