package view;

import java.util.EventObject;

public class PlaceBetPanelEvent extends EventObject
{	
	private String id;
	private String bet;
	private String betType;

	public PlaceBetPanelEvent(Object arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public PlaceBetPanelEvent(Object object, String id, String points, String betType) 
	{
		super(object);
		this.id = id;
		this.bet = points;
		this.betType = betType;
	}

	public String getId() 
	{
		return id;
	}

	public String getBet() 
	{
		return bet;
	}

	public String getBetType() 
	{
		return betType;
	}


}
