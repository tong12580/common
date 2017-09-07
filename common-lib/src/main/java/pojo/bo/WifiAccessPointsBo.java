package pojo.bo;

/**
 * @author yuton
 * @version 1.0
 * @description Wi-Fi 接入点对象
 * @since 2017/4/20 17:15
 */
public class WifiAccessPointsBo {

    /**
     * macAddress : 00:25:9c:cf:1c:ac
     * signalStrength : -43
     * age : 0
     * channel : 11
     * signalToNoiseRatio : 0
     */

    private String macAddress;
    private int signalStrength;
    private int age;
    private int channel;
    private int signalToNoiseRatio;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getSignalToNoiseRatio() {
        return signalToNoiseRatio;
    }

    public void setSignalToNoiseRatio(int signalToNoiseRatio) {
        this.signalToNoiseRatio = signalToNoiseRatio;
    }
}
