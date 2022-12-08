package thermodynamicsModel;

public class RaoultLaw {
	public static Double y(Double X, Double T, VapourPressureModel vpm1, VapourPressureModel vpm2, GammaModel gm, Double pressure) {
		Double y = gm.gamma(1, X, T)*X*vpm1.pressure(T)/pressure;
		return y;
	}
	public static Double x(Double Y, Double previousX, Double T, VapourPressureModel vpm1, VapourPressureModel vpm2, GammaModel gm, Double pressure) {
		Double x = Y*pressure/(gm.gamma(1, previousX, T)*vpm1.pressure(T));
		return x;
	}
}
