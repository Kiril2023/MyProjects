package accessingdb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "raspberrypi_data")
public class RaspberrypiData {

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer id;
	  private String timestamp;
	  private Integer kwh;
	  private Double pressure;
	  private Double temperature;
	  
	  public RaspberrypiData() {};
	  public RaspberrypiData(String timestamp, Integer kwh, Double pressure, Double temperature) {
		  this.timestamp = timestamp;
		  this.kwh = kwh;
		  this.pressure = pressure;
		  this.temperature = temperature;
	  }

	  public Integer getId() {
		  return id;
	  }

	  public void setId(Integer id) {
		    this.id = id;
	  }
	  public String getTimestamp() {
	    return timestamp;
	  }

	  public void setTimestamp(String timestamp) {
	    this.timestamp = timestamp;
	  }

	  public Integer getKwh() {
		    return kwh;
		  }

		  public void setKwh(Integer kwh) {
		    this.kwh = kwh;
		  }
		  
	  public Double getPressure() {
	    return pressure;
	  }

	  public void setPressure(Double pressure) {
	    this.pressure = pressure;
	  }
	  
	  public Double getTemperature() {
		    return temperature;
		  }

	  public void setTemperature(Double temperature) {
		    this.temperature = temperature;
		  }
	  
}
