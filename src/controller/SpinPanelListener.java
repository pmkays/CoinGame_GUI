package controller;

import java.awt.BorderLayout;


import java.util.Collection;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.enumeration.CoinFace;
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
	private boolean readyToSpin = true;
	
	public SpinPanelListener(GameEngine gameEngine, MainFrame mainFrame, SummaryPanel summaryPanel)
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
		this.coinPanel = mainFrame.getCoinPanel(); 
		this.players = gameEngine.getAllPlayers();
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
				CoinPanel newCoinPanel = new CoinPanel();
				newCoinPanel.setCoin(face1, face2);
				mainFrame.setCoinPanel(newCoinPanel);
				coinPanel.setVisible(false);
				coinPanel.setVisible(true);
			}
		}
	}
}
