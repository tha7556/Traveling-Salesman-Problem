package utility;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Creates a Display for the City Array, connecting each by the order it is in
 * @author Tyler Atkinson
 */
public class Display extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Line2D> lines;
	private double maxYValue, maxXValue, xScale, yScale;
	private City[] cities;
    private static final int PAD = 20;
    /**
     * Creates a Display for the City Array
     * @param cities The City Array
     */
    public Display(City[] cities) {
    	this.cities = cities;
    	lines = new ArrayList<Line2D>();
    }
    /**
     * Removes the Previously displayed Route and Replaces it with the new one
     * @param path The new Route to display
     */
    public void updateRoute(City[] path) {
    	
    	ArrayList<Line2D> newLines = new ArrayList<Line2D>(lines.size());
    	for(int i = 0; i < path.length; i++) {
    		double x1 = 0, x2 = 0, y1 = 0, y2 = 0;
    		x1 = path[i].getX();
			y1 = path[i].getY();
    		if(i+1 < path.length) {
    			x2 = path[i+1].getX();
    			y2 = path[i+1].getY();
    		}
    		else {
    			x2 = path[0].getX();
    			y2 = path[0].getY();
    		}
    		newLines.add(new Line2D.Double(x1, y1, x2, y2));
    	}
    	lines = newLines;
    	repaint();
    }
    /**
     * Paints the Display
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        maxYValue = Salesman.MAX_HEIGHT;
        maxXValue = Salesman.MAX_WIDTH;
        xScale = (getWidth() - 2*PAD)/maxXValue;
        yScale = (getHeight() - 2*PAD)/maxYValue;
        g2.drawLine(PAD, PAD, PAD, h-PAD);
        g2.drawLine(PAD, h-PAD, w-PAD, h-PAD);
        
        // The origin location.
        int x0 = PAD;
        int y0 = h-PAD;
        g2.setPaint(Color.red);
        for(City city : cities) {
            int x = x0 + (int)(xScale * city.getX());
            int y = y0 - (int)(yScale * city.getY());
            g2.fillOval(x-2, y-2, 4, 4);
        }
        for(Line2D line : lines) {
        	double x1 = (line.getX1()*xScale)+x0;
    		double x2 = (line.getX2()*xScale)+x0;
    		double y1 = y0-(line.getY1()*yScale);
    		double y2 = y0-(line.getY2()*yScale);
        	g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }
}