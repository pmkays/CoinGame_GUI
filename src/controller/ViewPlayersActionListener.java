package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Toolbar;

public class ViewPlayersActionListener implements ActionListener
{
	private Toolbar toolbar;

	public ViewPlayersActionListener(Toolbar toolbar)
	{
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String id = (String) toolbar.getPlayersCombo().getSelectedItem();
		if(toolbar.getToolbarViewModel() != null)
		{
			toolbar.getToolbarViewModel().displayLastCoins(id);
		}
	}

}