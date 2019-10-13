package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.SummaryPanel;
import view.Toolbar;

public class RemovePlayerActionListener implements ActionListener
{
	private Toolbar toolbar;

	
	public RemovePlayerActionListener(Toolbar toolbar)
	{
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String id = (String) toolbar.getPlayersCombo().getSelectedItem();
		if(toolbar.getToolbarViewModel() != null)
		{
			toolbar.getToolbarViewModel().removePlayerEventOccurred(id);
		}
	}
	

}
