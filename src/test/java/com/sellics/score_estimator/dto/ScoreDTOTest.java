package com.sellics.score_estimator.dto;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import com.sellics.score_estimator.model.Score;

public class ScoreDTOTest {

	private ScoreDTO dto;

	@Test
	public void testDTOAwardPoints() {
		dto=new ScoreDTO("test");
		dto.awardPoints(3);
		Score score = dto.processFinalScore();
		assertThat(score.getScore()).isEqualTo(100);
	}
	
	@Test
	public void testDTOPenalizePoints() {
		dto=new ScoreDTO("test");
		dto.givePenalty(2);
		Score score = dto.processFinalScore();
		assertThat(score.getScore()).isEqualTo(33);
	}
}
