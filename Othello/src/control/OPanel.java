package control;

import java.awt.GridLayout;
import javax.swing.JPanel;

import manager.CellManager;
import constant.Constant;

/**
 * オセロゲームの盤面.
 */
public class OPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** コンストラクタ. */
	public OPanel() {
		super();
		setParams();
		setItems();
	}
	
	/** 盤面のパラメータを設定する. */
	private void setParams() {
		//Grid状に部品を配置可能にする
		setLayout(new GridLayout(Constant.CELL_NUM_X, Constant.CELL_NUM_Y));
	}
	
	/** 盤面に部品を設定する. */
	private void setItems() {
		int n = Constant.CELL_NUM_X * Constant.CELL_NUM_Y;
		for(int i=0; i<n;i++){
			//配置位置を計算する
			int x = i % Constant.CELL_NUM_X;
			int y = i / Constant.CELL_NUM_X;

			//盤面にセルを配置する
			OCell cell = new OCell(x, y);
			add(cell);

			//セルをマネージャーに登録する
			CellManager.getInstance().registCell(cell, x, y);
		}
	}
}
