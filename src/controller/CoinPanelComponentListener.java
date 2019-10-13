package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import view.CoinPanel;

public class CoinPanelComponentListener extends ComponentAdapter
{
	/*new class for component listener to use ComponentAdapter and avoid having to 
	implement all methods in ComponentListener*/
	private CoinPanel coinPanel;
	
	public CoinPanelComponentListener(CoinPanel coinPanel)
	{
		this.coinPanel = coinPanel;
	}
	
	@Override
	public void componentResized(ComponentEvent arg0) 
	{
		coinPanel.scaleImage(coinPanel.getWidth(), coinPanel.getHeight());
	}
}
