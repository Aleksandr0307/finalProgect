package model;

import java.util.ArrayList;

public enum ReductionFactor {
	YES("0.5"), NO("1.0");

	private final String reductionFactor;

	ReductionFactor(String reductionFactor) {
		this.reductionFactor = reductionFactor;
	}

	public String reductionFactor() {
		return reductionFactor;
	}

	public static ArrayList<ReductionFactor> getReductionFactor() {
		ArrayList<ReductionFactor> reductionFactor = new ArrayList<>();
		reductionFactor.add(YES);
		reductionFactor.add(NO);
		return reductionFactor;

	}
}
