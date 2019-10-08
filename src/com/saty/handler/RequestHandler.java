package com.saty.handler;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saty.ml.App;

import org.tensorflow.*;

public class RequestHandler {
	private static RequestHandler handler;
	
	private RequestHandler(){
	}
	
	public static RequestHandler getInstance(){
		if(Objects.isNull(handler))
			return new RequestHandler();
		return handler;
	}
	public int handleRequest(HttpServletRequest request, HttpServletResponse response) {
		try (Graph g1 = new Graph()) {

			Output<Integer> c1 = new App().addConstant(g1, "C1", Integer.parseInt(request.getParameter("ip1")));
			Output<Integer> c2 = new App().addConstant(g1, "C2", Integer.parseInt(request.getParameter("ip2")));
			Output<Operation> a = new App().addAddOperation(g1, c1, c2);

			try (Session s = new Session(g1); Tensor<?> output = s.runner().fetch(a).run().get(0);) {
				return output.intValue();
			}
		}

	}
}
