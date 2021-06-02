package etf.openpgp.sr170398dsl170423d.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.awt.Insets;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import etf.openpgp.sr170398dsl170423d.gui.dtos.BrisanjeKljucevaDtos;
import etf.openpgp.sr170398dsl170423d.gui.dtos.SlanjePorukeDtos;
import etf.openpgp.sr170398dsl170423d.impl.Constants;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;

public class SlanjePoruke {
	//[Podatak za obradu]
	//----------------------------
	public SlanjePorukeDtos data;
	private ArrayList<RingOutput> selectedDataPublicKeys;
	private RingOutput selectedDataPrivateKey;
	private String Lozinka;
	//----------------------------
	Projekat p;
	JFrame frame;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JLabel lblNewLabel_5;
	private JPasswordField passwordField;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JButton btnNewButton;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
    private JComboBox<RingOutput> cb;
    private JCheckBox chckbxNewCheckBox_6;
    private JCheckBox chckbxNewCheckBox_7;
    private JComboBox comboBox;
    private JList jList;
    private JScrollPane scrollPane_1;
    private JLabel Poruka;
    private JButton btnNewButton_1;
    private JTextField textField;
    private JLabel lblNewLabel_6;
    private JCheckBox chckbxNewCheckBox_8;
    private JCheckBox chckbxNewCheckBox_9;
	/**
	 * Create the application.
	 */
	public SlanjePoruke(Projekat p) {
		this.data = new SlanjePorukeDtos();
		this.selectedDataPublicKeys = new ArrayList<RingOutput>();
		this.selectedDataPrivateKey = null;
		this.Lozinka = "";
		this.p = p;
		initialize();	
	}

	private void resizeForm(JFrame myFrame, JTable t) {	
		myFrame.pack();
		myFrame.setLocation(new Point(p.getX()+p.getW()+50,p.getY()-220));
		myFrame.setVisible(true);
		myFrame.setResizable(true);
	}
	private void DisplayAutenticationParam(Boolean visible) {
		passwordField.setVisible(visible);
		lblNewLabel_5.setVisible(visible);
		lblNewLabel_1.setVisible(visible);
		cb.setVisible(visible);
	}
	private void DisplaySifrovanjeParam(Boolean visible) {
		lblNewLabel.setVisible(visible);
		chckbxNewCheckBox_6.setVisible(visible);
		chckbxNewCheckBox_7.setVisible(visible);
		lblNewLabel_2.setVisible(visible);
		scrollPane_1.setVisible(visible);
	}
	
	private boolean checkDataSelected() {
		if((!chckbxNewCheckBox_8.isSelected() && !chckbxNewCheckBox_9.isSelected()) || 
				(chckbxNewCheckBox_8.isSelected() && ( selectedDataPublicKeys.size()==0 || (!chckbxNewCheckBox_6.isSelected() && !chckbxNewCheckBox_7.isSelected())))) {
			System.out.println("error1");
			return false;
		}
		if((chckbxNewCheckBox_2.isSelected() && (selectedDataPrivateKey==null || passwordField.getText().equals(""))) ||(!chckbxNewCheckBox_2.isSelected() && !chckbxNewCheckBox_3.isSelected()) ) {
			System.out.println("error2");
			return false;
		}
		if(!chckbxNewCheckBox.isSelected() && !chckbxNewCheckBox_1.isSelected()) {
			System.out.println("error3");
			return false;
		}
		if(!chckbxNewCheckBox_4.isSelected() && !chckbxNewCheckBox_5.isSelected()) {
			System.out.println("error4");
			return false;
		}
		if(textField.getText().equals("") || textField.getText().equals("Obavezno je odabrati destinaciju fajla"))
		{
			System.out.println("error5");
			return false;
		}else
		return true;
	}
/**
 * Initialize the contents of the frame.
 */
private void initialize() {
	// Frame initiallization
    frame = new JFrame();


    
    panel_1 = new JPanel();
    panel_1.setBackground(Color.LIGHT_GRAY);
    frame.getContentPane().add(panel_1, BorderLayout.CENTER);
    GridBagLayout gbl_panel_1 = new GridBagLayout();
    gbl_panel_1.columnWidths = new int[]{161, 161, 161, 0};
    gbl_panel_1.rowHeights = new int[]{0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
    gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    panel_1.setLayout(gbl_panel_1);
    
    lblNewLabel_4 = new JLabel("Slanje poruke");
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 30));
    lblNewLabel_4.setBackground(Color.RED);
    GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
    gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_4.gridx = 1;
    gbc_lblNewLabel_4.gridy = 0;
    panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
    
    lblNewLabel_6 = new JLabel("SIFROVANJE");
    lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
    lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
    GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
    gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
    gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_6.gridx = 0;
    gbc_lblNewLabel_6.gridy = 2;
    panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
    
    chckbxNewCheckBox_8 = new JCheckBox("DA");
    chckbxNewCheckBox_8.setBackground(Color.LIGHT_GRAY);
    GridBagConstraints gbc_chckbxNewCheckBox_8 = new GridBagConstraints();
    gbc_chckbxNewCheckBox_8.insets = new Insets(0, 0, 5, 5);
    gbc_chckbxNewCheckBox_8.gridx = 1;
    gbc_chckbxNewCheckBox_8.gridy = 2;
    panel_1.add(chckbxNewCheckBox_8, gbc_chckbxNewCheckBox_8);
    
    

    
    chckbxNewCheckBox_9 = new JCheckBox("NE");
    chckbxNewCheckBox_9.setBackground(Color.LIGHT_GRAY);
    GridBagConstraints gbc_chckbxNewCheckBox_9 = new GridBagConstraints();
    gbc_chckbxNewCheckBox_9.insets = new Insets(0, 0, 5, 0);
    gbc_chckbxNewCheckBox_9.gridx = 2;
    gbc_chckbxNewCheckBox_9.gridy = 2;
    panel_1.add(chckbxNewCheckBox_9, gbc_chckbxNewCheckBox_9);
    
  
    lblNewLabel = new JLabel("ALGORITAM");
    lblNewLabel.setVisible(true);
    lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
    gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 3;
    panel_1.add(lblNewLabel, gbc_lblNewLabel);
    
    chckbxNewCheckBox_6 = new JCheckBox("3DES");
    chckbxNewCheckBox_6.setVisible(true);
    chckbxNewCheckBox_6.setBackground(Color.LIGHT_GRAY);
    GridBagConstraints gbc_chckbxNewCheckBox_6 = new GridBagConstraints();
    gbc_chckbxNewCheckBox_6.insets = new Insets(0, 0, 5, 5);
    gbc_chckbxNewCheckBox_6.gridx = 1;
    gbc_chckbxNewCheckBox_6.gridy = 3;
    panel_1.add(chckbxNewCheckBox_6, gbc_chckbxNewCheckBox_6);
    
    chckbxNewCheckBox_6.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		if(chckbxNewCheckBox_7.isSelected())
    		{			
    			chckbxNewCheckBox_7.setSelected(false);
    			
    		}
    	}
    });
    
    chckbxNewCheckBox_7 = new JCheckBox("CAST5");
    chckbxNewCheckBox_7.setVisible(true);
    chckbxNewCheckBox_7.setBackground(Color.LIGHT_GRAY);
    GridBagConstraints gbc_chckbxNewCheckBox_7 = new GridBagConstraints();
    gbc_chckbxNewCheckBox_7.insets = new Insets(0, 0, 5, 0);
    gbc_chckbxNewCheckBox_7.gridx = 2;
    gbc_chckbxNewCheckBox_7.gridy = 3;
    panel_1.add(chckbxNewCheckBox_7, gbc_chckbxNewCheckBox_7);
    
    chckbxNewCheckBox_7.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if(chckbxNewCheckBox_6.isSelected())
    		{
    			chckbxNewCheckBox_6.setSelected(false);
    			System.out.println("CAST5");
    		}
    	}
    });
    
   
    lblNewLabel_2 = new JLabel("JAVNI KLJUCEVI");
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
    GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
    gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
    gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
    gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_2.gridx = 0;
    gbc_lblNewLabel_2.gridy = 4;
    panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
    
	ArrayList<RingOutput> ret = p.b.showPublicKeyRingCollection();
	
	RingOutput[] choices = new RingOutput[ret.size()];
	for(int i=0; i< ret.size();i++)
	{
		choices[i] = ret.get(i);
	}
    
    jList = new JList<RingOutput>(choices);
    jList.setFixedCellHeight(15);
    jList.setFixedCellWidth(200);
    jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    jList.setVisibleRowCount(5);
    
    MouseListener mouseListener = (MouseListener) new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
            	selectedDataPublicKeys = (ArrayList<RingOutput>) jList.getSelectedValuesList();
              
               System.out.println(selectedDataPublicKeys+"\n************");

             }
        }
    };
    jList.addMouseListener(mouseListener);
    
     
     scrollPane_1 = new JScrollPane(jList);
     GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
     gbc_scrollPane_1.gridwidth = 2;
     gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
     gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
     gbc_scrollPane_1.gridx = 1;
     gbc_scrollPane_1.gridy = 4;
     panel_1.add(scrollPane_1, gbc_scrollPane_1);
    
    lblNewLabel_3 = new JLabel("AUTENTIKACIJA");
    lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
    lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
    GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
    gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
    gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_3.fill = GridBagConstraints.VERTICAL;
    gbc_lblNewLabel_3.gridx = 0;
    gbc_lblNewLabel_3.gridy = 5;
    panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
    
    chckbxNewCheckBox_2 = new JCheckBox("DA");
    chckbxNewCheckBox_2.setBackground(Color.LIGHT_GRAY);
    GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
    gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 5, 5);
    gbc_chckbxNewCheckBox_2.gridx = 1;
    gbc_chckbxNewCheckBox_2.gridy = 5;
    panel_1.add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);
    
    chckbxNewCheckBox_3 = new JCheckBox("NE");
    chckbxNewCheckBox_3.setBackground(Color.LIGHT_GRAY);
    GridBagConstraints gbc_chckbxNewCheckBox_3 = new GridBagConstraints();
    gbc_chckbxNewCheckBox_3.insets = new Insets(0, 0, 5, 0);
    gbc_chckbxNewCheckBox_3.gridx = 2;
    gbc_chckbxNewCheckBox_3.gridy = 5;
    panel_1.add(chckbxNewCheckBox_3, gbc_chckbxNewCheckBox_3);
    
    chckbxNewCheckBox_2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		DisplayAutenticationParam(true);
    		frame.pack();
    		if(chckbxNewCheckBox_3.isSelected())
    		{			
    			chckbxNewCheckBox_3.setSelected(false);
    			System.out.println("DA");
    		}
    	}
    });
    
    chckbxNewCheckBox_3.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		DisplayAutenticationParam(false);
    		frame.pack();
    		if(chckbxNewCheckBox_2.isSelected())
    		{
    			chckbxNewCheckBox_2.setSelected(false);
    			System.out.println("NE");
    		}
    	}
    });
    
    lblNewLabel_1 = new JLabel("PRIVATNI KLJUC");
    lblNewLabel_1.setForeground(Color.BLUE);
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
    lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblNewLabel_1.setVisible(false);
    GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
    gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
    gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_1.gridx = 0;
    gbc_lblNewLabel_1.gridy = 6;
    panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
    
	ArrayList<RingOutput> ret2 = p.b.showPrivateKeyRingCollection();
	RingOutput[] choices2 = new RingOutput[ret2.size()];
	for(int i=0; i< ret2.size();i++)
	{
		choices2[i] = ret2.get(i);
	}
	
	cb = new JComboBox<RingOutput>(choices2);
	cb.setPreferredSize(new Dimension(400,20));
	cb.setVisible(false);
	GridBagConstraints gbc_cb_1 = new GridBagConstraints();
	gbc_cb_1.gridwidth = 3;
	gbc_cb_1.anchor = GridBagConstraints.WEST;
	gbc_cb_1.insets = new Insets(0, 0, 5, 5);
	gbc_cb_1.gridx = 1;
	gbc_cb_1.gridy = 6;
	panel_1.add(cb, gbc_cb_1);
	
	cb.addActionListener (new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	        if(cb.getSelectedItem() instanceof RingOutput) {
	        	RingOutput item = (RingOutput) cb.getSelectedItem();
	        	//System.out.println(p.b.removeKeyPair(item.getKeyID(),txtunesiLozinku.getText()));
	  
	        	selectedDataPrivateKey = item;
	        	System.out.println("PRIVATE KEY->"+selectedDataPrivateKey);
	        }else
	        	System.out.println(false);
	        
	    }
	});
	
	lblNewLabel_5 = new JLabel("LOZINKA");
	lblNewLabel_5.setForeground(Color.BLUE);
	lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
	lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblNewLabel_5.setVisible(false);
	GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
	gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_5.gridx = 0;
	gbc_lblNewLabel_5.gridy = 7;
	panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
	
	passwordField = new JPasswordField();
	passwordField.setVisible(false);
	GridBagConstraints gbc_passwordField = new GridBagConstraints();
	gbc_passwordField.insets = new Insets(0, 0, 5, 0);
	gbc_passwordField.gridwidth = 2;
	gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
	gbc_passwordField.gridx = 1;
	gbc_passwordField.gridy = 7;
	panel_1.add(passwordField, gbc_passwordField);
	
	lblNewLabel_7 = new JLabel("KOMPRESIJA");
	lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
	lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
	gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_7.gridx = 0;
	gbc_lblNewLabel_7.gridy = 8;
	panel_1.add(lblNewLabel_7, gbc_lblNewLabel_7);
	
	chckbxNewCheckBox = new JCheckBox("DA");
	chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
	GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
	gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
	gbc_chckbxNewCheckBox.gridx = 1;
	gbc_chckbxNewCheckBox.gridy = 8;
	panel_1.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
	
	chckbxNewCheckBox_1 = new JCheckBox("NE");
	chckbxNewCheckBox_1.setBackground(Color.LIGHT_GRAY);
	GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
	gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxNewCheckBox_1.gridx = 2;
	gbc_chckbxNewCheckBox_1.gridy = 8;
	panel_1.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
	
	 chckbxNewCheckBox.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(chckbxNewCheckBox_1.isSelected())
	    		{			
	    			chckbxNewCheckBox_1.setSelected(false);
	    			System.out.println("DA");
	    		}
	    	}
	    });
	    
    chckbxNewCheckBox_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if(chckbxNewCheckBox.isSelected())
    		{
    			chckbxNewCheckBox.setSelected(false);
    			System.out.println("NE");
    		}
    	}
    });
	
	lblNewLabel_8 = new JLabel("RADIX KONVERZIJA");
	lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
	lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
	gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_8.gridx = 0;
	gbc_lblNewLabel_8.gridy = 10;
	panel_1.add(lblNewLabel_8, gbc_lblNewLabel_8);
	
	chckbxNewCheckBox_4 = new JCheckBox("DA");
	chckbxNewCheckBox_4.setBackground(Color.LIGHT_GRAY);
	GridBagConstraints gbc_chckbxNewCheckBox_4 = new GridBagConstraints();
	gbc_chckbxNewCheckBox_4.insets = new Insets(0, 0, 5, 5);
	gbc_chckbxNewCheckBox_4.gridx = 1;
	gbc_chckbxNewCheckBox_4.gridy = 10;
	panel_1.add(chckbxNewCheckBox_4, gbc_chckbxNewCheckBox_4);
	
	chckbxNewCheckBox_5 = new JCheckBox("NE");
	chckbxNewCheckBox_5.setBackground(Color.LIGHT_GRAY);
	GridBagConstraints gbc_chckbxNewCheckBox_5 = new GridBagConstraints();
	gbc_chckbxNewCheckBox_5.insets = new Insets(0, 0, 5, 0);
	gbc_chckbxNewCheckBox_5.gridx = 2;
	gbc_chckbxNewCheckBox_5.gridy = 10;
	panel_1.add(chckbxNewCheckBox_5, gbc_chckbxNewCheckBox_5);
	
	btnNewButton_1 = new JButton("ODABERI PUTANJU FAJLA\r\n");
	btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
	gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
	gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton_1.gridx = 0;
	gbc_btnNewButton_1.gridy = 11;
	panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
	
	btnNewButton_1.addActionListener(new ActionListener() {		
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
	        	textField.setText("Obavezno je odabrati destinaciju fajla");
	        	textField.setForeground(Color.red);
	        	frame.pack();
	        }
	
		}
	});
	
	textField = new JTextField();
	textField.setEditable(false);
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.gridwidth = 2;
	gbc_textField.insets = new Insets(0, 0, 5, 0);
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.gridx = 1;
	gbc_textField.gridy = 11;
	panel_1.add(textField, gbc_textField);
	textField.setColumns(10);
	
	btnNewButton = new JButton("Enkriptuj");
	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
	gbc_btnNewButton.gridx = 1;
	gbc_btnNewButton.gridy = 12;
	panel_1.add(btnNewButton, gbc_btnNewButton);
	
	//[ENKRIPCIJA KLJUCA]
	btnNewButton.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(checkDataSelected())
				{
					boolean encrypt = chckbxNewCheckBox_8.isSelected() ? true:false;
					boolean signature = chckbxNewCheckBox_2.isSelected() ? true:false;
					boolean zip = chckbxNewCheckBox.isSelected() ? true:false;
					boolean radix64 = chckbxNewCheckBox_4.isSelected()?true:false;
					String symmetricAlgortihm = chckbxNewCheckBox_6.isSelected()? Constants.TripleDES:Constants.CAST5;
					long secretKeyID = chckbxNewCheckBox_2.isSelected() ? selectedDataPrivateKey.getKeyID() : 0;
					long[] publicKeyIDs = null;
					
					if(chckbxNewCheckBox_8.isSelected())
					{//postoji elemenata u nizu
						publicKeyIDs = new long[selectedDataPublicKeys.size()];
						for(int i =0; i < selectedDataPublicKeys.size() ; i++)
						{
							publicKeyIDs[i] = selectedDataPublicKeys.get(i).getKeyID();
						}
					}
					String passphrase = passwordField.getText();
					if(p.b.sendMessage(new File(textField.getText()), 
							encrypt, signature, zip, radix64, symmetricAlgortihm, secretKeyID, publicKeyIDs, passphrase))
					{
						Poruka.setText("Poruka ispravno poslata!");
						Poruka.setForeground(Color.GREEN);
						frame.pack();
					}else {
						Poruka.setText("sendMessage metod nije dobro odradio!");
						Poruka.setForeground(Color.RED);
						frame.pack();
					}
				}else {
					Poruka.setText("Neispravno selektovani/uneti podaci!");
					Poruka.setForeground(Color.RED);
					frame.pack();
				}
			
			}catch (Exception e2) {
				
			}
			
		}
	});
	
	chckbxNewCheckBox_8.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		DisplaySifrovanjeParam(true);
       		frame.pack();
       		if(chckbxNewCheckBox_9.isSelected())
       		{			
       			chckbxNewCheckBox_9.setSelected(false);
       			
       			System.out.println("DA");
       		}
       	}
       });
	
	  chckbxNewCheckBox_9.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		DisplaySifrovanjeParam(false);
	    		frame.pack();
	    		if(chckbxNewCheckBox_8.isSelected())
	    		{
	    			chckbxNewCheckBox_8.setSelected(false);
	    			System.out.println("NE");
	    			jList.clearSelection();
	    		}
	    	}
	    });
	  
	  
	chckbxNewCheckBox_4.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(chckbxNewCheckBox_5.isSelected())
	    		{			
	    			chckbxNewCheckBox_5.setSelected(false);
	    			System.out.println("DA");
	    		}
	    	}
	    });
	    
  chckbxNewCheckBox_5.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		if(chckbxNewCheckBox_4.isSelected())
  		{
  			chckbxNewCheckBox_4.setSelected(false);
  			System.out.println("NE");
  		}
  	}
  });
	    
	Poruka = new JLabel("");
	Poruka.setFont(new Font("Tahoma", Font.PLAIN, 20));
	GridBagConstraints gbc_Poruka = new GridBagConstraints();
	gbc_Poruka.insets = new Insets(0, 0, 0, 5);
	gbc_Poruka.gridx = 1;
	gbc_Poruka.gridy = 13;
	panel_1.add(Poruka, gbc_Poruka);
	

	DisplaySifrovanjeParam(false);
    resizeForm(frame,null);
}

}
