package com.jokers.pojo.bo;

/**
 * <p>GPSussResultBo class.</p>
 *
 * @author yuton
 * @version 1.0
 *  地理位置请求成功返回
 * @since 2017/4/20 19:00
 */
public class GPSussResultBo {

    /**
     * location : {"lat":22.5519702,"lng":113.9393849}
     * accuracy : 997
     */

    private LocationBean location;
    private int accuracy;

    /**
     * <p>Getter for the field <code>location</code>.</p>
     *
     * @return a {@link com.jokers.pojo.bo.GPSussResultBo.LocationBean} object.
     */
    public LocationBean getLocation() {
        return location;
    }

    /**
     * <p>Setter for the field <code>location</code>.</p>
     *
     * @param location a {@link com.jokers.pojo.bo.GPSussResultBo.LocationBean} object.
     */
    public void setLocation(LocationBean location) {
        this.location = location;
    }

    /**
     * <p>Getter for the field <code>accuracy</code>.</p>
     *
     * @return a int.
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * <p>Setter for the field <code>accuracy</code>.</p>
     *
     * @param accuracy a int.
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public static class LocationBean {
        /**
         * lat : 22.5519702
         * lng : 113.9393849
         */

        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
