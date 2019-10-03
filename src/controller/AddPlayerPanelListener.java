package controller;

import java.util.Collection;
import java.util.EventListener;



import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.SimplePlayer;
import model.enumeration.BetType;
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
		int playerCount = 0;
		Player player = new SimplePlayer(id, name, points);
		
		gameEngine.addPlayer(player); //same playerID still can get added?
		
		Collection<Player> players = gameEngine.getAllPlayers();
		for(Player aPlayer : players)
		{
			playerCount++; 

		}
		
		System.out.println(gameEngine.getAllPlayers());
		JOptionPane.showMessageDialog(mainFrame,
		        "Player: " + id + " added successfully", "Player Added",
		        JOptionPane.INFORMATION_MESSAGE);
		summaryPanel.updatePanel(); 
		summaryPanel.updatePlayerCount(playerCount);
		toolbar.showPlayers(players);
		
	}

}
