//==============================================================================
//		readWriteDataPlan.java
//			2020/06/06		Masahiro Arakawa
//	.\\data\\\plan.csv ����
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
	static ArrayList<Plan>	planList;					//---Plan �I�u�W�F�N�g�̃��X�g(���ꕨ)
	//-------------------------------------------------
	static String readFName=	".\\data\\plan.csv";	//�v��f�[�^�t�@�C��(����)
	static String writeFName=	".\\data\\outMRP.csv";	//�v��f�[�^�t�@�C��(�o��)
	//=================================================
	public ReadDataPlan(  ){
		planList= new ArrayListt<Plan>();
		readDaraFromCsvFile();
	}
	//==================================================
	//---  planList ���O������get����@(�O�������X�g��Plan�I�u�W�F�N�g�𗘗p����Ƃ��C���̃��\�b�h�𗘗p����)
	public static ArrayList getPlanList(){
		return(planList);
	}
	//==================================================
	//  �v��f�[�^���I�u�W�F�N�g�Ɋi�[���郁�\�b�h(�t�@�C������̓ǂݍ���)
	public static void readDaraFromCsvFile() {

		try {
			FileReader		fr = new FileReader(readFName);
			BufferedReader	br = new BufferedReader(fr);
			//------------------------------------------------
			String		s;
			//------------------------------------------------
			String[]	strArray;
			int			line=0;
			Plan		oPlan;			//Plan �I�u�W�F�N�g�̕ϐ�
			//-------------------------------------------------
			while((s =br.readLine( )) != null){
				if(s.indexOf("//")>=0) continue;

				if(flgPrint==1){
					System.out.println((line++)+">>"+s);
				}
				//------��������---�@Plan �I�u�W�F�N�g�Ƀt�@�C������ǂݍ��񂾃f�[�^���i�[����(�t�@�C����1�s��Plan�I�u�W�F�N�g1�ɑΉ�����)
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
	//  list�ւ̓o�^(�J�n���ԏ��ɕ��ׂ�)
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
	// ���v�ʌv��̃f�[�^���t�@�C���ɏo�͂��郁�\�b�h(�t�@�C���ւ̏o��)  �O�����痘�p����
	//                                   <----- outList�̒��g�͊O���̃v���O�����ō쐬���遦
	//   �����́@�o�͗p���ʂ̃��X�g�@(1�s��String�^�ŏo�͓��e���L�q����) 
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
