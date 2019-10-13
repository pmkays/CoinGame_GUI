package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddPlayerPanel;

public class AddPlayerButtonActionListener implements ActionListener
{
	private AddPlayerPanel addPlayerPanel;
	
	public AddPlayerButtonActionListener(AddPlayerPanel playerPanel)
	{
		this.addPlayerPanel = playerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		AddPlayerPanelListener addPlayerListener = addPlayerPanel.getAddPlayerListener(); 
		
		//retrieve the values from the JTextFields
		String id = addPlayerPanel.getIdField().getText();
		String name = addPlayerPanel.getNameField().getText();
		int points = Integer.parseInt(addPlayerPanel.getPointsField().getText());
		
		if (addPlayerListener != null)
		{
			addPlayerListener.addPlayerEventOccurred(id, name, points);
		}
		
	}

}
