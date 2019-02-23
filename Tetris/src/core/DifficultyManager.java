package core;


/**
 * 
 * Class that handles the difficulty of the game according to the player's score.
 * The higher the score the higher the difficulty.
 * The difficulty is translated as a speed for falling blocks.
 * 
 * @author Jeremy
 *
 */
public class DifficultyManager {
	
	/**
	 * Min speed that represent the lowest difficulty
	 */
	public static float MIN_SPEED = 0.075f;
	
	/**
	 * Max speed that represent the highest difficulty
	 */
	public static float MAX_SPEED = 0.7f;
	
	/**
	 * Current speed / difficulty
	 */
	private float m_speed = MIN_SPEED;
	
	/**
	 * Constructor
	 */
	public DifficultyManager() {
		//Nothing to do
	}
	
	/**
	 * Computes the current speed / difficulty according to the current score
	 * @param score_ Score that the player has. The higher the score, the higher the speed
	 * @return New calculated speed
	 */
	public float getCurrentSpeed(ScoreManager score_) {
		float coef = score_.getScore()/ScoreManager.BONUS/1000;
		if(coef >= 1.0f) {
			coef = 1.0f;
		}
		m_speed = MIN_SPEED + (MAX_SPEED - MIN_SPEED)*coef;
		return m_speed;
	}
}
