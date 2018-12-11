package buildings.gui;

import buildings.Buildings;
import buildings.dwelling.Dwelling.*;
import buildings.Factories.DwellingFactory;
import buildings.Factories.OfficeFactory;
import buildings.Interface.Floor;
import buildings.dwelling.Office.OfficeBuilding;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class MainWindow extends JFrame{
    private Container panelMain;
    private JMenuBar menuBar;
    private JMenuItem menuItemOpenDwelling;
    private JMenuItem menuItemOpenOffice;
    private JMenu fileMenu;
    private JMenu lfMenu;
    private JRadioButtonMenuItem metalMenuItem;
    private JRadioButtonMenuItem systemMenuItem;
    private JRadioButtonMenuItem motifMenuItem;
    private ButtonGroup bg;
    private File file;
    private JPanel planOfBuilding = new JPanel();
    private JTextPane textArea = new JTextPane();
    private JTextPane textArea1 = new JTextPane();
    private JTextPane textArea2 = new JTextPane();

    public MainWindow() {
        MainWindow mw = this;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ex) {
            System.out.println("Error setting LAF: " + ex);
        }
        SwingUtilities.updateComponentTreeUI(mw);
        setTitle("Show buildings");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //File
        fileMenu = new JMenu("File");

        menuItemOpenDwelling = new JMenuItem("Open dwelling");
        menuItemOpenDwelling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                    try {
                        Buildings.setBuildingFactory(new DwellingFactory());
                        Dwelling dwelling = (Dwelling) Buildings.readBuilding(new InputStreamReader(new FileInputStream(file)));
                        textArea.setText("Type: " + dwelling.getClass().getSimpleName() + "\nCount of floors: " + dwelling.getCntFloors() + "\nArea: " + dwelling.getAreaSpaces() + "\n");
                        Floor[] floors = dwelling.getFloors();
                        textArea1.setText("Type: " + floors[0].getClass().getSimpleName() + "\nCount of spaces: " + floors[0].getCnt() + "\nArea: " + floors[0].getArea());
                        textArea2.setText("Type: " + dwelling.getSpace(0).getClass().getSimpleName() + "\nCount of rooms: " + dwelling.getSpace(0).getRooms() + "\nArea: " + dwelling.getSpace(0).getArea());

                        planOfBuilding.setLayout(new BoxLayout(planOfBuilding,1));
                        planOfBuilding.removeAll();

                        for (int i = 0; i < floors.length; i++)
                        {
                            PlanPanel planPanel = new PlanPanel();
                            planPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                            planPanel.setPreferredSize(new Dimension(60*i+10,40));
                            planPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 7));
                            planPanel.setName("Type: " +floors[floors.length-1-i].getClass().getSimpleName() + "\nCount of rooms: " +
                                               floors[floors.length-1-i].getCnt()+ "\nArea: " +floors[floors.length-1-i].getArea());
                            for (int j = 0; j < floors[floors.length-1-i].getCnt(); j++)
                            {
                                JButton button = new JButton();
                                button.setPreferredSize(new Dimension(50,20));
                                button.setName("Type: " + floors[floors.length-1-i].getSpace(j).getClass().getSimpleName() + "\nCount of rooms: " +
                                                floors[floors.length-1-i].getSpace(j).getRooms()+ "\nArea: " +floors[floors.length-1-i].getSpace(j).getArea());
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        textArea2.setText(null);
                                        textArea2.setText(button.getName());
                                    }
                                });
                                planPanel.add(button);
                            }
                            planOfBuilding.add(planPanel);
                        }

                        planOfBuilding.updateUI();

                    }
                    catch(FileNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "File not found!");
                    }
                    catch(IOException ex){
                        JOptionPane.showMessageDialog(null, "Format of file is wrong!");
                    }
                }
            }
        });

        menuItemOpenOffice = new JMenuItem("Open office building");
        menuItemOpenOffice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                int ret = fileOpen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileOpen.getSelectedFile();
                }
                try{
                    Buildings.setBuildingFactory(new OfficeFactory());
                    OfficeBuilding officeBuilding = (OfficeBuilding) Buildings.readBuilding(new InputStreamReader(new FileInputStream(file)));
                    textArea.setText("Type: " + officeBuilding.getClass().getSimpleName() + "\nCount of floors: " + officeBuilding.getCntFloors() + "\nArea: " + officeBuilding.getAreaSpaces() + "\n");


                    Floor[] floors = officeBuilding.getFloors();
                    textArea1.setText("Type: " + floors[0].getClass().getSimpleName() + "\nCount of spaces: " + floors[0].getCnt() + "\nArea: " + floors[0].getArea());
                    textArea2.setText("Type: " + officeBuilding.getSpace(0).getClass().getSimpleName() + "\nCount of rooms: " + officeBuilding.getSpace(0).getRooms() + "\nArea: " + officeBuilding.getSpace(0).getArea());

                    planOfBuilding.setLayout(new BoxLayout(planOfBuilding,1));
                    planOfBuilding.removeAll();

                    for (int i = 0; i < floors.length; i++)
                    {
                        PlanPanel planPanel = new PlanPanel();
                        planPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                        planPanel.setPreferredSize(new Dimension(60*i+10,40));
                        planPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 7));
                        planPanel.setName("Type: " +floors[floors.length-1-i].getClass().getSimpleName() + "\nCount of rooms: " +
                                floors[floors.length-1-i].getCnt()+ "\nArea: " +floors[floors.length-1-i].getArea());
                        for (int j = 0; j < floors[floors.length-1-i].getCnt(); j++)
                        {
                            JButton button = new JButton();
                            button.setPreferredSize(new Dimension(50,20));
                            button.setName("Type: " + floors[floors.length-1-i].getSpace(j).getClass().getSimpleName() + "\nCount of rooms: " +
                                    floors[floors.length-1-i].getSpace(j).getRooms()+ "\nArea: " +floors[floors.length-1-i].getSpace(j).getArea());
                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    textArea2.setText(null);
                                    textArea2.setText(button.getName());
                                }
                            });
                            planPanel.add(button);
                        }
                        planOfBuilding.add(planPanel);
                    }

                    planOfBuilding.updateUI();

                }
                catch(FileNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "File not found!");
                }
                catch(IOException ex){
                    JOptionPane.showMessageDialog(null, "Format of file is wrong!");
                }
            }
        });

        //Look & Feel
        lfMenu = new JMenu("Look & Feel");
        bg = new ButtonGroup();
        metalMenuItem = new JRadioButtonMenuItem("Metal",false);
        systemMenuItem = new JRadioButtonMenuItem("System",true);
        motifMenuItem = new JRadioButtonMenuItem("Motif",false);
        metalMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch(Exception ex) {
                    System.out.println("Error setting LAF: " + ex);
                }
                SwingUtilities.updateComponentTreeUI(mw);
            }
        });

        systemMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(Exception ex) {
                    System.out.println("Error setting LAF: " + ex);
                }
                SwingUtilities.updateComponentTreeUI(mw);
            }
        });

        motifMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                } catch(Exception ex) {
                    System.out.println("Error setting LAF: " + ex);
                }
                SwingUtilities.updateComponentTreeUI(mw);
            }
        });

        lfMenu.add(systemMenuItem);
        lfMenu.add(metalMenuItem);
        lfMenu.add(motifMenuItem);
        bg.add(systemMenuItem);
        bg.add(metalMenuItem);
        bg.add(motifMenuItem);


        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);


        fileMenu.add(menuItemOpenDwelling);
        fileMenu.add(menuItemOpenOffice);
        menuBar.add(fileMenu);
        menuBar.add(lfMenu);

        panelMain = this.getContentPane();
        panelMain.setLayout(new GridLayout(4,1));
        textArea.setEditable(false);
        textArea1.setEditable(false);
        textArea2.setEditable(false);
        panelMain.add(scrollPane);
        panelMain.add(scrollPane1);
        panelMain.add(scrollPane2);
        panelMain.add(new JScrollPane(planOfBuilding));

    }

    private class PlanPanel extends JPanel implements MouseListener
    {

        PlanPanel()
        {
            addMouseListener(this);
        }

        @Override public void mouseClicked(MouseEvent e) {
            textArea1.setText(null);
            textArea1.setText(this.getName());
        }

        @Override public void mousePressed(MouseEvent e){}
        @Override public void mouseReleased(MouseEvent e){}
        @Override public void mouseEntered(MouseEvent e){}
        @Override public void mouseExited(MouseEvent e){}
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mw.setSize(900,700);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        mw.setLocation(screenWidth/4, screenHeight/4);
        mw.setVisible(true);
        mw.setResizable(true);
    }


}
