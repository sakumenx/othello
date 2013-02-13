package control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import mediator.Mediator;
import status.Circle;

/**
 * �I�Z���Q�[���̃Z��.
 */
public class OCell extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	/** �ێ������. */
	private Circle circle;
	/** �Z���̈ʒu�ix���W�j. */
	private int xPos;
	/** �Z���̈ʒu�iy���W�j. */
	private int yPos;
	
	/**
	 * �R���X�g���N�^.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 */
	public OCell(int x, int y) {
		super();
		xPos = x;
		yPos = y;
		
		//�}�E�X�C�x���g��Listen����
		addMouseListener(this);
		setParams();
	}
	
	/** �Z���̃p�����[�^��ݒ肷��. */
	private void setParams() {
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.GREEN);
	}

	/** �΂��Z�b�g����. */
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	/** �΂��Q�b�g����. */
	public Circle getCircle() {
		return circle;
	}
	
	/** �Z���̈ʒu�ix���W�j���Q�b�g����. */
	public int getXPos() {
		return xPos;
	}
	
	/** �Z���̈ʒu�ix���W�j���Q�b�g����. */
	public int getYPos() {
		return yPos;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (circle!=null) {
			circle.paintCricle(g);
		}
 	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Mediator.getInstance().cellClicked(this);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {return;}

	@Override
	public void mouseExited(MouseEvent arg0) {return;}

	@Override
	public void mousePressed(MouseEvent arg0) {return;}

	@Override
	public void mouseReleased(MouseEvent arg0) {return;}

}
