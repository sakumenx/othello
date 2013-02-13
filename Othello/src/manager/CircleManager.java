package manager;

import constant.Constant;
import status.BlackCircle;
import status.Circle;
import status.WhiteCircle;

/**
 * �΂��Ǘ�����N���X.
 */
public class CircleManager {

	/** �΂̎��. */
	private static final int TURN_CYCLE = 2;

	/** Singleton. */
	private static CircleManager instance = new CircleManager();
	/** ���݂̃^�[��. */
	private boolean isBlack = true;
	
	/** ���΂̐�. */
	private int numBlack = 0;
	/** ���΂̐�. */
	private int numWhite = 0;
	
	/** �R���X�g���N�^. */
	private CircleManager() {
		super();
	}
	
	/** Singleton���擾����. */
	public static CircleManager getInstance() {
		return instance;
	}

	/** �^�[����؂�Ԃ�. */
	public void changeTurn() {
		isBlack = !isBlack;
	}

	/** �΂̎�ނ̐����擾����. */
	public int getTurnCycle() {
		return TURN_CYCLE;
	}
	
	/**
	 * ���݂̃^�[���ɑΉ�����΃N���X�i��C���X�^���X�j���擾����.
	 * @return ���݂̃^�[���ɑΉ�����΃N���X
	 */
	public Class<? extends Circle> getCurrentCircle() {
		if (isBlack) {
			return BlackCircle.class;
		} else {
			return WhiteCircle.class;
		}
	}

	/**
	 * �����̐΃C���X�^���X�����݂̃^�[���ƈ�v���邩��������.
	 * @param circle �΃C���X�^���X
	 * @return ���݂̃^�[���ƈ�v���Ă���ꍇtrue
	 */
	public boolean isSameCircleClass(Circle circle) {
		boolean result = false;
		if (isBlack) {
			result = circle instanceof BlackCircle;
		} else {
			result = circle instanceof WhiteCircle;
		}
		return result;
	}

	/**
	 * ���݂̃^�[�����������b�Z�[�W���擾����.
	 * @return ���݂̃^�[�����������b�Z�[�W
	 */
	public String getTurnMessage() {
		String msg = null;
		if (isBlack) {
			msg = Constant.MSG_TURN_BLACK;
		} else {
			msg = Constant.MSG_TURN_WHITE;
		}
		
		return msg;
	}

	/**
	 * �����ϐ��̐΂̐������Z�b�g����.
	 */
	public void resetCircleCounter() {
		numBlack = 0;
		numWhite = 0;
	}
	
	/**
	 * �΂̐����J�E���g����.
	 * @param circle �΃C���X�^���X
	 */
	public void countCircle(Circle circle) {
		if (circle instanceof BlackCircle) {
			numBlack++;
		} else if (circle instanceof WhiteCircle) {
			numWhite++;
		}
	}

	/**
	 * �Q�[���̏��҂Ɛ΂̐��𕶎���Ŏ擾����.
	 * @return �Q�[���̏��҂Ɛ΂̐�
	 */
	public String getWinnerMessage() {
		String message;
		//�Q�[���̏��҂����肷��
		if (numWhite < numBlack) {
			message = Constant.MSG_WINNER_BLACK;
		} else if (numBlack < numWhite) {
			message = Constant.MSG_WINNER_WHITE;
		} else {
			//��������
			message = Constant.MSG_WINNER_EVEN;
		}

		//�΂̐������b�Z�[�W�ɒǉ�����
		message = message + " ��" + numBlack + "�� �� ��" + numWhite + "��";
		return message;
	}
	
}
