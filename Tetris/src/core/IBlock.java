package core;

/**
 * 
 * Interface to all the different blocks that can be found in tetris game
 * 
 * @author Jeremy
 *
 */
public interface IBlock {
	
	/**
	 * Method to draw the block on the screen
	 */
	public void render();
	
	/**
	 * Method to update the position of the block according to its velocity
	 * and the player's inputs
	 * @param delta_ delta time used to be in sync
	 * @param speed_ speed of the falling block
	 */
	public void update(int delta_, float speed_);
	
	/**
	 * Method to initialize the block. Has to be implemented by each block classes
	 */
	public void init();
	
	/**
	 * Move the block to the given direction
	 * @param direction_
	 */
	public void move(Direction direction_);
	
	/**
	 * Causes the block to move down instantly and snap to the grid when it collides
	 * @param delta_ delta time used to be in sync
	 * @param speed_ speed of the falling block
	 */
	public void zap(int delta_, float speed_);
	
	/**
	 * Rotate the block with the given rotation
	 * @param rotation_
	 */
	public void rotate(Rotation rotation_);
	
	/**
	 * @return True if the block is snapped to the level
	 */
	public boolean isSnapped();
	
	/**
	 * Move the block to the SavedBlock coordinates
	 */
	public void moveToSaved();
	
	/**
	 * Move the block to the spawn
	 */
	public void moveToSpawn();
	
	/**
	 * Move the block to the queuing coordinates
	 */
	public void moveToNext();
}
