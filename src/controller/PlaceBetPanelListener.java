package controller;

import java.util.EventListener;


import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.enumeration.BetType;
import model.interfaces.Player;
import view.MainFrame;
import view.SummaryPanel;

public class PlaceBetPanelListener
{
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private SummaryPanel summaryPanel;
	
	public PlaceBetPanelListener(GameEngine gameEngine, MainFrame mainFrame, SummaryPanel summaryPanel) 
	{
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
	}

	public void placeBetPanelEventOccurred(String id, int bet, String betTypeString)
	{
		BetType betType = null;
		
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
			break;
		}
		Player player = gameEngine.getPlayer(id);
		gameEngine.placeBet(player, bet, betType);
		
		System.out.println(player);
		
		JOptionPane.showMessageDialog(mainFrame,
		        "Bet successfully placed for Player:"  + id, "Bet Placed",
		        JOptionPane.INFORMATION_MESSAGE);
		summaryPanel.updatePanel();
	}

}
