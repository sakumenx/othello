package status;

import java.awt.Color;
import java.awt.Graphics;

/**
 * �΃N���X.
 * �q�N���X�ɂĐF����ݒ肷��K�v������.
 */
public abstract class Circle {

	protected Color color;

	/**
	 * �~��`�悷��.
	 * ���̃��\�b�h��OCell��repaint���Ă΂��Ǝ��s�����.
	 * @param g Graphics
	 */
	public void paintCricle(Graphics g) {
		g.setColor(color);
		g.fillOval(3, 3, 20, 20);
	}
}
