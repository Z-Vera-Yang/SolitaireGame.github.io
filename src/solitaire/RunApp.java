package solitaire;

import solitaire.controller.Controller;
import solitaire.view.View;

public class RunApp {
	Controller controller;
	View view;
	
	public RunApp() {
		controller = new Controller();
		view= new View(controller);
	}	
	
	public static void main(String[] args) {
		RunApp solitaire = new RunApp();
	}

}
