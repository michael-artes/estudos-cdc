package capitulo8;

import java.util.function.IntSupplier;

public class ExampleFibonacci implements IntSupplier{

	private int anterior = 0;
	private int proximo = 1;
	
	@Override
	public int getAsInt() {
		proximo = proximo + anterior;
		anterior = proximo - anterior;
		return anterior;
	}

}
