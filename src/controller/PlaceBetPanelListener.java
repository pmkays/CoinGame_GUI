package controller;

import java.util.Collection;
import java.util.EventListener;


import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.enumeration.BetType;
import model.interfaces.Player;
import view.MainFrame;
import view.SummaryPanel;
import view.Toolbar;

public class PlaceBetPanelListener
{
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private SummaryPanel summaryPanel;
	private Toolbar toolbar;
	
	public PlaceBetPanelListener(GameEngine gameEngine, MainFrame mainFrame, SummaryPanel summaryPanel, Toolbar toolbar) 
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel; 
		this.toolbar = toolbar;
	}

	public void placeBetPanelEventOccurred(String id, int bet, String betTypeString)
	{
		BetType betType = null;
		int noBet = 0;
		Player player = gameEngine.getPlayer(id);
		
		switch(betTypeString)
		{
		case "Coin 1":
			betType = BetType.COIN1;
			break;
		case "Coin 2":
			betType = BetType.COIN2;
			break;
		case "Both":
			betType = BetType.BOTH;
			break;
		case "No Bet":
			betType = BetType.NO_BET;
			bet = noBet; 
			break;
		}

		
		if(bet <= player.getPoints())
		{
			gameEngine.placeBet(player, bet, betType);
			JOptionPane.showMessageDialog(mainFrame,
			        "Bet successfully placed for Player:"  + id, "Bet Placed",
			        JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(mainFrame,
			        "Unable to place bet. Please ensure Player: "  + id + " has sufficient points", "Invalid bet",
			        JOptionPane.ERROR_MESSAGE);
		}
		
		System.out.println(player);
		
		summaryPanel.updatePanel();
	}

}
