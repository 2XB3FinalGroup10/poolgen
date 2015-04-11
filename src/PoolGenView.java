/**
 * Created by Serge on 08/04/2015.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PoolGenView extends JFrame {
    private PoolGen mPoolGen;
    private PoolGenModel mPoolGenModel;

    private PoolGenViewForm mForm;

    //FileChooser
    private JFileChooser fc;

    //Image data here:
    public static final String deleteButtonImageName            = "delete1.png";
    public static final String deleteButtonImageNamePressed     = "delete2.png";
    public static final String generateButtonImageName          = "generate1.png";
    public static final String generateButtonImageNamePressed   = "generate2.png";
    public static final String searchButtonImageName            = "search1.png";
    public static final String searchButtonImageNamePressed     = "search2.png";

    //constructors
    public PoolGenView(final PoolGen poolGen){
        this.mPoolGen = poolGen;
        this.mPoolGenModel = poolGen.getModel();
        setTitle("Test");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mForm = new PoolGenViewForm();
        this.add(mForm.getBase());
        this.setVisible(true);
        pack();

        mForm.competitorList.setCellRenderer(new ListCellRenderer<Competitor>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Competitor> list, Competitor value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(value.getName());
            }
        });

        mForm.browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                String path = chooser.getSelectedFile().getAbsolutePath();
                mForm.file.setText(path);
            }
        });

        mForm.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = mForm.competitorList.getSelectedIndex();
                mPoolGenModel.getCompetitors().remove(i);
                generateList();
            }
        });

        mForm.insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPlayer ip = new insertPlayer();
                ip.pack();
                ip.mListener = new insertPlayer.Listener() {
                    @Override
                    public void onOk(Competitor c) {
                        mPoolGenModel.getCompetitors().add(c);
                        generateList();
                    }
                };
                ip.setVisible(true);
            }
        });

        mForm.generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPoolGen.generatePools();
            }
        });

        generateList();
    }

    public void generateList(){
        mForm.competitorList.setListData(mPoolGenModel.getCompetitors().toArray());
    }
}
