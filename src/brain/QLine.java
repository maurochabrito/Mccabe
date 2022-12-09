package brain;

import thermodynamicsModel.GammaModel;
import thermodynamicsModel.VapourPressureModel;

public class QLine extends Line{
	public Double q;
	public Double z;
	
	public QLine(Double q, Double z) {
		super((q)/(q-1), -z/(q-1));
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
}
