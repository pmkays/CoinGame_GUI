package controller;

import java.awt.BorderLayout;


import java.util.Collection;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinPanel;
import view.MainFrame;
import view.SummaryPanel;

public class SpinPanelListener 
{
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private CoinPanel coinPanel;
	private SummaryPanel summaryPanel;
	Collection<Player> players;
	
	public SpinPanelListener(GameEngine gameEngine, MainFrame mainFrame, SummaryPanel summaryPanel)
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
		this.coinPanel = mainFrame.getCoinPanel(); 
		this.players = gameEngine.getAllPlayers();
		
		//do coinPanel methods here
	}

	public void spinPanelEventOccurred(String id) 
	{
		new Thread()
		{
			@Override
			public void run()
			{
				
				Player spinPlayer = null;
				for(Player player : players)
				{
					if(player.getPlayerId().equals(id))
					{
						spinPlayer = player;
					}
				}
//				summaryPanel.updatePlayerStatus(spinPlayer.getPlayerId());
				
				if(!(spinPlayer.getBetType() == BetType.NO_BET) && spinPlayer.getBet() > 0)
				{
					summaryPanel.updatePlayerStatus(spinPlayer.getPlayerId());
					gameEngine.spinPlayer(spinPlayer, 100, 1000, 100, 50, 500, 50);
					summaryPanel.updatePanel();
					summaryPanel.updatePlayerStatus("");
					autoSpin();
				}
				else
				{
					JOptionPane.showMessageDialog(mainFrame,
					        "Please ensure that a bet has been placed for player: " 
					+ spinPlayer.getPlayerId(), "Unable to spin",
					        JOptionPane.ERROR_MESSAGE);
				}
//				summaryPanel.updatePanel();
//				summaryPanel.updatePlayerStatus("");
			}
		}.start();
	}

	public void spinSpinnerEventOccurred() 
	{
		new Thread()
		{
			@Override
			public void run()
			{
				int count = 0; 
				Player toDelete = null;
				summaryPanel.updateSpinnerStatus(true);
				gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
				summaryPanel.updateSpinnerStatus(false);
				
				for(Player player : players)
				{
					count++;
					player.resetBet();
					if(player.getPoints() <=  0)
					{
						toDelete = player;
						count--;
					}
				}
				
				if(toDelete != null)
				{
					gameEngine.removePlayer(toDelete);
					JOptionPane.showMessageDialog(mainFrame,
					        "Player: " + toDelete.getPlayerId() + " has been eliminated", "Player removed",
					        JOptionPane.ERROR_MESSAGE);
					
					//refreshes the players combobox
					mainFrame.getSpinPanel().showPlayers(players);
					
					//refreshes the toolbar
					mainFrame.getSummaryPanel().updatePlayerCount(count);
				}
				summaryPanel.updatePanel();
			}
		}.start();
		
	}
	
	public void autoSpin()
	{
		int count = 0; 
		int amountOfPlayers = players.size();
		for(Player player : players)
		{
			if(player.getBet() > 0 && player.getBetType() != BetType.NO_BET && player.getResult() != null)
			{
				count++;
			}
		}
		
		if(count == amountOfPlayers)
		{
			spinSpinnerEventOccurred();
			
		}
	}
}
