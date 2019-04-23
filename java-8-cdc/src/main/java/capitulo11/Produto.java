package capitulo11;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Produto {
	
	private String name;
	private Path path;
	private BigDecimal price;
	
	public Produto(String name, Path path, BigDecimal price) {
		this.name = name;
		this.path = path;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Produto [name=" + name + "]";
	}

}
