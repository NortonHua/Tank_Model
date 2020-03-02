package com.norton.tank;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.norton.tank.cor.ColliderChain;

/**
 * 即是门面也是调停者，写成单例
 * @author Artificial Intellige
 *
 */
public class GameModel {

	private static final GameModel INSTANCE=new GameModel();
	
	static {
		INSTANCE.init();
	}
	
	public static GameModel getInstance() {
		return INSTANCE;
	}
	
	//碰撞链
	ColliderChain chain =new ColliderChain();
	
	//主战坦克
	Tank myTank;
	
	//存放游戏中的实体的List
	private List<GameObject> objects=new ArrayList<>();
	
	//构造函数私有化，以保证只有一个对象
	private GameModel() {}
	
	public void add(GameObject go) {
		this.objects.add(go);
	}
	
	public Tank getMainTank() {
		return myTank;
	}
	
	private void init() {
		//初始化主战坦克
		myTank=new Tank(200,400,Dir.DOWN,Group.GOOD);
		
		int initTankCount=Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		
		//初始化敌方坦克
		for(int i=0;i<initTankCount;i++) {
			new Tank(50+i*80,200,Dir.DOWN,Group.BAD);
		}
		
		//初始化墙
		this.add(new Wall(150,150,200,50));
		this.add(new Wall(550,150,200,50));
		this.add(new Wall(300,300,20,200));
		this.add(new Wall(550,300,50,200));
		
		//初始化森林，河流，铁墙，家等
	}
	
	//存盘功能save()
	//读盘功能load()
	
	public void paint(Graphics g) {
		//画主战坦克
		myTank.paint(g);
		
		//画游戏物体
		for(int i=0;i<objects.size();i++) {
			objects.get(i).paint(g);
		}
		
		//碰撞处理
		for(int i=0;i<objects.size();i++) {
			for(int j=i+1;j<objects.size();j++) { //Comparator.compare(o1,o2)
				GameObject o1=objects.get(i);
				GameObject o2=objects.get(j);
				
				chain.collide(o1,o2);
			}
		}
	}
	
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
}
