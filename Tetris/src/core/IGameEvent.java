package core;

/**
 * 
 * Interface that provides the method that can be executed by an EventTimer.
 * 
 * @author Jeremy
 *
 */
public interface IGameEvent {
	
	/**
	 * Generic method executed by an EventTimer.
	 */
	public void executeEvent();
}
