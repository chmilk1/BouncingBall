package sec;

public class Graphing {
public static double getSlope(int x, int y, int x2, int y2){
	double ymy = y - y2;
	double xmx = x - x2;
	double slope = ymy/xmx;
	return slope;
}
}
