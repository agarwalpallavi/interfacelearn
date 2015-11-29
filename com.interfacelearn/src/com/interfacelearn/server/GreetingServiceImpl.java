package com.interfacelearn.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet{
	public static Key key;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Double number;
		DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
        boolean flag=true;
        Key k;
        Integer num;
		do {
			
			flag = true;
			number = Math.random();
			double number2 = Math.random()*100000;
			num = (int)(number * number2);
			k = KeyFactory.createKey("id", num.toString());
			try{
				datastore.get(k);
			}
			catch(EntityNotFoundException e){
				flag=false;
			}
		}while(flag);
        
		
        Entity entity = new Entity(k);
        key=k;
        entity.setProperty("name", req.getParameter("nameField"));
        entity.setProperty("age", 20);
        //entity.setProperty("age", Integer.parseInt(req.getParameter("ageField")));
        entity.setProperty("gender", req.getParameter("genderField"));
        entity.setProperty("ethnicity", req.getParameter("ethnicityField"));
        entity.setProperty("email", req.getParameter("emailField"));
        entity.setProperty("score1", null);
        entity.setProperty("score2", null);
        entity.setProperty("score3", null);
        entity.setProperty("total", null);
        entity.setProperty("selection", null);
        datastore.put(entity);
        
        System.out.println(entity.toString());
        
        req.setAttribute("message", num.toString()); // This will be available as ${message}
        try {
			req.getRequestDispatcher("test1.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
