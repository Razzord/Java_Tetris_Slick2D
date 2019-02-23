package core;

/**
 * 
 * Class to handle the player's score according to the level's state.
 * Each time a row is cleared, then the player earn points.
 * 
 * @author Jeremy
 *
 */
public class ScoreManager {
	
	/**
	 * Number of points earn for each block
	 */
	public static int BONUS = 1;
	
	/**
	 * THe player's current score
	 */
	private int m_score = 0;
	
	/**
	 * Tracks the number of snapped blocks the last time the score was updated
	 */
	private int m_lastNumberOfSnappedBlock = 0;
	
	/**
	 * Update the player's score according to the number of snapped blocks
	 * @param level_ Current level
	 */
	public void update(StaticLevel level_) {
		int currentNumberOfSnappedBlock = level_.getBlocks().size();
		int deltaBlocks = m_lastNumberOfSnappedBlock - currentNumberOfSnappedBlock;
		
		//If the number decreased, then blocks were removed because a line was cleared
		if(deltaBlocks > 0) {
			m_score += BONUS*level_.CELL_WIDTH_NUMBER;
		}
		
		//Now update the tracker
		m_lastNumberOfSnappedBlock = currentNumberOfSnappedBlock;
	}
	
	/**
	 * @return The player's current score
	 */
	public int getScore() {
		return m_score;
	}
}
