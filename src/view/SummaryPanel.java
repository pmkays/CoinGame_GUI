package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.Collection;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SummaryPanel extends JPanel 
{
	private GameEngine gameEngine;
	private JLabel displayUpdates;
	private StatusBarPanel statusBarPanel;
	private Collection<Player> players;
	private String playersDisplay;
	public SummaryPanel(GameEngine gameEngine, StatusBarPanel statusBarPanel)
	{
		this.statusBarPanel = statusBarPanel;
		Dimension dim = getPreferredSize();
		dim.height = 150;
		setPreferredSize(dim);
		this.gameEngine = gameEngine;
		this.players  = gameEngine.getAllPlayers(); 
		
		Border innerBorder = BorderFactory.createTitledBorder("Summary Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		

		displayUpdates = new JLabel("");
		
		setLayout(new BorderLayout());
		add(new JScrollPane(displayUpdates), BorderLayout.CENTER);
		add(statusBarPanel, BorderLayout.SOUTH); 
	}
	
	public void updatePanel()
	{
		playersDisplay = "";
		playersDisplay = "<html>";	
		for(Player player : players)
		{	
			playersDisplay += player.toString() + "<br/>";
		}
		playersDisplay += "</html>";
		displayUpdates.setText(playersDisplay);
		this.repaint();
		this.revalidate();
	}
	
	public void updateWinner()
	{
		updatePanel();
		int highestPoints = 0;
		for(Player player : players)
		{
			if(highestPoints < player.getPoints())
			{
				highestPoints = player.getPoints();
			}
		}
		String displayWinner = playersDisplay + "<html><br/>" + String.valueOf(highestPoints) + "<br/></html>";
		displayUpdates.setText(displayWinner);
		this.repaint();
		this.revalidate();
		System.out.println(displayWinner);
	}
	
	public void updatePlayerCount(int count)
	{
		statusBarPanel.setplayerUpdateCount(count);
	}
	
	public void updatePlayerStatus(String id)
	{
		statusBarPanel.setPlayerStatus(id);
	}
	
	public void updateSpinnerStatus(boolean spinning)
	{
		statusBarPanel.setSpinnerStatus(spinning);
	}
	

}
