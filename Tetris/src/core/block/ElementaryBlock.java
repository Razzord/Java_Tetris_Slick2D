package core.block;

import org.newdawn.slick.Image;

import core.StaticLevel;

/**
 * This class represent small individual blocks
 * @author Jeremy
 *
 */
public class ElementaryBlock {
	
	/**
	 * Reference on the level
	 */
	private StaticLevel m_level;
	
	/**
	 * Image to represent the block
	 */
	private Image m_material;
	
	/**
	 * X coordinate in the screen
	 */
	private float m_x;
	
	/**
	 * Y coordinate in the screen
	 */
	private float m_y;
	

	/**
	 * Constructor
	 * @param level_ Reference to the level
	 * @param material_ material to use when we draw this block
	 * @param x_ x initial coordinate
	 * @param y_ y initial coordinate
	 */
	public ElementaryBlock(StaticLevel level_, Image material_, float x_, float y_) {
		m_level = level_;
		m_material = material_;
		m_x = x_;
		m_y = y_;
	}
	
	/**
	 * The elementary block collides if and only if it will be at 
	 * the same position of a block at the next step. Since the 
	 * elementary blocks coordinates are always perfectly aligned on the 
	 * grid, it is easy to determine where it will be next step when moving down
	 * @return True if no blocks under of if it is not going out
	 */
	public boolean willSnap() {
		boolean collides = false;
		if(m_y == (m_level.CELL_HEIGHT_NUMBER-1)*m_level.CELL_SIZE) {
			//Out of bound
			collides = true;
		} else {
			//Checking collision with other blocks
			for(ElementaryBlock b : m_level.getBlocks()) {
				if(m_y == b.m_y - m_level.CELL_SIZE && m_x == b.m_x - m_level.CELL_SIZE) {
					collides = true;
					break;
				}
			}
		}
		return collides;
	}
	
	
	/**
	 * @return True if the block is either at the same position of another block or
	 * out of bound
	 */
	public boolean isColliding() {
		boolean isColliding = false;
		
		//Test the out of bound (only on the x axis since this is the only ddl available)
		if(m_x <= -m_level.CELL_SIZE || m_x >= m_level.CELL_WIDTH_NUMBER*m_level.CELL_SIZE) {
			isColliding = true;
		} else if(m_y >= m_level.CELL_HEIGHT_NUMBER*m_level.CELL_SIZE) {
			isColliding = true;
		} else {
			//Check if collides with another block
			for(ElementaryBlock b : m_level.getBlocks()) {
				if(m_x == b.m_x && m_y == b.m_y) {
					isColliding = true;
					break;
				}
			}
		}
		
		return isColliding;
	}
	
	/**
	 * Draw the block at its coordinates
	 */
	public void render() {
		if(m_material != null) {
			m_material.draw(m_x, m_y, m_level.CELL_SIZE, m_level.CELL_SIZE);
		}
	}
	
	/**
	 * Destroys the elementary block by freeing memory and stop rendering it
	 */
	public void destroy() {
		m_material = null;
	}
	
	/**
	 * Change the X coordinate of the block
	 * @param x_
	 */
	public void setX(float x_) {
		m_x = x_;
	}
	
	/**
	 * Change the Y coordinate of the block
	 * @param y_
	 */
	public void setY(float y_) {
		m_y = y_;
	}
	
	/**
	 * X Getter
	 * @return
	 */
	public float getX() {
		return m_x;
	}
	
	/**
	 * Y Getter
	 * @return
	 */
	public float getY() {
		return m_y;
	}
}
