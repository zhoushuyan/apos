package com.wbasic.apos.drivers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

/**
 * Created by zhoushuyan on 2016-09-18.
 */
public class Bluetooth {
    private BluetoothAdapter bluetoothAdapter=null;
    public Bluetooth(){
        if (bluetoothAdapter == null) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
    }

    /**
     * 获得蓝牙设备列表
     * @return
     */
    public JSONArray bluetoothDeviceList(){
        JSONArray deviceList = new JSONArray();
        try{
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : bondedDevices) {
                deviceList.put(deviceToJSON(device));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return deviceList;
    }
    private JSONObject deviceToJSON(BluetoothDevice device) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", device.getName());
        json.put("address", device.getAddress());
        json.put("id", device.getAddress());
        if (device.getBluetoothClass() != null) {
            json.put("class", device.getBluetoothClass().getDeviceClass());
        }
        return json;
    }
}
