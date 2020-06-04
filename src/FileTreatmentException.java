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

//==============================================================================================
class FileTreatmentException extends RuntimeException{
	public FileTreatmentException(){
		}
	public FileTreatmentException(String msg){
		super(msg);
	}
}
//--class FileTreatmentException
//==============================================================================================
