package com.sellics.score_estimator.calculator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import com.sellics.score_estimator.core.IMatchingWordRetriever;


public class MatcherTest {

	private Matcher matcher;
	private IMatchingWordRetriever retriever;
	
	@BeforeEach
	public void setup() {
		retriever = Mockito.mock(IMatchingWordRetriever.class);
		Logger logger = Mockito.mock(Logger.class);
		matcher=new Matcher(retriever, logger);
	}
	
	@Test
	public void testScoreForCompleteMatch() {
		String keyword="test";
		String searchValue = "t";
		List<String> list = new ArrayList<>();
		list.add(keyword);
		when(retriever.retrieveMatchingWords(searchValue)).thenReturn(list);
		MatchResult matchResult = matcher.getMatchResult(keyword, searchValue);
		assertThat(matchResult.getMatchFactor()).isEqualTo(Integer.valueOf(3));
		assertThat(matchResult.isMatch()).isTrue();
	}
	
	@Test
	public void testScoreForPartialMatch() {
		String keyword="test";
		List<String> list = new ArrayList<>();
		list.add("tester");
		String searchValue = "t";
		when(retriever.retrieveMatchingWords(searchValue)).thenReturn(list);
		MatchResult matchResult = matcher.getMatchResult(keyword, searchValue);
		assertThat(matchResult.getMatchFactor()).isEqualTo(Integer.valueOf(2));
		assertThat(matchResult.isMatch()).isTrue();
	}
	
	@Test
	public void testScoreForNoMatch() {
		String keyword="test";
		List<String> list = new ArrayList<>();
		list.add("example");
		String searchValue = "t";
		when(retriever.retrieveMatchingWords(searchValue)).thenReturn(list);
		MatchResult matchResult = matcher.getMatchResult(keyword, searchValue);
		assertThat(matchResult.getMatchFactor()).isEqualTo(Integer.valueOf(1));
		assertThat(matchResult.isMatch()).isFalse();
	}
}
