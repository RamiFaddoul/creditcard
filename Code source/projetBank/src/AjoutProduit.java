import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Choice;


public class AjoutProduit extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JButton okButton;
	private JLabel label;
	private JTextField textProduit;
	private JLabel label_1;
	private JTextField textScoreMin;
	private JLabel label_2;
	private JTextField textLDMax;
	private JLabel label_3;
	private JTextField textLDMin;
	private JLabel label_4;
	private JTextField textFromRange;
	private JTextField textToRange;
	private JLabel label_5;
    private TablePanel table;
    private int index=-1;
    private Choice choiceActif;
    private JLabel lblNewLabel;
	public AjoutProduit(TablePanel t) {
		table=t;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			label = new JLabel("Nom produit");
			label.setBounds(23, 16, 119, 14);
			contentPanel.add(label);
		}
		{
			textProduit = new JTextField();
			textProduit.setColumns(10);
			textProduit.setBounds(176, 11, 113, 20);
			contentPanel.add(textProduit);
		}
		{
			label_1 = new JLabel("Score minimal requis");
			label_1.setBounds(23, 46, 143, 14);
			contentPanel.add(label_1);
		}
		{
			textScoreMin = new JTextField();
			textScoreMin.setColumns(10);
			textScoreMin.setBounds(176, 42, 113, 20);
			contentPanel.add(textScoreMin);
		}
		{
			label_2 = new JLabel("Limite d\u00E9pense maximal");
			label_2.setBounds(23, 76, 143, 14);
			contentPanel.add(label_2);
		}
		{
			textLDMax = new JTextField();
			textLDMax.setColumns(10);
			textLDMax.setBounds(176, 73, 113, 20);
			contentPanel.add(textLDMax);
		}
		{
			label_3 = new JLabel("Limite d\u00E9pense minimal");
			label_3.setBounds(23, 106, 143, 14);
			contentPanel.add(label_3);
		}
		{
			textLDMin = new JTextField();
			textLDMin.setColumns(10);
			textLDMin.setBounds(176, 104, 113, 20);
			contentPanel.add(textLDMin);
		}
		{
			label_4 = new JLabel("From range");
			label_4.setBounds(23, 136, 143, 14);
			contentPanel.add(label_4);
		}
		{
			textFromRange = new JTextField();
			textFromRange.setColumns(10);
			textFromRange.setBounds(176, 135, 113, 20);
			contentPanel.add(textFromRange);
		}
		{
			textToRange = new JTextField();
			textToRange.setColumns(10);
			textToRange.setBounds(176, 166, 113, 20);
			contentPanel.add(textToRange);
		}
		{
			label_5 = new JLabel("To range");
			label_5.setBounds(23, 166, 143, 14);
			contentPanel.add(label_5);
		}
		{
			choiceActif = new Choice();
			choiceActif.setBounds(176, 197, 113, 20);
			choiceActif.add("Oui");
			choiceActif.add("Non");
			contentPanel.add(choiceActif);
		}
		{
			lblNewLabel = new JLabel("Actif");
			lblNewLabel.setBounds(23, 196, 89, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void remplir(int i){
		textProduit.setText(table.getRow(i).get(0).toString());
	       textScoreMin.setText(table.getRow(i).get(1).toString());
	       textLDMax.setText(table.getRow(i).get(2).toString());
	       textLDMin.setText(table.getRow(i).get(3).toString());
	       textFromRange.setText(table.getRow(i).get(4).toString());
	       textToRange.setText(table.getRow(i).get(5).toString());
	       if(choiceActif.getItem(0)==table.getRow(i).get(6).toString()) choiceActif.select(0);
	       else choiceActif.select(1);
	       textFromRange.setEditable(false);
	       textToRange.setEditable(false);
	       index=i;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==okButton){
				
			 Vector<Object> vect =new Vector<Object>();
		       vect.add(textProduit.getText());
		       vect.add(textScoreMin.getText());
		       vect.add(textLDMax.getText());
		       vect.add(textLDMin.getText());
		       vect.add(textFromRange.getText());
		       vect.add(textToRange.getText());
		       vect.add(choiceActif.getSelectedItem());
		       if(index==-1)   table.addRow(vect);
		       else {
		    	   table.setRow(index, vect);
		       }
		       try{
		          Statement s;
				  ResultSet res;
				  s=FenetrePrincipale.connection.createStatement();
		    	 if(index==-1)
		    	  {
				     char actif='F';
		    	     if(choiceActif.getSelectedItem()=="Oui") actif='V';
		             s.execute("insert into Produit values(seq_idproduit.nextval,'"+FenetrePrincipale.IdBanque+"','"+textProduit.getText()+"','"+Integer.parseInt(textScoreMin.getText())+"','"+Integer.parseInt(textLDMax.getText())+"','"+Integer.parseInt(textLDMin.getText())+"','"+Integer.parseInt(textFromRange.getText())+"','"+textToRange.getText()+"','"+actif+"',"+Integer.parseInt(textFromRange.getText())+")");
		             res=s.executeQuery("select seq_idproduit.currval from dual");
		            res.next();
		             FenetrePrincipale.indexVect.add(res.getString("CURRVAL"));
		    	  }
		    	  else
		    	  { 
		    		char actif='F';
		    		if(choiceActif.getSelectedItem()=="Oui") actif='V';
		    	    System.out.println(index);
		    		res=s.executeQuery("update Produit set nomproduit='"+textProduit.getText()+"',scoreminimalrequis='"+Integer.parseInt(textScoreMin.getText())+"',Limitedepensemax='"+Integer.parseInt(textLDMax.getText())+"',Limitedepensemin='"+Integer.parseInt(textLDMax.getText())+"',fromrange='"+Integer.parseInt(textFromRange.getText())+"',torange='"+Integer.parseInt(textToRange.getText())+"',actif='"+actif+"' where idproduit='"+FenetrePrincipale.indexVect.get(index)+"' and idbanque='"+FenetrePrincipale.IdBanque+"' ");
		    	  }
		       }
		       catch(SQLException e){System.out.println("erreursql");}
		
		
		
		
		       this.dispose();
		}
		else if(arg0.getSource()==cancelButton)
		{
			this.dispose();
		}
	}

}
