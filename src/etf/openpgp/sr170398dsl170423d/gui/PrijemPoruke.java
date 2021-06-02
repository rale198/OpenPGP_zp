package etf.openpgp.sr170398dsl170423d.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import etf.openpgp.sr170398dsl170423d.gui.dtos.BrisanjeKljucevaDtos;
import etf.openpgp.sr170398dsl170423d.gui.dtos.GenerisanjeKljucevaDtos;
import etf.openpgp.sr170398dsl170423d.gui.dtos.PrijemPorukeDtos;
import etf.openpgp.sr170398dsl170423d.gui.dtos.UvozKljucaDtos;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;

public class PrijemPoruke {
	//[Podatak za obradu]
	//----------------------------
	private PrijemPorukeDtos data;
	//----------------------------
	Projekat p;
	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public PrijemPoruke(Projekat p) {
		data = new PrijemPorukeDtos();
		this.p = p;
		initialize();	
	}

	private void resizeForm(int width,JFrame myFrame) {	
		//myFrame.pack();
		//myFrame.setLocation(new Point(p.getX()+p.getW()+50,p.getY()+ p.getH()/2));
		myFrame.setBounds(p.getX()+p.getW()+50,p.getY()+ p.getH()/2,600,300);
		myFrame.setVisible(true);
		myFrame.setResizable(true);
}
/**
 * Initialize the contents of the frame.
 */
private void initialize() {
	frame = new JFrame();
	frame.getContentPane().setBackground(Color.LIGHT_GRAY);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	frame.getContentPane().setLayout(gridBagLayout);
	
	JLabel lblNewLabel_1_2 = new JLabel("PRIJEM PORUKE");
	lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 25));
	lblNewLabel_1_2.setBackground(Color.YELLOW);
	GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
	gbc_lblNewLabel_1_2.gridwidth = 3;
	gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1_2.gridx = 0;
	gbc_lblNewLabel_1_2.gridy = 2;
	frame.getContentPane().add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
	
	JSeparator separator_1_1 = new JSeparator();
	separator_1_1.setBackground(new Color(0, 0, 0));
	separator_1_1.setForeground(Color.WHITE);
	GridBagConstraints gbc_separator_1_1 = new GridBagConstraints();
	gbc_separator_1_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_separator_1_1.gridwidth = 3;
	gbc_separator_1_1.insets = new Insets(0, 0, 5, 5);
	gbc_separator_1_1.gridx = 0;
	gbc_separator_1_1.gridy = 3;
	frame.getContentPane().add(separator_1_1, gbc_separator_1_1);
	
	textField = new JTextField();
	textField.setEditable(false);
	textField.setHorizontalAlignment(SwingConstants.CENTER);
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.gridwidth = 3;
	gbc_textField.insets = new Insets(0, 0, 5, 5);
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.gridx = 0;
	gbc_textField.gridy = 5;
	frame.getContentPane().add(textField, gbc_textField);
	textField.setColumns(10);
	
	JButton btnNewButton_2 = new JButton("Odaberi putanju");
	btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
	gbc_btnNewButton_2.gridwidth = 3;
	gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton_2.gridx = 0;
	gbc_btnNewButton_2.gridy = 6;
	frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
	
	JLabel PrimljenOdgovor = new JLabel("");
	PrimljenOdgovor.setFont(new Font("Tahoma", Font.PLAIN, 16));
	GridBagConstraints gbc_PrimljenOdgovor = new GridBagConstraints();
	gbc_PrimljenOdgovor.gridwidth = 4;
	gbc_PrimljenOdgovor.insets = new Insets(0, 0, 5, 5);
	gbc_PrimljenOdgovor.gridx = 0;
	gbc_PrimljenOdgovor.gridy = 11;
	frame.getContentPane().add(PrimljenOdgovor, gbc_PrimljenOdgovor);
	

	JSeparator separator_2 = new JSeparator();
	separator_2.setPreferredSize(new Dimension(200, 2));
	GridBagConstraints gbc_separator_2 = new GridBagConstraints();
	gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
	gbc_separator_2.gridwidth = 3;
	gbc_separator_2.insets = new Insets(0, 0, 5, 5);
	gbc_separator_2.gridx = 0;
	gbc_separator_2.gridy = 13;
	frame.getContentPane().add(separator_2, gbc_separator_2);
	

	JLabel lblNewLabel_1_1 = new JLabel("LOZINKA");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblNewLabel_1_1.setBackground(Color.YELLOW);
	GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
	gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1_1.gridx = 0;
	gbc_lblNewLabel_1_1.gridy = 14;
	frame.getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
	lblNewLabel_1_1.setVisible(false);
	
	textField_1 = new JPasswordField();
	textField_1.setVisible(false);
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.gridwidth = 2;
	gbc_textField_1.insets = new Insets(0, 0, 5, 5);
	gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_1.gridx = 1;
	gbc_textField_1.gridy = 14;
	frame.getContentPane().add(textField_1, gbc_textField_1);
	
	JSeparator separator_2_2 = new JSeparator();
	separator_2_2.setPreferredSize(new Dimension(200, 2));
	GridBagConstraints gbc_separator_2_2 = new GridBagConstraints();
	gbc_separator_2_2.fill = GridBagConstraints.HORIZONTAL;
	gbc_separator_2_2.gridwidth = 3;
	gbc_separator_2_2.insets = new Insets(0, 0, 5, 5);
	gbc_separator_2_2.gridx = 0;
	gbc_separator_2_2.gridy = 15;
	frame.getContentPane().add(separator_2_2, gbc_separator_2_2);
	
	
	JButton btnNewButton_3 = new JButton("DEKRIPTUJ");
	btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
	btnNewButton_3.setForeground(new Color(0, 0, 0));
	btnNewButton_3.setBackground(new Color(0, 128, 0));
	GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
	gbc_btnNewButton_3.gridwidth = 6;
	gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
	gbc_btnNewButton_3.gridx = 0;
	gbc_btnNewButton_3.gridy = 17;
	frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
	
	JLabel Poruka = new JLabel("");
	Poruka.setFont(new Font("Tahoma", Font.PLAIN, 17));
	GridBagConstraints gbc_Poruka = new GridBagConstraints();
	gbc_Poruka.gridwidth = 3;
	gbc_Poruka.insets = new Insets(0, 0, 5, 5);
	gbc_Poruka.gridx = 0;
	gbc_Poruka.gridy = 18;
	frame.getContentPane().add(Poruka, gbc_Poruka);
	frame.setBounds(100, 100, 331, 414);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	
	btnNewButton_3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
        	data.Lozinka = textField_1.getText();

			System.out.println(data.PutanjaFajla+ " "+data.Lozinka);
			ArrayList<String> odgovor = p.b.receiveMessage(data.PutanjaFajla, data.Lozinka);
			String ispis = "";
			for(String s: odgovor) {
				ispis += s;
				ispis += "\n";
			}
				
			Poruka.setText(ispis);
			Poruka.setForeground(Color.green);
		}
	});
	btnNewButton_2.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	        int r = fileChooser.showOpenDialog(null);
	        if (r == JFileChooser.APPROVE_OPTION)
	        {      
	            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            textField.setForeground(Color.black);
	            
	            if(!textField.getText().equals("Obavezno je odabrati destinaciju za izvoz") &&!textField.getText().equals("") )
	            {
	            	data.PutanjaFajla = textField.getText();

	            	String s = p.b.isUsingEncryption(data.PutanjaFajla);
	            	if(s != null)
	            	{
	            		PrimljenOdgovor.setText(s);
	            		PrimljenOdgovor.setForeground(Color.BLUE);
	            		
	            		lblNewLabel_1_1.setVisible(true);
	                	textField_1.setVisible(true);
	                	
	                	//ArrayList<String> response = p.b.receiveMessage(data.PutanjaFajla, s)
	            	}
	            }        
	        }
	        // if the user cancelled the operation
	        else
	        {
	        	textField.setText("Obavezno je odabrati destinaciju za izvoz");
	        	textField.setForeground(Color.red);
	        
	        	lblNewLabel_1_1.setVisible(false);
            	textField_1.setVisible(false);
	        	
	        }
	
		}
	});
	
	
	this.resizeForm(0, frame);
}

}
