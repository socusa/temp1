import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UKCity {
   private String name;
   private String yearOfCityStatus;
   private String cathedral;
   private String council;
   private String region;
   private String image;
   private String population;
   private String county;

   public UKCity(String name,
                 String yearOfCityStatus,
                 String cathedral,
                 String council,
                 String region,
                 String image,
                 String population,
                 String county) {
      this.name = name;
      this.yearOfCityStatus = yearOfCityStatus;
      this.cathedral = cathedral;
      this.council = council;
      this.region = region;
      this.image = image;
      this.population = population;
      this.county = county;
   }

   public static UKCity process(String line) {
      String[] tokens = line.split(";");

      UKCity ukCity = new UKCity(tokens[0].trim(),
                                 tokens[1].trim(),
                                 tokens[2].trim(),
                                 tokens[3].trim(),
                                 tokens[4].trim(),
                                 tokens[5].trim(),
                                 tokens[6].trim(),
                                 tokens[7].trim());

      return(ukCity);
   }
   
   public static UKCity[] getCities() {
      UKCity[] output = new UKCity[0];	   
	   
      String url = "jdbc:sqlite:C:/users/public/databases/uk.sqlite";

	  Connection connection = null;
	      
	  try {
	     connection = DriverManager.getConnection(url);

	     if (connection != null) {
	        DatabaseMetaData meta = connection.getMetaData();
	        System.out.println("The driver name is " + meta.getDriverName());
	        System.out.println("A database is open.");
	     }
	  } catch (SQLException e) {
	     System.out.println(e.getMessage());
	  }
	  
	  if (connection != null) {
		  String line = "SELECT * from cities";
		  
		  try {
			  PreparedStatement statement = connection.prepareStatement(line);
			  
			  ResultSet rs = statement.executeQuery();
			  
			  java.util.List<UKCity> cities = new ArrayList<>();
			  
			  while (rs.next()) {
				  String name = rs.getString("City");
				  String yearGrantedStatusOrConfirmed = rs.getString("YearGrantedStatusOrConfirmed");
				  String cathedral = rs.getString("Cathedral");
				  String cityCouncil = rs.getString("CityCouncil");
				  String nationRegion = rs.getString("NationRegion");
				  String image = rs.getString("Image");
				  String population = rs.getString("Population");
				  String county = rs.getString("County");
				  
				  cities.add(new UKCity(name,
						                yearGrantedStatusOrConfirmed,
						                cathedral,
						                cityCouncil,
						                nationRegion,
						                image,
						                population,
						                county));
				  
				  output = cities.toArray(new UKCity[cities.size()]);
			  }
		} catch (SQLException se) {
		   System.out.println(se);
	    }
	  }
	  
	  return(output);	   
   }

   public String getName() {
      return(name);
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getYearOfCityStatus() {
      return(yearOfCityStatus);
   }

   public void setYearOfCityStatus(String yearOfCityStatus) {
      this.yearOfCityStatus = yearOfCityStatus;
   }

   public String getCathedral() {
      return(cathedral);
   }

   public void setCathedral(String cathedral) {
      this.cathedral = cathedral;
   }

   public String getCoucil() {
      return(council);
   }

   public void setCouncil(String council) {
      this.council = council;
   }

   public String getRegion() {
      return(region);
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public String getImage() {
      return(image);
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getPopulation() {
      return(population);
   }

   public void setCounty(String county) {
      this.county = county;
   }

   public String getCounty() {
      return(county);
   }

   public boolean english() {
      return(region.toLowerCase().contains("england") || region.toLowerCase().contains("london") || region.toLowerCase().contains("yorkshire"));
   }

   public boolean scottish() {
      return(region.toLowerCase().contains("scotland"));
   }

   public boolean northernirish() {
      return(region.toLowerCase().contains("northern ireland"));
   }

   public boolean welsh() {
      return(region.toLowerCase().contains("wales"));
   }

   public String getCountry() {
      if (english())
         return("England");
      else if (scottish())
         return("Scotland");
      else if (northernirish())
         return("Northern Ireland");
      else if (welsh())
         return("Wales");
      else
         return("");      
   }

   public static List<UKCity> englishCities(List<UKCity> cities) {
      List<UKCity> output = new ArrayList<>();

      for (UKCity city : cities)
         if (city.english())
            output.add(city);

      return(output);
   }

   public static List<UKCity> scottishCities(List<UKCity> cities) {
      List<UKCity> output = new ArrayList<>();

      for (UKCity city : cities)
         if (city.scottish())
            output.add(city);

      return(output);
   }

   public static List<UKCity> northernIrishCities(List<UKCity> cities) {
      List<UKCity> output = new ArrayList<>();

      for (UKCity city : cities)
         if (city.northernirish())
            output.add(city);

      return(output);
   }

   public static List<UKCity> welshCities(List<UKCity> cities) {
      List<UKCity> output = new ArrayList<>();

      for (UKCity city : cities)
         if (city.welsh())
            output.add(city);

      return(output);
   }
}