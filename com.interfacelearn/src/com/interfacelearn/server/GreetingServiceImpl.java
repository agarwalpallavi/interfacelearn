package com.interfacelearn.server;

import com.interfacelearn.client.GreetingService;
import com.interfacelearn.shared.FieldVerifier;
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
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}
//		Entity entity = new Entity("Todo", "Todo1");
//        // Alternatively use
//        // Key todoKey = KeyFactory.createKey("Todo", "Todo1");
//        // Entity entity = new Entity(todoKey);
//        entity.setProperty("summary", "This is my summary");
//        DatastoreService datastore = DatastoreServiceFactory
//                .getDatastoreService();
//        datastore.put(entity);
        DatastoreService datastore2 = DatastoreServiceFactory
                .getDatastoreService();
        Key todoKey = KeyFactory.createKey("Todo", "Todo1");
        Entity entity2;
        String summary = null;
            try {
				entity2 = datastore2.get(todoKey);
				summary = (String) entity2.getProperty("summary");
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + summary + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;

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
