package factory;

import manager.CircleManager;
import manager.MessageManager;
import status.Circle;

/**
 * 石を生成するFactoryクラス.
 */
public class CircleFactory {

	/** Singleton. */
	private static CircleFactory factory = new CircleFactory();
	
	/** コンストラクタ, */
	private CircleFactory() {
		super();
	}
	
	/** Singletonを取得する. */
	public static CircleFactory getFactory() {
		return factory;
	}
	
	/**
	 * 石を生成する.
	 * @return 石
	 */
	public Circle createCricle() {
		//現在のターンに対応する石のクラスを取得する
		Class<? extends Circle> circleClass = CircleManager.getInstance().getCurrentCircle();

		Circle circle = null;
		try {
			//石をインスタンス化する
			circle = circleClass.newInstance();
		} catch (InstantiationException e) {
			//例外発生時にメッセージ領域に例外名を表示する
			MessageManager.getInstance().setMessage(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			//例外発生時にメッセージ領域に例外名を表示する
			MessageManager.getInstance().setMessage(e.toString());
			e.printStackTrace();
		}

		return circle;
	}
}
