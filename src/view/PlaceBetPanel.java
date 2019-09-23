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

import controller.PlaceBetPanelListener;
import model.interfaces.Player;

public class PlaceBetPanel extends JPanel 
{
	private JLabel idLabel; 
	private JLabel betLabel;
	private JLabel betTypeLabel;
	private JComboBox<String> playersCombo;
	private JTextField betField;
	private JComboBox<String> betTypeCombo;
	private JButton betButton;
	private DefaultComboBoxModel<String> betTypeModel;
	private DefaultComboBoxModel<String> playersModel;
	
	private PlaceBetPanelListener placeBetPanelListener;
	 
	public PlaceBetPanel()
	{
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Place a Bet");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		
		//set up individual components
		idLabel = new JLabel("Player ID: ");
		betLabel = new JLabel("Bet: ");
		betTypeLabel = new JLabel("Bet Type: ");
		betField = new JTextField(10);
		betButton = new JButton("Place Bet");
		
		playersModel = new DefaultComboBoxModel<String>();
		playersCombo = new JComboBox<String>();
		playersCombo.setModel(playersModel);
		playersCombo.setBorder(BorderFactory.createEtchedBorder());

		
		betTypeModel = new DefaultComboBoxModel<String>();
		betTypeModel.addElement("Coin 1");
		betTypeModel.addElement("Coin 2");
		betTypeModel.addElement("Both");
		betTypeModel.addElement("No Bet");
		betTypeCombo = new JComboBox<String>();
		betTypeCombo.setModel(betTypeModel);
		betTypeCombo.setBorder(BorderFactory.createEtchedBorder());

		betButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = (String) playersCombo.getSelectedItem();
				int bet = Integer.parseInt(betField.getText());
				String betType = (String) betTypeCombo.getSelectedItem();
				if (placeBetPanelListener != null)
				{
					placeBetPanelListener.placeBetPanelEventOccurred(id, bet, betType);
					
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
		
		//next row
		gc.gridy++; //on 1
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; 
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(betLabel, gc);
		
		gc.gridx = 1; 
		gc.anchor = GridBagConstraints.LINE_START; 
		add(betField, gc);
		
		//next row
		gc.gridy++; //on 1
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; 
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(betTypeLabel, gc);
		
		gc.gridx = 1; 
		gc.anchor = GridBagConstraints.LINE_START;
		add(betTypeCombo, gc);
		
		//final row fix OK BUTTON
		gc.gridy++; //on 2
		gc.weightx = 0;
		gc.weighty = 2;
		
		gc.gridx = 1;
		add(betButton, gc);
	}

	public void setPlaceBetPanelListener(PlaceBetPanelListener placeBetPanelListener) 
	{
		this.placeBetPanelListener = placeBetPanelListener;
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

