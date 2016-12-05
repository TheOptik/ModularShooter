package util;

/**
 * A GameObject implementing this interface will receive an update every game
 * tick.
 */
@FunctionalInterface
public interface Tickable {
	
	public void tick();
	
}
