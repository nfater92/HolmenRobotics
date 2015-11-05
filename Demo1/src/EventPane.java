import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class EventPane extends JPanel {

	private int contentScope;
	private ArrayList<Event> events;
	private Database db;
	private MouseListener actList;

	public EventPane(int contentScope, Database db, MouseListener actList)
			throws SQLException {
		this.contentScope = contentScope;// 0 for pending, 1 for approved, -1
											// for declined, 2 for all
		this.db = db;
		this.actList = actList;
		setLayout(null);
		loadList();
		loadContents();
		setPreferredSize(new Dimension(300, 10000));
	}

	private void loadList() throws SQLException {
		events = db.fillEventList();
	}

	private void loadContents() {
		int i = 0;
		if (contentScope == 2) {
			for (Event e : events) {
				MyLabel l = new MyLabel(e.event + " at " + e.time + " on "
						+ e.date + " : " + e.description);
				l.id(e.id);
				l.setBounds(0, 25 * i, 400, 25);
				l.addMouseListener(actList);
				i++;
				if (i % 2 == 0)
					l.setBackground(Color.blue.LIGHT_GRAY);
				add(l);
			}
		} else if (contentScope == 1) {
			for (Event e : events) {
				if (e.status == 1) {
					MyLabel l = new MyLabel(e.event + " at " + e.time + " on "
							+ e.date + " : " + e.description);
					l.id(e.id);
					l.setBounds(0, 25 * i, 400, 25);
					l.addMouseListener(actList);
					i++;
					if (i % 2 == 0)
						l.setBackground(Color.blue.LIGHT_GRAY);
					add(l);
				}
			}
		} else if (contentScope == 0) {
			for (Event e : events) {
				if (e.status == 0) {
					MyLabel l = new MyLabel(e.event + " at " + e.time + " on "
							+ e.date + " : " + e.description);
					l.id(e.id);
					l.setBounds(0, 25 * i, 400, 25);
					l.addMouseListener(actList);
					i++;
					if (i % 2 == 0)
						l.setBackground(Color.blue.LIGHT_GRAY);
					add(l);
				}
			}
		} else if (contentScope == -1) {
			for (Event e : events) {
				if (e.status == -1) {
					MyLabel l = new MyLabel(e.event + " at " + e.time + " on "
							+ e.date + " : " + e.description);
					l.id(e.id);
					l.setBounds(0, 25 * i, 400, 25);
					l.addMouseListener(actList);
					i++;
					if (i % 2 == 0)
						l.setBackground(Color.blue.LIGHT_GRAY);
					add(l);
				}
			}
		} else {
		}
		i = 0;
		repaint();

	}
}
