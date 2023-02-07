package solitaire.model;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Card extends JPanel {
	public int value;
	public Card suit;
	private BufferedImage image;
	private BufferedImage backImage;
	boolean isReversed;
	Point positionOffset;
	
	public enum Suit {
		
	}
	
	public Card(int value, Suit suit) {
		
	}

}
