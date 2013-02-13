package manager;

import javax.swing.JLabel;

/**
 * ���b�Z�[�W�𔭍s����N���X.
 */
public class MessageManager {

	/** Singleton. */
	private static MessageManager instance = new MessageManager();
	/** ���b�Z�[�W�̈惉�x��. */
	private JLabel label;
	
	/** �R���X�g���N�^. */
	private MessageManager() {
		super();
	}
	
	/** Singleton���擾����. */
	public static MessageManager getInstance() {
		return instance;
	}
	
	/** ���b�Z�[�W�̈惉�x����ݒ肷��. */
	public void setLabel(JLabel label) {
		this.label = label;
	}

	/**
	 * ���b�Z�[�W�𔭍s����.
	 * @param message ���s���郁�b�Z�[�W
	 */
	public void setMessage(String message) {
		label.setText(message);
		label.repaint();
	}
	
}
