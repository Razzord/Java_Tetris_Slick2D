package core.block;

import org.newdawn.slick.Image;

import core.Direction;
import core.IBlock;
import core.Rotation;
import core.StaticLevel;

/**
 * 
 * Abstract class for blocks to put all the attributes in the same class
 * 
 * @author Jeremy
 *
 */
abstract class  AbstractBlock implements IBlock{
	
	/**
	 * The number of blocks
	 */
	protected static final int BLOCK_COUNTS = 4;

	/**
	 * Reference to the level that is a grid where the blocks are drawn
	 */
	protected StaticLevel m_level;
	
	/**
	 * Material to represent the block
	 */
	protected Image m_material;
	
	/**
	 * List of blocks that constitute that main block
	 */
	protected ElementaryBlock[] m_blocks;
	
	/**
	 * Defines how many elementary blocks are needed
	 */
	protected int m_blockCount;
	
	/**
	 * X position of the block in the grid referential
	 */
	protected int m_xGrid;
	
	/**
	 * X position
	 */
	protected float m_x;
	
	/**
	 * Y position of the block in the grid referential
	 */
	protected int m_yGrid;
	
	/**
	 * Y position
	 */
	protected float m_y;
	
	/**
	 * Tells whether or not the block is snapped on the level
	 */
	protected boolean m_isSnapped;
	
	/**
	 * Angle of the block. It is an integer that must be between 0 and 3 included.
	 */
	protected int m_angle;
	
	/**
	 * Constructor
	 * @param level_ reference to current level
	 */
	public AbstractBlock(StaticLevel level_) {
		m_level = level_;
		m_isSnapped = false;
		m_angle = 0;
		init();
	}

	@Override
	public abstract void init();
	
	/**
	 * Method to precompute the next position of the block as an array.
	 * It is especially useful to determine in advance what the effect of 
	 * the angle will be.
	 * It does not compute the next step according to the speed however.
	 * @return
	 */
	protected abstract ElementaryBlock[] getNextPosition();
	
	/**
	 * @return list of elementary blocks
	 */
	public ElementaryBlock[] getBlocks() {
		return m_blocks;
	}
	
	/**
	 * Change the current blocks by specified ones
	 * @param new_ New blocks to replace current ones
	 */
	protected void setBlocks(ElementaryBlock[] new_) {
		if(new_.length == m_blocks.length) {
			for(int i = 0; i < m_blocks.length; i++) {
				m_blocks[i] = new_[i];
			}
		} else {
			//Nothing to do
		}
	}

	/**
	 * Update the position of each elementary blocks according to
	 * the global blocks coordinates. The angle attribute is taken into
	 * account to define the rotated position in which the current block is.
	 * Each position is predefined
	 */
	protected void updateElementaryBlocksPosition() {
		ElementaryBlock[] nextPosition = getNextPosition();
		boolean isColliding = false;
		
		for(ElementaryBlock b : nextPosition) {
			if(b.isColliding()) {
				isColliding = true;
			}
		}
		
		if(!isColliding) {
			this.setBlocks(nextPosition);
		}
	}
	
	/**
	 * Tell if when the block moves to another cell, it will collide with another block
	 * when moving down
	 * @return
	 */
	private boolean willSnap(int delta_, float speed_) {
		m_y += speed_*delta_;
		
		if(willCollide()) {
			m_y -= speed_*delta_;
			return true;
		} else {
			m_y -= speed_*delta_;
			return false;
		}
	}
	
	/**
	 * @return true if the next computed position is either colliding with another block or out of bound
	 */
	private boolean willCollide() {
		ElementaryBlock[] nextPosition = getNextPosition();
		boolean isColliding = false;
		
		for(ElementaryBlock b : nextPosition) {
			if(b.isColliding()) {
				isColliding = true;
			}
		}
		return isColliding;
	}
	
	/**
	 * When a block hits another block of the level, it should be snap on the level.
	 * This means that its speed is set to 0 and it is added to the level's snapped blocks
	 */
	private void snap() {
		m_isSnapped = true;
		for(ElementaryBlock b : m_blocks) {
			m_level.snap(b);
		}
	}

	@Override
	public void render() {
		for(ElementaryBlock b : m_blocks) {
			b.render();
		}
	}

	@Override
	public void update(int delta_, float speed_) {
		
		if(isSnapped())
			return;
		
		//Move down
		if(!willSnap(delta_, speed_)) {
			m_y += speed_*delta_;

			//Set grid coordinates
			m_yGrid = (int)(m_y / m_level.CELL_SIZE) * m_level.CELL_SIZE;
		
			updateElementaryBlocksPosition();
		} else if(!isSnapped()){
			//Collision with the level means snap the block on the level
			snap();
		}
	}
	
	@Override
	public boolean isSnapped() {
		return m_isSnapped;
	}
	
	@Override
	public void move(Direction direction_) {
		if(m_isSnapped) {
			return;
		}
		
		if(direction_ == Direction.RIGHT) {
			m_xGrid += m_level.CELL_SIZE;
			
			if(willCollide()) {
				//Revert
				m_xGrid -= m_level.CELL_SIZE;
			}
		}
		
		if(direction_ == Direction.LEFT) {
			m_xGrid -= m_level.CELL_SIZE;
			
			if(willCollide()) {
				//Revert
				m_xGrid += m_level.CELL_SIZE;
			}
		}
	}
	
	@Override
	public void rotate(Rotation rotation_) {
		if(m_isSnapped) {
			return;
		}
		
		if(rotation_ == Rotation.CLOCKWISE) {
			m_angle = (m_angle + 1) % 4;
			
			if(willCollide()) {
				//Revert
				m_angle = m_angle - 1;
				
				//Correction
				if(m_angle == -1) {
					m_angle = 3;
				}
			}
		}
		
		if(rotation_ == Rotation.ANTICLOCKWISE) {
			m_angle = m_angle - 1;
			
			//Correction
			if(m_angle == -1) {
				m_angle = 3;
			}
			
			if(willCollide()) {
				//Revert
				m_angle = (m_angle + 1) % 4;
			}
		}
	}
	
	@Override
	public void zap(int delta_, float speed_) {
		while(!willSnap(delta_, speed_)) {
			m_yGrid += m_level.CELL_SIZE;
		
			updateElementaryBlocksPosition();
		}
	}
	
	@Override
	public void moveToSaved() {
		m_x = m_level.getSavedX();
		m_y = m_level.getSavedY();
		m_xGrid = (int)(m_x / m_level.CELL_SIZE) * m_level.CELL_SIZE;
		m_yGrid = (int)(m_y / m_level.CELL_SIZE) * m_level.CELL_SIZE;
		forceElementaryBlocksPosition();
	}
	
	@Override
	public void moveToSpawn() {
		m_x = m_level.getSpawnX();
		m_y = m_level.getSpawnY();
		m_xGrid = (int)(m_x / m_level.CELL_SIZE) * m_level.CELL_SIZE;
		m_yGrid = (int)(m_y / m_level.CELL_SIZE) * m_level.CELL_SIZE;
		forceElementaryBlocksPosition();
	}
	
	@Override
	public void moveToNext() {
		m_x = m_level.getNextX();
		m_y = m_level.getNextY();
		m_xGrid = (int)(m_x / m_level.CELL_SIZE) * m_level.CELL_SIZE;
		m_yGrid = (int)(m_y / m_level.CELL_SIZE) * m_level.CELL_SIZE;
		forceElementaryBlocksPosition();
	}
	
	/**
	 * Moves the elementary blocks according to the master block coordinates not caring about collisions
	 */
	private void forceElementaryBlocksPosition() {
		ElementaryBlock[] nextPosition = getNextPosition();
		this.setBlocks(nextPosition);
	}
}