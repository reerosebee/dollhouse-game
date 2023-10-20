/*
Riya *-*
5.27.23
Game.java
Game
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.util.Timer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.io.File;
import java.io.FileWriter; 
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException; 

//Create Class For Game
public class Game
{
	CardLayout cardLayout = new CardLayout();//cardlayout for game
	JPanel gp;
	boolean intro, red, win1, win2, win3, in;
	boolean first = false;
	boolean trees, water, crop, flowers, houses, pebble;
	int score1, score2, score3, points3;
	int currency = 0;
	int ha, fo, she;
	Font basic, basic2, cost, winMoney, leaderboard;
	Leaderboard le;
	//call constructor to get out of static
	public static void main(String[] args)
	{
		Game test = new Game();
	}
	//call JFrame class MainFrame
	public Game(){
		System.out.println("Starting Game...");
		MainFrame frame = new MainFrame();
	}	
	//Create JFrame and add Cardlayout
	class MainFrame extends JFrame
	{
		//add attriubtes to frame and then add CardLayout panels
		public MainFrame()
		{
			//create font basic
			try{
				basic = Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Riangriung-Demo 2.otf")).deriveFont(75f);	
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Riangriung-Demo 2.otf")));			
			}
			catch(IOException | FontFormatException e){}	
			
			//basic2
			try{
				basic2 = Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Riangriung-Demo 2.otf")).deriveFont(50f);	
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Riangriung-Demo 2.otf")));			
			}
			catch(IOException | FontFormatException e){}	
			
			//winMoney
			try{
				winMoney = Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Jack Armstrong.ttf")).deriveFont(75f);	
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Jack Armstrong.ttf")));			
			}
			catch(IOException | FontFormatException e){}	
			
			//cost
			try{
				cost = Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Jack Armstrong.ttf")).deriveFont(30f);	
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Jack Armstrong.ttf")));			
			}
			catch(IOException | FontFormatException e){}	
			
			//leaderboard
			try{
				leaderboard = Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Premier2019-rPv9.ttf")).deriveFont(65f);	
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("text-fonts/Premier2019-rPv9.ttf")));			
			}
			catch(IOException | FontFormatException e){}	
			
			//create JFrame and default stuff
			setPreferredSize(new Dimension(2000, 900));  
			setLocation(50,50);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
			pack(); 
			setResizable(true);
			setVisible(true);  
			setBackground(Color.GRAY);

			//make container for cardLayout aka GamePanel
			gp = new JPanel();
			
			gp.setLayout(cardLayout);
			add(gp);
			
			Intro in = new Intro();
			intro = true;
			Play pl = new Play();
			Purchase pe = new Purchase();
			GamePage gpa = new GamePage();
			Game1 g1 = new Game1();
			Game2 g2 = new Game2();
			Game3 g3 = new Game3();
			Instructions ins = new Instructions();
			Win wi = new Win();
			Pause pa = new Pause();
			le = new Leaderboard();
			
			gp.add(in, "Intro");
			gp.add(pl, "Play");
			gp.add(pe, "Purchase");
			gp.add(gpa, "Games");
			gp.add(g1, "Game1");
			gp.add(g2, "Game2");
			gp.add(g3, "Game3");
			gp.add(ins, "Inst");
			gp.add(wi, "Win");
			gp.add(pa, "Pause");
			gp.add(le, "Leader");

			//reset					
			revalidate();
		}
	}
	
	//Intro JPanel appearance 
	class Intro extends JPanel implements ActionListener, KeyListener
	{
		//Timer and class for character
		Timer gameTimer;
		PlayerIntro player;
			
		//arraylist objects for obstacles and terrain
		ArrayList<Objects> objects = new ArrayList<>();
		ArrayList<Objects> obstacles = new ArrayList<>();
			
			
		//locations for images
		int x1=0;
		int x2=2000;
		int s1=1500;
			
		//JButtons
		JButton play, how, leader;
			
		ImageIcon plB, plB2, leB, leB2, hoB, hoB2;
			
		//Create buttons for front page
		public Intro()
		{
			player = new PlayerIntro(400,100,this);
		
			gameTimer = new Timer();
			gameTimer.schedule(new TimerTask(){
				public void run()
				{
					player.set();
					repaint();
				}
			}, 0, 17);
			
			//resize ImageIcons to fit Jbuttons
			plB = new ImageIcon("images/Play.PNG");
			Image img = plB.getImage() ;  
			Image newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			plB = new ImageIcon( newimg );
			plB2 = new ImageIcon("images/Play2.PNG");
			img = plB2.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			plB2 = new ImageIcon( newimg );
			
			leB = new ImageIcon("images/Lead.PNG");
			img = leB.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			leB = new ImageIcon( newimg );
			leB2 = new ImageIcon("images/Lead2.PNG");
			img = leB2.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			leB2 = new ImageIcon( newimg );
			
			hoB = new ImageIcon("images/How.PNG");
			img = hoB.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			hoB = new ImageIcon( newimg );
			hoB2 = new ImageIcon("images/How2.PNG");
			img = hoB2.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			hoB2 = new ImageIcon( newimg );
			
			play = new JButton("Play");
			play.setIcon(plB);
			play.setOpaque(false);
			play.setContentAreaFilled(false);
			play.setBorderPainted(false);
			play.addActionListener(this);
			play.setBounds(535, 325, 400, 100);
			play.setFocusable(false);
			
			how = new JButton("How To Play");
			how.setIcon(hoB);
			how.setOpaque(false);
			how.setContentAreaFilled(false);
			how.setBorderPainted(false);
			how.addActionListener(this);
			how.setBounds(535, 425, 400, 100);
			how.setFocusable(false);
			
			leader = new JButton("Leaderboards");
			leader.setIcon(leB);
			leader.setOpaque(false);
			leader.setContentAreaFilled(false);
			leader.setBorderPainted(false);
			how.addActionListener(this);
			leader.addActionListener(this);
			leader.setBounds(535, 525, 400, 100);
			leader.setFocusable(false);
			
			addKeyListener(this);
			setLayout(null);
			add(play);
			add(how);
			add(leader);
			setFocusable(true);
		}
			
		//see if button is clicked and perform action based on that
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals("Play"))
			{
				intro = false;
				cardLayout.next(gp);
				Play play = new Play();
			}
			if(command.equals("How To Play")) cardLayout.show(gp, "Inst");
			if(command.equals("Leaderboards"))
			{
				in = true; 
				cardLayout.show(gp, "Leader");
			}
			
		}
		//make invisible rectangles for player to stand on/avoid
		public void makeObjects()
		{
				
			if(red)
			{
				//object that character is standing on
				objects.add(new Objects(-200, 775, 2000, 50));
				if(objects.size() > 1){objects.remove(0);}
					
				//remove obstacle if hit and replace when done
				if(!player.dead && player.wait < 300){
					obstacles.add(new Objects(s1, 725, 80, 50));
					if(obstacles.size() > 1){obstacles.remove(0);}
				}
				else {if(obstacles.size()== 1){obstacles.remove(0);}}
				if(player.wait == 300) player.wait=0;
			}
			else
			{
				//object that character is standing on
				objects.add(new Objects(-200, 800, 2000, 50));
				if(objects.size() > 1){objects.remove(0);}
					
				//remove obstacle if hit and replace when done
				if(obstacles.size() >= 1){obstacles.remove(0);}
			}
		}
		//If key pressed, player moves in a certain way
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyChar() == 'a'){ player.left = true; player.faceL = true;}
			if(e.getKeyChar() == 'd'){ player.right = true; player.faceL = false;}
			if(e.getKeyChar() == 'w') player.up = true;
			if(e.getKeyCode() == e.VK_SHIFT){player.shift = !player.shift;} 
			if(e.getKeyCode() == e.VK_CONTROL){red = !red;} 
				
		}
			
		//If key released, player resets to idle position
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyChar() == 'a') player.left = false;
			if(e.getKeyChar() == 'd') player.right = false;
			if(e.getKeyChar() == 'w') player.up = false;
		}
			
		//needed for execution, but no code inside
		public void keyTyped(KeyEvent e){}
		
		//paintComponent for background, player, and obstacles
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			
			Image background = new ImageIcon("images/intro.PNG").getImage();
			Image doll = new ImageIcon("images/dollhouseNorm.JPG").getImage();
			Image doll2 = new ImageIcon("gifs/dollDark.gif").getImage();
			Image background2 = new ImageIcon("images/introRed.PNG").getImage();
			Image spikes = new ImageIcon("images/spikes.png").getImage();
			Image title = new ImageIcon("images/title.png").getImage();
			Image title2 = new ImageIcon("images/titleRed.png").getImage();
				
			if(red)
			{
				g2d.drawImage(doll2, x1 , 0 , 2000, 850, null);
				g2d.drawImage(doll2, x2 , 0 , 2000, 850, null);
				g2d.drawImage(title2, 300 , -100 , 900, 550, null);
				g2d.drawImage(spikes, s1+70 , 725 , 80, 50, null);
				g2d.setColor(Color.BLACK);
				g2d.fillRect(0,0,2000,50);
				g2d.fillRect(0,770,2000,50);
				how.setIcon(hoB2);
				play.setIcon(plB2);
				leader.setIcon(leB2);
					
			}
			else
			{
				g2d.drawImage(doll, x1 , 0 , 2000, 820, null);
				g2d.drawImage(doll, x2 , 0 , 2000, 820, null);
				g2d.drawImage(title, -250 , 50 , 2000, 900, null);
				how.setIcon(hoB);
				play.setIcon(plB);
				leader.setIcon(leB);
			}		
				
			x1--;x2--; s1--;
			player.xC--;
			if(player.xC>=1475)player.xC=-295;
			if(player.xC<=-301) player.xC=1450;
			if(x1<=-2000) x1=1999;
			if(x2<=-2000) x2=1999;
			if(s1<=0) s1 = 1500;
				
			makeObjects();
			player.draw(g2d);
			setFocusable(true);
			requestFocus();
			for(Objects object: objects) object.draw(g2d);
			for(Objects obstacle: obstacles){ obstacle.draw(g2d); }
		}
	}
		
	//Instructions screen - all games 
	class Instructions extends JPanel implements ActionListener
	{
		Timer gameTimer;
		int x1 = 0;
		int x2 = 2000;
		int slide = 0;
		int page = 0;
		String home1, home2, doll1, doll2, doll3, ent1, ent2, ent3;
		String back1, back2, back3, back4, base1, base2, base3;
		JLabel inst;
		//for future buttrons
		
		//Create instructions, jlabels, and buttons for functionality
		public Instructions()
		{
			home1 = "Controls:<br/>W: Jump <br/>A: Right <br/>D: Left <br/>"
				+"SHIFT: speed increases on first click, decreases";
			home2 = "on second click, and so forth <br/>CONTROL: toggles view";
			
			doll1 = "Your job is to keep your doll alive! You can do this"
				+" by doing these simple steps:<br/>- Play games to earn"
				+" money and buy things";
			doll2 = "for your 'dollhouse'<br/>- You can play a game as many"
				+" times as you want, but you can only earn money from "
				+" a game once!";
			doll3 = "- When purchasing items, make sure you buy the right"
				+" things to keep your doll happy. Too much of one category"
				+" isn't good..";
			ent1 = "Controls:<br/>W: Jump<br/>S: Dive<br/>SHIFT: toggle speed"
				+"<br/>Goal:<br/>Conquer the entrance";
			ent2 = " of the dollhouse! Avoid the obstacles by jumping or "
				+"diving and stay alive as long as you can. The longer you live, the more money you";
			ent3 = " earn.<br/>You only have three lives!";
			back1 = "Controls:<br/>A: Launcher left <br/>D: Launcher right"
				+ "<br/>Q: Toggle bullet or bomb <br/>Click: where bullet will shoot<br/> ";
			back2 = "Goal: <br/> Conquer the backyard of the dollhouse!"
				+"<br/>- Move the launcher up and down to adjust where to shoot";
			back3 = "- Click where you want the bullet to go<br/>- Destroy"
				+" the squirrels with the bullet for one point<br/>- Switch to the bomb";
			back4 = "and blow up the squirrel for two points<br/>- Everytime you hit "
				+"one, they come back faster!<br/>- You only have half a minute!";
			base1 = "Controls:<br/>W: Jump<br/>S: Dive<br/>Click: Shoot bullet<br/> SHIFT: Toggle speed"
				+"<br/>Goal: Conquer the last";
			base2 = "part of the dollhouse, the basement!<br/>- Avoid the hands by jumping<br/>- Shoot"
				+" zombies to earn an extra point";
			base3 = "- Dive to avoid the swords<br/>- Stay alive as long as you can!";
			
			gameTimer = new Timer();
			gameTimer.schedule(new TimerTask(){
				public void run()
				{
					repaint();
				}
			}, 0, 17);
			
			ImageIcon baB = new ImageIcon("images/Back.PNG");
			Image img = baB.getImage() ;  
			Image newimg = img.getScaledInstance(1400, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			baB = new ImageIcon( newimg );
			
			ImageIcon neB = new ImageIcon("images/next.PNG");
			img = neB.getImage() ;  
			newimg = img.getScaledInstance(550, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			neB = new ImageIcon( newimg );
			
			ImageIcon prB = new ImageIcon("images/previous.PNG");
			img = prB.getImage() ;  
			newimg = img.getScaledInstance(550, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			prB = new ImageIcon( newimg );
			
			ImageIcon hdB = new ImageIcon("images/howDown.PNG");
			img = hdB.getImage() ;  
			newimg = img.getScaledInstance(550, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			hdB = new ImageIcon( newimg );
			
			ImageIcon huD = new ImageIcon("images/howUp.PNG");
			img = huD.getImage() ;  
			newimg = img.getScaledInstance(550, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			huD = new ImageIcon( newimg );
			
			JButton back = new JButton("Back");
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.setIcon(baB);
			back.addActionListener(this);
			back.setBounds(0, 25, 300, 75);
			back.setFocusable(false);
			
			JButton next = new JButton("next");
			next.setOpaque(false);
			next.setContentAreaFilled(false);
			next.setBorderPainted(false);
			next.setIcon(neB);
			next.addActionListener(this);
			next.setBounds(1075, 200, 150, 150);
			next.setFocusable(false);
			
			JButton previous = new JButton("previous");
			previous.setOpaque(false);
			previous.setContentAreaFilled(false);
			previous.setBorderPainted(false);
			previous.setIcon(prB);
			previous.addActionListener(this);
			previous.setBounds(275, 200, 150, 150);
			previous.setFocusable(false);
			
			JButton down = new JButton("up");
			down.setOpaque(false);
			down.setContentAreaFilled(false);
			down.setBorderPainted(false);
			down.setIcon(hdB);
			down.addActionListener(this);
			down.setBounds(300, 550, 100, 100);
			down.setFocusable(false);
			
			JButton up = new JButton("down");
			up.setOpaque(false);
			up.setContentAreaFilled(false);
			up.setBorderPainted(false);
			up.setIcon(huD);
			up.addActionListener(this);
			up.setBounds(300, 650, 100, 100);
			up.setFocusable(false);
			
			inst = new JLabel();
			inst.setText("<html>"+ home1 +"</html>");
			inst.setBounds(440, 315, 590, 490);
			inst.setFont(basic2);
			inst.setForeground(Color.WHITE);
			inst.setVerticalAlignment(JLabel.TOP);
			inst.setVerticalTextPosition(JLabel.TOP);
			
			setLayout(null);
			add(back);
			add(next);
			add(previous);
			add(down);
			add(up);
			add(inst);
		}
		
		//background image and instructions image depending on type
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Image bg = new ImageIcon("images/bg.JPEG").getImage();
			Image ht = new ImageIcon("images/howTitle.png").getImage();
			Image hd = new ImageIcon("images/howDoll.png").getImage();
			Image he = new ImageIcon("images/howEnter.png").getImage();
			Image hh = new ImageIcon("images/howHome.png").getImage();
			Image hb = new ImageIcon("images/howBack.png").getImage();
			Image hba = new ImageIcon("images/howBase.png").getImage();
			
			g.drawImage(bg, x1 , 0 , 2000, 820, null);
			g.drawImage(bg, x2 , 0 , 2000, 820, null);
			if(slide == 0)
			{ 
				g.drawImage(hh, 300, -125, 900, 1200, null);
				if(page == 0) inst.setText("<html>"+ home1 +"</html>");
				if(page == 1) inst.setText("<html>"+ home2 +"</html>");
			}
			if(slide == 1)
			{
				g.drawImage(hd, 300, -125, 900, 1200, null);
				if(page == 0) inst.setText("<html>"+ doll1 +"</html>");
				if(page == 1) inst.setText("<html>"+ doll2 +"</html>");
				if(page == 2) inst.setText("<html>"+ doll3 +"</html>");
			}
			if(slide == 2)
			{ 
				g.drawImage(he, 300, -125, 900, 1200, null);
				if(page == 0) inst.setText("<html>"+ ent1 +"</html>");
				if(page == 1) inst.setText("<html>"+ ent2 +"</html>");
				if(page == 2) inst.setText("<html>"+ ent3 +"</html>");
			}
			if(slide == 3)
			{ 
				g.drawImage(hb, 300, -125, 900, 1200, null);
				if(page == 0) inst.setText("<html>"+ back1 +"</html>");
				if(page == 1) inst.setText("<html>"+ back2 +"</html>");
				if(page == 2) inst.setText("<html>"+ back3 +"</html>");
				if(page == 3) inst.setText("<html>"+ back4 +"</html>");
			}
			if(slide == 4)
			{
				g.drawImage(hba, 300, -125, 900, 1200, null);
				if(page == 0) inst.setText("<html>"+ base1 +"</html>");
				if(page == 1) inst.setText("<html>"+ base2 +"</html>");
				if(page == 2) inst.setText("<html>"+ base3 +"</html>");
				
			}
			g.drawImage(ht, 375,50,800,850,null);
			
			x1--; x2--;
			if(x1<=-2000) x1=1999;
			if(x2<=-2000) x2=1999;
			
		}
		
		//if a button is clicked, change screen appropiately
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals("Back")) cardLayout.show(gp, "Intro");
			if(command.equals("next"))
			{ 
				slide++;
				page = 0;
				if(slide > 4) slide=0;
			}
			if(command.equals("previous"))
			{ 
				slide--;
				page = 0;
				if(slide < 0) slide=4;
			}
			if(command.equals("down"))
			{ 
				page++;
				if(slide == 0){ if(page > 1) page = 1; }
				if(slide == 1){ if(page > 2) page = 2; }
				if(slide == 2){ if(page > 2) page = 2; }
				if(slide == 3){ if(page > 3) page = 3; }
				if(slide == 4){ if(page > 3) page = 3; }
			}
			if(command.equals("up"))
			{ 
				page--;
				if(page < 0) page = 0;
			}
			
		}
	}
	
	//read into files and display it
	class Leaderboard extends JPanel implements ActionListener
	{
		JTextArea lead;
		JTextField name;
		JScrollPane sp;
		String info, user;
		Scanner input;
		int num;
		ArrayList<String> list = new ArrayList<String>();
		Timer back;
		
		//add file contents to string and add it to JLabel
		public Leaderboard()
		{
			info = "";
			user = "N/A";
			back = new Timer();
			back.schedule(new TimerTask(){
				public void run()
				{
					repaint();
				}
			}, 0, 17);
			
			ImageIcon neB = new ImageIcon("images/next.PNG");
			Image img = neB.getImage() ;  
			Image newimg = img.getScaledInstance(550, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			neB = new ImageIcon( newimg );
			
			ImageIcon prB = new ImageIcon("images/previous.PNG");
			img = prB.getImage() ;  
			newimg = img.getScaledInstance(550, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			prB = new ImageIcon( newimg );
			
			ImageIcon baB = new ImageIcon("images/Back.PNG");
			img = baB.getImage() ;  
			newimg = img.getScaledInstance(1400, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			baB = new ImageIcon( newimg );
			
			ImageIcon enB = new ImageIcon("images/enterName.PNG");
			img = enB.getImage() ;  
			newimg = img.getScaledInstance(450, 575,  java.awt.Image.SCALE_SMOOTH ) ;  
			enB = new ImageIcon( newimg );
			
			JButton back = new JButton("Back");
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.setIcon(baB);
			back.addActionListener(this);
			back.setBounds(-35, 25, 300, 75);
			back.setFocusable(false);
			
			JButton next = new JButton("next");
			next.setOpaque(false);
			next.setContentAreaFilled(false);
			next.setBorderPainted(false);
			next.setIcon(neB);
			next.addActionListener(this);
			next.setBounds(1175, 75, 100, 100);
			next.setFocusable(false);
			
			JButton previous = new JButton("previous");
			previous.setOpaque(false);
			previous.setContentAreaFilled(false);
			previous.setBorderPainted(false);
			previous.setIcon(prB);
			previous.addActionListener(this);
			previous.setBounds(225, 75, 100, 100);
			previous.setFocusable(false);
			
			lead = new JTextArea();
			lead.setBounds(275,185,1060,500);
			lead.setFont(leaderboard);
			lead.setForeground(Color.WHITE);
			lead.setMargin( new Insets(10,50,30,50) );
			lead.setEditable(true);
			lead.setOpaque(false);
			
			name = new JTextField();
			name.setBounds(40,620,175,50);
			name.setFont(cost);
			name.setForeground(Color.WHITE);
			name.setBorder(BorderFactory.createLineBorder(Color.white, 0));
			name.setBackground(null);
			name.setOpaque(false);
			name.setEditable(true);
			
			JButton enter = new JButton("Enter");
			enter.setBounds(20,680,200,100);
			enter.addActionListener(this);
			enter.setFocusable(false);
			enter.setOpaque(false);
			enter.setContentAreaFilled(false);
			enter.setBorderPainted(false);
			enter.setFont(cost);
			enter.setIcon(enB);
			
			sp = new JScrollPane(lead);
			sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(295, 225, 880, 500);
			sp.setOpaque(false);
			sp.setBackground(new Color(0, 0, 0, 0));
			sp.getViewport().setOpaque(false);
			sp.setBorder(BorderFactory.createEmptyBorder());
			
			read(1);
			list.clear();
			lead.setText(info);
			info = "";
			
			setLayout(null);
			//add(lead);
			add(next);
			add(previous);
			add(back);
			add(name);
			add(enter);
			add(sp);
		}
		
		//read into specific file
		public void read(int file)
		{
			info = "";
			File inFile = new File ("text-fonts/game1.txt"); 
			if(file == 2) inFile = new File ("text-fonts/game2.txt"); 
			if(file == 3) inFile = new File ("text-fonts/game3.txt"); 
			
			input = null; 
			try
			{ 
				input = new Scanner (inFile);
			}
			catch (FileNotFoundException e) 
			{ 
				System.err.println("Cannot find file.");  
				System.exit(1);
			}
			
			while (input.hasNext()){
				list.add(input.next());
			}
			input.close();
			
			for(int i = 0; i < list.size()-2; i+=3)
			{
				info += list.get(i) + "\t" + list.get(i+1) +"\t" + list.get(i+2) +"\n";
			}
			lead.setText(info);
		}
		
		//add user input and organize into right file
		public void organize(int file)
		{
			read(file);
			int count=0;
			int[] sort = new int[(list.size()/3)];
			String[] names = new String[(list.size()/3)];
			String[] array = new String[((list.size())-1-((list.size()/3-1)))];
			
			//make array version of list
			for(int i = 4; i < list.size(); i+=3)
			{
				array[count] = list.get(i);
				array[count+1] = list.get(i+1);
				count+=2;
			}
			count = 0;
			//add new input
			array[array.length-2] = user;
			if(file == 1) array[array.length-1] = ""+score1;
			if(file == 2) array[array.length-1] = ""+score2;
			if(file == 3) array[array.length-1] = ""+score3;
			
			//make new array of just numbesr
			for(int i = 0; i < array.length; i+=2)
			{
				names[count] = array[i];
				sort[count] = Integer.parseInt(array[i+1]);
				count++;
			}
			
			bubbleSort(sort, sort.length, 1, names);
			
			count = 0;
			for(int i = 0; i < array.length; i+=2)
			{
				array[i] = names[count];
				array[i+1] = ""+sort[count];
				count++;
			}
			
			list.clear();
			
			count = 1;
			for(int i = 0; i < array.length; i+=2)
			{
				list.add(""+count);
				list.add(array[i]);
				list.add(array[i+1]);
				count++;
			}
			write(file);
		}
		
		//sort by highest score to lowest score by file
		public void bubbleSort(int[] arr, int n1, int start, String[] names)
		{                                       
			if (n1 == 1)                     
			{
				return;
			}

			for (int i=0; i<n1-1; i++)       
			{
				if (arr[i] < arr[i+1])     
				{                          
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					
					String temp2 = names[i];
					names[i] = names[i+1];
					names[i+1] = temp2;
				}
			}		
			bubbleSort(arr, n1-1, start++, names);          
		}
			
		//write new information/organization into specific file
		public void write(int file)
		{
			File outFile = new File ("text-fonts/game1.txt"); 
			if(file == 2) outFile = new File ("text-fonts/game2.txt"); 
			if(file == 3) outFile = new File ("text-fonts/game3.txt"); 
			PrintWriter makesOutput = null;
			try
			{
				makesOutput = new PrintWriter( outFile );
			}
			catch (FileNotFoundException e)
			{
				System.err.println("Cannot create file to be written to.");
				System.exit(1);
			}
			
			makesOutput.println("Rank\tName\tScore");
			for(int i = 0; i < list.size(); i+=3)
			{
				makesOutput.printf("%s\t%s\t%s\n", list.get(i), list.get(i+1), list.get(i+2));	
			}
			makesOutput.close();	
			list.clear();
		}
		
		//check if button is clicked and perform action
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals("Back")) cardLayout.show(gp, "Intro");
			if(command.equals("next")) 
			{
				num++;
				if(num > 2) num = 0;
			}
			if(command.equals("previous")) 
			{
				num--;
				if(num < 0) num = 2;
			}
			if(command.equals("Enter"))
			{
				user = name.getText();
				name.setEditable(false);
			}
			if(num == 0) read(1);
			if(num == 1) read(2);
			if(num == 2) read(3);
			list.clear();
			lead.setText(info);
			info = "";
		}
		
		//paint background/leaderboard items
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			if(in)
			{
				if(num == 0) read(1);
				if(num == 1) read(2);
				if(num == 2) read(3);
				list.clear();
				in = false;
				lead.setText(info);
				info = "";
			}	
			
			Image back = new ImageIcon("images/Leaderboard1.png").getImage();
			if(num == 1) back = new ImageIcon("images/Leaderboard2.png").getImage();
			if(num == 2) back = new ImageIcon("images/Leaderboard3.png").getImage();
			Image clouds = new ImageIcon("gifs/cloud.gif").getImage();
			Image enterBox = new ImageIcon("images/entName.png").getImage();
			
			g.drawImage(clouds, 0, 0, 1500, 1000,null);
			g.drawImage(clouds, 1500, 0, 1500, 1000,null);
			g.drawImage(back, 200,-125,1100,1200,null);
			g.drawImage(enterBox, -50,425,350,450,null);
		}
	}
	
	//Play screen with additional buttons
	class Play extends JPanel implements ActionListener
	{
		//Jbuttons 
		JButton back, games, purchase;
		JLabel total;
		
		Timer backTimer;
		
		//Images for house
		Image tree, waterfall, crops, flower, house, pebbles;
		
		int x1, x2;
		
		//Make buttons and add to JPanel
		public Play()
		{
			backTimer = new Timer();
			backTimer.schedule(new TimerTask(){
				public void run()
				{
					repaint();
				}
			}, 0, 17);
			
			revalidate();
			x1 = 0; x2 = 1500;
			ImageIcon baB = new ImageIcon("images/Back.PNG");
			Image img = baB.getImage() ;  
			Image newimg = img.getScaledInstance(1400, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			baB = new ImageIcon( newimg );
			
			ImageIcon gaB = new ImageIcon("images/Games.PNG");
			img = gaB.getImage() ;  
			newimg = img.getScaledInstance(1400, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			gaB = new ImageIcon( newimg );
			
			ImageIcon paB = new ImageIcon("images/Purchase.PNG");
			img = paB.getImage() ;  
			newimg = img.getScaledInstance(1400, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			paB = new ImageIcon( newimg );
			
			back = new JButton("Home Page");
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.setIcon(baB);
			back.addActionListener(this);
			back.setBounds(1200, 25, 300, 75);
			back.setFocusable(false);
			
			games = new JButton("Games");
			games.setOpaque(false);
			games.setContentAreaFilled(false);
			games.setBorderPainted(false);
			games.setIcon(gaB);
			games.addActionListener(this);
			games.setBounds(1200, 110, 300, 75);
			games.setFocusable(false);
			
			purchase = new JButton("Purchase");
			purchase.setOpaque(false);
			purchase.setContentAreaFilled(false);
			purchase.setBorderPainted(false);
			purchase.setIcon(paB);
			purchase.addActionListener(this);
			purchase.setBounds(1200, 195, 300, 75);
			purchase.setFocusable(false);
			
			total = new JLabel(""+currency);
			total.setOpaque(false);
			total.setFont(cost);
			total.setForeground((new Color(210, 152, 73)));
			total.setBounds(100, 35, 200, 50);
			
			repaint();
			setLayout(null);
			add(back);
			add(games);
			add(purchase);
			add(total);
		}
		
		//Paint any background or images needed
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D)g;
			Image clouds = new ImageIcon("gifs/cloud2.gif").getImage();
			if(ha<1 || fo<1 || she <1) clouds = new ImageIcon("gifs/light.gif").getImage();
			if(ha>0 && fo>0 && she >0) clouds = new ImageIcon("gifs/cloud.gif").getImage();
			if(ha>1 && fo>1 && she >1) clouds = new ImageIcon("gifs/cloud2.gif").getImage();
			
			Image money = new ImageIcon("images/money.png").getImage();
			Image grass = new ImageIcon("images/grass.PNG").getImage();
			tree = new ImageIcon("images/tree.PNG").getImage();
			waterfall = new ImageIcon("images/waterfall.PNG").getImage();
			crops = new ImageIcon("images/crops.PNG").getImage();
			flower = new ImageIcon("images/flower.PNG").getImage();
			house = new ImageIcon("images/house.PNG").getImage();
			pebbles = new ImageIcon("images/pebbles.PNG").getImage();
			Image girl = new ImageIcon("images/dollGirl.PNG").getImage();
			if(ha<1 || fo<1 || she <1) girl = new ImageIcon("images/dollMad.PNG").getImage();
			if(ha>=1 && fo>=1 && she >=1) girl = new ImageIcon("images/dollGirl.PNG").getImage();
			if(ha==2 && fo==2 && she ==2) girl = new ImageIcon("images/dollHappy.PNG").getImage();
			
			Image hapBar = new ImageIcon("images/happy_empty.png").getImage();
			if(ha==1) hapBar = new ImageIcon("images/happy_half.png").getImage();
			if(ha==2) hapBar = new ImageIcon("images/happy_whole.png").getImage();
			Image fooBar = new ImageIcon("images/food_empty.png").getImage();
			if(fo==1) fooBar = new ImageIcon("images/food_half.png").getImage();
			if(fo==2) fooBar = new ImageIcon("images/food_whole.png").getImage();
			Image sheBar = new ImageIcon("images/shelter_empty.png").getImage();
			if(she==1) sheBar = new ImageIcon("images/shelter_half.png").getImage();
			if(she==2) sheBar = new ImageIcon("images/shelter_whole.png").getImage();
			
			if(ha<1 || fo<1 || she <1)
			{
				g2d.drawImage(clouds, 0, 0, 1000, 1000,null);
				g2d.drawImage(clouds, 1000, 0, 1000, 1000,null);
			}
			if(ha>0 && fo>0 && she >0)
			{
				g2d.drawImage(clouds, 0, 0, 1500, 900,null);
				//g2d.drawImage(clouds, 1000, 0, 1000, 1000,null);
			}
			if(ha>1 && fo>1 && she >1)
			{ 
				g2d.drawImage(clouds, x1, -70, 1500, 900,null);
				g2d.drawImage(clouds, x2, -70, 1500, 900,null);
			}
			g2d.drawImage(hapBar, -615, -150, 1550, 800,null);
			g2d.drawImage(fooBar, -615, -50, 1550, 800,null);
			g2d.drawImage(sheBar, -615, 50, 1550, 800,null);
			g2d.drawImage(grass, 300, 50, 800, 800,null);
			
			if(water) g2d.drawImage(waterfall, 300, 50, 800, 800,null);
			if(pebble) g2d.drawImage(pebbles, 300, 50, 800, 800,null);
			if(crop) g2d.drawImage(crops, 300, 50, 800, 800,null);
			if(houses) g2d.drawImage(house, 300, 50, 800, 800,null);
			if(trees) g2d.drawImage(tree, 300, 50, 800, 800,null);
			if(flowers) g2d.drawImage(flower, 300, 50, 800, 800,null);
			
			g2d.drawImage(money, -550, -250, 1400, 600,null);
			g2d.drawImage(girl, 300, 50, 800, 800,null);
			
			if(ha<1 || fo<1 || she <1){ x1 = 0; x2 = -1500; }
			if(ha>1 && fo>1 && she >1){ x1++; x2++; }
			else if(ha>0 && fo>0 && she >0){ x1 = 0; x2 = -1500; }
			if(x1>=1500) x1=-1499;
			if(x2>=1500) x2=-1499;
			
			total.setText(""+currency);
		}
		
		//If button clicked, perform action 
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals("Home Page")){ cardLayout.show(gp, "Intro"); intro = true;}
			if(command.equals("Purchase")) cardLayout.show(gp, "Purchase");
			if(command.equals("Games"))cardLayout.show(gp, "Games");
			
			
		}
	}	
	
	//purchase window to buy items for dollhouse
	class Purchase extends JPanel implements ActionListener
	{
		//booleans for if item is purchased
		JButton back;
		JButton[] buy = new JButton[6];
		JLabel total;
		//ImageIcon[] ib = new ImageIcon[6];
		Play play = new Play();
		Timer backTimer;
		//create buttons and icons for panel
		public Purchase()
		{
			backTimer = new Timer();
			backTimer.schedule(new TimerTask(){
				public void run()
				{
					repaint();
				}
			}, 0, 17);
			ImageIcon btB = new ImageIcon("images/Back.PNG");
			Image img = btB.getImage() ;  
			Image newimg = img.getScaledInstance(1400, 600,  java.awt.Image.SCALE_SMOOTH ) ;  
			btB = new ImageIcon( newimg );
			
			ImageIcon ib = new ImageIcon("images/TreeBuy.png");
			
			back = new JButton("Back To House");
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.addActionListener(this);
			back.setIcon(btB);
			back.setBounds(1200, 125, 300, 75);
			back.setFocusable(false);
			
			total = new JLabel(""+currency);
			total.setOpaque(false);
			total.setFont(cost);
			total.setForeground((new Color(210, 152, 73)));
			total.setBounds(1250, 35, 200, 50);
			total.setFocusable(false);
			
			buy[0] = new JButton("Tree");
			buy[1] = new JButton("Waterfall");
			buy[2] = new JButton("Crops");
			buy[3] = new JButton("Flowers");
			buy[4] = new JButton("House");
			buy[5] = new JButton("Walkway");
		
			for(int i = 0; i < 3; i++){
				if(i==0) ib = new ImageIcon("images/TreeBuy.png");
				if(i==1) ib = new ImageIcon("images/WaterfallBuy.png");
				if(i==2) ib = new ImageIcon("images/CropsBuy.png");
				img = ib.getImage() ;  
				newimg = img.getScaledInstance(1700, 800,  java.awt.Image.SCALE_SMOOTH ) ;  
				ib = new ImageIcon( newimg );
				buy[i].setOpaque(false);
				buy[i].setContentAreaFilled(false);
				buy[i].setBorderPainted(false);
				buy[i].setIcon(ib);
				buy[i].setBounds(100+(i*350),200, 300, 200);
				buy[i].addActionListener(this);
				buy[i].setFocusable(false);
				add(buy[i]);
			}
			
			for(int i = 0; i < 3; i++){
				if(i==0) ib = new ImageIcon("images/FlowersBuy.png");
				if(i==1) ib = new ImageIcon("images/HouseBuy.png");
				if(i==2) ib = new ImageIcon("images/WalkwayBuy.png");
				img = ib.getImage() ;  
				newimg = img.getScaledInstance(1700, 800,  java.awt.Image.SCALE_SMOOTH ) ;  
				ib = new ImageIcon( newimg );
				buy[i+3].setOpaque(false);
				buy[i+3].setContentAreaFilled(false);
				buy[i+3].setBorderPainted(false);
				buy[i+3].setIcon(ib);
				buy[i+3].setBounds(100+(i*350),450, 300, 200);
				buy[i+3].addActionListener(this);
				buy[i+3].setFocusable(false);
				add(buy[i+3]);
			}
			
			
			setLayout(null);
			add(back);
			add(total);
		}
		
		//paint background, title, and currency box
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Image clouds = new ImageIcon("gifs/cloud2.gif").getImage();
			Image title = new ImageIcon("images/TitleP.png").getImage();
			Image money = new ImageIcon("images/money.png").getImage();
			
			g.drawImage(clouds, 0,0,1500,820,null);
			g.drawImage(title, -100,20,1500,600,null);
			g.drawImage(money, 600, -250, 1400, 600,null);
			
			total.setText(""+currency);
			
		}
		
		//buy items or go back
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals("Back To House")) cardLayout.show(gp, "Play");;
			if(command.equals("Tree"))
			{
				if(currency >= 200 && !trees)
				{
					trees = true;
					currency -= 200;
					she++;
				}
			}
			if(command.equals("Waterfall"))
			{
				if(currency >= 150 && !water)
				{
					water = true;
					currency -= 150;
					ha++;
				}
			}
			if(command.equals("Crops"))
			{
				if(currency >= 150 && !crop)
				{
					crop = true;
					currency -= 150;
					fo++;
				}
			}
			if(command.equals("Flowers"))
			{
				if(currency >= 100 && !flowers)
				{
					flowers = true;
					currency -= 100;
					fo++;
				}
			}
			if(command.equals("House"))
			{
				if(currency >= 350 && !houses)
				{
					houses = true;
					currency -= 350;
					she++;
				}
			}
			if(command.equals("Walkway"))
			{
				if(currency >= 150 && !pebble)
				{
					pebble = true;
					currency -= 150;
					ha++;
				}
			}
		
		}
	}
	
	//Game page with buttons to go to all three games
	class GamePage extends JPanel implements ActionListener
	{
		
		JButton jb2, jb3;
		
		//Buttons for games and for going back
		public GamePage()
		{	
			
			
			ImageIcon bgB = new ImageIcon("images/BackG.PNG");
			Image img = bgB.getImage() ;  
			Image newimg = img.getScaledInstance(700, 300,  java.awt.Image.SCALE_SMOOTH ) ;  
			bgB = new ImageIcon( newimg );
			
			ImageIcon enB = new ImageIcon("images/Entrance.PNG");
			img = enB.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			enB = new ImageIcon( newimg );
				
			ImageIcon baB = new ImageIcon("images/Locked.PNG");
			if(win1) baB = new ImageIcon("images/Backyard.PNG");
			img = baB.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			baB = new ImageIcon( newimg );
				
			ImageIcon bsB = new ImageIcon("images/Locked.PNG");
			if(win2) bsB = new ImageIcon("images/Basement.PNG");
			img = bsB.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			bsB = new ImageIcon( newimg );
			
			JButton jb = new JButton("1");
			jb.setOpaque(false);
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			jb.setIcon(enB);
			jb.setBounds(100, 375, 300, 100);
			jb.addActionListener(this);
			jb.setFocusable(false);
			
			
			jb2 = new JButton("2");
			jb2.setOpaque(false);
			jb2.setContentAreaFilled(false);
			jb2.setBorderPainted(false);
			jb2.setIcon(baB);
			jb2.setBounds(600, 375, 300, 100);
			jb2.addActionListener(this);
			jb2.setFocusable(false);
			
			jb3 = new JButton("3");
			jb3.setOpaque(false);
			jb3.setContentAreaFilled(false);
			jb3.setBorderPainted(false);
			jb3.setIcon(bsB);
			jb3.setBounds(1100, 375, 300, 100);
			jb3.addActionListener(this);
			jb3.setFocusable(false);
			
			JButton back = new JButton("Back");
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.setIcon(bgB);
			back.setBounds(10, 10, 100, 50);
			back.addActionListener(this);
			
			setSize(2000,900);
			setLayout(null);
			add(jb);
			add(jb2);
			add(jb3);
			add(back);
		}
		//go to games or go back
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();	
			if(command.equals("1")) cardLayout.show(gp, "Game1");
			if(command.equals("2")/* && win1*/) cardLayout.show(gp, "Game2");
			if(command.equals("3")/* && win2*/) cardLayout.show(gp, "Game3");
			if(command.equals("Back")){ cardLayout.show(gp, "Play"); }
			
		}
		
		//paint background images for each game
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D)g;
			Image bg1 = new ImageIcon("images/map1.jpg").getImage();
			Image bg2 = new ImageIcon("images/g2_bg2.jpg").getImage();
			Image bg3 = new ImageIcon("images/g3_bg1.jpeg").getImage();
		
			g2d.drawImage(bg1, 0, 50, 2000, 715,null);
			g2d.drawImage(bg2, 500, 50, 2000, 715,null);
			g2d.drawImage(bg3, 1000, 50, 2000, 715,null);
			
			
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0,760,2000,65);
			g2d.fillRect(0,0,2000,80);
			g2d.fillRect(480,0,20,850);
			g2d.fillRect(1980,0,20,850);
			
			ImageIcon baB = new ImageIcon("images/Locked.PNG");
			if(win1) baB = new ImageIcon("images/Backyard.PNG");
			Image img = baB.getImage() ;  
			Image newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			baB = new ImageIcon( newimg );
				
			ImageIcon bsB = new ImageIcon("images/Locked.PNG");
			if(win2) bsB = new ImageIcon("images/Basement.PNG");
			img = bsB.getImage() ;  
			newimg = img.getScaledInstance(1600, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			bsB = new ImageIcon( newimg );
			
			jb2.setIcon(baB);
			jb3.setIcon(bsB);
		}
	}
	
	//First game where character avoids obstacles in entry
	class Game1 extends JPanel implements ActionListener, KeyListener
	{
		PlayerGame1 player;
		Timer gameTimer;
		ArrayList<Objects2> objects = new ArrayList<>();
		ArrayList<Objects2> obstacles = new ArrayList<>();
		int x1 = 0;
		int x2 = 2000;
		int r1,r2, r3;
		int o1 = -200;
		int o2 = 700;
		int o3 = 2500;
		int t1, t2, t3;
		int l1, l2, l3;
		
		//Add go back button and start timer for player/background/items
		public Game1()
		{
			score1 = 0;
			r1 = 1500; r2 = 2500; r3 = 3000;
			l1 = 150; l2 = 300; l3 = 650;
			
			ImageIcon paB = new ImageIcon("images/Pause.PNG");
			Image img = paB.getImage() ;  
			Image newimg = img.getScaledInstance(1000, 400,  java.awt.Image.SCALE_SMOOTH ) ;  
			paB = new ImageIcon( newimg );
			
			JButton jb = new JButton("Pause");
			jb.setIcon(paB);
			jb.setOpaque(false);
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			jb.setBounds(5, 5, 60, 60);
			jb.addActionListener(this);
			jb.setFocusable(false);
			
			player = new PlayerGame1(600,350,this);
			
			gameTimer = new Timer();
			
				gameTimer.schedule(new TimerTask(){
					public void run()
					{
						repaint();
					}
				}, 0, 17); 
			
			addKeyListener(this);
			setFocusable(true);
			requestFocus();
			setSize(2000,900);
			setLayout(null);
			add(jb);
		}
		
		//randomize obstacle locations horizontally
		public void randomizeObjects(int choice)
		{
			if(choice == 1) r1 = (int)(Math.random()*900 + 1500);
			if(choice == 2) r2 = (int)(Math.random()*900 + 2500);
			if(choice == 3) r3 = (int)(Math.random()*500 + 2500);
		}
		
		//randomize obstacle locations vertically
		public void randomizeLocation(int choice)
		{
			if(choice == 1) l1 = (int)(Math.random()*100 + 150);
			if(choice == 2) l2 = (int)(Math.random()*125 + 300);
		}
		
		//create Rectangles for the obstacles and the objects to stand on
		public void makeObjects()
		{
			//object that character is standing on
			objects.add(new Objects2(o1, 750, 500, 50));
			if(objects.size() > 3){objects.remove(0);}
			objects.add(new Objects2(o2, 750, 1500, 50));
			if(objects.size() > 3){objects.remove(0);}
			objects.add(new Objects2(o3, 750, 2000, 50));
			if(objects.size() > 3){objects.remove(0);}
			
			//obstacles that the character has to avoid
			obstacles.add(new Objects2((r1-50), l1, 300, 270));
			if(obstacles.size() > 3){obstacles.remove(0);}
			obstacles.add(new Objects2((r2-50), l2, 300, 120));
			if(obstacles.size() > 3){obstacles.remove(0);}
			obstacles.add(new Objects2((r3-25), (l3+25), 150, 60));
			if(obstacles.size() > 3){obstacles.remove(0);}
			
		}
		
		//If key pressed, player moves in a certain way
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyChar() == 'w') player.up = true;
			if(e.getKeyChar() == 's'){ player.yC = 450; player.down = true; }
			if(e.getKeyCode() == e.VK_SHIFT){player.shift = !player.shift;} 

		}
			
		//If key released, player resets to idle position
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyChar() == 'w') player.up = false;
			if(e.getKeyChar() == 's')
			{ 
				player.down = false; 
				player.yC = 450;
				player.charBox.y = 450;
			}
		}
		
		//for functional use only; no code
		public void keyTyped(KeyEvent e){}
		
		//change panels when button clicked
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();	
			if(command.equals("Pause")) cardLayout.show(gp, "Pause");
			
		}
		
		//paint background, player, and obstacles
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D)g;
			Image bg1 = new ImageIcon("images/map1.jpg").getImage();
			Image bg2 = new ImageIcon("images/map2.jpeg").getImage();
			Image bgT = new ImageIcon("images/mapT.png").getImage();
			Image bgT2 = new ImageIcon("images/mapT2.png").getImage();
			if(player.dead)
			{
				bg1 = new ImageIcon("gifs/map1R.gif").getImage();
				bg2 = new ImageIcon("gifs/map2R.gif").getImage();
				bgT = new ImageIcon("images/mapTR.png").getImage();
				bgT2 = new ImageIcon("images/mapT2R.png").getImage();
			}
			
			Image axe = new ImageIcon("gifs/axe copy.gif").getImage();
			Image mace = new ImageIcon("images/mace.png").getImage();
			Image dolls = new ImageIcon("gifs/doll.gif").getImage();
			Image hearts = new ImageIcon("images/hearts_full.png").getImage();
			
			if(player.deaths >=1 && !win1) score1++;
			if(player.deaths == 1 && player.death) hearts = new ImageIcon("gifs/hearts_2.gif").getImage();
			if(player.deaths == 2)
			{
				if(player.death) hearts = new ImageIcon("gifs/hearts_1.gif").getImage();
				else hearts = new ImageIcon("images/hearts_2.png").getImage();
			}
			if(player.deaths == 3)
			{
				if(player.death) hearts = new ImageIcon("gifs/hearts_none.gif").getImage(); 
				else hearts = new ImageIcon("images/hearts_1.png").getImage();
			}
			if(player.deaths == 4) hearts = new ImageIcon("images/hearts_none.png").getImage();
		
			g2d.drawImage(bg1, x1, 50, 2005, 715,null);
			g2d.drawImage(bg2, x2, 50, 2005, 715,null);
			g2d.drawImage(bgT, x1+700, 50, 2005, 715,null);
			g2d.drawImage(bgT2, x2+360, 50, 2005, 715,null);
			g2d.drawImage(axe, r1, l1, 300, 300,null);
			g2d.drawImage(mace, r2, l2, 300, 130,null);
			g2d.drawImage(dolls, r3, l3, 200, 100,null);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0,0,2000,65);
			g2d.drawImage(hearts, 80, -40, 200, 150,null);
			g.fillRect(0,760,2000,80);
			
			if(!player.dead)
			{
				if(player.shift)
				{ 
					if(player.down)
					{
						x1-=8; x2-=8; 
						o1-=8; o2-=8; o3-=8;
						r1-=12; r2-=18; r3-=14;
					}
					else
					{
						x1-=4; x2-=4; 
						o1-=4; o2-=4; o3-=4;
						r1-=8; r2-=10; r3-= 9;
					}
				}
				else 
				{ 
					if(player.down)
					{
						x1-=4; x2-=4; 
						o1-=4; o2-=4; o3-=4;
						r1-=8; r2-=10; r3-=9;
					}
					else
					{
						x1-=2; x2-=2; 
						o1-=2; o2-=2; o3-=2;
						r1-=4; r2-=6; r3-=5;
					}
				}
			}
			if(x1 <= -2000) x1 = 1999;
			if(x2 <= -2000) x2 = 1999;
			if(o1 <= -2200) o1 = 1799;
			if(o2 <= -1300) o2 = 2699;
			if(o3 <= -1500) o3 = 2499;
			if(r1 <= -400){
				randomizeObjects(1);
				randomizeLocation(1);
			}
			if(r2 <= -400){
				randomizeObjects(2);
				randomizeLocation(2);
			}
			if(r3 <= -400){
				randomizeObjects(3);
			}
			
			requestFocus();
			makeObjects();
			player.draw(g2d);
			for(Objects2 object: objects) object.draw(g2d);
			for(Objects2 obstacle: obstacles){ obstacle.draw(g2d); }
		}
	}
	
	//Game2 destroy the squirrels
	class Game2 extends JPanel implements ActionListener, MouseListener, KeyListener
	{
		Timer gameTimer;
		ArrayList<Objects3> obstacles = new ArrayList<>();
		ArrayList<Objects3> obstaclesBlow = new ArrayList<>();
		ArrayList<Objects3> shoot = new ArrayList<>();
		Bullet bull;
							
		JLabel time, points;
							
		int[] sqX = new int[6];
		int[] sqY = new int[6];
		int[] speed = new int[6];
		int[] bTime = new int[6];
		int b1, b2, mY, x1;
		long timer;
		boolean click, first, swap;
		boolean[] blow = new boolean[6];
		boolean[] blowDone = new boolean[6];
		
		//Create jlabel for points/time, jbutton for pause
		public Game2()
		{	
			for(int i = 0; i < 3; i++) sqX[i] = 2000+(i*500);
			for(int i = 3; i < 6; i++) sqX[i] = -200-(i*500);
			for(int i = 0; i < 6; i++) sqY[i] = 275;
			for(int i = 0; i < 6; i++) speed[i] = 5;
			
			b1 = 500; b2 = 600;
			x1 = 500;
			timer = 1800;
			
			bull = new Bullet(this, b1, 225);
			
			ImageIcon paB = new ImageIcon("images/Pause.PNG");
			Image img = paB.getImage() ;  
			Image newimg = img.getScaledInstance(1000, 400,  java.awt.Image.SCALE_SMOOTH ) ;  
			paB = new ImageIcon( newimg );
			
			JButton jb = new JButton("Pause");
			jb.setIcon(paB);
			jb.setOpaque(false);
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			jb.setBounds(5, 5, 60, 60);
			jb.addActionListener(this);
			
			time = new JLabel("Time Left: 30");
			time.setFont(cost);
			time.setForeground(Color.WHITE);
			time.setOpaque(false);
			time.setBounds(700,5,300,60);
			
			points = new JLabel("Points: 0");
			points.setFont(cost);
			points.setForeground(Color.WHITE);
			points.setOpaque(false);
			points.setBounds(450,5,200,60);
			
			gameTimer = new Timer();
			gameTimer.schedule(new TimerTask(){
				public void run()
				{
					bull.set();
					repaint();
				}
			}, 0, 17); 
			
			addMouseListener(this);
			addKeyListener(this);
			setLayout(null);
			add(jb);
			add(time);
			add(points);
		}
		
		//change panels when button clicked
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals("Pause")) cardLayout.show(gp, "Pause");
		}
		
		//create Rectangles for the obstacles and the objects to stand on
		public void makeObjects()
		{
			//obstacles that the character has to avoid
			for(int i = 0; i < 6; i++)
			{	
				if(bull.dead[i]) obstacles.add(new Objects3(sqX[i]+10, (sqY[i]-1000), 250, 125));
				else obstacles.add(new Objects3(sqX[i]+10, sqY[i], 250, 125));
				if(obstacles.size() > 6){obstacles.remove(0);}
				obstaclesBlow.add(new Objects3(sqX[i]+10, sqY[i], 250, 125));
				if(obstaclesBlow.size() > 6){obstaclesBlow.remove(0);}
			}
		}
		
		//set random x location for squirrels to run
		public void randomTime(int type)
		{
			for(int i = 0; i < 3; i++)
			{
				if(type == i) sqX[i] = (int)(Math.random()*700 + 1800);
				if(bull.de[i]) bull.de[i] = false;
			}
			for(int i = 3; i < 6; i++)
			{
				if(type == i) sqX[i] = (int)(Math.random()*700 - 1800);
				if(bull.de[i]) bull.de[i] = false;
			}
		}
		
		//draw launcher, bullet, acorns, and squirrel
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Image background = new ImageIcon("images/g2_b1.jpeg").getImage();
			Image shooter = new ImageIcon("images/shooter.png").getImage();
			Image squirrel = new ImageIcon("gifs/squirrel.gif").getImage();
			Image squirrelD = new ImageIcon("images/squirrel_dead.png").getImage();
			Image squirrelF = new ImageIcon("gifs/squirrelF.gif").getImage();
			Image squirrelDF = new ImageIcon("images/squirrelF_dead.png").getImage();
			Image squirrelSD = new ImageIcon("images/squirrel_superDead.png").getImage();
			Image squirrelSDF = new ImageIcon("images/squirrelF_superDead.png").getImage();
			Image acorns = new ImageIcon("images/acorns.png").getImage();
			Image acorn = new ImageIcon("images/acorn.png").getImage();
			Image bullet = new ImageIcon("images/bullet.png").getImage();
			Image explode = new ImageIcon("gifs/explosion.gif").getImage();
			Image bomb = new ImageIcon("images/bomb.png").getImage();
			
			g.drawImage(background, 0, -150,1800,1200, null);
			
			for(int i = 0; i < 3; i++)
			{
				if(blowDone[i])g.drawImage(squirrelSD, sqX[i], sqY[i], 250, 125, null);
				else if(bull.dead[i])g.drawImage(squirrelD, sqX[i], sqY[i], 250, 125, null);
				else g.drawImage(squirrel, sqX[i], sqY[i], 250, 125, null);
				if(sqY[i] ==275) blowDone[i] = false;
			}
			for(int i = 3; i < 6; i++)
			{
				if(blowDone[i])g.drawImage(squirrelSDF, sqX[i], sqY[i], 250, 125, null);
				else if(bull.dead[i])g.drawImage(squirrelDF, sqX[i], sqY[i], 250, 125, null);
				else g.drawImage(squirrelF, sqX[i], sqY[i], 250, 125, null);
				if(sqY[i] ==275) blowDone[i] = false;
			}
			
			//g.drawImage(acorn, s1,275,50,50, null);
			if(swap)
			{
				g.drawImage(bullet, 1300, 600,150,220, null);
				g.drawImage(bomb, b1, b2+100, 100, 100, null);
				g.drawImage(shooter, x1, 500, 350, 450, null);
			}
			else
			{
				g.drawImage(bullet, b1, b2,100,140, null);
				g.drawImage(bomb, 1275, 575, 200, 200, null);
				g.drawImage(shooter, x1, 400, 350, 450, null);
			}
			
			for(int i = 0; i < 6; i++)
			{
				if(blow[i] == true)
				{ 
					bTime[i]++;
					g.drawImage(explode, sqX[i]-125, sqY[i]-125, 475, 400, null);
				}
				if(bTime[i] == 30)
				{
					score2+=2;
					bTime[i] = 0;
					blow[i] = false;
					blowDone[i] = true;
				}
			}
			
			g.setColor(Color.BLACK);
			g.fillRect(0,0,2000,65);
			g.fillRect(0,752,2000,80);
			time.setText("Time Left: "+(timer/60));
			points.setText("Points: "+score2);
			
			if(timer <= 0)
			{ 
				win2 = true;
				currency += (score2*8);
				le.organize(2);
				cardLayout.show(gp, "Win");
			}
			
			b2 = bull.yC;
			if(!bull.click)
			{ 
				b1 = x1+125;
				bull.xC = b1;
				bull.bullBox.x = bull.xC;
			}
			
			for(int i = 0; i < 3; i++)
			{
				if(!bull.de[i] && !bull.dead[i]) sqX[i]-=speed[i];
			} 
			for(int i = 3; i < 6; i++)
			{
				if(!bull.de[i] && !bull.dead[i]) sqX[i]+=speed[i];
			} 
			
			if(x1 < 0) x1 = 200;
			if(x1 > 1800) x1 = 200;
			
			for(int i = 0; i < 3; i++)
			{
				if(bull.de[i] || sqX[i] <= -300) randomTime(i);
			}
			for(int i = 3; i < 6; i++)
			{
				if(bull.de[i] || sqX[i] >= 1800) randomTime(i);
			}
			
			requestFocus();
			Graphics2D g2d = (Graphics2D)g;
			makeObjects();
			bull.draw(g2d);
			
			//for(Objects3 obstacle: obstacles){ obstacle.draw(g2d); }
			//for(Objects3 obstacle: obstaclesBlow){ obstacle.draw(g2d); }
			for(Objects3 shot: shoot){ shot.draw(g2d); }
		}
	
		//If key pressed, launcher goes up/down
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyChar() == 'd') bull.right = true;
			if(e.getKeyChar() == 'a') bull.left = true;
			if(e.getKeyChar() == 'q') swap = !swap;
		}
			
		//If key released, launcher stops moving
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyChar() == 'd') bull.right = false;
			if(e.getKeyChar() == 'a') bull.left = false;
		}
		
		//for functional use only; no code
		public void keyTyped(KeyEvent e){}
		
		//only here for functional use
		public void mouseClicked(MouseEvent e) {}
		
		//if clicked, shoot bullet
		public void mousePressed(MouseEvent e) 
		{
			if(!bull.click)
			{
				mY = e.getY();
				bull.click = true;
			}
		}
		//only here for functional use
		public void mouseReleased(MouseEvent e) {}
		//only here for functional use
		public void mouseEntered(MouseEvent e) 	{}
		//only here for functional use
		public void mouseExited(MouseEvent e) {}
		
	}
	
	//Game 3 - combination of game2 and game1
	class Game3 extends JPanel implements ActionListener, KeyListener, MouseListener
	{
		int x1, x2, mX;
		int[] oX = new int[4];
		int[] oY = new int[4];
		boolean die;
		int timeDie;
		
		JLabel points;
		
		Timer gameTimer;
		PlayerGame3 player;
		Bullet2 bull2;
		ArrayList<Objects4> objects = new ArrayList<>();
		ArrayList<Objects4> obstacles = new ArrayList<>();
		
		//create buttons and jlabels
		public Game3()
		{
			x1 = 0; x2 = 1800;
			oX[0] = 1800; oX[1] = 1600; oX[2] = 2000; oX[3] = 2200;
			oY[0] = 400; oY[1] = 650; oY[2] = 300; oY[3] = 100;
			
			player = new PlayerGame3(600,350,this);
			bull2 = new Bullet2(this, 600, 615);
			
			ImageIcon paB = new ImageIcon("images/Pause.PNG");
			Image img = paB.getImage() ;  
			Image newimg = img.getScaledInstance(1000, 400,  java.awt.Image.SCALE_SMOOTH ) ;  
			paB = new ImageIcon( newimg );
			
			JButton jb = new JButton("Pause");
			jb.setIcon(paB);
			jb.setOpaque(false);
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			jb.setBounds(5, 5, 60, 60);
			jb.addActionListener(this);
			
			points = new JLabel("Points: 0");
			points.setFont(cost);
			points.setForeground(Color.WHITE);
			points.setOpaque(false);
			points.setBounds(625,5,200,60);
			
			
			gameTimer = new Timer();
			gameTimer.schedule(new TimerTask(){
				public void run()
				{
					repaint();
					bull2.set();
				}
			}, 0, 17); 
			
			setLayout(null);
			addKeyListener(this);
			addMouseListener(this);
			add(jb);
			add(points);
		}
		
		//create objects for player to stand/avoid
		public void makeObjects()
		{
			//object that character is standing on
			objects.add(new Objects4(0, 750, 2000, 50));
			if(objects.size() > 1){objects.remove(0);}
			
			//obstacles that the character has to avoid
			if(!die) obstacles.add(new Objects4(oX[0],oY[0],300,350));
			else obstacles.add(new Objects4(oX[0],oY[0]-1000,300,350));
			obstacles.add(new Objects4(oX[1], oY[1]+25, 50, 75));
			obstacles.add(new Objects4(oX[2], oY[2], 400, 100));
			if(obstacles.size() > 3){obstacles.remove(0);}
			if(obstacles.size() > 3){obstacles.remove(0);}
			if(obstacles.size() > 3){obstacles.remove(0);}
		}
		
		//randomize obstacle locations horizontally
		public void randomizeObjects(int choice)
		{
			
			for(int i = 0; i < oX.length; i++)
			{
				if(choice == i)
				{	
					if(i == 1) oX[i] = oX[i+1]+700;
					else oX[i] = (int)(Math.random()*1500 + 1500);
				}
			}
		}
		
		//randomize obstacle locations vertically
		public void randomizeLocation(int choice)
		{
			for(int i = 0; i < oY.length; i++)
			{
				if(choice == i) oY[i] = (int)(Math.random()*100 + 350);
			}
		}
		
		//If key pressed, player jumps/slides
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyChar() == 'w') player.up = true;
			if(e.getKeyChar() == 's') player.down = true;
			if(e.getKeyCode() == e.VK_SHIFT){player.shift = !player.shift;} 
		}
			
		//If key released, player returns to walking/running motion
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyChar() == 'w') player.up = false;
			if(e.getKeyChar() == 's') player.down = false;
		}
		
		//for functional use only; no code
		public void keyTyped(KeyEvent e){}
		//only here for functional use
		public void mouseReleased(MouseEvent e) {}
		//only here for functional use
		public void mouseEntered(MouseEvent e) 	{}
		//only here for functional use
		public void mouseExited(MouseEvent e) {}
		//only here for functional use
		public void mouseClicked(MouseEvent e) {}
		
		//if clicked, shoot bullet
		public void mousePressed(MouseEvent e) 
		{
			if(!bull2.click)
			{
				mX = e.getX();
				bull2.click = true;
			}
		}
		
		//if pause button, then go to pause screen
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals("Pause")) cardLayout.show(gp, "Pause");
		}
		
		//paint background and obstacles
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Image back1 = new ImageIcon("images/g3_bg1.jpeg").getImage();
			Image back2 = new ImageIcon("images/g3_bg2.jpeg").getImage();
			Image Rback1 = new ImageIcon("gifs/2map1R.gif").getImage();
			Image Rback2 = new ImageIcon("gifs/2map2R.gif").getImage();
			Image zombie = new ImageIcon("gifs/zombie_walk.gif").getImage();
			Image hand = new ImageIcon("gifs/hands.gif").getImage();
			Image hearts = new ImageIcon("images/hearts_full.png").getImage();
			Image launch = new ImageIcon("images/launcher.png").getImage();
			Image sword = new ImageIcon("images/sword.png").getImage();
			Image sword2 = new ImageIcon("images/sword2.png").getImage();
			
			if(die)
			{
				timeDie++;
				zombie = new ImageIcon("gifs/zombie_die.gif").getImage();
				if(timeDie == 84)
				{
					timeDie = 0;
					die = false;
					randomizeObjects(0);
				}	
			}
			else 
			{
				if(oX[0] < 900 && oX[0] > 600) zombie = new ImageIcon("gifs/zombie_attack.gif").getImage();
			}
			
			if(player.deaths >=1 && !win3) score3++;
			if(player.deaths == 1 && player.death) hearts = new ImageIcon("gifs/hearts_2.gif").getImage();
			if(player.deaths == 2)
			{
				if(player.death) hearts = new ImageIcon("gifs/hearts_1.gif").getImage();
				else hearts = new ImageIcon("images/hearts_2.png").getImage();
			}
			if(player.deaths == 3)
			{
				if(player.death) hearts = new ImageIcon("gifs/hearts_none.gif").getImage(); 
				else hearts = new ImageIcon("images/hearts_1.png").getImage();
			}
			if(player.deaths == 4) hearts = new ImageIcon("images/hearts_none.png").getImage();
			
			if(player.dead)
			{
				g.drawImage(Rback1, x1,65,1800,700, null);
				g.drawImage(Rback2, x2,65,1800,700, null);
			}
			else
			{
				g.drawImage(back1, x1,65,1800,700, null);
				g.drawImage(back2, x2,65,1800,700, null);
			}
			if(die) g.drawImage(zombie, oX[0],oY[0]+50,400,350, null);
			else g.drawImage(zombie, oX[0],oY[0],300,350, null);
			g.drawImage(hand, oX[1], oY[1]+15, 140, 100, null);
			g.drawImage(sword, oX[2], oY[2], 400, 100, null);
			g.drawImage(sword2, oX[3], oY[3], 300, 75, null);
			
			g.fillRect(0,0,2000,65);
			g.fillRect(0,752,2000,80);
			
			g.drawImage(hearts, 80, -40, 200, 150,null);
			
			if(!player.dead)
			{
				if(player.shift)
				{ 
					if(player.down|| player.up)
					{
						x1-=8; x2-=8; 
						if(!die) oX[0] -=10; 
						else oX[0] -=8; 
						oX[1] -=10; oX[2] -=16; oX[3] -=14;
					}
					else
					{
						x1-=4; x2-=4; 
						if(!die) oX[0] -=5; 
						else oX[0] -=4; 
						oX[1] -=6; oX[2] -=8; oX[3] -=7;
					}
				}
				else 
				{ 
					if(player.down|| player.up)
					{
						x1-=4; x2-=4; 
						if(!die) oX[0] -=5; 
						else oX[0] -=4; 
						oX[1] -=6; oX[2] -=8; oX[3] -=7;
					}
					else
					{
						x1-=2; x2-=2; 
						if(!die) oX[0]-=3; 
						else oX[0] -=2; 
						oX[1]-=4; oX[2]-=4; oX[3]-=4;
					} 
				}
			}
			if(x1 <= -1800) x1 = x2+1800;;
			if(x2 <= -1800) x2 = x1+1800;
			if(oX[0] <= -300) randomizeObjects(0);
			if(oX[1] <= -100) randomizeObjects(1);
			if(oX[2] <= -400)
			{ 
				randomizeObjects(2);
				randomizeLocation(2);
			}
			if(oX[3] <= -300) randomizeObjects(3);
			
			points.setText("Points: " + points3);
			
			Graphics2D g2d = (Graphics2D)g;
			requestFocus();
			makeObjects();
			player.draw(g2d);
			bull2.draw(g2d);
			for(Objects4 object: objects){ object.draw(g2d); }
			for(Objects4 obstacle: obstacles){ obstacle.draw(g2d); }
		}
	}
	
	//Pause screen options
	class Pause extends JPanel implements ActionListener
	{
		JButton resume, exit;
		
		//create resume/exit buttons
		public Pause()
		{
			ImageIcon prB = new ImageIcon("images/pauseResume.PNG");
			Image img = prB.getImage() ;  
			Image newimg = img.getScaledInstance(650, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			prB = new ImageIcon( newimg );
			
			ImageIcon peB = new ImageIcon("images/pauseExit.PNG");
			img = peB.getImage() ;  
			newimg = img.getScaledInstance(650, 700,  java.awt.Image.SCALE_SMOOTH ) ;  
			peB = new ImageIcon( newimg );
			
			resume = new JButton("Resume");
			resume.setIcon(prB);
			resume.setOpaque(false);
			resume.setContentAreaFilled(false);
			resume.setBorderPainted(false);
			resume.setBounds(615, 325, 300, 100);
			resume.addActionListener(this);
			resume.setFocusable(false);
			
			exit = new JButton("Exit");
			exit.setIcon(peB);
			exit.setOpaque(false);
			exit.setContentAreaFilled(false);
			exit.setBorderPainted(false);
			exit.setBounds(615, 475, 300, 100);
			exit.addActionListener(this);
			exit.setFocusable(false);
			
			setLayout(null);
			add(resume);
			add(exit);
		}
		
		//resume to current game or stop game
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();	
			if(command.equals("Resume"))
			{
				if(!win1) cardLayout.show(gp, "Game1");
				if(win1 && !win2) cardLayout.show(gp, "Game2");
				if(win1 && win2) cardLayout.show(gp, "Game3");
			}
			if(command.equals("Exit")) cardLayout.show(gp, "Win");
			
		}
		
		//draw background and pause screen graphics
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Image back = new ImageIcon("images/map1.jpg").getImage();
			if(win2) back = new ImageIcon("images/g3_bg2.jpeg").getImage();
			else if (win1) back = new ImageIcon("images/g2_b1.jpeg").getImage();
			else back = new ImageIcon("images/map1.jpg").getImage();
			Image pause = new ImageIcon("images/pauseScreen.png").getImage();
			
			if(win2) g.drawImage(back,0, 50, 2005, 715,null);
			else if(win1) g.drawImage(back, 0,-20,1800,1000, null);
			else g.drawImage(back,0, 50, 2005, 715,null);
			g.drawImage(pause,460,100,600,650,null);
			
			g.fillRect(0,0,2000,65);
			g.fillRect(0,752,2000,80);
		}
		
	}
	
	//When any of 3 games end, display results
	class Win extends JPanel implements ActionListener
	{
		JLabel money;
		
		//make money label and jbuttons to go back
		public Win()
		{
			ImageIcon dhB = new ImageIcon("images/doneHome.png");
			Image img = dhB.getImage() ;  
			Image newimg = img.getScaledInstance(725, 800,  java.awt.Image.SCALE_SMOOTH ) ;  
			dhB = new ImageIcon( newimg );
			
			ImageIcon dbB = new ImageIcon("images/doneBack.png");
			img = dbB.getImage() ;  
			newimg = img.getScaledInstance(725, 800,  java.awt.Image.SCALE_SMOOTH ) ;  
			dbB = new ImageIcon( newimg );
			
			JButton home = new JButton("Home");
			home.setOpaque(false);
			home.setContentAreaFilled(false);
			home.setBorderPainted(false);
			home.setIcon(dhB);
			home.setBounds(560,575,175,150);
			home.addActionListener(this);
			
			JButton back = new JButton("Back");
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.setIcon(dbB);
			back.setBounds(780,575,175,	150);
			back.addActionListener(this);
			
			money = new JLabel(""+(score1/5));
			money.setOpaque(false);
			money.setFont(winMoney);
			money.setForeground((new Color(210, 152, 73)));
			money.setBounds(670, 445, 200, 100);
			
			setLayout(null);
			add(home);
			add(back);
			add(money);
			
		}
		
		//go back to games or front page
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();	
			if(command.equals("Home")) cardLayout.first(gp);
			if(command.equals("Back")) cardLayout.show(gp, "Games");
			
		}
		
		//make background and win board graphics
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Image back = new ImageIcon("images/map1.jpg").getImage();
			Image win = new ImageIcon("images/oneStar.png").getImage();
			
			if(win1)
			{ 
				back = new ImageIcon("images/map1.jpg").getImage();
				if(score1 >= 750) win = new ImageIcon("images/twoStars.png").getImage();
				if(score1 >= 1250) win = new ImageIcon("images/threeStars.png").getImage();
				money.setText(""+(score1/5));
				g.drawImage(back, 0, 0, 2005, 880,null);
			}
			if(win2)
			{ 
				back = new ImageIcon("images/g2_b1.jpeg").getImage();
				if(score2 >= 35) win = new ImageIcon("images/twoStars.png").getImage();
				if(score2 >= 45) win = new ImageIcon("images/threeStars.png").getImage();
				money.setText(""+(score2*8));
				g.drawImage(back,0,-20,1800,1000,null);
			}
			if(win3)
			{ 
				back = new ImageIcon("images/g3_bg2.jpeg").getImage();
				if(score2 >= 750) win = new ImageIcon("images/twoStars.png").getImage();
				if(score2 >= 1250) win = new ImageIcon("images/threeStars.png").getImage();
				money.setText(""+(score3/5));
				g.drawImage(back,0,-20,1800,1000,null);
			}
			
			g.drawImage(win, 385, 25, 750, 800, null);
			g.fillRect(0,0,2000,65);
			g.fillRect(0,752,2000,80);
		}
		
	}
	
	//Control Player's movement and location on intro page
	class PlayerIntro
	{
		Intro panel;
		int xC, yC, wC, hC, deathLength;
		double xspeed, yspeed;
		Rectangle charBox;
		boolean up, right, left, faceL, shift, dead;
		boolean idle = true;
		long wait = 0;
		
		//set Player's width, length, and location
		//add rectangle as player's hitbox
		public PlayerIntro(int x, int y, Intro panel)
		{
			this.panel = panel;
			xC = x;
			yC = y;
			wC = 35;
			hC = 200;
			charBox = new Rectangle(x, y, wC, hC);
		}
		
		//change the speeds, velocity of the jump, and direction of the
		//player depending on what the user inputs
		public void set()
		{
			//increase/decrease the speed
			if(left&&right || !left && !right) xspeed *=0.9;
			else if(left&&!right){
				if(shift)xspeed-=6;
				else xspeed-=2;}
			else if(!left&&right){
				if(shift)xspeed+=6;
				else xspeed+=2;}
			
			//make sure the speed is controlled and doesn't go out of
			//bounds
			if(xspeed > 0 && xspeed < 0.75) xspeed = 0;
			if(xspeed < 0 && xspeed > -0.75) xspeed = 0;
			
			if(xspeed > 7) xspeed = 7;
			if(xspeed < -7) xspeed = -7;
			
			//make sure that when jumping, player comes down again
			//check if player hit box intersects with obstacles
			if(up)
			{
				charBox.y++;
				for(Objects object: panel.objects)
				{
					if(object.hitBox.intersects(charBox)) {
						if(shift)yspeed-=9;
						else yspeed=-7;}
				}
				for(Objects obstacle: panel.obstacles)
				{
					if(obstacle.hitBox.intersects(charBox)) {
						if(shift)yspeed-=9;
						else yspeed=-7;
						dead = true;
						idle = false;
					}
				}
				charBox.y--;
			}
			
			//make sure the player doesn't pass through obstacles 
			//or objects both vertically and horizontally.
			charBox.x+=xspeed;
			for(Objects object: panel.objects)
			{
				if(charBox.intersects(object.hitBox))
				{
					charBox.x -= xspeed;
					while(!object.hitBox.intersects(charBox)) charBox.x+= Math.signum(xspeed);
					charBox.x -= Math.signum(xspeed);
					xspeed = 0;
					xC = charBox.x;
				}
			}
			for(Objects obstacle: panel.obstacles)
			{
				if(charBox.intersects(obstacle.hitBox))
				{
					charBox.x -= xspeed;
					while(!obstacle.hitBox.intersects(charBox)) charBox.x+= Math.signum(xspeed);
					charBox.x -= Math.signum(xspeed);
					xspeed = 0;
					xC = charBox.x;
					dead = true;
					idle= false;
				}
			}
			
			charBox.y+=yspeed;
			for(Objects object: panel.objects)
			{
				if(charBox.intersects(object.hitBox))
				{
					charBox.y -= yspeed;
					while(!object.hitBox.intersects(charBox)) charBox.y+= Math.signum(yspeed);
					charBox.y -= Math.signum(yspeed);
					yspeed = 0;
					yC = charBox.y;
				}
			}
			for(Objects obstacle: panel.obstacles)
			{
				if(charBox.intersects(obstacle.hitBox))
				{
					charBox.y -= yspeed;
					while(!obstacle.hitBox.intersects(charBox)) charBox.y+= Math.signum(yspeed);
					charBox.y -= Math.signum(yspeed);
					yspeed = 0;
					yC = charBox.y;
					dead = true;
					idle = false;
				}
			}
			
			yspeed+=0.3;
			
			xC+=xspeed;
			yC+=yspeed;
			
			charBox.x = xC;
			charBox.y = yC;
		}
		
		//draw the character depending on what position it's in
		public void draw(Graphics2D g2d)
		{
			Image charac = new ImageIcon("gifs/girlR_dead.gif").getImage();
			if(red)
			{
				if(idle)
				{
					if(faceL) charac = new ImageIcon("gifs/girlR_idle2.gif").getImage();
					else charac = new ImageIcon("gifs/girlR_idle.gif").getImage();
					if(shift){
						if(left) charac = new ImageIcon("gifs/girlR_run2.gif").getImage();
						if(right) charac = new ImageIcon("gifs/girlR_run.gif").getImage();
					}
					else{
						if(left) charac = new ImageIcon("gifs/girlR_walk2.gif").getImage();
						if(right) charac = new ImageIcon("gifs/girlR_walk.gif").getImage();
					}
					if(up) charac = new ImageIcon("gifs/girlR_jump.gif").getImage();
					if(up && faceL) charac = new ImageIcon("gifs/girlR_jump2.gif").getImage();
				}
				else{
					if(dead) 
					{
						if(left) charac = new ImageIcon("gifs/girlR_dead2.gif").getImage();
						if(right) charac = new ImageIcon("gifs/girlR_dead.gif").getImage();
						deathLength++;
						wait++;
					}
					if(deathLength == 121){ 
						dead = false; deathLength = 0; idle = true; 
						if(faceL) xC+=300;
						else xC-=300;}
				}
			}
			else
			{
				if(idle)
				{
					if(faceL) charac = new ImageIcon("gifs/girl_idle2.gif").getImage();
					else charac = new ImageIcon("gifs/girl_idle.gif").getImage();
					if(shift){
						if(left) charac = new ImageIcon("gifs/girl_run2.gif").getImage();
						if(right) charac = new ImageIcon("gifs/girl_run.gif").getImage();
					}
					else{
						if(left) charac = new ImageIcon("gifs/girl_walk2.gif").getImage();
						if(right) charac = new ImageIcon("gifs/girl_walk.gif").getImage();
					}
					if(up) charac = new ImageIcon("gifs/girl_jump.gif").getImage();
					if(up && faceL) charac = new ImageIcon("gifs/girl_jump2.gif").getImage();
				}
				else{
					if(dead) 
					{
						if(left) charac = new ImageIcon("gifs/girl_dead2.gif").getImage();
						if(right) charac = new ImageIcon("gifs/girl_dead.gif").getImage();
						deathLength++;
						wait++;
					}
					if(deathLength == 121){ 
						dead = false; deathLength = 0; idle = true; 
						if(faceL) xC+=300;
						else xC-=300;}
				}
			}
			if(yC > 700) yC=600;
			if(dead) g2d.drawImage(charac, xC, yC+40, wC+230, hC, null);
			else g2d.drawImage(charac, xC, yC, wC+150, hC, null);
			//g2d.fillRect(xC, yC, wC, hC);
		}
	}
	
	//Control Player's movement and location in first game
	class PlayerGame1
	{
		Game1 panel;
		int xC, yC, wC, hC, deathLength, deaths;
		double xspeed, yspeed;
		Rectangle charBox;
		boolean up, down, faceL, shift, dead, death;
		boolean idle = true;
		long wait = 0;
		int hitLength;
		
		//set Player's width, length, and location
		//add rectangle as player's hitbox
		public PlayerGame1(int x, int y, Game1 panel)
		{
			this.panel = panel;
			xC = x;
			yC = y;
			wC = 100;
			hC = 300;
			charBox = new Rectangle(x, y, wC, hC);
		}
		
		//makes sure that the player dies when hitting the obstacle
		public void set()
		{
			//make sure that when jumping, player comes down again
			//check if player hit box intersects with obstacles
			if(up)
			{
				charBox.y++;
				for(Objects2 object: panel.objects)
				{
					if(object.hitBox.intersects(charBox)) {
						if(shift)yspeed-=9;
						else yspeed=-7;}
				}
				for(Objects2 obstacle: panel.obstacles)
				{
					if(obstacle.hitBox.intersects(charBox)) {
						if(shift)yspeed-=17;
						else yspeed=-13;
						idle = false;
					}
				}
				charBox.y--;
			}
			
			//make sure the player doesn't pass through obstacles 
			//or objects both vertically and horizontally.
			charBox.x+=xspeed;
			for(Objects2 object: panel.objects)
			{
				if(charBox.intersects(object.hitBox))
				{
					charBox.x -= xspeed;
					while(!object.hitBox.intersects(charBox)) charBox.x+= Math.signum(xspeed);
					charBox.x -= Math.signum(xspeed);
					xspeed = 0;
					xC = charBox.x;
				}
			}
			
			
			for(Objects2 obstacle: panel.obstacles)
			{
				if(charBox.intersects(obstacle.hitBox))
				{
					death = true;
					idle= false;
				}
			}
			
			charBox.y+=yspeed;
			for(Objects2 object: panel.objects)
			{
				if(charBox.intersects(object.hitBox))
				{
					charBox.y -= yspeed;
					while(!object.hitBox.intersects(charBox)) charBox.y+= Math.signum(yspeed);
					charBox.y -= Math.signum(yspeed);
					yspeed = 0;
					yC = charBox.y;
				}
			}
			
			for(Objects2 obstacle: panel.obstacles)
			{
				if(charBox.intersects(obstacle.hitBox))
				{
					charBox.y -= yspeed;
					while(!obstacle.hitBox.intersects(charBox)) charBox.y+= Math.signum(yspeed);
					charBox.y -= Math.signum(yspeed);
					yspeed = 0;
					yC = charBox.y;
					death = true;
					idle = false;
				}
			}
			
			yspeed+=0.3;
			
			xC+=xspeed;
			yC+=yspeed;
			
			charBox.x = xC;
			charBox.y = yC;
			if(!up){if(yC < 450) yC = 450;}
			if(yC < 300) yC = 450;
		}
		
		//draw the character depending on what position it's in
		public void draw(Graphics2D g2d)
		{
			Image charac = new ImageIcon("gifs/girl_walk.gif").getImage();
			if(shift) charac = new ImageIcon("gifs/girl_run.gif").getImage(); 
			if(up) charac = new ImageIcon("gifs/girl_jump.gif").getImage();
			if(down)
			{ 
				charac = new ImageIcon("images/girl_slide.png").getImage();
				wC = 300;
				hC = 150;
				yC = 600;
				charBox.y = 600;
			}
			else
			{
				wC = 100;
				hC = 300;
			}
			if(deaths >= 4)
			{ 
				if(!win1) currency += (score1/5);
				dead = true; win1 = true;
			}
			if(death)
			{
				hitLength++;
				if(hitLength == 200)
				{
					hitLength = 0;
					death = false;
					deaths++;
				}
			}
			if(dead) 
			{
				charac = new ImageIcon("gifs/girlG_dead.gif").getImage();
				deathLength++;
				wait++;
				//yC-=5;
				//charBox.y = yC;
			}
			if(deathLength == 121)
			{ 
				deaths = 0; dead = false; 
				deathLength = 0; idle = true;
				
				le.organize(1);
				cardLayout.show(gp, "Win"); 
			}
			
			if(yC > 1200){death = true; yC=450;}
			if(yC < 300) yC = 450;
			if(dead) g2d.drawImage(charac, xC, yC+40, wC+230, hC, null);
			else g2d.drawImage(charac, xC, yC, wC+150, hC, null);
			
			if(death &&deaths>0 && ((hitLength >= 0 && hitLength <= 25)||(hitLength >= 50 && hitLength <= 75) || (hitLength >= 100 && hitLength <= 125)||(hitLength >= 150 && hitLength <= 175)))
			{
				g2d.setColor(new Color(154, 42, 42, 127));
				g2d.fillRect(0,65,2000,695);
			}
			
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0,760,2000,80);
		//	g2d.fillRect(charBox.x, charBox.y, wC, hC);
			set();
		}
	}
	
	//Control Player's movement and location in third game
	class PlayerGame3
	{
		Game3 panel;
		int xC, yC, wC, hC, deathLength, deaths;
		double xspeed, yspeed;
		Rectangle charBox;
		boolean up, down, faceL, shift, dead, death;
		boolean idle = true;
		long wait = 0;
		int hitLength;
		
		//set Player's width, length, and location
		//add rectangle as player's hitbox
		public PlayerGame3(int x, int y, Game3 panel)
		{
			this.panel = panel;
			xC = x;
			yC = y;
			wC = 150;
			hC = 350;
			charBox = new Rectangle(x, y, wC, hC);
		}
		
		//makes sure that the player dies when hitting the obstacle
		public void set()
		{
			//make sure that when jumping, player comes down again
			//check if player hit box intersects with obstacles
			if(up)
			{
				charBox.y++;
				for(Objects4 object: panel.objects)
				{
					if(object.hitBox.intersects(charBox)) {
						if(shift)yspeed-=9;
						else yspeed=-7;}
				}
				for(Objects4 obstacle: panel.obstacles)
				{
					if(obstacle.hitBox.intersects(charBox)) {
						if(shift)yspeed-=17;
						else yspeed=-13;
						idle = false;
					}
				}
				charBox.y--;
			}
			
			//make sure the player doesn't pass through obstacles 
			//or objects both vertically and horizontally.
			charBox.x+=xspeed;
			for(Objects4 object: panel.objects)
			{
				if(charBox.intersects(object.hitBox))
				{
					charBox.x -= xspeed;
					while(!object.hitBox.intersects(charBox)) charBox.x+= Math.signum(xspeed);
					charBox.x -= Math.signum(xspeed);
					xspeed = 0;
					xC = charBox.x;
				}
			}
			
			
			for(Objects4 obstacle: panel.obstacles)
			{
				if(charBox.intersects(obstacle.hitBox))
				{
					death = true;
					idle= false;
				}
			}
			
			charBox.y+=yspeed;
			for(Objects4 object: panel.objects)
			{
				if(charBox.intersects(object.hitBox))
				{
					charBox.y -= yspeed;
					while(!object.hitBox.intersects(charBox)) charBox.y+= Math.signum(yspeed);
					charBox.y -= Math.signum(yspeed);
					yspeed = 0;
					yC = charBox.y;
				}
			}
			
			for(Objects4 obstacle: panel.obstacles)
			{
				if(charBox.intersects(obstacle.hitBox))
				{
					charBox.y -= yspeed;
					while(!obstacle.hitBox.intersects(charBox)) charBox.y+= Math.signum(yspeed);
					charBox.y -= Math.signum(yspeed);
					yspeed = 0;
					yC = charBox.y;
					death = true;
					idle = false;
				}
			}
			
			yspeed+=0.3;
			
			xC+=xspeed;
			yC+=yspeed;
			
			charBox.x = xC;
			charBox.y = yC;
			if(up){if(yC < 250) yC = 400;}
			else{if(yC < 300) yC = 400;}
		}
		
		//draw the character depending on what position it's in
		public void draw(Graphics2D g2d)
		{
			Image bullet = new ImageIcon("images/Bullet2.png").getImage();
			Image charac = new ImageIcon("gifs/girl_walk3.gif").getImage();
			if(shift) charac = new ImageIcon("gifs/girl_run3.gif").getImage(); 
			if(up) charac = new ImageIcon("gifs/girl_jump3.gif").getImage();
			if(down)
			{ 
				charac = new ImageIcon("images/girl_slide3.png").getImage();
				wC = 300;
				hC = 150;
				yC = 600;
				charBox.y = yC;
			}
			else if(!down && !up)
			{
				wC = 150;
				hC = 350;
				yC = 400;
				charBox.y = yC;
			}
			else
			{
				wC = 150;
				hC = 350;
			}
			
			if(deaths >= 4)
			{ 
				if(!win3) 
				{
					score3 += points3*7;
					currency += (score3/5);
					le.organize(3);
				}
				dead = true; win3 = true;
			}
			if(death)
			{
				hitLength++;
				if(hitLength == 200)
				{
					hitLength = 0;
					death = false;
					deaths++;
				}
			}
			if(dead) 
			{
				charac = new ImageIcon("gifs/girlG_dead.gif").getImage();
				deathLength++;
				wait++;
			}
			if(deathLength == 121)
			{ 
				deaths = 0; dead = false; 
				deathLength = 0; idle = true;
				cardLayout.show(gp, "Win"); 
			}
			
			if(!panel.bull2.click)g2d.drawImage(bullet, panel.bull2.xC-1000, panel.bull2.yC, panel.bull2.wC, panel.bull2.hC, null);
			else g2d.drawImage(bullet, panel.bull2.xC, panel.bull2.yC, panel.bull2.wC, panel.bull2.hC, null);
			
			if(yC > 1200){death = true; yC=400;}
			if(dead) g2d.drawImage(charac, xC, yC+40, wC+230, hC, null);
			else g2d.drawImage(charac, xC, yC, wC+150, hC, null);
			
			if(death &&deaths>0 && ((hitLength >= 0 && hitLength <= 25)||(hitLength >= 50 && hitLength <= 75) || (hitLength >= 100 && hitLength <= 125)||(hitLength >= 150 && hitLength <= 175)))
			{
				g2d.setColor(new Color(154, 42, 42, 127));
				g2d.fillRect(0,65,2000,695);
			}
			
			
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0,760,2000,80);
			//g2d.fillRect(charBox.x, charBox.y, wC, hC);
			set();
		}
	}
	
	//control the bullet location and hit 
	class Bullet
	{
		Game2 panel;
		int xC, yC, wC, hC;
		boolean click, right, left;
		boolean[] dead = new boolean[6];
		boolean[] de = new boolean[6];
		int[] time = new int[6];
		
		Rectangle bullBox;
		
		//create hitbox for bullet
		public Bullet(Game2 panel, int x, int y)
		{
			this.panel = panel;
			xC = x;
			yC = y;
			wC = 100;
			hC = 125;
			bullBox = new Rectangle(x, y, wC, hC);
		}
		
		//check for collision between bullet and squirrel
		public void set()
		{	
			int count = 0;
			if(panel.swap)
			{
				for(Objects3 obstacle: panel.obstaclesBlow)
				{
					if(bullBox.intersects(obstacle.hitBox) && panel.sqY[count] != 275)
					{
						panel.blow[count] = true;
						click = false;
					}
					count++;
				}
			}
			
			else
			{
				for(Objects3 obstacle: panel.obstacles)
				{
					if(bullBox.intersects(obstacle.hitBox))
					{
						if(panel.sqY[count] == 275) panel.speed[count]+=2;
						if(!first) first = true;
						dead[count] = true;
						click = false;
						if(panel.sqY[count] == 275) score2++;
					}
					count++;
				}
				
				count = 0;
			}
			
			for(int i = 0; i < 6; i++)
			{
				if(dead[i])
				{
					if(panel.sqY[i] >= 500)
					{
						time[i]++;
						if(time[i] >=100)
						{
							dead[i] = false;
							de[i] = true;
							panel.blowDone[i] = false;
							panel.sqY[i] = 275;
							time[i] = 0;
						}
					}
					else panel.sqY[i]+=10;
				}
			}
			
			if(click) 
			{
				if(panel.swap)
				{
					if(panel.mY <= yC && panel.mY > 0)
					{
						yC-=10;
						bullBox.y = yC+110;
					}
					else
					{
						yC = 600;
						bullBox.y = yC+110;
						xC = panel.x1;
						bullBox.x = xC;
						click = false;
					}
				}
				else
				{
					if(panel.mY <= yC && panel.mY > 0)
					{
						yC-=10;
						bullBox.y = yC+10;
					}
					else
					{
						yC = 600;
						bullBox.y = yC+10;
						xC = panel.x1;
						bullBox.x = xC;
						click = false;
					}
				}
			}
			if(!click)
			{
				if(panel.swap)
				{
					yC = 600;
					bullBox.y = yC+110;
				}
				else
				{
					yC = 600;
					bullBox.y = yC+10;
				}
				xC = panel.x1;
				bullBox.x = xC;
				if(left)
				{	
					panel.x1-=5;
					xC = panel.x1;
					bullBox.x = xC;
				}
				if(right)
				{
					panel.x1+=5;
					xC = panel.x1;
					bullBox.x = xC;
				}
			}
			if(first) panel.timer--;
			if(left) panel.x1-=5;
			if(right) panel.x1+=5;
		}
		
		//if needed, draw the hitbox around the bullet
		public void draw(Graphics g2d)
		{
			g2d.setColor(Color.YELLOW);
			//g2d.drawRect(bullBox.x, bullBox.y, wC, hC);
		}
	}
	
	//control the bullet location and hit in the second game
	class Bullet2
	{
		Game3 panel;
		int xC, yC, wC, hC;
		boolean click;
		int[] time = new int[6];
		
		Rectangle bullBox;
		
		//create hitbox for bullet
		public Bullet2(Game3 panel, int x, int y)
		{
			this.panel = panel;
			xC = x;
			yC = y;
			wC = 25;
			hC = 20;
			bullBox = new Rectangle(x, y, wC, hC);
		}
		
		//check for collision between bullet and squirrel
		public void set()
		{	
			int count = 0;
			for(Objects4 obstacle: panel.obstacles)
			{
				if(bullBox.intersects(obstacle.hitBox) && count == 0)
				{
					if(panel.oX[0] > 900)
					{
						click = false;
						panel.die = true;
						points3++;
					}
				}
				count++;
			}
			
			if(click) 
			{
				if(panel.mX >= xC && panel.mX < 1800)
				{
					yC =  panel.player.yC+225;
					bullBox.y = yC;
					xC+=10;
					bullBox.x = xC+10;
				}
				else
				{
					yC =  panel.player.yC+225;
					bullBox.y = yC;
					xC = panel.player.xC+150;
					bullBox.x = xC;
					click = false;
				}
			}
			if(!click)
			{
				yC =  panel.player.yC+225;
				xC = panel.player.xC+150;
				bullBox.y = yC;
				bullBox.x = xC;
			}
		}
		
		//if needed, draw the hitbox around the bullet
		public void draw(Graphics g2d)
		{
			g2d.setColor(Color.YELLOW);
			//g2d.drawRect(bullBox.x, bullBox.y, wC, hC);
		}
	}
	
	//create objects/obstacles intro
	class Objects
	{
		int xo, yo, wo, ho;
		Rectangle hitBox;
		
		//identify the size and location of an object
		public Objects(int x, int y, int width, int height)
		{
			xo = x;
			yo = y;
			wo = width;
			ho = height;
			hitBox = new Rectangle(xo, yo, wo, ho);
		}
		
		//draw object if necessary
		public void draw(Graphics g2d)
		{
			g2d.setColor(Color.YELLOW);
			//g2d.drawRect(xo+70, yo, wo, ho);
		}
	}	
	
	//create objects/obstacles game1
	class Objects2
	{
		int xo, yo, wo, ho;
		Rectangle hitBox;
		
		//identify the size and location of an object
		public Objects2(int x, int y, int width, int height)
		{
			xo = x;
			yo = y;
			wo = width;
			ho = height;
			hitBox = new Rectangle(xo, yo, wo, ho);
		}
		
		//draw object if necessary
		public void draw(Graphics g2d)
		{
			g2d.setColor(Color.YELLOW);
			//g2d.drawRect(xo+70, yo, wo, ho);
		}
	}	
	
	//create squirrel hitbox game2
	class Objects3
	{
		int xo, yo, wo, ho;
		Rectangle hitBox;
		
		//identify the size and location of an object
		public Objects3(int x, int y, int width, int height)
		{
			xo = x;
			yo = y;
			wo = width;
			ho = height;
			hitBox = new Rectangle(xo, yo, wo, ho);
		}
		
		//draw object if necessary
		public void draw(Graphics g2d)
		{
			g2d.setColor(Color.YELLOW);
			g2d.drawRect(xo, yo, wo, ho);
		}
	}	
	
	//create objects/obstacles game3
	class Objects4
	{
		int xo, yo, wo, ho;
		Rectangle hitBox;
		
		//identify the size and location of an object
		public Objects4(int x, int y, int width, int height)
		{
			xo = x;
			yo = y;
			wo = width;
			ho = height;
			hitBox = new Rectangle(xo, yo, wo, ho);
		}
		
		//draw object if necessary
		public void draw(Graphics g2d)
		{
			g2d.setColor(Color.YELLOW);
		//	g2d.drawRect(xo, yo, wo, ho);
		}
	}	
}
