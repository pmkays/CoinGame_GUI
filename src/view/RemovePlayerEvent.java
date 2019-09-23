package view;

import java.util.EventObject;

public class RemovePlayerEvent extends EventObject
{
	private String id;

	public RemovePlayerEvent(Object source) 
	{
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public RemovePlayerEvent(Object source, String id) 
	{
		super(source);
		this.id = id;
	}
	
	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

}
