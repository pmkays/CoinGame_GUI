package controller;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import model.enumeration.BetType;
import model.enumeration.CoinFace;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinPanel;
import view.MainFrame;
import view.SummaryPanel;
import view.Toolbar;

public class ToolbarViewModel
{
	private Toolbar toolbar;
	private GameEngine gameEngine;
	private MainFrame mainFrame; 
	private SummaryPanel summaryPanel;
	private Collection<Player> players;
	private Collection<Player> spunPlayers; 
	Player spinPlayer = null;
	
	public ToolbarViewModel(Toolbar toolbar, GameEngine gameEngine, SummaryPanel summaryPanel, MainFrame mainFrame)
	{
		this.toolbar = toolbar;
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
		this.players = gameEngine.getAllPlayers();
		this.spunPlayers = new ArrayList<Player>();
	}
	
	public void removePlayerEventOccurred(String id) 
	{
		gameEngine.removePlayer(gameEngine.getPlayer(id));
		if(!id.equals("No players added") || !id.isEmpty())
		{
			JOptionPane.showMessageDialog(mainFrame,
			        "Player: " + id + " removed successfully", "Player Removed",
			        JOptionPane.INFORMATION_MESSAGE);	
			
			//summary panel/toolbar updates
			summaryPanel.updatePanel();
			summaryPanel.updatePlayerCount(this.players.size()); 
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
		Player player = gameEngine.getPlayer(id);
		player.resetBet();
		
		checkSpinSpinnerButton();
		//each bet cancellation counts as a spin:
		spunPlayers.add(player);
				
		JOptionPane.showMessageDialog(mainFrame,
		        "Bet successfully removed for Player:" + id, "Bet Removed",
		        JOptionPane.INFORMATION_MESSAGE);	
		
		summaryPanel.updatePanel();
		autoSpin();
	}
	
	public void spinPlayerEventOccurred(String id) 
{	
		new Thread()
		{
			@Override
			public void run()
			{
				spinPlayer = gameEngine.getPlayer(id);
				
				if(!(spinPlayer.getBetType() == BetType.NO_BET) 
						&& spinPlayer.getBet() > 0 && !(hasSpunBefore(spinPlayer)))
				{
					//initial coinPanel setup for spinning
					setUpCoinPanel();
					
					//updates spinning state and spin button so they can't spin when a player is already spinning
					summaryPanel.updatePlayerStatus(spinPlayer.getPlayerId());
					toolbar.getSpinButton().setEnabled(false);
					gameEngine.spinPlayer(spinPlayer, 100, 1000, 100, 50, 500, 50);
					toolbar.getSpinButton().setEnabled(true);
					
					spunPlayers.add(spinPlayer);
					checkSpinSpinnerButton();
					
					//update summary panel after each spin
					summaryPanel.updatePanel();
					summaryPanel.updatePlayerStatus("");
					autoSpin();
				}
				else if (hasSpunBefore(spinPlayer))
				{
					JOptionPane.showMessageDialog(mainFrame,
					        "Player: " 
					+ spinPlayer.getPlayerId() + "has already spun!", "Unable to spin",
					        JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(mainFrame,
					        "Please ensure that a bet has been placed for player: " 
					+ spinPlayer.getPlayerId(), "Unable to spin",
					        JOptionPane.ERROR_MESSAGE);
				}
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
				//sets the spinner coin panel for spinning in the mainframe
				setUpCoinPanel();
				
				//handles the spinner status and the actual spin logic
				summaryPanel.updateSpinnerStatus(true);
				toolbar.getSpinSpinnerButton().setEnabled(false);
				gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
				toolbar.getSpinSpinnerButton().setEnabled(true);
				
				//checks if any players need to get deleted
				zeroPoints();
				
				summaryPanel.updateWinner();
				for(Player player: players)
				{
					player.resetBet();
				}
				summaryPanel.updatePanel();
				

				//refreshes the spun players for next round
				spunPlayers.clear();
			}
		}.start();
	
	}
	
	private void setUpCoinPanel()
	{
		CoinPanel coinPanel = mainFrame.getCoinPanel();
		mainFrame.getLastCoinsPanel().setVisible(false);
		mainFrame.add(coinPanel, BorderLayout.CENTER);
		coinPanel.setVisible(true);
	}
	
	
	private void zeroPoints()
	{
		Player toDelete = null;
		
		//keeps count of players and finds player to delete
		for(Player player : players)
		{
			if(player.getPoints() <=  0)
			{
				toDelete = player;
			}
		}
		
		if(toDelete != null)
		{
			gameEngine.removePlayer(toDelete);
			JOptionPane.showMessageDialog(mainFrame,
			        "Player: " + toDelete.getPlayerId() + " has been eliminated", 
			        "Player removed",JOptionPane.ERROR_MESSAGE);
			
			//refreshes the toolbar with the player count after removal
			mainFrame.getSummaryPanel().updatePlayerCount(players.size());
		}
	}
	
	private void checkSpinSpinnerButton()
	{
		boolean spinnerCanSpin = true;
		for(Player playerToCheck: players)
		{
			//if everyone has either made a bet BUT they have no results (haven't spun) && they placed some sort of betType
			if(playerToCheck.getBet() > 0 && playerToCheck.getResult()== null && playerToCheck.getBetType() != BetType.NO_BET)
			{
				spinnerCanSpin = false;
			}
		}
		
		if(spinnerCanSpin)
		{
			toolbar.getSpinSpinnerButton().setEnabled(true);
		}
	}
	
	private void autoSpin()
	{
		int amountOfPlayers = players.size();
		int spunPlayersAmount = spunPlayers.size();
		
		//i.e. if ALL the players have spun/cancelled a bet
		if(spunPlayersAmount == amountOfPlayers)
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

	private boolean hasSpunBefore(Player player)
	{
		return spunPlayers.contains(player);
	}
}
