package control;

import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.MessageManager;

/**
 * �I�Z���Q�[���̃��b�Z�[�W�̈�.
 */
public class OMessage extends JPanel {

	private static final long serialVersionUID = 1L;

	/** ���b�Z�[�W���x��. */
	private JLabel label = new JLabel();
	
	/** �R���X�g���N�^. */
	public OMessage() {
		super();
		setParams();
		setItems();
	}

	/** ���b�Z�[�W�̈�̃p�����[�^��ݒ肷��. */
	private void setParams() {
		//���b�Z�[�W�̈�̃��x�������b�Z�[�W�}�l�[�W���[�ɓo�^����
		MessageManager.getInstance().setLabel(label);
	}

	/** ���b�Z�[�W�̈�ɕ��i��ݒ肷��. */
	private void setItems() {
		add(label);
	}

}
