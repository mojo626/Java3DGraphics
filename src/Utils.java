package src;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Utils {
    

    public static void drawLine(BufferedImage img, Vector2 p1, Vector2 p2) {
        double a = ((double)(p2.y - p1.y) / (p2.x - p1.x));

        if (Math.abs(a) > 1.0) {
            if (p2.y - p1.y < 0) {
                Vector2 temp = p2;
                p2 = p1;
                p1 = temp;
            }

            for (double y = p1.y; y <= p2.y; y++) {
            
                int x = (int)(( y - (p1.y - a * p1.x)) / a );


                img.setRGB(x, (int)y, new Color(0, 0, 0).getRGB());
            }
        } else {
            if (p2.x - p1.x < 0) {
                Vector2 temp = p2;
                p2 = p1;
                p1 = temp;
            }

            for (double x = p1.x; x <= p2.x; x ++) {
            
                int y = (int)(a * x + p1.y - a * p1.x);


                img.setRGB((int)x, y, new Color(0, 0, 0).getRGB());
            }
        }
        
    }

    public static void drawTriangle(BufferedImage img, Vector2 p1, Vector2 p2, Vector2 p3) {
        Utils.drawLine(img, p1, p2);
        Utils.drawLine(img, p2, p3);
        Utils.drawLine(img, p3, p1);
    }
}
