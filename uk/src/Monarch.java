import java.sql.*;

public class Monarch {
   private String name;
   private int beginMonth;
   private int beginDay;
   private int beginYear;
   private int endMonth;
   private int endDay;
   private int endYear;
   private String house;
   private String spouse;
   private int bornMonth;
   private int bornDay;
   private int bornYear;
   private int diedMonth;
   private int diedDay;
   private int diedYear;
   private String[] months;
   private String comma;

   {
      months = new String[]{"","January","February","March",
                            "April","May","June",
                            "July","August","September",
                            "October","November","December"};
   }

   public Monarch(ResultSet rs) {
      try {
         name = rs.getString("name");
         beginMonth = rs.getInt("beginmonth");
         beginDay = rs.getInt("beginday");
         beginYear = rs.getInt("beginyear");
         endMonth = rs.getInt("endmonth");
         endDay = rs.getInt("endday");
         endYear = rs.getInt("endyear");
         house = rs.getString("house");
         spouse = rs.getString("spouse");
         bornMonth = rs.getInt("bornmonth");
         bornDay = rs.getInt("bornday");
         bornYear = rs.getInt("bornyear");
         diedMonth = rs.getInt("diedmonth");
         diedDay = rs.getInt("diedday");
         diedYear = rs.getInt("diedyear");
      } catch (SQLException se) {
         System.out.println(se);
      }
   }

   public String getName() {
      return(name);
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getBeginMonth() {
      return(beginMonth);
   }
 
   public void setBeginMonth(int beginMonth) {
      this.beginMonth = beginMonth;
   }

   public int getBeginDay() {
      return(beginDay);
   }

   public void setBeginDay(int beginDay) {
      this.beginDay = beginDay;
   }

   public int getBeginYear() {
      return(beginYear);
   }

   public void setBeginYear(int beginYear) {
      this.beginYear = beginYear;
   }

   public int getEndMonth() {
      return(endMonth);
   }

   public void setEndMonth(int endMonth) {
      this.endMonth = endMonth;
   }

   public int getEndDay() {
      return(endDay);
   }

   public void setEndDay(int endDay) {
      this.endDay = endDay;
   }

   public int getEndYear() {
      return(endYear);
   }

   public void setEndYear(int endYear) {
      this.endYear = endYear;
   }

   public String getHouse() {
      return(house);
   }

   public void setHouse(String house) {
      this.house = house;
   }

   public String getSpouse() {
      if (spouse.trim().equals(""))
         spouse = "None";

      return(spouse);
   }

   public void setSpouse(String spouse) {
      this.spouse = spouse;
   }

   public int getBornMonth() {
      return(bornMonth);
   }

   public void setBornMonth(int bornMonth) {
      this.bornMonth = bornMonth;
   }

   public int getBornDay() {
      return(bornDay);
   }

   public void setBornDay(int bornDay) {
      this.bornDay = bornDay;
   }

   public int getBornYear() {
      return(bornYear);
   }

   public void setBornYear(int bornYear) {
      this.bornYear = bornYear;
   }

   public int getDiedMonth() {
      return(diedMonth);
   }

   public void setDiedMonth(int diedMonth) {
      this.diedMonth = diedMonth;
   }

   public int getDiedDay() {
      return(diedDay);
   }

   public void setDiedDay(int diedDay) {
      this.diedDay = diedDay;
   }

   public int getDiedYear() {
      return(diedYear);
   }

   public void setDiedYear(int diedYear) {
      this.diedYear = diedYear;
   }

   public String reignBegin() {
      String month = "";
      String day = "";
      String year = String.valueOf(beginYear);

      if (beginMonth != 0)
         month = months[beginMonth];

      if (beginDay != 0)
         day = String.valueOf(beginDay);

      comma = ", ";

      if (beginMonth == 0 || beginDay == 0)
         comma = "";

      return(month + " " + day + comma + year);
   }

   public String reignEnd() {
      String month = "";
      String day = "";
      String year = String.valueOf(endYear);

      if (endMonth != 0)
         month = months[endMonth];

      if (endDay != 0)
         day = String.valueOf(endDay);

      comma = ", ";

      if (endMonth == 0 || endDay == 0)
         comma = "";

      return(month + " " + day + comma + year);
   }

   public String born() {
      String month = "";
      String day = "";
      String year = String.valueOf(bornYear);

      if (bornMonth != 0)
         month = months[bornMonth];

      if (bornDay != 0)
         day = String.valueOf(bornDay);

      comma = ", ";

      if (bornMonth == 0 || bornDay == 0)
         comma = "";

      return(month + " " + day + comma + year);
   }

   public String died() {
      String month = "";
      String day = "";
      String year = String.valueOf(diedYear);

      if (diedMonth != 0)
         month = months[diedMonth];

      if (diedDay != 0)
         day = String.valueOf(diedDay);

      comma = ", ";

      if (diedMonth == 0 || diedDay == 0)
         comma = "";

      return(month + " " + day + comma + year);
   }
}