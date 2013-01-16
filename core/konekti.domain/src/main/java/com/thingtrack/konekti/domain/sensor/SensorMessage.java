package com.thingtrack.konekti.domain.sensor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("MESSAGE")
public class SensorMessage extends Sensor {

}
