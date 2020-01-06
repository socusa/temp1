package music;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import static music.CreatingMusic.myAdjustmentListener;

public class MeasureMonitor extends JFrame implements ActionListener {
	public String message;
	public int trebleTime;
	public int bassTime;
	public JTable table;
	public JButton stop;
	public JButton releaseAdjustmentListener;
	public JScrollPane pane;
	
	public MeasureMonitor(String title) {
		message = "";
		
		setSize(700,400);
		
		setLayout(new FlowLayout());
		
		String[] columnNames = {"Measure","Treble Time","Bass Time"};
		
		Object[][] data = {{"","",""}};
		
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		
		table = new JTable(model);
		
		table.setRowHeight(40);
		
		table.setFont(new java.awt.Font("Comic Sans MS",java.awt.Font.BOLD,20));
		
		pane = new JScrollPane(table);
		
		pane.getVerticalScrollBar().addAdjustmentListener(myAdjustmentListener);
		
		table.setFillsViewportHeight(true);
		
		JTableHeader header = table.getTableHeader();

		header.setFont(new java.awt.Font("Comic Sans MS",java.awt.Font.BOLD,20));
		
		model = (DefaultTableModel)table.getModel();
		
		model.removeRow(0);
		
		pane.setPreferredSize(new Dimension(600,200));
		
		JPanel panel1 = new JPanel();
		
		panel1.setPreferredSize(new Dimension(700,50));
		
		JPanel panel = new JPanel();
		
		panel.add(stop = new JButton("Stop"));
		
		stop.addActionListener(this);
		
		panel.add(releaseAdjustmentListener = new JButton("Release Adjustment Listener"));
		
		releaseAdjustmentListener.addActionListener(this);
		
		panel.setPreferredSize(new Dimension(700,50));
		
		add(panel1);
		
		add(panel);
		
		add(pane);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);		
	}
	
	public void setMessage(int measure,
			               int trebleTime,
			               int bassTime) {
		this.message = message;
		this.trebleTime = trebleTime/CreatingMusic.factor;
		this.bassTime = bassTime/CreatingMusic.factor;
		
		message = "Measure " + measure;

        DefaultTableModel model = (DefaultTableModel)table.getModel();
        
        model.addRow(new Object[]{String.valueOf(measure),
        		                  String.valueOf(this.trebleTime),
        		                  String.valueOf(this.bassTime)});
               
		repaint();
	}
	
	public void setTrebleAndBassTime(int trebleTime,
			                         int bassTime) {
        this.trebleTime = trebleTime;
        this.bassTime = bassTime;
        
        repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		
		FontMetrics metrics = g.getFontMetrics();
		
		int width = metrics.stringWidth(message);
		
		int height = metrics.getHeight();
		
		g.drawString(message, 350 - width/2, 55);		

		String message1 = "Treble Time: " + trebleTime + " Bass Time: " + bassTime;
		
        width = metrics.stringWidth(message1);
        
        g.drawString(message1, 350 - width/2, 80);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == stop)
			music.CreatingMusic.player.stop();
		else if (e.getSource() == releaseAdjustmentListener) {
			pane.getVerticalScrollBar().removeAdjustmentListener(myAdjustmentListener);
			
			releaseAdjustmentListener.setVisible(false);
		}
	}

}
