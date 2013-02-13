package factory;

import manager.CircleManager;
import manager.MessageManager;
import status.Circle;

/**
 * �΂𐶐�����Factory�N���X.
 */
public class CircleFactory {

	/** Singleton. */
	private static CircleFactory factory = new CircleFactory();
	
	/** �R���X�g���N�^, */
	private CircleFactory() {
		super();
	}
	
	/** Singleton���擾����. */
	public static CircleFactory getFactory() {
		return factory;
	}
	
	/**
	 * �΂𐶐�����.
	 * @return ��
	 */
	public Circle createCricle() {
		//���݂̃^�[���ɑΉ�����΂̃N���X���擾����
		Class<? extends Circle> circleClass = CircleManager.getInstance().getCurrentCircle();

		Circle circle = null;
		try {
			//�΂��C���X�^���X������
			circle = circleClass.newInstance();
		} catch (InstantiationException e) {
			//��O�������Ƀ��b�Z�[�W�̈�ɗ�O����\������
			MessageManager.getInstance().setMessage(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			//��O�������Ƀ��b�Z�[�W�̈�ɗ�O����\������
			MessageManager.getInstance().setMessage(e.toString());
			e.printStackTrace();
		}

		return circle;
	}
}
