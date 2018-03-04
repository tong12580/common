package com.jokers.pojo.bo;

/**
 * <p>GpsBo class.</p>
 *
 * @author yuton
 * @version 1.0
 * @since 2017/4/7 16:13
 */
public class GpsBo {
    private double wgLat;
    private double wgLon;

    /**
     * <p>Getter for the field <code>wgLat</code>.</p>
     *
     * @return a double.
     */
    public double getWgLat() {
        return wgLat;
    }

    /**
     * <p>Setter for the field <code>wgLat</code>.</p>
     *
     * @param wgLat a double.
     * @return a {@link com.jokers.pojo.bo.GpsBo} object.
     */
    public GpsBo setWgLat(double wgLat) {
        this.wgLat = wgLat;
        return this;
    }

    /**
     * <p>Getter for the field <code>wgLon</code>.</p>
     *
     * @return a double.
     */
    public double getWgLon() {
        return wgLon;
    }

    /**
     * <p>Setter for the field <code>wgLon</code>.</p>
     *
     * @param wgLon a double.
     * @return a {@link com.jokers.pojo.bo.GpsBo} object.
     */
    public GpsBo setWgLon(double wgLon) {
        this.wgLon = wgLon;
        return this;
    }

    /**
     * <p>Constructor for GpsBo.</p>
     */
    public GpsBo() {
    }

    /**
     * <p>Constructor for GpsBo.</p>
     *
     * @param wgLat a double.
     * @param wgLon a double.
     */
    public GpsBo(double wgLat, double wgLon) {
        this.wgLat = wgLat;
        this.wgLon = wgLon;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return wgLat + "," + wgLon;
    }
}
