package view;

import java.awt.BorderLayout;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.GameEngineImpl;
import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private GameEngine gameEngine;
	private CoinPanel coinPanel;
	private MainFrame mainFrame;
	private SummaryPanel summaryPanel;
	
	public GameEngineCallbackGUI(GameEngine gameEngine)
	{
		this.mainFrame = new MainFrame(gameEngine); 
		this.gameEngine = gameEngine;
		this.coinPanel = mainFrame.getCoinPanel();
		this.summaryPanel = mainFrame.getSummaryPanel();
	}
	
	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				summaryPanel.updatePlayerStatus(player.getPlayerId());

			}
		});
		coinFlip(coin);
	}
	
	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				summaryPanel.updatePanel();
				summaryPanel.updatePlayerStatus("");

			}
		});

	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				summaryPanel.updateSpinnerStatus(true);

			}
		});
		coinFlip(coin);
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				summaryPanel.updateSpinnerStatus(false);
			}
		});
	}
	
	private void coinFlip(Coin coin)
	{
		if(coin.getFace() == CoinFace.HEADS && coin.getNumber() == 1)
		{
			coinPanel.setFace1Heads();
		}
		else if (coin.getFace() == CoinFace.TAILS && coin.getNumber() == 1)
		{
			coinPanel.setFace1Tails();
		}			
		else if(coin.getFace() == CoinFace.HEADS && coin.getNumber() == 2)
		{
			coinPanel.setFace2Heads();
		}
		else if (coin.getFace() == CoinFace.TAILS && coin.getNumber() == 2)
		{
			coinPanel.setFace2Tails();
		}
	}
}
