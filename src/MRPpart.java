//==============================================================================
//MRPpart.java
//2020/06/12		Masahiro Arakawa
//部品名とmrpのデータを格納する1次元配列を格納する
//2020/06/16		Masahiro Arakawa修正(テーブルの変更のため)
//==============================================================================
public class MRPpart {

	//=================================================
	static int MAX=200;
	String part;
	int mrpData[];
	//=================================================
	// コンストラクタ初期化(2020/06/16) 引数
	public MRPpart( String xPart){
		part=xPart;
		mrpData =new int[MAX];
		for(int i=0; i<MAX; i++){
			mrpData[i]=0;
		}
	}
	//-------------------------------------------------
	public void addMrpData( int xj){
		mrpData[xj]++;
	}
	//-------------------------------------------------
	public void setMrpData( int xj, int data){
		mrpData[xj] += data;
	}
	//==================================================
	//20200616修正
	public String getPartName( ){
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
	// 変更20200616 compareName() -> comparePartName()
	public boolean comparePartName( String str ){
		boolean  result= str.equals(part);  /// compareTo -> equals ���(20200612)
		return(result);
	}
	//==================================================
}
