package view;

import java.awt.BorderLayout;
import java.util.Collection;

import model.GameEngineImpl;
import model.interfaces.Player;

public class SpinPanelListener 
{
	private GameEngineImpl gameEngine;
	private MainFrame mainFrame;
	private CoinPanel coinPanel;
	
	public SpinPanelListener(GameEngineImpl gameEngine, MainFrame mainFrame)
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		
		//do coinPanel methods here
	}

	public void spinPanelEventOccurred(String id) 
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
		
		coinPanel = new CoinPanel(spinPlayer, gameEngine);
		mainFrame.getCoinPanel().setVisible(false);
		mainFrame.add(coinPanel,BorderLayout.CENTER);
		gameEngine.spinPlayer(spinPlayer, 100, 1000, 100, 50, 500, 50);
		
	}

}
