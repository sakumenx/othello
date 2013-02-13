package status;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 石クラス.
 * 子クラスにて色情報を設定する必要がある.
 */
public abstract class Circle {

	protected Color color;

	/**
	 * 円を描画する.
	 * このメソッドはOCellのrepaintが呼ばれると実行される.
	 * @param g Graphics
	 */
	public void paintCricle(Graphics g) {
		g.setColor(color);
		g.fillOval(3, 3, 20, 20);
	}
}
