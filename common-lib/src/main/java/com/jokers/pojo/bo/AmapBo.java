package com.jokers.pojo.bo;

/**
 * <p>AmapBo class.</p>
 *
 * @author yuton
 * @version 1.0
 *  高德API消息接收
 * @since 2017/4/17 11:22
 */
public class AmapBo {
    /**
     * status : 1
     * info : OK
     * infocode : 10000
     * result : {"type":"4","location":"113.9434017,22.5496889","radius":"550","desc":"广东省 深圳市 南山区 高新中一道 靠近招商银行(科兴科学园社区支行)","country":"中国","province":"广东省","city":"深圳市","citycode":"0755","adcode":"440305","road":"高新中一道","street":"高新中一道","poi":"招商银行(科兴科学园社区支行)"}
     */

    private String status;
    private String info;
    private String infocode;
    private ResultBean result;

    /**
     * <p>Getter for the field <code>status</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStatus() {
        return status;
    }

    /**
     * <p>Setter for the field <code>status</code>.</p>
     *
     * @param status a {@link java.lang.String} object.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <p>Getter for the field <code>info</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getInfo() {
        return info;
    }

    /**
     * <p>Setter for the field <code>info</code>.</p>
     *
     * @param info a {@link java.lang.String} object.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * <p>Getter for the field <code>infocode</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getInfocode() {
        return infocode;
    }

    /**
     * <p>Setter for the field <code>infocode</code>.</p>
     *
     * @param infocode a {@link java.lang.String} object.
     */
    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    /**
     * <p>Getter for the field <code>result</code>.</p>
     *
     * @return a {@link com.jokers.pojo.bo.AmapBo.ResultBean} object.
     */
    public ResultBean getResult() {
        return result;
    }

    /**
     * <p>Setter for the field <code>result</code>.</p>
     *
     * @param result a {@link com.jokers.pojo.bo.AmapBo.ResultBean} object.
     */
    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * type : 4
         * location : 113.9434017,22.5496889
         * radius : 550
         * desc : 广东省 深圳市 南山区 高新中一道 靠近招商银行(科兴科学园社区支行)
         * country : 中国
         * province : 广东省
         * city : 深圳市
         * citycode : 0755
         * adcode : 440305
         * road : 高新中一道
         * street : 高新中一道
         * poi : 招商银行(科兴科学园社区支行)
         */

        private String type;
        private String location;
        private String radius;
        private String desc;
        private String country;
        private String province;
        private String city;
        private String citycode;
        private String adcode;
        private String road;
        private String street;
        private String poi;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(String road) {
            this.road = road;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getPoi() {
            return poi;
        }

        public void setPoi(String poi) {
            this.poi = poi;
        }
    }
}
