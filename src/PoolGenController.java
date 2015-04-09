/**
 * Created by Serge on 08/04/2015.
 */

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class PoolGenController {
    private PoolGenView view;
    private PoolGenModel model;

    //constructor
    public PoolGenController(PoolGenView v, PoolGenModel m) {
        view = v;
        model = m;

        Listener listener = new Listener();

    }

    class Listener implements ActionListener(ActionEvent e) {
        //calls for buttons
        //Must check which button is pressed
        String buttonPressedIconString = ((JButton) e.getSource()).getIcon().toString(); //gets image name of the button
        //Generate button
        if (buttonPressedIconString.contains(PoolGenView.generateButtonImageName)) {

        } else if (buttonPressedIconString.contains(PoolGenView.)

    }
}
