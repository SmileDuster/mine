package com.lxc.mine;

import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
class Point extends JButton {
	
	public int id = 0;
	public int state= 0;
	public boolean findflag = true;
	public boolean vflag = false;
	public boolean mark = false;
	
	public Point() {
		// TODO 自动生成的构造函数存根
	}

	public Point(Icon icon) {
		super(icon);
		// TODO 自动生成的构造函数存根
	}
}
