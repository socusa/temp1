
public class NationalPark {
	private String name;
	private double areaInSquareKilometers;
	private String highestPoint;
	private double lengthOfCoastlineInKilometers;
	private String mainSettlements;
	
	public NationalPark(String name,
			            double areaInSquareKilometers,
			            String highestPoint,
			            double lengthOfCoastlineInKilometers,
			            String mainSettlement) {
		this.name = name;
		this.areaInSquareKilometers = areaInSquareKilometers;
		this.highestPoint = highestPoint;
		this.lengthOfCoastlineInKilometers = lengthOfCoastlineInKilometers;
		this.mainSettlements = mainSettlement;
	}
	
	public static NationalPark process(String line) {
		String[] tokens = line.split(";");
		
		NationalPark nationalPark = new NationalPark(tokens[0],
				                                     Double.parseDouble(tokens[1].replaceAll(",", "")),
				                                     tokens[2],
				                                     Double.parseDouble(tokens[3].replaceAll(",", "")),
				                                     tokens[4]);
		
		return(nationalPark);		
	}
	
	public String getName() {
		return(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getAreaInSquareKilometers() {
		return(areaInSquareKilometers);
	}
	
	public void setAreaInSquareKilometers(double areaInSquareKilometers) {
		this.areaInSquareKilometers = areaInSquareKilometers;
	}
	
	public String getHighestPoint() {
		return(highestPoint);
	}
	
	public void setHighestPoint(String highestPoint) {
		this.highestPoint = highestPoint;
	}
	
	public double getLengthOfCoastlineInKilometers() {
		return(lengthOfCoastlineInKilometers);
	}
	
	public void setLenthOfCloastlineInKilometers(double lengthOfCoastlineInKilometers) {
		this.lengthOfCoastlineInKilometers = lengthOfCoastlineInKilometers;
	}
	
	public String getMainSettlements() {
		return(mainSettlements);
	}
	
	public void setMainSettlements(String mainSettlement) {
		this.mainSettlements = mainSettlement;
	}
	

}
