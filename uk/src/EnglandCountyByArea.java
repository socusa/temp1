public class EnglandCountyByArea extends EnglandCounty implements Comparable<EnglandCountyByArea> {

   public EnglandCountyByArea(String name,
                              String population,
                              String rankByPopulation,
                              String areaSquareKilometers,
                              String areaSquareMiles,
                              String rankByArea,
                              String density,
                              String rankByDensity) {
      super(name,population,rankByPopulation,areaSquareKilometers,areaSquareMiles,rankByArea,density,rankByDensity);
   }

   public EnglandCountyByArea(EnglandCounty county) {
      this(county.name,
           county.population,
           county.rankByPopulation,
           county.areaSquareKilometers,
           county.areaSquareMiles,
           county.rankByArea,
           county.density,
           county.rankByDensity);
   }

   public static EnglandCountyByArea process(String line) {
      String[] tokens = line.split(";");

      EnglandCountyByArea county = new EnglandCountyByArea(tokens[0],
                                                           tokens[1],
                                                           tokens[2],
                                                           tokens[3],
                                                           tokens[4],
                                                           tokens[5],
                                                           tokens[6],
                                                           tokens[7]);

      return(county);
   }

   public int compareTo(EnglandCountyByArea englandCountyByArea) {
      double area1 = Double.parseDouble(areaSquareMiles.replaceAll(",",""));

      double area2 = Double.parseDouble(englandCountyByArea.areaSquareMiles.replaceAll(",",""));

      if (area1 < area2)
         return(-1);
      else if (area1 > area2)
         return(1);
      else
         return(0);
   }

}