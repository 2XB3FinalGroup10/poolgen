/**
 * Created by Serge on 08/04/2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PoolGenView extends JFrame {
    //Panels
    private JPanel mainMenu;

    //Buttons
    private JButton deleteButton;
    private JButton generateButton;
    private JButton searchButton;

    //Labels
    private JLabel textfield;       //displays error messages.

    //Textfields (for user input)
    private JTextField poolNum;
    private JTextField playersPerPool;

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
        //gets rid of java's default positioning system so we have more control and can manually position everything using absolute positioning
        this.getContentPane().setLayout(null);
        this.setLayout(null);

        setupMainMenu(width, height);                   //sets up the main menu panel

        //This will setup what the user will be able to see.  Main menu is initially visible.
        //Additional JPanels should be set to false.
        mainMenu.setVisible(true);  // shows the main menu

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//sets the default close operation
        this.setSize(width, height);//sets the size of the screen
    }

    //Setup
    private void setupMainMenu(int width, int height){
        //create the main menu panel
        mainMenu = new JPanel();            // creates an empty panel
        mainMenu.setSize(width, height);    // sets its size equal to the size and height of the screen
        mainMenu.setLocation(0,0);          // sets the location of the panel
        mainMenu.setLayout(null);           // removes the default layout manager, allowing us to position everything manually and giving us more control

        //create the main menu buttons
        deleteButton = createButton(deleteButtonImageName, width/2-buttonWidth/2, height/4*2-buttonHeight/2, buttonWidth, buttonHeight, mainMenu, deleteButtonImageNamePressed);
        generateButton = createButton(generateButtonImageName, width/2-buttonWidth/2, height/4*2-buttonHeight/2, buttonWidth, buttonHeight, mainMenu, generateButtonImageNamePressed);
        searchButton = createButton(searchButtonImageName, width/2-buttonWidth/2, height/4*2-buttonHeight/2, buttonWidth, buttonHeight, mainMenu, searchButtonImageNamePressed);

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
        generateButton.setSize((int) (buttonWidth * scale), (int) (buttonHeight * scale));

        //the boundary represents the distance from the edge of the screen to the components on the screen,
        //by changing this variable we can increase or decrease the distance for all the buttons from the edge
        int boundary = 20;
        //adjust all the button's position
        generateButton.setLocation(this.getWidth() / 16, this.getHeight()/8);//top
    }

    public JButton createButton(String name, int x, int y, JPanel parent, String pressedName){
        return createButton(name, x,y, buttonWidth, buttonHeight, parent, pressedName); }

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

    //this function assigns the listeners for the button and the screen.
    public void addCalculateListener(ActionListener listenForButton){
        //make the listener for the main menu buttons
        generateButton.addActionListener(listenForButton);

    }//end calculate listener
}
