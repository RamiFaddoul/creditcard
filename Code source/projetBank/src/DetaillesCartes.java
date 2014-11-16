import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;


public class DetaillesCartes extends JFrame implements ActionListener,ListSelectionListener{
	private TablePanel table;
	private CarteSup sup;
	private long idCompte;
	private JButton btnAct;
	private boolean listener=true;
	private long lastid;
	private JButton btnRenouveler;
	/**
	 * Create the panel.
	 */
	public DetaillesCartes(int idc) {
		idCompte=idc;
		lastid=-1;
		 getContentPane().setLayout(null);
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("ID carte");
	  	 columnNames.add("Début de Validité");
		 columnNames.add("Fin de Validité");
		 columnNames.add("Actif");
		 columnNames.add("Nom sur Carte");
		 columnNames.add("Carte Principale");
		 columnNames.add("Service SMS");
		 columnNames.add("Service Mail");
		 columnNames.add("Service Lettre");

	
	     table = new TablePanel(columnNames);
	     table.setBounds(10, 11, 901, 233);
	     table.addRowSelectionListener(this);
	     getContentPane().add(table);
	     sup=new CarteSup();
	     sup.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	     sup.textNationalite.setEditable(false);
	     sup.textNationalite.setEnabled(true);
	     sup.textnumtel.setEditable(false);
	     sup.textnumtel.setEnabled(true);
	     sup.textnumtelM.setEditable(false);
	     sup.textnumtelM.setEnabled(true);
	     sup.textemail.setEditable(false);
	     sup.textemail.setEnabled(true);
	     sup.textAdr.setEditable(false);
	     sup.textAdr.setEnabled(true);
	     sup.textLien.setEnabled(true);
	     sup.textLien.setEditable(false);
	     sup.textprenom.setEditable(false);
	     sup.textprenom.setEnabled(true);
	     sup.textnom.setEditable(false);
	     sup.textnom.setEnabled(true);
	     sup.setBounds(10, 255, 599, 223);
	     sup.setVisible(false);
	     getContentPane().add(sup);
	     
	     btnAct = new JButton("Active / D\u00E9sactive");
	     btnAct.addActionListener(this);
	     btnAct.setBounds(767, 255, 144, 23);
	     getContentPane().add(btnAct);
	     
	     btnRenouveler = new JButton("Renouveler");
	     btnRenouveler.setBounds(767, 289, 144, 23);
	     btnRenouveler.addActionListener(this);
	     getContentPane().add(btnRenouveler);
	     setResizable(false);
	     load();
	}
	public void load(){
		table.empty();
		Statement s;
		ResultSet res;
		try{
		s=FenetrePrincipale.connection.createStatement();
		res=s.executeQuery("select * from CARTE where CARTE.NUMEROCOMPTE="+idCompte+
				" and CARTE.IDBANQUE="+FenetrePrincipale.IdBanque);
		Vector<Vector<Object>> vect=new Vector<Vector<Object>>();
		
		while(res.next()){
			Vector<Object> v=new Vector<Object>();
		//	int idc=Integer.parseInt(res.getString("IDCARTE"));
			v.add(res.getString("IDCARTE"));
			v.add(res.getString("DATEDEBUTVALIDITE").substring(8, 10)+"-"+res.getString("DATEDEBUTVALIDITE").substring(5, 8)+res.getString("DATEDEBUTVALIDITE").substring(0, 4));
			v.add(res.getString("DATEFINVALIDITE").substring(8, 10)+"-"+res.getString("DATEFINVALIDITE").substring(5, 8)+res.getString("DATEFINVALIDITE").substring(0, 4));
			if(res.getString("ACTIF").equals("V")) v.add("Oui");
			else v.add("Non");
			v.add(res.getString("NOMSURCARTE"));
			if(res.getString("CARTEPRINCIPAL").equals("V")) v.add("Oui");
			else v.add("Non");
			if(res.getString("SERVICESMS").equals("V")) v.add("Oui");
			else v.add("Non");
			if(res.getString("SERVICEMAIL").equals("V")) v.add("Oui");
			else v.add("Non");
			if(res.getString("SERVICELETTRE").equals("V")) v.add("Oui");
			else v.add("Non");
			
			
		vect.add(v);
		}
		
		for(int i=0 ;i <vect.size() ;i++) table.addRow(vect.get(i));
		
		}
		catch(Exception exc){
			//System.out.println(exc.getMessage());
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnAct){
			if(table.getSelectedRowindex()>-1){
				long id=Long.parseLong(table.getRow(table.getSelectedRowindex()).get(0).toString());
				String str=table.getRow(table.getSelectedRowindex()).get(3).toString();
				char c='V';
				if(str.equals("Oui")) c='F';
				Statement s;
				try{
					s=FenetrePrincipale.connection.createStatement();
					s.execute("update carte set actif='"+c+"' where IDCARTE="+id+" and IDBANQUE="+FenetrePrincipale.IdBanque);
				
				}
				catch(Exception exc){}
				listener=false;
				load();
				listener=true;
			}
		}
		else if(arg0.getSource()==btnRenouveler){
			if(table.getSelectedRowindex()>-1){
				try{
				long id=Long.parseLong(table.getRow(table.getSelectedRowindex()).get(0).toString());
				SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
			    SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
			    Date date1 = format1.parse(table.getRow(table.getSelectedRowindex()).get(2).toString());
			    Statement s;
			    s=FenetrePrincipale.connection.createStatement();
				s.execute("update carte set DATEFINVALIDITE=add_months('"+ format2.format(date1)+"',12) where IDCARTE="+id+" and IDBANQUE="+FenetrePrincipale.IdBanque);

				}
				catch(Exception e){System.out.println(e.getMessage());}
				listener=false;
				load();
				listener=true;
			}
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	   if(!listener) return;
	
	   long id=Long.parseLong(table.getRow(table.getSelectedRowindex()).get(0).toString());
	   if(lastid==id)return;
		lastid=id;
		Statement s,s1;
		ResultSet res,res1;
		try{
			s=FenetrePrincipale.connection.createStatement();
			s1=FenetrePrincipale.connection.createStatement();
		    res=s.executeQuery("select CARTEPRINCIPAL from CARTE where CARTE.IDCARTE="+id+
				" and CARTE.IDBANQUE="+FenetrePrincipale.IdBanque);
		    res.next();
		    if(res.getString("CARTEPRINCIPAL").equals("F")){
		    	res1=s1.executeQuery("select * from CARTESUPLEMENTAIRE where CARTESUPLEMENTAIRE.IDCARTE="+id);
		    	res1.next();
		    	sup.textemail.setText(res1.getString("ADDRESSEEMAIL"));
		    	sup.textLien.setText(res1.getString("LEINRELATIVE"));
		    	sup.textNationalite.setText(res1.getString("NATIONALITE"));
		    	sup.textnom.setText(res1.getString("NOM"));
		    	sup.textnumtel.setText(res1.getString("NUMEROTEL"));
		    	sup.textnumtelM.setText(res1.getString("NUMEROTELMOBILE"));
		    	sup.textprenom.setText(res1.getString("PRENOM"));
		    	sup.textAdr.setText(res1.getString("ADDRESSE"));
		    	
		    	if(res1.getString("SEXE").equals("M")) sup.checkBoxM.setSelected(true);
		    	else sup.checkBoxF.setSelected(true);
		    	sup.setVisible(true);
		    }
		    else sup.setVisible(false);
			
		}catch(Exception exc){System.out.println(exc.getMessage());}
	}
	


	

}
