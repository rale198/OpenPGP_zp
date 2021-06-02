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
import etf.openpgp.sr170398dsl170423d.impl.RingOutput;

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

public class IzvozKljuca {
	//[Podatak za obradu]
	//----------------------------
	private BrisanjeKljucevaDtos data;
	private RingOutput selectedData;
	//----------------------------
	Projekat p;
	JFrame frame;
	private JTextField textField;
	JComboBox<RingOutput> cb;

	/**
	 * Create the application.
	 */
	public IzvozKljuca(Projekat p) {
		data = new BrisanjeKljucevaDtos();
		this.p = p;
		initialize();	
	}

	private void resizeForm(int width,JFrame myFrame) {	
		myFrame.pack();
		myFrame.setLocation(new Point(p.getX()- 50,p.getY()+p.getH() + 20));
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
	gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	frame.getContentPane().setLayout(gridBagLayout);
	
	JLabel lblNewLabel_1 = new JLabel("IZVOZ KLJUCA");
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	
	lblNewLabel_1.setBackground(Color.YELLOW);
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.gridwidth = 2;
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1.gridx = 0;
	gbc_lblNewLabel_1.gridy = 0;
	frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	
	JSeparator separator = new JSeparator();
	separator.setPreferredSize(new Dimension(200,2));
	GridBagConstraints gbc_separator = new GridBagConstraints();
	gbc_separator.gridwidth = 2;
	gbc_separator.insets = new Insets(0, 0, 5, 5);
	gbc_separator.gridx = 0;
	gbc_separator.gridy = 1;
	frame.getContentPane().add(separator, gbc_separator);
	
	//[get private, get public array)
	ArrayList<RingOutput> ret = p.b.showPrivateKeyRingCollection();
	ArrayList<RingOutput> ret2 = p.b.showPublicKeyRingCollection();
	RingOutput[] choices = new RingOutput[ret.size()+ ret2.size()];
	
	//[add private keys]
	for(int i=0; i< ret.size();i++)
	{
		choices[i] = ret.get(i);
	}
	//[add public keys]
	for(int i=ret.size(); i< ret.size()+ ret2.size();i++)
	{
		choices[i] = ret2.get(i-ret.size());
	}
	
	
	cb = new JComboBox<RingOutput>(choices);
	cb.setPreferredSize(new Dimension(500,20));
	cb.setVisible(true);
	frame.getContentPane().add(cb);    
	GridBagConstraints gbc_list = new GridBagConstraints();
	gbc_list.gridwidth = 2;
	gbc_list.insets = new Insets(0, 0, 5, 0);
	gbc_list.fill = GridBagConstraints.BOTH;
	gbc_list.gridx = 0;
	gbc_list.gridy = 2;
	frame.getContentPane().add(cb, gbc_list);
	
	JButton btnNewButton_1 = new JButton("Privatni kljucevi");
	btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
	GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
	gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
	gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton_1.gridx = 0;
	gbc_btnNewButton_1.gridy = 9;
	frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

	
	JButton btnNewButton = new JButton("Javni kljucevi");
	btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
	btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton.gridx = 1;
	gbc_btnNewButton.gridy = 9;
	frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	
	JLabel lblNewLabel = new JLabel("PUTANJA");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.gridwidth = 2;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 10;
	frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	
	textField = new JTextField();
	textField.setEditable(false);
	textField.setHorizontalAlignment(SwingConstants.CENTER);
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.gridwidth = 2;
	gbc_textField.insets = new Insets(0, 0, 5, 5);
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.gridx = 0;
	gbc_textField.gridy = 11;
	frame.getContentPane().add(textField, gbc_textField);
	textField.setColumns(10);
	
	JButton btnNewButton_2 = new JButton("Odaberi putanju");
	btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
	gbc_btnNewButton_2.gridwidth = 3;
	gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
	gbc_btnNewButton_2.gridx = 0;
	gbc_btnNewButton_2.gridy = 12;
	frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
	
	btnNewButton_2.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	        int r = fileChooser.showOpenDialog(null);
	       
	        // if the user selects a file
	        if (r == JFileChooser.APPROVE_OPTION)
	        {
	        	
	            // set the label to the path of the selected file
	            //data.FilePath = (fileChooser.getSelectedFile().getAbsolutePath());
	           // data.KeyType = keyType;
	            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            textField.setForeground(Color.black);
	            frame.pack();
	            //System.out.println(data.FilePath);
	            
	        }
	        // if the user cancelled the operation
	        else
	        {
	        	textField.setText("Obavezno je odabrati destinaciju za izvoz");
	        	textField.setForeground(Color.red);
	        	frame.pack();
	        }
	
		}
	});
	
	cb.addActionListener (new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	        if(cb.getSelectedItem() instanceof RingOutput) {
	        	RingOutput item = (RingOutput) cb.getSelectedItem();
	        	//System.out.println(p.b.removeKeyPair(item.getKeyID(),txtunesiLozinku.getText()));
	  
	        	selectedData = item;
	        	System.out.println("EXPORT->"+selectedData);
	        	
	        	if(selectedData.isPrivate())
	        	{
	        		btnNewButton.setEnabled(true);
	        		btnNewButton_1.setEnabled(true);
	        	}else
	        	{
	        		btnNewButton.setEnabled(true);
	        		btnNewButton_1.setEnabled(false);
	        	}
	        }else
	        	System.out.println(false);
	        
	    }
	});
	
	
	JLabel Poruka = new JLabel("");
	Poruka.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_Poruka = new GridBagConstraints();
	gbc_Poruka.gridwidth = 2;
	gbc_Poruka.insets = new Insets(0, 0, 5, 5);
	gbc_Poruka.gridx = 0;
	gbc_Poruka.gridy = 14;
	frame.getContentPane().add(Poruka, gbc_Poruka);
	frame.setBounds(100, 100, 431, 600);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	btnNewButton_1.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().equals("") || textField.getText().equals("Obavezno je odabrati destinaciju za izvoz"))
			{
				Poruka.setText("Putanju je obavezno odabrati");
				Poruka.setForeground(Color.RED);
				frame.pack();
			}else
			{//[Putanja odabrana]
				if(p.b.printSecretKey(selectedData.getKeyID(), textField.getText()))
				{
					Poruka.setText("Izvoz privatnog kljuca uspesan");
					Poruka.setForeground(Color.GREEN);
					frame.pack();
				}else
				{
					Poruka.setText("Izvoz privatnog kljuca nije uspesan");
					Poruka.setForeground(Color.RED);
					frame.pack();
				}
			}
		}
	});
	
	btnNewButton.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().equals("") || textField.getText().equals("Obavezno je odabrati destinaciju za izvoz"))
			{
				Poruka.setText("Putanju je obavezno odabrati");
				Poruka.setForeground(Color.RED);
				frame.pack();
			}else
			{//[Putanja odabrana]
				if(p.b.exportPublicKey(selectedData.getKeyID(), textField.getText()))
				{
					Poruka.setText("Izvoz javnog kljuca uspesan");
					Poruka.setForeground(Color.GREEN);
					frame.pack();
				}else
				{
					Poruka.setText("Izvoz javnog kljuca nije uspesan");
					Poruka.setForeground(Color.RED);
					frame.pack();
				}
			}
		}
	});
	
	JSeparator separator_1 = new JSeparator();
	separator_1.setForeground(Color.WHITE);
	GridBagConstraints gbc_separator_1 = new GridBagConstraints();
	gbc_separator_1.gridwidth = 2;
	gbc_separator_1.insets = new Insets(0, 0, 5, 5);
	gbc_separator_1.gridx = 0;
	gbc_separator_1.gridy = 13;
	frame.getContentPane().add(separator_1, gbc_separator_1);

	this.resizeForm(0, frame);
}

}
