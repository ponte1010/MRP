import java.util.ArrayList;

//==============================================================================================
//部品情報を格納する
class parts_structure{
	private String parts_ID;
	private String parts_name;
	private String actualparts_name;	
	private String work_order_ID;
	private String work_ID;	
	private ArrayList<parts_structure> child_list = new ArrayList<parts_structure>();
	work_data work;

	public parts_structure(String parent){
		this.parts_ID = parent;
	}
	//-----------------------------------------
	public String get_parts_ID(){
		return this.parts_ID;
	}
	//-----------------------------------------
	public void setChild(parts_structure ps){
		child_list.add(ps);
	}
	public parts_structure getChild(int i){
		parts_structure xps;
		xps = child_list.get(i);
		return xps;
	}
	public int getNumOfChild(){
		int num= child_list.size();
		return num;
	}
	//-----------------------------------------
	public void setName(String name){
		this.parts_name = name;
	}
	public String getName(){
		return(parts_name);
	}
	//-----------------------------------------
	public String getwork_order_ID() {
		return work_order_ID;
	}
	public void setwork_order_ID(String work_order_ID) {
		this.work_order_ID = work_order_ID;
	}
	//-----------------------------------------
	public String getActualparts_name() {
		return actualparts_name;
	}
	public void setActualparts_name(String actualparts_name) {
		this.actualparts_name = actualparts_name;
	}
	//-----------------------------------------	
	public String getWork_ID() {
		return work_ID;
	}
	public void setWork_ID(String work_ID) {
		this.work_ID = work_ID;
	}
	//-----------------------------------------	
	public void setWork(work_data work) {
		this.work = work;
	}
	public work_data getWork() {
		return work;
	}
}	//--class parts_structure
//==============================================================================================
