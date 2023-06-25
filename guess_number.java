import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class guess_number extends JFrame
{
    JTextField text1;
    JLabel display; 
    ButtonListener buttonListner;  

    //setting random number in rand variable
    int rand=(int) (Math.random()*100); 
    int attempts=0;

    public guess_number(){

        //Get the container
        Container container = getContentPane();

        //Set absolute layout        
        container.setLayout(null);   

        //Set Background Color
        container.setBackground(Color.GREEN); 

        //Creating label for input number
        JLabel label1=new JLabel("Guess your number b/w 1-100");
        label1.setFont(new Font("tunga",Font.PLAIN,17));
        label1.setSize(270,20);
        label1.setLocation(140,20);

        //Creating TextField for input guess
        text1=new JTextField(10);
        text1.setSize(50,30);
        text1.setLocation(230,60);

        //Creating Label for Display message        
        display=new JLabel("Try and guess your number");
        //display.setFont(new Font("tunga",Font.PLAIN,16));
        display.setSize(270,20);
        display.setLocation(160,100);

        //creating Guess button
        JButton button=new JButton("Guess");
        button.setSize(150,40);
        button.setLocation(190,170);
        buttonListner=new ButtonListener();        
        button.addActionListener(buttonListner);

        //Place the components in the pane
        container.add(display);     
        container.add(label1);
        container.add(text1);
        container.add(button);        

        //Set the title of the window
        setTitle("GUESS THE NUMBER GAME");        

        //Set the size of the window and display it
        setSize(550,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int number = Integer.parseInt(text1.getText());
            attempts=attempts+1;
            if(number<rand)
            {
                display.setText(number+" is too low..., try again!!");
            }    
            else if(number>rand)
            {
                display.setText(number+" is too high..., try again!!");
            }
            else
            {
                display.setText("Congrats! You guessed the number in " + attempts + " attempts.");    
            }
        }
    }    

    public static void main(String[] args){
        guess_number guess = new guess_number();
    }
}