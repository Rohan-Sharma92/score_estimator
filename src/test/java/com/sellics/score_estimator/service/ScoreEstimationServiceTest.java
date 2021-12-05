package com.sellics.score_estimator.service;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import static org.mockito.Mockito.when;
import com.sellics.score_estimator.calculator.IMatcher;
import com.sellics.score_estimator.calculator.MatchResult;
import com.sellics.score_estimator.factory.IScoreDTOFactory;
import com.sellics.score_estimator.factory.ScoreDTOFactory;
import com.sellics.score_estimator.model.Score;

public class ScoreEstimationServiceTest {

	private ScoreEstimationService estimationService;
	private IMatcher scoreCalculator;
	private IScoreDTOFactory scoreDTOFactory;

	@BeforeEach
	public void setup() {
		Logger logger=Mockito.mock(Logger.class);
		scoreCalculator = Mockito.mock(IMatcher.class);
		scoreDTOFactory= new ScoreDTOFactory();
		estimationService=new ScoreEstimationService(scoreCalculator, scoreDTOFactory, logger);
	}
	
	@Test
	public void testEstimate() {
		when(scoreCalculator.getMatchResult("test", "t")).thenReturn(new MatchResult(true, 3));
		Score calculateScore = estimationService.calculateScore("test");
		assertThat(calculateScore.getKeyword()).isEqualTo("test");
		assertThat(calculateScore.getScore()).isEqualTo(100);
	}
}
