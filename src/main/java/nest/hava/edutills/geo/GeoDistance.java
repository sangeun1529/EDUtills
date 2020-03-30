package nest.hava.edutills.geo;

public class GeoDistance {
	
	private static final int RADIUS = 6378135;

	public static double calculate(double lat1, double lng1, double lat2, double lng2)
	{	
		Point p1 = new Point(lat1,lng1);
		Point p2 = new Point(lat2,lng2);
		
		double distance =  distance(p1, p2);
		
		return distance;
		
	}
	
	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = (double) (earthRadius * c);

	    return dist;
	    }
	
	public static double distance(Point p1, Point p2) {
		double p1lat = Math.toRadians(p1.getLat());
		double p1lon = Math.toRadians(p1.getLon());
		double p2lat = Math.toRadians(p2.getLat());
		double p2lon = Math.toRadians(p2.getLon());
		return RADIUS
				* Math.acos(makeDoubleInRange(Math.sin(p1lat) * Math.sin(p2lat)
						+ Math.cos(p1lat) * Math.cos(p2lat)
						* Math.cos(p2lon - p1lon)));
	}
	
	public static double makeDoubleInRange(double d) {
		double result = d;
		if (d > 1) {
			result = 1;
		} else if (d < -1) {
			result = -1;
		}
		return result;
	}

}
