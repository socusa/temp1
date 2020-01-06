public class EnglandCountyByName extends EnglandCounty implements Comparable<EnglandCountyByName> {

   public EnglandCountyByName(String name,
                              String population,
                              String rankByPopulation,
                              String areaSquareKilometers,
                              String areaSquareMiles,
                              String rankByArea,
                              String density,
                              String rankByDensity) {
      super(name,population,rankByPopulation,areaSquareKilometers,areaSquareMiles,rankByArea,density,rankByDensity);
   }

   public EnglandCountyByName(EnglandCounty county) {
      this(county.name,
           county.population,
           county.rankByPopulation,
           county.areaSquareKilometers,
           county.areaSquareMiles,
           county.rankByArea,
           county.density,
           county.rankByDensity);
   }

   public static EnglandCountyByName process(String line) {
      String[] tokens = line.split(";");

      EnglandCountyByName county = new EnglandCountyByName(tokens[0],
                                                           tokens[1],
                                                           tokens[2],
                                                           tokens[3],
                                                           tokens[4],
                                                           tokens[5],
                                                           tokens[6],
                                                           tokens[7]);

      return(county);
   }

   public int compareTo(EnglandCountyByName englandCountyByName) {
      String name1 = name.toLowerCase();

      String name2 = englandCountyByName.name.toLowerCase();

      if (name1.compareTo(name2) < 0)
         return(-1);
      else if (name1.compareTo(name2) > 0)
         return(1);
      else
         return(0);
   }

}