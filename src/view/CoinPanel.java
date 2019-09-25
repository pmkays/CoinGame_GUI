package view;

import java.awt.BorderLayout;

import java.awt.Component;

import javax.swing.*;
import javax.swing.border.Border;

import model.enumeration.CoinFace;
import model.interfaces.Player;

public class CoinPanel extends JPanel
{
	private ImageIcon heads = new ImageIcon("heads.png");
	private ImageIcon tails = new ImageIcon("tails.png");
	
	private JLabel face1 = new JLabel(heads);
	private JLabel face2 = new JLabel(tails);
	

	private Player player;
	
	public CoinPanel()
	{
		Border innerBorder = BorderFactory.createTitledBorder("Coin Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		setLayout(new BorderLayout());

		add(face1, BorderLayout.WEST);
		add(face2, BorderLayout.EAST);
	}
	
	public void setFace1Heads()
	{
		face1.setIcon(heads);
		this.clearDisplayface1();
	}
	
	public void setFace1Tails()
	{
		face1.setIcon(tails);
		this.clearDisplayface1();
	}
	
	public void setFace2Heads()
	{
		face2.setIcon(heads);
		this.clearDisplayface2();
	}
	
	public void setFace2Tails()
	{
		face2.setIcon(tails);
		this.clearDisplayface2();
		
	}
	
	private void clearDisplayface1()
	{
		face1.repaint(); 
		face1.revalidate();
	}
	
	private void clearDisplayface2()
	{
		face2.repaint(); 
		face2.revalidate();
	}
}
