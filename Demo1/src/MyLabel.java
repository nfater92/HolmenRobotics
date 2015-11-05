import javax.swing.JLabel;


public class MyLabel extends JLabel{
	private int id;
	public MyLabel(String s){
		setText(s);
	}
	public int id(){
		return id;
	}
	public void id(int id){
		this.id = id;
	}
}