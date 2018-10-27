package business.employee;

public class TPermanentEmployee extends TEmployee {
	private float apportionment;

	public TPermanentEmployee() {
	}

	public TPermanentEmployee(Integer id, String id_card_number, float salary, boolean active, Integer id_main_office, float apportionment) {
		super(id, id_card_number, salary, active, id_main_office, "Permanent");
		this.apportionment = apportionment;
	}

	public float getApportionment() {
		return apportionment;
	}

	public void setApportionment(float apportionment) {
		this.apportionment = apportionment;
	}
}