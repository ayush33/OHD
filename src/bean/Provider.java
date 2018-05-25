package bean;

public class Provider {
	int facilitator_id;
	int facility_id;
	int block_id;
	
	public Provider() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Provider(int facilitator_id, int facility_id, int block_id) {
		super();
		this.facilitator_id = facilitator_id;
		this.facility_id = facility_id;
		this.block_id = block_id;
	}
	@Override
	public String toString() {
		return "Provider [facilitator_id=" + facilitator_id + ", facility_id=" + facility_id + ", block_id=" + block_id
				+ "]";
	}
	public int getFacilitator_id() {
		return facilitator_id;
	}
	public void setFacilitator_id(int facilitator_id) {
		this.facilitator_id = facilitator_id;
	}
	public int getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(int facility_id) {
		this.facility_id = facility_id;
	}
	public int getBlock_id() {
		return block_id;
	}
	public void setBlock_id(int block_id) {
		this.block_id = block_id;
	}
	
}
