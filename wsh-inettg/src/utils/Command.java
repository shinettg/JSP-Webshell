package utils;

public class Command {
	
	private String name;
	private String result;
	
	public Command(String name, String result) {
		this.name = name;
		this.result = result;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
