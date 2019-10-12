package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MainFrame;

public class MenuBarActionListener implements ActionListener
{
	private MainFrame mainFrame;
	
	public MenuBarActionListener(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{		
		int action = JOptionPane.showConfirmDialog(this.mainFrame, 
				"Do you really want to exit?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
		
		if(action == JOptionPane.OK_OPTION)
		{
			System.exit(0);
		}
		
	}

}
