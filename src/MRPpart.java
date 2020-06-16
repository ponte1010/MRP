//==============================================================================
//		MRPpart.java
//			2020/06/12		Masahiro Arakawa
//	部品名とmrpのデータを格納する1次元配列を格納する
//==============================================================================

public class MRPpart {

	//=================================================
	static int MAX=200;
	String part;
	int mrpData[];
	//=================================================
	// コンストラクタ　初期化
	public MRPpart( String str){
		part=str;
		mrpData =new int[MAX];
		for(int i=0; i<MAX; i++){
			mrpData[i]=0;
		}
	}
	//-------------------------------------------------
	public void addMrpData( int xj){
		mrpData[xj]=++;
	}
	//-------------------------------------------------
	public void setMrpData( int xj, int data){
		mrpData[xj] += data;
	}
	//==================================================
	public String getName( ){
		return(part);
	}
	//-------------------------------------------------
	public int getMrpData(int xj ){
		return(  mrpData[xj]);
	}
	//-------------------------------------------------
	public String getMRPResult( ){
		String	str;

		str=part+",";

		for(int i=0; i<MAX; i++){
			str += ","+ mrpData[i];
		}
		str+=",\n";

		return( str );
	}
	//==================================================
	public boolean compareName( String str ){
		boolean  result= str.compareTo(part);
		return(result);
	}
	//==================================================
}
