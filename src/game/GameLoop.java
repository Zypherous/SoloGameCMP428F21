package game;
// A fixed step game loop that updates the game 60 times per second.

public class GameLoop implements Runnable{
	
	public static final int UPDATES_PER_SECOND = 60;
	
	// Game 
	private Game game;
	
	// Interval for printing to console for debugging
	private long nextConsoleLog;
	
	// fps = frames per second, ups = updates per second;
	private int fps, ups;
	
	
	// Variable that lets main know if the game is running
	private boolean running;
	
	// How many times per second we will be updating the game
	private final double updateRate = 1.0d/UPDATES_PER_SECOND;
	
	public GameLoop(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		running = true;
		
		// Keep track of time it takes to update
		double accumulator = 0;
		
		// Keep track of current time and last updated time
		long currentTime, lastUpdate = System.currentTimeMillis();
		
		// Set's the first console print to 1 second from when loop begins
		nextConsoleLog = System.currentTimeMillis() + 1000;
		
		// Loop for game
		while(running) {
			
			// Get current system time in milli seconds
			currentTime = System.currentTimeMillis();
			
			// Convert time into seconds
			double lastRenderInSeconds = (currentTime - lastUpdate) /1000d;
			
			// Set an accumulator to the last time a render was made
			accumulator += lastRenderInSeconds;
			
			// Grab the time at which the last update was made
			lastUpdate = currentTime;
			
			// Fixed render times
			if(accumulator >= updateRate) {
				// If the accumulator has passed enough time, update the game.
				while(accumulator > updateRate) {
//					if(gamePaused == false({
						update();
//					}
					accumulator -= updateRate;
				}
				render();
			}
			
			// Render to the screen
			
			printDetails();
		}
	}
	private void update() {
		// increments updates per second
		game.update();
		ups++;
	}
	private void render() {
		// Increments frames per second
		game.render();
		fps++;;
	}

	// Debugging tools to see visual information
	
	private void printDetails() {
		if(System.currentTimeMillis() > nextConsoleLog) {
			System.out.println(String.format("FPS: %d  UPS: %d", fps, ups));
			System.out.println(String.format("Camera x: %d, y: %d", 
					(int)game.getState().getCamera().getPosition().getX(), 
					(int)game.getState().getCamera().getPosition().getY()));
			fps = 0;
			ups = 0;
			nextConsoleLog = System.currentTimeMillis() + 1000;
		}
	}
}
