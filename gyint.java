
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class gyint {
protected static JTable tab = new JTable ();
protected static Vector<Object> v = new Vector<Object> ();
protected static JTextField id = new JTextField ();
protected static JTextField res = new JTextField ();                 
protected static JTextField adrs = new JTextField ();                                     
protected static JTextField vil = new JTextField ();  	
protected static JTextField tel = new JTextField (); 
protected static JFrame newel = new JFrame("Ajouter");
protected static JFrame alter = new JFrame("Modifier");
@SuppressWarnings("serial")
static DefaultTableModel mod = new DefaultTableModel() {@Override 
	public boolean isCellEditable(int row, int column) {      
	       //all cells false                                  
	       return false;                                      
	    }                                                     
};                                                         
private TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(mod);	 
JFrame ffr = new JFrame();
		gyint() throws SQLException{
	   ffr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ffr.setLayout(null);
		ffr.setBounds(10, 10, 600, 850);
		ffr.add(adminmanagment());
		ffr.setVisible(true);
		ffr.setResizable(false);		
	}

	private JPanel adminmanagment() throws SQLException {
		sqlconnect sql;
		Statement aff;
			sql = new sqlconnect();
		JPanel adminpage = new JPanel();
		adminpage.setLayout(null);
		adminpage.setBounds(0, 0, 600, 850);
		
		
		JButton add =  new JButton("Ajouter");
			add.setBounds(80,550,110,40);
		JButton del =  new JButton("Supprimer");
			del.setBounds(380,550,110,40);
		JButton updt =  new JButton("Mise à jour");
			updt.setBounds(220,550,110,40);
		JButton srch =  new JButton("Chercher");
			srch.setBounds(400,690,110,40);
		JTextField tx = new JTextField();	
			tx.setBounds(80, 690, 300, 40);
			
		Object [] col = {"ID","Raison Social","Adress Client","Ville","Telephone"};
		
		tab.setBounds(12, 12 ,560,500);
		tab.setRowHeight(30);
		mod.setColumnIdentifiers(col);
		tab.setModel(mod);
		tab.setAutoCreateRowSorter(true);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		

		JScrollPane pe= new JScrollPane(tab);
		
		tab.setRowSorter(sort);
		
		
		
		pe.setBounds(10, 10, 560, 500);
		
		Object [] row = new Object[6]; 
		// show table by defeault
	
		
			aff = sql.cxn.createStatement();
			sql.rst = aff.executeQuery("select * from client");
			while (sql.rst.next()) {
				v.add(sql.rst.getObject(1));
				row[0]= sql.rst.getObject(1);
				row[1]= sql.rst.getObject(2);
				row[2]= sql.rst.getObject(3);
				row[3]= sql.rst.getObject(4);
				row[4]= sql.rst.getObject(5);

				mod.addRow(row);
		
			}
			
			///// input frame 
		    newel.setLayout(null);                                  
		    newel.setBounds(600, 50, 380, 400);                                                    
		    newel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    newel.setAlwaysOnTop(true);                             
		    alter.setLayout(null);                                  
		    alter.setBounds(600, 50, 380, 400);                                                    
		    alter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    alter.setAlwaysOnTop(true);                             
			JLabel idlab = new JLabel ("ID                            :");
			idlab.setBounds(30, 20, 100, 30);                             
			JLabel reslab = new JLabel ("Nom et Prenom  :");              	
			reslab.setBounds(30, 50, 100, 30);                            	
			JLabel adrslab = new JLabel ("Adress                  :");    	
			adrslab.setBounds(30, 80, 100, 30);                           	
			JLabel villb = new JLabel ("Ville                        :"); 
			villb.setBounds(30, 110, 100, 30);                            
			JLabel tellb = new JLabel ("Telephone            :");         
			tellb.setBounds(30, 140, 100, 30);                
			JLabel  entr = new JLabel("press <Enter>"); 
			entr.setBounds(240, 170, 80,20);            
		     id.setBounds(160, 20, 180, 24);  
		     res.setBounds(160, 50, 180, 24); 
		     adrs.setBounds(160, 80, 180, 24);
		     vil.setBounds(160, 110, 180, 24);
		     tel.setBounds(160, 140, 180, 24);
		     id.addKeyListener(new keyinp());    
		     res.addKeyListener(new keyinp());   
		     adrs.addKeyListener(new keyinp());  
		     vil.addKeyListener(new keyinp());   
		     tel.addKeyListener(new keyinp());   
			////// end of input frame 
			
			
		//// delete
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				if (tab.getSelectedRowCount() >= 1 ) {
					// UPDATE SQL
					try {
						Statement stt =sql.cxn.createStatement();
						stt.executeUpdate("delete from client where Numclient = '"+tab.getValueAt(tab.getSelectedRow(), 0)+"'");
	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					v.remove(tab.getValueAt(tab.getSelectedRow(), 0));
			mod.removeRow(tab.getSelectedRow());
			
		
		
				}
				if (tab.getRowCount() ==0 ) {
					JOptionPane.showMessageDialog(null, "table vide");
				}
		
			
			
			}

			
			
		});
		//// add
		add.addActionListener(new ActionListener() {			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (newel.isVisible() == false) {
		
		id.setText(null);          	
		res.setText(null); 	
		adrs.setText(null);	
		tel.setText(null); 
		vil.setText(null); 	
		id.setFocusable(true);		              
		newel.add(entr);               
	    newel.add(idlab);  	        
	    newel.add(reslab); 	        
	    newel.add(adrslab);	        
	    newel.add(villb);  	        
	    newel.add(tellb);           
	    newel.add(id);              
	    newel.add(res);             
	    newel.add(adrs);            
	    newel.add(vil);             
	    newel.add(tel);             
					
					newel.setVisible(true);  
				}	
			}
		});
		//// update 
		updt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			if (tab.getSelectedRowCount()  == 0 || tab.getSelectedRowCount() > 1) {
				
			}else {	
		      id.setText((String) tab.getValueAt(tab.getSelectedRow(), 0));  
		      id.setFocusable(false);
		      res.setText((String) tab.getValueAt(tab.getSelectedRow(), 1));    
		      adrs.setText((String) tab.getValueAt(tab.getSelectedRow(), 2));    
		      vil.setText((String) tab.getValueAt(tab.getSelectedRow(), 3));    
		      tel.setText((String) tab.getValueAt(tab.getSelectedRow(), 4));    
				    alter.add(entr);          
				    alter.add(idlab);  	      
				    alter.add(reslab); 	      
				    alter.add(adrslab);	      
				    alter.add(villb);  	      
				    alter.add(tellb);         
				    alter.add(id);            
				    alter.add(res);           
				    alter.add(adrs);          
				    alter.add(vil);           
				    alter.add(tel);           
			alter.setVisible(true);
		
			}
				
			}		
		});
		
		srch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			sort.setRowFilter(RowFilter.regexFilter(tx.getText()));

				
			}
			
		});	
		adminpage.add(pe);
		adminpage.add(add);
		adminpage.add(del);
		adminpage.add(updt);
		adminpage.add(srch);
		adminpage.add(tx);
		
		// TODO Auto-generated method stub
		return adminpage;
	}
	
	
	

}
