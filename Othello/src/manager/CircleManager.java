package manager;

import constant.Constant;
import status.BlackCircle;
import status.Circle;
import status.WhiteCircle;

/**
 * 石を管理するクラス.
 */
public class CircleManager {

	/** 石の種類. */
	private static final int TURN_CYCLE = 2;

	/** Singleton. */
	private static CircleManager instance = new CircleManager();
	/** 現在のターン. */
	private boolean isBlack = true;
	
	/** 黒石の数. */
	private int numBlack = 0;
	/** 白石の数. */
	private int numWhite = 0;
	
	/** コンストラクタ. */
	private CircleManager() {
		super();
	}
	
	/** Singletonを取得する. */
	public static CircleManager getInstance() {
		return instance;
	}

	/** ターンを切り返る. */
	public void changeTurn() {
		isBlack = !isBlack;
	}

	/** 石の種類の数を取得する. */
	public int getTurnCycle() {
		return TURN_CYCLE;
	}
	
	/**
	 * 現在のターンに対応する石クラス（非インスタンス）を取得する.
	 * @return 現在のターンに対応する石クラス
	 */
	public Class<? extends Circle> getCurrentCircle() {
		if (isBlack) {
			return BlackCircle.class;
		} else {
			return WhiteCircle.class;
		}
	}

	/**
	 * 引数の石インスタンスが現在のターンと一致するか検査する.
	 * @param circle 石インスタンス
	 * @return 現在のターンと一致している場合true
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
	 * 現在のターンを示すメッセージを取得する.
	 * @return 現在のターンを示すメッセージ
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
	 * 内部変数の石の数をリセットする.
	 */
	public void resetCircleCounter() {
		numBlack = 0;
		numWhite = 0;
	}
	
	/**
	 * 石の数をカウントする.
	 * @param circle 石インスタンス
	 */
	public void countCircle(Circle circle) {
		if (circle instanceof BlackCircle) {
			numBlack++;
		} else if (circle instanceof WhiteCircle) {
			numWhite++;
		}
	}

	/**
	 * ゲームの勝者と石の数を文字列で取得する.
	 * @return ゲームの勝者と石の数
	 */
	public String getWinnerMessage() {
		String message;
		//ゲームの勝者を決定する
		if (numWhite < numBlack) {
			message = Constant.MSG_WINNER_BLACK;
		} else if (numBlack < numWhite) {
			message = Constant.MSG_WINNER_WHITE;
		} else {
			//引き分け
			message = Constant.MSG_WINNER_EVEN;
		}

		//石の数をメッセージに追加する
		message = message + " 黒" + numBlack + "個 対 白" + numWhite + "個";
		return message;
	}
	
}
