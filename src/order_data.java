import java.util.ArrayList;

//==============================================================================================
//注文情報の格納クラス--2016/06/20大橋
class order_data{
	private String orderID;//受注番号
	private String product;//製品のID
	private parts_structure ps;//=====
	
	private String product_size;//製造数
	private String accept_date;//受注日
	private String delivery_date;//納期
	//=====
	public parts_structure get_ps(){
		return this.ps;
	}
	public void set_ps(parts_structure odps){
		this.ps = odps;
	}
	//=====
	//-----------------------------------------
	public order_data(String ID){
		this.orderID = ID;
	}
	public String get_orderID(){
		return orderID;
	}
	//-----------------------------------------
	public void set_product(String p){
		this.product = p;
	}
	public String get_product(){
		return product;
	}
	//-----------------------------------------
	public void set_size(String s){
		this.product_size = s;
	}
	public String get_size(){
		return product_size;
	}
	//-----------------------------------------
	public void set_accept(String acc){
		this.accept_date = acc;
	}
	public String get_accept(){
		return accept_date;
	}
	//-----------------------------------------
	public void set_delivery(String d){
		this.delivery_date = d;
	}
	public String get_delivery(){
		return delivery_date;
	}
	//-----------------------------------------
}