package TicTacToe;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class TicTacToe{
	//main����
	public static void main(String[] args){
		new TicTacToe();
	}
	
	private char whoseTurn = 'X';       // ��ʾ��ǰ��Ϸ��
	private Cell[][] cells = new Cell[3][3];   //��ʾ�Ź��񣬵�Ԫ�������ά����
	private JLabel jlblStatus = new JLabel("X's turn to play");
	
	public TicTacToe(){
		
		JPanel p = new JPanel(new GridLayout(3,3,0,0));
		JFrame frame = new JFrame();
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j<3 ; j++)
			{
				cells[i][j] = new Cell();
				p.add(cells[i][j]);
			}
		}
		
		
		frame.getContentPane().add(p,BorderLayout.CENTER);
		frame.getContentPane().add(jlblStatus,BorderLayout.SOUTH);
		frame.setSize(400,400);
		//frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public boolean isFull(){    //�жϾŹ����Ƿ�����
		for (int i = 0;i<3;i++){
			for (int j=0;j<3;j++)
			{
				if (cells[i][j].getToken() == ' ')
					return false;
			}
		}
		return true;
	}
	
	public boolean isWon(char token){//�ж��Ƿ����ʤ����
		for (int i = 0 ;i<3;i++)
		{
			if ((cells[i][0].getToken() == token) && (cells[i][1].getToken()==token)&& (cells[i][2].getToken()==token))
				return true;
		}
		for (int j = 0 ;j<3;j++)
		{
			if ((cells[0][j].getToken() == token) && (cells[1][j].getToken()==token)&& (cells[2][j].getToken()==token))
				return true;
		}
		if ((cells[0][0].getToken() == token) && (cells[1][1].getToken()==token)&& (cells[2][2].getToken()==token))
			return true;
		if ((cells[0][2].getToken() == token) && (cells[1][1].getToken()==token)&& (cells[2][0].getToken()==token))
			return true;
		return false;
	}
	
	//��Ԫ������
	public class Cell extends JPanel implements MouseListener{
		private char token =' ';//��ʼ״̬Ϊ��
		
		public Cell(){
			setBorder(new LineBorder(Color.black,1));
			addMouseListener(this);
		}
		//��ȡ��Ԫ��״̬
		public char getToken(){
			return token;
		}
		//���õ�Ԫ��״̬
		public void setToken(char c){
			token =c;
			repaint();
		}
		//������Ԫ��״̬
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			if (token== 'X'){
				g.drawLine(10,10,getWidth()-10,getHeight()-10);
				g.drawLine(getWidth()-10,10,10,getHeight()-10);
			}
			else if (token == 'O'){
				g.drawOval(10, 10, getWidth()-20, getHeight()-20);
			}
		}
		//��Ӧ����¼�
		public void mouseClicked(MouseEvent e){
			if (token ==' ' && whoseTurn!=' '){
				setToken(whoseTurn);
				
				if (isWon(whoseTurn)){
					jlblStatus.setText(whoseTurn + "won! The game is over");
					whoseTurn = ' ';
				}
				else if(isFull()){
					jlblStatus.setText("Draw!The game is over");
					whoseTurn = ' ';
				}
				else {
					whoseTurn = (whoseTurn == 'X') ? 'O':'X';
					jlblStatus.setText(whoseTurn + "'s turn");
				}
			}
			
		}
		public void mousePressed(MouseEvent e){
		}
		public void mouseReleased(MouseEvent e){
		}
		public void mouseEntered(MouseEvent e){
		}
		public void mouseExited(MouseEvent e){
		}
	}
}
