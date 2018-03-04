package com.jokers.pojo.bo;

/**
 * <p>WifiAccessPointsBo class.</p>
 *
 * @author yuton
 * @version 1.0
 *  Wi-Fi 接入点对象
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

    /**
     * <p>Getter for the field <code>macAddress</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * <p>Setter for the field <code>macAddress</code>.</p>
     *
     * @param macAddress a {@link java.lang.String} object.
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * <p>Getter for the field <code>signalStrength</code>.</p>
     *
     * @return a int.
     */
    public int getSignalStrength() {
        return signalStrength;
    }

    /**
     * <p>Setter for the field <code>signalStrength</code>.</p>
     *
     * @param signalStrength a int.
     */
    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    /**
     * <p>Getter for the field <code>age</code>.</p>
     *
     * @return a int.
     */
    public int getAge() {
        return age;
    }

    /**
     * <p>Setter for the field <code>age</code>.</p>
     *
     * @param age a int.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * <p>Getter for the field <code>channel</code>.</p>
     *
     * @return a int.
     */
    public int getChannel() {
        return channel;
    }

    /**
     * <p>Setter for the field <code>channel</code>.</p>
     *
     * @param channel a int.
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * <p>Getter for the field <code>signalToNoiseRatio</code>.</p>
     *
     * @return a int.
     */
    public int getSignalToNoiseRatio() {
        return signalToNoiseRatio;
    }

    /**
     * <p>Setter for the field <code>signalToNoiseRatio</code>.</p>
     *
     * @param signalToNoiseRatio a int.
     */
    public void setSignalToNoiseRatio(int signalToNoiseRatio) {
        this.signalToNoiseRatio = signalToNoiseRatio;
    }
}
