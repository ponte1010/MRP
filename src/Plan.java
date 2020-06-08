//==============================================================================
//		aPlan.java
//			2020/06/06		Masahiro Arakawa
//	.\\data\\\plan.csvの1行を格納する
//==============================================================================

public class Plan {

	//=================================================
	String JobID;
	String Product;
	int WC;
	int StartTime;
	int FinishedTime;
	//=================================================
	// コンストラクタ　初期化
	public Plan(){
		JobID="No-Name";
		Product="";
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
