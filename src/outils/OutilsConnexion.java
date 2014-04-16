package outils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class OutilsConnexion {	
	public static Properties chargerProps (String pathFile){		
		Properties props = new Properties();
		File file = new File(pathFile);
		FileReader fr = null;
		try {
			fr = new FileReader(file);;
			props.load(fr); // on récupère les paramètres dans l'objet Properties
			fr.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}
}
