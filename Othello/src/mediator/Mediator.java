package mediator;

import java.util.ArrayList;
import java.util.Iterator;

import manager.CellManager;
import manager.CircleManager;
import manager.MessageManager;
import control.OCell;
import factory.CircleFactory;

/**
 * オセロゲームの処理を司るクラス.
 */
public class Mediator {

	/** Singleton. */
	private static Mediator instance = new Mediator();

	/** コンストラクタ. */
	private Mediator() {
		super();
	}

	/** Singletonを取得する. */
	public static Mediator getInstance() {
		return instance;
	}

	/**
	 * セルの初期配置を行う.
	 */
	public void initializeCells() {
		CellManager cellManager = CellManager.getInstance();

		//中央の4セルに石を配置する
		int[][] buf = { { 3, 3 }, { 3, 4 }, { 4, 4 }, { 4, 3 } };
		for (int i = 0; i < buf.length; i++) {
			OCell cell = cellManager.getCell(buf[i][0], buf[i][1]);
			setCircle(cell);
			changeTurn();
		}
	}

	/**
	 * セルがクリックされたときに呼び出される.
	 * セルに石を配置しターンを終了する.
	 * 終了条件に一致した場合はゲームを終了する.
	 * @param cell クリックされたセル
	 */
	public void cellClicked(OCell cell) {
		//セルが配置可能か検査する
		if (!isCellLocatableWithFlip(cell)) return;
		
		setCircle(cell);
		changeTurn();
		checkGameEnd();
	}

	/**
	 * セルに石を配置する.
	 * @param cell セル
	 */
	private void setCircle(OCell cell) {
		//生成した石をセルに配置する
		cell.setCircle(CircleFactory.getFactory().createCricle());
		//セルを再描画する
		cell.repaint();
	}

	/**
	 * ターンを終了しメッセージを表示する.
	 */
	private void changeTurn() {
		//ターンを切り替える
		CircleManager.getInstance().changeTurn();
		//新しいターンに対応するメッセージを表示する
		MessageManager.getInstance().setMessage(
				CircleManager.getInstance().getTurnMessage());
	}
	
	/**
	 * 現在のターンにおいて石が配置可能か検査する.
	 * 石を配置可能なセルがない場合はターンを切り替える.
	 * すべてのターンにおいて石を配置不可能な場合はゲームを終了する.
	 */
	private void checkGameEnd() {
		//1サイクルを構成するターン数を取得する
		int turnCycle = CircleManager.getInstance().getTurnCycle();
		int i = 0;
		//石を配置可能なターンがあるか検査する
		while (!checkNextTurnAvailable() && i < turnCycle) {
			//石を配置不可能な場合はターンを切り替える
			changeTurn();
			i++;
		}
		
		//すべてのターンにおいて石が配置不可能な場合
		if (i == turnCycle) {
			//ゲームの終了処理を呼び出す
			exitGame();
		}
	}

	/**
	 * 石が配置可能なセルが存在するか検査する.
	 * @return 石が配置可能な場合はtrue
	 */
	private boolean checkNextTurnAvailable() {
		//石が配置されていないセルを取得する
		ArrayList<OCell> cellArray = CellManager.getInstance().getEmptyCells();
		//取得したセルに対してイテレート処理を行う
		Iterator<OCell> cellItelator = cellArray.iterator();
		while (cellItelator.hasNext()) {
			//石を配置可能なセルがある場合はtrueを返却
			OCell cell = cellItelator.next();
			if (isCellLocatable(cell)) return true;
		}
		//石を配置可能なセルが見つからなかった場合はfalseを返却
		return false;
	}

	/**
	 * ゲームの終了処理を行う.
	 */
	private void exitGame() {
		CircleManager circleManager = CircleManager.getInstance();
		//石の数のカウンタをリセットする
		circleManager.resetCircleCounter();

		//すべてのセルを取得する
		ArrayList<OCell> cellArray = CellManager.getInstance().getAllCells();
		//取得したセルに対してイテレート処理を行う
		Iterator<OCell> cellItelator = cellArray.iterator();
		while (cellItelator.hasNext()) {
			//存在する石をサークルマネージャーに通知する
			OCell cell = cellItelator.next();
			if (cell.getCircle() != null) {
				circleManager.countCircle(cell.getCircle());
			}
		}

		//勝者をメッセージ領域に表示する
		MessageManager.getInstance().setMessage(
				circleManager.getWinnerMessage());
	}

	/**
	 * 指定したセルに石を配置可能か検査するとともに石の反転を行う.
	 * @param cell 指定セル
	 * @return 石が配置可能な場合はtrue
	 */
	private boolean isCellLocatableWithFlip(OCell cell) {
		return isCellLocatable(cell, true);
	}

	/**
	 * 指定したセルに石を配置可能か検査する.
	 * @param cell 指定セル
	 * @return 石が配置可能な場合はtrue
	 */
	private boolean isCellLocatable(OCell cell) {
		return isCellLocatable(cell, false);
	}

	/**
	 * 指定したセルに石を配置可能か検査する.
	 * 他の石を反転するかどうかをflipにて指定する.
	 * @param cell 指定セル
	 * @param flip 他の石を反転する場合trueを指定
	 * @return 石が配置可能な場合はtrue
	 */
	private boolean isCellLocatable(OCell cell, boolean flip) {
		//セルに石が配置されている場合はfalse
		if (cell.getCircle() != null) return false;

		//位置情報とフラグを用意する
		int x = cell.getXPos();
		int y = cell.getYPos();
		boolean flag = false;
		CellManager cellManager = CellManager.getInstance();

		//各方向のセル配列を保存
		ArrayList<ArrayList<OCell>> directionArray = new ArrayList<ArrayList<OCell>>();
		directionArray.add(cellManager.getCellsUp(x, y));
		directionArray.add(cellManager.getCellsDown(x, y));
		directionArray.add(cellManager.getCellsLeft(x, y));
		directionArray.add(cellManager.getCellsRight(x, y));
		directionArray.add(cellManager.getCellsUpperLeft(x, y));
		directionArray.add(cellManager.getCellsUpperRight(x, y));
		directionArray.add(cellManager.getCellsDownerLeft(x, y));
		directionArray.add(cellManager.getCellsDownerRight(x, y));
		
		//各方向に対して処理
		Iterator<ArrayList<OCell>> directionIterator = directionArray.iterator();
		while (directionIterator.hasNext()) {
			//ある方向に対するセルの配列を取り出す
			ArrayList<OCell> cellArray = directionIterator.next();
			//石が配置可能な場合はflagをたてる
			if (isCellLocatableForDirection(cellArray)) {
				//flip指定の場合は石を反転
				if (flip) replaceCells(cellArray);
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 指定された方向（cellArray）に対して石を配置可能か検査する.
	 * @param cellArray セルの配列
	 * @return 石が配置可能な場合はtrue
	 */
	private boolean isCellLocatableForDirection(ArrayList<OCell> cellArray) {
		CircleManager circleManager = CircleManager.getInstance();
		Iterator<OCell> cellIterator = cellArray.iterator();

		//セルがない場合はfalse
		if (!cellIterator.hasNext()) return false;
		//最初のセルの石が現在のターンと一致する場合はfalse
		if (circleManager.isSameCircleClass(cellIterator.next().getCircle())) return false;

		//2個目以降のセルに現在のターンと一致する石がある場合はtrue
		while (cellIterator.hasNext()) {
			if (circleManager.isSameCircleClass(cellIterator.next().getCircle())) return true;
		}
		
		//2個目以降のセルに現在のターンと一致する石がない場合はfalse
		return false;
	}

	/**
	 * 指定されたセルの配列の石を反転する.
	 * @param cellArray セルの配列
	 */
	private void replaceCells(ArrayList<OCell> cellArray) {
		CircleManager circleManager = CircleManager.getInstance();
		Iterator<OCell> cellIterator = cellArray.iterator();

		//セルの配列に対してイテレート処理
		while (cellIterator.hasNext()) {
			OCell cell = cellIterator.next();
			//現在のターンと一致する石に到達した場合は処理を終了する
			if (circleManager.isSameCircleClass(cell.getCircle())) return;
			//石を反転する
			setCircle(cell);
		}
	}

}
