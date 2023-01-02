package practice2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

	private static final String RESOURCES = "src/main/resources/";

	private final BankStatementParser bankStatementParser;

	public BankStatementAnalyzer(final BankStatementParser bankStatementParser) {
		this.bankStatementParser = bankStatementParser;
	}

	public void analyze(final String fileName) throws IOException {

		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		System.out.println("Total Amount : "
			+ bankStatementProcessor.calculateTotalAmount());
		System.out.println("Total Amount in January : "
			+ bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
		System.out.println("Total Amount in March : "
			+ bankStatementProcessor.calculateTotalInMonth(Month.MARCH));
	}

	public void analyzeRangeOfDate(final String fileName, final Month minimumOfMonth, final Month maximumOfMonth) throws IOException {
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		RangeResult rangeResult = bankStatementProcessor.calculateRangeOfDate(minimumOfMonth, maximumOfMonth);

		System.out.println("Minimum : " + rangeResult.getMinimumValue());
		System.out.println("Maximum : " + rangeResult.getMaximumValue());
	}

	public void analyzeMonthAmount(final String fileName) throws IOException {
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		bankTransactions.forEach(
			tx -> {
				drawHistogram(
					bankStatementProcessor.calculateTotalInMonth(tx.getDate().getMonth()),
					tx.getDate().getMonth()
				);
			}
		);
	}

	private void drawHistogram(final double amount, final Month month) {
		System.out.print(month.getValue() + " : ");
		int star = 0;
		if (Math.abs(amount) > 1000d) {
			star = (int)(Math.abs(amount) / 100.0d);
		} else {
			star = (int)(Math.abs(amount) / 10.0d);
		}
		for (int i = 0; i < star; i++) {
			System.out.print("*");
		}
		System.out.println();
	}

}
