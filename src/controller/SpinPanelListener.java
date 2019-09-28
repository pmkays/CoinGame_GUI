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
	
	public SpinPanelListener(GameEngine gameEngine, MainFrame mainFrame, SummaryPanel summaryPanel)
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
		this.coinPanel = mainFrame.getCoinPanel(); 
		
		//do coinPanel methods here
	}

	public void spinPanelEventOccurred(String id) 
	{
		new Thread()
		{
			@Override
			public void run()
			{
				Collection<Player> players = gameEngine.getAllPlayers();
				Player spinPlayer = null;
				for(Player player : players)
				{
					if(player.getPlayerId().equals(id))
					{
						spinPlayer = player;
					}
				}
				summaryPanel.updatePlayerStatus(spinPlayer.getPlayerId());
				
				if(!(spinPlayer.getBetType() == BetType.NO_BET) && spinPlayer.getBet() > 0)
				{
					gameEngine.spinPlayer(spinPlayer, 100, 1000, 100, 50, 500, 50);
				}
				else
				{
					JOptionPane.showMessageDialog(mainFrame,
					        "Please ensure that a bet has been placed for player: " 
					+ spinPlayer.getPlayerId(), "Unable to spin",
					        JOptionPane.ERROR_MESSAGE);
				}
				summaryPanel.updatePanel();
				summaryPanel.updatePlayerStatus("");
			}
		}.start();
	}
}
