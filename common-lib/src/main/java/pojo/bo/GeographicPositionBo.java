package pojo.bo;

import java.util.List;

/**
 * @author yuton
 * @version 1.0
 * @description 地理位置请求Bo
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

    public int getHomeMobileCountryCode() {
        return homeMobileCountryCode;
    }

    public void setHomeMobileCountryCode(int homeMobileCountryCode) {
        this.homeMobileCountryCode = homeMobileCountryCode;
    }

    public int getHomeMobileNetworkCode() {
        return homeMobileNetworkCode;
    }

    public void setHomeMobileNetworkCode(int homeMobileNetworkCode) {
        this.homeMobileNetworkCode = homeMobileNetworkCode;
    }

    public String getRadioType() {
        return radioType;
    }

    public void setRadioType(String radioType) {
        this.radioType = radioType;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getConsiderIp() {
        return considerIp;
    }

    public void setConsiderIp(String considerIp) {
        this.considerIp = considerIp;
    }

    public List<CellTowersBo> getCellTowers() {
        return cellTowers;
    }

    public void setCellTowers(List<CellTowersBo> cellTowers) {
        this.cellTowers = cellTowers;
    }

    public List<WifiAccessPointsBo> getWifiAccessPointsBos() {
        return wifiAccessPointsBos;
    }

    public void setWifiAccessPointsBos(List<WifiAccessPointsBo> wifiAccessPointsBos) {
        this.wifiAccessPointsBos = wifiAccessPointsBos;
    }
}
