package chap05;

public class ImplCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		// 4! -> 4*(4-1)*(3-1)*(2-1)
		// 재귀호출
//		long start = System.currentTimeMillis();
		long result = 1;
		try {
			for (long i = 1; i <= num; i++) {
				result *= num;
			}
			System.out.println("나는 ImplCalculator");
			return result;
		} finally {
//			long end = System.currentTimeMillis();
//			System.out.println("재귀 : " +(end-start));

		}

	}

}
