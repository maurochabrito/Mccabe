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
		System.out.println("Test #2\n\n");
		Double xb = 0.01;
		Double xd = 0.8;
		Double z = 0.5;
		List<Plate> plateList = new ArrayList<>();
		Double q = 0.5;
		QLine qLine = new QLine(q, z);
		Double xi = qLine.intersectionEquilibriumLine(vpm1, vpm2, gm, externalPressure);
		Double yi = qLine.y(xi);
		Double rMin = ((yi-xd)/(xi-xd))/(1-((yi-xd)/(xi-xd)));
		Double r = 1.3;
		Line minimalRectifyingLine = new Line(rMin/(rMin+1),xd/(rMin+1));
		Line rectifyingLine = new Line(r*rMin/(r*rMin+1),xd/(r*rMin+1));
		Double xTriple = qLine.intersection(rectifyingLine);//Triple intersection between qline and operational lines
		Double yTriple = qLine.y(xTriple);
		//StrippingLine
		Double strippingAlpha = (yTriple-xb)/(xTriple-xb);
		Double strippingBetha = yTriple-strippingAlpha*xTriple;
		Line strippingLine = new Line(strippingAlpha, strippingBetha);
		//First stage
		Double currentY = xd;
		Line horizontalLine = new Line(0.0, currentY);
		Double currentX = horizontalLine.nonElementarIntersection(gm, vpm1, vpm2, externalPressure);
		Double currentT = RaoultLaw.iterativeY_T(currentX, vpm1, vpm2, gm, externalPressure);
		int i = 1;
		Plate currentPlate = new Plate(i, currentX, currentY, currentT);
		System.out.println(currentPlate);
		while(currentX > xb) {
			if(!rectifyingLine.compareTo(strippingLine, currentX)) {
				currentY = rectifyingLine.y(currentX);
			} else {
				currentY = strippingLine.y(currentX);
			}
			horizontalLine = new Line(0.0, currentY);
			currentX = horizontalLine.nonElementarIntersection(gm, vpm1, vpm2, externalPressure);
			currentT = RaoultLaw.iterativeY_T(currentX, vpm1, vpm2, gm, externalPressure);
			i++;
			currentPlate = new Plate(i, currentX, currentY, currentT);
			System.out.println(currentPlate);
		}
	}

}
