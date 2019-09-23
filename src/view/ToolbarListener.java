package view;

import java.awt.event.ActionEvent;
import java.util.EventListener;

import javax.swing.JButton;

public interface ToolbarListener extends EventListener
{
	public void toolbarEventOccurred(JButton button);

}
