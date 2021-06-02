package etf.openpgp.sr170398dsl170423d.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import etf.openpgp.sr170398dsl170423d.impl.RingOutput;



public class PrikazPrstena {
	
	Projekat p;
	JFrame frame;
	private JScrollPane scrollPane;
	private JTable table,table2;
	private JScrollPane sp_1;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Create the application.
	 */
	public PrikazPrstena(Projekat p) {
		this.p = p;
		initialize();	
	}

	private void resizeForm(JFrame myFrame, JTable t) {	
		//myFrame.pack();
		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();	
		myFrame.setSize(size.width * 60/100,size.height*60/100);
		myFrame.setVisible(true);
		myFrame.setLocationRelativeTo(null);
		myFrame.setResizable(true);
		
		
}
/**
 * Initialize the contents of the frame.
 */
private void initialize() {
	// Frame initiallization
    frame = new JFrame();
    frame.setBackground(Color.LIGHT_GRAY);
    frame.setTitle("JTable Example");
    
    ArrayList<RingOutput> ret = p.b.showPrivateKeyRingCollection();
	String[][] dataPrivateKeys=new String[ret.size()][5];
	for(int i = 0; i<ret.size();i++)
	{
		String[] split =  ret.get(i).toString().split(" ");
		for(int j = 0; j<5;j++)
		{
			dataPrivateKeys[i][j] = split[j];
		}
	}
	

    // Column Names
    String[] columnNames = { "Name", "Email", "Valid From","Valid Until","Key ID" };
    
    // Initializing the JTable
    table= new JTable(dataPrivateKeys, columnNames);
    table.setFillsViewportHeight(true);
    table.setBounds(30, 40, 400, 300);
    
    ArrayList<RingOutput> ret2 = p.b.showPublicKeyRingCollection();
   	String[][] dataPublicKeys=new String[ret2.size()][5];
   	for(int i = 0; i<ret2.size();i++)
   	{
   		String[] split =  ret2.get(i).toString().split(" ");
   		for(int j = 0; j<5;j++)
   		{
   			dataPublicKeys[i][j] = split[j];
   		}
   	}
   	
   	
    table2= new JTable(dataPublicKeys, columnNames);
    table2.setBackground(Color.WHITE);
    table2.setBounds(30, 40, 400, 300);

    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
      
    // adding it to JScrollPane
    JScrollPane sp = new JScrollPane(table);
    frame.getContentPane().add(sp);
    frame.getContentPane().add(sp);
    // Frame Size
    frame.setSize(1000, 200);
    
    
   
    // adding it to JScrollPane
    frame.getContentPane().add(sp);
    
    panel = new JPanel();
    panel.setBackground(Color.LIGHT_GRAY);
    frame.getContentPane().add(panel);
    panel.setLayout(null);
    
    lblNewLabel = new JLabel("PRIVATNI ->");
    lblNewLabel.setBounds(0, 0, 78, 203);
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
    panel.add(lblNewLabel);
    
    lblNewLabel_2 = new JLabel("KLJUCEVI");
    lblNewLabel_2.setBounds(0, 203, 78, 203);
    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel_2);
    
    lblNewLabel_1 = new JLabel("<-JAVNI");
    lblNewLabel_1.setBounds(0, 406, 78, 203);
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
    panel.add(lblNewLabel_1);
    
    sp_1 = new JScrollPane(table2);
    frame.getContentPane().add(sp_1);
    
    resizeForm(frame,null);
}
}
