//==============================================================================
// Plan.java
//	2020/06/06		Masahiro Arakawa
//.\\data\\\plan.csvの1行を格納する
//==============================================================================

public class Plan {

	//=================================================
	String JobID;
	String Part;
	String Product;
	int WC;
	int StartTime;
	int FinishedTime;
	//=================================================
	public Plan(){
		JobID="No-Name";
		Part="";
		Product="";
		WC=-1;
		StartTime=-1;
		FinishedTime=-1;
	}
	public Plan(String xJob, String xPart, String xProduct ){
		JobID=xJob;
		Part=xPart;
		Product=xProduct;
		WC=-1;
		StartTime=-1;
		FinishedTime=-1;
	}
	//-------------------------------------------------
	public void setJobID( String xstr){
		JobID= xstr;
	}
	//-------------------------------------------------
	public void setProduct( String xstr){
		Product= xstr;
	}
	//-------------------------------------------------
	public void setPart( String xstr){
		Part= xstr;
	}
	//-------------------------------------------------
	public void setWC( int xdata){
		WC= xdata;
	}
	//-------------------------------------------------
	public void setStartTime( int xdata){
		StartTime= xdata;
	}
	//-------------------------------------------------
	public void setFinishedTime( int xdata){
		FinishedTime= xdata;
	}
	//==================================================
	public String getJobID( ){
		return(JobID);
	}
	//-------------------------------------------------
	public String getProduct( ){
		return(Product);
	}
	//-------------------------------------------------
	public String getPart( ){
		return(Part);
	}
	//-------------------------------------------------
	public int getWC( ){
		return(WC);
	}
	//-------------------------------------------------
	public int getStartTime( ){
		return(StartTime);
	}
	//-------------------------------------------------
	public int getFinishedTime( ){
		return(FinishedTime);
	}
	//==================================================
}
