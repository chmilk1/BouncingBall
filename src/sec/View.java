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
        g.fillOval(model.getX(), model.getY(),
                model.BALL_SIZE, model.BALL_SIZE);
        
        
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        int slope = (mouse.y-model.getyLimit()-120)/(mouse.x-model.getxLimit()-20);  
        g.drawLine(model.getxLimit()-20,model.getyLimit()-120, model.getyLimit()-120-(slope*(model.getxLimit()-20)), 0);
        
        
        g.setColor(Color.black);
        g.fillOval(model.getxLimit()-20, model.getyLimit()-120, 20, 20);
    }

    public void update(Observable obs, Object arg) {
        repaint();
    }
}