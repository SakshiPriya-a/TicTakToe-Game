import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame {
    JFrame frame=new JFrame("tic Tac Toe");	
	JPanel[] pa=new JPanel[3];
	JLabel label=new JLabel("First player turn...");
	JButton[] bt=new JButton[9];
	JButton reset=new JButton("RESET");
	JButton exit=new JButton("EXIT");
	//Code to load both images
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/user1.png"));
	ImageIcon icon2=new ImageIcon(getClass().getResource("images/user2.png"));
	int player=1,count=0;
	boolean winnerFound=false;
	public TicTacToeGame()
	{
		frame.setSize(500,580);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		addPanels();
		frame.setVisible(true);
	}
	private void addPanels()
	{
		for(int i=0;i<3;i++)
		{
			pa[i]=new JPanel();
			frame.add(pa[i]);
		}
		pa[0].setBounds(50,30,400,40);
		pa[1].setBounds(50,100,400,360);
		pa[2].setBounds(50,490,400,40);
		addLabel();
	}
	private void addLabel()
	{
		pa[0].add(label);
		label.setFont(new Font("elephant",Font.PLAIN,25));
		label.setForeground(Color.blue);
		pa[0].setBackground(Color.cyan);
		addButtons();
	}
	private void addButtons()
	{
		pa[1].setLayout(new GridLayout(3,3));
		TicListener listener=new TicListener();
		for(int i=0;i<9;i++)
		{
			bt[i]=new JButton();
			bt[i].addActionListener(listener);
			bt[i].setBackground(Color.yellow);
			pa[1].add(bt[i]);
		}
		addResetAndExitButton();
	}
	private void addResetAndExitButton()
	{
		pa[2].add(reset);
		pa[2].add(exit);
		pa[2].setOpaque(false);//It will make panel transparent
		Font fo=new Font("arial",Font.PLAIN,20);
		reset.setFont(fo);
		exit.setFont(fo);
		exit.setForeground(Color.red);
		reset.addActionListener(new ResetListener());
		reset.setEnabled(false);
	}
	class TicListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			JButton bb=(JButton)evt.getSource();
			if(player==1)
			{
				bb.setIcon(icon1);
				label.setText("Second player turn...");
				label.setForeground(Color.white);
				pa[0].setBackground(Color.gray);
				player=2;
			}
			else if(player==2)
			{
				bb.setIcon(icon2);
				label.setText("First player turn...");
				label.setForeground(Color.blue);
				pa[0].setBackground(Color.cyan);
				player=1;
			}
			bb.setEnabled(false);
			findWinner();
			count++;
			if(count==9 && !winnerFound)
			{
				label.setText("GAME IS OVER");
				label.setForeground(Color.white);
				pa[0].setBackground(Color.red);
				reset.setEnabled(true);
				JOptionPane.showMessageDialog(frame,"No one is winner");
			}
		}//end of actionPerformed method
		private void findWinner()
		{
			//First row
			if(bt[0].getIcon()==icon1 && bt[1].getIcon()==icon1 && bt[2].getIcon()==icon1)
				announceWinner(0,1,2);
			else if(bt[0].getIcon()==icon2 && bt[1].getIcon()==icon2 && bt[2].getIcon()==icon2)
				announceWinner(0,1,2);
			//Second row
			else if(bt[3].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[5].getIcon()==icon1)
				announceWinner(3,4,5);
			else if(bt[3].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[5].getIcon()==icon2)
				announceWinner(3,4,5);
			//Third row
			else if(bt[6].getIcon()==icon1 && bt[7].getIcon()==icon1 && bt[8].getIcon()==icon1)
				announceWinner(6,7,8);
			else if(bt[6].getIcon()==icon2 && bt[7].getIcon()==icon2 && bt[8].getIcon()==icon2)
				announceWinner(6,7,8);
			//First column
			else if(bt[0].getIcon()==icon1 && bt[3].getIcon()==icon1 && bt[6].getIcon()==icon1)
				announceWinner(0,3,6);
			else if(bt[0].getIcon()==icon2 && bt[3].getIcon()==icon2 && bt[6].getIcon()==icon2)
				announceWinner(0,3,6);
			//Second column
			else if(bt[1].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[7].getIcon()==icon1)
				announceWinner(1,4,7);
			else if(bt[1].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[7].getIcon()==icon2)
				announceWinner(1,4,7);
			//Third column
			else if(bt[2].getIcon()==icon1 && bt[5].getIcon()==icon1 && bt[8].getIcon()==icon1)
				announceWinner(2,5,8);
			else if(bt[2].getIcon()==icon2 && bt[5].getIcon()==icon2 && bt[8].getIcon()==icon2)
				announceWinner(2,5,8);
			//First diagonal
			else if(bt[0].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[8].getIcon()==icon1)
				announceWinner(0,4,8);
			else if(bt[1].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[8].getIcon()==icon2)
				announceWinner(0,4,8);
			//Second diagonal
			else if(bt[2].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[6].getIcon()==icon1)
				announceWinner(2,4,6);
			else if(bt[2].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[6].getIcon()==icon2)
				announceWinner(2,4,6);
		}//end of findWinner
		private void announceWinner(int i,int j,int k)
		{
			winnerFound=true;
			reset.setEnabled(true);
			bt[i].setBackground(Color.green);
			bt[j].setBackground(Color.green);
			bt[k].setBackground(Color.green);
			label.setText("GAME IS OVER");
			label.setForeground(Color.white);
			pa[0].setBackground(Color.red);
			disableButtons();
			if(player==2)
				JOptionPane.showMessageDialog(frame,"First player has won");
			else
				JOptionPane.showMessageDialog(frame,"Second player has won");
		}//end of announceWinner method
		private void disableButtons()
		{
			for(int i=0;i<9;i++)
			{
				bt[i].setEnabled(false);
			}
		}
	}//end of TicListener
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			for(int i=0;i<9;i++)
			{
				bt[i].setIcon(null);//It will remove image
				bt[i].setBackground(Color.yellow);
				bt[i].setEnabled(true);
			}
			label.setText("First player turn...");
			label.setForeground(Color.blue);
			pa[0].setBackground(Color.cyan);
			player=1;
			count=0;
			winnerFound=false;
			reset.setEnabled(false);
		}

    }
    public static void main(String[] args) {
        new TicTacToeGame();
    }

    
}
