package view;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

public class App {

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				GameEngineImpl gameEngine = new GameEngineImpl();			
				GameEngineCallbackGUI engineCallbackGUI = new GameEngineCallbackGUI(gameEngine);
				gameEngine.addGameEngineCallback(engineCallbackGUI);

			}
		});
	}

}
