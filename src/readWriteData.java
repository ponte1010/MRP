//==============================================================================
//		readWriteDataPlan.java
//			2020/06/06		Masahiro Arakawa
//	.\\data\\\plan.csv から
//  -------------------
//==============================================================================
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class ReadDataPlan {
	static int	MAX=1000;
	static int	flgPrint=1;
	//-------------------------------------------------
	static ArrayList<Plan>	planList;					//---Plan オブジェクトのリスト(入れ物)
	//-------------------------------------------------
	static String readFName=	".\\data\\plan.csv";	//計画データファイル(入力)
	static String writeFName=	".\\data\\outMRP.csv";	//計画データファイル(出力)
	//=================================================
	public ReadDataPlan(  ){
		planList= new ArrayListt<Plan>();
		readDaraFromCsvFile();
	}
	//==================================================
	//---  planList を外部からgetする　(外部がリスト＆Planオブジェクトを利用するとき，このメソッドを利用する)
	public static ArrayList getPlanList(){
		return(planList);
	}
	//==================================================
	//  計画データをオブジェクトに格納するメソッド(ファイルからの読み込み)
	public static void readDaraFromCsvFile() {

		try {
			FileReader		fr = new FileReader(readFName);
			BufferedReader	br = new BufferedReader(fr);
			//------------------------------------------------
			String		s;
			//------------------------------------------------
			String[]	strArray;
			int			line=0;
			Plan		oPlan;			//Plan オブジェクトの変数
			//-------------------------------------------------
			while((s =br.readLine( )) != null){
				if(s.indexOf("//")>=0) continue;

				if(flgPrint==1){
					System.out.println((line++)+">>"+s);
				}
				//------ここから---　Plan オブジェクトにファイルから読み込んだデータを格納する(ファイルの1行がPlanオブジェクト1つに対応する)
				strArray=s.split(",");

				oPlan= new Plan();
				oPlan.setJobID(strArray[0]));
				oPlan.setProduct(strArray[1]));
				oPlan.setWC(Integer.parseInt(strArray[2]));
				oPlan.setStartTime(   Integer.parseInt(strArray[3]);
				oPlan.setFinishedTime(Integer.parseInt(strArray[4]);

				addPlan2List( oPlan );

			}//--- while()-end
			fr.close( );

		}catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	//--------------------------------------------------
	//  listへの登録(開始時間順に並べる)
	public static void addPlan2List( Plan oPlan ) {

			plan	xPlan;

			int		i;
			for(i=0; i<planList.size();i++){
				xPlan = planList.get(i);

				if(oPlan.getStartTime()<xPlan.getStartTime()  ){
					planList.add(i, oPlan);
				}
			}
			if(planList.size()==0){
				planList.add(oPlan);
			}else if( i== <planList.size() ){
				planList.add(oPlan);
			}
	}
	//==================================================
	// 所要量計画のデータをファイルに出力するメソッド(ファイルへの出力)  外部から利用する
	//                                   <----- outListの中身は外部のプログラムで作成する※
	//   引数は　出力用結果のリスト　(1行にString型で出力内容を記述する) 
	//   ---------------------------------------------------------------
	public static int writeMRPResult2CsvFile( ArrayList<String> outList ) {

		String str;

		try {
			FileWriter fw =     new FileWriter(writeFName);
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i=0; i< outList.size(); i++){
				str = outList.get(i);
				bw.write(str);
			}
			//-------------------------------
			bw.close( );

			return(1);
		}catch (Exception e) {
			System.out.println("Exception: " + e);
			return(-1);
		}

	
	}
	//==================================================
}
