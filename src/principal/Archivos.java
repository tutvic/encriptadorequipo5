package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Archivos {

	public String leerArchivo(String dir){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String cadena = "";
		try {
			archivo = new File (dir);
			br  = new BufferedReader (new InputStreamReader (new FileInputStream (archivo), "utf-8"));   
			fr = new FileReader (archivo);
			String linea;
			while((linea=br.readLine())!=null)
				cadena = cadena+linea;
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fr ){   
					fr.close();     
					br.close();
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}

		cadena = cadena.replaceAll("EOL", "\n");
		return cadena;
	}

}
