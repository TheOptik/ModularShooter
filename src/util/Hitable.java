package util;

import objects.GameObject;

public interface Hitable {
	
	public boolean hitTest(GameObject other);
	
	public void hit();
	
}
