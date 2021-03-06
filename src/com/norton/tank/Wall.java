package com.norton.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {

	public Rectangle rect;
	
	int w,h;
	
	public Wall(int x,int y,int w,int h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
		this.rect=new Rectangle(x,y,w,h);
		
		//添加墙到gameModel中
	}
	
	@Override
	public void paint(Graphics g) {
		Color c=g.getColor();

		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return w;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return h;
	}

}
