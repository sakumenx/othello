package control;

import java.awt.GridLayout;
import javax.swing.JPanel;

import manager.CellManager;
import constant.Constant;

/**
 * �I�Z���Q�[���̔Ֆ�.
 */
public class OPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** �R���X�g���N�^. */
	public OPanel() {
		super();
		setParams();
		setItems();
	}
	
	/** �Ֆʂ̃p�����[�^��ݒ肷��. */
	private void setParams() {
		//Grid��ɕ��i��z�u�\�ɂ���
		setLayout(new GridLayout(Constant.CELL_NUM_X, Constant.CELL_NUM_Y));
	}
	
	/** �Ֆʂɕ��i��ݒ肷��. */
	private void setItems() {
		int n = Constant.CELL_NUM_X * Constant.CELL_NUM_Y;
		for(int i=0; i<n;i++){
			//�z�u�ʒu���v�Z����
			int x = i % Constant.CELL_NUM_X;
			int y = i / Constant.CELL_NUM_X;

			//�ՖʂɃZ����z�u����
			OCell cell = new OCell(x, y);
			add(cell);

			//�Z�����}�l�[�W���[�ɓo�^����
			CellManager.getInstance().registCell(cell, x, y);
		}
	}
}
