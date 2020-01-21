/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
/**
 *
 * @author vmvs0
 */
/*
 * LabelDemo.java needs one other file:
 *   images/middle.gif
 */
public class LabelIcon extends JPanel {
    public LabelIcon() {
        super(new GridLayout(3,1));  //3 rows, 1 column
        JLabel label1, label2, label3;
 
        ImageIcon icon = createImageIcon("images/middle.gif",
                                         "a pretty but meaningless splat");
 
        //Create the first label.
        label1 = new JLabel("Image and Text",
                            icon,
                            JLabel.CENTER);
        //Set the position of its text, relative to its icon:
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setHorizontalTextPosition(JLabel.CENTER);
 
        //Create the other labels.
        label2 = new JLabel("Text-Only Label");
        label3 = new JLabel(icon);
 
        //Create tool tips, for the heck of it.
        label1.setToolTipText("A label containing both image and text");
        label2.setToolTipText("A label containing only text");
        label3.setToolTipText("A label containing only an image");
 
        //Add the labels.
        add(label1);
        add(label2);
        add(label3);
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = LabelIcon.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}