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

public class StartTest3 extends RemoteServiceServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
		String id = req.getParameter("id");
		Key k = KeyFactory.createKey("id",id );
        Entity entity;
		try {
			entity = datastore.get(k);
			// entity.setProperty("selection", req.getParameter("selection"));
			 entity.setProperty("score3", 10);
		        datastore.put(entity);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("id", id); 
	System.out.println("Done");
//        try {
//			//req.getRequestDispatcher("test3.jsp").forward(req, resp);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
       
	}
}
