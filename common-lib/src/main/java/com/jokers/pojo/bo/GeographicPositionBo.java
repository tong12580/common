package com.jokers.pojo.bo;

import java.util.List;

/**
 * <p>GeographicPositionBo class.</p>
 *
 * @author yuton
 * @version 1.0
 *  地理位置请求Bo
 * @since 2017/4/20 17:10
 */
public class GeographicPositionBo {

    /**
     * homeMobileCountryCode : 310 设备的家庭网络的移动国家代码 (MCC)
     * homeMobileNetworkCode : 410 设备的家庭网络的移动网络代码 (MNC)。
     * radioType : gsm 移动无线网络类型。 暂时为GSM
     * carrier : Vodafone 运营商名称。
     * considerIp : true 指定当 Wi-Fi 和移动电话基站的信号不可用时，是否回退到 IP 地理位置。请注意，请求头中的 IP 地址不能是设备的 IP 地址。默认为 true。将 considerIp 设置为 false 以禁用回退。
     * cellTowers : [] 移动电话基站对象的数组。请参阅下面的移动电话基站对象部分。
     * wifiAccessPoints : [] wifi位置 暂时不用
     */

    private int homeMobileCountryCode;
    private int homeMobileNetworkCode;
    private String radioType;
    private String carrier;
    private String considerIp;
    private List<CellTowersBo> cellTowers;
    private List<WifiAccessPointsBo> wifiAccessPointsBos;

    /**
     * <p>Getter for the field <code>homeMobileCountryCode</code>.</p>
     *
     * @return a int.
     */
    public int getHomeMobileCountryCode() {
        return homeMobileCountryCode;
    }

    /**
     * <p>Setter for the field <code>homeMobileCountryCode</code>.</p>
     *
     * @param homeMobileCountryCode a int.
     */
    public void setHomeMobileCountryCode(int homeMobileCountryCode) {
        this.homeMobileCountryCode = homeMobileCountryCode;
    }

    /**
     * <p>Getter for the field <code>homeMobileNetworkCode</code>.</p>
     *
     * @return a int.
     */
    public int getHomeMobileNetworkCode() {
        return homeMobileNetworkCode;
    }

    /**
     * <p>Setter for the field <code>homeMobileNetworkCode</code>.</p>
     *
     * @param homeMobileNetworkCode a int.
     */
    public void setHomeMobileNetworkCode(int homeMobileNetworkCode) {
        this.homeMobileNetworkCode = homeMobileNetworkCode;
    }

    /**
     * <p>Getter for the field <code>radioType</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRadioType() {
        return radioType;
    }

    /**
     * <p>Setter for the field <code>radioType</code>.</p>
     *
     * @param radioType a {@link java.lang.String} object.
     */
    public void setRadioType(String radioType) {
        this.radioType = radioType;
    }

    /**
     * <p>Getter for the field <code>carrier</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * <p>Setter for the field <code>carrier</code>.</p>
     *
     * @param carrier a {@link java.lang.String} object.
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     * <p>Getter for the field <code>considerIp</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getConsiderIp() {
        return considerIp;
    }

    /**
     * <p>Setter for the field <code>considerIp</code>.</p>
     *
     * @param considerIp a {@link java.lang.String} object.
     */
    public void setConsiderIp(String considerIp) {
        this.considerIp = considerIp;
    }

    /**
     * <p>Getter for the field <code>cellTowers</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<CellTowersBo> getCellTowers() {
        return cellTowers;
    }

    /**
     * <p>Setter for the field <code>cellTowers</code>.</p>
     *
     * @param cellTowers a {@link java.util.List} object.
     */
    public void setCellTowers(List<CellTowersBo> cellTowers) {
        this.cellTowers = cellTowers;
    }

    /**
     * <p>Getter for the field <code>wifiAccessPointsBos</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<WifiAccessPointsBo> getWifiAccessPointsBos() {
        return wifiAccessPointsBos;
    }

    /**
     * <p>Setter for the field <code>wifiAccessPointsBos</code>.</p>
     *
     * @param wifiAccessPointsBos a {@link java.util.List} object.
     */
    public void setWifiAccessPointsBos(List<WifiAccessPointsBo> wifiAccessPointsBos) {
        this.wifiAccessPointsBos = wifiAccessPointsBos;
    }
}
