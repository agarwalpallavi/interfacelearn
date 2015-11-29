package com.interfacelearn.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SelectionImpl extends RemoteServiceServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
		Key k = KeyFactory.createKey("id",req.getParameter("id") );
		System.out.println(req.getParameter("id"));
		System.out.println(req.getParameter("sex"));
        Entity entity;
		try {
			entity = datastore.get(k);
			 entity.setProperty("score1", req.getParameter("sex"));
			 
		        
		        datastore.put(entity);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
	}
}
