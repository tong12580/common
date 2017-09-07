package pojo.bo;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/4/7 16:13
 */
public class GpsBo {
    private double wgLat;
    private double wgLon;

    public double getWgLat() {
        return wgLat;
    }

    public GpsBo setWgLat(double wgLat) {
        this.wgLat = wgLat;
        return this;
    }

    public double getWgLon() {
        return wgLon;
    }

    public GpsBo setWgLon(double wgLon) {
        this.wgLon = wgLon;
        return this;
    }

    public GpsBo() {
    }

    public GpsBo(double wgLat, double wgLon) {
        this.wgLat = wgLat;
        this.wgLon = wgLon;
    }

    @Override
    public String toString() {
        return wgLat + "," + wgLon;
    }
}
