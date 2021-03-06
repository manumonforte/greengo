package business.employee;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Temporary.findByid", query = "select obj from Temporary obj where :id = obj.id ")
public class Temporary extends Employee implements Serializable {

	private static final long serialVersionUID = 0;

	private Integer numWorkedHours;

	public Temporary() {
	}

	public Temporary(TTemporaryEmployee tt) {
		super(tt);
		numWorkedHours = tt.getNumWorkedHours();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getNumWorkedHours() {
		return numWorkedHours;
	}

	public void setNumWorkedHours(Integer numWorkedHours) {
		this.numWorkedHours = numWorkedHours;
	}

	@Override
	public Float getDetailedSalary() {
		Float result;
		result = getSalary() + numWorkedHours * 4.6f;
		return result;
	}

}