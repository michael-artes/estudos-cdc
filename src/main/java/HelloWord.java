import java.util.Collections;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HelloWord {
	
	public static void main(String[] args) throws ScriptException {
		
		List<String> ss = Collections.EMPTY_LIST;
		
		ss.forEach(s -> System.out.println(s));
		
		System.out.println("Hello World Java 8");
		
		
		//processor javascript
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engineByName = engineManager.getEngineByName("nashorn");
		
		engineByName.eval("load('src/main/java/hello-world.js')");
		
		/*IntStream stream = IntStream.of(1, 2);
		stream.forEach(System.out::println);
		// That was fun! Let's do it again!
		stream.forEach(System.out::println);*/
		
	}
}