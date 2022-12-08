package thermodynamicsModel.impl;

import thermodynamicsModel.GammaModel;

public class IdealLiquidGammaModel implements GammaModel {
	Double A1;
	Double A2;
	@Override
	public Double gamma(Integer index, Double X, Double T) {
		return 1.0;
	}

}
