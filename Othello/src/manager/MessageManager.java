package manager;

import javax.swing.JLabel;

/**
 * メッセージを発行するクラス.
 */
public class MessageManager {

	/** Singleton. */
	private static MessageManager instance = new MessageManager();
	/** メッセージ領域ラベル. */
	private JLabel label;
	
	/** コンストラクタ. */
	private MessageManager() {
		super();
	}
	
	/** Singletonを取得する. */
	public static MessageManager getInstance() {
		return instance;
	}
	
	/** メッセージ領域ラベルを設定する. */
	public void setLabel(JLabel label) {
		this.label = label;
	}

	/**
	 * メッセージを発行する.
	 * @param message 発行するメッセージ
	 */
	public void setMessage(String message) {
		label.setText(message);
		label.repaint();
	}
	
}
