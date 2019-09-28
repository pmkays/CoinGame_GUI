package controller;

import java.util.Collection;


import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.SummaryPanel;

public class RemovePlayerListener
{
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private SummaryPanel summaryPanel;

	
	public RemovePlayerListener(GameEngine gameEngine, MainFrame frame, SummaryPanel summaryPanel)
	{
		this.gameEngine = gameEngine;
		this.mainFrame = frame;
		this.summaryPanel = summaryPanel; 
	}
	
	public void removePlayerEventOccurred(String id) 
	{
		int playerCount = 0;
		Collection<Player> players = gameEngine.getAllPlayers();
		Player toDelete = null;
		for(Player player : players)
		{
			playerCount++; 
			if(player.getPlayerId().equals(id))
			{
				toDelete = player;
			}
		}
		gameEngine.removePlayer(toDelete);
		
		System.out.println(gameEngine.getAllPlayers());
		
		if(!id.equals("No players added") || !id.isEmpty()) //slight bug if not refreshed
		{
			JOptionPane.showMessageDialog(mainFrame,
			        "Player: " + id + " removed successfully", "Player Removed",
			        JOptionPane.INFORMATION_MESSAGE);	
			summaryPanel.updatePanel();
			playerCount--;
			summaryPanel.updatePlayerCount(playerCount); 
		}
		else
		{
			JOptionPane.showMessageDialog(mainFrame,
			        "No players to remove", "Player Removed",
			        JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
