package controller;

import java.awt.BorderLayout;


import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.enumeration.BetType;
import model.enumeration.CoinFace;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerPanel;
import view.CoinPanel;
import view.MainFrame;
import view.PlaceBetPanel;
import view.SummaryPanel;
import view.Toolbar;

public class ToolbarListener
{
	private Toolbar toolbar;
	private GameEngine gameEngine;
	private MainFrame mainFrame; 
	private SummaryPanel summaryPanel;
	private Collection<Player> players;
	private CoinPanel coinPanel;
	private boolean readyToSpin;
	
	public ToolbarListener(Toolbar toolbar, GameEngine gameEngine, SummaryPanel summaryPanel, MainFrame mainFrame)
	{
		this.toolbar = toolbar;
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
		this.players =gameEngine.getAllPlayers();
		this.coinPanel = mainFrame.getCoinPanel();
		this.readyToSpin = true;
	}
	
	public void removePlayerEventOccurred(String id) 
	{
		int playerCount = 0;
		
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
			toolbar.showPlayers(players);
		}
		else
		{
			JOptionPane.showMessageDialog(mainFrame,
			        "No players to remove", "Player Removed",
			        JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void removeBetEventOccurred(String id)
	{
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
		summaryPanel.updatePanel();
		
	}
	
	public void spinPanelEventOccurred(String id) 
	{	
		if(readyToSpin)
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
					if(!(spinPlayer.getBetType() == BetType.NO_BET) && spinPlayer.getBet() > 0)
					{
						summaryPanel.updatePlayerStatus(spinPlayer.getPlayerId());
						readyToSpin = false; 
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
					readyToSpin = true;
				}
			}.start();
		}
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

	public void displayLastCoins(String id) 
	{
		CoinFace face1 = null;
		CoinFace face2 = null;
		
		
		for(Player player : players)
		{
			if(player.getPlayerId().equals(id) && player.getResult() != null)
			{
				face1 = player.getResult().getCoin1().getFace();
				face2 = player.getResult().getCoin2().getFace();
				CoinPanel newCoinPanel = new CoinPanel(toolbar);
				newCoinPanel.setCoin(face1, face2);
				mainFrame.setCoinPanel(newCoinPanel);
				coinPanel.setVisible(false);
				coinPanel.setVisible(true);
			}
		}
	}

}
