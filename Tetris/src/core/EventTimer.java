package core;

/**
 * 
 * Class that executes an event after an amount of time.
 * The event can be executed more than once if the parameter is fireOnce is set to false.
 * The event to execute should be encapsulated in a class implementing the IGameEvent interface.
 * 
 * @author Jeremy
 *
 */
public class EventTimer {
	
	/**
	 * Tells if the event should be fired only once.
	 */
	private boolean m_fireOnce;
	
	/**
	 * Tells if the event was already executed
	 */
	private boolean m_alreadyFired;
	
	/**
	 * The amount of time in ms before executing the event.
	 */
	private float m_timeAmount;
	
	/**
	 * Elapsed time since the timer was started
	 */
	private float m_elapsedTime;
	
	/**
	 * Event to execute
	 */
	private IGameEvent m_event;
	
	/**
	 * Constructor
	 * @param fireOnce_ Should the event be fired only once
	 * @param timeAmount_ The amount of time in ms before executing the event
	 * @param event_ The event to execute
	 */
	public EventTimer(boolean fireOnce_, float timeAmount_, IGameEvent event_) {
		m_fireOnce = fireOnce_;
		m_timeAmount = timeAmount_;
		m_event = event_;
		m_elapsedTime = 0.0f;
		m_alreadyFired = false;
	}
	
	/**
	 * Update the timer and increase the elapsed time by delta
	 * @param delta_ Amount of time in ms since the last update
	 */
	public void update(int delta_) {
		if(!m_alreadyFired) {
			if(m_elapsedTime >= m_timeAmount) {
				
				//Time elapsed so execute
				m_event.executeEvent();
				
				if(!m_fireOnce) {
					//Reset the timer
					m_elapsedTime = 0.0f;
				} else {
					//Stop the timer
					m_alreadyFired = true;
				}
			} else {
				//Not the time yet
				m_elapsedTime += delta_;
			}
		}
	}

}
