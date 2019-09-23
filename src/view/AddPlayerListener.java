package view;

import java.util.EventListener;

public interface AddPlayerListener extends EventListener
{
	
	public void addPlayerEventOccurred(AddPlayerEvent e);

}
