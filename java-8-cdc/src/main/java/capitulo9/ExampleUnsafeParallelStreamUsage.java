package capitulo9;

import java.util.stream.LongStream;

public class ExampleUnsafeParallelStreamUsage {
	
	private static long total = 0;
	
	public static void main(String[] args) {
		
		LongStream.range(1, 1_000_000_000)
			.parallel()
			.filter(y -> y % 2 == 0)
			.forEach(n -> total += n);
		
		System.out.println(total);
	}

}
