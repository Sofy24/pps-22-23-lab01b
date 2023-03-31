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
    private final Map<JButton,Cell> buttons = new HashMap<>();
    private final Logics logics;
    private Set<Cell> clickedCells = new HashSet<>();
    
    public GUI(int size, int numberOfMines) {
        this.logics = new LogicsImpl(size, numberOfMines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Cell cell = buttons.get(bt);
            boolean aMineWasFound = this.logics.isThereMine(cell); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
                System.exit(0);
            } else {
                bt.setEnabled(false);
                this.clickedCells.add(cell);
                this.logics.setLocalNumberOfMines(cell);
                cell.setRevealed(true);
                this.clickedCells.addAll(this.logics.getAutoClickedCells(cell));
                this.clickedCells.stream().forEach(c -> c.setText(c.getLocalNumberOfMines() > 0 ? String.valueOf(c.getLocalNumberOfMines()) : ""));
                this.buttons.forEach((b,c) -> b.setEnabled(!this.clickedCells.contains(c)));
                drawBoard();            	
            }
            boolean isThereVictory = this.logics.areYouAWinner(clickedCells, GridSingleton.getGrid()); // call the logic here to ask if there is victory
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
                    final Cell cell = buttons.get(bt);
                    logics.changeFlagList(cell);
                    // call the logic here to put/remove a flag
                }
                drawBoard(); 
            }
        };
                
        Set<Cell> allCells = new HashSet<>();
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton("");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                Cell cell = new CellImpl(i,j);
                this.buttons.put(jb, cell);
                panel.add(jb);
            }
        }
        this.buttons.forEach((b,c) -> allCells.add(c));
        GridSingleton.getInstance(allCells);
        this.buttons.forEach((b,c) -> this.logics.setLocalNumberOfMines(c));
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
        this.buttons.forEach((b,c) -> b.setText(this.logics.getMines().contains(c) ? "*" : ""));
        // call the logic here
        // if this button is a mine, draw it "*"
        // disable the button
    }

    private void drawBoard() {
        this.buttons.forEach((b,c) -> b.setText(this.clickedCells.contains(c) ? (c.getLocalNumberOfMines() > 0 ? String.valueOf(c.getLocalNumberOfMines()) : "") : b.getText()));
        this.buttons.forEach((b,c) -> b.setText(this.logics.getFlagList().contains(c) ? "F" : b.getText().equals("F") ? "" : b.getText()));
        this.buttons.forEach((b,c) -> c.setText(b.getText()));
        // call the logic here
        // if this button is a cell with counter, put the number
        // if this button has a flag, put the flag
    }

    
}
