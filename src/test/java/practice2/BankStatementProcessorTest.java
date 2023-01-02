package practice2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankStatementProcessorTest {

	private BankStatementProcessor bankStatementProcessor;

	@BeforeEach
	void setup() {
		List<BankTransaction> transactionList =
			Arrays.asList(
				new BankTransaction(
					LocalDate.of(2020, Month.JANUARY, 1),
					-100d,
					"test1"),
				new BankTransaction(
					LocalDate.of(2020, Month.FEBRUARY, 1),
					-500d,
					"test2"),
				new BankTransaction(
					LocalDate.of(2020, Month.MARCH, 1),
					10000d,
					"test3"),
				new BankTransaction(
					LocalDate.of(2020, Month.APRIL, 1),
					100d,
					"test4")
			);
		bankStatementProcessor = new BankStatementProcessor(transactionList);
	}

	@Test
	@DisplayName("1월부터 3월까지 최소, 최대 결제액을 확인한다.")
	void confirm_minimumAmount_and_MaximumAmount() {
		//given
		Month january = Month.JANUARY;
		Month march = Month.MARCH;

		//when
		RangeResult rangeResult = bankStatementProcessor.calculateRangeOfDate(january, march);

		//then
		assertEquals(-500d, rangeResult.getMinimumValue());
		assertEquals(10000d, rangeResult.getMaximumValue());
	}
}