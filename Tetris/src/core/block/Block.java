package core.block;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.StaticLevel;

class Block extends AbstractBlock {

	public Block(StaticLevel level_) {
		super(level_);
	}

	@Override
	public void init() {
		//Define material
		try {
			m_material = new Image("sprites/block_cyan.png");
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
			 * #
			 */
			case 0:
				x = m_xGrid;
				for(int i = 0; i < BLOCK_COUNTS; i++) {
					y = m_yGrid + i*m_level.CELL_SIZE;
					precomputedBlocks[i].setX(x);
					precomputedBlocks[i].setY(y);
				}
				break;
				
			/**
			 * # # # #
			 */
			case 1:
				y = m_yGrid;
				for(int i = 0; i < BLOCK_COUNTS; i++) {
					x = m_xGrid + i*m_level.CELL_SIZE;
					precomputedBlocks[i].setX(x);
					precomputedBlocks[i].setY(y);
				}
				break;

			/**
			 * #
			 * #
			 * #
			 * #
			 */
			case 2:
				x = m_xGrid;
				for(int i = 0; i < BLOCK_COUNTS; i++) {
					y = m_yGrid + i*m_level.CELL_SIZE;
					precomputedBlocks[i].setX(x);
					precomputedBlocks[i].setY(y);
				}
				break;

			/**
			 * # # # #
			 */
			case 3:
				y = m_yGrid;
				for(int i = 0; i < BLOCK_COUNTS; i++) {
					x = m_xGrid + i*m_level.CELL_SIZE;
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
