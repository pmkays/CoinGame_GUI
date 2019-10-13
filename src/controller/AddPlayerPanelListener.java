package controller;

import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.SimplePlayer;
import model.interfaces.Player;
import view.MainFrame;
import view.SummaryPanel;
import view.Toolbar;

public class AddPlayerPanelListener
{
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private SummaryPanel summaryPanel;
	private Toolbar toolbar;
	
	public AddPlayerPanelListener(GameEngine gameEngine, MainFrame mainFrame, SummaryPanel summaryPanel, Toolbar toolbar) 
	{
		this.gameEngine = gameEngine; 
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
		this.toolbar = toolbar; 
	} 
 
	public void addPlayerEventOccurred(String id, String name, int points)
	{
		Player player = new SimplePlayer(id, name, points);
		
		gameEngine.addPlayer(player); 
		
		Collection<Player> players = gameEngine.getAllPlayers();

		JOptionPane.showMessageDialog(mainFrame,
		        "Player: " + id + " added successfully", "Player Added",
		        JOptionPane.INFORMATION_MESSAGE);
		
		//update summary panel and jcombobox
		summaryPanel.updatePanel(); 
		summaryPanel.updatePlayerCount(players.size());
		toolbar.showPlayers(players);
		
	}

}
