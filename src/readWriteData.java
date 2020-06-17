//==============================================================================
//readWriteData.java
//	2020/06/06		Masahiro Arakawa
//.\\data\\\plan.csv ､ｫ､・
//-------------------
//==============================================================================
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class ReadWriteData {

	static int	MAX=1000;
	static int	flgPrint=1;
	//-------------------------------------------------
	static ArrayList<Plan>	planList;					//---Plan ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ､ﾎ･・ｹ･ﾈ(ﾆ､・ｪ)
	//-------------------------------------------------
	static MRPCal	mrp;
	//-------------------------------------------------
	static String readFName=	".\\data\\plan.csv";	//ｷﾗｲ隘ﾇ｡ｼ･ｿ･ﾕ･｡･､･・ﾆﾎﾏ)
	static String writeFName=	".\\data\\outMRP.csv";	//ｷﾗｲ隘ﾇ｡ｼ･ｿ･ﾕ･｡･､･・ｽﾐﾎﾏ)
	//=================================================

	public static void main(String[] args) {
		// TODO ｼｫﾆｰﾀｸﾀｮ､ｵ､・ｿ･皈ｽ･ﾃ･ﾉ｡ｦ･ｹ･ｿ･ﾖ
		ReadWriteData rdp= new ReadWriteData();			// ･ﾕ･｡･､･・ﾎﾆﾉ､ﾟｹ､ﾟｴﾞ､・
		//====================================
		// MRPｷﾗｻｻ
		mrp= new MRPCal(planList);		//MRPCal･ｯ･鬣ｹ､ﾎ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈｺ鋿ｮ
		mrp.calMrp();					//MRPｷﾗｻｻ
		//-----------------------------------
		// ｷ・ﾌﾉｽｼｨ
		ArrayList<String> outList= new ArrayList<String>();
		int num= mrp.getNumPartsInList();
		for(int i=0; i<num; i++){
			String str=mrp.getMRPdataForPart(i);
			outList.add(str);
		}
		writeMRPResult2CsvFile( outList );
		//===================================
	}
	//=================================================
	public ReadWriteData(  ){
		planList= new ArrayList<Plan>();
		readDaraFromCsvFile();
	}
	//==================================================
	//---  planList ､ｰﾉｫ､馮et､ｹ､・｡(ｳｰﾉｬ･・ｹ･ﾈ｡lan･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ､ﾑ､ｹ､・ﾈ､ｭ｡､､ｳ､ﾎ･皈ｽ･ﾃ･ﾉ､ﾑ､ｹ､・
	public static ArrayList getPlanList(){
		return(planList);
	}
	//==================================================
	//  ｷﾗｲ隘ﾇ｡ｼ･ｿ､ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ､ﾋｳﾊﾇｼ､ｹ､・皈ｽ･ﾃ･ﾉ(･ﾕ･｡･､･・ｫ､鬢ﾎﾆﾉ､ﾟｹ､ﾟ)
	public static void readDaraFromCsvFile() {

		try {
			FileReader		fr = new FileReader(readFName);
			BufferedReader	br = new BufferedReader(fr);
			//------------------------------------------------
			String		s;
			//------------------------------------------------
			String[]	strArray;
			int			line=0;
			Plan		oPlan;			//Plan ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ､ﾎﾊﾑｿ・
			//-------------------------------------------------
			while((s =br.readLine( )) != null){
				if(s.indexOf("//")>=0) continue;

				if(flgPrint==1){
					System.out.println((line++)+">>"+s);
				}
				//------､ｳ､ｳ､ｫ､・--｡｡Plan ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ､ﾋ･ﾕ･｡･､･・ｫ､鯣ﾉ､ﾟｹ､ﾀ･ﾇ｡ｼ･ｿ､ﾊﾇｼ､ｹ､・･ﾕ･｡･､･・ﾎ1ｹﾔ､ｬPlan･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ1､ﾄ､ﾋﾂﾐｱ､ｹ､・
				strArray=s.split(",");

				oPlan= new Plan();
				oPlan.setJobID(strArray[0]);
				oPlan.setPart(strArray[1]);
				oPlan.setProduct(strArray[2]);
				oPlan.setWC(Integer.parseInt(strArray[3]));
				oPlan.setStartTime(   Integer.parseInt(strArray[4]));
				oPlan.setFinishedTime(Integer.parseInt(strArray[5]));

				addPlan2List( oPlan );

			}//--- while()-end
			fr.close( );

		}catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	//--------------------------------------------------
	//  list､ﾘ､ﾎﾅﾐﾏｿ(ｳｫｻﾏｻｴﾖｽ遉ﾋﾊﾂ､ﾙ､・
	public static void addPlan2List( Plan oPlan ) {

			Plan	xPlan;

			int		i;
			for(i=0; i<planList.size();i++){
				xPlan = planList.get(i);

				if(oPlan.getStartTime()<xPlan.getStartTime()  ){
					planList.add(i, oPlan);
					break;      //20200611 add
				}
			}
			if(planList.size()==0){
				planList.add(oPlan);
			}else if( i >= planList.size() ){
				planList.add(oPlan);
			}
	}
	//==================================================
	// ｽ・ﾗﾎﾌｷﾗｲ隍ﾎ･ﾇ｡ｼ･ｿ､ﾕ･｡･､･・ﾋｽﾐﾎﾏ､ｹ､・皈ｽ･ﾃ･ﾉ(･ﾕ･｡･､･・ﾘ､ﾎｽﾐﾎﾏ)  ｳｰﾉｫ､鯱ﾑ､ｹ､・
	//                                   <----- outList､ﾎﾃ豼ﾈ､ﾏｳｰﾉﾎ･ﾗ･愠ｰ･鬣爨ﾇｺ鋿ｮ､ｹ､・ｨ
	//   ｰ惞ﾏ｡｡ｽﾐﾎﾏﾍﾑｷ・ﾌ､ﾎ･・ｹ･ﾈ｡｡(1ｹﾔ､ﾋStringｷｿ､ﾇｽﾐﾎﾏﾆ簣ﾆ､ｭｽﾒ､ｹ､・
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
