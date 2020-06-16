//==============================================================================
//MRPCal.java
//	2020/06/12		Masahiro Arakawa
//mrpの計算を行う
//【注意】	未チェックなので各自で実行し確認のこと＋Debug
//※calMrp( )のあとにgetNumPartsInList( )+getMRPdataForPart()を行う。
//2020/06/16		Masahiro Arakawa　修正
//==============================================================================

import java.util.ArrayList;

public class MRPCal{

	//=================================================
	static int MAX=200;
	static int TB= 6;
	static int stdHOUR=60;
	//=================================================
	static ArrayList<Plan>	planList;
	static ArrayList<MRPpart>	mrpPartsList;
	//=================================================
	// コンストラクタ初期化
	public MRPCal( ArrayList<Plan> xList){
		planList= xList;
		mrpPartsList= new ArrayList<MRPpart>();
	}
	//==================================================
	public int getNumPartsInList( ){
		return(  mrpPartsList.size());
	}
	//--------------------------------------------------
	// ※calMrp( )のあとにgetNumPartsInList( )+getMRPdataForPart()を行う。
	// MRPデータのファイル出力用のメソッド（各部品についてMRPの結果の１次元配列をString型で返す→受ける方はそのままファイル出力させる）
	public String getMRPdataForPart(int xj){
		String	str;
		MRPpart	xMRPpart;

		xMRPpart= mrpPartsList.get(xj);
		str= xMRPpart.getMRPResult();

		return( str );
	}
	//-------------------------------------------------
	public  ArrayList<MRPpart> getMrpPartsList(){
		return(  mrpPartsList);
	}
	//==================================================
	// 計算
	public void calMrp( ){

			Plan	xPlan;
			int		startTime;
			String	name;
			MRPpart	xMRPpart;
			boolean	bflg;

			int		i;
			for(i=0; i<planList.size();i++){
				xPlan = planList.get(i);
				startTime=	xPlan.getStartTime();
				name=		xPlan.getPart();

				int	xj= startTime/stdHOUR/TB;
				int	j;
				//---------------------------------------
				for( j=0; j<mrpPartsList.size(); j++){
					xMRPpart= mrpPartsList.get(j);
					bflg=xMRPpart.comparePartName(name);

					if(bflg){
						xMRPpart.addMrpData(xj);
						break;
					}
				}

				if( (mrpPartsList.size()<=0) || (j==mrpPartsList.size()) ){
					MRPpart mp= new MRPpart(name);
					mp.addMrpData(xj);
					mrpPartsList.add( mp );
				}
				//--------------------------------------
			}
	}
	//==================================================
}
