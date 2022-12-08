package brain;

import thermodynamicsModel.GammaModel;
import thermodynamicsModel.VapourPressureModel;

public class QLine extends Line{
	public Double q;
	public Double z;
	
	public QLine(Double q, Double z) {
		super((-q)/(1-q), 1/(1-q));
		this.q = q;
		this.z = z;
	}

	public void setQ(Double q) {
		this.q = q;
	}
	public void setZ(Double z) {
		this.z = z;
	}


	public Double intersectionEquilibriumLine(VapourPressureModel vpm1, VapourPressureModel vpm2, GammaModel gm, Double pressure) {
		if(q == 1) {
			return z;
		}else {
		Line qLine = new Line(alpha, betha);
		double xIntersected = qLine.nonElementarIntersection(gm, vpm1, vpm2, pressure);
		return xIntersected;
		}
	}
	public Double y(Double x) {
		Double y = x*(-q)/(1-q)+1/(1-q);
		return y;
	}
}
