package manager;

import java.util.ArrayList;

import constant.Constant;
import control.OCell;

/**
 * �Z�����Ǘ�����N���X.
 */
public class CellManager {

	/** Singleton. */
	private static CellManager instance = new CellManager();

	/** �o�^���ꂽ�Z��. */
	private OCell[][] cells = new OCell[Constant.CELL_NUM_X][Constant.CELL_NUM_Y];

	/** �R���X�g���N�^. */
	private CellManager() {
		super();
	}
	
	/** Singleton���擾����. */
	public static CellManager getInstance() {
		return instance;
	}
	
	/**
	 * �Z����o�^����.
	 * @param cell �Z��
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 */
	public void registCell(OCell cell, int x, int y) {
		cells[x][y] = cell;
	}

	/**
	 * �Z�����擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z��
	 */
	public OCell getCell(int x, int y) {
		if (x < 0 || Constant.CELL_NUM_X < x) return null;
		if (y < 0 || Constant.CELL_NUM_Y < y) return null;
		return cells[x][y];
	}

	/**
	 * ���ׂẴZ�����擾����.
	 * @return ���ׂẴZ��
	 */
	public ArrayList<OCell> getAllCells() {
		ArrayList<OCell> cellArray = new ArrayList<OCell>();
		for (int i=0; i<Constant.CELL_NUM_X; i++) {
			for (int j=0; j<Constant.CELL_NUM_Y; j++) {
				cellArray.add(cells[i][j]);
			}
		}
		return cellArray;
	}	
	
	/**
	 * �΂��ݒ肳��Ă��Ȃ��Z�����擾����.
	 * @return �΂��ݒ肳��Ă��Ȃ��Z��
	 */
	public ArrayList<OCell> getEmptyCells() {
		ArrayList<OCell> cellArray = new ArrayList<OCell>();
		for (int i=0; i<Constant.CELL_NUM_X; i++) {
			for (int j=0; j<Constant.CELL_NUM_Y; j++) {
				if (cells[i][j].getCircle()==null) {
					cellArray.add(cells[i][j]);
				}
			}
		}
		return cellArray;
	}

	/**
	 * �w�肳�ꂽ�ʒu���������ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsUp(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerUp());
	}

	/**
	 * �w�肳�ꂽ�ʒu���牺�����ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsDown(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerDown());
	}

	/**
	 * �w�肳�ꂽ�ʒu���獶�����ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsLeft(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerLeft());
	}

	/**
	 * �w�肳�ꂽ�ʒu����E�����ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsRight(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerRight());
	}

	/**
	 * �w�肳�ꂽ�ʒu���獶������ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsUpperLeft(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerUpperLeft());
	}

	/**
	 * �w�肳�ꂽ�ʒu����E������ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsUpperRight(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerUpperRight());
	}

	/**
	 * �w�肳�ꂽ�ʒu���獶�������ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsDownerLeft(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerDownerLeft());
	}

	/**
	 * �w�肳�ꂽ�ʒu����E�������ɂ���΂����Z����ArrayList���擾����.
	 * @param x �Z���̈ʒu�ix���W�j
	 * @param y �Z���̈ʒu�iy���W�j
	 * @return �Z����ArrayList
	 */
	public ArrayList<OCell> getCellsDownerRight(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerDownerRight());
	}

	/**
	 * �w�肳�ꂽ�����ichecker�j�ɂ���΂����Z����ArrayList���擾����.
	 * @param xIn �Z���̊J�n�ʒu�ix���W�j
	 * @param yIn �Z���̊J�n�ʒu�iy���W�j
	 * @param checker �������w�肵���N���X
	 * @return �Z����ArrayList
	 */
	private ArrayList<OCell> getCellsForDirection(int xIn, int yIn, CellChecker checker) {
		int x = xIn;
		int y = yIn;
		ArrayList<OCell> cellArray = new ArrayList<OCell>();
		
		//�J�n�ʒu�̃Z���͊܂܂Ȃ����߂Ɏ��̈ʒu���擾����
		x = checker.getNextXPos(x);
		y = checker.getNextYPos(y);
		//�w��ʒu�ɐ΂����Z�����������ArrayList�ɃZ����ǉ�����
		while (checker.hasCellWithCircle(x, y)) {
			cellArray.add(cells[x][y]);
			x = checker.getNextXPos(x);
			y = checker.getNextYPos(y);
		}
		return cellArray;
	}
	
	/* Inner Class */
	
	/**
	 * �Z�������邩�ǂ��������̕����ɑ΂��Č������Ă����N���X.
	 * �g����O��Ƃ����N���X.
	 */
	abstract private class CellChecker {
		/**
		 * �w�肳�ꂽ�ʒu�̃Z�����΂�ێ����Ă��邩��������.
		 * @param x �Z���̈ʒu�ix���W�j
		 * @param y �Z���̈ʒu�iy���W�j
		 * @return �w�肳�ꂽ�ʒu�̃Z���ɐ΂�����ꍇ��true
		 */
		abstract protected boolean hasCellWithCircle(int x, int y);
		
		/**
		 * ����x���W���擾����.
		 * @param x ���݂�x���W
		 * @return ����x���W
		 */
		protected int getNextXPos(int x) {return x;}
		
		/**
		 * ����y���W���擾����.
		 * @param y ���݂�y���W
		 * @return ����y���W
		 */
		protected int getNextYPos(int y) {return y;}
	}
	
	/**
	 * ������ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerUp extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (0<=y && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextYPos(int y) {return y-1;}
	}

	/**
	 * �������ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerDown extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (y<Constant.CELL_NUM_Y && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextYPos(int y) {return y+1;}
	}
	
	/**
	 * �������ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerLeft extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (0<=x && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextXPos(int x) {return x-1;}
	}

	/**
	 * �E�����ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerRight extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (x<Constant.CELL_NUM_X && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextXPos(int x) {return x+1;}
	}

	/**
	 * ��������ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerUpperLeft extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (0<=x && 0<=y && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextXPos(int x) {return x-1;}
		@Override
		protected int getNextYPos(int y) {return y-1;}
	}

	/**
	 * �E������ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerUpperRight extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (x<Constant.CELL_NUM_X && 0<=y && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextXPos(int x) {return x+1;}
		@Override
		protected int getNextYPos(int y) {return y-1;}
	}

	/**
	 * ���������ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerDownerLeft extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (0<=x && y<Constant.CELL_NUM_Y && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextXPos(int x) {return x-1;}
		@Override
		protected int getNextYPos(int y) {return y+1;}
	}

	/**
	 * �E�������ɑΉ�����CellChecker�N���X.
	 */
	private class CellCheckerDownerRight extends CellChecker {
		@Override
		protected boolean hasCellWithCircle(int x, int y) {
			if (x<Constant.CELL_NUM_X && y<Constant.CELL_NUM_Y && cells[x][y].getCircle()!=null) return true;
			return false;
		}
		@Override
		protected int getNextXPos(int x) {return x+1;}
		@Override
		protected int getNextYPos(int y) {return y+1;}
	}

}
