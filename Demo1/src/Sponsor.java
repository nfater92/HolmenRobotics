import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Sponsor implements ActionListener {

	private Database db;
	private String fullName;
	private String username;
	private static final int usertype = 3;
	private ArrayList<Component> win;
	private JFrame frame;
	private JTextField txtRequest;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtLocation;
	private JTextField txtDescription;
	private JTextField txtDetails;
	
	public Sponsor(String s,Database d) throws SQLException{
		db = d;
		win = new ArrayList<Component>();
		username = s;
		String[] name = db.getName(s);
		fullName = name[0]+" "+name[1];
		createWindow();
	}
	private void createWindow(){
		frame = new JFrame(fullName +" -- Sponsor");
		frame.setBounds(300,200,600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		JButton btnCal= new JButton("Calendar");
		btnCal.setBounds(15,125,100,25);
		frame.add(btnCal);
		JButton btnReq= new JButton("Request Event");
		btnReq.setBounds(15,65,100,25);
		frame.add(btnReq);
		JButton btnDon= new JButton("Donate");
		btnDon.setBounds(15,95,100,25);
		frame.add(btnDon);
		JButton btnAccnt = new JButton("My Account");
		btnAccnt.setBounds(15,35,100,25);
		frame.add(btnAccnt);
		JButton btnlogout = new JButton("Log out");
		btnlogout.setBounds(15, 330, 100, 20);
		btnlogout.addActionListener(this);
		btnlogout.setActionCommand("logout");
		frame.add(btnlogout);
		
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
		lblInfo.setBounds(5,35,150,350);
		lblInfo.setFont(new Font("Calibri",0,12));
		win.add(lblInfo);
		
		txtRequest = new JTextField();
		txtRequest.setBounds(300,25,200,25);
		win.add(txtRequest);
		JLabel lblRequest = new JLabel("Name of Event: ");
		lblRequest.setBounds(200,25,200,25);
		win.add(lblRequest);
		
		txtDate = new JTextField();
		txtDate.setBounds(300,65,200,25);
		win.add(txtDate);
		JLabel lblDate = new JLabel("Date of Event: ");
		lblDate.setBounds(200,65,200,25);
		win.add(lblDate);
		
		txtTime = new JTextField();
		txtTime.setBounds(300,105,200,25);
		win.add(txtTime);
		JLabel lblTime = new JLabel("Time of Event: ");
		lblTime.setBounds(200,105,200,25);
		win.add(lblTime);
		
		txtLocation = new JTextField();
		txtLocation.setBounds(300,145,200,25);
		win.add(txtLocation);
		JLabel lblLocation = new JLabel("Location: ");
		lblLocation.setBounds(200,145,200,25);
		win.add(lblLocation);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(300,185,200,25);
		win.add(txtDescription);
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(200,185,200,25);
		win.add(lblDescription);
		
		txtDetails = new JTextField();
		txtDetails.setBounds(300,225,200,25);
		win.add(txtDetails);
		JLabel lblDetails = new JLabel("Other Details: ");
		lblDetails.setBounds(200,225,200,25);
		win.add(lblDetails);
		
//		JTextField  = new JTextField();
//		.setBounds(300,265,200,25);
//		win.add();
//		JLabel lbl = new JLabel("dddd: ");
//		lbl.setBounds(200,265,200,25);
//		win.add(lbl);
		
		
		JButton addEvent = new JButton("Add Event");
		addEvent.setBounds(350,300,100,25);
		addEvent.addActionListener(this);
		addEvent.setActionCommand("addEvent");
		win.add(addEvent);
		
		addWin();
	}
	
	private void createRequest() throws SQLException{
		Event event = new Event();
		event.date=txtDate.getText();
		event.description=txtDescription.getText();
		event.details=txtDetails.getText();
		event.time=txtTime.getText();
		event.location=txtLocation.getText();
		event.event=txtRequest.getText();
		event.username=username;
		event.usertype=usertype;
		db.addRequest(event);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("addEvent")){
			try {
				createRequest();
				txtDate.setText("");
				txtDescription.setText("");
				txtDetails.setText("");
				txtTime.setText("");
				txtLocation.setText("");
				txtRequest.setText("");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("logout")){
			new Login(db);
			frame.dispose();
		}
		
	}
}
