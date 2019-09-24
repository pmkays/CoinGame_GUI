package view;

import java.awt.BorderLayout;
import java.util.Collection;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SpinPanelListener 
{
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private CoinPanel coinPanel;
	private GameEngineCallbackGUI callbackGUI;
	
	public SpinPanelListener(GameEngine gameEngine, MainFrame mainFrame)
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
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
				gameEngine.spinPlayer(spinPlayer, 100, 1000, 100, 50, 500, 50);
			}
		}.start();

		
//		coinPanel = new CoinPanel(); //set a new coinPanel each time a new player is chosen
//		mainFrame.getCoinPanel().setVisible(false); //whatever coinpanel is in the main frame is set to be invisible
//		mainFrame.add(coinPanel,BorderLayout.CENTER); //add this new coinPanel in the mainFrame
//		coinPanel.setVisible(true); //makes this coinPanel visible now
//		
//		callbackGUI = new GameEngineCallbackGUI(gameEngine, coinPanel);


}}
