package practice1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankStatementCSVParserTest {

	@Test
	@DisplayName("콤마를 파싱한다.")
	void parse_correct() {
		//given
		final BankStatementCSVParser parser = new BankStatementCSVParser();
		final String line = "30-01-2017,-50,Tesco";
		final BankTransaction expected = new BankTransaction(
			LocalDate.of(2017, Month.JANUARY, 30),
			-50d,
			"Tesco"
		);
		double tolerance = 0.0d;

		//when
		final BankTransaction bankTransaction = parser.parseLinesFromCSV(Arrays.asList(line)).get(0);

		//then
		assertEquals(expected.getAmount(), bankTransaction.getAmount());
		assertEquals(expected.getDate(), bankTransaction.getDate());
		assertEquals(expected.getDescription(), bankTransaction.getDescription());
	}
}