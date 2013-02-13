package control;

import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.MessageManager;

/**
 * オセロゲームのメッセージ領域.
 */
public class OMessage extends JPanel {

	private static final long serialVersionUID = 1L;

	/** メッセージラベル. */
	private JLabel label = new JLabel();
	
	/** コンストラクタ. */
	public OMessage() {
		super();
		setParams();
		setItems();
	}

	/** メッセージ領域のパラメータを設定する. */
	private void setParams() {
		//メッセージ領域のラベルをメッセージマネージャーに登録する
		MessageManager.getInstance().setLabel(label);
	}

	/** メッセージ領域に部品を設定する. */
	private void setItems() {
		add(label);
	}

}
