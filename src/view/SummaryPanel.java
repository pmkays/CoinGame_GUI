package view;

import java.awt.BorderLayout;

import java.awt.Dimension;

import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SummaryPanel extends JPanel 
{
	private JLabel displayUpdates;
	private StatusBarPanel statusBarPanel;
	private Collection<Player> players;
	private String playersInfo;
	private MainFrame mainFrame;
	public SummaryPanel(GameEngine gameEngine, StatusBarPanel statusBarPanel, MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		this.statusBarPanel = statusBarPanel;
		this.players  = gameEngine.getAllPlayers(); 
		
		//set out summary panel
		Dimension dim = getPreferredSize();
		dim.height = 150;
		setPreferredSize(dim);

		//set up border
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
		playersInfo = "";
		for(Player player : players)
		{	
			playersInfo += player.toString() + "<br/>";
		}
		
		//use html elements to format the outputted string
		String playersDisplay = "<html>" + playersInfo + "</html>";
		displayUpdates.setText(playersDisplay);
		this.repaint();
		this.revalidate();
	}
	
	public void updateWinner()
	{
		updatePanel();
		int highestPoints = 0;
		String winner = "";
		for(Player player : players)
		{
			if(!players.isEmpty())
			{
				if(highestPoints < player.getPoints() && player.getBetType()!= BetType.NO_BET)
				{
					highestPoints = player.getPoints();
					winner = player.getPlayerId();
				}
			}
		}
		
		if(!winner.isEmpty())
		{
			//use the playersInfo from updatePanel() and output the winner; html used for formatting
			String displayWinner = "<html><br/>" + playersInfo + "The winner is " + 
			winner + " with " + String.valueOf(highestPoints) + " points!<br/></html>";
			displayUpdates.setText(displayWinner);
			this.repaint();
			this.revalidate();
			JOptionPane.showMessageDialog(mainFrame,
			        "Thanks for playing!", "Round finished",
			        JOptionPane.INFORMATION_MESSAGE);
		}

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
