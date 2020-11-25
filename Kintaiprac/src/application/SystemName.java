package application;

import java.net.InetAddress;

public class SystemName {
	public  String getHostName() {
	    try {
	        return InetAddress.getLocalHost().getHostName();
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "UnknownHost";
	}
}
