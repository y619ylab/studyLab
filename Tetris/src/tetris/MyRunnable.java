package tetris;



public class MyRunnable implements Runnable {

	private Test task;
	private int[] array;
//	private boolean isStop = false;


	public MyRunnable(Test task, int[] array) {
		this.task = task;
		this.array = array;
	}

	@Override
	public void run() {
		task.run(array);
	}

//	public boolean setEnd() {
//		return true;
//	};



	public interface Test {
		void run(int[] array);
	}

}
