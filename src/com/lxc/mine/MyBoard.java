package com.lxc.mine;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyBoard extends JPanel implements MouseListener{

	public Point point[][];
	private int pointx,pointy,pointmine;
	private int playermine;
	private int safeblock;
	
	public MyBoard() {
		// TODO 自动生成的构造函数存根
		
	}
	
	public void about() {
		clear();
		this.setLayout(new GridLayout(4,1,0,0));
		JLabel jl1 = new JLabel("制作：梁笑尘",0);
		JLabel jl2 = new JLabel("IDE:eclipse",0);
		JLabel jl3 = new JLabel("版本：0.1",0);
		JLabel jl4 = new JLabel("2018.12.1",0);
		jl1.setFont(new Font(null , 1 , 50));
		jl2.setFont(new Font(null , 1 , 50));
		jl3.setFont(new Font(null , 1 , 50));
		jl4.setFont(new Font(null , 1 , 50));

		
		
		this.add(jl1);
		this.add(jl2);
		this.add(jl3);
		this.add(jl4);
	}
	
	
	public void create(int x,int y,int number) {
		clear();
		pointx = x;
		pointy = y;
		pointmine = number;
		this.setLayout(new GridLayout(x, y, 0, 0));
		point = new Point[x][y];
		for(int i = 0; i < x; i++) {
			for(int o = 0; o < y; o++) {
				point[i][o] = new Point();
				point[i][o].id = i*100+o;
				point[i][o].addMouseListener(this);
				point[i][o].setFont(new Font(null , 1 , 20));
				this.add(point[i][o]);
			}
		}
		setmine(x,y,number);
		settips(x,y);
		safeblock = x * y - number;
		playermine = number;
		this.revalidate();
		Run.mywindow.setVisible(true);
	}
	public void create(int mode) {
		switch(mode) {
		case 1:
			create(9,9,10);
			Run.mywindow.setSize(380, 430);
			Run.mywindow.setLocationRelativeTo(null);
			break;
		case 2:
			create(16,16,40);
			Run.mywindow.setSize(580, 630);
			Run.mywindow.setLocationRelativeTo(null);
			break;
		case 3:
			create(16,30,99);
			Run.mywindow.setSize(1075, 630);
			Run.mywindow.setLocationRelativeTo(null);
			break;
		case 4:
			create(20,30,125);
			Run.mywindow.setSize(1075, 800);
			Run.mywindow.setLocationRelativeTo(null);
			break;
		}
	}
	
	public void clear() {
		this.removeAll();
		this.revalidate();
	}
	
	public void setmine(int x,int y,int number) {
		playermine = number;
		Random rx = new Random(System.currentTimeMillis());
		Random ry = new Random(System.currentTimeMillis()+1);
		int ranx = rx.nextInt(x) , rany = ry.nextInt(y);
		for(int i = 1; i <= number; i++) {
			while(true) {
				if(point[ranx][rany].state == 0) {
					point[ranx][rany].state = -1;
//					point[ranx][rany].setBackground(new Color(255,0,0));
					break;
				}
				ranx = rx.nextInt(x);
				rany = ry.nextInt(y);
			}
		}
	}
	
	public void settips(int x,int y) {
		for(int i = 0; i < x; i++) {
			for(int o = 0; o < y; o++) {
				if(point[i][o].state != -1) {
					int number = 0;
					if(i > 0) {
						if(point[i-1][o].state == -1) {number++;}
						if(o > 0) {
							if(point[i-1][o-1].state == -1) {number++;}
						}
						if(o < y - 1) {
							if(point[i-1][o+1].state == -1) {number++;}
						}
					}
					if(i < x - 1) {
						if(point[i+1][o].state == -1) {number++;}
						if(o > 0) {
							if(point[i+1][o-1].state == -1) {number++;}
						}
						if(o < y - 1) {
							if(point[i+1][o+1].state == -1) {number++;}
						}
					}
					if(o > 0) {
						if(point[i][o-1].state == -1) {number++;}
					}
					if(o < y - 1) {
						if(point[i][o+1].state == -1) {number++;}
					}
					point[i][o].state = number;
				}
			}
		}
	}
	
	public void win() {
		this.removeAll();
		this.setLayout(new GridLayout(2,1,0,0));
		JLabel jlwin = new JLabel(new ImageIcon("win.png"));
		jlwin.setText("成功");
		jlwin.setFont(new Font(null, 1, 75));
		jlwin.setForeground(new Color(41,247,12));
		
		JPanel jpbottom = new JPanel();
		jpbottom.setLayout(new GridLayout(3,2,20,20));
		jpbottom.add(new JPanel());
		jpbottom.add(new JPanel());
		
		
		JButton jbrestart = new JButton("再来一次");
		JButton jbnext = new JButton("接下来...");
		jbrestart.setFont(new Font(null, 1, 30));
		jbnext.setFont(new Font(null, 1, 30));
		jpbottom.add(jbrestart);
		jpbottom.add(jbnext);
		
		
		this.add(jlwin);
		this.add(jpbottom);
		this.revalidate();
		
		jbrestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				create(pointx,pointy,pointmine);
			}
		});
	}
	public void fail() {
		this.removeAll();
		this.setLayout(new GridLayout(2,1,0,0));
		
		JLabel jlfail = new JLabel(new ImageIcon("fail.png"));
		jlfail.setText("失败");
		jlfail.setFont(new Font(null, 1, 75));
		jlfail.setForeground(new Color(247,41,12));
		
		JPanel jpbottom = new JPanel();
		jpbottom.setLayout(new GridLayout(3,2,20,20));
		jpbottom.add(new JPanel());
		jpbottom.add(new JPanel());
		
		
		JButton jbrestart = new JButton("再来一次");
		JButton jbnext = new JButton("接下来...");
		jbrestart.setFont(new Font(null, 1, 30));
		jbnext.setFont(new Font(null, 1, 30));
		jpbottom.add(jbrestart);
		jpbottom.add(jbnext);
		
		this.add(jlfail);
		this.add(jpbottom);
		
		this.revalidate();
		
		jbrestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				create(pointx,pointy,pointmine);
			}
		});
	}

	
	public void find(int x,int y) {	
		if(point[x][y].findflag) {
			point[x][y].findflag = false;
			if(point[x][y].state == 0) {
				point[x][y].setEnabled(false);
				if(!point[x][y].vflag){safeblock--;}
				point[x][y].vflag = true;
				if(x > 0) {
					find(x-1,y);
					if(y > 0) {find(x-1,y-1);}
					if(y < pointy - 1) {find(x-1,y+1);}
				}
				if(x < pointx - 1) {
					find(x+1,y);
					if(y > 0) {find(x+1,y-1);}
					if(y < pointy - 1) {find(x+1,y+1);}
				}
				if(y > 0) {find(x,y-1);}
				if(y < pointy - 1) {find(x,y+1);}
			}
			if(point[x][y].state > 0) {
				ImageIcon iinum = new ImageIcon(point[x][y].state+".png");
				iinum.setImage(iinum.getImage().getScaledInstance(
						point[0][0].getWidth(), point[0][0].getHeight(),
						Image.SCALE_DEFAULT));
				point[x][y].setIcon(iinum);
				if(!point[x][y].vflag){safeblock--;}
				point[x][y].vflag = true;
			}
		}
		else {
			return;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		Point targetp = (Point) e.getSource();
		switch(e.getButton()) {
		case MouseEvent.BUTTON1:
			switch(e.getClickCount()) {
			case 1:
				if(!targetp.mark && !targetp.vflag) {
					int command = targetp.id;
					int x = command / 100;
					int y = command % 100;
					if(targetp.state == -1) {
						fail();
					}
					else if(targetp.state == 0){
						find(x,y);
						wincheck();
						for(Point p[] : point) {
							for(Point pp : p) {
								pp.findflag = true;
							}
						}
					}
					else {
						ImageIcon iinum = new ImageIcon(point[x][y].state+".png");
						iinum.setImage(iinum.getImage().getScaledInstance(
								point[0][0].getWidth(), point[0][0].getHeight(),
								Image.SCALE_DEFAULT));
						point[x][y].setIcon(iinum);
						if(!point[x][y].vflag){safeblock--;}
						point[x][y].vflag = true;
						wincheck();
					}
					break;
				}
				break;
			case 2:
				if(targetp.state == -1 && !targetp.mark) {
					fail();
				}
				else if(targetp.state > 0 && targetp.vflag) {
					int i = targetp.id / 100;
					int o = targetp.id % 100;
					int number = 0;
					if(i > 0) {
						if(point[i-1][o].mark) {number++;}
						if(o > 0) {
							if(point[i-1][o-1].mark) {number++;}
						}
						if(o < pointy - 1) {
							if(point[i-1][o+1].mark) {number++;}
						}
					}
					if(i < pointx - 1) {
						if(point[i+1][o].mark) {number++;}
						if(o > 0) {
							if(point[i+1][o-1].mark) {number++;}
						}
						if(o < pointy - 1) {
							if(point[i+1][o+1].mark) {number++;}
						}
					}
					if(o > 0) {
						if(point[i][o-1].mark) {number++;}
					}
					if(o < pointy - 1) {
						if(point[i][o+1].mark) {number++;}
					}
					if(number == targetp.state) {
						pointit(targetp.id);
					}
				}
				break;
			}
			break;
		case MouseEvent.BUTTON3:
			if(!targetp.vflag) {
				if(targetp.mark) {
					targetp.mark = false;
					targetp.setIcon(null);
					playermine++;
				}
				else {
					targetp.mark = true;
					ImageIcon iimark = new ImageIcon("mark.png");
					iimark.setImage(iimark.getImage().getScaledInstance(
							point[0][0].getWidth(), point[0][0].getHeight(),
							Image.SCALE_DEFAULT));
					targetp.setIcon(iimark);
					playermine--;
				}
			}
			break;
		}
	}
	private void pointit(int id) {
		int x = id / 100;
		int y = id % 100;
		if(x > 0) {
			switch(point[x-1][y].state) {
			case 0:
				if(!point[x-1][y].vflag) {
					find(x-1,y);
					wincheck();
					for(Point p[] : point) {
						for(Point pp : p) {
							pp.findflag = true;
						}
					}
				}
				break;
			case -1:
				if(!point[x-1][y].mark) {
					fail();
				}
				break;
			default:
				ImageIcon iinum = new ImageIcon(point[x-1][y].state+".png");
				iinum.setImage(iinum.getImage().getScaledInstance(
						point[0][0].getWidth(), point[0][0].getHeight(),
						Image.SCALE_DEFAULT));
				point[x-1][y].setIcon(iinum);				
				if(!point[x-1][y].vflag){safeblock--;}
				point[x-1][y].vflag = true;
				wincheck();
				break;
			}
			if(y > 0) {
				switch(point[x-1][y-1].state) {
				case 0:
					if(!point[x-1][y-1].vflag) {
						find(x-1,y-1);
						wincheck();
						for(Point p[] : point) {
							for(Point pp : p) {
								pp.findflag = true;
							}
						}
					}
					break;
				case -1:
					if(!point[x-1][y-1].mark) {
						fail();
					}
					break;
				default:
					ImageIcon iinum = new ImageIcon(point[x-1][y-1].state+".png");
					iinum.setImage(iinum.getImage().getScaledInstance(
							point[0][0].getWidth(), point[0][0].getHeight(),
							Image.SCALE_DEFAULT));
					point[x-1][y-1].setIcon(iinum);
					if(!point[x-1][y-1].vflag){safeblock--;}
					point[x-1][y-1].vflag = true;
					wincheck();
					break;
				}
			}
			if(y < pointy - 1) {
				switch(point[x-1][y+1].state) {
				case 0:
					if(!point[x-1][y+1].vflag) {
						find(x-1,y+1);
						wincheck();
						for(Point p[] : point) {
							for(Point pp : p) {
								pp.findflag = true;
							}
						}
					}
					break;
				case -1:
					if(!point[x-1][y+1].mark) {
						fail();
					}
					break;
				default:
					ImageIcon iinum = new ImageIcon(point[x-1][y+1].state+".png");
					iinum.setImage(iinum.getImage().getScaledInstance(
							point[0][0].getWidth(), point[0][0].getHeight(),
							Image.SCALE_DEFAULT));
					point[x-1][y+1].setIcon(iinum);
					if(!point[x-1][y+1].vflag){safeblock--;}
					point[x-1][y+1].vflag = true;
					wincheck();
					break;
				}
			}
		}
		if(x < pointx - 1) {
			switch(point[x+1][y].state) {
			case 0:
				if(!point[x+1][y].vflag) {
					find(x+1,y);
					wincheck();
					for(Point p[] : point) {
						for(Point pp : p) {
							pp.findflag = true;
						}
					}
				}
				break;
			case -1:
				if(!point[x+1][y].mark) {
					fail();
				}
				break;
			default:
				ImageIcon iinum = new ImageIcon(point[x+1][y].state+".png");
				iinum.setImage(iinum.getImage().getScaledInstance(
						point[0][0].getWidth(), point[0][0].getHeight(),
						Image.SCALE_DEFAULT));
				point[x+1][y].setIcon(iinum);
				if(!point[x+1][y].vflag){safeblock--;}
				point[x+1][y].vflag = true;
				wincheck();
				break;
			}
			if(y > 0) {
				switch(point[x+1][y-1].state) {
				case 0:
					if(!point[x+1][y-1].vflag) {
						find(x+1,y-1);
						wincheck();
						for(Point p[] : point) {
							for(Point pp : p) {
								pp.findflag = true;
							}
						}
					}
					break;
				case -1:
					if(!point[x+1][y-1].mark) {
						fail();
					}
					break;
				default:
					ImageIcon iinum = new ImageIcon(point[x+1][y-1].state+".png");
					iinum.setImage(iinum.getImage().getScaledInstance(
							point[0][0].getWidth(), point[0][0].getHeight(),
							Image.SCALE_DEFAULT));
					point[x+1][y-1].setIcon(iinum);
					if(!point[x+1][y-1].vflag){safeblock--;}
					point[x+1][y-1].vflag = true;
					wincheck();
					break;
				}
			}
			if(y < pointy - 1) {
				switch(point[x+1][y+1].state) {
				case 0:
					if(!point[x+1][y+1].vflag) {
						find(x+1,y+1);
						wincheck();
						for(Point p[] : point) {
							for(Point pp : p) {
								pp.findflag = true;
							}
						}
					}
					break;
				case -1:
					if(!point[x+1][y+1].mark) {
						fail();
					}
					break;
				default:
					ImageIcon iinum = new ImageIcon(point[x+1][y+1].state+".png");
					iinum.setImage(iinum.getImage().getScaledInstance(
							point[0][0].getWidth(), point[0][0].getHeight(),
							Image.SCALE_DEFAULT));
					point[x+1][y+1].setIcon(iinum);
					if(!point[x+1][y+1].vflag){safeblock--;}
					point[x+1][y+1].vflag = true;
					wincheck();
					break;
				}
			}
		}
		if(y > 0) {
			switch(point[x][y-1].state) {
			case 0:
				if(!point[x][y-1].vflag) {
					find(x,y-1);
					wincheck();
					for(Point p[] : point) {
						for(Point pp : p) {
							pp.findflag = true;
						}
					}
				}
				break;
			case -1:
				if(!point[x][y-1].mark) {
					fail();
				}
				break;
			default:
				ImageIcon iinum = new ImageIcon(point[x][y-1].state+".png");
				iinum.setImage(iinum.getImage().getScaledInstance(
						point[0][0].getWidth(), point[0][0].getHeight(),
						Image.SCALE_DEFAULT));
				point[x][y-1].setIcon(iinum);
				if(!point[x][y-1].vflag){safeblock--;}
				point[x][y-1].vflag = true;
				wincheck();
				break;
			}
		}
		if(y < pointy - 1) {
			switch(point[x][y+1].state) {
			case 0:
				if(!point[x][y+1].vflag) {
					find(x,y+1);
					wincheck();
					for(Point p[] : point) {
						for(Point pp : p) {
							pp.findflag = true;
						}
					}
				}
				break;
			case -1:
				if(!point[x][y+1].mark) {
					fail();
				}
				break;
			default:
				ImageIcon iinum = new ImageIcon(point[x][y+1].state+".png");
				iinum.setImage(iinum.getImage().getScaledInstance(
						point[0][0].getWidth(), point[0][0].getHeight(),
						Image.SCALE_DEFAULT));
				point[x][y+1].setIcon(iinum);
				if(!point[x][y+1].vflag){safeblock--;}
				point[x][y+1].vflag = true;
				wincheck();
				break;
			}
		}
	}

	private void wincheck() {
		// TODO 自动生成的方法存根
		if(safeblock == 0) {
			win();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
