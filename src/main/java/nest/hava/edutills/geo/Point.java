package nest.hava.edutills.geo;

import org.apache.commons.lang3.Validate;

public class Point {

    private double lat;
    private double lon;

    public Point() {
    	
    }

    public Point(double lat, double lon) {
    	Validate.isTrue(!(lat > 90.0 || lat < -90.0), "Latitude must be in [-90, 90]  but was ", lat);
    	Validate.isTrue(!(lon > 180.0 || lon < -180.0), "Longitude must be in [-180, 180] but was ", lon);
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }

}