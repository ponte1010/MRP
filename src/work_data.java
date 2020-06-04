import java.util.ArrayList;

//==============================================================================================
//作業情報の格納クラス
class work_data{
	private String work_ID;
	private String work_name;	
	private String MTM_data;
	private String work_time;

	public work_data(String ID) {
		this.work_ID = ID;
	}
	//-----------------------------------------	
	public String getWork_ID(){
		return work_ID;
	}
	//-----------------------------------------	
	public String getWork_name() {
		return work_name;
	}
	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}
	//-----------------------------------------
	public String getMTM_data() {
		return MTM_data;
	}
	public void setMTM_data(String MTM_data) {
		this.MTM_data = MTM_data;
	}
	//-----------------------------------------
	public String getWork_time() {
		return work_time;
	}
	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
	//-----------------------------------------
}	//--class work_data
//==============================================================================================
