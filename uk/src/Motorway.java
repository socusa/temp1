public class Motorway {
   private String name;
   private String route;
   private String counties;
   private double lengthInMiles;
   private double lengthInKilometers;

   public Motorway(String name,
                   String route,
                   String counties,
                   double lengthInMiles,
                   double lengthInKilometers) {
      this.name = name;
      this.route = route;
      this.counties = counties;
      this.lengthInMiles = lengthInMiles;
      this.lengthInKilometers = lengthInKilometers;
   }

   public static Motorway process(String line) {
      String[] tokens = line.split(";");

      Motorway motorway = new Motorway(tokens[0],
                                       tokens[1],
                                       tokens[2],
                                       Double.parseDouble(tokens[3]),
                                       Double.parseDouble(tokens[4]));

      return(motorway);
   }

   public String getName() {
      return(name);
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getRoute() {
      return(route);
   }

   public void setRoute(String route) {
      this.route = route;
   }

   public String getCounties() {
      return(counties);
   }

   public void setCounties(String counties) {
      this.counties = counties;
   }

   public double getLengthInMiles() {
      return(lengthInMiles);
   }

   public void setLengthInMiles(double lengthInMiles) {
      this.lengthInMiles = lengthInMiles;
   }

   public double getLengthInKilometers() {
      return(lengthInKilometers);
   }

   public void setLengthInKilometers(double lengthInKilometers) {
      this.lengthInKilometers = lengthInKilometers;
   }
}