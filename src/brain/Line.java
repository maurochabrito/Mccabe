package brain;

public class Line {
	Double alpha;
	Double betha;
	
	public Line(Double alpha, Double betha) {
		this.alpha = alpha;
		this.betha = betha;
	}
	public Double y(Double X) {
		Double y = alpha*X+betha;
		return y;
	}
	public Double x(Double Y) {
		Double x = (Y-betha)/alpha;
		return x;
	}
	public Boolean compareTo(Line otherLine, Double X) {
		return this.y(X) > otherLine.y(X);
	}
	public Double intersection(Line otherLine) {
		Double X = 0.0; //initial X
		Double h = 0.001; //Sensibility
		Boolean comparation = this.compareTo(otherLine, X);
		Boolean newComparation = this.compareTo(otherLine, X+h);
		while(newComparation == comparation) {
			X = X+h;
			comparation = newComparation;
			newComparation = this.compareTo(otherLine, X+h);
		}
		Double limitX = X;
		X = (limitX+X)/2;
		return X;
	}
}
