package manager;

import java.util.ArrayList;

import constant.Constant;
import control.OCell;

/**
 * セルを管理するクラス.
 */
public class CellManager {

	/** Singleton. */
	private static CellManager instance = new CellManager();

	/** 登録されたセル. */
	private OCell[][] cells = new OCell[Constant.CELL_NUM_X][Constant.CELL_NUM_Y];

	/** コンストラクタ. */
	private CellManager() {
		super();
	}
	
	/** Singletonを取得する. */
	public static CellManager getInstance() {
		return instance;
	}
	
	/**
	 * セルを登録する.
	 * @param cell セル
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 */
	public void registCell(OCell cell, int x, int y) {
		cells[x][y] = cell;
	}

	/**
	 * セルを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セル
	 */
	public OCell getCell(int x, int y) {
		if (x < 0 || Constant.CELL_NUM_X < x) return null;
		if (y < 0 || Constant.CELL_NUM_Y < y) return null;
		return cells[x][y];
	}

	/**
	 * すべてのセルを取得する.
	 * @return すべてのセル
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
	 * 石が設定されていないセルを取得する.
	 * @return 石が設定されていないセル
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
	 * 指定された位置から上方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsUp(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerUp());
	}

	/**
	 * 指定された位置から下方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsDown(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerDown());
	}

	/**
	 * 指定された位置から左方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsLeft(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerLeft());
	}

	/**
	 * 指定された位置から右方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsRight(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerRight());
	}

	/**
	 * 指定された位置から左上方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsUpperLeft(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerUpperLeft());
	}

	/**
	 * 指定された位置から右上方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsUpperRight(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerUpperRight());
	}

	/**
	 * 指定された位置から左下方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsDownerLeft(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerDownerLeft());
	}

	/**
	 * 指定された位置から右下方向にある石を持つセルのArrayListを取得する.
	 * @param x セルの位置（x座標）
	 * @param y セルの位置（y座標）
	 * @return セルのArrayList
	 */
	public ArrayList<OCell> getCellsDownerRight(int x, int y) {
		return getCellsForDirection(x, y, new CellCheckerDownerRight());
	}

	/**
	 * 指定された方向（checker）にある石を持つセルのArrayListを取得する.
	 * @param xIn セルの開始位置（x座標）
	 * @param yIn セルの開始位置（y座標）
	 * @param checker 方向を指定したクラス
	 * @return セルのArrayList
	 */
	private ArrayList<OCell> getCellsForDirection(int xIn, int yIn, CellChecker checker) {
		int x = xIn;
		int y = yIn;
		ArrayList<OCell> cellArray = new ArrayList<OCell>();
		
		//開始位置のセルは含まないために次の位置を取得する
		x = checker.getNextXPos(x);
		y = checker.getNextYPos(y);
		//指定位置に石を持つセルがある限りArrayListにセルを追加する
		while (checker.hasCellWithCircle(x, y)) {
			cellArray.add(cells[x][y]);
			x = checker.getNextXPos(x);
			y = checker.getNextYPos(y);
		}
		return cellArray;
	}
	
	/* Inner Class */
	
	/**
	 * セルがあるかどうかを特定の方向に対して検査していくクラス.
	 * 拡張を前提としたクラス.
	 */
	abstract private class CellChecker {
		/**
		 * 指定された位置のセルが石を保持しているか検査する.
		 * @param x セルの位置（x座標）
		 * @param y セルの位置（y座標）
		 * @return 指定された位置のセルに石がある場合はtrue
		 */
		abstract protected boolean hasCellWithCircle(int x, int y);
		
		/**
		 * 次のx座標を取得する.
		 * @param x 現在のx座標
		 * @return 次のx座標
		 */
		protected int getNextXPos(int x) {return x;}
		
		/**
		 * 次のy座標を取得する.
		 * @param y 現在のy座標
		 * @return 次のy座標
		 */
		protected int getNextYPos(int y) {return y;}
	}
	
	/**
	 * 上方向に対応するCellCheckerクラス.
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
	 * 下方向に対応するCellCheckerクラス.
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
	 * 左方向に対応するCellCheckerクラス.
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
	 * 右方向に対応するCellCheckerクラス.
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
	 * 左上方向に対応するCellCheckerクラス.
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
	 * 右上方向に対応するCellCheckerクラス.
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
	 * 左下方向に対応するCellCheckerクラス.
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
	 * 右下方向に対応するCellCheckerクラス.
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
