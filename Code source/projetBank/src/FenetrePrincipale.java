
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import java.awt.Component;
import java.io.FileInputStream;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.awt.Dialog.ModalExclusionType;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Toolkit;


public class FenetrePrincipale implements ActionListener {

	private JFrame frame;
    private JButton btnNouvApp;
    private JButton btnClients;
    private JButton btnApp;
    private JButton btnProduit;
    private JButton btnScoring;
    private JMenuItem mntmQuitter;
    private JScrollPane mainpanel;
    public static Connection connection=null;
    public static int IdBanque=0;
    public static String username=null;
    public static Vector<Object> indexVect;
    private JMenuItem mntmChangerUtilisateur;
    private JLabel lblNomBanque;
    private JLabel lblUserName;
    private JLabel lblDate;
    private JButton btnPrivileges;
    private LoginDialog login;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale window = new FenetrePrincipale();
					window.frame.setVisible(true);
				    
			

					
				} catch (Exception e) {
				
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetrePrincipale() {

		
		try {
			String url = null;
	        String username = null;
            String password=null;
	    Properties mainProperties = new Properties();
	    FileInputStream file;
	    String path = "./config.txt";
	    file = new FileInputStream(path);
			mainProperties.load(file);
	    file.close();
	    url = mainProperties.getProperty("url");
	    username = mainProperties.getProperty("username");
	    password = mainProperties.getProperty("password");
			DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			connection = DriverManager.getConnection (url,username,password);

			LoginDialog login = new LoginDialog(true);
		    login.setVisible(true);
			initialize();
			
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			  ErreurCnx c=new ErreurCnx();
			   c.setVisible(true);
			   	c.setModal(true);
			
		} 
	
	}


	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	
	
	
	private void initialize()   {
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	  
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       
		frame.getContentPane().setLayout(null);
		
		mainpanel = new JScrollPane();
		mainpanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    mainpanel.setBounds(181, 90, 1171, 604);
		frame.getContentPane().add(mainpanel);
		
		btnNouvApp = new JButton("Nouvelle application");
		btnNouvApp.addActionListener(this);
		btnNouvApp.setBounds(10, 90, 172, 47);
		frame.getContentPane().add(btnNouvApp);
		
		btnProduit = new JButton("Produits");
		btnProduit.addActionListener(this);
		btnProduit.setBounds(10, 270, 172, 47);
		frame.getContentPane().add(btnProduit);
		
		btnScoring = new JButton("Systeme de scoring");
		btnScoring.setBounds(10, 225, 172, 47);
		btnScoring.addActionListener(this);
		frame.getContentPane().add(btnScoring);
		
		btnClients = new JButton("Clients");
		btnClients.addActionListener(this);
		btnClients.setBounds(10, 135, 172, 47);
		frame.getContentPane().add(btnClients);
		
		btnApp = new JButton("Applications");
		btnApp.addActionListener(this);
		btnApp.setBounds(10, 180, 172, 47);
		frame.getContentPane().add(btnApp);
		
		lblNomBanque = new JLabel("Nom Banque");
		lblNomBanque.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNomBanque.setBounds(10, 32, 285, 47);
		frame.getContentPane().add(lblNomBanque);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1362, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmChangerUtilisateur = new JMenuItem("Changer Utilisateur");
		mnFichier.add(mntmChangerUtilisateur);
		mntmChangerUtilisateur.addActionListener(this);
		
		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmQuitter.addActionListener(this);
		mnFichier.add(mntmQuitter);
		
		lblUserName = new JLabel("UserName");
		lblUserName.setBounds(1214, 32, 138, 14);
		frame.getContentPane().add(lblUserName);
		
		DateFormat dateformat=new SimpleDateFormat("EEEE dd/MM/yyyy");
		lblDate = new JLabel(dateformat.format(System.currentTimeMillis()));
		
		
		lblDate.setBounds(1214, 62, 138, 14);
		frame.getContentPane().add(lblDate);
		
		btnPrivileges = new JButton("Utilisateurs");
		btnPrivileges.setBounds(10, 315, 172, 47);
		btnPrivileges.addActionListener(this);
		frame.getContentPane().add(btnPrivileges);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNomBanque, mainpanel, btnNouvApp, btnProduit, btnScoring, btnClients, btnApp, menuBar, mnFichier, mntmQuitter, lblUserName, lblDate}));
	privilege();
		
		
		
	}
	public void privilege()
	{
		Statement s;
		ResultSet res;
		try {
			mainpanel.setViewportView(null);
			btnNouvApp.setContentAreaFilled(true);
			btnClients.setContentAreaFilled(true);
			btnApp.setContentAreaFilled(true);
			btnProduit.setContentAreaFilled(true);
			btnScoring.setContentAreaFilled(true);
			btnPrivileges.setContentAreaFilled(true);
			s=connection.createStatement();
	        res= s.executeQuery("select nomPrivilege from privilege,possede where Username='"
			+username+"' and privilege.IDPrivilege=possede.IDPrivilege ");
	          griseBouton(btnNouvApp,true);
			  griseBouton(btnProduit,true);
		      griseBouton(btnClients,true);
		      griseBouton(btnScoring,true);
		      griseBouton(btnApp,true);
		      griseBouton(btnPrivileges,true);
		  
		    while(res.next()){
		   
			if(res.getString("nomPrivilege").equals("Nouvelle application"))
					{
				     griseBouton(btnNouvApp,false);
					}
			else if(res.getString("nomPrivilege").equals("Clients"))
			{
				griseBouton(btnClients,false);
			     
			}
			else if(res.getString("nomPrivilege").equals("Applications"))
			{
				griseBouton(btnApp,false);
			}
			else if(res.getString("nomPrivilege").equals("Systeme de scoring"))
			{
				griseBouton(btnScoring,false);
			}
			else if(res.getString("nomPrivilege").equals("Produits"))
			{
				griseBouton(btnProduit,false);
			}
			else if(res.getString("nomPrivilege").equals("Utilisateur"))
			{
				griseBouton(btnPrivileges,false);
			}
		 
		  }
	    }
		catch (Exception e1) {}
		
		lblUserName.setText(username);
			
		try{
			s=connection.createStatement();
			res=s.executeQuery("select nombanque from banque where idbanque="+IdBanque);
			res.next();
			lblNomBanque.setText(res.getString("nombanque"));
		}
		catch(Exception e){}

	}
	
	public void actionPerformed(ActionEvent e)
	{
		

		
		 if(e.getSource()==btnNouvApp)
		  {
			System.gc();
			NouvelleApplication app=new  NouvelleApplication();
			mainpanel.setViewportView(app);
			btnNouvApp.setContentAreaFilled(false);
			btnClients.setContentAreaFilled(true);
			btnApp.setContentAreaFilled(true);
			btnProduit.setContentAreaFilled(true);
			btnScoring.setContentAreaFilled(true);
			btnPrivileges.setContentAreaFilled(true);
			
		  } 
			
		  else if(e.getSource()==btnClients)
		  {
			  System.gc();
			PanelInfoClient infoClient=new  PanelInfoClient();
	        mainpanel.setViewportView(infoClient);
			btnNouvApp.setContentAreaFilled(true);
			btnClients.setContentAreaFilled(false);
			btnApp.setContentAreaFilled(true);
			btnProduit.setContentAreaFilled(true);
			btnScoring.setContentAreaFilled(true);
			btnPrivileges.setContentAreaFilled(true);
		
		  }
		  else if(e.getSource()==btnApp)
		  {
			  System.gc();
			 PanelListeApplication app=new  PanelListeApplication();
		     mainpanel.setViewportView(app);
				btnNouvApp.setContentAreaFilled(true);
				btnClients.setContentAreaFilled(true);
				btnApp.setContentAreaFilled(false);
				btnProduit.setContentAreaFilled(true);
				btnScoring.setContentAreaFilled(true);
				btnPrivileges.setContentAreaFilled(true);
			
		  }
		  else if(e.getSource()==btnPrivileges)
		{
			    PanelPrivilege priv =new  PanelPrivilege();
			    mainpanel.setViewportView(priv);
				btnNouvApp.setContentAreaFilled(true);
				btnClients.setContentAreaFilled(true);
				btnApp.setContentAreaFilled(true);
				btnProduit.setContentAreaFilled(true);
				btnScoring.setContentAreaFilled(true);
				btnPrivileges.setContentAreaFilled(false);
			
			priv.setVisible(true);
		
		}
		  else if(e.getSource()==btnProduit)
		  {
			  System.gc();
			 PanelProduit prod =new  PanelProduit();
			 mainpanel.setViewportView(prod);
				btnNouvApp.setContentAreaFilled(true);
				btnClients.setContentAreaFilled(true);
				btnApp.setContentAreaFilled(true);
				btnProduit.setContentAreaFilled(false);
				btnScoring.setContentAreaFilled(true);
				btnPrivileges.setContentAreaFilled(true);
				try{
			          Statement s;
					  ResultSet res;
					  s=FenetrePrincipale.connection.createStatement();
				res=s.executeQuery("select * from produit where idbanque='"+IdBanque+"'");
				
				
				 indexVect=new Vector<Object>();
				
				while(res.next())
				{
					Vector<Object> vect1=new Vector<Object>();
					vect1.add(res.getString("NomProduit"));
					vect1.add(res.getString("scoreminimalrequis"));
					vect1.add(res.getString("limitedepensemax"));
					vect1.add(res.getString("limitedepensemin"));
					vect1.add(res.getString("fromrange"));
					vect1.add(res.getString("torange"));
					String str="Non";
					if((res.getString("actif")).equals("V")) str="Oui";
					
					vect1.add(str);
					
				prod.table.addRow(vect1);
				indexVect.add(res.getString("Idproduit"));
				}
				}
				catch(Exception exc){}
				
		  }
		  else if(e.getSource()==btnScoring)
		  {
			  System.gc();
			 PanelTableScoring scoring=new  PanelTableScoring();
			 
			    mainpanel.setViewportView(scoring);
				btnNouvApp.setContentAreaFilled(true);
				btnClients.setContentAreaFilled(true);
				btnApp.setContentAreaFilled(true);
				btnProduit.setContentAreaFilled(true);
				btnScoring.setContentAreaFilled(false);
				btnPrivileges.setContentAreaFilled(true);
				
		  }else if(e.getSource()==mntmChangerUtilisateur){
			  login.setVisible(true);
			    privilege();
		  }
		  else if(e.getSource()==mntmQuitter){
			  try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
			}
			  frame.dispose();
			  
		  }
		  
		
	}
	
	public void griseBouton(JButton btn,boolean b)
	{
		btn.setEnabled(!b);
	}
}
