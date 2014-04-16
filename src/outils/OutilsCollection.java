package outils;

import java.util.Collection;

public class OutilsCollection {
	
	public static StringBuffer listerCollection(Collection obj){
		StringBuffer str = new StringBuffer();
		for ( Object o : obj ){
			str.append(o.toString() + "\n");
		}
		return str;
	}

}
