package core.block;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.StaticLevel;

class LBlock extends AbstractBlock{

	public LBlock(StaticLevel level_) {
		super(level_);
	}

	@Override
	public void init() {
		//Define material
		try {
			m_material = new Image("sprites/block_orange.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		//Setup elementary blocks
		m_blocks = new ElementaryBlock[BLOCK_COUNTS];
		for(int i = 0; i < BLOCK_COUNTS; i++) {
			m_blocks[i] = new ElementaryBlock(m_level, m_material, 0, 0);
		}
		updateElementaryBlocksPosition();
	}

	@Override
	protected ElementaryBlock[] getNextPosition() {
		float x = 0.0f;
		float y = 0.0f;
		ElementaryBlock precomputedBlocks[] = new ElementaryBlock[BLOCK_COUNTS];

		//init the array
		for(int i = 0; i < BLOCK_COUNTS; i++) {
			precomputedBlocks[i] = new ElementaryBlock(m_level, m_material, 0, 0);
		}
		
		//Compute the next position according to the angle
		switch(m_angle) {
			/**
			 * #
			 * #
			 * #
			 * # #
			 */
			case 0:
				for(int i = 0; i < BLOCK_COUNTS-1; i++) {
					x = m_xGrid;
					y = m_yGrid + i*m_level.CELL_SIZE;
					precomputedBlocks[i].setX(x);
					precomputedBlocks[i].setY(y);
				}
				
				x = m_xGrid + m_level.CELL_SIZE;
				y = m_yGrid + (BLOCK_COUNTS-2)*m_level.CELL_SIZE;
				precomputedBlocks[BLOCK_COUNTS-1].setX(x);
				precomputedBlocks[BLOCK_COUNTS-1].setY(y);
				break;
				
			/**
			 * 
			 * # # # #
			 * #
			 */
			case 1:
				for(int i = 0; i < BLOCK_COUNTS-1; i++) {
					x = m_xGrid + i*m_level.CELL_SIZE;
					y = m_yGrid + m_level.CELL_SIZE;
					precomputedBlocks[i].setX(x);
					precomputedBlocks[i].setY(y);
				}
				
				x = m_xGrid;
				y = m_yGrid + 2*m_level.CELL_SIZE;
				precomputedBlocks[BLOCK_COUNTS-1].setX(x);
				precomputedBlocks[BLOCK_COUNTS-1].setY(y);
				break;

			/**
			 * # #
			 *   #
			 *   #
			 *   #
			 */
			case 2:
				x = m_xGrid;
				y = m_yGrid;
				precomputedBlocks[0].setX(x);
				precomputedBlocks[0].setY(y);
				
				for(int i = 1; i < BLOCK_COUNTS; i++) {
					x = m_xGrid + m_level.CELL_SIZE;
					y = m_yGrid + (i-1)*m_level.CELL_SIZE;
					precomputedBlocks[i].setX(x);
					precomputedBlocks[i].setY(y);
				}
				break;

			/**
			 * 
			 *       #
			 * # # # #
			 */
			case 3:
				x = m_xGrid + (BLOCK_COUNTS-2)*m_level.CELL_SIZE;
				y = m_yGrid + m_level.CELL_SIZE;
				precomputedBlocks[0].setX(x);
				precomputedBlocks[0].setY(y);
				
				for(int i = 1; i < BLOCK_COUNTS; i++) {
					x = m_xGrid + (i-1)*m_level.CELL_SIZE;
					y = m_yGrid + 2*m_level.CELL_SIZE;
					precomputedBlocks[i].setX(x);
					precomputedBlocks[i].setY(y);
				}
				break;
				
			default:
				throw new IllegalStateException("The angle can't be a number other than 0, 1, 2 or 3 and got " + m_angle);
		}
		
		return precomputedBlocks;
	}
}
