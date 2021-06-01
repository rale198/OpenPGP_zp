package etf.openpgp.sr170398dsl170423d.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;

import etf.openpgp.sr170398dsl170423d.gui.GenerisanjeKljuceva;
import etf.openpgp.sr170398dsl170423d.impl.Backend;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
public class Projekat {

	private JFrame frame;
	Backend b;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projekat window = new Projekat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Projekat() {
		b = new Backend();
		System.out.println(b.showPrivateKeyRingCollection());
		
		initialize();
	}
	
	public int getX() {
		return frame.location().x;
	}
	public int getY() {
		return frame.location().y;
	}
	public int getW() {
		return frame.getWidth();
	}
	public int getH() {
		return frame.getHeight();
	}
	private void resizeForm(int width, JFrame myFrame) {
			int position = width+30 ;
			myFrame.setBounds(100, 100,position,700 );
			myFrame.pack();
			myFrame.setLocationRelativeTo(null);
			myFrame.setVisible(true);
			myFrame.setResizable(false);
	}
	private void openForm(JButton e,JFrame myFrame)
	{
		System.out.println(e.getText());
		switch(e.getText()) {
		  case "Generisanje novog para kljuceva":
			 GenerisanjeKljuceva window = new GenerisanjeKljuceva(this);
			 window.frame.setVisible(true);
		     break;
		  case "Brisanje postojeceg para kljuceva":
			  BrisanjeParaKljuceva window1 = new BrisanjeParaKljuceva(this);
			  window1.frame.setVisible(true);
			  break;
		  case "Uvoz kljuca":
			  UvozKljuca window2 = new UvozKljuca(this);
			  window2.frame.setVisible(true);
			  break;
		  case "Prikaz prstena kljuceva":
			  PrikazPrstena window3 = new PrikazPrstena(this);
			  window3.frame.setVisible(true);
			  break;
		  case "Slanje poruke":
			  SlanjePoruke window4 = new SlanjePoruke(this);
			  window4.frame.setVisible(true);
			  break;
		  case "Zatvori program":
			  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); 
			  break;
		  case "Izvoz kljuca":
			  IzvozKljuca window5 = new IzvozKljuca(this);
			  window5.frame.setVisible(true); 
			  break;
		  case "Prijem poruke":
			  PrijemPoruke window6 = new PrijemPoruke(this);
			  window6.frame.setVisible(true); 
			  break;
		  default:
		    // code block
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 650, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Zastita podataka");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 6;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 6;
		gbc_separator.gridy = 1;
		frame.getContentPane().add(separator, gbc_separator);
		
		JButton btnNewButton_1 = new JButton("Generisanje novog para kljuceva");
		btnNewButton_1.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 6;
		gbc_btnNewButton_1.gridy = 3;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton_1,frame);
			}
		});
		
		JButton btnNewButton = new JButton("Brisanje postojeceg para kljuceva");
		btnNewButton.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 5;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton,frame);
			}
		});
		
		//dodavanje za dugme 1
		btnNewButton_1.setPreferredSize(new Dimension(btnNewButton.getPreferredSize().width,btnNewButton.getPreferredSize().height ));
		
		JButton btnNewButton_2 = new JButton("Uvoz kljuca");
		btnNewButton_2.setPreferredSize(new Dimension(btnNewButton.getPreferredSize().width,btnNewButton.getPreferredSize().height ));
		btnNewButton_2.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 6;
		gbc_btnNewButton_2.gridy = 7;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton_2,frame);
			}
		});
		
		JButton btnNewButton_3 = new JButton("Izvoz kljuca");
		btnNewButton_3.setPreferredSize(new Dimension(btnNewButton.getPreferredSize().width,btnNewButton.getPreferredSize().height ));
		btnNewButton_3.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 6;
		gbc_btnNewButton_3.gridy = 9;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton_3,frame);
			}
		});
		
		JButton btnNewButton_4 = new JButton("Prikaz prstena kljuceva");
		btnNewButton_4.setPreferredSize(new Dimension(btnNewButton.getPreferredSize().width,btnNewButton.getPreferredSize().height ));
		btnNewButton_4.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 6;
		gbc_btnNewButton_4.gridy = 11;
		frame.getContentPane().add(btnNewButton_4, gbc_btnNewButton_4);
		
		btnNewButton_4.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton_4,frame);
			}
		});
		JButton btnNewButton_5 = new JButton("Slanje poruke");
		btnNewButton_5.setPreferredSize(new Dimension(btnNewButton.getPreferredSize().width,btnNewButton.getPreferredSize().height ));
		btnNewButton_5.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 6;
		gbc_btnNewButton_5.gridy = 13;
		frame.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton_5,frame);
			}
		});
		
		JButton btnNewButton_6 = new JButton("Prijem poruke");
		btnNewButton_6.setPreferredSize(new Dimension(btnNewButton.getPreferredSize().width,btnNewButton.getPreferredSize().height ));
		btnNewButton_6.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 6;
		gbc_btnNewButton_6.gridy = 15;
		frame.getContentPane().add(btnNewButton_6, gbc_btnNewButton_6);
		
		btnNewButton_6.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton_6,frame);
			}
		});
		
		JButton btnNewButton_7 = new JButton("Zatvori program");
		btnNewButton_7.setPreferredSize(new Dimension(btnNewButton.getPreferredSize().width,btnNewButton.getPreferredSize().height ));
		btnNewButton_7.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 6;
		gbc_btnNewButton_7.gridy = 17;
		frame.getContentPane().add(btnNewButton_7, gbc_btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					openForm(btnNewButton_7,frame);
			}
		});
		
		resizeForm(btnNewButton.getPreferredSize().width, frame);
	}

}
