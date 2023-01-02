package practice2;

import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.Comparator;
import java.util.List;

public class BankStatementProcessor {

	private final List<BankTransaction> bankTransactions;

	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public double calculateTotalAmount() {
		double total = 0;
		for (final BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}

	public double calculateTotalInMonth(final Month month) {
		double total = 0;
		for (final BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDate().getMonth() == month) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}

	public RangeResult calculateRangeOfDate(final Month minimumOfMonth, final Month maximumOfMonth) {
		double maximum = 0d;
		double minimum = 0d;

		final Comparator<Double> comparator = (d1, d2) -> d1.compareTo(d2);

		maximum
			= bankTransactions.stream()
					.filter(tx -> tx.getDate().getMonth().getValue() >= minimumOfMonth.getValue())
					.filter(tx -> tx.getDate().getMonth().getValue() <= maximumOfMonth.getValue())
					.map(tx -> tx.getAmount())
					.max(comparator)
					.orElseThrow(NoSuchFieldError::new);

		minimum
			= bankTransactions.stream()
				.filter(tx -> tx.getDate().getMonth().getValue() >= minimumOfMonth.getValue())
				.filter(tx -> tx.getDate().getMonth().getValue() <= maximumOfMonth.getValue())
				.map(element -> element.getAmount())
				.min(comparator)
				.orElseThrow(NoSuchFieldError::new);

		return new RangeResult(maximum, minimum);
	}
}
