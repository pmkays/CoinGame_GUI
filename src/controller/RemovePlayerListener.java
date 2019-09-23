package controller;

import java.util.Collection;

import javax.swing.JOptionPane;

import model.GameEngineImpl;
import model.interfaces.Player;
import view.MainFrame;

public class RemovePlayerListener
{
	private GameEngineImpl gameEngine;
	private MainFrame mainFrame;
	
	public RemovePlayerListener(GameEngineImpl gameEngine, MainFrame frame)
	{
		this.gameEngine = gameEngine;
		this.mainFrame = frame;
	}
	
	public void removePlayerEventOccurred(String id) 
	{
		Collection<Player> players = gameEngine.getAllPlayers();
		Player toDelete = null;
		for(Player player : players)
		{
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
		}
		else
		{
			JOptionPane.showMessageDialog(mainFrame,
			        "No players to remove", "Player Removed",
			        JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
