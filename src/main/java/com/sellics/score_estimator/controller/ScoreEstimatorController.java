package com.sellics.score_estimator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellics.score_estimator.model.Score;
import com.sellics.score_estimator.service.IScoreEstimationService;

@RestController
public class ScoreEstimatorController {

	private final IScoreEstimationService scoreEstimationService;
	
	public ScoreEstimatorController(final IScoreEstimationService scoreEstimationService) {
		this.scoreEstimationService = scoreEstimationService;
	}
	
	@GetMapping(path="/api/estimate")
	public Score estimateScore(@RequestParam("keyword") String keyword) {
		return scoreEstimationService.calculateScore(keyword);
	}
}
