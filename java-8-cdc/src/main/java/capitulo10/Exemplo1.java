package capitulo10;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

//Chega de Calendar! Nova API de datas
public class Exemplo1 {

	public static void main(String[] args) {

		// acrescenta um mes apartir da data atual
		LocalDate mesQVem = LocalDate.now().plusMonths(1);

		// menos 1 ano
		LocalDate minusYears = LocalDate.now().minusYears(1);

		// Se as informações de horário forem importantes, usamos a classe
		// LocalDateTime
		System.out.println(LocalDateTime.now());

		// representando somente horas
		System.out.println(LocalTime.now());

		LocalDateTime hojeMeioDia = LocalDate.now().atTime(12, 0);
		System.out.println(hojeMeioDia);

		// unir data e hora
		LocalDate agora = LocalDate.now();
		LocalTime agoraHoras = LocalTime.now();
		LocalDateTime atTime = agora.atTime(agoraHoras);
		System.out.println(atTime);

		// data a partir de uma ZonaTime
		ZonedDateTime dataComHorasETimeZone = atTime.atZone(ZoneId.of(ZoneId.SHORT_IDS.get("BET")));
		System.out.println(dataComHorasETimeZone);

		// Para converter esses objetos para outras medidas de tempo podemos
		// utilizar os métodos to
		LocalDateTime semTimeZone = dataComHorasETimeZone.toLocalDateTime();

		// factory method of
		LocalDate myAniversario = LocalDate.of(2015, 10, 8);
		LocalDateTime myAniversarioTime = LocalDateTime.of(2015, 10, 8, 8, 30);
		System.out.println(myAniversario);
		System.out.println(myAniversarioTime);

		// Para modificar o ano de um LocalDate
		LocalDate dataDoPassado = LocalDate.now().withYear(1988);

		// Comparacao de datas
		LocalDate hoje = LocalDate.now();
		LocalDate amanha = hoje.plusDays(1);

		System.out.println(hoje.isBefore(amanha));
		System.out.println(hoje.isAfter(amanha));
		System.out.println(hoje.isEqual(amanha));

		ZonedDateTime tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		System.out.println(tokyo);
		System.out.println(saoPaulo);

		System.out.println(tokyo.isEqual(saoPaulo));// retorna false por q tem
													// timezones diferentes

		/*
		 * precisamos acertar a diferença de tempo entre as duas datas. Uma
		 * forma de fazer isso seria adicionar as 12 horas de diferença na
		 * instância de tokyo
		 */
		tokyo = tokyo.plusHours(12);

		System.out.println(tokyo.isEqual(saoPaulo));// retorn true

		// obtendo o dia do mes
		System.out.println("hoje e dia: " + MonthDay.now().getDayOfMonth());

		// obtendo ano e mes
		YearMonth yearMonth = YearMonth.now();
		System.out.println(yearMonth.getYear() + "  -  " + yearMonth.getMonth());

		// Enums no lugar de constantes
		System.out.println(LocalDate.of(2015, Month.OCTOBER, 8));

		// Outra vantagem de utilizar os enums são seus diversos métodos
		// auxiliares
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.NOVEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2));
		System.out.println(Month.DECEMBER.minus(1));

		// imprimir nomes formatados
		Locale pt = new Locale("pt");

		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));

		// Formatando com a nova API de datas
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_TIME));
		String result = now.format(ofPattern);
		System.out.println(result);

		// podemos transformar uma String em alguma representação de data ou
		// tempo válida
		System.out.println(LocalDate.parse(result, ofPattern)); // resultado
																// inverso do
																// anterior

		try {

			// Datas inválidas
			// lançam exception
			LocalDate.now().atTime(25, 0);
			LocalDate.of(2015, Month.FEBRUARY, 30);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Duração e Período
		LocalDate agora1 = LocalDate.now();
		LocalDate outraData = LocalDate.of(2016, Month.JANUARY, 25);

		long dias = ChronoUnit.DAYS.between(outraData, agora);
		long meses = ChronoUnit.MONTHS.between(outraData, agora);
		long anos = ChronoUnit.YEARS.between(outraData, agora);

		System.out.printf("%s dias, %s meses e %s anos", dias, meses, anos);

		// outra forma de obter o mesmo resultado
		Period p = Period.between(outraData, agora1);

		if (p.isNegative()) {
			p = p.negated();
		}

		System.out.printf("\n%s dias, %s meses e %s anos", p.getDays(), p.getMonths(), p.getYears());

		Period periodo = Period.of(2, 10, 5);// outra forma de se criar um
												// periodo

		// periodo por horas
		LocalDateTime agora2 = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(agora2, daquiAUmaHora);
		
		if (duration.isNegative()) {
			duration = duration.negated();
		}
		
		System.out.printf("\n%s horas, %s minutos e %s segundos", duration.toHours(), duration.toMinutes(),duration.getSeconds());
		
		
		//Diferenças para o Joda Time
		
		

	}
}
