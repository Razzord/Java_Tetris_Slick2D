package core.block;

import core.IBlock;
import core.StaticLevel;

/**
 * 
 * Factory to instantiate blocks for the tetris game
 * 
 * @author Jeremy
 *
 */
public class BlockFactory {
	public static IBlock createOBlock(StaticLevel level_) {
		return new OBlock(level_);
	}
	
	public static IBlock createLBlock(StaticLevel level_) {
		return new LBlock(level_);
	}
	
	public static IBlock createTBlock(StaticLevel level_) {
		return new TBlock(level_);
	}
	
	public static IBlock createBlock(StaticLevel level_) {
		return new Block(level_);
	}
	
	public static IBlock createJBlock(StaticLevel level_) {
		return new JBlock(level_);
	}
	
	public static IBlock createZBlock(StaticLevel level_) {
		return new ZBlock(level_);
	}
	
	public static IBlock createSBlock(StaticLevel level_) {
		return new SBlock(level_);
	}
}
