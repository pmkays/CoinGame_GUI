package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;

public class SidePanels extends JPanel
{

	public SidePanels(PlaceBetPanel placeBetPanel, AddPlayerPanel addPlayerPanel) 
	{
		Dimension dim = getPreferredSize();
		dim.width = 270;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Function Panels");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new BorderLayout());
		add(addPlayerPanel, BorderLayout.NORTH);
		add(placeBetPanel, BorderLayout.SOUTH);
	}
	

}
