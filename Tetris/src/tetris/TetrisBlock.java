package tetris;

public class TetrisBlock {

	public int id = 0;
	public int[][][] block = {

			{
					{ 1, 1, 1, 1 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 }
			},
			{
					{ 0, 2, 2, 0 },
					{ 0, 2, 2, 0 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 }
			},
			{
					{ 0, 3, 3, 0 },
					{ 3, 3, 0, 0 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 }
			},
			{
					{ 4, 4, 0, 0 },
					{ 0, 4, 4, 0 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 }
			},
			{
					{ 5, 0, 0, 0 },
					{ 5, 5, 5, 0 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 }
			},
			{
					{ 0, 0, 6, 0 },
					{ 6, 6, 6, 0 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 }
			},
			{
					{ 0, 7, 0, 0 },
					{ 7, 7, 7, 0 },
					{ 0, 0, 0, 0 },
					{ 0, 0, 0, 0 }
			}
	};



	public int[][] newBlock() {

		int[][] currentBlock = new int[4][4];
		int id = (int) (Math.floor(Math.random() * block.length));

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				currentBlock[y][x] = block[id][y][x];
			}
		}

		return currentBlock;

	}

}
