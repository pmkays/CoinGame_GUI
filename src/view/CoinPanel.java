package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;

import model.GameEngineImpl;
import model.enumeration.CoinFace;
import model.interfaces.Player;

public class CoinPanel extends JPanel
{
	private ImageIcon heads = new ImageIcon("heads.png");
	private ImageIcon tails = new ImageIcon("tails.png");
	
	private JLabel face1 = new JLabel(heads);
	private JLabel face2 = new JLabel(tails);
	
	private GameEngineImpl gameEngine;
	private Player player;
	
	public CoinPanel()
	{
		setLayout(new BorderLayout());
//		face1.setIcon(heads);
//		face2.setIcon(heads);
		add(face1, BorderLayout.WEST);
		add(face2, BorderLayout.EAST);
	}
	
	public void setFace1Heads()
	{
		face1.setIcon(heads);
		face1.repaint(); 
		face1.revalidate();
	}
	
	public void setFace1Tails()
	{
		face1.setIcon(tails);
		face1.repaint(); 
		face1.revalidate();
	}
	
	public void setFace2Heads()
	{
		face2.setIcon(heads);
		face2.repaint(); 
		face2.revalidate();
	}
	
	public void setFace2Tails()
	{
		face2.setIcon(tails);
		face2.repaint(); 
		face2.revalidate();
	}
}
