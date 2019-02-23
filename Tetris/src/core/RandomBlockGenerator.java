package core;

import java.util.Random;

import core.block.BlockFactory;

/**
 * 
 * Class used to generate to random a block
 * 
 * @author Jeremy
 *
 */
public class RandomBlockGenerator {
	
	/**
	 * Number of block types to generate
	 */
	public static final int MAX_NUMBER_BLOCK = 7;
	
	/**
	 * L block constant
	 */
	public static final int L_BLOCK = 0;
	
	/**
	 * O block constant
	 */
	public static final int O_BLOCK = 1;
	
	/**
	 * T block constant
	 */
	public static final int T_BLOCK = 2;
	
	/**
	 * Long block constant
	 */
	public static final int BLOCK = 3;
	
	/**
	 * J block constant
	 */
	public static final int J_BLOCK = 4;
	
	/**
	 * Z block constant
	 */
	public static final int Z_BLOCK = 5;
	
	/**
	 * S block constant
	 */
	public static final int S_BLOCK = 6;
	
	/**
	 * Random generator
	 */
	private final Random m_rand = new Random();
	
	/**
	 * Static reference to the level
	 */
	private StaticLevel m_level;
	
	/**
	 * Constructor
	 * @param level_ reference to the current level
	 */
	public RandomBlockGenerator(StaticLevel level_)
	{
		m_level = level_;
	}
	
	/**
	 * @return Randomly generated block
	 */
	public IBlock getRandomBlock() {
		
		int rand = m_rand.nextInt(MAX_NUMBER_BLOCK);
		switch(rand) {
			case L_BLOCK:
				return BlockFactory.createLBlock(m_level);
				
			case O_BLOCK:
				return BlockFactory.createOBlock(m_level);
				
			case T_BLOCK:
				return BlockFactory.createTBlock(m_level);
				
			case BLOCK:
				return BlockFactory.createBlock(m_level);
				
			case J_BLOCK:
				return BlockFactory.createJBlock(m_level);
				
			case Z_BLOCK:
				return BlockFactory.createZBlock(m_level);
				
			case S_BLOCK:
				return BlockFactory.createSBlock(m_level);
				
			default:
				return null;
		}
	}
}
