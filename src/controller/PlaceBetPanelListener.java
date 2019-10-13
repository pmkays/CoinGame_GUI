package controller;



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
		}

		
		if(bet <= player.getPoints())
		{
			gameEngine.placeBet(player, bet, betType);
			//ensures that players must spin first
			mainFrame.getToolbar().getSpinSpinnerButton().setEnabled(false);
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
		summaryPanel.updatePanel();
	}

}
