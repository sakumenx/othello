package frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import mediator.Mediator;
import control.OMessage;
import control.OPanel;

/**
 * �I�Z���Q�[���̃E�B���h�E.
 */
public class OFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/** �R���X�g���N�^. */
	public OFrame() {
		super();
		setParams();
		setItems();
		//�E�B���h�E��\������
		setVisible(true);

		//�Ֆʂ̏������������s��
		Mediator.getInstance().initializeCells();
	}

	/** �E�B���h�E�̃p�����[�^��ݒ肷��. */
	private void setParams() {
		setTitle("othello");
		setBounds(0, 0, 400, 440);
		setLayout(new BorderLayout());
		//�E�B���h�E������ꂽ���ɃA�v���P�[�V�������I������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** �E�B���h�E�ɕ��i��z�u����. */
	private void setItems() {
		//���b�Z�[�W�G���A��ǉ�
		add(new OMessage(),BorderLayout.NORTH);
		//�Ֆʂ�ǉ�
		add(new OPanel());
	}
}
