package view;

import java.awt.BorderLayout;


import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;

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
				//make sure player's bet isnt 0 bettype != no bet
				gameEngine.spinPlayer(spinPlayer, 100, 1000, 100, 50, 500, 50);
				summaryPanel.updatePanel();
			}
		}.start();
	}
}
