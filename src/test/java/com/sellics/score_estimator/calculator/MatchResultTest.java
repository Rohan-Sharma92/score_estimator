package com.sellics.score_estimator.calculator;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
public class MatchResultTest {

	@Test
	public void testGetterAndSetters() {
		MatchResult matchResult=new MatchResult(true, 3);
		assertThat(matchResult.isMatch()).isTrue();
		assertThat(matchResult.getMatchFactor()).isEqualTo(3);
	}
}
