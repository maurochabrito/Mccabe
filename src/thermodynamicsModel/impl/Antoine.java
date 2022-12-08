package thermodynamicsModel.impl;

import thermodynamicsModel.VapourPressureModel;

public class Antoine implements VapourPressureModel {
	Double A;
	Double B;
	Double C;
	public Antoine(Double a, Double b, Double c) {
		A = a;
		B = b;
		C = c;
	}
	@Override
	public Double temperature(Double pressure) {
		Double temperature = B/(A-Math.log(pressure))-C;
		return temperature;
	}
	@Override
	public Double pressure(Double temperature) {
		Double pressure = Math.exp(A-B/(temperature+C));
		return pressure;
	}
	
	
}
