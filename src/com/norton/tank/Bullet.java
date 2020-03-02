package com.norton.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{

	public static int HEIGHT=ResourceMgr.bulletD.getHeight();
	public static int WIDTH=ResourceMgr.bulletD.getWidth();
	public static final int SPEED=6;
	
	private Dir dir;
	public Group group=Group.BAD;
	private boolean living=true;
	public Rectangle rect=new Rectangle();
	public Bullet(int x,int y,Dir dir, Group group) {
		this.dir = dir;
		this.group = group;
		this.x=x;
		this.y=y;
		
		rect.x=this.x;
		rect.y=this.y;
		rect.width=WIDTH;
		rect.height=HEIGHT;
		
		//被创建时要添加到GameModel中
		GameModel.getInstance().add(this);
		
	}
	
	public void die() {
		this.living=false;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group=group;
	}

	@Override
	public void paint(Graphics g) {
		if(!living) {
			//这地方要改成GameModel.getInstance.remove(this)
			return;
		}
		
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
			
		}
		
		move();
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}
	
	private void move() {
		switch(dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		
		//update rect
		rect.x=this.x;
		rect.y=this.y;
		
		if(x<0 || y<0 || x>Integer.parseInt((String) PropertyMgr.get("gameWidth"))
				|| y>Integer.parseInt((String) PropertyMgr.get("gameHeight"))) {
			this.living=false;
		}
	}
	
}
