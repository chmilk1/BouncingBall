package sec;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Controller extends JFrame {
	JPanel buttonPanel = new JPanel();
	JButton runButton = new JButton("Resume");
	JButton stopButton = new JButton("Pause");
	Timer timer;
	static Controller c;

	Model model = new Model();
	View view = new View(model); // View must know about Model

	public static void main(String[] args) {
		c = new Controller();
		c.init();
		c.setSize(800, 800);
		c.setVisible(true);
	}

	public void init() {
		layOutComponents();
		attachListenersToComponents();
		// Connect model and view
		model.addObserver(view);
	}

	private void layOutComponents() {
		setLayout(new BorderLayout());
		this.add(BorderLayout.SOUTH, buttonPanel);
		buttonPanel.add(runButton);
		buttonPanel.add(stopButton);
		stopButton.setEnabled(false);
		this.add(BorderLayout.CENTER, view);
	}

	private void attachListenersToComponents() {
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				model.bounce();
				runButton.setEnabled(false);
				stopButton.setEnabled(true);
				timer = new Timer(true);
				timer.schedule(new Strobe(), 0, 40); // 25 times a second
			}
		});
		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				double slope = Graphing.getSlope(e.getX(), e.getY() - 50, model.getxLimit() - 20,
						model.getyLimit() - 120);
				int bx = model.getX();
				int by = model.getY();
				System.out.println(bx + " " + by + " " + bx * slope);
				if (bx * slope >= by - 20 && bx * slope < by + 45) {
					model.hit();
					model.hits.add(new Point(e.getX(), e.getY()));
				}
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
		});
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				runButton.setEnabled(true);
				stopButton.setEnabled(false);
				timer.cancel();
			}
		});
	}

	private class Strobe extends TimerTask {
		public void run() {
			model.setLimits(view.getWidth(), view.getHeight());
			model.makeOneStep();
		}
	}

}