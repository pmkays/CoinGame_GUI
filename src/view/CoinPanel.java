package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;

import model.GameEngineImpl;
import model.enumeration.CoinFace;
import model.interfaces.Player;

public class CoinPanel extends JPanel
{
	private JLabel heads = new JLabel(new ImageIcon(""));
	private JLabel tails = new JLabel(new ImageIcon(""));
	
	private JLabel[] coin1 = {heads, tails};
	private JLabel[] coin2 = {heads, tails};
	
	private GameEngineImpl gameEngine;
	private Player player;
	
	public CoinPanel(Player spinPlayer, GameEngineImpl gameEngine)
	{
		this.player = spinPlayer;
		this.gameEngine = gameEngine;
		setLayout(new BorderLayout());
		
		if(player.getResult().getCoin1().getFace() == CoinFace.HEADS)
		{
			add(heads, BorderLayout.WEST);
		}
		else if (player.getResult().getCoin1().getFace() == CoinFace.TAILS)
		{
			//disable the other one first
			add(tails, BorderLayout.WEST);
		}
		
		if(player.getResult().getCoin2().getFace() == CoinFace.HEADS)
		{
			add(heads, BorderLayout.EAST);
		}
		else if (player.getResult().getCoin2().getFace() == CoinFace.TAILS)
		{
			//disable the other one first
			add(tails, BorderLayout.EAST);
		}
	}
}
