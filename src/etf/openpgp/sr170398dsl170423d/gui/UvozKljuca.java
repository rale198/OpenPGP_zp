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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import etf.openpgp.sr170398dsl170423d.gui.dtos.BrisanjeKljucevaDtos;
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

public class UvozKljuca {
	//[Podatak za obradu]
	//----------------------------
	private UvozKljucaDtos data;
	//----------------------------
	Projekat p;
	JFrame frame;

	/**
	 * Create the application.
	 */
	public UvozKljuca(Projekat p) {
		data = new UvozKljucaDtos();
		this.p = p;
		initialize();	
	}

	private void resizeForm(int width,JFrame myFrame) {	
		myFrame.pack();
		myFrame.setLocation(new Point(p.getX()+50,p.getY()-frame.getHeight()-50));
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
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	frame.getContentPane().setLayout(gridBagLayout);
	
	JLabel lblNewLabel_1 = new JLabel("UVOZ KLJUCA");
	
	lblNewLabel_1.setBackground(Color.YELLOW);
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1.gridx = 0;
	gbc_lblNewLabel_1.gridy = 0;
	frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	
	JSeparator separator = new JSeparator();
	separator.setPreferredSize(new Dimension(200,2));
	GridBagConstraints gbc_separator = new GridBagConstraints();
	gbc_separator.insets = new Insets(0, 0, 5, 5);
	gbc_separator.gridx = 0;
	gbc_separator.gridy = 1;
	frame.getContentPane().add(separator, gbc_separator);
	
	JButton btnJavniKljuc = new JButton("ODABERI PUTANJU");
	btnJavniKljuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnJavniKljuc = new GridBagConstraints();
	gbc_btnJavniKljuc.insets = new Insets(0, 0, 5, 5);
	gbc_btnJavniKljuc.gridx = 0;
	gbc_btnJavniKljuc.gridy = 5;
	frame.getContentPane().add(btnJavniKljuc, gbc_btnJavniKljuc);
	
	JSeparator separator_1 = new JSeparator();
	GridBagConstraints gbc_separator_1 = new GridBagConstraints();
	gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_separator_1.insets = new Insets(0, 0, 5, 5);
	gbc_separator_1.gridx = 0;
	gbc_separator_1.gridy = 6;
	frame.getContentPane().add(separator_1, gbc_separator_1);
	
	JLabel Poruka = new JLabel("New label");
	Poruka.setFont(new Font("Tahoma", Font.PLAIN, 15));
	GridBagConstraints gbc_Poruka = new GridBagConstraints();
	gbc_Poruka.insets = new Insets(0, 0, 5, 5);
	gbc_Poruka.gridx = 0;
	gbc_Poruka.gridy = 7;
	frame.getContentPane().add(Poruka, gbc_Poruka);
	frame.setBounds(100, 100, 431, 600);
	this.resizeForm(0, frame);
	Poruka.setVisible(false);
	
	btnJavniKljuc.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		        int r = fileChooser.showOpenDialog(null);
		       
		        // if the user selects a file
		        if (r == JFileChooser.APPROVE_OPTION)
		        {
		        	data.PutanjaFajla = fileChooser.getSelectedFile().getAbsolutePath();
		            if(p.b.importKey(data.PutanjaFajla))
		            {	            	
		            	Poruka.setText("Uvoz kljuca je uspesan!");
		            	Poruka.setForeground(Color.GREEN);
		            	Poruka.setVisible(true);
		            	frame.pack();     	
		            }else
			        {
			        	Poruka.setText("Uvoz kljuca je neuspesan!");
		            	Poruka.setForeground(Color.RED);
		            	Poruka.setVisible(true);
		            	frame.pack();
			        }
		        }
		        // if the user cancelled the operation
		        else
		        {
		        	Poruka.setText("Putanja mora biti odabrana!");
	            	Poruka.setForeground(Color.RED);
	            	Poruka.setVisible(true);
	            	frame.pack();
		        }
			}
	});
	
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

}
