package com.lxc.mine;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class MyWindow extends JFrame implements ActionListener{

	public MyWindow(String title) throws HeadlessException {
		super(title);
		// TODO 自动生成的构造函数存根
	}
	

	public void Initialize() {
		this.setSize(380, 430);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		Run.mywindow.add(Run.myboard);
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu jmstart = new JMenu("开始(B)");
		jmstart.setMnemonic(KeyEvent.VK_B);
		JMenuItem jmilow = new JMenuItem("初级");
		JMenuItem jmimedium = new JMenuItem("中级");
		JMenuItem jmihigh = new JMenuItem("高级");
		JMenuItem jmiown = new JMenuItem("噩梦");
		jmilow.setActionCommand("101");
		jmimedium.setActionCommand("102");
		jmihigh.setActionCommand("103");
		jmiown.setActionCommand("104");
		jmilow.addActionListener(this);
		jmimedium.addActionListener(this);
		jmihigh.addActionListener(this);
		jmiown.addActionListener(this);
		jmstart.add(jmilow);
		jmstart.add(jmimedium);
		jmstart.add(jmihigh);
		jmstart.add(jmiown);
		jmb.add(jmstart);
		
		JMenu jmgame = new JMenu("游戏(P)");
		jmgame.setMnemonic(KeyEvent.VK_P);
		JMenuItem jmisetting = new JMenuItem("设置");
		JMenuItem jmistatistics = new JMenuItem("统计数据");
		jmisetting.setActionCommand("201");
		jmistatistics.setActionCommand("202");
		jmisetting.addActionListener(this);
		jmistatistics.addActionListener(this);
		jmgame.add(jmisetting);
		jmgame.add(jmistatistics);
		jmb.add(jmgame);
		
		JMenu jmothers = new JMenu("其他(O)");
		jmothers.setMnemonic(KeyEvent.VK_O);
		JMenuItem jmiabout = new JMenuItem("关于");
		jmiabout.setActionCommand("301");
		jmiabout.addActionListener(this);
		jmothers.add(jmiabout);
		jmb.add(jmothers);
		
		this.setJMenuBar(jmb);

		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		switch(Integer.parseInt(e.getActionCommand())) {
		case 101:
			Run.myboard.create(1);
			break;
		case 102:
			Run.myboard.create(2);
			break;
		case 103:
			Run.myboard.create(3);
			break;
		case 104:
			Run.myboard.create(4);
			break;
		case 201:
			break;
		case 202:
			break;
		case 301:
			Run.myboard.about();
			break;
		default:
			break;
		}
	}
}
