package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Toolbar;

public class SpinSpinnerActionListener implements ActionListener
{
	private Toolbar toolbar;

	public SpinSpinnerActionListener(Toolbar toolbar)
	{
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(toolbar.getToolbarListener() != null)
		{
			toolbar.getToolbarListener().spinSpinnerEventOccurred();
		}
	}

}