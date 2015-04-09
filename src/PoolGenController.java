/**
 * Created by Serge on 08/04/2015.
 */

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoolGenController {
    private PoolGenView view;
    private PoolGenModel model;

    //constructor
    public PoolGenController(PoolGenView v, PoolGenModel m) {
        view = v;
        model = m;

        Listener listener = new Listener();
        view.addCalculateListener(listener); // add the listener to the view
    }

    class Listener implements ActionListener {
        //calls for buttons
        //Must check which button is pressed
        public void actionPerformed(ActionEvent e) {
            String buttonPressedIconString = ((JButton) e.getSource()).getIcon().toString(); //gets image name of the button
            if (String input = bracketSize.getText();)
            if (buttonPressedIconString.contains(PoolGenView.generateButtonImageName)) {
                //---------------------------------------write code here----------------------------------
            } else if (buttonPressedIconString.contains(PoolGenView.searchButtonImageName)) {
                //-----------------------------------write code here----------------------------------
            } else if (buttonPressedIconString.contains(PoolGenView.deleteButtonImageName)) {
                //-----------------------------------write code here----------------------------------
            }
        }//end actionPerformedMethod
        //all these functions were mandatory because if we do not have them then this class would be implementing an interface it does not have functions to
//
    }
}
