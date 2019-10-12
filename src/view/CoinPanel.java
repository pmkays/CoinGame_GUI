package view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;
import javax.swing.border.Border;

import model.enumeration.CoinFace;

public class CoinPanel extends JPanel implements ComponentListener
{
	private ImageIcon heads = new ImageIcon("heads.png");
	private ImageIcon tails = new ImageIcon("tails.png");
	
	private boolean haveSpun;
	
	//heads and tails images resized for initial view
	private Image newHeadsImage;
	private Image newTailsImage;
	
	//jlabels getting added on
	private JLabel face1;
	private JLabel face2;
	
	//resizable icons set by component listener
	private ImageIcon tailsIcon;
	private ImageIcon headsIcon;
	
	private MainFrame mainFrame;
	
	public CoinPanel(Toolbar toolbar, MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		this.addComponentListener(this);
		
		haveSpun = false;
		
		Image headsImage = heads.getImage(); 
		newHeadsImage = headsImage.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);  
		
		Image tailsImage = tails.getImage();
		newTailsImage = tailsImage.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); 
		
		heads = new ImageIcon(newHeadsImage);
		tails = new ImageIcon(newTailsImage);
		face1 = new JLabel(heads);
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
		face1.setIcon(headsIcon);
		this.clearDisplayface1();
	}
	
	public void setFace1Tails()
	{
		face1.setIcon(tailsIcon);
		this.clearDisplayface1();
	}
	
	public void setFace2Heads()
	{
		face2.setIcon(headsIcon);
		this.clearDisplayface2();
	}
	
	public void setFace2Tails()
	{
		face2.setIcon(tailsIcon);
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
	

	public void setCoin(CoinFace face1, CoinFace face2) 
	{
		haveSpun = true;
		if (face1 == CoinFace.HEADS)
		{
			setFace1Heads(); 
		}
		else if (face1 == CoinFace.TAILS)
		{
			setFace1Tails(); 
		}
		
		if(face2 == CoinFace.HEADS)
		{
			setFace2Heads();
		}
		else if(face2 == CoinFace.TAILS)
		{
			setFace2Tails();
		}	
	}
	
	private void scaleImage(int width, int height)
	{
		//the panel's width is divided to fit two coin panels
		int newWidth = width/2;
		
		//use the images that have a minimum size already displayed
	    Image heads = newHeadsImage;
	    Image tails = newTailsImage; 

	    //if the panel is expanded horizontally (i.e. panel's width is getting larger)
	    if(newWidth >= height)
	    {
	        heads = newHeadsImage.getScaledInstance(height, height, Image.SCALE_SMOOTH);
	        tails = newTailsImage.getScaledInstance(height, height, Image.SCALE_SMOOTH);
	    }
	    else
	    {
	    	heads = newHeadsImage.getScaledInstance(newWidth, newWidth, Image.SCALE_SMOOTH);
		    tails = newTailsImage.getScaledInstance(newWidth, newWidth, Image.SCALE_SMOOTH);
	    }
	    headsIcon = new ImageIcon(heads);
	    tailsIcon = new ImageIcon(tails);
	    
	    //sets initial default coin faces before anyone has spun
	    if(!haveSpun)
	    {
	    	face1.setIcon(headsIcon);
	    	face2.setIcon(tailsIcon);
	    }
	}
	

	@Override
	public void componentResized(ComponentEvent arg0) 
	{
		scaleImage(this.getWidth(), this.getHeight());
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
