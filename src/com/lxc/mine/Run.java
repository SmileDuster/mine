package com.lxc.mine;

import javax.swing.SwingUtilities;

class Run {

	public static MyWindow mywindow;
	public static MyBoard myboard = new MyBoard();
	
	public Run() {
		// TODO 自动生成的构造函数存根
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				mywindow = new MyWindow("扫雷");
				mywindow.Initialize();
			}
		});
	}

}
