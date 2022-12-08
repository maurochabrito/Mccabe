package application;

import java.util.ArrayList;
import java.util.List;

import brain.Line;
import brain.Plate;
import brain.QLine;
import thermodynamicsModel.GammaModel;
import thermodynamicsModel.RaoultLaw;
import thermodynamicsModel.VapourPressureModel;
import thermodynamicsModel.impl.Antoine;
import thermodynamicsModel.impl.MargulesGammaModel;

public class Program2 {

	public static void main(String[] args) {
		VapourPressureModel vpm1 = new Antoine(18.9119,3803.98,-41.68);
		VapourPressureModel vpm2 = new Antoine(18.3036,3816.44,-46.13);
		GammaModel gm = new MargulesGammaModel(1.6022,0.7947);
		Double externalPressure = 760.0; //mmHg
		Double X = 0.2;
		Double Y = RaoultLaw.iterativeY(X, vpm1, vpm2, gm, externalPressure);
		X = RaoultLaw.iterativeX(Y, X, vpm1, vpm2, gm, externalPressure);
		System.out.println("X = "+ X +"\nY = "+ Y);
		System.out.println("Test #1 ended\n\n");
		
		Double xb = 0.1;
		Double xd = 0.8;
		Double z = 0.5;
		List<Plate> plateList = new ArrayList<>();
		Double q = 0.5;
		QLine qLine = new QLine(q, z);
		Double xi = qLine.intersectionEquilibriumLine(vpm1, vpm2, gm, externalPressure);
		Double yi = qLine.y(xi);
		Double rMin = (-yi+xd)/(yi-xi);
		Double r = 1.5;
		Line minimalRectifyingLine = new Line(rMin/(rMin+1),1/(rMin+1));
		Line rectifyingLine = new Line(r*rMin/(r*rMin+1),1/(r*rMin+1));
		Double yTriple = qLine.intersection(rectifyingLine);//Triple intersection between qline and operational lines
		Double xTriple = qLine.x(yTriple);
		//StrippingLine
		Double strippingAlpha = (xb-yTriple)/(xb-xTriple);
		Double strippingBetha = yTriple-strippingAlpha*xTriple;
		Line strippingLine = new Line(strippingAlpha, strippingBetha);
	}

}
