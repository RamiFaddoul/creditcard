import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class ChangerApp extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboprod;
	private JSpinner limite;
	private JButton okButton;
	private JButton cancelButton;
    private PanelListeApplication panel;
    private int idapp;
    private int lim;
    private Vector<Integer> idproduit=new Vector<Integer>();
	public ChangerApp(int id,int lim,PanelListeApplication p) {
		idapp=id;
		panel=p;
		this.lim=lim;
		setBounds(100, 100, 265, 153);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		comboprod = new JComboBox();
		comboprod.setBounds(128, 11, 103, 20);
		contentPanel.add(comboprod);
		
		Label label = new Label("Produit");
		label.setBounds(10, 11, 78, 22);
		contentPanel.add(label);
		
		limite = new JSpinner();
		limite.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(100)));
		limite.setBounds(128, 42, 103, 20);
		contentPanel.add(limite);
		
		Label label_1 = new Label("Limite de d\u00E9penses");
		label_1.setBounds(10, 40, 112, 22);
		contentPanel.add(label_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
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
		loadProduits();
	}
	public void loadProduits(){
		Statement s;
		ResultSet res;
		try{
			s=FenetrePrincipale.connection.createStatement();
			res=s.executeQuery("select * from produit where actif='V' and idbanque="+FenetrePrincipale.IdBanque);
			
			while(res.next()){
				
			idproduit.add(Integer.parseInt(res.getString("IDPRODUIT")));
			
			comboprod.addItem(res.getString("NOMPRODUIT"));
			//depenserange.add(new Pair(Integer.parseInt(res.getString("LIMITEDEPENSEMIN")),Integer.parseInt(res.getString("LIMITEDEPENSEMAX"))));
			}
			//limitdepenses.setModel(new SpinnerNumberModel( depenserange.get(choiceTypeProduit.getSelectedIndex()).getmin() , depenserange.get(choiceTypeProduit.getSelectedIndex()).getmin() , depenserange.get(choiceTypeProduit.getSelectedIndex()).getmax(),100 ));
            limite.setValue(new Integer(lim));
		}
		catch(Exception exc){
		
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Statement s;
		if(e.getSource()==okButton){
			try{
			s=FenetrePrincipale.connection.createStatement();
	    	  s.execute("update APPLICATION set IDPRODUIT='"+idproduit.get(comboprod.getSelectedIndex())+"',LIMITEDEPENSE='"+limite.getValue().toString()+"' where IDAPPLICATION="+idapp+" and idbanque="+FenetrePrincipale.IdBanque);
			 this.dispose();
			 panel.reload();
			}catch(Exception exc){System.out.println(exc.getMessage());}
			
	 }else if(e.getSource()==cancelButton){
			this.dispose();
		}
	}
}
