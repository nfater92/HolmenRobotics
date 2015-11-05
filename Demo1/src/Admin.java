import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Admin implements ActionListener, MouseListener {

	private ArrayList<Component> win;
	private JFrame frame;
	private Database db;
	private String username;
	private String fullName;
	private int scope;
	public static final int usertype = 0;
	private JTextField txtRequest;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtLocation;
	private JTextField txtDescription;
	private JTextField txtDetails;

	// Constructor takes String s which is the login username and a database
	// class
	public Admin(String s, Database d) throws SQLException {
		win = new ArrayList<Component>();
		db = d;
		username = s;
		scope = 2;
		String[] name = db.getName(s);
		fullName = name[0] + " " + name[1];
		createWindow();
		viewRequests();
	}

	// creates the basic layou of the GUI
	private void createWindow() {
		frame = new JFrame(fullName + " -- Admin");
		frame.setBounds(300, 200, 600, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);

		JButton btnreq = new JButton("Requests");
		btnreq.setBounds(10, 125, 100, 25);
		btnreq.addActionListener(this);
		btnreq.setActionCommand("requests");
		JButton btnuser = new JButton("Users");
		btnuser.setBounds(10, 65, 100, 25);
		btnuser.addActionListener(this);
		btnuser.setActionCommand("users");
		JButton btncal = new JButton("Calendar");
		btncal.setBounds(10, 95, 100, 25);
		btncal.addActionListener(this);
		btncal.setActionCommand("cal");
		JButton btnacct = new JButton("My Account");
		btnacct.setBounds(10, 35, 100, 25);
		btnacct.addActionListener(this);
		btnacct.setActionCommand("accnt");
		JButton btnlogout = new JButton("Log out");
		btnlogout.setBounds(10, 355, 100, 20);
		btnlogout.addActionListener(this);
		btnlogout.setActionCommand("logout");
		frame.add(btnlogout);
		frame.add(btnreq);
		frame.add(btnuser);
		frame.add(btncal);
		frame.add(btnacct);

		frame.repaint();
	}

	// adds all components in the list to the jframe
	private void addWin() {
		for (Component c : win) {
			frame.add(c);
		}
		frame.repaint();
	}

	// clears all components from the jframe and empties the list
	private void clearWin() {
		while (!win.isEmpty()) {
			frame.remove(win.remove(0));
		}
	}

	// shows pending requests and accepted requests
	private void viewRequests() throws SQLException {
		JLabel lblPage = new JLabel("Request");
		lblPage.setBounds(5, 0, 100, 35);
		lblPage.setFont(new Font("Calibri", 0, 21));
		win.add(lblPage);

		JButton btnApproved = new JButton("Approved");
		btnApproved.setBounds(10, 195, 100, 25);
		btnApproved.addActionListener(this);
		btnApproved.setActionCommand("approved");
		win.add(btnApproved);

		JButton btnAll = new JButton("View All");
		btnAll.setBounds(10, 285, 100, 25);
		btnAll.addActionListener(this);
		btnAll.setActionCommand("all");
		win.add(btnAll);

		JButton btnPending = new JButton("Pending");
		btnPending.setBounds(10, 225, 100, 25);
		btnPending.addActionListener(this);
		btnPending.setActionCommand("pending");
		win.add(btnPending);

		JButton btnCreateEvent = new JButton("New Event");
		btnCreateEvent.setBounds(10, 165, 100, 25);
		btnCreateEvent.addActionListener(this);
		btnCreateEvent.setActionCommand("createEvent");
		win.add(btnCreateEvent);

		JButton btnDeclined = new JButton("Declined");
		btnDeclined.setBounds(10, 255, 100, 25);
		btnDeclined.addActionListener(this);
		btnDeclined.setActionCommand("declined");
		win.add(btnDeclined);

		EventPane ep = new EventPane(scope, db, this);
		JScrollPane jsp = new JScrollPane(ep);
		jsp.setBounds(150, 25, 400, 350);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		win.add(jsp);

		// adds all components to the window
		addWin();
	}

	// shows current users and their info
	private void viewUsers() {
		JLabel h = new JLabel("Users");
		h.setBounds(5, 0, 100, 35);
		h.setFont(new Font("Calibri", 0, 21));
		win.add(h);

		// adds all components to the window
		addWin();
	}

	// shows the calendar admin screen
	private void viewCalendar() {
		JLabel h = new JLabel("Calendar");
		h.setBounds(5, 0, 100, 35);
		h.setFont(new Font("Calibri", 0, 21));
		win.add(h);
		Calendar cal = new Calendar();

		// adds all components to the window
		addWin();
	}

	// shows personal account info
	private void viewAccount() {
		JLabel h = new JLabel("My Account");
		h.setBounds(5, 0, 150, 35);
		h.setFont(new Font("Calibri", 0, 21));
		win.add(h);

		// adds all components to the window
		addWin();
	}

	// create new event window
	private void newEventWindow(){
		JLabel lblpage= new JLabel("Requests");
		lblpage.setBounds(5,0,100,35);
		lblpage.setFont(new Font("Calibri",0,21));
		win.add(lblpage);
				
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
		event.status=1;
		db.addRequest(event);
	}
	
	// handle buttons and user actions
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("users")) {
			clearWin();
			viewUsers();
		}
		if (e.getActionCommand().equals("createEvent")) {
			clearWin();
			newEventWindow();
		}
		if (e.getActionCommand().equals("requests")) {
			clearWin();
			try {
				viewRequests();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("cal")) {
			clearWin();
			viewCalendar();
		}
		if (e.getActionCommand().equals("accnt")) {
			clearWin();
			viewAccount();
		}
		if (e.getActionCommand().equals("declined")) {
			scope = -1;
			clearWin();
			try {
				viewRequests();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("approved")) {
			scope = 1;
			clearWin();
			try {
				viewRequests();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("pending")) {
			scope = 0;
			clearWin();
			try {
				viewRequests();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("all")) {
			scope = 2;
			clearWin();
			try {
				viewRequests();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("logout")){
			new Login(db);
			frame.dispose();
		}
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

	}

	// add user
	private void addUser() {

	}

	// remove user
	private void removeUser() {

	}

	// edit user
	private void editUser() {

	}

	// change password
	private void changePassword() {

	}

	// approve request
	private void approveRequest() {

	}

	// deny request
	private void denyRequest() {

	}

	// create calendar
	private void createCalendar() {

	}

	// edit calendar
	private void editCalendar() {

	}

	// remove calendar
	private void deleteCalendar() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		int i = ((MyLabel) e.getComponent()).id();
		System.out.println(i);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
