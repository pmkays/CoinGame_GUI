package view;

import java.awt.BorderLayout;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.interfaces.GameEngine;
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
	
//	private ImageIcon heads = new ImageIcon("heads.png");
//	private ImageIcon tails = new ImageIcon("tails.png");
//	
//	private JLabel face1 = new JLabel("");
//	private JLabel face2 = new JLabel("");
			

	
	public GameEngineCallbackGUI(GameEngine gameEngine)
	{
		this.mainFrame = new MainFrame(gameEngine); 
		this.gameEngine = gameEngine;
		this.coinPanel = mainFrame.getCoinPanel();
	}
	
	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
	{
		coinFlip(coin);
	}
	
	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
	{
		
	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) 
	{
		coinFlip(coin);
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) 
	{
		// TODO Auto-generated method stub
		
	}
	
	private void coinFlip(Coin coin)
	{
		if(coin.getFace() == CoinFace.HEADS && coin.getNumber() == 1)
		{
			System.out.println("coin1heads");
			coinPanel.setFace1Heads();
		}
		else if (coin.getFace() == CoinFace.TAILS && coin.getNumber() == 1)
		{
			System.out.println("coin1tails");
			coinPanel.setFace1Tails();
		}			
		else if(coin.getFace() == CoinFace.HEADS && coin.getNumber() == 2)
		{
			System.out.println("coin2heads");
			coinPanel.setFace2Heads();
		}
		else if (coin.getFace() == CoinFace.TAILS && coin.getNumber() == 2)
		{
			System.out.println("coin2tails");
			coinPanel.setFace2Tails();
		}
	}
}
