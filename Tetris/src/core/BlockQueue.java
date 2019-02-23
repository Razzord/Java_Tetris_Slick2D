package core;

/**
 * 
 * Class that computes all the queue events i.e. 
 * - Gives the current falling block
 * - Generate the next block
 * - Store a save block for the user to switch with if needed
 * 
 * It gives all the mechanics to change the blocks between the save one, the current one and the next one.
 * 
 * @author Jeremy
 *
 */
public class BlockQueue {
	
	/**
	 * Current block that the player moves in the level
	 */
	IBlock m_currentBlock = null;
	
	/**
	 * Next block to come that will take the place of the current block when it is snapped
	 */
	IBlock m_nextBlock = null;
	
	/**
	 * A saved block that the player can use as a switch
	 */
	IBlock m_savedBlock = null;
	
	/**
	 * Random block generator to get new blocks
	 */
	private RandomBlockGenerator m_generator;
	
	/**
	 * Constructor
	 * @param level_ reference to the current level
	 */
	public BlockQueue(StaticLevel level_) {
		m_generator = new RandomBlockGenerator(level_);
		
		//Setup blocks
		m_currentBlock = m_generator.getRandomBlock();
		m_currentBlock.moveToSpawn();
		
		m_nextBlock = m_generator.getRandomBlock();
		m_nextBlock.moveToNext();
	}
	
	/**
	 * Switch the current block with the save block
	 * If the saved block is not yet defined (null), it will assign to the 
	 * current block the supposed next block. Otherwise, the current block
	 * will be assign the the previously saved block
	 */
	public void switchToSaveBlock() {
		IBlock tempBlock = m_currentBlock;
		
		if(m_savedBlock != null) {
			m_currentBlock = m_savedBlock;
			m_currentBlock.moveToSpawn();
		} else {
			m_currentBlock = m_nextBlock;
			m_currentBlock.moveToSpawn();
			
			m_nextBlock = m_generator.getRandomBlock();
			m_nextBlock.moveToNext();
		}
		m_savedBlock = tempBlock;
		m_savedBlock.moveToSaved();
	}
	
	/**
	 * Sets the current block to be the supposed next block
	 * and generate a new random block for the next block to come
	 */
	public void getNewNextBlock() {
		m_currentBlock = m_nextBlock;
		m_currentBlock.moveToSpawn();
		
		m_nextBlock = m_generator.getRandomBlock();
		m_nextBlock.moveToNext();
	}
	
	/**
	 * @return the current block
	 */
	public IBlock getCurrentBlock() {
		return m_currentBlock;
	}
	
	/**
	 * @return the next block
	 */
	public IBlock getNextBlock() {
		return m_nextBlock;
	}
	
	/**
	 * @return the saved block
	 */
	public IBlock getSavedBlock() {
		return m_savedBlock;
	}
	
	/**
	 * Update the state of the queue according to the state of blocks in it
	 */
	public void update() {
		if(m_currentBlock.isSnapped()) {
			getNewNextBlock();
		} else {
			//Nothing to do
		}
	}
	
	/**
	 * Allows to render the saved block and the next coming block
	 */
	public void render() {
		if(m_savedBlock != null) {
			m_savedBlock.render();
		}
		m_nextBlock.render();
	}
}
