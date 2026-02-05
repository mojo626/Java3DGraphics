package src;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Utils {
    
    static ArrayList<Integer> interpolate(int a1, int b1, int a2, int b2) {
        ArrayList<Integer> list = new ArrayList<>();
        double m = ((double)(b2 - b1) / (a2 - a1));
        for (double a = a1; a <= a2; a ++) {
            
            int b = (int)Math.round(m * a + b1 - m * a1);


            list.add(b);
        }
        
        return list;
    }

    public static void drawLine(BufferedImage img, Vector2 p1, Vector2 p2) {
        double a = ((double)(p2.y - p1.y) / (p2.x - p1.x));

        if (Math.abs(a) > 1.0) {
            if (p2.y - p1.y < 0) {
                Vector2 temp = p2;
                p2 = p1;
                p1 = temp;
            }

            ArrayList<Integer> xs = interpolate((int)Math.round(p1.y), (int)Math.round(p1.x), (int)Math.round(p2.y), (int)Math.round(p2.x));

            for (double y = p1.y; y <= p2.y; y ++) {
            
                int x = xs.get((int)(y - p1.y));


                img.setRGB(x, (int)Math.round(y), new Color(0, 0, 0).getRGB());
            }
        } else {
            if (p2.x - p1.x < 0) {
                Vector2 temp = p2;
                p2 = p1;
                p1 = temp;
            }

            ArrayList<Integer> ys = interpolate((int)Math.round(p1.x), (int)Math.round(p1.y), (int)Math.round(p2.x), (int)Math.round(p2.y));

            for (double x = p1.x; x <= p2.x; x ++) {
            
                int y = ys.get((int)(x - p1.x));


                img.setRGB((int)Math.round(x), y, new Color(0, 0, 0).getRGB());
            }
        }
        
    }

    public static void drawTriangle(BufferedImage img, Vector2 p1, Vector2 p2, Vector2 p3) {
        if (p1.y > p2.y) {
            Vector2 temp = p2;
            p2 = p1;
            p1 = temp;
        }
        if (p1.y > p3.y) {
            Vector2 temp = p3;
            p3 = p1;
            p1 = temp;
        }
        if (p2.y > p3.y) {
            Vector2 temp = p2;
            p2 = p3;
            p3 = temp;
        }

        System.out.println(p1.y + ", " + p2.y + ", " + p3.y);

        ArrayList<Integer> x13 = interpolate((int)Math.round(p1.y), (int)Math.round(p1.x), (int)Math.round(p3.y), (int)Math.round(p3.x));
        ArrayList<Integer> x12 = interpolate((int)Math.round(p1.y), (int)Math.round(p1.x), (int)Math.round(p2.y), (int)Math.round(p2.x));
        ArrayList<Integer> x23 = interpolate((int)Math.round(p2.y), (int)Math.round(p2.x), (int)Math.round(p3.y), (int)Math.round(p3.x));

        x12.removeLast();
        x12.addAll(x23);

        System.out.println(x13.size() + ". " + x12.size());


        for (double y = p1.y; y <= p3.y; y ++) {
            int x1 = x13.get((int)(y - p1.y));
            int x2 = x12.get((int)(y - p1.y));

            drawLine(img, new Vector2(x1, y), new Vector2(x2, y));
        }


        Utils.drawLine(img, p1, p2);
        Utils.drawLine(img, p2, p3);
        Utils.drawLine(img, p3, p1);
    }
}
