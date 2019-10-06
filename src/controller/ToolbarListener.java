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
		
//		autoSpin();
		
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
						toolbar.getSpinButton().setEnabled(false);
						gameEngine.spinPlayer(spinPlayer, 100, 1000, 100, 50, 500, 50);
						toolbar.getSpinButton().setEnabled(true);
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
				//sets the spinner coin panel for spinning in the mainframe
				CoinPanel coinPanel = mainFrame.getCoinPanel();
				mainFrame.getLastCoinsPanel().setVisible(false);
				mainFrame.add(coinPanel, BorderLayout.CENTER);
				coinPanel.setVisible(true);
				
				int playerCount = 0; 
				Player toDelete = null;
				
				//handles the spinner status and the actual spin logic
				summaryPanel.updateSpinnerStatus(true);
				toolbar.getSpinSpinnerButton().setEnabled(true);
				gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
				summaryPanel.updateSpinnerStatus(false);
				
				toolbar.getSpinSpinnerButton().setEnabled(false);
				
				//keeps count of players and finds player to delete
				for(Player player : players)
				{
					playerCount++;
					player.resetBet();
					if(player.getPoints() <=  0)
					{
						toDelete = player;
						playerCount--;
					}
				}
				
				if(toDelete != null)
				{
					gameEngine.removePlayer(toDelete);
					JOptionPane.showMessageDialog(mainFrame,
					        "Player: " + toDelete.getPlayerId() + " has been eliminated", "Player removed",
					        JOptionPane.ERROR_MESSAGE);
					
					//refreshes the toolbar with the player count after removal
					mainFrame.getSummaryPanel().updatePlayerCount(playerCount);
				}
//				summaryPanel.updatePanel();
				summaryPanel.updateWinner();
			}
		}.start();
		
	}
	
	public void autoSpin()
	{
		int count = 0; 
		int amountOfPlayers = players.size();
		for(Player player : players)
		{
			//ensures all players have bet and spun
			if(player.getResult() != null)
			{
				count++;
			}
		}
		
		//i.e. if all the players have spun
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
				//finds the face from the results of each coin flip
				face1 = player.getResult().getCoin1().getFace();
				face2 = player.getResult().getCoin2().getFace();
				
				//displays the last-spun coins by making a new coinPanel and setting its faces
				CoinPanel newCoinPanel = mainFrame.getLastCoinsPanel();
				mainFrame.getCoinPanel().setVisible(false);
				newCoinPanel.setCoin(face1, face2);
				mainFrame.add(newCoinPanel, BorderLayout.CENTER);

				//refreshes the main panel so newCoinPanel can be seen
				newCoinPanel.setVisible(true);
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		}
	}

}
