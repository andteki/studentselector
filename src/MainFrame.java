import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final int MAX_STUDENT = 30;
    private final int NUM_OF_STUDENT = 4;
    
    private JCheckBox[] boxes = new JCheckBox[MAX_STUDENT];
    private JButton readyButton = new JButton("Jelöl");
    private JButton autoButton = new JButton("Atumokatikus");

    ArrayList<Integer> seletableList = new ArrayList<>();
    ArrayList<Integer> selectedList = new ArrayList<>();    

    public MainFrame() {

        readyButton.setEnabled(false);
        readyButton.addActionListener(e -> startNote());
        autoButton.addActionListener(e -> startAuto());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 10));
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new JCheckBox(String.valueOf(i+1));
            boxes[i].addItemListener(e -> changeItem());
            panel.add(boxes[i]);
        }
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(autoButton, BorderLayout.NORTH);
        this.add(readyButton, BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setVisible(true);
    }

    private void changeItem() {
        System.out.println("jelöl");
        int count = 0;
        for(JCheckBox box : boxes) {
            if(box.isSelected()) {
                count++;
            }
        }
        readyButton.setEnabled(count == 4);
    }

    private void startNote() {
        System.out.println("Kész");
    }   
    
    private void startAuto() {
        System.out.println("Automatikus jelölés");
        startRandomSelect();
        deleteSelection();
        setBoxes();
        printAutoSelected();
    }

    private void startRandomSelect() {
        //Feltöltjük a számokkal numList
        for (int i = 0; i < MAX_STUDENT; i++) {
            seletableList.add(i);
        }
        Random ran = new Random();
        int sel = 0;
        for (int i = MAX_STUDENT; 
                i > MAX_STUDENT - NUM_OF_STUDENT; i--) {
            sel = ran.nextInt(i);
            selectedList.add(seletableList.remove(sel));
        }
    }

    private void setBoxes() {
        for (int i = 0; i < boxes.length; i++) {
            if(selectedList.contains(i)) {
                boxes[i].setSelected(true);
            }
        }
    }

    private void printAutoSelected() {
        for(int num : selectedList) {
            System.out.print(num + 1 + " ");
        }
        System.out.println();
    }

    private void deleteSelection() {
        for (int i = 0; i < boxes.length; i++) {
            boxes[i].setSelected(false);            
        }
    }
}
