
public class Town {
	private String name;
	private String county;
	private String status;
	
	public Town(String name,
			    String county,
			    String status) {
		this.name = name;
		this.county = county;
		this.status = status;
	}
	
	public String getName() {
		return(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCounty() {
		return(county);
	}
	
	public void setCounty(String county) {
		 this.county = county;
	}
	
	public String getStatus() {
		 return(status);
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
