package practice1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankStatementProcessorTest {

	// 프로세서는 계산하고 결과를 돌려주는 역할을 한다.
	// 현재 단순히 더하기 연산만 처리해서 전달해줌
	// 반환 값은 double
	// double은 부동 소수니까 소수점 연산에서 발생하는 문제를 테스트해볼까?

	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Test
	@DisplayName("전체 잔고를 확인한다.")
	void calculate_total_amount_on_success() {
		//given
		final List<BankTransaction> testTransactions = createTestTransactions();
		final BankStatementProcessor processor = new BankStatementProcessor(testTransactions);
		final double totalAmount =  100d;

		//when
		final double calculateTotalAmount = processor.calculateTotalAmount();

		//then
		assertEquals(totalAmount, calculateTotalAmount);

	}

	@Test
	@DisplayName("1월 잔고를 확인한다.")
	void calculate_total_amount_january() {
		//given
		final List<BankTransaction> testTransactions = createTestTransactions();
		final BankStatementProcessor processor = new BankStatementProcessor(testTransactions);
		final double totalAmountInJanuary = 50d;

		//when
		final double calculateTotalInMonth = processor.calculateTotalInMonth(Month.JANUARY);

		//then
		assertEquals(totalAmountInJanuary, calculateTotalInMonth);
	}

	private List<BankTransaction> createTestTransactions() {
		return Arrays.asList(
			new BankTransaction(LocalDate.parse("01-01-2000", DATE_PATTERN), -100d, "a"),
			new BankTransaction(LocalDate.parse("01-01-2000", DATE_PATTERN), 100d, "b"),
			new BankTransaction(LocalDate.parse("01-01-2000", DATE_PATTERN), 50d, "c"),
			new BankTransaction(LocalDate.parse("01-02-2000", DATE_PATTERN), 50d, "d")
		);
	}
}