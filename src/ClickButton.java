import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class ClickButton extends Field{
    int row,col;    //declaring variables to store the row and column index of the button
    public JButton button1 = new JButton();

    //method to check if the button is already disabled
    public boolean checkIfDisabled(JButton button){
        for(int i=0;i<disabled.size();i++){
            if(disabled.get(i)==button)
                return true;
        }
        return false;
    }

    //method to create an User Interface to send message that there is no possible continuation of the game
    public void noContinuation(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel label1 = new JLabel("No possible continuation of the game. :(");
        label1.setFont(new Font("Serif",Font.PLAIN,16));
        GridLayout grid = new GridLayout(1,1);
        frame.setLayout(grid);
        frame.add(label1,BorderLayout.CENTER);
        frame.pack();
    }
    public ClickButton(int row, int col){   //main method of the class, defining the whole behaviour of every button
        button1.setBackground(Color.lightGray);
        Random rand = new Random();
        int n = rand.nextInt(10-1)+1;
        this.button1.setText(Integer.toString(n));
        this.row=row;
        this.col=col;
        button1.addActionListener(e);   //when button is clicked
        }
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adjusting values of previous and current
                if(previous==0)
                {
                    previous=Integer.parseInt(button1.getText());
                    current=Integer.parseInt(button1.getText());
                }
                else
                {
                    previous=current;
                    current=Integer.parseInt(button1.getText());
                }
                disabled.add(button1);  //add as already clicked button so it is disabled in the next moves
                moves=moves-1;  //update the moves

                score=score+Integer.parseInt(button1.getText()); //update the score

                //end of the game
                if(moves==0){

                    JFrame info1 = new JFrame();
                    info1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    info1.setVisible(true);
                    JLabel infolabel = new JLabel();
                    infolabel.setFont(new Font("Serif",Font.PLAIN,14));
                    int deviation = target - score;
                    JLabel deviat = new JLabel();
                    deviat.setFont(new Font("Serif",Font.PLAIN,14));
                    GridLayout grid = new GridLayout(2,1);
                    info1.setLayout(grid);
                    info1.add(infolabel);
                    info1.add(deviat);

                    for(int i=0;i<n*n;i++)  //disabling all the buttons because the game is finished
                    {
                        buttons.get(i).setEnabled(false);
                    }

                    if(score<target){   //message if you lose
                        infolabel.setText("You ran out of moves and lost the game! :(");
                        deviat.setText("You missed "+Integer.toString(deviation)+" points to obtain the target");
                    }
                    else{   //message if you win
                        infolabel.setText("You won the game! :)");
                        deviat.setText("Try a new game.");
                    }

                    info1.pack();
                }
                else {  //creating an User Interface that sends you a message if you won
                    if(score>=target){
                        JFrame info1 = new JFrame();
                        info1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        info1.setVisible(true);
                        JLabel infolabel = new JLabel();
                        infolabel.setFont(new Font("Serif",Font.PLAIN,14));
                        int deviation = target - score;
                        JLabel deviat = new JLabel();
                        deviat.setFont(new Font("Serif",Font.PLAIN,14));
                        GridLayout grid = new GridLayout(2,1);
                        info1.setLayout(grid);
                        info1.add(infolabel);
                        info1.add(deviat);

                        for(int i=0;i<n*n;i++)
                        {
                            buttons.get(i).setEnabled(false);
                        }
                        infolabel.setText("You won the game! :)");
                        deviat.setText("Try a new game.");
                        info1.pack();
                    }
                    else {
                        int ct_of_disabled=0;
                        label22.setText(Integer.toString(moves));
                        label4.setText(Integer.toString(score));
                        button1.setEnabled(false);
                        for (int i = 0; i < n * n; i++) {
                            //main condition to check all possible continuations of the game by checking if rows index or column index is divisible by previous or current in any way
                            if ((cols.get(i) % previous == 0 && rows.get(i) % previous == 0) || (cols.get(i) % previous == 0 && rows.get(i) % current == 0)
                                    || (cols.get(i) % current == 0 && rows.get(i) % previous == 0) || (cols.get(i) % current == 0 && rows.get(i) % previous == 0)) {
                                buttons.get(i).setBackground(Color.ORANGE);
                                buttons.get(i).setEnabled(true);
                                if (checkIfDisabled(buttons.get(i)) == true) {
                                    buttons.get(i).setEnabled(false);
                                    ct_of_disabled++;
                                }
                            } else {
                                buttons.get(i).setBackground(Color.lightGray);
                                buttons.get(i).setEnabled(false);
                                ct_of_disabled++;
                            }
                        }
                        //if all buttons are disabled the game ends
                        if(ct_of_disabled==n*n){
                            noContinuation();
                        }
                    }
                }
            }
        };
    }
