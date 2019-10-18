package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private CoinPanel coinPanel;
	private MainFrame mainFrame;
	private SummaryPanel summaryPanel;
	
	public GameEngineCallbackGUI(GameEngine gameEngine)
	{
		this.mainFrame = new MainFrame(gameEngine); 
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
				//displays which player is spinning
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
				//displays player results and no players spinning
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
				//displays spinner spinning
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
				//display spinning idle
				summaryPanel.updateSpinnerStatus(false);
			}
		});
	}
	
	private void coinFlip(Coin coin)
	{
		int coin1Number = 1;
		int coin2Number = 2;
		//decides what face to set for each coin
		if(coin.getFace() == CoinFace.HEADS && coin.getNumber() == coin1Number)
		{
			coinPanel.setFace1Heads();
		}
		else if (coin.getFace() == CoinFace.TAILS && coin.getNumber() == coin1Number)
		{
			coinPanel.setFace1Tails();
		}			
		else if(coin.getFace() == CoinFace.HEADS && coin.getNumber() == coin2Number)
		{
			coinPanel.setFace2Heads();
		}
		else if (coin.getFace() == CoinFace.TAILS && coin.getNumber() == coin2Number)
		{
			coinPanel.setFace2Tails();
		}
	}
}
