package frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import mediator.Mediator;
import control.OMessage;
import control.OPanel;

/**
 * オセロゲームのウィンドウ.
 */
public class OFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/** コンストラクタ. */
	public OFrame() {
		super();
		setParams();
		setItems();
		//ウィンドウを表示する
		setVisible(true);

		//盤面の初期化処理を行う
		Mediator.getInstance().initializeCells();
	}

	/** ウィンドウのパラメータを設定する. */
	private void setParams() {
		setTitle("othello");
		setBounds(0, 0, 400, 440);
		setLayout(new BorderLayout());
		//ウィンドウが閉じられた時にアプリケーションも終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** ウィンドウに部品を配置する. */
	private void setItems() {
		//メッセージエリアを追加
		add(new OMessage(),BorderLayout.NORTH);
		//盤面を追加
		add(new OPanel());
	}
}
