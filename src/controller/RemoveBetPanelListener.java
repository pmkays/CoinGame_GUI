package controller;

import java.util.Collection;
import java.util.EventListener;

import javax.swing.JOptionPane;

import model.GameEngineImpl;
import model.interfaces.Player;
import view.MainFrame;

public class RemoveBetPanelListener
{
	GameEngineImpl gameEngine;
	MainFrame mainFrame;
	public RemoveBetPanelListener(GameEngineImpl gameEngine, MainFrame mainFrame) 
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}

	public void removeBetPanelEventOccurred(String id)
	{
		Collection<Player> players = gameEngine.getAllPlayers();
		for(Player player : players)
		{
			if(player.getPlayerId().equals(id))
			{
				player.resetBet();
			}
		}
		System.out.println(gameEngine.getAllPlayers());
		JOptionPane.showMessageDialog(mainFrame,
		        "Bet successfully removed for Player:" + id, "Bet Placed",
		        JOptionPane.INFORMATION_MESSAGE);	
	}

}
