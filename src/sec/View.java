package sec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

class View extends JPanel implements Observer {
	Model model;

	View(Model model) {
		this.model = model;
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.red);
		g.fillOval(model.getX(), model.getY(), model.BALL_SIZE, model.BALL_SIZE);
		
		g.setColor(Color.gray);
		for (Point p : model.hits) {
			double slope = Graphing.getSlope(p.x, p.y - 50, model.getxLimit() - 20, model.getyLimit() - 120);
			g.drawLine(
					model.getxLimit() - 20,
					model.getyLimit() - 120,
					0,
					(int) (model.getyLimit() - 120 - (slope * (model.getxLimit() - 20))));
		}
		
		g.setColor(Color.red);
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		double slope = Graphing.getSlope(mouse.x, mouse.y - 50, model.getxLimit() - 20, model.getyLimit() - 120);
		g.drawLine(model.getxLimit() - 20, model.getyLimit() - 120, 0,
				(int) (model.getyLimit() - 120 - (slope * (model.getxLimit() - 20))));

		g.setColor(Color.black);
		g.fillOval(model.getxLimit() - 20, model.getyLimit() - 120, 20, 20);
		g.drawString(""+model.getScore(), model.getxLimit()-100, 100);
	}

	public void update(Observable obs, Object arg) {
		repaint();
	}
}// 380 480