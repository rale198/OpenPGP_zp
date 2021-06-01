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
import javax.swing.ComboBoxModel;

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
import javax.swing.JPasswordField;

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
import javax.swing.JTextArea;

public class BrisanjeParaKljuceva {
	//[Podatak za obradu]
	//----------------------------
	private BrisanjeKljucevaDtos data;
	private RingOutput selectedDataPrivate;
	private RingOutput selectedDataPublic;
	//----------------------------
	Projekat p;
	JFrame frame;
	JComboBox<RingOutput> cb;
	JComboBox<RingOutput> cb2;

	/**
	 * Create the application.
	 */
	public BrisanjeParaKljuceva(Projekat p) {
		data = new BrisanjeKljucevaDtos();
		this.p = p;
		initialize();	
	}

	private void resizeForm(int width,JFrame myFrame) {	
		myFrame.pack();
		myFrame.setLocation(new Point(p.getX()-p.getW()-250,p.getY()+p.getH()/2 + 80));
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
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	frame.getContentPane().setLayout(gridBagLayout);
	
	JLabel lblNewLabel_1 = new JLabel("ODABERI ELEMENT ZA BRISANJE");
	
	lblNewLabel_1.setBackground(Color.YELLOW);
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1.gridx = 0;
	gbc_lblNewLabel_1.gridy = 0;
	frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	
	JSeparator separator_1_1 = new JSeparator();
	separator_1_1.setPreferredSize(new Dimension(200, 2));
	separator_1_1.setBackground(new Color(30, 144, 255));
	GridBagConstraints gbc_separator_1_1 = new GridBagConstraints();
	gbc_separator_1_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_separator_1_1.insets = new Insets(0, 0, 5, 5);
	gbc_separator_1_1.gridx = 0;
	gbc_separator_1_1.gridy = 1;
	frame.getContentPane().add(separator_1_1, gbc_separator_1_1);
	
	ArrayList<RingOutput> ret = p.b.showPrivateKeyRingCollection();
	
	RingOutput[] choices = new RingOutput[ret.size()];
	for(int i=0; i< ret.size();i++)
	{
		choices[i] = ret.get(i);
	}
	
	cb = new JComboBox<RingOutput>(choices);
	cb.setPreferredSize(new Dimension(500,20));
	cb.setVisible(true);
	frame.getContentPane().add(cb);    
	GridBagConstraints gbc_list = new GridBagConstraints();
	gbc_list.insets = new Insets(0, 0, 5, 0);
	gbc_list.fill = GridBagConstraints.BOTH;
	gbc_list.gridx = 0;
	gbc_list.gridy = 2;
	frame.getContentPane().add(cb, gbc_list);
	
	
	
	ArrayList<RingOutput> ret2 = p.b.showPublicKeyRingCollection();
	RingOutput[] choices2 = new RingOutput[ret2.size()];
	for(int i=0; i< ret2.size();i++)
	{
		choices2[i] = ret2.get(i);
	}
	
	cb2 = new JComboBox<RingOutput>(choices2);
	cb2.setPreferredSize(new Dimension(400,20));
	cb2.setVisible(true);
	frame.getContentPane().add(cb2);    
	GridBagConstraints gbc_list2 = new GridBagConstraints();
	gbc_list2.insets = new Insets(0, 0, 5, 0);
	gbc_list2.fill = GridBagConstraints.BOTH;
	gbc_list2.gridx = 0;
	gbc_list2.gridy = 12;
	frame.getContentPane().add(cb2, gbc_list2);
	
	JLabel lblNewLabel_1_1 = new JLabel("Lozinka");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblNewLabel_1_1.setBackground(Color.YELLOW);
	GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
	gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1_1.gridx = 0;
	gbc_lblNewLabel_1_1.gridy = 8;
	frame.getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
	
	
	JButton btnNewButton = new JButton("OBRISI  PRIVATNI KLJUC");
	btnNewButton.setBackground(Color.WHITE);
	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	gbc_btnNewButton.gridwidth = 2;
	gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
	gbc_btnNewButton.gridx = 0;
	gbc_btnNewButton.gridy = 10;
	
	
	JPasswordField txtunesiLozinku = new JPasswordField();
	GridBagConstraints gbc_txtunesiLozinku = new GridBagConstraints();
	gbc_txtunesiLozinku.insets = new Insets(0, 0, 5, 5);
	gbc_txtunesiLozinku.fill = GridBagConstraints.BOTH;
	gbc_txtunesiLozinku.gridx = 0;
	gbc_txtunesiLozinku.gridy = 9;
	frame.getContentPane().add(txtunesiLozinku, gbc_txtunesiLozinku);
	
	frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	
	cb.addActionListener (new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	        if(cb.getSelectedItem() instanceof RingOutput) {
	        	RingOutput item = (RingOutput) cb.getSelectedItem();
	        	//System.out.println(p.b.removeKeyPair(item.getKeyID(),txtunesiLozinku.getText()));
	  
	        	selectedDataPrivate = item;
	        	System.out.println("PRIVATE->"+selectedDataPrivate);
	        }else
	        	System.out.println(false);
	        
	    }
	});
	
	cb2.addActionListener (new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	        if(cb2.getSelectedItem() instanceof RingOutput) {
	        	RingOutput item = (RingOutput) cb2.getSelectedItem();
	        	//System.out.println(p.b.removeKeyPair(item.getKeyID(),txtunesiLozinku.getText()));
	  
	        	selectedDataPublic = item;
	        	System.out.println("Public->"+selectedDataPublic);
	        }else
	        	System.out.println(false);
	        
	    }
	});
	
	JSeparator separator_1 = new JSeparator();
	separator_1.setBackground(new Color(30, 144, 255));
	separator_1.setPreferredSize(new Dimension(200, 2));
	GridBagConstraints gbc_separator_1 = new GridBagConstraints();
	gbc_separator_1.gridwidth = 2;
	gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_separator_1.insets = new Insets(0, 0, 5, 0);
	gbc_separator_1.gridx = 0;
	gbc_separator_1.gridy = 11;
	frame.getContentPane().add(separator_1, gbc_separator_1);
	

	
	JButton btnNewButton_1 = new JButton("OBRISI  JAVNI KLJUC");
	btnNewButton_1.setBackground(Color.WHITE);
	GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
	gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton_1.gridx = 0;
	gbc_btnNewButton_1.gridy = 13;
	frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
	
	JLabel Poruka = new JLabel("");
	Poruka.setFont(new Font("Tahoma", Font.BOLD, 17));
	GridBagConstraints gbc_Poruka = new GridBagConstraints();
	gbc_Poruka.insets = new Insets(0, 0, 5, 5);
	gbc_Poruka.gridx = 0;
	gbc_Poruka.gridy = 14;
	
	//[BRISANJE PRIVATNOG KLJUCA]
	btnNewButton.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(p.b.removeKeyPair(selectedDataPrivate.getKeyID(),txtunesiLozinku.getText())){
					ArrayList<RingOutput> ret = p.b.showPrivateKeyRingCollection();
					RingOutput[] choices = new RingOutput[ret.size()];
					cb.removeAllItems();
					for(int i=0; i< ret.size();i++)
					{
						choices[i] = ret.get(i);
						cb.addItem(choices[i]);
					}
					
					Poruka.setText("BRISANJE PRIVATNOG KLJUCA USPESNO!");
					Poruka.setForeground(Color.GREEN);
					frame.pack();
					
					cb.setSelectedIndex(0);
				}else
				{
					Poruka.setText("BRISANJE PRIVATNOG KLJUCA NEUSPESNO!");
					Poruka.setForeground(Color.RED);
					frame.pack();
				}
			}catch (Exception e2) {
				
			}
			
		}
	});
	
	//[BRISANJE JAVNOG KLJUCA]
	btnNewButton_1.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(p.b.removeImportKey(selectedDataPublic.getKeyID())){
					ArrayList<RingOutput> ret = p.b.showPublicKeyRingCollection();
					RingOutput[] choices = new RingOutput[ret.size()];
					cb2.removeAllItems();
					for(int i=0; i< ret.size();i++)
					{
						choices[i] = ret.get(i);
						cb2.addItem(choices[i]);
					}
					
					Poruka.setText("BRISANJE JAVNOG KLJUCA USPESNO!");
					Poruka.setForeground(Color.GREEN);
					frame.pack();
					
					cb2.setSelectedIndex(0);
				}else
				{
					Poruka.setText("BRISANJE JAVNOG KLJUCA NEUSPESNO!");
					Poruka.setForeground(Color.RED);
					frame.pack();
				}
			}catch (Exception e2) {
				
			}
			
		}
	});
	
	frame.getContentPane().add(Poruka, gbc_Poruka);
	frame.setBounds(100, 100, 534, 370);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.resizeForm(0, frame);
}

}
