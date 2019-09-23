package view;

import java.util.EventObject;

public class RemoveBetPanelEvent extends EventObject
{
	private String id;

	public RemoveBetPanelEvent(Object arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public RemoveBetPanelEvent(Object arg0, String id) 
	{
		super(arg0);
		this.id = id;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	

}
