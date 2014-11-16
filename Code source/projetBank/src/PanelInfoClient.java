import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import java.awt.Label;
import java.awt.Button;
import java.awt.Font;


public class PanelInfoClient extends JPanel implements ActionListener {
	 private TablePanel table;
	 private TablePanel stat;
	 private Vector<Choix> vect=new Vector<Choix>();
	 private JButton btnSearch; 
	 private JButton btnInfo;
	 private Label label_1;
	 private Label label_2;
	 private Label label_3;
	 private Label label_4;
	 private Vector<Long> vectid=new Vector<Long>();
	 private JButton btnGenerer;
	 private Choice choiceGroupBy;
	public PanelInfoClient() {
		  setLayout(null);
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("ID");
	     columnNames.add("Nom");
	  	 columnNames.add("Prenom");
		 columnNames.add("Sexe");
		 columnNames.add("Date de naissance");
		 columnNames.add("Numero Telephone");
		 columnNames.add("Profession");
	   
	
	     
	     table = new TablePanel(columnNames);
	     table.setBounds(10, 207, 687, 286);
	     add(table);
	     
	     Vector<String> column = new Vector<String>();
	     column.add("Group by clause");
	     column.add("Moyenne Dépenses");
	     column.add("% Remboursement");
	     stat = new TablePanel(column);
	     stat.setBounds(764, 207, 360, 286);
	     add(stat);
		
		btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(596, 173, 101, 23);
		add(btnSearch);
		
		btnInfo = new JButton("Informations d\u00E9taill\u00E9es");
		btnInfo.addActionListener(this);
		btnInfo.setBounds(10, 504, 182, 23);
		add(btnInfo);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(UIManager.getBorder("Button.border"));
		verticalBox.setBounds(10, 11, 687, 120);
		add(verticalBox);
		Choix choix_1 = new Choix();
		verticalBox.add(choix_1);
		vect.add(choix_1);
		Choix choix_2 = new Choix();
		verticalBox.add(choix_2);
		vect.add(choix_2);
		Choix choix_3 = new Choix();
		verticalBox.add(choix_3);
		vect.add(choix_3);
		Choix choix_4 = new Choix();
		verticalBox.add(choix_4);
		
		Choix choix = new Choix();
		verticalBox.add(choix);
		vect.add(choix);
		
		choiceGroupBy = new Choice();
		for(GroupBy g:GroupBy.values()){
			choiceGroupBy.add(g.toString());
		}
		choiceGroupBy.setBounds(850, 174, 163, 20);
		add(choiceGroupBy);
		
		Label label = new Label("Group By :");
		label.setBounds(764, 173, 80, 22);
		add(label);
		
		btnGenerer = new JButton("G\u00E9nerer");
		btnGenerer.setEnabled(false);
		btnGenerer.addActionListener(this);
		btnGenerer.setBounds(1035, 173, 89, 23);
		add(btnGenerer);
		
		label_1 = new Label("Moyenne des d\u00E9penses mensuelles :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setVisible(false);
		label_1.setBounds(764, 26, 222, 22);
		add(label_1);
		
		label_2 = new Label("New label");
		label_2.setVisible(false);
		label_2.setBounds(1010, 26, 62, 22);
		add(label_2);
		
		label_3 = new Label("Pourcentage de remboursement :");
		label_3.setFont(new Font("Dialog", Font.BOLD, 12));
		label_3.setVisible(false);
		label_3.setBounds(782, 71, 222, 22);
		add(label_3);
		
		label_4 = new Label("New label");
		label_4.setVisible(false);
		label_4.setBounds(1010, 71, 62, 22);
		add(label_4);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSearch){
			try{
				table.empty();
				stat.empty();
				vectid.clear();
				label_1.setVisible(true);
				label_2.setVisible(true);
				label_3.setVisible(true);
				label_4.setVisible(true);
				btnGenerer.setEnabled(true);
				String q="";
				Statement s=FenetrePrincipale.connection.createStatement();
				for(int i=0;i<vect.size();i++){
					if(vect.get(i).isSelected()){
						q+=" and "+vect.get(i).getQuery();
					}
				}
				ResultSet res=s.executeQuery("select unique client.idclient,INFORMATIONSPERSONELLES.NOM,INFORMATIONSPERSONELLES.PRENOM,INFORMATIONSPERSONELLES.SEXE," +
						"to_char(INFORMATIONSPERSONELLES.DATENAISSANCE,'DD-MON-YYYY'),INFORMATIONSPERSONELLES.NUMEROTELEPHONEMOBILE,INFORMATIONSEMPLOI.PROFESSION" +
						" from client,banque,INFORMATIONSPERSONELLES,ADDRESSE,COMPTE,INFORMATIONSEMPLOI,INFORMATIONSFINANCIERES where " +
						"banque.idbanque="+FenetrePrincipale.IdBanque+" and client.idbanque = banque.idbanque and  client.idip=INFORMATIONSPERSONELLES.IDIP " +
						"and INFORMATIONSPERSONELLES.idadresse=addresse.idadresse and compte.idclient = client.idclient and " +
						"INFORMATIONSEMPLOI.idemploi=client.idemploi and INFORMATIONSEMPLOI.IDREVENU=INFORMATIONSFINANCIERES.IDREVENU "+q);
			
				while(res.next()){
					  Vector<Object> vect=new Vector<Object>();
					   vect.add(res.getString(1));
					   vect.add(res.getString(2));
					   vect.add(res.getString(3));
					   vect.add(res.getString(4));
					   vect.add(res.getString(5));
					   vect.add(res.getString(6));
					   vect.add(res.getString(7));
					   vectid.add(Long.parseLong(res.getString(1)));
					   table.addRow(vect);
				}
				if(vectid.size()==0){
					label_1.setVisible(false);
				label_2.setVisible(false);
				label_3.setVisible(false);
				label_4.setVisible(false);
				btnGenerer.setEnabled(false);
			}
			     String list="";
				for(int i=0;i<vectid.size();i++){
					if(i==0)list+=" client.IDCLIENT='"+vectid.get(i)+"'";
					else list+=" or client.IDCLIENT='"+vectid.get(i)+"'";
				}
				res=s.executeQuery("select Round(avg(depense),2)as avg,Round((sum(REMBOURSEMENT)/sum(DEPENSE))*100,2) as prct from DEPENSE_REMBOURSEMENTCOMPTE,client,banque,INFORMATIONSPERSONELLES,ADDRESSE,COMPTE,INFORMATIONSEMPLOI where "+ 
						"Banque.idbanque="+FenetrePrincipale.IdBanque+" and client.idbanque = banque.idbanque and  client.idip=INFORMATIONSPERSONELLES.IDIP"+
						" and INFORMATIONSPERSONELLES.idadresse=addresse.idadresse and compte.idclient = client.idclient and COMPTE.NUMEROCOMPTE=DEPENSE_REMBOURSEMENTCOMPTE.NUMEROCOMPTE"+ 
						" and INFORMATIONSEMPLOI.idemploi=client.idemploi and ("+list+")");
				res.next();
				label_2.setText(res.getString("avg"));
				label_4.setText(res.getString("prct")+" %");
			}
			catch(Exception exc){
				System.out.println(exc.getMessage());
			}
		}
		else if(e.getSource()==btnInfo){
			try{InfoClient info=new InfoClient(Long.parseLong(table.getRow(table.getSelectedRowindex()).get(0).toString()));
			info.setVisible(true);}
			catch(Exception exc){
				System.out.println(exc.getMessage());
			}
		}else if(e.getSource()==btnGenerer){
			try{
				stat.empty();
				Statement s=FenetrePrincipale.connection.createStatement();
				String list="";
				for(int i=0;i<vectid.size();i++){
					if(i==0)list+=" client.IDCLIENT='"+vectid.get(i)+"'";
					else list+=" or client.IDCLIENT='"+vectid.get(i)+"'";
				}
				String q="";
				for(GroupBy g:GroupBy.values()){
					if(choiceGroupBy.getSelectedItem().equals(g.toString())){
						q=g.getQuery();break;
					}
					}
				ResultSet res=s.executeQuery("select "+q+",Round(avg(depense),2)as avg,Round((sum(REMBOURSEMENT)/sum(DEPENSE))*100,2) as prct from DEPENSE_REMBOURSEMENTCOMPTE,client,banque,INFORMATIONSPERSONELLES,ADDRESSE,COMPTE,INFORMATIONSEMPLOI,INFORMATIONSFINANCIERES where INFORMATIONSEMPLOI.IDREVENU=INFORMATIONSFINANCIERES.IDREVENU and "+ 
						"Banque.idbanque="+FenetrePrincipale.IdBanque+" and client.idbanque = banque.idbanque and  client.idip=INFORMATIONSPERSONELLES.IDIP"+
						" and INFORMATIONSPERSONELLES.idadresse=addresse.idadresse and compte.idclient = client.idclient and COMPTE.NUMEROCOMPTE=DEPENSE_REMBOURSEMENTCOMPTE.NUMEROCOMPTE"+ 
						" and INFORMATIONSEMPLOI.idemploi=client.idemploi and ("+list+") Group by "+q+" order by "+q);
				while(res.next()){
					 Vector<Object> vect=new Vector<Object>();
					 vect.add(res.getString(1));
					 vect.add(res.getString(2));
					 vect.add(res.getString(3));
					 stat.addRow(vect);
				}
				
			}catch(Exception exc){
				System.out.println(exc.getMessage());
			}
		}
	}
}
