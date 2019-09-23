package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.*;
import javax.swing.border.Border;

import model.GameEngineImpl;
import model.interfaces.Player;

public class RemovePlayerPanel extends JPanel 
{
	private JLabel idLabel; 
	private JComboBox<String> playersCombo;
	private JButton removeButton;
	private DefaultComboBoxModel<String> playersModel;

	private RemovePlayerListener removePlayerListener;
	 
	public RemovePlayerPanel()
	{
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Remove Player");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		playersModel = new DefaultComboBoxModel<String>();
		
		idLabel = new JLabel("Player ID: ");
		playersCombo = new JComboBox<String>();
		playersCombo.setModel(playersModel);
		playersCombo.setBorder(BorderFactory.createEtchedBorder());
		
		//set up individual components
		removeButton = new JButton("Remove");
		

		removeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = (String) playersCombo.getSelectedItem();
				if (removePlayerListener != null)
				{
					removePlayerListener.removePlayerEventOccurred(id);
					playersModel.removeElement(playersCombo.getSelectedItem());
					//refreshes the view after deletion of player
				}
			}	
		});
		this.layoutComponents();
	}
	
	public void layoutComponents()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//first row
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; 
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(idLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(playersCombo, gc);
		
		//final row fix OK BUTTON
		gc.gridy++; //on 2
		gc.weightx = 0;
		gc.weighty = 2;
		
		gc.gridx = 1;
		add(removeButton, gc);
	}

	public void setRemovePlayerListener(RemovePlayerListener removePlayerListener) 
	{
		this.removePlayerListener = removePlayerListener;
	}
	
	public void showPlayers(Collection<Player> players)
	{
		System.out.println(players);
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

