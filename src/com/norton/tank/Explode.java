package com.norton.tank;

import java.awt.Graphics;

public class Explode extends GameObject {
	
	public static int HEIGHT =ResourceMgr.explodes[0].getHeight();
	public static int WIDTH =ResourceMgr.explodes[0].getWidth();
	
	private int step=0;
	public Explode(int x,int y) {
		this.x=x;
		this.y=y;
		
		new Thread(()->{
			new Audio("audio/explode.wav").play();
		}).start();
		
		//添加到GameModel中
	    GameModel.getInstance().add(this);
	}
	
	

	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step >= ResourceMgr.explodes.length)
			
			//这地方要改成GameModel.getInstance.remove(this)
			GameModel.getInstance().remove(this);
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

}
