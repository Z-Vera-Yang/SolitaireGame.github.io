package solitaire.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import solitaire.controller.Controller;
import solitaire.model.Card;
import solitaire.model.CardDeck;
import solitaire.model.CardPile;

public class View extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
	
	public class menuOption {
		public String name;
		public Integer shorcut = 0;
		
		public menuOption(String name, Integer shorcut) {
			this.name = name;
			this.shorcut = shorcut;
		}
	}
	
	public class JPanelWithBackground extends JPanel {
		  private Image backgroundImage;
		  public JPanelWithBackground(String fileName) throws IOException {
			  backgroundImage = ImageIO.read(new File(fileName));
		  }
		  public void paintComponent(Graphics graphic) {
		    super.paintComponent(graphic);
		    graphic.drawImage(backgroundImage, 0, 0, this);
		  }
	}

	private JMenuBar menuBar;	
	Map<String, String> displayText;
	JPanel gameArea;
	JPanel columns;
	JPanel topColumns;
	JLayeredPane lp;
	Controller controller;
	CardPile tempPile;
	Point mouseOffset;
	
	public View (Controller controller) {			
		this.controller = controller;	
		
		displayText = new HashMap<String, String>();		
		displayText.put("File", "File");
		displayText.put("New", "New");
		displayText.put("Save", "Save");
		displayText.put("Load", "Load");
		displayText.put("Exit", "Exit");
		
		setTitle("Solitaire");
		setSize(1400, 900);
		
		try {
			setContentPane((new JPanelWithBackground("./images/background.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		
		gameArea = new JPanel();
		gameArea.setOpaque(false);
		gameArea.setLayout(new BoxLayout(gameArea, BoxLayout.PAGE_AXIS));
		

		setLocationRelativeTo(null);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    

	    menuBar = new JMenuBar();		
		JMenu FileMenu = new JMenu("File");
		FileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(FileMenu);		
		menuOption[] fileOptions = new menuOption[] {
			new menuOption(displayText.get("New"), KeyEvent.VK_N),
			new menuOption(displayText.get("Save"), KeyEvent.VK_S),
			new menuOption(displayText.get("Load"), KeyEvent.VK_L),
			new menuOption(displayText.get("Exit"), KeyEvent.VK_X)
		};				
		for(menuOption option: fileOptions) {	
			JMenuItem opt = new JMenuItem(option.name);
			if(option.shorcut != 0) opt.setMnemonic(option.shorcut);
			
			opt.addActionListener(this);
			FileMenu.add(opt);				
		}		
		setJMenuBar(menuBar);
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		flow.setAlignOnBaseline(true);
		
		columns = new JPanel();
		columns.setOpaque(false);
		columns.setLayout(flow);
		columns.setMinimumSize(new Dimension(300, 1200));
		
		FlowLayout topFlow = new FlowLayout(FlowLayout.LEFT);
		topFlow.setAlignOnBaseline(true);
		
		topColumns = new JPanel();
		topColumns.setOpaque(false);
		topColumns.setLayout(topFlow);
		
		
		gameArea.add(topColumns);
		gameArea.add(columns);
		
		add(gameArea);
		
		lp = getLayeredPane();
		setVisible(true);
		
		mouseOffset = new Point(0, 0);
		
		topColumns.removeAll();
		columns.removeAll();
		
		for(Card c: controller.deck.cards) {
			c.addMouseListener(this);
			c.addMouseMotionListener(this);		
		}		
		controller.setupGame();
		for(CardPile p : controller.piles) {
			columns.add(p);
		}
		
		topColumns.add(controller.drawPile);
		topColumns.add(controller.getPile);
		
		for(CardPile p : controller.finalPiles) {
			topColumns.add(p);
		}
		
		validate();
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
