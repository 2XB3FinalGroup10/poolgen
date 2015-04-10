/**
 * Created by Serge on 08/04/2015.
 */
import com.sun.org.glassfish.gmbal.ParameterNames;
import jdk.nashorn.internal.runtime.JSObjectListAdapter;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class PoolGenView extends JFrame {
    //Panels
    private JPanel mainMenu;

    //Panes
    private JScrollPane listPane;

    //Buttons
    private JButton deleteButton;
    private JButton generateButton;
    private JButton searchButton;

    //Labels
    private JLabel errorText;       //displays error messages.

    //Textfields (for user input)
    private JTextField bracketSize;
    private JTextField seededPlayers;

    //String
    private String error;

    //FileChooser
    private JFileChooser fc;

    //List
    private JList editor;

    //Image data here:
    public static final String deleteButtonImageName            = "delete1.png";
    public static final String deleteButtonImageNamePressed     = "delete2.png";
    public static final String generateButtonImageName          = "generate1.png";
    public static final String generateButtonImageNamePressed   = "generate2.png";
    public static final String searchButtonImageName            = "search1.png";
    public static final String searchButtonImageNamePressed     = "search2.png";

    //this is the size of the buttons on the screen
    private static final int buttonWidth = 160;     //button width
    private static final int buttonHeight = 41;     //stores height

    //Screen size
    private static final int initialScreenHeight = 800;
    private static final int initialScreenWidth  = 500;

    //constructors
    public PoolGenView(){ this(initialScreenWidth, initialScreenHeight); }
    public PoolGenView(int width, int height){
        //gets rid of java's default positioning system.  We can edit the position of the buttons, textlabels, scrollpanes, etc..
        this.getContentPane().setLayout(null);

        //This will setup what the user will be able to see.
        //Additional JPanels should be set to false.
        setupGUI(width, height);                   //sets up the main menu panel
        this.setTitle("2XB3 Final Project PoolGen");    //title of the program
        this.setSize(width, height);                    //sets the size of the screen
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//sets the default close operation
        mainMenu.setVisible(true);                          // shows the main menu
    }

    //Setup
    private void setupGUI(int width, int height){
        //create the main menu panel
        mainMenu = new JPanel();            // creates an empty panel
        mainMenu.setSize(width, height);    // sets its size equal to the size and height of the screen
        mainMenu.setLocation(0, 0);         // sets the location of the panel
        mainMenu.setLayout(null);           // removes the default layout manager.
        //create the scroll pane
        listPane = new JScrollPane();       //creates the scroll pane
        listPane.setSize(width/2, height-60); //set pane size (should be half the size of the panel)
        listPane.setLocation(0, 0);         //set location of pane
        listPane.setLayout(null);           //removes default layout manager.

        //create the main menu buttons
        deleteButton = createButton(deleteButtonImageName, 0, 0, buttonWidth, buttonHeight, mainMenu, deleteButtonImageNamePressed);
        generateButton = createButton(generateButtonImageName, 0, 0, buttonWidth, buttonHeight, mainMenu, generateButtonImageNamePressed);
        searchButton = createButton(searchButtonImageName, 0, 0, buttonWidth, buttonHeight, mainMenu, searchButtonImageNamePressed);
        //create the main menu JTextFields
        bracketSize = createTextField(0, 0, 4, mainMenu);
        seededPlayers = createTextField(0, 0, 4, mainMenu);
        //creates the main menu JLabel.  This is initially NOT visible because no error exists.
        errorText = createLabel(error, 0, 0, mainMenu);
        errorText.setOpaque(false);
        errorText.setSize(width - generateButton.getWidth(), 110);
        errorText.setLocation((width - generateButton.getWidth()) / 2, 0);
        errorText.setHorizontalAlignment(JTextField.CENTER);
        //add the main menu to the screen
        this.add(mainMenu);
    }

    @Override
    public void paint(Graphics g){

        super.paint(g); // repaint the screen

        //since this function gets called when the screen is resized we need to adjust the position and size of all the panels or else resizing would not work
        //the board might have been moved or resized so we need to resize and reposition all the buttons
        mainMenu.setSize(this.getSize());   //updates the size of the screen
        //all the buttons are with respect to the scale variable. what this does is that it allows
        // each component to resize depending on this size variable, And if we make this variable adjust
        // its size based on the screen size the entire screen should scale accordingly, But currently the image on the buttons
        // does not scale so it does not look visually so we kept it 1.0
        float scale = 1.0f;
        //position all the values
        deleteButton.setSize((int) (buttonWidth * scale), (int) (buttonHeight * scale));
        generateButton.setSize((int) (buttonWidth * scale), (int) (buttonHeight * scale));
        searchButton.setSize((int) (buttonWidth * scale), (int) (buttonHeight * scale));

        bracketSize.setSize((int) (scale), (int) (scale));
        seededPlayers.setSize((int) (scale), (int) (scale));

        errorText.setSize((int) (scale), (int) (scale));

        //the boundary represents the distance from the edge of the screen to the components on the screen,
        //by changing this variable we can increase or decrease the distance for all the buttons from the edge
        int boundary = 20;
        //adjust all the buttons' position
        deleteButton.setLocation(this.getWidth(), this.getHeight());
        generateButton.setLocation(this.getWidth(), this.getHeight());
        searchButton.setLocation(this.getWidth(), this.getHeight());
        //adjust all the TextFields' position
        bracketSize.setLocation(this.getWidth(), this.getHeight());
        seededPlayers.setLocation(this.getWidth(), this.getHeight());
    }

    /**
     *
     * @param name          the button image
     * @param x             the x coordinate of location
     * @param y             the y coordinate of location
     * @param width         the width of the button
     * @param height        the height of the button
     * @param parent        the JPanel which will be added on
     * @param pressedName   the button image when pressed
     * @return the created button
     */
    public JButton createButton(String name, int x, int y, int width, int height, JPanel parent, String pressedName) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/"+name));
        JButton newButton = new JButton(imageIcon); // creates the button, with the image name that is given
        newButton.setPressedIcon(new ImageIcon(getClass().getResource("/images/"+name))); // makes a new button
        //if the image name that you have given does not exist then use the default image,

        // if the image given exists then remove the default button graphics
        if(newButton.getIcon().toString().length() > 2){
            //removes the default button graphics
            newButton.setOpaque(false);
            newButton.setContentAreaFilled(false);
            newButton.setBorderPainted(false);
            newButton.setFocusPainted(false);
        }else{
            //inform the user that the image can not be found
            System.out.println("go an empty location for image picture, using the default button");
            newButton.setText("place button label here");
        }

        // this sets the buttons size and location on the screen
        newButton.setSize(width,height);
        newButton.setLocation(x, y);

        newButton.setVisible(true);

        parent.add(newButton);//this adds the button to the parent
        return newButton;//returns the newly created button
    }

    /**
     *
     * @param x         the x coordinate of location
     * @param y         the y coordinate of location
     * @param l         the length of the JTextField
     * @param parent    the JPanel which will be added on
     * @return created JTextField
     */
    public JTextField createTextField(int x, int y, int l, JPanel parent){
        JTextField newTextField = new JTextField(l);
        newTextField.setLocation(x, y);
        newTextField.setVisible(true);

        parent.add(newTextField);
        return newTextField;
    }

    /**
     *
     * @param text      the text that will be displayed
     * @param x         the x-coordinate of the location
     * @param y         the y-coordinate of the location
     * @param parent    the JPanel which will be added on
     * @return created JLabel
     */
    public JLabel createLabel(String text, int x, int y, JPanel parent){
        JLabel newLabel = new JLabel();
        newLabel.setText(text);
        newLabel.setLocation(x, y);
        newLabel.setVisible(true);

        parent.add(newLabel);
        return newLabel;
    }


    public JList createList(Competitor[] competitorList, int x, int y, int width, int height, int tournamentSize, JScrollPane parent){
        JList newList = new JList(competitorList);
        newList.setVisibleRowCount(8);
        newList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        newList.setSize(width, height);
        newList.setLocation(x, y);

        parent.add(newList);
        return newList;
    }

    //this method assigns the listeners for the button and the text fields
    public void addCalculateListener(ActionListener listenForButton){
        //make the listener for the main menu buttons
        deleteButton.addActionListener(listenForButton);
        generateButton.addActionListener(listenForButton);
        searchButton.addActionListener(listenForButton);
        //make listener for the JTextFields
        bracketSize.addActionListener(listenForButton);
        seededPlayers.addActionListener(listenForButton);
    }//end calculate listener
}
