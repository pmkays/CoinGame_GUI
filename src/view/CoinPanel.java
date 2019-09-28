package view;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.Border;

import model.enumeration.CoinFace;
import model.interfaces.Player;

public class CoinPanel extends JPanel
{
	private ImageIcon heads = new ImageIcon("heads.png");
	private ImageIcon tails = new ImageIcon("tails.png");
	
	
	private JLabel face1;
	private JLabel face2;
	

	private Player player;
	
	public CoinPanel()
	{
		Image headsImage = heads.getImage(); 
		Image newHeadsImage = headsImage.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		heads = new ImageIcon(newHeadsImage);
		face1 = new JLabel(heads);
		
		Image tailsImage = tails.getImage();// transform it 
		Image newTailsImage = tailsImage.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		tails = new ImageIcon(newTailsImage);
		face2 = new JLabel(tails);
		
		Border innerBorder = BorderFactory.createTitledBorder("Coin Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		setLayout(new GridLayout(1,2));

		add(face1);
		add(face2);
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
