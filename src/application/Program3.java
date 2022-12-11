package application;

import java.util.ArrayList;
import java.util.List;

import brain.MccabeThiele;
import brain.Plate;
import thermodynamicsModel.GammaModel;
import thermodynamicsModel.VapourPressureModel;
import thermodynamicsModel.impl.Antoine;
import thermodynamicsModel.impl.MargulesGammaModel;

public class Program3 {

	public static void main(String[] args) {
		VapourPressureModel vpm1 = new Antoine(18.9119,3803.98,-41.68);
		VapourPressureModel vpm2 = new Antoine(18.3036,3816.44,-46.13);
		GammaModel gm = new MargulesGammaModel(1.6022,0.7947);
		Double externalPressure = 1.0;//atm
		Double xb = 0.01;
		Double xd = 0.8;
		Double z = 0.5;
		Double q = 0.5;
		Double r = 1.3;
		System.out.println("Test: 3 - Generic simple column\nImplementing Mccabe-Thile by a proper Class\n\n");
		MccabeThiele mt = new MccabeThiele(externalPressure, xd, xb, z, q, r, vpm1, vpm2, gm);
		List<Plate> plateList = new ArrayList<>();
		plateList = mt.plateList();
		for(Plate p : plateList) {
			System.out.println(p);
		}
	}

}
