public class MyCalendar {

   public static int number_of_days(int seconds) {
      return((int)(seconds/(24*60*60*1.0)));
   }

   public static int number_of_hours(int seconds) {
      int days = (int)(seconds/(24*60*60));
      
      int rest = seconds - days*24*60*60;

      return((int)(rest/(60*60)));
   }

   public static int number_of_minutes(int seconds) {
      int days = (int)(seconds/(24*60*60));

      int rest = seconds - days*24*60*60;

      int hours = (int)(rest/(60*60));

      rest = rest - hours*60*60;

      return((int)(rest/60));
   }

   public static int number_of_seconds(int seconds) {
      int days = (int)(seconds/(24*60*60));

      int rest = seconds - days*24*60*60;

      int hours = (int)(rest/(60*60));

      rest = rest - hours*60*60;
      
      int minutes = (int)(rest/60);

      return(rest - 60*minutes);
   }

}
