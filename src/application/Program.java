package application;

import java.util.ArrayList;
import java.util.List;

import brain.Line;
import brain.Plate;
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
		System.out.println("Test #1 ended\n\n");
		
		Double xb = 0.01;
		Double Xd = 0.85;
		List<Plate> plateList = new ArrayList<>();
		Line operationalLine = new Line(1.0, 0.0);
		Line horizontalLine = new Line(0.0, Xd);
		Double currentX = horizontalLine.nonElementarIntersection(gm, vpm1, vpm2, externalPressure);
		Double currentT = RaoultLaw.iterativeY_T(currentX, vpm1, vpm2, gm, externalPressure);
		Integer i = 1;
		Double currentY = RaoultLaw.iterativeY(currentX, vpm1, vpm2, gm, externalPressure);
		Plate currentPlate = new Plate(i, currentX, currentY, currentT);
		System.out.println(currentPlate);
		while (currentX > xb) {
			i++;
			horizontalLine = new Line(0.0, currentX);
			currentX = horizontalLine.nonElementarIntersection(gm, vpm1, vpm2, externalPressure);
			currentT = RaoultLaw.iterativeY_T(currentX, vpm1, vpm2, gm, externalPressure);
			currentY = RaoultLaw.iterativeY(currentX, vpm1, vpm2, gm, externalPressure);
			currentPlate = new Plate(i, currentX, currentY, currentT);
			System.out.println(currentPlate);
		}
	}

}
