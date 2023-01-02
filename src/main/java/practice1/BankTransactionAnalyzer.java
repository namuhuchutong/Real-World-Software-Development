package practice1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzer {

	private static final String RESOURCES = "src/main/resources/";

	// SRP
	// 1. input read
	// 2. parsing using format argument
	// 3. process
	// 4. report

	public static void main(String[] args) throws IOException {
		final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

		// input read
		final String fileName = args[0];
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		// parse
		final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		collectSummary(bankStatementProcessor);
	}

	private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
		System.out.println("Total Amount : "
			+ bankStatementProcessor.calculateTotalAmount());
		System.out.println("Total Amount in January : "
			+ bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
		System.out.println("Total Amount in March : "
			+ bankStatementProcessor.calculateTotalInMonth(Month.MARCH));
	}
}