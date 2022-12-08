package brain;

import java.io.Serializable;

public class Plate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Double X;
	private Double Y;
	private Double temperature;
	public Double getX() {
		return X;
	}
	public Double getY() {
		return Y;
	}
	public Double getTemperature() {
		return temperature;
	}
	@Override
	public String toString() {
		return "Plate [Nº = +"+id+" X=" + X + ", Y=" + Y + ", temperature=" + temperature + "K ]";
	}
	
}
