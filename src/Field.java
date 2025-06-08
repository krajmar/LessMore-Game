import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Field {
    public static int n,filect=0;   //declaring size of field and counter for saving to a file to be in a different file every time
    public static int target;   //declaring target
    public static int moves, temp_moves;    //declaring moves and temp_moves which will always hold the starting value for moves

    public static ArrayList<JButton> buttons = new ArrayList<JButton>();    //array list to store all buttons
    public static ArrayList<Integer> cols = new ArrayList<Integer>();   //array list to store column index of each button
    public static ArrayList<Integer> rows = new ArrayList<Integer>();   //array list to store row index of each button
    public static ArrayList<JButton> disabled = new ArrayList<JButton>();   //array list to store all the disabled buttons

    public static int score = 0;    //declaring score
    public static int previous = 0, current = 0;    //declaration of previous and current
    public static boolean restart = false; //variable when restart button is clicked just to confirm it is restarting
    public JFrame frame = new JFrame(); //the main jFrame
    public static JLabel label22 = new JLabel(); //moves label
    public static JLabel label4 = new JLabel(); //score label

    public Field() {
    }

    public Field(int n, int target, int moves) {  //constructor for customizing the field
        this.n = n;
        this.target = target;
        this.moves = moves;
        temp_moves = moves;
        construct_field(n, target, moves);
    }

    private void saveToFile() { //Method for saving to file
        try {
            filect++;
            String s = new String("File"+filect+".txt");
            File fajl = new File("C:\\Users\\User3245\\Desktop\\"+s);
            FileWriter file = new FileWriter(fajl.getPath());
            PrintWriter writer = new PrintWriter(file);
            writer.println(n);  //save in the file the size of the field, the target and number of moves
            writer.println(target);
            writer.println(moves);
            writer.close();
        } catch (
                IOException ex) {
        }
    }
    private void loadFromFile(){ //method to load from file
        try {
            String s = new String("File"+filect+".txt");
            FileReader file = new FileReader("C:\\Users\\User3245\\Desktop\\"+s);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            int counter=1;
            int temp_n=0,temp_target=0,temp_moves=0;
            while (line != null) { //If the line in txt file is not empty get the values saved in the file line by line
               if(counter==1)
                   temp_n=Integer.parseInt(line);
               else if(counter==2)
                   temp_target=Integer.parseInt(line);
               else if(counter==3)
                   temp_moves=Integer.parseInt(line);
               else
                   break;
               line=reader.readLine();  //go to the next line
               counter++;
            }
            frame.dispose();
            Field f_load = new Field(temp_n,temp_target,temp_moves);
        } catch (IOException ex) {}
    }
    private void changeOnRestart(){ //function to change all the key parameters of the frame to their starting values
        previous=0;
        current=0;
        moves=temp_moves;   //temp_moves has the starting value given for moves
        score=0;
        label22.setText(Integer.toString(moves));
        label4.setText(Integer.toString(score));
    }

    boolean isStringFullOfDigits(String s){ //validation of values when new game is created with values given from the user
        if(s.length()>0) {
            if (s.charAt(0) == '0')
                return false;
            else {
                for (int i = 0; i < s.length(); i++) {
                    if (Character.isDigit(s.charAt(i)) == false) //checking each character
                        return false;
                }
                return true;
            }
        }
        else
            return false;
    }
    private void createNewGame(){   //method for creating a new Gameplay Field of the game
        JFrame frame_new = new JFrame();
        frame_new.setVisible(true);
        frame_new.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5,1);
        frame_new.setLayout(grid);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.ORANGE);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.ORANGE);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.ORANGE);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.ORANGE);
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.ORANGE);

        frame_new.add(panel1);
        frame_new.add(panel2);
        frame_new.add(panel3);
        frame_new.add(panel4);
        frame_new.add(panel5);
        frame_new.setSize(500,500);
        frame_new.setResizable(false);
        frame_new.setResizable(false);



        //panel1 of the menu after clicking New Game
        JLabel label1 = new JLabel("Select a level");
        label1.setFont(new Font("serif",Font.BOLD,20));
        panel1.add(label1,BorderLayout.CENTER);

        //panel2 of the menu after clicking New Game
        JButton button_easy = new JButton("Easy");
        JButton button_medium = new JButton("Medium");
        JButton button_hard = new JButton("Hard");

        button_easy.setBackground(Color.BLACK);
        button_medium.setBackground(Color.BLACK);
        button_hard.setBackground(Color.BLACK);

        button_easy.setForeground(Color.WHITE);
        button_medium.setForeground(Color.WHITE);
        button_hard.setForeground(Color.WHITE);

        Dimension max = new Dimension(70,70);
        button_easy.setSize(max);
        button_medium.setSize(max);
        button_hard.setSize(max);

        GridLayout gridpanel2 = new GridLayout(1,3,8,18);
        panel2.setLayout(gridpanel2);
        panel2.add(button_easy);
        panel2.add(button_medium);
        panel2.add(button_hard);

        //panel3 of the menu after clicking New Game
        JLabel label2 = new JLabel("Customize the new game");
        label2.setFont(new Font("serif",Font.BOLD,20));
        panel3.add(label2,BorderLayout.CENTER);

        //panel4 of the menu after clicking New Game
        GridLayout gridpanel4 = new GridLayout(1,3);
        JPanel panel41 = new JPanel();
        panel41.setBackground(Color.ORANGE);
        JPanel panel42 = new JPanel();
        panel42.setBackground(Color.ORANGE);
        JPanel panel43 = new JPanel();
        panel43.setBackground(Color.ORANGE);
        panel4.setLayout(gridpanel4);
        panel4.add(panel41);
        panel4.add(panel42);
        panel4.add(panel43);

        JLabel label41 = new JLabel("Size of the board: ");
        JLabel label42 = new JLabel("Target: ");
        JLabel label43 = new JLabel("Moves: ");
        JTextField text41 = new JTextField();
        JTextField text42 = new JTextField();
        JTextField text43 = new JTextField();

        text41.setFont(new Font("serif",Font.BOLD,14));
        text42.setFont(new Font("serif",Font.BOLD,14));
        text43.setFont(new Font("serif",Font.BOLD,14));
        text41.setColumns(3);
        text42.setColumns(3);
        text43.setColumns(3);

        panel41.add(label41,BorderLayout.NORTH);
        panel41.add(text41,BorderLayout.SOUTH);

        panel42.add(label42,BorderLayout.NORTH);
        panel42.add(text42,BorderLayout.SOUTH);

        panel43.add(label43,BorderLayout.NORTH);
        panel43.add(text43,BorderLayout.SOUTH);

        //panel5 of the menu after clicking New Game
        GridLayout gridpanel5 = new GridLayout(1,2);
        panel5.setLayout(gridpanel5);
        JLabel errormsg=new JLabel();
        JButton buttonok = new JButton("Ok");
        buttonok.setBackground(Color.BLACK);
        buttonok.setForeground(Color.WHITE);
        panel5.add(errormsg);
        panel5.add(buttonok);

        //giving functionalities to the buttons
        ActionListener easy = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                score=0;
                label4.setText(Integer.toString(score));
                Field f2 = new Field(6,50,20);
                frame_new.dispose();
            }
        };
        button_easy.addActionListener(easy);
        ActionListener medium = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                score=0;
                label4.setText(Integer.toString(score));
                Field f2 = new Field(8,75,15);
                frame_new.dispose();
            }
        };
        button_medium.addActionListener(medium);
        ActionListener hard = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                score=0;
                label4.setText(Integer.toString(score));
                Field f2 = new Field(12,150,25);
                frame_new.dispose();
            }
        };
        button_hard.addActionListener(hard);

        //an action listener to the Ok button
        ActionListener ok = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n_value=text41.getText();
                String target_value=text42.getText();
                String moves_value=text43.getText();
                if(isStringFullOfDigits(n_value)==false || isStringFullOfDigits(target_value)==false
                || isStringFullOfDigits(moves_value)==false)
                    errormsg.setText("Invalid input of the numbers!");
                else{
                    frame.dispose();
                    Field f2 = new Field(Integer.parseInt(n_value),Integer.parseInt(target_value),Integer.parseInt(moves_value));
                }
                frame_new.dispose();
            }
        };
        buttonok.addActionListener(ok);
    }

    //construction function
    private void construct_field(int n,int target,int moves){
        int previous=0,current=0;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setSize(1200,1000);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setSize(1000,100);
        panel3.setSize(1000,100);
        panel2.setSize(1000,800);

        //panel1
        JLabel label1 = new JLabel("Target: ");
        JLabel label11 = new JLabel();
        label11.setText(Integer.toString(target));
        JLabel label2 = new JLabel("Moves: ");
        label22.setText(Integer.toString(moves));
        FlowLayout flow1 = new FlowLayout();
        panel1.setLayout(flow1);
        panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel1.add(label1);
        panel1.add(label11);
        panel1.add(label2);
        panel1.add(label22);

        //panel2
        GridLayout grid = new GridLayout(n,n);
        panel2.setLayout(grid);
            int ct = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ClickButton jbut = new ClickButton(i, j);
                    panel2.add(jbut.button1);
                    buttons.add(ct, jbut.button1);
                    rows.add(ct, i + 1);
                    cols.add(ct, j + 1);
                    ct++;
                }
            }
        //panel3
        JLabel label3 = new JLabel("Score: ");
        label4.setText(Integer.toString(score));
        FlowLayout flow2 = new FlowLayout();
        panel3.setLayout(flow2);
        panel3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel3.add(label3);
        panel3.add(label4);

        //panel4
        JButton button_save = new JButton("Save");
        JButton button_load = new JButton("Load");
        JButton button_restart = new JButton("Restart");
        JButton button_newgame = new JButton("New Game");

        GridLayout grid2 = new GridLayout(4,1);
        panel4.setLayout(grid2);
        panel4.add(button_save);
        panel4.add(button_load);
        panel4.add(button_restart);
        panel4.add(button_newgame);

        ActionListener restarting = new ActionListener() {  //action listener when Restart button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                restart = true;
                for(int i=0;i<n*n;i++){
                    buttons.get(i).setEnabled(true);
                    buttons.get(i).setBackground(Color.lightGray);
                }
                int ct=0;
                while(disabled.size()>0)
                {
                    disabled.remove(ct);
                }
                changeOnRestart();
            }
        };
    button_restart.addActionListener(restarting);

    ActionListener newgame = new ActionListener() { //action listener when New Game button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            createNewGame();
        }
    };
    button_newgame.addActionListener(newgame);

    ActionListener save = new ActionListener() {    //action Listener when Save button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            saveToFile();
        }
    };
    button_save.addActionListener(save);

    ActionListener load = new ActionListener() {    //action listener when load button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            loadFromFile();
        }
    };
    button_load.addActionListener(load);

        //putting panels together
        frame.add(panel1,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.CENTER);
        frame.add(panel3,BorderLayout.SOUTH);
        frame.add(panel4,BorderLayout.EAST);

        frame.pack();
    }

}
