package controller;

import java.util.EventListener;


import javax.swing.JOptionPane;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.Player;
import view.MainFrame;

public class AddPlayerListener
{
	private MainFrame mainFrame;
	private GameEngineImpl gameEngine;
	
	public AddPlayerListener(GameEngineImpl gameEngine, MainFrame mainFrame) 
	{
		this.gameEngine = gameEngine; 
		this.mainFrame = mainFrame;
	}

	public void addPlayerEventOccurred(String id, String name, int points)
	{
		
		Player player = new SimplePlayer(id, name, points);
		
		gameEngine.addPlayer(player); //same playerID still can get added?
		System.out.println(gameEngine.getAllPlayers());
		JOptionPane.showMessageDialog(mainFrame,
		        "Player: " + id + " added successfully", "Player Added",
		        JOptionPane.INFORMATION_MESSAGE);
	}

}
