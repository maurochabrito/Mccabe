package application;

import thermodynamicsModel.GammaModel;
import thermodynamicsModel.RaoultLaw;
import thermodynamicsModel.VapourPressureModel;
import thermodynamicsModel.impl.Antoine;
import thermodynamicsModel.impl.MargulesGammaModel;

public class Program {

	public static void main(String[] args) {
		VapourPressureModel vpm1 = new Antoine(18.9119,3803.98,-41.68);
		VapourPressureModel vpm2 = new Antoine(18.3036,3816.44,-46.13);
		GammaModel gm = new MargulesGammaModel(1.6022,0.7947);
		Double externalPressure = 760.0; //mmHg
		Double X = 0.2;
		Double Y = RaoultLaw.iterativeY(X, vpm1, vpm2, gm, externalPressure);
		X = RaoultLaw.iterativeX(Y, X, vpm1, vpm2, gm, externalPressure);
		System.out.println("X = "+ X +"\nY = "+ Y);
	}

}
