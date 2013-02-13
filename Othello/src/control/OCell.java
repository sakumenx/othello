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
 * オセロゲームのセル.
 */
public class OCell extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	/** 保持する石. */
	private Circle circle;
	/** セルの位置（x座標）. */
	private int xPos;
	/** セルの位置（y座標）. */
	private int yPos;
	
	/**
	 * コンストラクタ.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 */
	public OCell(int x, int y) {
		super();
		xPos = x;
		yPos = y;
		
		//マウスイベントをListenする
		addMouseListener(this);
		setParams();
	}
	
	/** セルのパラメータを設定する. */
	private void setParams() {
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.GREEN);
	}

	/** 石をセットする. */
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	/** 石をゲットする. */
	public Circle getCircle() {
		return circle;
	}
	
	/** セルの位置（x座標）をゲットする. */
	public int getXPos() {
		return xPos;
	}
	
	/** セルの位置（x座標）をゲットする. */
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
