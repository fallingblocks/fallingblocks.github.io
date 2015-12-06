package nh.tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nh.tetris.core.TitleState;

@SuppressWarnings("serial")
public class TitleWindow extends JFrame implements ActionListener
{
    private TitleState titleState;
    
    private JButton soloButton, duelButton, exitButton;
    private JPanel panel;
    
    public TitleWindow(TitleState titleState) 
    {
        super("Falling Blocks");
        
        this.titleState = titleState;
        
        panel = new JPanel() {
            public void paintComponent(Graphics g) 
            {
                g.setColor(Color.BLACK);
                
                g.setFont(new Font("Tahoma", Font.PLAIN, 72));
                
                g.drawString("Falling", 50,  80);
                g.drawString("Blocks",  52, 160);
            }
        };
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(290, 280));
        
        soloButton = new JButton("Solo"); soloButton.addActionListener(this); soloButton.setSize(300, 20);
        duelButton = new JButton("Duel"); duelButton.addActionListener(this); duelButton.setSize(300, 20);
        exitButton = new JButton("Exit"); exitButton.addActionListener(this); exitButton.setSize(300, 20);
        
        soloButton.setLocation(0, 200 +  0); panel.add(soloButton);
        duelButton.setLocation(0, 200 + 30); panel.add(duelButton);
        exitButton.setLocation(0, 200 + 60); panel.add(exitButton);
        
//        this.setLayout(null);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        this.add(panel);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        
        if (source == soloButton) 
        {
            titleState.playSoloGame();
        }
        else if (source == duelButton) 
        {
            titleState.playDuelGame();
        }
        else if (source == exitButton) 
        {
            System.exit(0);
        }
    }
}
