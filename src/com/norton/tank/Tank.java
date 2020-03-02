package com.norton.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.norton.tank.strategy.DefaultStrategy;
import com.norton.tank.strategy.FireStrategy;

public class Tank extends GameObject {

	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();

	private static final int SPEED = 2;

	public Dir dir = Dir.DOWN;

	// private List<TankFireObserver> fireObservers=Arrays.asList(new
	// TankFireHandler());

	FireStrategy fs;

	public Group group = Group.BAD;

	private boolean living = true;

	private boolean moving = true;

	int oldX, oldY;
	private Random random = new Random();

	public Rectangle rect = new Rectangle();

	public Tank(int x, int y, Dir dir, Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;

		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;

		if (group == Group.GOOD) {
			String goodFSName = (String) PropertyMgr.get("goodFS");
			try {
				// 主战坦克，开火策略
				fs=(FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 敌方坦克开火策略指定为默认策略
			fs=new DefaultStrategy();
		}

		// 将坦克添加到GameModel中
		GameModel.getInstance().add(this);
	}

	public void back() {
		x = oldX;
		y = oldY;
	}

	private void boundsCheck() {
		if (this.x < 2)
			x = 2;
		if (this.y < 28)
			y = 28;
		if (this.x > Integer.parseInt((String) PropertyMgr.get("gameWidth")) - this.WIDTH - 2)
			x = Integer.parseInt((String) PropertyMgr.get("gameWidth")) - this.WIDTH - 2;
		if (this.y > Integer.parseInt((String) PropertyMgr.get("gameHeight")) - this.HEIGHT - 2)
			y = Integer.parseInt((String) PropertyMgr.get("gameHeight")) - this.HEIGHT - 2;
	}

	public void die() {
		this.living = false;
	}

	public void fire() {
		// 开火
		fs.fire(this);
	}

	public Dir getDir() {
		return dir;
	}

	public Group getGroup() {
		return group;
	}

	@Override
	public void paint(Graphics g) {
		if(!living)
			//如果死了就从game model中移除
			GameModel.getInstance().remove(this);
			
		switch (dir) {
		case LEFT:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
			break;
		}

		move();

	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	public Rectangle getRect() {
		return rect;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// 观察者模式
	/*
	 * public void handleFireKey() { TankFireEvent event=new TankFireEvent(this);
	 * for(TankFireObserver o: fireObservers) { o.actionOnFire(event); } }
	 */

	public boolean isMoving() {
		return moving;
	}

	private void move() {
		// 记录移动之前的位置
		oldX = x;
		oldY = y;

		if (!moving)
			return;

		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}

		if (this.group == Group.BAD && random.nextInt(100) > 95)
			this.fire();

		if (this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();

		boundsCheck();
		// update rect
		rect.x = this.x;
		rect.y = this.y;

	}

	private void randomDir() {

		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void stop() {
		moving = false;
	}
}
