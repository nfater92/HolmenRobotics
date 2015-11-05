import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login implements ActionListener {
	private Database db;
	private JFrame win;
	private JTextField t;
	private JPasswordField p;
	private boolean badpass;
	
	public Login(Database d){
		db = d;
		badpass = false;
		createWindow();
	}
	private void createWindow(){
		win = new JFrame();
		win.setLayout(null);
		win.setVisible(true);
		win.setBounds(350,100,400,350);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel un = new JLabel("Username:");
		un.setBounds(100,100,100,50);
		JLabel pw = new JLabel("Password:");
		pw.setBounds(100,150,100,50);
		JLabel banner = new JLabel("Holmen Highschool Robotics Club");
		banner.setBounds(50,25,300,50);
		banner.setFont(new Font("Calibri",0,21));
		
		t = new JTextField();
		t.setBounds(200,115,100,25);
		
		p = new JPasswordField();
		p.setBounds(200,165,100,25);
		p.addActionListener(this);
		p.setActionCommand("enter");
		
		JButton b = new JButton("Login");
		b.setBounds(150,250,100,25);
		b.addActionListener(this);
		b.setActionCommand("enter");
		
		win.add(banner);
		win.add(p);
		win.add(t);
		win.add(b);
		win.add(pw);
		win.add(un);
		win.repaint();
		
	}
	
	//displays error message for unknown username or incorrect password
	private void unknownUser(){
		if(!badpass){
			badpass = true;
			JLabel unknown = new JLabel("Incorrect username or password.");
			JLabel unknown2 = new JLabel("Please try again or contact your administrator.");
			unknown.setBounds(50,65,300,25);
			unknown2.setBounds(50,80,300,25);
			unknown.setForeground(Color.red);
			unknown2.setForeground(Color.red);
			win.add(unknown);
			win.add(unknown2);
			win.repaint();
		}
	}
	
	private void enter() throws SQLException, Exception{
		// dispose of login window boolean
		boolean disp = true;
		
		//check username and password and returns usertype if correct, -1 for incorrect
		switch(db.validate(t.getText(),p.getPassword())){
			case(-1):
			// display error msg
				unknownUser();
			// do not dispose of login screen
				disp = false;
				break;
			case(0):
				new Admin(t.getText(),db);
				break;
			case(1):
				new Student(t.getText(),db);
				break;
			case(2):
				new Volunteer(t.getText(),db);
				break;
			case(3):
				new Sponsor(t.getText(),db);
				break;
		}
		// if correct password then window is disposed
		if(disp)win.dispose();
	}
	
	// when login is pressed or enter is pressed in passwordfield
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("enter")){
			try {
				enter();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}
