package controller;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import view.AddPlayerPanel;
import view.MainFrame;
import view.PlaceBetPanel;
import view.RemoveBetPanel;
import view.RemovePlayerPanel;
import view.SpinPanel;
import view.Toolbar;

public class ToolbarListener
{
	private Toolbar toolbar;
	private GameEngine gameEngine;
	private MainFrame mainFrame; 
	
	public ToolbarListener(Toolbar toolbar, GameEngine gameEngine, MainFrame mainFrame)
	{
		this.toolbar = toolbar;
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}
	
	public void toolbarEventOccurred(JButton button)
	{
		AddPlayerPanel addPlayerPanel = mainFrame.getAddPlayerPanel(); 
		RemovePlayerPanel removePlayerPanel = mainFrame.getRemovePlayerPanel(); 
		PlaceBetPanel placeBetPanel = mainFrame.getPlaceBetPanel(); 
		RemoveBetPanel removeBetPanel = mainFrame.getRemoveBetPanel();
		SpinPanel spinPanel = mainFrame.getSpinPanel();
		
		if(button == toolbar.getAddPlayerButton())
		{
			mainFrame.add(addPlayerPanel, BorderLayout.EAST);
			mainFrame.terminatePanels();
			addPlayerPanel.setVisible(true);
		}
		else if (button == toolbar.getRemovePlayerButton())
		{
			mainFrame.add(removePlayerPanel, BorderLayout.EAST);
			mainFrame.terminatePanels();
			removePlayerPanel.setVisible(true);
			removePlayerPanel.showPlayers(gameEngine.getAllPlayers());
		}
		else if (button == toolbar.getPlaceBetButton())
		{
			mainFrame.add(placeBetPanel, BorderLayout.EAST);
			mainFrame.terminatePanels();
			placeBetPanel.setVisible(true);
			placeBetPanel.showPlayers(gameEngine.getAllPlayers());	
		}
		else if (button == toolbar.getRemoveBetButton())
		{
			mainFrame.add(removeBetPanel, BorderLayout.EAST);
			mainFrame.terminatePanels();
			removeBetPanel.setVisible(true);
			removeBetPanel.showPlayers(gameEngine.getAllPlayers());					
		}
		else if (button == toolbar.getSpinButton())
		{
			mainFrame.add(spinPanel, BorderLayout.EAST);
			mainFrame.terminatePanels();
			spinPanel.setVisible(true);
			spinPanel.showPlayers(gameEngine.getAllPlayers());					
		}
		
	}

}
