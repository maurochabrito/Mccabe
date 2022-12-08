package thermodynamicsModel.impl;

import thermodynamicsModel.GammaModel;

public class MargulesGammaModel implements GammaModel {
	Double A1;
	Double A2;
	@Override
	public Double gamma(Integer index, Double X, Double T) {
		if(index == 1) {
			Double gamma = Math.exp((A1+2*(A2-A1)*X)*(1-X)*(1-X));
			return gamma;
		}else {
			Double gamma = Math.exp((A2+2*(A1-A1)*(1-X))*(X)*(X));
			return gamma;
		}
	}

}
