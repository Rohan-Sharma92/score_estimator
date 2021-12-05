package com.sellics.score_estimator.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellics.score_estimator.calculator.IMatcher;
import com.sellics.score_estimator.calculator.MatchResult;
import com.sellics.score_estimator.dto.ScoreDTO;
import com.sellics.score_estimator.factory.IScoreDTOFactory;
import com.sellics.score_estimator.model.Score;

@Service
public class ScoreEstimationService implements IScoreEstimationService {

	private final IMatcher scoreCalculator;
	private final IScoreDTOFactory scoreDTOFactory;
	private final Logger logger;

	@Autowired
	public ScoreEstimationService(final IMatcher scoreCalculator, final IScoreDTOFactory scoreDTOFactory,
			final Logger logger) {
		this.scoreCalculator = scoreCalculator;
		this.scoreDTOFactory = scoreDTOFactory;
		this.logger = logger;
	}

	@Override
	public Score calculateScore(String keyword) {
		/*
		 * Retrieve matching words for first character in the keyword and check if your
		 * keyword is present. If present, then return. Otherwise, reduce the score by a
		 * factor and search again with adding another character
		 * 
		 * 
		 */
		logger.info("Received request for estimation for keyword {}", keyword);
		StringBuilder sb = new StringBuilder();
		ScoreDTO scoreDTO = scoreDTOFactory.create(keyword);
		for (char character : keyword.toCharArray()) {
			sb.append(character);
			MatchResult matchResult = scoreCalculator.getMatchResult(keyword, sb.toString());
			if (matchResult.isMatch()) {
				scoreDTO.awardPoints(matchResult.getMatchFactor());
				break;
			}
			scoreDTO.givePenalty(matchResult.getMatchFactor());
		}
		Score finalScore = scoreDTO.processFinalScore();
		logger.info("Final score calculated {}", finalScore);
		return finalScore;
	}

}
