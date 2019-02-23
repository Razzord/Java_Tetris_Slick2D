package core.block;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.StaticLevel;

/**
 * 
 * Square block for the tetris game
 * 
 * @author Jeremy
 *
 */
final class OBlock extends AbstractBlock{

	/**
	 * Constructor
	 * @param level_ reference to current level
	 */
	public OBlock(StaticLevel level_) {
		super(level_);
	}

	@Override
	public void init() {
		//Define material
		try {
			m_material = new Image("sprites/block_yellow.png");
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
		
		for(int i = 0; i < BLOCK_COUNTS/2; i ++) {
			x = m_xGrid + i*m_level.CELL_SIZE;
			for(int j = 0; j < BLOCK_COUNTS/2; j++) { 
				y = m_yGrid + j*m_level.CELL_SIZE;
				precomputedBlocks[i*2+j].setX(x);
				precomputedBlocks[i*2+j].setY(y);
			}
		}
		return precomputedBlocks;
	}
}
