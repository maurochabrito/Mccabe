package thermodynamicsModel;

public interface VapourPressureModel {
	
	public Double temperature(Double pressure);
	public Double pressure(Double temperature);
}
