import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Volunteer {

	private Database db;
	private String username;
	private String fullName;
	private static final int usertype = 2;
	private ArrayList<Component> win;
	private JFrame frame;
	
	public Volunteer(String s,Database d) throws SQLException{
		db = d;
		win = new ArrayList<Component>();
		username = s;
		String[] name = db.getName(s);
		fullName = name[0]+" "+name[1];
		createWindow();
	}
	private void createWindow(){
		frame = new JFrame(fullName +" -- Volunteer");
		frame.setBounds(300,200,600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
//		JButton btnCal= new JButton("Calendar");
//		JButton btnReq= new JButton("Request Event");
//		JButton btnDon= new JButton("Donate");
//		JButton btnAccnt = new JButton("My Account");
		requestWindow();
		
		
		
		frame.repaint();
	}
	
	private void addWin(){
		for(Component c:win){
			frame.add(c);
		}
		frame.repaint();
	}
	
	private void clearWin(){
		while(!win.isEmpty()){
			frame.remove(win.remove(0));
		}
	}
	
	private void requestWindow(){
		JLabel lblpage= new JLabel("Requests");
		lblpage.setBounds(5,0,100,35);
		lblpage.setFont(new Font("Calibri",0,21));
		win.add(lblpage);
		
		JLabel lblInfo= new JLabel("<html>Please enter as much detail as possible. If the field does not "
				+"fit your request just leave it blank. Thank you for commitment to Holmen FIRST Robotics.</html>");
		lblInfo.setBounds(5,40,100,350);
		lblInfo.setFont(new Font("Calibri",0,12));
		win.add(lblInfo);
		
//		JTextField txtRequest = new JTextField();
//		txtRequest.setBounds(,,,);
//		win.add(txtRequest);
		JLabel lblRequest = new JLabel("Name of Event: ");
		lblRequest.setBounds(150,25,200,25);
		win.add(lblRequest);
		
//		JTextField txtDate = new JTextField();
//		txtDate.setBounds(,,,);
//		win.add(txtDate);
		JLabel lblDate = new JLabel("Date of Event: ");
		lblDate.setBounds(150,65,200,25);
		win.add(lblDate);
		
//		JTextField  = new JTextField();
//		.setBounds(,,,);
//		win.add();
		JLabel lblTime = new JLabel("Time of Event: ");
		lblTime.setBounds(150,105,200,25);
		win.add(lblTime);
		
//		JTextField  = new JTextField();
//		.setBounds(,,,);
//		win.add();
		JLabel lblLocation = new JLabel("Location: ");
		lblLocation.setBounds(150,145,200,25);
		win.add(lblLocation);
		
//		JTextField  = new JTextField();
//		.setBounds(,,,);
//		win.add();
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(150,185,200,25);
		win.add(lblDescription);
		
//		JTextField  = new JTextField();
//		.setBounds(,,,);
//		win.add();
		JLabel lblDetails = new JLabel("Other Details: ");
		lblDetails.setBounds(150,225,200,25);
		win.add(lblDetails);
		
//		JTextField  = new JTextField();
//		.setBounds(,,,);
//		win.add();
//		JLabel lbl = new JLabel("dddd: ");
//		lbl.setBounds(150,265,200,25);
//		win.add(lbl);
		
		
		JButton addEvent = new JButton("Add Event");
		addEvent.setBounds(250,300,100,25);
		win.add(addEvent);
		
		addWin();
	}
}
