package practice1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple {

	private static final String RESOURCES = "src/practice1/resources/";

	public static void main(String[] args) throws IOException {

		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);
		final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		double total = 0d;

		for (final String line: lines) {
			final String[] columns = line.split(",");
			final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
			if (date.getMonth() == Month.JANUARY) {
				final double amount = Double.parseDouble(columns[1]);
				total += amount;
			}
		}
		System.out.println("Total : " + total);

		// what if file is empty?
		// what if data format is not correct?
		// what if columns are not correct?
		// how to support json or other format?
		// code duplications?

		// SRP
		// 1. input read
		// 2. parsing using format argument
		// 3. process
		// 4. report
	}
}
