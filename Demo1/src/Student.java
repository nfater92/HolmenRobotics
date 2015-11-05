import java.sql.SQLException;

import javax.swing.JFrame;


public class Student {

	private Database db;
	private String username;
	private String fullName;
	private static final int usertype = 1;
	
	public Student(String s,Database d) throws SQLException{
		db = d;
		username = s;
		String[] name = db.getName(s);
		fullName = name[0]+" "+name[1];
		createWindow();
	}
	private void createWindow(){
		JFrame win = new JFrame(fullName +" -- Student");
		win.setBounds(300,200,600,400);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLayout(null);
		win.setVisible(true);
		
		
		
		
		win.repaint();
	}
}
