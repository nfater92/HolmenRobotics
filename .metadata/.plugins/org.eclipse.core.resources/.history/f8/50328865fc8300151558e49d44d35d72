import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


public class Database{
	Connection c;
	Statement stmt;
	public Database() throws SQLException{
		// connect to database
		c = null;
	    stmt = null;
	 	try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:HolmenRobots.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	createTables();
	}
	private void createTables() throws SQLException{
	 
			stmt = c.createStatement();
		    String sql = "CREATE TABLE IF NOT EXISTS USER " +
		                   "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
		                   " FIRSTNAME      TEXT    NOT NULL, " +
		                   " LASTNAME		TEXT	NOT NULL, " +
		                   " USERNAME       TEXT    NOT NULL, " +  
		                   " PASSWORD       TEXT    NOT NULL, " + 
		                   " USERTYPE       INT    NOT NULL)"; 
		    stmt.executeUpdate(sql);
		    System.out.println("Table USER created successfully");
		    
		    stmt = c.createStatement();
			sql = "INSERT OR IGNORE INTO USER (FIRSTNAME,LASTNAME,USERNAME,PASSWORD,USERTYPE) " +
					"VALUES ('Paul','Smith', 'psmith', 'pass1', 0 );"; 
			stmt.executeUpdate(sql);
	
			sql = "INSERT OR IGNORE INTO USER (FIRSTNAME,LASTNAME,USERNAME,PASSWORD,USERTYPE) " +
					"VALUES ('Allen','Kent', 'akent', 'pass2', 2 );"; 
			stmt.executeUpdate(sql);
	
			sql = "INSERT OR IGNORE INTO USER (FIRSTNAME,LASTNAME,USERNAME,PASSWORD,USERTYPE) " +
					"VALUES ('Teddy', 'Dog', 'tdog', 'pass3', 1 );"; 
			stmt.executeUpdate(sql);
	
			sql = "INSERT OR IGNORE INTO USER (FIRSTNAME,LASTNAME,USERNAME,PASSWORD,USERTYPE) " +
					"VALUES ('Mark', 'Third', 'mkiii', 'pass4', 3);"; 
			stmt.executeUpdate(sql);
			
			
			stmt = c.createStatement();
		    sql = "CREATE TABLE IF NOT EXISTS EVENT " +
		                   "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
		                   " USERNAME       TEXT    NOT NULL, " +  
		                   " EVENT          TEXT    NOT NULL, " + 
		                   " DATE           TEXT    NOT NULL, " +
		                   " TIME           TEXT    NOT NULL, " +
		                   " LOCATION       TEXT    NOT NULL, " +
		                   " DESCRIPTION    TEXT    NOT NULL, " +
		                   " DETAILS        TEXT    NOT NULL, " +
		                   " USERTYPE       INT     NOT NULL, " +
		                   " STATUS         INT     NOT NULL, " +
		                   " TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP)"; 
		    stmt.executeUpdate(sql);
		    System.out.println("Table EVENT created successfully");
	
			stmt.close();
			c.commit();
		}
	
	public boolean addRequest(Event event) throws SQLException{
		stmt = c.createStatement();
		String sql = "INSERT OR IGNORE INTO EVENT (USERNAME,EVENT,DATE,TIME,LOCATION,DESCRIPTION,DETAILS,USERTYPE,STATUS) " +
				"VALUES ('"+event.username+"','"+ event.event+"','"+event.date+"','"+
				event.time+"','"+event.location+"','"+event.description+"','"+event.details+"',"+event.usertype+","+0+");"; 
		System.out.println(sql);
		stmt.executeUpdate(sql);
		stmt.close();
		c.commit();
		return true;
	}
	

	public int validate(String username, char[] pass) throws Exception{
	// database get password for "username"
		stmt = c.createStatement();
		String str = "SELECT PASSWORD FROM USER WHERE USERNAME='"+username+"';";
		ResultSet rs = stmt.executeQuery(str);
	//create char[] for password
		if(!rs.isClosed()){
			char[] c = rs.getString("password").toCharArray();
		// if c == pass return usertype of "username"
			if(Arrays.equals(c,pass)){
				//clear char[] for security
				Arrays.fill(c, '0');
				return getUserType(username);
			}
		}
		//return -1 if pass is incorrect
		return -1;
	}
	
	public int getUserType(String username) throws SQLException{
	//default to -1, (no user type)// admin = 0 , student = 1, volunteer = 2, sponsor = 3
		int t = -1;
		stmt = c.createStatement();
		String str = "SELECT USERTYPE FROM USER WHERE USERNAME='"+username+"';";
		ResultSet rs = stmt.executeQuery(str);
		t = rs.getInt("usertype");
	//return usertype int
		return t;
	}
	
	public String[] getName(String username) throws SQLException{
	//get "username" first and last name from database
		String[] s = new String[2];
		stmt = c.createStatement();
		String str = "SELECT FIRSTNAME FROM USER WHERE USERNAME='"+username+"';";
		ResultSet rs = stmt.executeQuery(str);
		s[0]=rs.getString("firstname");
		str = "SELECT LASTNAME FROM USER WHERE USERNAME='"+username+"';";
		rs = stmt.executeQuery(str);
		s[1]=rs.getString("lastname");
	// return [firstname,lastname]
		return s;
	}
	
	public ArrayList<Event> fillEventList() throws SQLException{
		ArrayList<Event> list = new ArrayList<Event>();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT");
		while(rs.next()){
			Event e = new Event();
			e.event = rs.getString("event");
			e.username = rs.getString("username");
			e.date = rs.getString("date");
			e.time = rs.getString("time");
			e.location = rs.getString("location");
			e.description = rs.getString("description");
			e.details = rs.getString("details");
			e.usertype = rs.getInt("usertype");
			e.status = rs.getInt("status");
			e.id = rs.getInt("id");
			e.timeStamp = rs.getString("timestamp");
			list.add(e);
		}
		
		return list;
	}
	
}
