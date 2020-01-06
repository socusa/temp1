import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.poi.util.IOUtils;

public class UpdateImage extends JFrame implements ActionListener {
	private JLabel cityLabel;
	private JComboBox cities;
	private JLabel newImageLabel;
	private JTextField newImage;
	private JButton setNewImage;
	private Connection connection;
	private JButton paste;
	private JButton clear;
		
	public UpdateImage(String title) {
		super(title);
		
	    String url = "jdbc:sqlite:C:/users/public/databases/uk.sqlite";

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
	    
	    java.util.List<String> temp1 = new ArrayList<>();
	    
	    String temp = "SELECT * FROM cities";
	    
	    try {
	    	PreparedStatement statement = connection.prepareStatement(temp);
	    	
	    	ResultSet rs = statement.executeQuery();
	    	
	    	while(rs.next())
	    		temp1.add(rs.getString("City"));
	    } catch (SQLException se) {
	    	System.out.println(se);
	    }
	    
	    setLayout(new FlowLayout());
	    
	    add(cityLabel = new JLabel("City"));
	    add(cities = new JComboBox(temp1.toArray(new String[temp1.size()])));
	    
	    cityLabel.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	    cities.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	    
	    JPanel panel = new JPanel();
	    
	    panel.add(newImageLabel = new JLabel("New Image"));
	    panel.add(newImage = new JTextField());
	    
	    newImageLabel.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        newImage.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	    
	    panel.setPreferredSize(new Dimension(700,40));
	    
	    add(panel);
	    
	    newImage.setPreferredSize(new Dimension(500,40));
	    
	    add(paste = new JButton("Paste"));
	    
	    paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				newImage.paste();				
			}
	    	
	    });
	    
	    add(clear = new JButton("Clear"));
	    
	    clear.addActionListener(this);;
	    
	    add(setNewImage = new JButton("Set New Image"));
	    
	    setNewImage.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	    
	    setNewImage.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getSource() == setNewImage) {
			String cityName = cities.getSelectedItem().toString();
			
			String newImage = this.newImage.getText();
			
			String line = "UPDATE cities SET Image=? WHERE CITY=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(line);
				
				statement.setString(1,newImage);
				statement.setString(2,cityName);
				
				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			try {
				byte[] bytes = IOUtils.toByteArray(new URL(newImage).openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getSource() == clear)
			newImage.setText("");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UpdateImage updateImage = new UpdateImage("Update Image");
		
		updateImage.setSize(800,400);
		updateImage.setLocationRelativeTo(null);
		updateImage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateImage.setVisible(true);
	}

}
