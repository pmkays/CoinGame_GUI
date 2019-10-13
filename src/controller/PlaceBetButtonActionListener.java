package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddPlayerPanel;
import view.PlaceBetPanel;

public class PlaceBetButtonActionListener implements ActionListener
{
	private PlaceBetPanel placeBetPanel;
	
	public PlaceBetButtonActionListener(PlaceBetPanel placeBetPanel)
	{
		this.placeBetPanel = placeBetPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		PlaceBetPanelListener placeBetPanelListener = placeBetPanel.getPlaceBetListener(); 
		
		//retrieve the values from Jtextfield and combobox
		String id = (String) placeBetPanel.getToolbar().getPlayersCombo().getSelectedItem();
		int bet = Integer.parseInt(placeBetPanel.getBetField().getText());
		String betType = (String) placeBetPanel.getBetTypeCombo().getSelectedItem();
		if (placeBetPanelListener != null)
		{
			placeBetPanelListener.placeBetPanelEventOccurred(id, bet, betType);
			
		}
		
	}

}
