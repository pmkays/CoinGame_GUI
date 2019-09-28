package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.Collection;

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
	JLabel displayUpdates;
	private StatusBarPanel statusBarPanel;
	public SummaryPanel(GameEngine gameEngine, StatusBarPanel statusBarPanel)
	{
		this.statusBarPanel = statusBarPanel;
		Dimension dim = getPreferredSize();
		dim.height = 150;
		setPreferredSize(dim);
		this.gameEngine = gameEngine;
		
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
		String playersDisplay = "<html>";	
		Collection<Player> players = gameEngine.getAllPlayers();
		for(Player player : players)
		{	
			playersDisplay += player.toString() + "<br/>";
		}
		playersDisplay += "</html>";
		displayUpdates.setText(playersDisplay);
		this.repaint();
		this.revalidate();
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
