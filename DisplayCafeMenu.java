package testmain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

class MenuPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTable menuTable;
	private DbConnect dbConnect;
	private DefaultTableModel dtm;
	private JScrollPane scrollpane;
	private JTextArea jTextArea;
	
	/**
	 * @throws SQLException
	 */
	public MenuPanel() throws SQLException{
		panel = new JPanel(new BorderLayout());
		dbConnect = new DbConnect("(select * from food_item where fooditem_id in(select item_id from cafe_menu cm where cafe_id = 'C01'))"
				+ "union (select * from drink_item where drinkitem_id in(select item_id from cafe_menu cm where cafe_id = 'C01'))");
		dtm = new DefaultTableModel();
		/*jTextArea = new JTextArea();
		jTextArea.setText(dbConnect.displayData());
		add(panel);
		panel.add(jTextArea);*/
		add(panel);
		dtm = dbConnect.displayMenuTable();
		menuTable = new JTable(dtm);
		menuTable.setVisible(true);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//menuTable.setSize(screenSize.width,screenSize.height);
		menuTable.setFillsViewportHeight(true);
		menuTable.setAutoResizeMode(4);;
		//menuTable.getColumnModel().getColumn(4).setPreferredWidth(1000);
		scrollpane = new JScrollPane(menuTable);
		scrollpane.setVisible(true);
		panel.add(scrollpane);
	}
}

public class DisplayCafeMenu {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("Campus Smart Cafe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 // make the frame 1/4 the height and width
		
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	int height = screenSize.height;
    	int width = screenSize.width;
    	frame.setSize(width/2, height/2);

    	// center the mainFrame on screen
    	frame.setLocationRelativeTo(null);
    	
    	MenuPanel menuPanel = new MenuPanel();
    	frame.getContentPane().add(menuPanel);
    	frame.setVisible(true);

	}

}
