//==============================================================================
//		MRPCal.java
//			2020/06/12		Masahiro Arakawa
//	mrp�̌v�Z���s��
//	�y���Ӂz	���`�F�b�N�Ȃ̂Ŋe���Ŏ��s���m�F�̂��Ɓ{Debug
//		���@calMrp( )�̂��Ƃ�getNumPartsInList( )+getMRPdataForPart()���s���B
//==============================================================================

public class MRPCal{

	//=================================================
	static int MAX=200;
	static int TB= 6;
	static int stdHOUR=60;
	
	String part;
		int mrpData[];
		
	//=================================================
	static ArrayList<Plan>	planList;
	static ArrayList<MRPpart>	mrpPartsList;
	//=================================================
	// �R���X�g���N�^�@������
	public MRPCal( ArrayList<Plan> xList){
		planList= xList;
		mrpPartsList= new ArrayList<MRPpart>();
	}
	//==================================================
	public int getNumPartsInList( ){
		return(  mrpPartsList.size());
	}
	//--------------------------------------------------
	// ���@calMrp( )�̂��Ƃ�getNumPartsInList( )+getMRPdataForPart()���s���B
	// MRP�f�[�^�̃t�@�C���o�͗p�̃��\�b�h�i�e���i�ɂ���MRP�̌��ʂ̂P�����z���String�^�ŕԂ����󂯂���͂��̂܂܃t�@�C���o�͂�����j
	public String getMRPdataForPart(int xj){
		String	str;
		MRPpart	xMRPpart;

		xMRPpart= mrpPartList.get(xj);
		str= xMRPpart.getMRPResult();

		return(  str );
	}
	//-------------------------------------------------
	public  ArrayList<MRPpart> getMrpPartsList(){
		return(  mrpParstList);
	}
	//==================================================
	// �v�Z
	public void calMrp( ){

			plan	xPlan;
			int		startTime;
			String	name;
			MRPpart	xMRPpart;
			boolean	bflg;

			int		i;
			for(i=0; i<planList.size();i++){
				xPlan = planList.get(i);
				startTime=	xPlan.getStartTime();
				name=		xPlan.getName();
				
				int xj= startTime/stdHOUR/TB;
				//---------------------------------------
				for(int j=0; j<mrpPartsList.size(); j++){
					xMRPpart= mrpPartsList.get(j);
					bflg=xMRPpart.compareName(name);

					if(bflg){
						xMRPpart.addMrpData(xj);
						break;
					}
				}
			}

			if( (j==0) || (j==mrpPartsList.size()) ){
				MRPpart mp= new MRPpart(name);
				mp.addMrpData(xj);
				mrpPartsList.add( mp );
			}
	}
	//==================================================
}