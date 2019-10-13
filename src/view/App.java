package view;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.interfaces.GameEngineCallback;

public class App 
{
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				GameEngine gameEngine = new GameEngineImpl();			
				GameEngineCallback engineCallbackGUI = new GameEngineCallbackGUI(gameEngine);
				GameEngineCallback engineCallback = new GameEngineCallbackImpl();
				gameEngine.addGameEngineCallback(engineCallbackGUI);
				gameEngine.addGameEngineCallback(engineCallback);

			}
		});
	}

}
