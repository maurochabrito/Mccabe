package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import brain.BrainException;
import brain.MccabeThiele;
import brain.Plate;
import thermodynamicsModel.GammaModel;
import thermodynamicsModel.VapourPressureModel;
import thermodynamicsModel.impl.Antoine;
import thermodynamicsModel.impl.MargulesGammaModel;

public class Program5 {
    /*It is a program 3 continuation: previous, was implemented a class for simple column mccabe.
     On this application, it is tested a use for that class by user input data
     It is expected that, in a continuation for program 5 and 3, a Class that calcule q fator by enthalpy*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Test: 5 - Generic simple column\nImplementing Mccabe-Thile by a proper Class\nIt allows the user to choice the project variables.\n\n");
		VapourPressureModel vpm1 = new Antoine(18.9119,3803.98,-41.68);
		VapourPressureModel vpm2 = new Antoine(18.3036,3816.44,-46.13);
		GammaModel gm = new MargulesGammaModel(1.6022,0.7947);
		Boolean running = true;
		Double xb = null;
		Double xd = null;
		Double z = null;
		Double q = null;
		Double r = null;
		Double choice = null;
		while(running) {
			try {
				System.out.println("Enter externalPressure");
				Double externalPressure = variableRequest("system pressure","values in atm and dot for decimal places.",sc)*760.0;
				xb = variableFill("Xbottom","dot for decimal places.",sc,xb);
				xd = variableFill("Xdistillate","dot for decimal places.",sc,xd);
				z = variableFill("Zf","feed global composition.",sc,z);
				System.out.println("Feed thermodinamic condiction: (type option number)");
				choice = variableFill("(1) Subcooled liquid\n(2) Bubble-point liquid\n(3) Partially vaporized\n(4) Dew-point vapor\n(5) Superheated vapor\n",null,sc,choice);				
				switch(choice.intValue()) { 
				case 1:
					q = variableFill("q parameter","dot for decimal places",sc,q);
				break;
				case 2:
					q = 1.0;
				break;
				case 3:
					q = 1-variableFill("molar fraction vaporized","dot for decimal places",sc,q);
				break;
				case 4:
					q = 0.0;
				break;
				case 5:
					q = variableFill("q parameter","dot for decimal places",sc,q);
				break;
				default:
					throw new BrainException("Invalid option!");
				}
				r = variableFill("reflux ratio","type the proportion to minimal reflux rate.\nFor ex: 1.2 means a reflux ration of 120 % of minimal.\nDot for decimal places.",sc,r);
				MccabeThiele mt = new MccabeThiele(externalPressure, xd, xb, z, q, r, vpm1, vpm2, gm);
				List<Plate> plateList = new ArrayList<>();
				plateList = mt.plateList();
				for(Plate p : plateList) {
					System.out.println(p);
				}
				running = false;
			}
			catch(BrainException e) {
				System.out.println("Oi");
				System.out.println(e.getMessage());
			}
		}
		sc.close();
	}
	public static Double variableRequest(String name, String obs, Scanner sc) {
		System.out.println("\nEnter "+name+" value:");
		if(!(obs == null)) {
			System.out.println("Obs: "+obs);
		}
		try {
			Locale.setDefault(Locale.US);
			Double variable = Double.valueOf(sc.nextLine());
			return variable;
		}
		catch(RuntimeException e) {
			throw new BrainException("Input mismatch!\n\n");
		}
	}
	public static Double variableFill(String s1, String s2, Scanner sc, Double variable){
		if(variable == null) {
			variable = variableRequest(s1,s2,sc);
			return variable;
		}else {
			return variable;
		}
	}
}
