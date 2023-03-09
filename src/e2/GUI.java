package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    private Set<Pair<Integer,Integer>> clickedCells = new HashSet<>();
    
    public GUI(int size, int numberOfMines) {
        this.logics = new LogicsImpl(size, numberOfMines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            boolean aMineWasFound = this.logics.isThereMine(pos); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
                System.exit(0);
            } else {
                bt.setEnabled(false);
                this.clickedCells.add(pos);
                this.logics.setLocalNumberOfMines(pos);
                this.clickedCells.addAll(this.logics.getAutoClickedPositions(pos));
                System.out.println("autoclicked"+this.logics.getAutoClickedPositions(pos));
                drawBoard();            	
            }
            Set<Pair<Integer, Integer>> allPositions = new HashSet<>();
            this.buttons.forEach((b,p) -> allPositions.add(p));
            boolean isThereVictory = this.logics.areYouAWinner(clickedCells, allPositions); // call the logic here to ask if there is victory
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }

        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    logics.changeFlagList(pos);
                    // call the logic here to put/remove a flag
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
        this.buttons.forEach((b,p) -> b.setText(this.logics.getMines().contains(p) ? "*" : ""));
        // call the logic here
        // if this button is a mine, draw it "*"
        // disable the button
    }

    private void drawBoard() {
        this.buttons.forEach((b,p) -> b.setText(this.clickedCells.contains(p) ? (this.logics.getLocalNumberOfMines().containsKey(p) ? String.valueOf(this.logics.getLocalNumberOfMines().get(p)) : "") : b.getText()));
        this.buttons.forEach((b,p) -> b.setText(this.logics.getFlagList().contains(p) ? "F" : b.getText().equals("F") ? "" : b.getText()));
        this.buttons.forEach((b,p) -> b.setText(this.logics.getMines().contains(p) ? "*" : b.getText()));
        // call the logic here
        // if this button is a cell with counter, put the number
        // if this button has a flag, put the flag
    }

    
}
