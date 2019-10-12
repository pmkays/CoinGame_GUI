package view;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.*;

import controller.RemoveBetActionListener;
import controller.RemovePlayerActionListener;
import controller.SpinActionListener;
//import controller.SpinPanelListener;
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
	
	private ToolbarViewModel toolbarListener;
//	private SpinPanelListener spinListener; 
	
	public Toolbar()
	{
		setBorder(BorderFactory.createEtchedBorder());
		removePlayerButton = new JButton("Remove Player");
		removeBetButton = new JButton("Remove Bet");
		spinButton = new JButton("Spin Coin"); 
		spinSpinnerButton = new JButton("Spin Spinner");
		playersModel = new DefaultComboBoxModel<String>();
		playersCombo = new JComboBox<String>();
		playersCombo.setModel(playersModel);
		playersCombo.setBorder(BorderFactory.createEtchedBorder());
		
		spinSpinnerButton.setEnabled(false);
		
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
	
	public void setToolbarListener(ToolbarViewModel listener)
	{
		this.toolbarListener = listener;
	}
	

	public ToolbarViewModel getToolbarListener() 
	{
		// TODO Auto-generated method stub
		return this.toolbarListener;
	}

	
//	public void setSpinListener(SpinPanelListener listener)
//	{
//		this.spinListener = listener;
//	}
	
	
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
		return playersCombo; 
	}
	
	public void showPlayers(Collection<Player> players)
	{
		playersModel.removeAllElements();
		if(players != null && !players.isEmpty())
		{
			for(Player player : players)
			{
				playersModel.addElement(player.getPlayerId());
			}
		}
		else
		{
			playersModel.addElement("No players added");
		}
	}


}
