package capitulo11;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exemplo1 {
	
	public static void main(String[] args) {
		
		//Clientes
		Customer michael = new Customer("Michael Douglas");
		Customer cassia = new Customer("Cassia Suelem");
		Customer arthur = new Customer("Arthur Luiz");
		Customer noeme = new Customer("Noeme Moura");
		
		//Produtos
		Produto bach = new Produto("Bach Completo",	Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Produto adele = new Produto("Adele Completo", Paths.get("/music/bach.mp3"), new BigDecimal(90));
		Produto vingadores = new Produto("Os vingadores", Paths.get("/filme/vin.mp4"), new BigDecimal(50));
		Produto starWars = new Produto("StarWars", Paths.get("/filme/star.mp4"), new BigDecimal(150));
		Produto salt = new Produto("Salt Completo", Paths.get("/filme/bach.mp4"), new BigDecimal(200));
		Produto speed = new Produto("speed Completo", Paths.get("/filme/speed.mp4"), new BigDecimal(130));
		
		LocalDateTime hoje = LocalDateTime.now();
		LocalDateTime ontem = hoje.minusDays(1);
		LocalDateTime amanha = hoje.plusDays(1);
		
		Payment payment1 = new Payment(Arrays.asList(bach, adele), hoje, michael);
		Payment payment2 = new Payment(Arrays.asList(vingadores, starWars), ontem, cassia);
		Payment payment3 = new Payment(Arrays.asList(salt, speed), amanha, arthur);
		Payment payment4 = new Payment(Arrays.asList(salt, speed, adele), hoje, michael);
		Payment payment5 = new Payment(Arrays.asList(salt, vingadores), hoje, noeme);
		
		List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);
		
		//Ordenando os pagamentos
		payments.stream()
			.sorted(Comparator.comparing(Payment::getDateTime))
			.forEach(System.out::println);
		
		//Somando o total dos payment1
		payment1.getProdutos().stream()
			.map(Produto::getPrice)
			.reduce(BigDecimal::add)
			.ifPresent(System.out::println);
		
		//forma de somar todos
		Optional<BigDecimal> total = payments.stream()
			.map(p -> p.getProdutos().stream()
						.map(Produto::getPrice)
						.reduce(BigDecimal::add)
						.orElse(new BigDecimal(1)))
			.reduce(BigDecimal::add);

		System.out.println(total);
		
		//forma de somar todos resumida		
		BigDecimal totalFlat =
				payments.stream()
				.flatMap(p -> p.getProdutos().stream().map(Produto::getPrice))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		System.out.println(totalFlat);
		
		
		//produtos mais vendidos
		Stream<Produto> products = payments.stream()
				.map(Payment::getProdutos)
				.flatMap(List::stream); //ou .flatMap(p -> p.stream());
		
		Map<Produto, Long> groupById = payments.stream()
			.flatMap(p -> p.getProdutos().stream())
			.collect(Collectors.groupingBy(
							Function.identity(),
							Collectors.counting()));
		
		groupById.entrySet().stream().forEach(System.out::println);
		
		System.out.println("----------------------------");
		//o maior
		groupById.entrySet().stream()
			.max(Comparator.comparing(Map.Entry::getValue))
			.ifPresent(System.out::println);
	}
}
