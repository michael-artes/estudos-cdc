package capitulo4;

public class Configuration {

	private String s;

	public void setAddResource(String s){
		System.out.println(this.getClass() + "  String: " + s);
		this.s = s;
	}
	
	public String getResource(){
		return this.s;
	}
	
}
