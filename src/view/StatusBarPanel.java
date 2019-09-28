package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class StatusBarPanel extends JPanel
{
	private JLabel status1;
	private JLabel status2;
	private JLabel status3;
	private String status1Text; 
	private String status2Text;
	private String status3Text;
	public StatusBarPanel()
	{
		//1 row of 3 columns
		setLayout(new GridLayout(1,3));
		
		//set up toolbar section names
		status1Text = "Number of players: "; 
		status2Text = "Player state: ";
		status3Text = "Spinner state: ";
		
		//default label if nothing is selected
		status1 = new JLabel(status1Text + "0"); 
		status1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		status2 = new JLabel(status2Text + "idle", SwingConstants.CENTER);
		status2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		status3 = new JLabel(status3Text + "idle", SwingConstants.RIGHT); 
		status3.setBorder(BorderFactory.createLineBorder(Color.black));
			
		add(status1, BorderLayout.WEST);
		add(status2, BorderLayout.CENTER);
		add(status3, BorderLayout.EAST);
	}
	

	public void setplayerUpdateCount(int count) 
	{
		status1.setText(status1Text + count);
	}
	
	public void setPlayerStatus(String player)
	{
		if(!player.isEmpty())
		{
			status2.setText(status2Text + player + " spinning");
		}
		else
		{
			status2.setText(status2Text + "idle");
		}
	}
	
	public void setSpinnerStatus(String spinning)
	{
		if(!spinning.isEmpty())
		{
			status3.setText(status3Text + "spinning");
		}
		else
		{
			status3.setText(status3Text + "idle");
		}
	}
}
