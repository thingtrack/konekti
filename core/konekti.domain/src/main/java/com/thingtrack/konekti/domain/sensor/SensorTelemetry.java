package com.thingtrack.konekti.domain.sensor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("TELEMETRY")
public class SensorTelemetry extends Sensor {

}