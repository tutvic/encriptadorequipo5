package principal;


public class Huffman {
	String cadena;
	ListaArbol caracteres;
	Arbol arbol;
	Lista caminos;
	String codigo;
	public Huffman(){

	}
	public Huffman(String arg){
		this.Codificar(arg);
	}

	public String Codificar(String arg) {    
		cadena = arg;
		caracteres = new ListaArbol();
		caracteres.toListaArbol(cadena);
		caracteres.generarArbol();
		arbol = caracteres.arbolEnLaPosicion(1);
		caracteres.eliminaPosicion(1);
		caracteres = null;
		arbol.establecerCaminos("", arbol.raiz);
		caminos = arbol.getCaminos();
		codigo = arbol.toCodigoHuffman(cadena, caminos);
		return codigo;
	}

	public String Decodificar(String arg1){

		String frase = "";
		String[] partes = arg1.split("////");
		String temp = partes[1];

		for(int i=0;i<temp.length();i++){
			frase+=toBinarioFromDecimal((int)temp.charAt(i));
		}

		frase = frase.substring(0,frase.length()-Integer.parseInt(partes[2]));

		String[] caracteres = partes[0].split("<");

		temp = frase;
		frase = "";
		while(temp.length()>0){
			for(int i = 0;i<caracteres.length;i++){
				if(temp.startsWith(caracteres[i].substring(1))){
					frase+=caracteres[i].charAt(0);
					temp = temp.substring(caracteres[i].length()-1);
					break;
				}
			}
		}
		return frase;
	}

	public String toChar(String s){
		int tam = 0;

		if((s.length()%8)!=0){
			do{
				s+='0';
				tam++;
			}while(s.length()%8!=0);
		}

		String temp = s;
		s = "";
		while(temp.length()>0){
			s = s+toCharFromBinario(temp.substring(0,8));
			temp = temp.substring(8);
		}

		return s+"////"+tam;
	}	

	public char toCharFromBinario(String cad){
		int val = 0;
		int cont = 0;
		for(int i=7;i>=0;i--){
			val = (int) (val+(Integer.parseInt(cad.charAt(cont)+"")*Math.pow(2, i)));
			cont++;
		}
		return (char)val;
	}

	public String toBinarioFromDecimal(int decimal){
		String bin = "";
		while ( decimal >0 ) {
			bin = decimal % 2 + bin;
			decimal /= 2;
		}

			while(bin.length()<8){
				bin = "0"+bin;
			}
		return bin; 
	}
}