package pojo.bo;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/4/20 16:56
 */
public class CellTowersBo {

    /**
     * cellId : 42 小区唯一标识符
     * locationAreaCode : 415 GSM 和 WCDMA 网络的位置区域代码 (LAC)。CDMA 网络的网络 ID (NID)。
     * mobileCountryCode : 310 移动电话基站的移动国家代码 (MCC)。
     * mobileNetworkCode : 410 移动电话基站的移动网络代码。对于 GSM 和 WCDMA，这就是 MNC；CDMA 使用的是系统 ID (SID)。
     * age : 0
     * signalStrength : -60 信号强度(非必须)
     * timingAdvance : 15 时间提前值(非必须)
     */

    private int cellId;
    private int locationAreaCode;
    private int mobileCountryCode;
    private int mobileNetworkCode;
    private int age;
    private int signalStrength;
    private int timingAdvance;

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public int getLocationAreaCode() {
        return locationAreaCode;
    }

    public void setLocationAreaCode(int locationAreaCode) {
        this.locationAreaCode = locationAreaCode;
    }

    public int getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(int mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public int getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(int mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    public int getTimingAdvance() {
        return timingAdvance;
    }

    public void setTimingAdvance(int timingAdvance) {
        this.timingAdvance = timingAdvance;
    }
}
