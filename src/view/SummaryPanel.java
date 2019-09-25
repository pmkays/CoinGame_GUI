package view;

import java.awt.Dimension;

import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SummaryPanel extends JPanel 
{
	private GameEngine gameEngine;
	JLabel displayUpdates;
	public SummaryPanel(GameEngine gameEngine)
	{
		Dimension dim = getPreferredSize();
		dim.height = 150;
		setPreferredSize(dim);
		this.gameEngine = gameEngine;
		
		Border innerBorder = BorderFactory.createTitledBorder("Summary Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		

		displayUpdates = new JLabel("");
		add(displayUpdates);
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

}
