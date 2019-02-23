package core.block;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.StaticLevel;

class TBlock extends AbstractBlock {

	public TBlock(StaticLevel level_) {
		super(level_);
	}

	@Override
	public void init() {
		//Define material
		try {
			m_material = new Image("sprites/block_pink.png");
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
		ElementaryBlock precomputedBlocks[] = new ElementaryBlock[BLOCK_COUNTS];

		//init the array
		for(int i = 0; i < BLOCK_COUNTS; i++) {
			precomputedBlocks[i] = new ElementaryBlock(m_level, m_material, 0, 0);
		}
		
		//Compute the next position according to the angle
		//This one is too complicated to do with loops
		switch(m_angle) {
			/**
			 * #
			 * # #
			 * #
			 */
			case 0:
				precomputedBlocks[0].setX(m_xGrid);
				precomputedBlocks[0].setY(m_yGrid);

				precomputedBlocks[1].setX(m_xGrid);
				precomputedBlocks[1].setY(m_yGrid + m_level.CELL_SIZE);

				precomputedBlocks[2].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[2].setY(m_yGrid + m_level.CELL_SIZE);

				precomputedBlocks[3].setX(m_xGrid);
				precomputedBlocks[3].setY(m_yGrid + 2*m_level.CELL_SIZE);
				break;
				
			/**
			 * # # #
			 *   #
			 */
			case 1:
				precomputedBlocks[0].setX(m_xGrid);
				precomputedBlocks[0].setY(m_yGrid);

				precomputedBlocks[1].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[1].setY(m_yGrid);

				precomputedBlocks[2].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[2].setY(m_yGrid + m_level.CELL_SIZE);

				precomputedBlocks[3].setX(m_xGrid + 2*m_level.CELL_SIZE);
				precomputedBlocks[3].setY(m_yGrid);
				break;

			/**
			 *   #
			 * # #
			 *   #
			 */
			case 2:
				precomputedBlocks[0].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[0].setY(m_yGrid);

				precomputedBlocks[1].setX(m_xGrid);
				precomputedBlocks[1].setY(m_yGrid + m_level.CELL_SIZE);

				precomputedBlocks[2].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[2].setY(m_yGrid + m_level.CELL_SIZE);

				precomputedBlocks[3].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[3].setY(m_yGrid + 2*m_level.CELL_SIZE);
				break;

			/**
			 *   #
			 * # # #
			 */
			case 3:
				precomputedBlocks[0].setX(m_xGrid);
				precomputedBlocks[0].setY(m_yGrid + m_level.CELL_SIZE);

				precomputedBlocks[1].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[1].setY(m_yGrid + m_level.CELL_SIZE);

				precomputedBlocks[2].setX(m_xGrid + m_level.CELL_SIZE);
				precomputedBlocks[2].setY(m_yGrid);

				precomputedBlocks[3].setX(m_xGrid + 2*m_level.CELL_SIZE);
				precomputedBlocks[3].setY(m_yGrid + m_level.CELL_SIZE);
				break;
				
			default:
				throw new IllegalStateException("The angle can't be a number other than 0, 1, 2 or 3 and got " + m_angle);
		}
		
		return precomputedBlocks;
	}

}
