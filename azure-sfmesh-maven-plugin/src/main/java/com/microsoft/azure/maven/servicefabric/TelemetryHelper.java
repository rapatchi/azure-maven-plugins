package com.microsoft.azure.maven.servicefabric;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.azure.maven.telemetry.TelemetryProxy;

import org.apache.maven.plugin.logging.Log;
import java.util.HashMap;
import java.util.Map;

public class TelemetryHelper {
	private static final TelemetryClient client= new TelemetryClient();

	public static void sendEvent(TelemetryEventType type, String value, TelemetryProxy proxy){
		try {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("Description", value);
			proxy.trackEvent(type.getValue(), properties);
			Thread.sleep(1500);
		}
		catch(InterruptedException e){
			// Not handling this exception as failing to send telemetry event is acceptable
		}
	}
}