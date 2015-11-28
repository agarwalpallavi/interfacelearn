package com.interfacelearn.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SelectionImpl extends RemoteServiceServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println(GreetingServiceImpl.key.toString());
		System.out.println(req.getParameter("sex"));
		System.out.println(req.getParameter("id"));
	}
}
