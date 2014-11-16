import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.Label;


public class Recherche extends JPanel implements ItemListener{
	 public Choice  choicesearch;
	 private JTextField textVal;
	 private JTextField textValmin;
	 private Choice choiceAnnemin;
	 private Choice choiceMoismin;
	 private Choice choiceJourmin;
	 private Choice choiceJourmax;
	 private Choice choiceMoismax;
	 private Choice choiceAnnemax;
	 private JTextField textValmax;
	 private Label label;

	public Recherche() {
		Calendar calendar = Calendar.getInstance();
	       choicesearch = new Choice();
	       choicesearch.setBounds(0, 0, 172, 20);
	       
	       for(AppSearch app:AppSearch.values()){
	       choicesearch.add(app.toString());
	       }
	       choicesearch.addItemListener(this);
	       setLayout(null);
	       add(choicesearch);
	       
	       choiceJourmin = new Choice();
	       for(int i=1;i<32;i++){
	    	   choiceJourmin.add(""+i);
	    	  
	       }
	       choiceJourmin.setBounds(178, 0, 51, 20);
	       add(choiceJourmin);
	       
	       String[] months = new java.text.DateFormatSymbols().getShortMonths();
	       choiceMoismin = new Choice();
	       for(int i=0;i<months.length;i++){
	    	   choiceMoismin.add(months[i]);
	       }
	       choiceMoismin.setBounds(235, 0, 79, 20);
	       add(choiceMoismin);
	       
	       int currentYear = calendar.get(Calendar.YEAR);
	       choiceAnnemin = new Choice();
	       for(int i=currentYear;i>1900;i--){
	    	   choiceAnnemin.add(""+i);
	       }
	       choiceAnnemin.setBounds(320, 0, 55, 20);
	       add(choiceAnnemin);
	       
	       textVal = new JTextField();
	       textVal.setBounds(178, 0, 197, 20);
	       add(textVal);
	       textVal.setColumns(10);
	       
	       textValmin = new JTextField();
	       textValmin.setBounds(178, 0, 197, 20);
	       add(textValmin);
	       
	       choiceJourmax = new Choice();
	       for(int i=1;i<32;i++){
	    	   choiceJourmax.add(""+i);
	       }
	       choiceJourmax.setBounds(402, 0, 49, 20);
	       
	       add(choiceJourmax);
	       
	       choiceMoismax = new Choice();
	       for(int i=0;i<months.length;i++){
	    	   choiceMoismax.add(months[i]);
	       }
	       choiceMoismax.setBounds(457, 0, 76, 20);
	       add(choiceMoismax);
	       
	       choiceAnnemax = new Choice();
	       for(int i=currentYear;i>1900;i--){
	    	   choiceAnnemax.add(""+i);
	       }
	       choiceAnnemax.setBounds(539, 0, 60, 20);
	       add(choiceAnnemax);
	       
	       textValmax = new JTextField();
	       textValmax.setBounds(402, 0, 197, 20);
	       add(textValmax);
	       
	      label = new Label("-");
	       label.setBounds(383, 0, 15, 22);
	       add(label);
	       textVal.setVisible(true);
			 textValmin.setVisible(false);
			 choiceAnnemin.setVisible(false);
			 choiceMoismin.setVisible(false);
			 choiceJourmin.setVisible(false);
			 choiceJourmax.setVisible(false);
			choiceMoismax.setVisible(false);
			choiceAnnemax.setVisible(false);
			textValmax.setVisible(false);
			label.setVisible(false);
	}
     
	public String getVal(){
		String s="";
		for(AppSearch app:AppSearch.values()){
			  if(choicesearch.getSelectedItem().equals(app.toString()))
				if(app==AppSearch.DATE){
					s=choiceJourmin.getSelectedItem()+"-"+choiceMoismin.getSelectedItem()+"-"+choiceAnnemin.getSelectedItem();
				}
			    else if(app.getType().equals("string")){
			       s=textVal.getText();
				}
				else{
					s=textValmin.getText();
				}
			}
		return s;
	}
	public String getValmax(){
		String s="";
		for(AppSearch app:AppSearch.values()){
			  if(choicesearch.getSelectedItem().equals(app.toString()))
				if(app==AppSearch.DATE){
					s=choiceJourmax.getSelectedItem()+"-"+choiceMoismax.getSelectedItem()+"-"+choiceAnnemax.getSelectedItem();
				}
			    else if(app.getType().equals("string")){
			       
				}
				else{
		           s=textValmax.getText();
				}
			}
		return s;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==choicesearch)
		{
			for(AppSearch app:AppSearch.values()){
			  if(choicesearch.getSelectedItem().equals(app.toString()))
				if(app==AppSearch.DATE){
					
					 textVal.setVisible(false);
					 textValmin.setVisible(false);
					 choiceAnnemin.setVisible(true);
					 choiceMoismin.setVisible(true);
					 choiceJourmin.setVisible(true);
					 choiceJourmax.setVisible(true);
					choiceMoismax.setVisible(true);
					choiceAnnemax.setVisible(true);
					textValmax.setVisible(false);
					label.setVisible(true);
				}
			    else if(app.getType().equals("string")){
			    	 textVal.setVisible(true);
					 textValmin.setVisible(false);
					 choiceAnnemin.setVisible(false);
					 choiceMoismin.setVisible(false);
					 choiceJourmin.setVisible(false);
					 choiceJourmax.setVisible(false);
					choiceMoismax.setVisible(false);
					choiceAnnemax.setVisible(false);
					textValmax.setVisible(false);
					label.setVisible(false);
				}
				else{
					
					 textVal.setVisible(false);
					 textValmin.setVisible(true);
					 choiceAnnemin.setVisible(false);
					 choiceMoismin.setVisible(false);
					 choiceJourmin.setVisible(false);
					 choiceJourmax.setVisible(false);
					choiceMoismax.setVisible(false);
					choiceAnnemax.setVisible(false);
					textValmax.setVisible(true);
					label.setVisible(true);
				}
			}
		}
	}
}
