package view;
import java.util.EventObject;
public class AddPlayerEvent extends EventObject 
{
	private String id;
	private String name;
	private String points;

	public AddPlayerEvent(Object source) 
	{
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public AddPlayerEvent(Object source, String id, String name, String points)
	{
		super(source);
		this.id = id;
		this.name = name;
		this.points = points;
	}

	public String getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public String getPoints() 
	{
		return points;
	}

}
