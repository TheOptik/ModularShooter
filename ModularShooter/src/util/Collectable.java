package util;

import modules.Module;
import objects.GameObject;

public interface Collectable {
	
	Module collect();
	
	boolean hitTest(GameObject other);
	
}
