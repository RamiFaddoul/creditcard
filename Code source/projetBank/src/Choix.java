import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.util.Vector;
import java.awt.Window.Type;
import javax.swing.border.TitledBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Choix extends JPanel implements ItemListener {
	 private JTextField textValeur;
	 private Choice choiceValeur;
     private JTextField textValeurMax;
     private Choice choiceCritere;
     private Choice choiceCondition;
	 private boolean selected=false;
	public Choix() {
		choiceCritere= new Choice();
		choiceCritere.setBounds(48, 0, 137, 20);
		for(Critere c:Critere.values()){
			choiceCritere.add(c.toString());
		}
		choiceCondition = new Choice();
		choiceCondition.setBounds(191, 0, 129, 20);
		choiceCondition.setVisible(true);
		textValeurMax = new JTextField();
		textValeurMax.setBounds(493, 0, 187, 20);
		textValeurMax.setVisible(false);
	    textValeur = new JTextField();
	    textValeur.setBounds(326, 0, 157, 20);
	    textValeur.setVisible(true);
	    choiceValeur=new Choice();
	    choiceValeur.setBounds(326, 0, 157, 20);
	    choiceValeur.setVisible(false);
		choiceCritere.addItemListener(this);
		choiceCondition.addItemListener(this);
		choiceCondition.add("égale à");
		choiceCondition.setEnabled(false);
		setLayout(null);
		add(textValeur);
	     add(textValeurMax);
	     add(choiceCritere);
	     add(choiceCondition);
	     add( choiceValeur);
	     JCheckBox chckbxNewCheckBox = new JCheckBox("");
	     chckbxNewCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				  selected=!selected;
		     				
			}
	     });
	     chckbxNewCheckBox.setBounds(6, -1, 36, 23);
	     add(chckbxNewCheckBox);
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==choiceCritere){
		for(Critere c:Critere.values()){
			if( ((Choice)(e.getSource())).getSelectedItem().equals(c.toString()) ){
				if(c.getType().equals("nombre")){
					choiceCondition.removeAll();
					choiceCondition.add("égale à");
					choiceCondition.add("plus petit que");
					choiceCondition.add("plus grand que");
					choiceCondition.add("interval");
					choiceCondition.setEnabled(true);
				    textValeurMax.setVisible(false);
					choiceValeur.setVisible(false);
					textValeur.setVisible(true);
				}
				else if(c.getType().equals("string")){
					choiceCondition.removeAll();
					choiceCondition.add("égale à");
					choiceCondition.setEnabled(false);
					textValeurMax.setVisible(false);
					choiceValeur.setVisible(false);
					textValeur.setVisible(true);
				}
				else if(c.getType().equals("choix")){
					choiceCondition.removeAll();
					choiceCondition.add("égale à");
					choiceCondition.setEnabled(false);
					textValeur.setVisible(false);
					textValeurMax.setVisible(false);
					choiceValeur.setVisible(true);
				      if(choiceCritere.getSelectedItem().equals(Critere.ETAT_CIVIL.toString()))
					{
						choiceValeur.removeAll();
						for(EtatCivil etat:EtatCivil.values()){
							choiceValeur.add(etat.toString());
						}
				    	  
					}else if(choiceCritere.getSelectedItem().equals(Critere.ETAT_HABITAT.toString()))
					{    choiceValeur.removeAll();
					      for(EtatHabitat etat:EtatHabitat.values()){
						choiceValeur.add(etat.toString());
					   }
						
					}else if(choiceCritere.getSelectedItem().equals(Critere.NIVEAU_EDUCATION.toString()))
					{ choiceValeur.removeAll();
				        for(NiveauEducation etat:NiveauEducation.values()){
					    choiceValeur.add(etat.toString());
				       }
						
					}else if(choiceCritere.getSelectedItem().equals(Critere.SEXE.toString()))
					{
						choiceValeur.removeAll();
						choiceValeur.add("M");
						choiceValeur.add("F");
					}
					else if(choiceCritere.getSelectedItem().equals(Critere.ETAT_EMPLOI.toString()))
					{
						choiceValeur.removeAll();
						 for(EtatEmploi etat:EtatEmploi.values()){
							    choiceValeur.add(etat.toString());
						       }

					}
				
				
				}
			}
		  }
		}
		else if (e.getSource()==choiceCondition){
			if(choiceCondition.getSelectedItem().equals("interval")){
				textValeurMax.setVisible(true);
			}
			else {
				textValeurMax.setVisible(false);
			}
		}
		
      }
	 
	public boolean isSelected(){
		return selected;
	}
	
	  public String getQuery(){
		  String s="";
		  String field="";
		  for (Critere c:Critere.values()){
			  if(c.toString().equals(choiceCritere.getSelectedItem())){
				  field=c.getQuery();
				  break;
			  }
		  }
		  if(choiceCondition.getSelectedItem().equals("égale à")){
			 if(textValeur.isVisible()) s=field+"='"+textValeur.getText()+"'";
			 else  s=field+"='"+choiceValeur.getSelectedItem()+"'";
		  }else if(choiceCondition.getSelectedItem().equals("plus grand que")){
			  s=field+">='"+textValeur.getText()+"'";
		  }else if(choiceCondition.getSelectedItem().equals("plus petit que")){
			  s=field+"<='"+textValeur.getText()+"'";
		  }else if(choiceCondition.getSelectedItem().equals("interval")){
			  s=field+">='"+textValeur.getText()+"'"+" and "+field+"<='"+textValeurMax.getText()+"'";
		  }  
		  return s;
		
	  }
}