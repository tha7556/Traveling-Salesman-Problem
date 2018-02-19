package utility;

import java.awt.Graphics;

import javax.swing.JFrame;
/**
 * Provides a JFrame for the Display
 * @author Tyler Atkinson
 */
public class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	private Display display;
	/**
	 * Creates a new Window with a Display based on an array of Cities
	 * @param width The width of the Window
	 * @param height The height of the Window
	 * @param cities The Array of Cities
	 */
	public Window(int width, int height, City[] cities) {
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.display = new Display(cities);
		add(display);
		setVisible(true);
	}
	/**
	 * Returns the Display
	 * @return The Display
	 */
	public Display getDisplay() {
		return display;
	}
	/**
	 * Updates the Route being displayed
	 * @param path The City[] for the new Route
	 */
	public void updateRoute(City[] path) {
		display.updateRoute(path);
	}
	/**
	 * Paints the Window
	 */
	public void paint(Graphics g) {
		super.paint(g);
	}

}
