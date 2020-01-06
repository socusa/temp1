public class EnglandCounty {
   protected String name;
   protected String population;
   protected String rankByPopulation;
   protected String areaSquareKilometers;
   protected String areaSquareMiles;
   protected String rankByArea;
   protected String density;
   protected String rankByDensity;

   public EnglandCounty(String name,
                        String population,
                        String rankByPopulation,
                        String areaSquareKilometers,
                        String areaSquareMiles,
                        String rankByArea,
                        String density,
                        String rankByDensity) {
      this.name = name;
      this.population = population;
      this.rankByPopulation = rankByPopulation;
      this.areaSquareKilometers = areaSquareKilometers;
      this.areaSquareMiles = areaSquareMiles;
      this.rankByArea = rankByArea;
      this.density = density;
      this.rankByDensity = rankByDensity;
   }

   public static EnglandCounty process(String line) {
      String[] tokens = line.split(";");

      EnglandCounty county = new EnglandCounty(tokens[0],
                                               tokens[1],
                                               tokens[2],
                                               tokens[3],
                                               tokens[4],
                                               tokens[5],
                                               tokens[6],
                                               tokens[7]);

      return(county);
   }

   public String getName() {
      return(name);
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPopulation() {
      return(population);
   }

   public void setPopulation(String population) {
      this.population = population;
   }

   public String getRankByPopulation() {
      return(rankByPopulation);
   }

   public void setRankByPopulation(String rankByPopulation) {
      this.rankByPopulation = rankByPopulation;
   }

   public String getAreaSquareKilometers() {
      return(areaSquareKilometers);
   }

   public void setAreaSquareKilometers(String areaSquareKilometers) {
      this.areaSquareKilometers = areaSquareKilometers;
   }

   public String getAreaSquareMiles() {
      return(areaSquareMiles);
   }

   public void setAreaSqureMiles(String areaSquareMiles) {
      this.areaSquareMiles = areaSquareMiles;
   }

   public String getRankByArea() {
      return(rankByArea);
   }

   public void setRankByArea(String rankByArea) {
      this.rankByArea = rankByArea;
   }

   public String getDensity() {
      return(density);
   }

   public void setDensity(String density) {
      this.density = density;
   }

   public String getRankByDensity() {
      return(rankByDensity);
   }

   public void setRankByDensity(String rankByDensity) {
      this.rankByDensity = rankByDensity;
   }
}