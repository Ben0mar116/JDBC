import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class keyinp implements KeyListener {
	sqlconnect x;
	
	boolean  exist() {
		return false;
		
		
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(gyint.newel.isVisible() && e.getKeyCode() == 10) {
			try {
				if (gyint.id.getText().isBlank()||gyint.vil.getText().isBlank() ||gyint.tel.getText().isBlank() ||gyint.res.getText().isBlank() ||gyint.adrs.getText().isBlank() ) {
					
				}else {
				
					//verify 
					if (gyint.v.contains(gyint.id.getText())) {
						JOptionPane.showMessageDialog(null, "incorrect ID");
						
					}else {
					// update sql
						x = new sqlconnect();
					Statement y = x.cxn.createStatement();
					y.executeUpdate("insert into client values ( '"+gyint.id.getText()+"','" +gyint.res.getText()+"','"+ gyint.adrs.getText()+"','"+gyint.vil.getText()+"','"+gyint.tel.getText()+"');");
					Object [] row = new Object[6];   
							row[0]= gyint.id.getText();
							row[1]=gyint.res.getText();
							row[2]=gyint.adrs.getText();
							row[3]=gyint.vil.getText();
							row[4]=gyint.tel.getText();
							gyint.v.add(row[0]);
							gyint.mod.addRow(row);
						
							
							
							
							
							
							 
							
							gyint.newel.setVisible(false);;
							
				}}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(gyint.alter.isVisible() && e.getKeyCode() == 10)
		
		{
			//update sql
			
			
			
			
			try {
				x = new sqlconnect();
				Statement y=x.cxn.createStatement();
				y.executeUpdate("Update client set RaisonSociale = '"+gyint.res.getText()+"' , AdresseClient = '"+gyint.adrs.getText()+"',VilleClient ='"+gyint.vil.getText()+"',Téléphone = '"+gyint.tel.getText() +"'where "
						+ "Numclient= '"+gyint.id.getText()+"';");
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
			
		
			gyint.tab.setValueAt(gyint.res.getText(), gyint.tab.getSelectedRow(), 1);
			gyint.tab.setValueAt(gyint.adrs.getText(), gyint.tab.getSelectedRow(), 2);
			gyint.tab.setValueAt(gyint.vil.getText(), gyint.tab.getSelectedRow(), 3);
			gyint.tab.setValueAt(gyint.tel.getText(), gyint.tab.getSelectedRow(), 4);
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
