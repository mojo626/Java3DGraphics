package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Frame extends JPanel {

    public Frame() {
        createAndShowGui();
    }

   @Override
   protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = new BufferedImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);


        for (int x = 0; x < Constants.WINDOW_WIDTH; x ++) {
            for (int y = 0; y < Constants.WINDOW_HEIGHT; y++) {
                Color col = new Color((int)(255 * (double) x / Constants.WINDOW_WIDTH), (int)(255 * (double) y / Constants.WINDOW_WIDTH), 0);
                img.setRGB(x, y, new Color(0, 255, 0).getRGB());
            }
        }

        Utils.drawTriangle(img, new Vector2(100.0, 200.0), new Vector2(200.0, 300.0), new Vector2(0.0, 0.0));

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, null, null);

   }


   @Override
   public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
   }

   // create the GUI explicitly on the Swing event thread
   private void createAndShowGui() {

        JFrame frame = new JFrame("DrawRect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        //frame.setResizable(false);
        frame.setTitle("Simulation");

        
        Timer timer = new Timer(Constants.LOOP_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint(); // Request a repaint (calls paintComponent later)
            }
        });
        timer.start(); // Start the "draw loop"
   }
}
