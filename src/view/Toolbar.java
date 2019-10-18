package view;

import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.*;

import controller.RemoveBetActionListener;
import controller.RemovePlayerActionListener;
import controller.SpinActionListener;
import controller.SpinSpinnerActionListener;
import controller.ToolbarViewModel;
import controller.ViewPlayersActionListener;
import model.interfaces.Player;

public class Toolbar extends JPanel
{
	private JButton removePlayerButton;
	private JButton removeBetButton; 
	private JButton spinButton; 
	private JButton spinSpinnerButton; 
	private DefaultComboBoxModel<String> playersModel;
	private JComboBox<String> playersCombo;
	
	private ToolbarViewModel toolbarViewModel;
	
	public Toolbar()
	{
		setBorder(BorderFactory.createEtchedBorder());
		
		//set up components
		removePlayerButton = new JButton("Remove Player");
		removeBetButton = new JButton("Remove Bet");
		spinButton = new JButton("Spin Coin"); 
		spinSpinnerButton = new JButton("Spin Spinner");
		playersModel = new DefaultComboBoxModel<String>();
		playersCombo = new JComboBox<String>();
		playersCombo.setModel(playersModel);
		playersCombo.setBorder(BorderFactory.createEtchedBorder());
		
		//set action listeners for each function of the toolbar
		removePlayerButton.addActionListener(new RemovePlayerActionListener(this));
		removeBetButton.addActionListener(new RemoveBetActionListener(this));
		spinButton.addActionListener(new SpinActionListener(this));
		spinSpinnerButton.addActionListener(new SpinSpinnerActionListener(this));
		playersCombo.addActionListener(new ViewPlayersActionListener(this));

		setLayout(new FlowLayout(FlowLayout.LEFT)); 
		
		add(removePlayerButton);
		add(removeBetButton);
		add(spinButton);
		add(spinSpinnerButton);
		add(playersCombo);
	}
	
	public void setToolbarViewModel(ToolbarViewModel viewModel)
	{
		this.toolbarViewModel = viewModel;
	}
	

	public ToolbarViewModel getToolbarViewModel() 
	{
		return this.toolbarViewModel;
	}

	
	public JButton getSpinSpinnerButton()
	{
		return this.spinSpinnerButton;
	}
	
	public JButton getSpinButton()
	{
		return this.spinButton;
	}
	
	public JComboBox<String> getPlayersCombo()
	{
		return this.playersCombo; 
	}
	
	public void showPlayers(Collection<Player> players)
	{
		//remove all players first
		playersModel.removeAllElements();
		
		if(players != null && !players.isEmpty())
		{
			for(Player player : players)
			{
				//add each player to the combobox
				playersModel.addElement(player.getPlayerId());
			}
		}
		else
		{
			playersModel.addElement("No players added");
		}
	}


}
