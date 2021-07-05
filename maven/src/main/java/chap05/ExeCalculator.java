package chap05;

public class ExeCalculator implements Calculator {
	private Calculator delegate;
	
	public ExeCalculator(Calculator delegate) {
		this.delegate = delegate; 
	}
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		
		long result = delegate.factorial(num);
		
		long end = System.nanoTime();
		System.out.println("Exe : " + (end-start));
		return result;
	}

}
