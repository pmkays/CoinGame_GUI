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
			addPlayerPanel.setVisible(true);
			removePlayerPanel.setVisible(false);
			placeBetPanel.setVisible(false);
			removeBetPanel.setVisible(false);
			spinPanel.setVisible(false);
		}
		else if (button == toolbar.getRemovePlayerButton())
		{
			mainFrame.add(removePlayerPanel, BorderLayout.EAST);
			removePlayerPanel.setVisible(true);
			addPlayerPanel.setVisible(false);
			placeBetPanel.setVisible(false);
			removeBetPanel.setVisible(false);
			spinPanel.setVisible(false);
			removePlayerPanel.showPlayers(gameEngine.getAllPlayers());
		}
		else if (button == toolbar.getPlaceBetButton())
		{
			mainFrame.add(placeBetPanel, BorderLayout.EAST);
			placeBetPanel.setVisible(true);
			removePlayerPanel.setVisible(false);
			addPlayerPanel.setVisible(false);
			removeBetPanel.setVisible(false);
			spinPanel.setVisible(false);
			placeBetPanel.showPlayers(gameEngine.getAllPlayers());	
		}
		else if (button == toolbar.getRemoveBetButton())
		{
			mainFrame.add(removeBetPanel, BorderLayout.EAST);
			removeBetPanel.setVisible(true);
			placeBetPanel.setVisible(false);
			removePlayerPanel.setVisible(false);
			addPlayerPanel.setVisible(false);
			spinPanel.setVisible(false);
			removeBetPanel.showPlayers(gameEngine.getAllPlayers());					
		}
		else if (button == toolbar.getSpinButton())
		{
			mainFrame.add(spinPanel, BorderLayout.EAST);
			spinPanel.setVisible(true);
			removeBetPanel.setVisible(false);
			placeBetPanel.setVisible(false);
			removePlayerPanel.setVisible(false);
			addPlayerPanel.setVisible(false);
			spinPanel.showPlayers(gameEngine.getAllPlayers());					
		}
		
	}

}
