package practice2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class BankStatementCSVParserTest {

	private final BankStatementParser parser = new BankStatementCSVParser();

	@Test
	void parse_comma() {
		//given
		final String line = "01-01-2020,100,test";
		final BankTransaction expected = new BankTransaction(
			LocalDate.of(2020, Month.JANUARY, 1),
			100d,
			"test"
		);

		//when
		BankTransaction bankTransaction = parser.parseFrom(line);

		//then
		assertEquals(expected, bankTransaction);
	}

	@Test
	void parse_multiple_lines() {
		//given
		final String line1 = "01-01-2020,100,tester1";
		final String line2 = "01-02-2017,-50,tester2";
		List<String> lines = Arrays.asList(line1, line2);

		//when
		List<BankTransaction> bankTransactions = parser.parseLinesFrom(lines);

		//then
		assertEquals(lines.size(), bankTransactions.size());
	}
}