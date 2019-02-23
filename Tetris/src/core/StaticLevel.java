package core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import core.block.ElementaryBlock;

/**
 * Represent the level and the snapped blocks on it
 * @author Jeremy
 *
 */
public class StaticLevel {
	
	/**
	 * Size of a grid cell
	 */
	public final int CELL_SIZE = 30;
	
	/**
	 * Number of cells in the height direction (y coordinate)
	 */
	public final int CELL_HEIGHT_NUMBER = 20;
	
	/**
	 * Number of cells in the width direction (x coordinate)
	 */
	public final int CELL_WIDTH_NUMBER = 10;
	
	/**
	 * List of blocks snapped on the grid. These blocks don't move !
	 */
	private ArrayList<ElementaryBlock> m_blockList;
	
	/**
	 * Tells if the level is full and that no other blocks can be snap.
	 * This means that the game is over.
	 */
	private boolean m_isLevelFull = false;
	
	/**
	 * Constructor
	 */
	public StaticLevel() {
		m_blockList = new ArrayList<ElementaryBlock>();
	}
	
	/**
	 * @return all snapped elementary blocks
	 */
	public ArrayList<ElementaryBlock> getBlocks(){
		return m_blockList;
	}
	
	/**
	 * @return the X coordinate where blocks should spawn (pixel coordinates)
	 */
	public float getSpawnX() {
		return CELL_SIZE*3.0f;
	}
	
	/**
	 * @return the Y coordinate where blocks should spawn (pixel coordinates)
	 */
	public float getSpawnY() {
		return CELL_SIZE*1.0f;
	}
	
	/**
	 * @return the X coordinate where saved blocks should be drawn (pixel coordinates)
	 */
	public float getSavedX() {
		return CELL_SIZE*(CELL_WIDTH_NUMBER + 1);
	}
	
	/**
	 * @return the Y coordinate where next blocks should be drawn (pixel coordinates)
	 */
	public float getSavedY() {
		return CELL_SIZE*(CELL_HEIGHT_NUMBER - 10);
	}
	
	/**
	 * @return the X coordinate where next blocks should be drawn (pixel coordinates)
	 */
	public float getNextX() {
		return CELL_SIZE*(CELL_WIDTH_NUMBER + 1);
	}
	
	/**
	 * @return the Y coordinate where next blocks should be drawn (pixel coordinates)
	 */
	public float getNextY() {
		return CELL_SIZE*(CELL_HEIGHT_NUMBER - 5);
	}
	
	/**
	 * @return True if the level cannot snap any new blocks and result in a game over
	 */
	public boolean isLevelFull() {
		return m_isLevelFull;
	}
	
	/**
	 * Snaps the block on the grid
	 * @param block_ Block to snap on the grid
	 */
	public void snap(ElementaryBlock block_) {
		m_blockList.add(block_);
		
		//Update the levelFull variable
		updateIsFull(block_);
	}
	
	/**
	 * Updates the m_isLevelFull variable according to the block snapping coordinates
	 * @param block_ Freshly new snapped block that may fill the level
	 */
	private void updateIsFull(ElementaryBlock block_) {
		if(block_.getY() <= getSpawnY()) {
			m_isLevelFull = true;
		}
	}
	
	/**
	 * Help method to draw a grid where blocks should be drawn
	 */
	private void showGrid(Graphics graphics_) {
		for(int i = 0; i < CELL_HEIGHT_NUMBER; i++) {
			for(int j = 0; j < CELL_WIDTH_NUMBER; j++) {
				Rectangle r = new Rectangle(j*CELL_SIZE, i*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				graphics_.draw(r);
			}
		}
	}
	
	/**
	 * Draw all snapped blocks
	 */
	public void render(Graphics graphics_) {
		for(ElementaryBlock b : m_blockList) {
			b.render();
		}
		
		showGrid(graphics_);
	}
	
	/**
	 * Update the status of the blocks i.e. checking if a row is
	 * deleted and moving blocks accordingly
	 * @param delta_ time between 2 frames
	 */
	public void update(int delta_) {
		cleanCompletedRows();
	}
	
	/**
	 * Remove the content of completed rows in the level
	 */
	private void cleanCompletedRows() {
		boolean isRowCompleted = false;
		
		//Row loop
		for(int i = 0; i < CELL_HEIGHT_NUMBER; i++) {
			
			//Initialize the check variable
			isRowCompleted = true;
			
			//Column loop
			for(int j = 0; j < CELL_WIDTH_NUMBER; j++) {
				isRowCompleted &= isCellUsed(i, j);
			}
			
			if(isRowCompleted) {
				clearRow(i);
			}
		}
	}
	
	/**
	 * Clears a row and move above blocks down to fill the empty space created
	 * @param rowIndex_ Index of the row to clear
	 */
	private void clearRow(int rowIndex_) {
		ElementaryBlock block = null;
		float y = 0.0f;
		
		//Destroy each block in the row
		for(int j = 0; j < CELL_WIDTH_NUMBER; j++) {
			block = getBlockWithCoordinates(rowIndex_, j);
			block.destroy(); //Should not be null !
			m_blockList.remove(block);
		}
		
		//Move all blocks above one step down
		for(int i = rowIndex_; i > 0; i--) {
			for(int j = 0; j < CELL_WIDTH_NUMBER; j++) {
				block = getBlockWithCoordinates(i, j);
				if(block != null) {
					
					//If we found a block, move it down one step
					y = block.getY() + CELL_SIZE;
					block.setY(y);
				}
			}
		}
	}
	
	/**
	 * Tells if a cell is occupied by a block
	 * @param row_ Row of the cell
	 * @param column_ Column of the cell
	 * @return True if the cell is occupied by a block, false otherwise
	 */
	private boolean isCellUsed(int row_, int column_) {
		return getBlockWithCoordinates(row_, column_) != null;
	}
	
	/**
	 * Method to get a block located at a row and column
	 * @param row_ Row of the searched block
	 * @param column_ Column of the searched block
	 * @return A block located a the row and column, null if does not exist
	 */
	private ElementaryBlock getBlockWithCoordinates(int row_, int column_) {
		ElementaryBlock foundBlock = null;
		
		float x = column_*CELL_SIZE;
		float y = row_*CELL_SIZE;
		
		for(ElementaryBlock b : m_blockList) {
			if(b.getX() == x && b.getY() == y) {
				foundBlock = b;
				break;
			}
		}
		
		return foundBlock;
	}
}
