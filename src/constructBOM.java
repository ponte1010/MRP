package mrp_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class constructBOM {
	static ArrayList<parts_structure> datalist = new ArrayList<parts_structure>();
	static ArrayList<work_data> worklist = new ArrayList<work_data>();
	static ArrayList<order_data> orderlist = new ArrayList<order_data>();
	//-------------------------------------------------------------------------------
	public static void main(String args[ ]) throws IOException{		
		make_structure();
		setPartsName();
		make_worklist();
		make_orderlist();
		setwork_order_ID();//parts_structureに作業情報を入れる

		//----------------------------------------------------
		BufferedReader br =		new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Print -> 1: Parts in BOM, 2: Work Data, 3:Order, 4:Exit");
		String str = br.readLine();
		int istr = Integer.parseInt(str);

		if(istr==1)
			printBOMStructure();		//--BOMの部品構造を中間製品から表示　【利用可】
		else if(istr==2)
			printWorkdata();			//--作業の順序で作業名と部品名, 作業時間を表示　【利用可】
		else if(istr==3)
			printOrderData();			//--受注のリスト　【利用可】
		else 
			System.out.println("Exit");

	}
	//-------------------------------------------------------------------------------
	static void make_structure(){//ownershipから子部品のリストを持つ部品データの作成
		int first;
		int second;
		String[] data;
		String[] parts;
		parts_structure ps;
		parts_structure ps1;
		try{
			//ファイルからデータの読み取り
			File file = new File("C:\\Users\\Yuki Shimazawa\\workspace\\MRP_3\\src\\mrp_2\\data_file\\ownership.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"SJIS"));
			
			String line;
			line = br.readLine();	
			while((line = br.readLine()) != null){
				data = line.split(",", -1);		
				//datalistにまだひとつもデータが格納されていない場合
				if(datalist.size() == 0){
					ps = new parts_structure(data[0]);
					datalist.add(ps);
					ps1 = new parts_structure(data[1]);
					datalist.add(ps1);
					ps.setChild(ps1);
					//datalistにひとつでもデータが格納されている場合
				}else{
					//親部品がdatalistに格納されているかを調べる
					first = index_search(data[0]);
					//親部品がdatalistに格納されていない場合
					if(first == -1){
						ps = new parts_structure(data[0]);
						datalist.add(ps);					
						//子部品がdatalistに格納されているかを調べる
						second = index_search(data[1]);
						//子部品がdatalistに格納されていない場合
						if(second == -1){
							ps1 = new parts_structure(data[1]);
							datalist.add(ps1);
							ps.setChild(ps1);
						}else{
							//子部品がdatalistに格納されている場合
							ps1 = datalist.get(second);
							ps.setChild(ps1);
						}
					}
					//親部品がdatalistに格納されている場合
					else{
						ps = datalist.get(first);
							
						//子部品がdatalistに格納されているかを調べる
						second = index_search(data[1]);
						//子部品がdatalistに格納されていない場合
						if(second == -1){
							ps1 = new parts_structure(data[1]);
							datalist.add(ps1);
							ps.setChild(ps1);
						}else{
							//子部品がdatalistに格納されている場合
							ps1 = datalist.get(second);
							ps.setChild(ps1);
						}
					}
				}
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e2){
		  System.out.println(e2);
		}
	}  //--method make_structure
	//------------------------------------------------------------------------------------------
	static int index_search(String data){
		for(int i=0 ; i < datalist.size() ; i++){
			if(datalist.get(i).get_parts_ID().equals(data)){
				return(i);
			}
		}
		return(-1);
	}  //--method index_search
	//-----------------------------------------------------------------------------------------
//  set names of parts into objects of parts referring to DB(部品名と実部品名の格納)
	static void setPartsName(){
		String[] data;
		String   xStr;
		parts_structure ps;
		try{
			File file = new File("C:\\Users\\Yuki Shimazawa\\workspace\\MRP_3\\src\\mrp_2\\data_file\\partsname.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"SJIS"));

			String line;
			line = br.readLine();
			while((line = br.readLine()) != null){
				data = line.split(",", -1);

				for(int i=0; i<datalist.size(); i++){
					ps = datalist.get(i);
					xStr = ps.get_parts_ID();
					if(xStr.indexOf(data[0])>=0){
						ps.setName(data[1]);
						ps.setActualparts_name(data[2]);
						break;
					}
				}
			}//--- while((line = br.readLine())-end
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e2){
		  System.out.println(e2);
		}
	}  //--method setPartsName 
	//-----------------------------------------------------------------------------------------
	//作業の順番、作業番号をparts_structureクラスに追加する
	static void setwork_order_ID(){
		String[] data;
		String   xStr;
		parts_structure ps;
		work_data wd;
		try{
			File file = new File("C:\\Users\\Yuki Shimazawa\\workspace\\MRP_3\\src\\mrp_2\\data_file\\operation.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"SJIS"));

			String line;
			line = br.readLine();
			while((line = br.readLine()) != null){
				data = line.split(",", -1);

				//parts_IDが一致したら作業データを入れる
				for(int i=0; i<datalist.size(); i++){
					ps= datalist.get(i);
					xStr = ps.get_parts_ID();

					//作業の順番、作業番号をparts_structureクラスに追加
					if(xStr.equals(data[0])){
						ps.setwork_order_ID(data[1]);
						ps.setWork_ID(data[2]);
//System.out.println(ps.getwork_order_ID()+","+ps.getWork_ID());
						//作業番号が一致したら、parts_structureクラスに作業オブジェクトを格納
						for(int ii=0; ii<worklist.size(); ii++){
							wd = worklist.get(ii);
							if(wd.getWork_ID().equals(data[2])){
								ps.setWork(wd);
							}
						}
					}
				}
			}//--- while((line = br.readLine())-end
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e2){
		  System.out.println(e2);
		}
	}  //--method setwork_order_ID 
	//-----------------------------------------------------------------------------------------
	//作業情報のファイルからの読み取り、登録
	static void make_worklist(){
		String[] data;
		work_data wd;
		int first;
		try{
			File file = new File("C:\\Users\\Yuki Shimazawa\\workspace\\MRP_3\\src\\mrp_2\\data_file\\work.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"SJIS"));

			String line;
			line = br.readLine();
			while((line = br.readLine()) != null){
				data = line.split(",", -1);
				
				//worklistにデータが格納されていない場合
				if(worklist.size() == 0){
					wd = new work_data(data[0]);
					worklist.add(wd);
					wd.setWork_name(data[1]);
					wd.setMTM_data(data[2]);
					wd.setWork_time(data[3]);
					//worklistにデータが一つでも格納されている場合
				}else{
					first = worklist_search(data[0]);
					//worklistに作業番号のデータが格納されていない場合
					if(first == -1){
						wd = new work_data(data[0]);
						worklist.add(wd);
						wd.setWork_name(data[1]);
						wd.setMTM_data(data[2]);
						wd.setWork_time(data[3]);
					}
				}
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e2){
		  System.out.println(e2);
		}
	}  //--method setwork_data 
	//-----------------------------------------------------------------------------------------
	static int worklist_search(String data){
		for(int i=0 ; i < worklist.size() ; i++){
			if(worklist.get(i).getWork_ID().equals(data)){
				return(i);
			}
		}
		return(-1);
	}  //--method index_search
	//------------------------------------------------------------------------------------------
	String  printParts="";
	//部品のBOMを表示
	static void	printBOMStructure(){//メインの表示 printParts()を使う
			String[] data;
			String   xStr;
			parts_structure ps = null;
			try {
				BufferedReader stdReader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Part Name? : ");
				String line;
				
				if((line = mButton.text.getText()) != null) { 
						System.out.print("\n BOM of " + line+"\n");
						//--------------------------------------
						int i;
						for(i=0; i<datalist.size(); i++){
							ps = datalist.get(i);
							xStr = ps.getName();
//							if( xStr.indexOf(line) >=0 ) break;
							if(xStr.equals(line)) break;
						}
						if(i<datalist.size()){
							printParts(ps, "");
						}
						//--------------------------------------
						ps = null;
						//System.out.print("\nPart Name? : ");
				}
				stdReader.close();
			} catch (Exception e) {
				e.getStackTrace();
				System.exit(1); 
			}
		}  //--method printBOMStructure 
	//-----------------------------------------------------------------------------------------
	static void printParts(parts_structure ps, String space){//親子関係の表示
		String   xName= ps.getName();
		System.out.print(space+xName+"\n");
		parts_structure xPs;
		String          newSpace=space+"--------";
		int numChild= ps.getNumOfChild();
		for(int i=0; i<numChild; i++){
			xPs=ps.getChild(i);			
			printParts(xPs, newSpace);
		}
	}  //--method printParts 
	//=================================================================================2016/06/21@@@
	static void printOrderData(){
		String   xStr;
		parts_structure ps = null;
		order_data od = null;
		
		for(int x=0; x<orderlist.size();x++){
			od = orderlist.get(x);
			String line = od.get_product();
			
			//System.out.print("odID="+od.get_orderID()); 
			//System.out.print("	od="+od.get_product());
			//System.out.print("	odname="+od.get_ps().getName()); 
			//System.out.println("	size="+od.get_size()); 
			
			int i;
			for(i=0; i<datalist.size(); i++){
				ps = datalist.get(i);
				xStr = ps.getName();
//				if( xStr.indexOf(line) >=0 ) break;
				//if(xStr.equals("line")) break;///===
				if(ps.equals(od.get_ps())) break;
			}
			//System.out.println("ps="+line+"	i="+i+"	size="+datalist.size());
			if(i<datalist.size()){
				System.out.println("ps="+ps.get_parts_ID()); 
				printOrder(ps,"",od.get_size());//
			}
			//--------------------------------------
			ps = null;
			
		}	
	}
	//=================================================================================2016/06/21@@@
	static void printOrder(parts_structure ps, String space, String s){//親子関係の表示
		int size = Integer.parseInt(s);//製造数
		String   xName= ps.getActualparts_name();		
		if(xName.equals("ファントム")){
			System.out.print(space+ps.getName()+"	"+ size+"個"+"\n");
		}else{
			System.out.print(space+xName+"	"+ size+"個"+"\n");
		}
		parts_structure xPs;
		String          newSpace= space +"--------";
		int numChild= ps.getNumOfChild();
		
		for(int i=0; i<numChild; i++){
			xPs=ps.getChild(i);
			int count = 1;
			
			if(i==0){				
				for(int j=1; j<numChild; j++){
					if(xPs.getActualparts_name().equals(ps.getChild(j).getActualparts_name())){
						count++;
					}
				}
				
				count = count*size;
				String c = String.valueOf(count);
				printOrder(xPs, newSpace, c);
				
			}else{
				int ii;
				for( ii=0 ; ii<i; ii++ ){
					if(ps.getChild(ii).getActualparts_name().equals(xPs.getActualparts_name())) {
						break;
					}else{	}
					
				}
				
				if(ii==i){				
					for(int j=i+1; j<numChild; j++){
						if(xPs.getActualparts_name().equals(ps.getChild(j).getActualparts_name())){
							count++;
						}
					}
					count = count*size;
					String c = String.valueOf(count);
					printOrder(xPs, newSpace, c);
				}
			}
		}
	}  //--method printParts 
	//-----------------------------------------------------------------------------------------		
	//作業順序順にデータを格納し、表示する
	static void	printWorkdata(){
		ArrayList<parts_structure> orderlist = new ArrayList<parts_structure>();
		parts_structure ps;
		work_data wd;
		
		//一度、orderlistにデータを格納する
		for(int i=0 ; i < datalist.size() ; i++){
			ps = datalist.get(i);
			orderlist.add(ps);
		}
		//orderlistに作業順序１から格納しなおす
		for(int i=0 ; i < orderlist.size() ; i++){
			ps = datalist.get(i);
			String a = ps.getwork_order_ID();
			int ii = Integer.parseInt(a);
			orderlist.set(ii-1, ps);
		}
		System.out.println("作業順序,		作業名,		部品名,		作業時間");
		for(int i=0 ; i < orderlist.size() ; i++){
			ps = orderlist.get(i);
			wd = ps.getWork();
			System.out.println(ps.getwork_order_ID()+",		"+wd.getWork_name()+",		"+ps.getName()+", 		"+wd.getWork_time()+"秒");
		}
	}  //--method printWorkdata 
	//-----------------------------------------------------------------------------------------	
	//ファイルのデータからリストの作成=============================================================2016/06/20@@@
	static void make_orderlist(){
		String[] data;
		order_data od;
		int first;
		try{
			//	data[0]:受注ID，	[1]製品ID，	[2]製造数，	[3]受注日，	[4]納期
			File file = new File("C:\\Users\\Yuki Shimazawa\\workspace\\MRP_3\\src\\mrp_2\\data_file\\order.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"SJIS"));

			String line;
			line = br.readLine();//一行目は読み飛ばす
			while((line = br.readLine()) != null){
				data = line.split(",", -1);
				
				//orderlistにデータが格納されていない場合
				if(orderlist.size() == 0){
					od = new order_data(data[0]);
					orderlist.add(od);
					od.set_product(data[1]);
					od.set_size(data[2]);
					od.set_accept(data[3]);
					od.set_delivery(data[4]);
					//=====
					parts_structure ps;
					for(int x=0; x<datalist.size();x++){
						ps = datalist.get(x);
						//if(ps.get_parts_ID().equals(data[1])){
						if(ps.getName().equals(data[1])){
							//System.out.println("orderlist_product="+ps.getName());
							od.set_ps(ps);
						}
					}
					//=====
				}else{//orderlistにデータが一つでも格納されている場合
					first = orderlist_search(data[0]);
					
					//orderlistに同一IDのデータがない場合
					if(first == -1){
						od = new order_data(data[0]);
						orderlist.add(od);
						od.set_product(data[1]);
						od.set_size(data[2]);
						od.set_accept(data[3]);
						od.set_delivery(data[4]);
						
						//=====
						parts_structure ps;
						for(int x=0; x<datalist.size();x++){
							ps = datalist.get(x);
							if(ps.get_parts_ID().equals(data[1])){
								od.set_ps(ps);
							}
						}
						//=====
						
					}
				}
			}//while.fin
			
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e2){
		  System.out.println(e2);
		}
	}
	//-----------------------------------------------------------------------------------------
	static int orderlist_search(String data){
		for(int i=0 ; i < orderlist.size() ; i++){
			if(orderlist.get(i).get_orderID().equals(data)){
				return(i);
			}
		}
		return(-1);
	}  //--method index_search
	//-----------------------------------------------------------------------------------------
}	//--class constructBOM
//==============================================================================================
