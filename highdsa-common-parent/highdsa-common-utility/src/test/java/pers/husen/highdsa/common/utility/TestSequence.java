package pers.husen.highdsa.common.utility;

import pers.husen.highdsa.common.sequence.Sequence;
import pers.husen.highdsa.common.sequence.SequenceManager;

/**
 * @Desc 测试分布式生成
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午9:49:14
 * 
 * @Version 1.0.0
 */
public class TestSequence {
	public static void main(String[] args) {
		testSequence();
		testSequenceManager();
	}

	public static void testSequence() {
		Sequence sequence = new Sequence(1, 0);

		int num = 1000;
		for (int i = 0; i < num; i++) {
			long id = sequence.nextId();
			System.out.println("id: " + id);
			System.out.println("id长度: " + Long.toString(id).length());
			System.out.println("id 二进制: " + Long.toBinaryString(id));
			System.out.println("id 二进制长度: " + Long.toBinaryString(id).length());
		}
	}

	public static void testSequenceManager() {
		int num = 5;
		for (int i = 0; i < num; i++) {
			Long id = SequenceManager.getNextId();

			System.out.println(id);
			System.out.println(id.toString().length());
		}
	}
}