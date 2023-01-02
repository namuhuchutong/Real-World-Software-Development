package practice2;

import java.io.IOException;
import java.time.Month;

public class Client {

	public static void main(String[] args) throws IOException {
		final BankStatementParser bankStatementParser = new BankStatementCSVParser();
		final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer(bankStatementParser);
		bankStatementAnalyzer.analyze(args[0]);
		bankStatementAnalyzer.analyzeRangeOfDate(args[0], Month.JANUARY, Month.FEBRUARY);
		bankStatementAnalyzer.analyzeMonthAmount(args[0]);
	}
}
