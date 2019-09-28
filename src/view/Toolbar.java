package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.ToolbarListener;

public class Toolbar extends JPanel implements ActionListener
{
	private JButton addPlayerButton;
	private JButton removePlayerButton;
	private JButton placeBetButton;
	private JButton removeBetButton; 
	private JButton spinButton; 
	
	private ToolbarListener toolbarListener;
	
	public Toolbar()
	{
		setBorder(BorderFactory.createEtchedBorder());
		addPlayerButton = new JButton("Add Player");
		removePlayerButton = new JButton("Remove Player");
		placeBetButton = new JButton("Place Bet");
		removeBetButton = new JButton("Remove Bet");
		spinButton = new JButton("Spin Coin"); 
		
		addPlayerButton.addActionListener(this);
		removePlayerButton.addActionListener(this);
		placeBetButton.addActionListener(this);
		removeBetButton.addActionListener(this);
		spinButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT)); 
		
		add(addPlayerButton);
		add(removePlayerButton);
		add(placeBetButton);
		add(removeBetButton);
		add(spinButton);

	}
	
	public void setToolbarListener(ToolbarListener listener)
	{
		this.toolbarListener = listener;
	}


//	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton clicked = (JButton) e.getSource();
		
		if(toolbarListener!= null)
		{
			toolbarListener.toolbarEventOccurred(clicked);
		};
	}

	public JButton getAddPlayerButton() 
	{
		return addPlayerButton;
	}

	public JButton getRemovePlayerButton() 
	{
		return removePlayerButton;
	}

	public JButton getPlaceBetButton() 
	{
		return placeBetButton;
	}

	public JButton getRemoveBetButton() 
	{
		return removeBetButton;
	}

	public JButton getSpinButton() 
	{
		return spinButton;
	}

}
