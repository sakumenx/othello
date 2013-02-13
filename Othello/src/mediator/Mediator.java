package mediator;

import java.util.ArrayList;
import java.util.Iterator;

import manager.CellManager;
import manager.CircleManager;
import manager.MessageManager;
import control.OCell;
import factory.CircleFactory;

/**
 * �I�Z���Q�[���̏������i��N���X.
 */
public class Mediator {

	/** Singleton. */
	private static Mediator instance = new Mediator();

	/** �R���X�g���N�^. */
	private Mediator() {
		super();
	}

	/** Singleton���擾����. */
	public static Mediator getInstance() {
		return instance;
	}

	/**
	 * �Z���̏����z�u���s��.
	 */
	public void initializeCells() {
		CellManager cellManager = CellManager.getInstance();

		//������4�Z���ɐ΂�z�u����
		int[][] buf = { { 3, 3 }, { 3, 4 }, { 4, 4 }, { 4, 3 } };
		for (int i = 0; i < buf.length; i++) {
			OCell cell = cellManager.getCell(buf[i][0], buf[i][1]);
			setCircle(cell);
			changeTurn();
		}
	}

	/**
	 * �Z�����N���b�N���ꂽ�Ƃ��ɌĂяo�����.
	 * �Z���ɐ΂�z�u���^�[�����I������.
	 * �I�������Ɉ�v�����ꍇ�̓Q�[�����I������.
	 * @param cell �N���b�N���ꂽ�Z��
	 */
	public void cellClicked(OCell cell) {
		//�Z�����z�u�\����������
		if (!isCellLocatableWithFlip(cell)) return;
		
		setCircle(cell);
		changeTurn();
		checkGameEnd();
	}

	/**
	 * �Z���ɐ΂�z�u����.
	 * @param cell �Z��
	 */
	private void setCircle(OCell cell) {
		//���������΂��Z���ɔz�u����
		cell.setCircle(CircleFactory.getFactory().createCricle());
		//�Z�����ĕ`�悷��
		cell.repaint();
	}

	/**
	 * �^�[�����I�������b�Z�[�W��\������.
	 */
	private void changeTurn() {
		//�^�[����؂�ւ���
		CircleManager.getInstance().changeTurn();
		//�V�����^�[���ɑΉ����郁�b�Z�[�W��\������
		MessageManager.getInstance().setMessage(
				CircleManager.getInstance().getTurnMessage());
	}
	
	/**
	 * ���݂̃^�[���ɂ����Đ΂��z�u�\����������.
	 * �΂�z�u�\�ȃZ�����Ȃ��ꍇ�̓^�[����؂�ւ���.
	 * ���ׂẴ^�[���ɂ����Đ΂�z�u�s�\�ȏꍇ�̓Q�[�����I������.
	 */
	private void checkGameEnd() {
		//1�T�C�N�����\������^�[�������擾����
		int turnCycle = CircleManager.getInstance().getTurnCycle();
		int i = 0;
		//�΂�z�u�\�ȃ^�[�������邩��������
		while (!checkNextTurnAvailable() && i < turnCycle) {
			//�΂�z�u�s�\�ȏꍇ�̓^�[����؂�ւ���
			changeTurn();
			i++;
		}
		
		//���ׂẴ^�[���ɂ����Đ΂��z�u�s�\�ȏꍇ
		if (i == turnCycle) {
			//�Q�[���̏I���������Ăяo��
			exitGame();
		}
	}

	/**
	 * �΂��z�u�\�ȃZ�������݂��邩��������.
	 * @return �΂��z�u�\�ȏꍇ��true
	 */
	private boolean checkNextTurnAvailable() {
		//�΂��z�u����Ă��Ȃ��Z�����擾����
		ArrayList<OCell> cellArray = CellManager.getInstance().getEmptyCells();
		//�擾�����Z���ɑ΂��ăC�e���[�g�������s��
		Iterator<OCell> cellItelator = cellArray.iterator();
		while (cellItelator.hasNext()) {
			//�΂�z�u�\�ȃZ��������ꍇ��true��ԋp
			OCell cell = cellItelator.next();
			if (isCellLocatable(cell)) return true;
		}
		//�΂�z�u�\�ȃZ����������Ȃ������ꍇ��false��ԋp
		return false;
	}

	/**
	 * �Q�[���̏I���������s��.
	 */
	private void exitGame() {
		CircleManager circleManager = CircleManager.getInstance();
		//�΂̐��̃J�E���^�����Z�b�g����
		circleManager.resetCircleCounter();

		//���ׂẴZ�����擾����
		ArrayList<OCell> cellArray = CellManager.getInstance().getAllCells();
		//�擾�����Z���ɑ΂��ăC�e���[�g�������s��
		Iterator<OCell> cellItelator = cellArray.iterator();
		while (cellItelator.hasNext()) {
			//���݂���΂��T�[�N���}�l�[�W���[�ɒʒm����
			OCell cell = cellItelator.next();
			if (cell.getCircle() != null) {
				circleManager.countCircle(cell.getCircle());
			}
		}

		//���҂����b�Z�[�W�̈�ɕ\������
		MessageManager.getInstance().setMessage(
				circleManager.getWinnerMessage());
	}

	/**
	 * �w�肵���Z���ɐ΂�z�u�\����������ƂƂ��ɐ΂̔��]���s��.
	 * @param cell �w��Z��
	 * @return �΂��z�u�\�ȏꍇ��true
	 */
	private boolean isCellLocatableWithFlip(OCell cell) {
		return isCellLocatable(cell, true);
	}

	/**
	 * �w�肵���Z���ɐ΂�z�u�\����������.
	 * @param cell �w��Z��
	 * @return �΂��z�u�\�ȏꍇ��true
	 */
	private boolean isCellLocatable(OCell cell) {
		return isCellLocatable(cell, false);
	}

	/**
	 * �w�肵���Z���ɐ΂�z�u�\����������.
	 * ���̐΂𔽓]���邩�ǂ�����flip�ɂĎw�肷��.
	 * @param cell �w��Z��
	 * @param flip ���̐΂𔽓]����ꍇtrue���w��
	 * @return �΂��z�u�\�ȏꍇ��true
	 */
	private boolean isCellLocatable(OCell cell, boolean flip) {
		//�Z���ɐ΂��z�u����Ă���ꍇ��false
		if (cell.getCircle() != null) return false;

		//�ʒu���ƃt���O��p�ӂ���
		int x = cell.getXPos();
		int y = cell.getYPos();
		boolean flag = false;
		CellManager cellManager = CellManager.getInstance();

		//�e�����̃Z���z���ۑ�
		ArrayList<ArrayList<OCell>> directionArray = new ArrayList<ArrayList<OCell>>();
		directionArray.add(cellManager.getCellsUp(x, y));
		directionArray.add(cellManager.getCellsDown(x, y));
		directionArray.add(cellManager.getCellsLeft(x, y));
		directionArray.add(cellManager.getCellsRight(x, y));
		directionArray.add(cellManager.getCellsUpperLeft(x, y));
		directionArray.add(cellManager.getCellsUpperRight(x, y));
		directionArray.add(cellManager.getCellsDownerLeft(x, y));
		directionArray.add(cellManager.getCellsDownerRight(x, y));
		
		//�e�����ɑ΂��ď���
		Iterator<ArrayList<OCell>> directionIterator = directionArray.iterator();
		while (directionIterator.hasNext()) {
			//��������ɑ΂���Z���̔z������o��
			ArrayList<OCell> cellArray = directionIterator.next();
			//�΂��z�u�\�ȏꍇ��flag�����Ă�
			if (isCellLocatableForDirection(cellArray)) {
				//flip�w��̏ꍇ�͐΂𔽓]
				if (flip) replaceCells(cellArray);
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * �w�肳�ꂽ�����icellArray�j�ɑ΂��Đ΂�z�u�\����������.
	 * @param cellArray �Z���̔z��
	 * @return �΂��z�u�\�ȏꍇ��true
	 */
	private boolean isCellLocatableForDirection(ArrayList<OCell> cellArray) {
		CircleManager circleManager = CircleManager.getInstance();
		Iterator<OCell> cellIterator = cellArray.iterator();

		//�Z�����Ȃ��ꍇ��false
		if (!cellIterator.hasNext()) return false;
		//�ŏ��̃Z���̐΂����݂̃^�[���ƈ�v����ꍇ��false
		if (circleManager.isSameCircleClass(cellIterator.next().getCircle())) return false;

		//2�ڈȍ~�̃Z���Ɍ��݂̃^�[���ƈ�v����΂�����ꍇ��true
		while (cellIterator.hasNext()) {
			if (circleManager.isSameCircleClass(cellIterator.next().getCircle())) return true;
		}
		
		//2�ڈȍ~�̃Z���Ɍ��݂̃^�[���ƈ�v����΂��Ȃ��ꍇ��false
		return false;
	}

	/**
	 * �w�肳�ꂽ�Z���̔z��̐΂𔽓]����.
	 * @param cellArray �Z���̔z��
	 */
	private void replaceCells(ArrayList<OCell> cellArray) {
		CircleManager circleManager = CircleManager.getInstance();
		Iterator<OCell> cellIterator = cellArray.iterator();

		//�Z���̔z��ɑ΂��ăC�e���[�g����
		while (cellIterator.hasNext()) {
			OCell cell = cellIterator.next();
			//���݂̃^�[���ƈ�v����΂ɓ��B�����ꍇ�͏������I������
			if (circleManager.isSameCircleClass(cell.getCircle())) return;
			//�΂𔽓]����
			setCircle(cell);
		}
	}

}
