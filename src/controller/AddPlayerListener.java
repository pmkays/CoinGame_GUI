package controller;

import java.util.EventListener;



import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.SimplePlayer;
import model.interfaces.Player;
import view.MainFrame;
import view.SummaryPanel;

public class AddPlayerListener
{
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private SummaryPanel summaryPanel;
	
	public AddPlayerListener(GameEngine gameEngine, MainFrame mainFrame, SummaryPanel summaryPanel) 
	{
		this.gameEngine = gameEngine; 
		this.mainFrame = mainFrame;
		this.summaryPanel = summaryPanel;
	}

	public void addPlayerEventOccurred(String id, String name, int points)
	{
		
		Player player = new SimplePlayer(id, name, points);
		
		gameEngine.addPlayer(player); //same playerID still can get added?
		System.out.println(gameEngine.getAllPlayers());
		JOptionPane.showMessageDialog(mainFrame,
		        "Player: " + id + " added successfully", "Player Added",
		        JOptionPane.INFORMATION_MESSAGE);
		summaryPanel.updatePanel();
		
	}

}
