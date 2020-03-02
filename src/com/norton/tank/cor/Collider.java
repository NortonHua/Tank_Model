package com.norton.tank.cor;

import com.norton.tank.GameObject;

public interface Collider {

	boolean collide(GameObject o1,GameObject o2);
}
