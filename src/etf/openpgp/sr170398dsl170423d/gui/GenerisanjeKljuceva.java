package etf.openpgp.sr170398dsl170423d.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
import java.awt.Font;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import etf.openpgp.sr170398dsl170423d.gui.dtos.GenerisanjeKljucevaDtos;

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
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;


public class GenerisanjeKljuceva {
	//[Podatak za obradu]
	//----------------------------
	private GenerisanjeKljucevaDtos data;
	//----------------------------
	private Projekat p;
	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the application.
	 */
	public GenerisanjeKljuceva(Projekat p) {
		data = new GenerisanjeKljucevaDtos();
		this.p = p;
		initialize();	
	}

	private void resizeForm(int width,JFrame myFrame) {	
		myFrame.setLocation(new Point(p.getX()-p.getW()-150,p.getY()-100));
		myFrame.setVisible(true);
		myFrame.setResizable(false);
}
/**
 * Initialize the contents of the frame.
 */
private void initialize() {
	frame = new JFrame();
	frame.getContentPane().setBackground(Color.LIGHT_GRAY);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{0, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	frame.getContentPane().setLayout(gridBagLayout);
	
	JLabel lblNewLabel_1 = new JLabel("Generisi par");
	
	lblNewLabel_1.setBackground(Color.YELLOW);
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.gridwidth = 2;
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
	gbc_lblNewLabel_1.gridx = 0;
	gbc_lblNewLabel_1.gridy = 0;
	frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	
	JSeparator separator = new JSeparator();
	separator.setPreferredSize(new Dimension(200,2));
	GridBagConstraints gbc_separator = new GridBagConstraints();
	gbc_separator.gridwidth = 2;
	gbc_separator.insets = new Insets(0, 0, 5, 0);
	gbc_separator.gridx = 0;
	gbc_separator.gridy = 1;
	frame.getContentPane().add(separator, gbc_separator);
	
	JLabel Warning = new JLabel("");
	Warning.setFont(new Font("Verdana Pro", Font.ITALIC, 11));
	Warning.setForeground(Color.RED);
	GridBagConstraints gbc_Warning = new GridBagConstraints();
	gbc_Warning.gridwidth = 2;
	gbc_Warning.insets = new Insets(0, 0, 5, 0);
	gbc_Warning.gridx = 0;
	gbc_Warning.gridy = 2;
	frame.getContentPane().add(Warning, gbc_Warning);
	
	JLabel lblNewLabel = new JLabel("IME");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 3;
	frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	
	textField = new JTextField();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.insets = new Insets(0, 0, 5, 0);
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.gridx = 1;
	gbc_textField.gridy = 3;
	frame.getContentPane().add(textField, gbc_textField);
	textField.setColumns(8);
	
	JLabel lblNewLabel_2 = new JLabel("MEJL");
	lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
	gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_2.gridx = 0;
	gbc_lblNewLabel_2.gridy = 5;
	frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
	
	textField_1 = new JTextField();
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.insets = new Insets(0, 0, 5, 0);
	gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_1.gridx = 1;
	gbc_textField_1.gridy = 5;
	frame.getContentPane().add(textField_1, gbc_textField_1);
	textField_1.setColumns(10);
	
	JLabel lblNewLabel_3 = new JLabel("ALGORITAM");
	lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
	gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_3.gridx = 0;
	gbc_lblNewLabel_3.gridy = 7;
	frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
	
	/*
	list.setModel(new AbstractListModel() {
		String[] values = new String[] {"RSA 1024", "RSA 2048", "RSA 4096"};
		public int getSize() {
			return values.length;
		}
		public Object getElementAt(int index) {
			return values[index];
		}
	});
	*/
	String[] choices = { "Odaberi algoritam","RSA 1024","RSA 2048","RSA 4096"};
	JComboBox<String> cb = new JComboBox<String>(choices);
	cb.setPreferredSize(new Dimension(100,20));
	cb.setVisible(true);
	frame.getContentPane().add(cb);    
	GridBagConstraints gbc_list = new GridBagConstraints();
	gbc_list.insets = new Insets(0, 0, 5, 0);
	gbc_list.fill = GridBagConstraints.BOTH;
	gbc_list.gridx = 1;
	gbc_list.gridy = 7;
	frame.getContentPane().add(cb, gbc_list);
	
	cb.addActionListener (new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	    	data.Algoritam = cb.getSelectedItem().toString();
	        System.out.println(cb.getSelectedItem().toString());
	    }
	});
	
	JLabel lblNewLabel_4 = new JLabel("LOZINKA");
	lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblNewLabel_4.setVisible(false);
	
	JButton btnNewButton_1 = new JButton("POTVRDI");
	btnNewButton_1.setBackground(Color.WHITE);
	GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
	gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
	gbc_btnNewButton_1.gridwidth = 2;
	gbc_btnNewButton_1.gridx = 0;
	gbc_btnNewButton_1.gridy = 10;
	frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
	btnNewButton_1.setVisible(false);
	
	JButton btnNewButton = new JButton("GENERISI KLJUC");
	btnNewButton.setBackground(Color.WHITE);
	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	gbc_btnNewButton.gridwidth = 2;
	gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
	gbc_btnNewButton.gridx = 0;
	gbc_btnNewButton.gridy = 8;
	
	
	btnNewButton.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			textField_2.setVisible(true);
			lblNewLabel_4.setVisible(true);
			btnNewButton_1.setVisible(true);
		}
	});
	
	frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
	gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_4.gridx = 0;
	gbc_lblNewLabel_4.gridy = 9;
	frame.getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
	
	textField_2 = new JTextField();
	textField_2.setVisible(false);
	GridBagConstraints gbc_textField_2 = new GridBagConstraints();
	gbc_textField_2.insets = new Insets(0, 0, 5, 0);
	gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_2.gridx = 1;
	gbc_textField_2.gridy = 9;
	frame.getContentPane().add(textField_2, gbc_textField_2);
	textField_2.setColumns(10);
	
	JLabel Poruka = new JLabel("");
	Poruka.setHorizontalAlignment(SwingConstants.CENTER);
	Poruka.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_Poruka = new GridBagConstraints();
	gbc_Poruka.gridwidth = 2;
	gbc_Poruka.insets = new Insets(0, 0, 0, 5);
	gbc_Poruka.gridx = 0;
	gbc_Poruka.gridy = 11;
	frame.getContentPane().add(Poruka, gbc_Poruka);
	
	
	btnNewButton_1.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("") && !data.Algoritam.equals("") && !data.Algoritam.equals("Odaberi algoritam")) {
				data.Ime = textField.getText();
				data.Mejl = textField_1.getText();
				data.Lozinka = textField_2.getText();
				
				boolean operation = p.b.generateKeyPair(data.Ime, data.Mejl, Integer.parseInt((data.Algoritam.substring(4))), data.Lozinka);
				System.out.println(operation);
				if(operation)
				{
					Poruka.setText("GENERISANJE USPELO!");
					Poruka.setForeground(Color.GREEN);
				}else {
					Poruka.setText("GENERISANJE NIJE USPELO!");
					Poruka.setForeground(Color.RED);
				}
			}else Warning.setText("NEISPRAVAN UNOS / ODABIR ALGORITMA!");
		}
	});	
	frame.setBounds(100, 100, 430, 367);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.resizeForm(0, frame);
}

}
