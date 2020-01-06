import java.io.*;
import java.sql.*;
import java.util.*;

public class SQLite {
   private Connection connection;

   public static Connection createNewDatabase(String name) {
      String url = "jdbc:sqlite:C:/users/public/databases/" + name + ".sqlite";

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
      
      return(connection);
   }
   
   public static Connection openDatabase(String name) {
      String url = "jdbc:sqlite:C:/users/public/databases/" + name + ".sqlite";

	  Connection connection = null;
	      
	  try {
	     connection = DriverManager.getConnection(url);

	     if (connection != null) {
	            DatabaseMetaData meta = connection.getMetaData();
	            System.out.println("The driver name is " + meta.getDriverName());
	            System.out.println("A new database has been created.");
	     }
	  } catch (SQLException e) {
	     System.out.println(e.getMessage());
	  }
	      
	  return(connection);
   }
   
   public static void addToDatabase(Connection connection,
		                            String tableName,
		                            String sourceFile) {
	   String[] lines = new String[0];
	   
	   try (Scanner scanner = new Scanner(new FileInputStream(sourceFile)).useDelimiter("\\Z")) {
		   lines = scanner.next().split("\r\n");
	   } catch (Exception e) {
		   System.out.println(e);
	   }
	   
	   String[] columnNames = lines[0].split(";");
	   
	   String line = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
	   
	   for (int counter=0;counter<columnNames.length;counter++)
		   line += columnNames[counter] + " TEXT NOT NULL,";
	   
	   line = line.substring(0,line.length()-1);
	   
	   line += ")";
	   
	   try {
	      Statement statement = connection.createStatement();

	      statement.executeUpdate(line);
	      
	      for (int counter=1;counter<lines.length;counter++) {
	    	  String[] tokens = lines[counter].split(";");
	    	  
	    	  line = "INSERT INTO " + tableName + " VALUES(";
	   	   
	   	      for (int counter1=0;counter1<tokens.length;counter1++)
	   		     line += "?,";
	   	   
	   	      line = line.substring(0,line.length()-1);
	   	   
	   	      line += ");";
	   	      
	   	      System.out.println(line + " " + lines[counter]);
	   	      
	   	      PreparedStatement preparedStatement = connection.prepareStatement(line);
	   	      
	   	      for (int counter1=0;counter1<tokens.length;counter1++)
	   	    	  try {
	   	    	     preparedStatement.setString(counter1+1, tokens[counter1]);
	   	    	     
	   	    	     System.out.println(counter1 + " " + tokens[counter1]);
	   	    	  } catch (Exception e1) {
	   	    		  System.out.println(e1);
	   	    	  }
	   	      
		      preparedStatement.execute();
	      }
	   } catch (SQLException se) {
	      se.printStackTrace();
	   }
   }
   
   public static void addToDatabase(Connection connection,
		                            String sourceFile) {
	   String[] lines = new String[0];
	   
	   try (Scanner scanner = new Scanner(new FileInputStream(sourceFile)).useDelimiter("\\Z")) {
		   lines = scanner.next().split("\r\n");
	   } catch (Exception e) {
		   System.out.println(e);
	   }
	   
	   for (String line : lines) {
		   try {
	          Statement statement = connection.createStatement();

	          statement.executeUpdate(line);
		   } catch (SQLException se) {
			  System.out.println(se);
		   }
	   }
   }

   public void populateDatabase(String fileName) throws Exception {
      Scanner scanner = new Scanner(new FileInputStream(fileName)).useDelimiter("\\Z");

      String[] lines = scanner.next().split("\n");

      scanner.close();

      for (String line : lines) {
         System.out.println(line);

         Statement statement = connection.createStatement();

         statement.executeUpdate(line);
      }
   }

   public static Connection createConnection(String fileName) {
      Connection connection = null;
      
      try {
         Class.forName("org.sqlite.JDBC");

         connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

         System.out.println("Opened database successfully");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }

      return(connection);
   }

   public static java.util.List<Monarch> getMonarchs(Connection connection) {
      java.util.List<Monarch> list = new ArrayList<>();

      try {
         String temp = "SELECT * FROM monarch";

         PreparedStatement statement = connection.prepareStatement(temp);

         ResultSet rs = statement.executeQuery();

         while (rs.next())
            list.add(new Monarch(rs));
      } catch (SQLException se) {
         System.out.println(se);
      }

      return(list);  
   }

   public void close() throws Exception {
      connection.close();
   }

   public static void main(String[] args) throws Exception {
//      SQLite sqlite = new SQLite();

//      sqlite.createNewDatabase();

//      sqlite.populateDatabase("c:\\Users\\Public\\uk\\monarchs.txt");

//      sqlite.close();

//      Connection connection = createConnection("c:\\Users\\Public\\uk\\monarchs.sqlite");
	   
	   Connection connection = createNewDatabase("uk");

//	   Connection connection = openDatabase("uk");
	   
	   addToDatabase(connection,"c:\\Users\\lynn\\important\\uk\\sourcefiles\\monarchs.sql");
	   addToDatabase(connection,"motorways","c:\\Users\\lynn\\important\\uk\\sourcefiles\\highways.txt");
	   
	   addToDatabase(connection,"cities","c:\\Users\\lynn\\important\\uk\\sourcefiles\\cities.txt");
	   addToDatabase(connection,"englishcounties","c:\\Users\\lynn\\important\\uk\\sourcefiles\\englandcounties.txt");
	   addToDatabase(connection,"nationalparks","c:\\Users\\lynn\\important\\uk\\sourcefiles\\nationalparks.txt");
	   
//	   addToDatabase(connection,"INSERT INTO cities VALUES('Aberdeen','1891 (Burgh: 1179)','St Machar\\\'s', High Kirk of Aberdeen','Local government district','Scotland','Aberdeen from Torry - geograph.org.uk - 1454885.jpg','189,120');","","");

   }

}
