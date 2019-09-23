package controller;

import java.util.Collection;
import java.util.Iterator;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.Player;
import view.AddPlayerEvent;
import view.PlaceBetPanelEvent;

public class Controller 
{
	private GameEngineImpl gameEngine =  new GameEngineImpl();

	public void addPlayer(AddPlayerEvent e) 
	{
		String id = e.getId();
		String name = e.getName();
		int points = Integer.parseInt(e.getPoints());
		
		Player player = new SimplePlayer(id, name, points);
		
		gameEngine.addPlayer(player); //same playerID still can get added?
		System.out.println(gameEngine.getAllPlayers());
	}
	
	public void removePlayer(String id) 
	{
		Collection<Player> players = getPlayers();
		Player toDelete = null;
		for(Player player : players)
		{
			if(player.getPlayerId().equals(id))
			{
				toDelete = player;
			}
		}
		gameEngine.removePlayer(toDelete);
		System.out.println(gameEngine.getAllPlayers());
	}
	
	public Collection<Player> getPlayers()
	{
		return gameEngine.getAllPlayers();	
	}
	
	public void placeBet(PlaceBetPanelEvent e)
	{
		String id = e.getId();
		int bet = Integer.parseInt(e.getBet());
		BetType betType = null;
		
		switch(e.getBetType())
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
	}

	public void removeBet(String id) 
	{
		Collection<Player> players = getPlayers();
		for(Player player : players)
		{
			if(player.getPlayerId().equals(id))
			{
				player.resetBet();
			}
		}
		System.out.println(gameEngine.getAllPlayers());
	}

}
