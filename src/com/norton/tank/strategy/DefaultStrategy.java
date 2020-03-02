package com.norton.tank.strategy;

import com.norton.tank.Audio;
import com.norton.tank.Bullet;
import com.norton.tank.Group;
import com.norton.tank.Tank;

public class DefaultStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int bX=t.x+Tank.WIDTH/2-Bullet.WIDTH/2;
		int bY=t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
		
		new Bullet(bX,bY,t.dir,t.group);
		if(t.group==Group.GOOD)
			new Thread(()->{
				new Audio("audio/tank_fire.wav").play();
			}).start();
	}

}
