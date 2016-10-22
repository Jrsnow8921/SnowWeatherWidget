package widget;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import javax.swing.Timer;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import net.miginfocom.swing.MigLayout;
import org.xml.sax.SAXException;

import static java.awt.Color.*;

/**
 * Created by jsnow on 10/19/2016.
 */
public class Widget extends JFrame {

    String icon_image = null;
    String temp = null;
    String sunup = null;
    String sundown = null;
    JLabel myText = new JLabel();
    JLabel mypic = new JLabel();
    JLabel sun_u = new JLabel();
    JLabel sun_d = new JLabel();
    JFrame frame = new JFrame("Weather");
    Image icon = Toolkit.getDefaultToolkit().getImage("images/partlycloudy.gif");
    MigLayout layout = new MigLayout("fillx", "[center]", "[]10[]");
    JPanel panel = new JPanel(layout);


    Timer SimpleTimer = new Timer(3600000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                Thread t_i = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    icon_image = weather.weather_pic();
                                } catch (IOException e1) {
                                e1.printStackTrace();
                                }
                            }
                        }
                );

                Thread t_t = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                    temp = weather.temp();
                            }
                        }
                );

                Thread t_u = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                sunup = weather.sunup();
                            }
                        }
                );

                Thread t_d = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                sundown = weather.sundown();
                            }
                        }
                );

                t_i.start();
                t_t.start();
                t_u.start();
                t_d.start();

                try {
                    t_i.join();
                    t_t.join();
                    t_u.join();
                    t_d.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }


                URL url = new URL(icon_image);



                ImageIcon img = new ImageIcon(url);


                myText.setText(temp);
                mypic.setIcon(img);
                sun_u.setText(sunup);
                sun_d.setText(sundown);

                panel.add(mypic, "cell 3 4");

                panel.add(myText, "cell  3 4");

                panel.add(sun_u, "cell  3 5");

                panel.add(sun_d, "cell  3 6");


            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    });

    public Widget() throws IOException, URISyntaxException {

        icon_image = weather.weather_pic();
        frame.setUndecorated(true);
        frame.setIconImage(icon);
        //frame.setLayout(new BorderLayout());
        frame.setSize(140,145);
        frame.getContentPane().add(panel).setBackground(Color.BLACK);
        temp = weather.temp();
        sunup = weather.sunup();
        sundown = weather.sundown();

        URL url = new URL(icon_image);



        ImageIcon img = new ImageIcon(url);


        myText.setText(temp);
        mypic.setIcon(img);
        sun_u.setText(sunup);
        sun_d.setText(sundown);


        myText.setForeground(Color.LIGHT_GRAY);
        mypic.setForeground(Color.LIGHT_GRAY);
        sun_u.setForeground(Color.LIGHT_GRAY);
        sun_d.setForeground(Color.LIGHT_GRAY);

        panel.add(mypic, "cell 3 4");

        panel.add(myText, "cell  3 4");

        panel.add(sun_u, "cell  3 5");

        panel.add(sun_d, "cell  3 6");


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Shape shape = new Ellipse2D.Float(0,0,140,145);
        AWTUtilities.setWindowShape(this.frame, shape);

        //runs every hour
        SimpleTimer.start();



    }





    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
        new Widget();
    }


}
