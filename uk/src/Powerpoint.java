import java.io.*;
import java.awt.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.util.*;
import org.apache.poi.sl.usermodel.*;
import java.awt.geom.*;

public class Powerpoint extends JFrame implements ActionListener,ItemListener {
   private JComboBox<String> fontFamilyNames;
   private JButton createPowerPoint;
   private DisplayPanel displayPanel;
   private java.util.List<UKCity> cities;
   private java.util.List<UKCity> englishCities;
   private java.util.List<UKCity> northernIrishCities;
   private java.util.List<UKCity> scottishCities;
   private java.util.List<UKCity> welshCities;

   public Powerpoint(String title) throws Exception {
      super(title);

      setLayout(new FlowLayout());

      String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

      add(fontFamilyNames = new JComboBox<String>(availableFontFamilyNames));

      fontFamilyNames.setFont(new Font("Comic Sans MS",Font.BOLD,20));

      fontFamilyNames.addItemListener(this);

      add(createPowerPoint = new JButton("Create PowerPoint"));

      createPowerPoint.addActionListener(this);

      displayPanel = new DisplayPanel(400,200);

      displayPanel.setFont("Arial");

      add(displayPanel);
   }

   public class DisplayPanel extends JPanel {
      private String text;
      private String fontFamily;

      public DisplayPanel(int width,int height) {
         setPreferredSize(new Dimension(width,height));

         fontFamily = "Arial";

         text = "This is how it looks";
      }

      public void setFont(String fontFamily) {
         this.fontFamily = fontFamily;

         repaint();
      }

      public void paintComponent(Graphics g) {
         super.paintComponent(g);

         g.setFont(new Font(fontFamily,Font.BOLD,20));

         g.drawString(text,0,20);
      }
   }

   public void actionPerformed(ActionEvent e) {
      String fontFamilyName = fontFamilyNames.getSelectedItem().toString();

      createUKCityPowerPoint(fontFamilyName);
   }

   public void itemStateChanged(ItemEvent e) {
      displayPanel.setFont(fontFamilyNames.getSelectedItem().toString());
   }

   public void clearTextShape(XSLFTextShape shape) {
      shape.clearText();

      shape.setText(" ");

      java.util.List<XSLFTextParagraph> paragraphs = shape.getTextParagraphs();


      paragraphs.get(0).setBullet(false);
   }

   public void createMainTitle(XMLSlideShow powerpoint,
                               XSLFSlideMaster slideMaster) {
      XSLFSlide slide = powerpoint.createSlide();

      XSLFTextBox box = slide.createTextBox();

      box.setAnchor(new Rectangle(0,0,2063,100));

      XSLFTextParagraph paragraph = box.addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(120.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");
      run.setText("Facts about the United Kingdom");

      box.setHorizontalCentered(true);

      try {
         byte[] bytes = IOUtils.toByteArray(new FileInputStream("c:\\Users\\Public\\uk\\images\\flag.png"));

         XSLFPictureData data = powerpoint.addPicture(bytes,PictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);
 
         shape.setAnchor(new Rectangle(100,200,800,800));
      } catch (Exception e) {
         System.out.println(e);
      }

      try {
         byte[] bytes = IOUtils.toByteArray(new FileInputStream("C:\\Users\\lynn\\important\\uk\\london.jpg"));

         XSLFPictureData data = powerpoint.addPicture(bytes,PictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);
 
         shape.setAnchor(new Rectangle(1163,200,800,800));
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void createTitleSlide(XMLSlideShow powerpoint,
                                XSLFSlideMaster slideMaster,
                                String fontFamily,
                                String title) {
      XSLFSlideLayout titleSlideLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

      XSLFSlide title1 = powerpoint.createSlide(titleSlideLayout);

      XSLFTextShape title3 = title1.getPlaceholder(0);

      title3.clearText();

      title3.setText(title);

      XSLFTextShape title2 = title1.getPlaceholder(1);

      Rectangle2D originalAnchor = title2.getAnchor();

      title2.clearText();

      title2.setText(" ");

/*

      XSLFTextParagraph paragraph1 = title2.addNewTextParagraph();

      Rectangle2D anchor = title2.getAnchor();

      Dimension d = powerpoint.getPageSize();

      anchor.setFrame(new Point(0,d.height/2-100),new Dimension(d.width,200));

         title2.setAnchor(anchor);

      paragraph1.setBulletCharacter(" ");

      paragraph1.setTextAlign(TextParagraph.TextAlign.CENTER);

      XSLFTextRun textRun1 = paragraph1.addNewTextRun();

      textRun1.setFontColor(new Color(0,0,200));
      textRun1.setFontSize(120.0);
      textRun1.setBold(true);
      textRun1.setItalic(true);
      textRun1.setFontFamily(fontFamily);
         
      textRun1.setText(title);

*/

   }

   public XSLFSlide getNewTitle(XMLSlideShow powerpoint,
                                XSLFSlideMaster slideMaster) {

/*
      XSLFSlide slide = null;

      try {
         File file = new File("c:\\Users\\Public\\uk\\title.pptx");

         XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
      
      //get the slides 
         java.util.List<XSLFSlide> slides = ppt.getSlides(); 

         slide = slides.get(0);
      } catch (Exception e) {
         System.out.println(e);
      }
*/
      XSLFSlideLayout slideLayout = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      XSLFSlide slide = powerpoint.createSlide(slideLayout);

      XSLFTextShape[] placeHolders = slide.getPlaceholders();
	      
      for (int counter=0;counter<placeHolders.length;counter++) {
         XSLFTextShape title = placeHolders[counter];
	    	  
	 title.setText("Placeholder " + counter);
      }

/*

      XSLFTextShape shape1 = slide.getPlaceholder(0);

      shape1.clearText();
 
      shape1.setText("Facts about the United Kingdom");

      clearTextShape(slide.getPlaceholder(1));
      clearTextShape(slide.getPlaceholder(2));
      clearTextShape(slide.getPlaceholder(3));
      clearTextShape(slide.getPlaceholder(4));

      try {
         byte[] bytes = IOUtils.toByteArray(new FileInputStream("c:\\Users\\lynn\\important\\uk\\london.jpg"));

         XSLFPictureData data = powerpoint.addPicture(bytes,PictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);
 
         shape.setAnchor(new Rectangle(200,100,100,400));
      } catch (Exception e) {
         System.out.println(e);
      }

*/

      return(slide);
   }

   public void displayEnglishCities(XMLSlideShow powerpoint,
                                    XSLFSlideMaster slideMaster) {
      XSLFSlideLayout slideLayout1 = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      XSLFSlide slide1 = powerpoint.createSlide(slideLayout1);

      XSLFBackground background = slide1.getBackground();

      background.setFillColor(new Color(52,73,94));

/*

      XSLFAutoShape shape = slide1.createAutoShape();

      shape.setShapeType(ShapeType.RECT);

      shape.setFillColor(new Color(0,0,100));

      shape.setAnchor(new Rectangle(0,200,2048,1336));

*/

      XSLFTextShape title = slide1.getPlaceholder(0);

      title.setAnchor(new Rectangle(0,20,2000,100));

      title.clearText();

      XSLFTextParagraph paragraph = title.addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(90.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");
         
      run.setText("English Cities");

      clearTextShape(slide1.getPlaceholder(1));
      clearTextShape(slide1.getPlaceholder(2));
      clearTextShape(slide1.getPlaceholder(3));
      clearTextShape(slide1.getPlaceholder(4));      

      XSLFTable table = slide1.createTable();

      table.setAnchor(new Rectangle(0,200,2000,1500));

      for (int counter=0;counter<13;counter++) {
         XSLFTableRow row = table.addRow();

         row.setHeight(1300/13);

         for (int counter1=0;counter1<4;counter1++) {
            XSLFTableCell cell = row.addCell();

            XSLFTextParagraph paragraph3 = cell.addNewTextParagraph();

            XSLFTextRun textRun3 = paragraph3.addNewTextRun();

            textRun3.setFontColor(Color.white);
            textRun3.setFontSize(45.0);
            textRun3.setBold(true);
            textRun3.setFontFamily("Comic Sans MS");

            if (counter + 13*counter1 < englishCities.size()) {
               UKCity city = englishCities.get(counter + 13*counter1);

               textRun3.setText(city.getName());
            } else {
               textRun3.setText("");
            }
         }
      }

      for (int counter=0;counter<4;counter++)
         table.setColumnWidth(counter,500);
   }

   public void listEnglishCountiesByName(XMLSlideShow powerpoint,
                                         XSLFSlideMaster slideMaster) {
      XSLFSlideLayout slideLayout1 = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      java.util.List<EnglandCountyByName> counties = new ArrayList<>();

      try {
         Scanner scanner = new Scanner(new FileInputStream("c:\\Users\\Public\\uk\\england counties.txt")).useDelimiter("\\Z");

         String[] lines = scanner.next().split("\n");

         scanner.close();

         for (int counter=0;counter<lines.length;counter++)
            if (!lines[counter].split(";")[0].equalsIgnoreCase("County"))
               counties.add(EnglandCountyByName.process(lines[counter]));
      } catch (Exception e) {
         System.out.println(e);
      }

      Collections.sort(counties);

      XSLFSlide slide1 = powerpoint.createSlide(slideLayout1);

      clearTextShape(slide1.getPlaceholder(0));

      XSLFTextParagraph paragraph = slide1.getPlaceholder(0).addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(90.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");

      run.setText("English Counties in Alphabetical Order");

      clearTextShape(slide1.getPlaceholder(1));
      clearTextShape(slide1.getPlaceholder(2));
      clearTextShape(slide1.getPlaceholder(3));
      clearTextShape(slide1.getPlaceholder(4));      

      XSLFTable table = slide1.createTable();

      table.setAnchor(new Rectangle(10,200,2000,1350));

      for (int counter=0;counter<12;counter++) {
         XSLFTableRow row = table.addRow();

         row.setHeight(1150/11);

         for (int counter1=0;counter1<4;counter1++) {
            XSLFTableCell cell = row.addCell();

            XSLFTextParagraph paragraph3 = cell.addNewTextParagraph();

            XSLFTextRun textRun3 = paragraph3.addNewTextRun();

            textRun3.setFontColor(Color.white);
            textRun3.setFontSize(45.0);
            textRun3.setBold(true);
            textRun3.setFontFamily("Lucida Bright");

            if (counter + 12*counter1 < counties.size()) {
               EnglandCountyByName county = counties.get(counter + 12*counter1);

               textRun3.setText(county.getName());
            } else {
               textRun3.setText("");
            }
         }
      }

      for (int counter=0;counter<4;counter++)
         if (counter == 1)
            table.setColumnWidth(counter,600);
         else
            table.setColumnWidth(counter,450);
   }

   public void listEnglishCountiesByArea(XMLSlideShow powerpoint,
                                         XSLFSlideMaster slideMaster) {
      XSLFSlideLayout slideLayout1 = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      java.util.List<EnglandCountyByArea> counties = new ArrayList<>();

      try {
         Scanner scanner = new Scanner(new FileInputStream("c:\\Users\\Public\\uk\\england counties.txt")).useDelimiter("\\Z");

         String[] lines = scanner.next().split("\n");

         scanner.close();

         for (int counter=0;counter<lines.length;counter++)
            if (!lines[counter].split(";")[0].equalsIgnoreCase("County"))
               counties.add(EnglandCountyByArea.process(lines[counter]));
      } catch (Exception e) {
         System.out.println(e);
      }

      Collections.sort(counties);

      XSLFSlide slide1 = powerpoint.createSlide(slideLayout1);

      clearTextShape(slide1.getPlaceholder(0));

      XSLFTextParagraph paragraph = slide1.getPlaceholder(0).addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(90.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");

      run.setText("English Counties by Area");

      clearTextShape(slide1.getPlaceholder(1));
      clearTextShape(slide1.getPlaceholder(2));
      clearTextShape(slide1.getPlaceholder(3));
      clearTextShape(slide1.getPlaceholder(4));      

      XSLFTable table = slide1.createTable();

      table.setAnchor(new Rectangle(10,200,2000,1350));

      for (int counter=0;counter<12;counter++) {
         XSLFTableRow row = table.addRow();

         row.setHeight(1150/11);

         for (int counter1=0;counter1<4;counter1++) {
            XSLFTableCell cell = row.addCell();

            XSLFTextParagraph paragraph3 = cell.addNewTextParagraph();

            XSLFTextRun textRun3 = paragraph3.addNewTextRun();

            textRun3.setFontColor(Color.white);
            textRun3.setFontSize(45.0);
            textRun3.setBold(true);
            textRun3.setFontFamily("Lucida Bright");

            if (counter + 12*counter1 < counties.size()) {
               EnglandCountyByArea county = counties.get(counter + 12*counter1);

               textRun3.setText(county.getName());
            } else {
               textRun3.setText("");
            }
         }
      }

      for (int counter=0;counter<4;counter++)
         if (counter == 0)
            table.setColumnWidth(counter,550);
         else if (counter == 2)
            table.setColumnWidth(counter,600);
         else
            table.setColumnWidth(counter,450);
   }

   public void displayNorthernIrishCities(XMLSlideShow powerpoint,
                                          XSLFSlideMaster slideMaster) {
      XSLFSlideLayout slideLayout1 = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      XSLFSlide slide1 = powerpoint.createSlide(slideLayout1);

      clearTextShape(slide1.getPlaceholder(0));

      XSLFTextShape title = slide1.getPlaceholder(0);

      title.setAnchor(new Rectangle(0,20,2000,100));

      title.clearText();

      XSLFTextParagraph paragraph = title.addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(90.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");
         
      run.setText("Northern Irish Cities");

      clearTextShape(slide1.getPlaceholder(1));
      clearTextShape(slide1.getPlaceholder(2));
      clearTextShape(slide1.getPlaceholder(3));
      clearTextShape(slide1.getPlaceholder(4));      

      XSLFTable table = slide1.createTable();

      table.setAnchor(new Rectangle(0,200,2000,1500));

      for (int counter=0;counter<northernIrishCities.size();counter++) {
         XSLFTableRow row = table.addRow();

         row.setHeight(30);

         XSLFTableCell cell = row.addCell();

         XSLFTextParagraph paragraph3 = cell.addNewTextParagraph();

         XSLFTextRun textRun3 = paragraph3.addNewTextRun();

         textRun3.setFontColor(Color.white);
         textRun3.setFontSize(60.0);
         textRun3.setBold(true);
         textRun3.setFontFamily("Lucida Bright");
          
         UKCity city = northernIrishCities.get(counter);

         textRun3.setText(city.getName()); 
      }

      table.setColumnWidth(0,500);
   }

   public void displayScottishCities(XMLSlideShow powerpoint,
                                     XSLFSlideMaster slideMaster) {
      XSLFSlideLayout slideLayout1 = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      XSLFSlide slide1 = powerpoint.createSlide(slideLayout1);

      clearTextShape(slide1.getPlaceholder(0));
 
      XSLFTextShape title = slide1.getPlaceholder(0);

      title.setAnchor(new Rectangle(0,20,2000,100));

      title.clearText();

      XSLFTextParagraph paragraph = title.addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(90.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");
         
      run.setText("Scottish Cities");

      clearTextShape(slide1.getPlaceholder(1));
      clearTextShape(slide1.getPlaceholder(2));
      clearTextShape(slide1.getPlaceholder(3));
      clearTextShape(slide1.getPlaceholder(4));      

      XSLFTable table = slide1.createTable();

      table.setAnchor(new Rectangle(0,200,2000,1500));

      for (int counter=0;counter<scottishCities.size();counter++) {
         XSLFTableRow row = table.addRow();

         row.setHeight(30);

         XSLFTableCell cell = row.addCell();

         XSLFTextParagraph paragraph3 = cell.addNewTextParagraph();

         XSLFTextRun textRun3 = paragraph3.addNewTextRun();

         textRun3.setFontColor(Color.white);
         textRun3.setFontSize(60.0);
         textRun3.setBold(true);
         textRun3.setFontFamily("Lucida Bright");
          
         UKCity city = scottishCities.get(counter);

         textRun3.setText(city.getName()); 
      }

      table.setColumnWidth(0,500);
   }

   public void displayWelshCities(XMLSlideShow powerpoint,
                                  XSLFSlideMaster slideMaster) {
      XSLFSlideLayout slideLayout1 = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      XSLFSlide slide1 = powerpoint.createSlide(slideLayout1);

      clearTextShape(slide1.getPlaceholder(0));

      XSLFTextShape title = slide1.getPlaceholder(0);

      title.setAnchor(new Rectangle(0,20,2000,100));

      title.clearText();

      XSLFTextParagraph paragraph = title.addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(90.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");
         
      run.setText("Welsh Cities");
 
      clearTextShape(slide1.getPlaceholder(1));
      clearTextShape(slide1.getPlaceholder(2));
      clearTextShape(slide1.getPlaceholder(3));
      clearTextShape(slide1.getPlaceholder(4));      

      XSLFTable table = slide1.createTable();

      table.setAnchor(new Rectangle(0,200,2000,1500));

      for (int counter=0;counter<welshCities.size();counter++) {
         XSLFTableRow row = table.addRow();

         row.setHeight(30);

         XSLFTableCell cell = row.addCell();

         XSLFTextParagraph paragraph3 = cell.addNewTextParagraph();

         XSLFTextRun textRun3 = paragraph3.addNewTextRun();

         textRun3.setFontColor(Color.white);
         textRun3.setFontSize(60.0);
         textRun3.setBold(true);
         textRun3.setFontFamily("Lucida Bright");
          
         UKCity city = welshCities.get(counter);

         textRun3.setText(city.getName()); 
      }

      table.setColumnWidth(0,500);
   }

   public void displayCities(XMLSlideShow powerpoint,
                             XSLFSlideMaster slideMaster,
                             java.util.List<UKCity> cities) {
      XSLFSlideLayout slideLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

      for (UKCity city : cities) {
         XSLFSlide slide = powerpoint.createSlide(slideLayout);

         XSLFBackground background = slide.getBackground();

//         background.setFillColor(new Color(46,204,113));

         XSLFTextShape title = slide.getPlaceholder(0);

//            title.setAnchor(originalAnchor);

         title.clearText();

         XSLFTextParagraph paragraph = title.addNewTextParagraph();

         XSLFTextRun textRun = paragraph.addNewTextRun();

         double fontSize = 30.0;

         if (city.getName().equalsIgnoreCase("Westminster") ||
             city.getName().equalsIgnoreCase("Dundee") ||
             city.getName().equalsIgnoreCase("Perth") ||
             city.getName().equalsIgnoreCase("Swansea"))
            fontSize = 25.0;

         textRun.setFontColor(Color.white);
         textRun.setFontSize(60.0);
         textRun.setBold(true);
         textRun.setItalic(true);
         textRun.setFontFamily("Comic Sans MS");

         textRun.setText(city.getName());

         slide.getPlaceholder(1).clearText();

         slide.getPlaceholder(1).setText(" ");

         XSLFTextBox body = slide.createTextBox();

         body.setAnchor(new Rectangle(100,100,2000,200));

         XSLFTextParagraph paragraph1 = body.addNewTextParagraph();

         paragraph1.setBullet(true);

         XSLFTextRun run = paragraph1.addNewTextRun();

         run.setFontSize(50.0);
         run.setFontColor(Color.white);

         run.setText("Year achieving status: " + city.getYearOfCityStatus());

         XSLFTextParagraph paragraph2 = body.addNewTextParagraph();

         paragraph2.setBullet(true);

         run = paragraph2.addNewTextRun();

         run.setFontSize(50.0);
         run.setFontColor(Color.white);

         run.setText("Cathedral: " + city.getCathedral().replaceAll("\\s+"," "));

         XSLFTextParagraph paragraph3 = body.addNewTextParagraph();

         paragraph3.setBullet(true);

         run = paragraph3.addNewTextRun();

         run.setFontSize(50.0);
         run.setFontColor(Color.white);

         run.setText("County: " + city.getCounty() + ", " + city.getCountry());

         XSLFTextParagraph paragraph4 = body.addNewTextParagraph();

         run = paragraph4.addNewTextRun();

         paragraph4.setBullet(true);

         run.setFontSize(50.0);
         run.setFontColor(Color.white);

         run.setText("Population: " + city.getPopulation());

         try {
            byte[] bytes = IOUtils.toByteArray(new URL(city.getImage()).openStream());

            XSLFPictureData data = powerpoint.addPicture(bytes,PictureData.PictureType.JPEG);

            XSLFPictureShape shape = slide.createPicture(data);
 
            shape.setAnchor(new Rectangle(0,550,2048,986));
         } catch (Exception e) {
            System.out.println(e);
         }
      }
   }

   public void displayEnglishCounties(XMLSlideShow powerpoint,
                                      XSLFSlideMaster slideMaster) {
      XSLFSlideLayout slideLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

      java.util.List<EnglandCounty> englandCounties = new ArrayList<>();

      try {
         Scanner scanner = new Scanner(new FileInputStream("c:\\Users\\Public\\uk\\england counties.txt")).useDelimiter("\\Z");

         String[] lines = scanner.next().split("\n");

         scanner.close();

         for (int counter=0;counter<lines.length;counter++)
            englandCounties.add(EnglandCounty.process(lines[counter]));
      } catch (Exception e) {
         System.out.println(e);
      }

      Dimension d = powerpoint.getPageSize();

      for (EnglandCounty englandCounty : englandCounties) {   
         if (englandCounty.getName().equalsIgnoreCase("County"))
            continue;

         XSLFSlide slide = powerpoint.createSlide(slideLayout);

         XSLFTextShape title = slide.getPlaceholder(0);

         title.clearText();

         XSLFTextParagraph paragraph1 = title.addNewTextParagraph();

         XSLFTextRun run1 = paragraph1.addNewTextRun();

         run1.setFontColor(Color.white);
         run1.setFontSize(90.0);
         run1.setBold(true);
         run1.setItalic(true);
         run1.setFontFamily("Comic Sans MS");
         
         run1.setText(englandCounty.getName());

         XSLFTextShape content = slide.getPlaceholder(1);

         content.clearText();
         
         content.setText(" ");

 //        XSLFTextParagraph paragraph = content.addNewTextParagraph();
         
         XSLFTextBox box = slide.createTextBox();
         
         box.setAnchor(new Rectangle(10,200,2000,100));
         
         XSLFTextParagraph paragraph = box.addNewTextParagraph();

         XSLFTextRun run = paragraph.addNewTextRun();

         run.setFontColor(Color.white);
         run.setFontSize(50.0);
         run.setBold(true);
         run.setItalic(true);
         run.setFontFamily("Comic Sans MS");

         run.setText("Population: " + englandCounty.getPopulation() + "\n");

         run = paragraph.addNewTextRun();

         run.setFontColor(Color.white);
         run.setFontSize(50.0);
         run.setBold(true);
         run.setItalic(true);
         run.setFontFamily("Comic Sans MS");

         run.setText("Area in square miles: " + englandCounty.getAreaSquareMiles());

         try {
            byte[] bytes = IOUtils.toByteArray(new FileInputStream("c:\\Users\\Public\\uk\\images\\" + englandCounty.getName().replaceAll(" ","").toLowerCase() + ".png"));

            XSLFPictureData data = powerpoint.addPicture(bytes,PictureData.PictureType.PNG);

            XSLFPictureShape shape = slide.createPicture(data);
 
            shape.setAnchor(new Rectangle(2000/2 - 350,400,350*2,1000));
         } catch (Exception e) {
            System.out.println(e);
         }

      }
   }

   public void displayMonarch(XMLSlideShow powerpoint,
                              XSLFSlideMaster slideMaster,
                              Monarch monarch) {
      XSLFSlideLayout slideLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

      XSLFSlide slide = powerpoint.createSlide(slideLayout);

      XSLFTextShape title = slide.getPlaceholder(0);

      title.clearText();

      XSLFTextParagraph paragraph = Util.createParagraph(title,
    		                                             Color.white,
    		                                             90.0,
    		                                             true,
    		                                             true,
    		                                             "Comic Sans MS",
    		                                             monarch.getName());

      XSLFTextShape body = slide.getPlaceholder(1);

      body.clearText();

      body.setText(" ");

      XSLFTextBox box = slide.createTextBox();

      box.setAnchor(new Rectangle(10,400,1000,1100));


      XSLFTextParagraph paragraph1 = Util.createParagraph(title,
    		                                             Color.white,
    		                                             50.0,
    		                                             true,
    		                                             true,
    		                                             "Comic Sans MS",
    		                                             monarch.born() + " - " + monarch.died() + "\n");

      XSLFTextRun textRun2 = paragraph1.addNewTextRun();

      textRun2.setFontColor(Color.white);
      textRun2.setFontSize(50.0);
      textRun2.setBold(true);
      textRun2.setItalic(true);
      textRun2.setFontFamily("Comic Sans MS");

      textRun2.setText("Reign: " + monarch.reignBegin() + " - " + monarch.reignEnd() + "\n");

      XSLFTextRun textRun3 = paragraph1.addNewTextRun();

      textRun3.setFontColor(Color.white);
      textRun3.setFontSize(50.0);
      textRun3.setBold(true);
      textRun3.setItalic(true);
      textRun3.setFontFamily("Comic Sans MS");

      textRun3.setText("House: " + monarch.getHouse() + "\n");

      XSLFTextRun textRun4 = paragraph1.addNewTextRun();

      textRun4.setFontColor(Color.white);
      textRun4.setFontSize(50.0);
      textRun4.setBold(true);
      textRun4.setItalic(true);
      textRun4.setFontFamily("Comic Sans MS");

      textRun4.setText("Spouse(s): " + monarch.getSpouse());

      try {
         File image = new File("c:\\Users\\Public\\uk\\images\\" + monarch.getName().replaceAll(" ","").toLowerCase() + ".jpg");

         byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

         XSLFPictureData data = powerpoint.addPicture(picture,XSLFPictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);

         shape.setAnchor(new Rectangle(1100,400,800,1100));
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void displayMotorway(XMLSlideShow powerpoint,
                               XSLFSlideMaster slideMaster,
                               Motorway motorway) {
      XSLFSlideLayout slideLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

      XSLFSlide slide = powerpoint.createSlide(slideLayout);

      XSLFTextShape title = slide.getPlaceholder(0);

      title.clearText();

      XSLFTextParagraph paragraph = title.addNewTextParagraph();

      XSLFTextRun textRun = paragraph.addNewTextRun();

      textRun.setFontColor(Color.white);
      textRun.setFontSize(90.0);
      textRun.setBold(true);
      textRun.setItalic(true);
      textRun.setFontFamily("Comic Sans MS");

      textRun.setText(motorway.getName());

      XSLFTextShape body = slide.getPlaceholder(1);

      body.clearText();

      body.setText(" ");

      XSLFTextBox box = slide.createTextBox();

      box.setAnchor(new Rectangle(50,100,2000,100));

      XSLFTextParagraph paragraph1 = box.addNewTextParagraph();

      XSLFTextRun textRun1 = paragraph1.addNewTextRun();

      textRun1.setFontColor(Color.white);
      textRun1.setFontSize(50.0);
      textRun1.setBold(true);
      textRun1.setItalic(true);
      textRun1.setFontFamily("Comic Sans MS");

      textRun1.setText(motorway.getRoute());
      
      XSLFTextBox box1 = slide.createTextBox();

      if (motorway.getName().equalsIgnoreCase("m62"))
    	  box1.setAnchor(new Rectangle(50,500,2000,50));
      else
          box1.setAnchor(new Rectangle(50,350,2000,50));

      XSLFTextParagraph paragraph2 = box1.addNewTextParagraph();

      XSLFTextRun textRun2 = paragraph2.addNewTextRun();

      textRun2.setFontColor(Color.white);
      textRun2.setFontSize(50.0);
      textRun2.setBold(true);
      textRun2.setItalic(true);
      textRun2.setFontFamily("Comic Sans MS");

      textRun2.setText("Length: " + String.valueOf(motorway.getLengthInMiles()) + " miles");

/*

      XSLFTextRun textRun2 = paragraph1.addNewTextRun();

      textRun2.setFontColor(Color.white);
      textRun2.setFontSize(50.0);
      textRun2.setBold(true);
      textRun2.setItalic(true);
      textRun2.setFontFamily("Comic Sans MS");

      textRun2.setText("Reign: " + monarch.reignBegin() + " - " + monarch.reignEnd() + "\n");

      XSLFTextRun textRun3 = paragraph1.addNewTextRun();

      textRun3.setFontColor(Color.white);
      textRun3.setFontSize(50.0);
      textRun3.setBold(true);
      textRun3.setItalic(true);
      textRun3.setFontFamily("Comic Sans MS");

      textRun3.setText("House: " + monarch.getHouse() + "\n");

      XSLFTextRun textRun4 = paragraph1.addNewTextRun();

      textRun4.setFontColor(Color.white);
      textRun4.setFontSize(50.0);
      textRun4.setBold(true);
      textRun4.setItalic(true);
      textRun4.setFontFamily("Comic Sans MS");

      textRun4.setText("Spouse(s): " + monarch.getSpouse());

*/

      try {
         File image = new File("c:\\Users\\lynn\\important\\uk\\motorways\\" + motorway.getName().toLowerCase().replaceAll("\\s+","") + ".png");

         byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

         XSLFPictureData data = powerpoint.addPicture(picture,XSLFPictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);

         shape.setAnchor(new Rectangle(1100,400,800,1100));
      } catch (Exception e) {
         try {
            File image = new File("c:\\Users\\lynn\\important\\uk\\motorways\\" + motorway.getName().toLowerCase().replaceAll("\\s+","") + ".jpg");

            byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

            XSLFPictureData data = powerpoint.addPicture(picture,XSLFPictureData.PictureType.JPEG);

            XSLFPictureShape shape = slide.createPicture(data);

            shape.setAnchor(new Rectangle(1100,400,800,1100));
         } catch (Exception e1) {
            System.out.println(e1);
         }

         System.out.println(e);
      }

   }
   
   public void displayNationalPark(XMLSlideShow powerpoint,
                                   XSLFSlideMaster slideMaster,
                                   NationalPark nationalPark) {
      XSLFSlideLayout slideLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

      XSLFSlide slide = powerpoint.createSlide(slideLayout);

      XSLFTextShape title = slide.getPlaceholder(0);

      title.clearText();

      XSLFTextParagraph paragraph = title.addNewTextParagraph();

      XSLFTextRun textRun = paragraph.addNewTextRun();

      textRun.setFontColor(Color.white);
      textRun.setFontSize(90.0);
      textRun.setBold(true);
      textRun.setItalic(true);
      textRun.setFontFamily("Comic Sans MS");

      textRun.setText(nationalPark.getName());

      XSLFTextShape body = slide.getPlaceholder(1);

      body.clearText();

      body.setText(" ");

      XSLFTextBox box = slide.createTextBox();

      box.setAnchor(new Rectangle(50,150,2000,100));

      XSLFTextParagraph paragraph1 = box.addNewTextParagraph();

      XSLFTextRun textRun1 = paragraph1.addNewTextRun();

      textRun1.setFontColor(Color.white);
      textRun1.setFontSize(50.0);
      textRun1.setBold(true);
      textRun1.setItalic(true);
      textRun1.setFontFamily("Comic Sans MS");

      textRun1.setText("Area: " + nationalPark.getAreaInSquareKilometers() + " square kilometers");

      XSLFTextParagraph paragraph2 = box.addNewTextParagraph();

      XSLFTextRun textRun2 = paragraph2.addNewTextRun();

      textRun2.setFontColor(Color.white);
      textRun2.setFontSize(50.0);
      textRun2.setBold(true);
      textRun2.setItalic(true);
      textRun2.setFontFamily("Comic Sans MS");

      textRun2.setText("Highest Point: " + nationalPark.getHighestPoint() + " meters");

      XSLFTextParagraph paragraph3 = box.addNewTextParagraph();

      XSLFTextRun textRun3 = paragraph3.addNewTextRun();

      textRun3.setFontColor(Color.white);
      textRun3.setFontSize(50.0);
      textRun3.setBold(true);
      textRun3.setItalic(true);
      textRun3.setFontFamily("Comic Sans MS");

      textRun3.setText("Length of Coastline: " + nationalPark.getLengthOfCoastlineInKilometers() + " kilometers");

      XSLFTextParagraph paragraph4 = box.addNewTextParagraph();

      XSLFTextRun textRun4 = paragraph4.addNewTextRun();

      textRun4.setFontColor(Color.white);
      textRun4.setFontSize(50.0);
      textRun4.setBold(true);
      textRun4.setItalic(true);
      textRun4.setFontFamily("Comic Sans MS");

      textRun4.setText("Main Settlements: " + nationalPark.getMainSettlements());

      try {
         File image = new File("c:\\Users\\lynn\\important\\uk\\nationalparks\\" + nationalPark.getName().replaceAll(" ","").toLowerCase() + ".jpg");

         byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

         XSLFPictureData data = powerpoint.addPicture(picture,XSLFPictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);

         shape.setAnchor(new Rectangle(100,500,750,370));
      } catch (Exception e) {
         System.out.println(e);
      }

      try {
         File image = new File("c:\\Users\\lynn\\important\\uk\\nationalparks\\" + nationalPark.getName().replaceAll(" ","").toLowerCase() + "map.jpg");

         byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

         XSLFPictureData data = powerpoint.addPicture(picture,XSLFPictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);

         shape.setAnchor(new Rectangle(900,500,488,970));
      } catch (Exception e) {
         System.out.println(e);
      }
      
      /*
      
      XSLFTextBox box1 = slide.createTextBox();

      if (motorway.getName().equalsIgnoreCase("m62"))
    	  box1.setAnchor(new Rectangle(50,500,2000,50));
      else
          box1.setAnchor(new Rectangle(50,350,2000,50));

      XSLFTextParagraph paragraph2 = box1.addNewTextParagraph();

      XSLFTextRun textRun2 = paragraph2.addNewTextRun();

      textRun2.setFontColor(Color.white);
      textRun2.setFontSize(50.0);
      textRun2.setBold(true);
      textRun2.setItalic(true);
      textRun2.setFontFamily("Comic Sans MS");

      textRun2.setText("Length: " + String.valueOf(motorway.getLengthInMiles()) + " miles");

      XSLFTextRun textRun2 = paragraph1.addNewTextRun();

      textRun2.setFontColor(Color.white);
      textRun2.setFontSize(50.0);
      textRun2.setBold(true);
      textRun2.setItalic(true);
      textRun2.setFontFamily("Comic Sans MS");

      textRun2.setText("Reign: " + monarch.reignBegin() + " - " + monarch.reignEnd() + "\n");

      XSLFTextRun textRun3 = paragraph1.addNewTextRun();

      textRun3.setFontColor(Color.white);
      textRun3.setFontSize(50.0);
      textRun3.setBold(true);
      textRun3.setItalic(true);
      textRun3.setFontFamily("Comic Sans MS");

      textRun3.setText("House: " + monarch.getHouse() + "\n");

      XSLFTextRun textRun4 = paragraph1.addNewTextRun();

      textRun4.setFontColor(Color.white);
      textRun4.setFontSize(50.0);
      textRun4.setBold(true);
      textRun4.setItalic(true);
      textRun4.setFontFamily("Comic Sans MS");

      textRun4.setText("Spouse(s): " + monarch.getSpouse());

      try {
         File image = new File("c:\\Users\\lynn\\important\\uk\\motorways\\" + motorway.getName().toLowerCase().replaceAll("\\s+","") + ".png");

         byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

         XSLFPictureData data = powerpoint.addPicture(picture,XSLFPictureData.PictureType.JPEG);

         XSLFPictureShape shape = slide.createPicture(data);

         shape.setAnchor(new Rectangle(1100,400,800,1100));
      } catch (Exception e) {
         try {
            File image = new File("c:\\Users\\lynn\\important\\uk\\motorways\\" + motorway.getName().toLowerCase().replaceAll("\\s+","") + ".jpg");

            byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

            XSLFPictureData data = powerpoint.addPicture(picture,XSLFPictureData.PictureType.JPEG);

            XSLFPictureShape shape = slide.createPicture(data);

            shape.setAnchor(new Rectangle(1100,400,800,1100));
         } catch (Exception e1) {
            System.out.println(e1);
         }

         System.out.println(e);
      }
      
      */

   }

   public void displayCountyTowns(XMLSlideShow powerpoint,
                                  XSLFSlideMaster slideMaster,
                                  String county,
                                  ArrayList<String> countyTowns) {
      XSLFSlideLayout slideLayout1 = slideMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ);

      XSLFSlide slide1 = powerpoint.createSlide(slideLayout1);

      clearTextShape(slide1.getPlaceholder(0));

      XSLFTextParagraph paragraph = slide1.getPlaceholder(0).addNewTextParagraph();

      XSLFTextRun run = paragraph.addNewTextRun();

      run.setFontColor(Color.white);
      run.setFontSize(90.0);
      run.setBold(true);
      run.setItalic(true);
      run.setFontFamily("Comic Sans MS");

      run.setText("Towns in " + county);

      clearTextShape(slide1.getPlaceholder(1));
      clearTextShape(slide1.getPlaceholder(2));
      clearTextShape(slide1.getPlaceholder(3));
      clearTextShape(slide1.getPlaceholder(4));      

      XSLFTable table = slide1.createTable();

      table.setAnchor(new Rectangle(20,200,2000,1350));

      for (int counter=0;counter<12;counter++) {
         XSLFTableRow row = table.addRow();

         row.setHeight(1150/11);

         for (int counter1=0;counter1<4;counter1++) {
            XSLFTableCell cell = row.addCell();

            XSLFTextParagraph paragraph3 = cell.addNewTextParagraph();
            
            paragraph3.setBullet(true);

            XSLFTextRun textRun3 = paragraph3.addNewTextRun();

            textRun3.setFontColor(Color.white);
            textRun3.setFontSize(45.0);
            textRun3.setBold(true);
            textRun3.setFontFamily("Lucida Bright");

            if (counter + 12*counter1 < countyTowns.size()) {
               String town = countyTowns.get(counter + 12*counter1);

               textRun3.setText(" " + town);
            } else {
               textRun3.setText("");
            }
         }
      }

      for (int counter=0;counter<4;counter++)
         if (counter == 1)
            table.setColumnWidth(counter,600);
         else
            table.setColumnWidth(counter,450);
   }
                             
   public void createUKCityPowerPoint(String fontFamily) {
      try {
         Scanner scanner = new Scanner(new FileInputStream("c:\\Users\\lynn\\important\\uk\\sourcefiles\\cities.txt")).useDelimiter("\\Z");

         String[] lines = scanner.next().split("\n");

         scanner.close();
         
         UKCity[] temp = UKCity.getCities();

         cities = new ArrayList<>();

         for (int counter=1;counter<temp.length;counter++)
            cities.add(temp[counter]);

         englishCities = UKCity.englishCities(cities);
         northernIrishCities = UKCity.northernIrishCities(cities);
         scottishCities = UKCity.scottishCities(cities);
         welshCities = UKCity.welshCities(cities);

         int englishcount = 0;
         int scottishcount = 0;
         int northernirishcount = 0;
         int welshcount = 0;

         for (UKCity city : cities) {
            if (city.english())
               englishcount++;

            if (city.scottish())
               scottishcount++;

            if (city.northernirish())
               northernirishcount++;

            if (city.welsh())
               welshcount++;
         }

         System.out.println(cities.size());
         System.out.println("English count: " + englishcount);
         System.out.println("Scottish count: " + scottishcount);
         System.out.println("Northern Irish count: " + northernirishcount);
         System.out.println("Welsh count: " + welshcount);
            
         XMLSlideShow powerpoint = new XMLSlideShow();

         powerpoint.setPageSize(new java.awt.Dimension(2048,1536));
      
         XSLFSlideMaster slideMaster = powerpoint.getSlideMasters().get(0);

         createMainTitle(powerpoint,slideMaster);

//         XSLFSlide title = getNewTitle(powerpoint,slideMaster);

//         XSLFSlide titleSlide = powerpoint.createSlide().importContent(title);

//         createTitleSlide(powerpoint,slideMaster,fontFamily,"UK Facts");
         
         displayEnglishCities(powerpoint,slideMaster);

         displayCities(powerpoint,slideMaster,englishCities);

         displayNorthernIrishCities(powerpoint,slideMaster);

         displayCities(powerpoint,slideMaster,northernIrishCities);

         displayScottishCities(powerpoint,slideMaster);

         displayCities(powerpoint,slideMaster,scottishCities);

         displayWelshCities(powerpoint,slideMaster);

         displayCities(powerpoint,slideMaster,welshCities);

         createTitleSlide(powerpoint,slideMaster,fontFamily,"English Counties");

         listEnglishCountiesByName(powerpoint,slideMaster);

         listEnglishCountiesByArea(powerpoint,slideMaster);

         displayEnglishCounties(powerpoint,slideMaster);      

         Connection connection = SQLite.createConnection("c:\\Users\\Public\\uk\\monarchs.sqlite");

         java.util.List<Monarch> monarchs = SQLite.getMonarchs(connection);

         createTitleSlide(powerpoint,slideMaster,fontFamily,"English Monarchs");

         for (Monarch monarch : monarchs)
            displayMonarch(powerpoint,slideMaster,monarch);

//         powerpoint.setSlideOrder(titleSlide,0);

         java.util.List<Motorway> motorways = new ArrayList<>();

         try {
            Scanner scanner1 = new Scanner(new FileInputStream("c:\\Users\\lynn\\important\\uk\\sourcefiles\\highways.txt")).useDelimiter("\\Z");

            String[] lines1 = scanner1.next().split("\r\n");

            scanner1.close();

            for (String line : lines1)
               if (!line.startsWith("Link"))
                  motorways.add(Motorway.process(line));
         } catch (Exception e) {
            System.out.println(e);
         }
         
         for (Motorway motorway : motorways) 
            displayMotorway(powerpoint,slideMaster,motorway);
            
         java.util.List<NationalPark> nationalParks = new ArrayList<>();
         
         try {
        	 Scanner scanner1 = new Scanner(new FileInputStream("c:\\Users\\lynn\\important\\uk\\sourcefiles\\nationalparks.txt")).useDelimiter("\\Z");
        	 
        	 String[] lines1 = scanner1.next().split("\r\n");
        	 
        	 scanner1.close();
        	 
        	 for (String line : lines1)
        		 if (!line.startsWith("National"))
        			 try {
           			    nationalParks.add(NationalPark.process(line)); 
        			 } catch (Exception e1) {
        				 System.out.println(e1);
        			 }
         } catch (Exception e) {
        	 System.out.println(e);
         }
         
         for (NationalPark nationalPark : nationalParks)
        	 displayNationalPark(powerpoint,slideMaster,nationalPark);
         
         java.util.List<Town> towns = new ArrayList<>();
         
         Map<String,ArrayList<String>> countyTowns = new TreeMap<>();
         
         try {
        	 Scanner scanner1 = new Scanner(new FileInputStream("c:\\Users\\lynn\\important\\uk\\sourcefiles\\englandtowns.txt")).useDelimiter("\\Z");
        	 
             String[] lines1 = scanner1.next().split("\n");
             
             for (String line : lines1) {
            	 String[] tokens = line.split(";");
            	 
            	 if (!tokens[0].equalsIgnoreCase("Town")) {
            	    if (tokens.length == 3)             	 
               	       towns.add(new Town(tokens[0],
            		  	                  tokens[1],
            			                  tokens[2]));
            	    else if (tokens.length == 2)
            		    towns.add(new Town(tokens[0],
            				               tokens[1],
            				               ""));
             	 }
             }
             
             for (Town town : towns) {
            	 ArrayList<String> list = countyTowns.get(town.getCounty());
            	 
            	 if (list == null)
            		 list = new ArrayList<>();
            	 
            	 list.add(town.getName());
            	 
            	 countyTowns.put(town.getCounty(), list);
             }
             
             for (String county : countyTowns.keySet()) {
            	 ArrayList<String> list = countyTowns.get(county);
            	 
            	 Collections.sort(list);
            	 
            	 countyTowns.put(county, list);
             }
        	 
         } catch (Exception e) {
        	 System.out.println(e);
         }
         
         for (String county : countyTowns.keySet()) {
        	 ArrayList<String> list = countyTowns.get(county);

        	 displayCountyTowns(powerpoint,
                                slideMaster,
                                county,
                                list);
         }         
         
         File file = new File("c:\\Users\\Public\\uk\\ukfacts.pptx");
         FileOutputStream stream = new FileOutputStream(file);

         powerpoint.write(stream);

         stream.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public static void main(String[] args) throws Exception {
      Powerpoint powerpoint = new Powerpoint("Powerpoint");

      powerpoint.setSize(500,500);
      powerpoint.setLocationRelativeTo(null);
      powerpoint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      powerpoint.setVisible(true);
   }
}
