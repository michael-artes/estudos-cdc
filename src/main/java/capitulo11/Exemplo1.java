package capitulo11;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exemplo1 {

	public static void main(String[] args) {

		// Clientes
		Customer michael = new Customer("Michael Douglas");
		Customer cassia = new Customer("Cassia Suelem");
		Customer arthur = new Customer("Arthur Luiz");
		Customer noeme = new Customer("Noeme Moura");

		// Produtos
		Produto bach = new Produto("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
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

		// Ordenando os pagamentos
		payments.stream().sorted(Comparator.comparing(Payment::getDateTime)).forEach(System.out::println);

		// Somando o total dos payment1
		payment1.getProdutos().stream().map(Produto::getPrice).reduce(BigDecimal::add).ifPresent(System.out::println);

		// forma de somar todos
		Optional<BigDecimal> total = payments.stream().map(
				p -> p.getProdutos().stream().map(Produto::getPrice).reduce(BigDecimal::add).orElse(new BigDecimal(1)))
				.reduce(BigDecimal::add);

		System.out.println(total);

		// forma de somar todos resumida
		BigDecimal totalFlat = payments.stream().flatMap(p -> p.getProdutos().stream().map(Produto::getPrice))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println(totalFlat);

		// produtos mais vendidos
		Stream<Produto> products = payments.stream().map(Payment::getProdutos).flatMap(List::stream); // ou .flatMap(p -> p.stream());

		System.out.println("----------------------------");

		// Agrupa por produto mais vendido
		Map<Produto, Long> groupById = payments.stream().flatMap(p -> p.getProdutos().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		//
		groupById.entrySet().stream().forEach(System.out::println);

		System.out.println("----------------------------");

		// Imprimi o que mais vendeu
		groupById.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).ifPresent(System.out::println);

		Map<Produto, BigDecimal> totalPorProduto = payments.stream().flatMap(p -> p.getProdutos().stream())
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.reducing(BigDecimal.ZERO, Produto::getPrice, BigDecimal::add)));

		System.out.println("----------------------------");

		// Imprimi o total de cada produto
		totalPorProduto.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
		.forEach(System.out::println);

		// Os pagamentos de cada Cliente
		Map<Customer, List<Payment>> collect = payments.stream().collect(Collectors.groupingBy(Payment::getCustomer));

		// Lista de clientes com uma list de uma lista de produtos
		Map<Customer, List<List<Produto>>> collect2 = payments.stream().collect(Collectors
				.groupingBy(Payment::getCustomer, Collectors.mapping(Payment::getProdutos, Collectors.toList())));

		System.out.println("----------------------------");

		// Ordenar pelo nome do cliente
		collect2.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey().getNome()))
		.forEach(System.out::println);

		// Organizando o map da forma q tenha somente uma lista
		Map<Customer, List<Produto>> customerToProducts2steps = collect2.entrySet().stream().collect(Collectors.toMap(
				Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream).collect(Collectors.toList())));

		System.out.println("----------------------------");

		customerToProducts2steps.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey().getNome()))
		.forEach(System.out::println);

		System.out.println("----------------------------");

		// Mesmo caso anterior porém com pouca legibilidade
		Map<Customer, List<Produto>> customerToProducts1step = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer,
						Collectors.mapping(Payment::getProdutos, Collectors.toList())))
				.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
						e -> e.getValue().stream().flatMap(List::stream).collect(Collectors.toList())));

		// Mesmo caso anterior porém com pouca legibilidade e utilizando reducing
		Map<Customer, List<Produto>> customerToProducts = payments.stream().collect(Collectors.groupingBy(
				Payment::getCustomer, Collectors.reducing(Collections.emptyList(), Payment::getProdutos, (l1, l2) -> {
					List<Produto> l = new ArrayList<>();
					l.addAll(l1);
					l.addAll(l2);
					return l;
				})));

		// O cliente mais comprador porém com pouca legibilidade e utilizando reducing
		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
				.collect(
						Collectors.groupingBy(Payment::getCustomer,
								Collectors.reducing(BigDecimal.ZERO, p -> p.getProdutos().stream()
										.map(Produto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add),
										BigDecimal::add)));
		
		

		Function<Payment, BigDecimal> paymentTotal = p -> p.getProdutos().stream().map(Produto::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		// O cliente mais comprador - código com uma maior legibilidade
		Map<Customer, BigDecimal> totalValuePerCustomer2 = payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer,
							Collectors.reducing(BigDecimal.ZERO,paymentTotal,BigDecimal::add)));
		
		System.out.println("----------------------------");
		totalValuePerCustomer.entrySet()
				.stream()
				.sorted(Comparator.comparing(Map.Entry::getValue))
				.forEach(System.out::println);
		
		
		
		//agrupando pagamentos por mes
		Map<YearMonth, List<Payment>> paymentsPerMonth = payments.stream()
				.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDateTime())));
		
		System.out.println("----------------------------");
		paymentsPerMonth.entrySet().stream()
				.forEach(System.out::println);
		
		//Descobrindo o total do mes
		Map<YearMonth, BigDecimal> paymentsValuePerMonth = payments.stream()
				.collect(
						Collectors.groupingBy(p -> YearMonth.from(p.getDateTime()),
								Collectors.reducing(BigDecimal.ZERO, p -> p.getProdutos().stream()
										.map(Produto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add), BigDecimal::add)));
		
		
		
		System.out.println("----------------------------");
		paymentsValuePerMonth.entrySet().stream()
				.forEach(System.out::println);
		
		
		
		System.out.println("----------------------------");
		
		BigDecimal valorMes = new BigDecimal("99.90");
		Subscription s1 = new Subscription(valorMes,ontem.minusMonths(5), michael);
		Subscription s2 = new Subscription(valorMes,ontem.minusMonths(8), hoje.minusMonths(1), cassia);
		Subscription s3 = new Subscription(valorMes,ontem.minusMonths(5), hoje.minusMonths(2), noeme);
		
		List<Subscription> assinaturas = Arrays.asList(s1, s2, s3);		
		
		//meses pagos de assinatura
//		long meses = ChronoUnit.MONTHS.between(s1.getInicio(), LocalDateTime.now());
		long meses = ChronoUnit.MONTHS.between(s1.getInicio(), s1.getFim().orElse(LocalDateTime.now()));
		
		System.out.println("Quantidade de meses: " + meses);
		
		//valor gerado pela assinatura - multiplica valor por mes pela quantidade de meses
		BigDecimal totalAssinatura = s1.getTotalAssinatura();
		
		System.out.println("Valor total pago: " + totalAssinatura);
		
		System.out.println("Valor total de todas assinaturas: " + assinaturas.stream().map(Subscription::getTotalAssinatura).reduce(BigDecimal::add));
	}
}
