package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Toolbar;

public class RemoveBetActionListener implements ActionListener
{
	private Toolbar toolbar;

	public RemoveBetActionListener(Toolbar toolbar)
	{
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String id = (String) toolbar.getPlayersCombo().getSelectedItem();
		if(toolbar.getToolbarListener() != null)
		{
			toolbar.getToolbarListener().removeBetEventOccurred(id);;
		}
	}

}
