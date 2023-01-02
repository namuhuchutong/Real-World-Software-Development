package practice2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.Month;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankStatementAnalyzerTest {

	// analyzer를 테스트할 수 있다.
	// 기존 practice1은 메인함수에 있어서 테스트하기 까다로웠음.
	// csv만 테스트할 수 있지만, 나중에 xml, json이 추가 되어도 설정이 가능하다.

	private BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer(new BankStatementCSVParser());

	@Test
	@DisplayName("1월부터 3월까지 가장 작은 결제 내역과.")
	void test() throws IOException {
	}

}