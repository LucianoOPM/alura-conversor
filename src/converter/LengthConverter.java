package converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LengthConverter {
	/**
	 * Método que toma los valores ingresados por los usuarios
	 * @return <String> Retorna el mensaje con los valores ya convertidos a el valor elegido
	 * @throws NullPointerException Si no se ingresan valores el método retorna un error
	 * @throws NumberFormatException Si no se ingresan los valores numericos el método retorna un error.
	 */
	public static String startConverter() {
		try {
			ArrayList<String> distances = new ArrayList<>();
			distances.add("Milimetro");
			distances.add("Centimetro");
			distances.add("Metro");
			distances.add("Kilometro");
			
			
			Object[] showValues = distances.toArray();
			
			String firstValue = (String) JOptionPane.showInputDialog(
					null, 
					"Seleccione la unidad de medida", 
					"Seleccionar unidad", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					showValues, 
					showValues[0]);
			
			for (int i = 0; i < distances.size(); i++) {
				if(firstValue.equalsIgnoreCase(distances.get(i))) {
					distances.remove(i);
				}
			}
			
			showValues = distances.toArray();
			
			String secondValue = (String) JOptionPane.showInputDialog(
					null, 
					"Inserte a que distancia convertir",
					"Insertar valores",
					JOptionPane.PLAIN_MESSAGE,
					null,
					showValues, showValues[0]);
					
			double value = Double.parseDouble(JOptionPane.showInputDialog(
					null,
					"Ingrese el valor a cambiar de " + firstValue + " a " + secondValue,
					"Ingresar valor", 
					JOptionPane.PLAIN_MESSAGE));
			
				return 	converter(value, firstValue, secondValue);
		} catch (NullPointerException e) {
			return "Se necesita un valor numerico para continuar" + e.getMessage();
		} catch (NumberFormatException e ) {
			return "No se pudo convertir un string a valor numerico" + e.getMessage();
		}
	}
	
	private static String converter(double value, String from, String to) {
		
		DecimalFormat format = new DecimalFormat("#.######");		
		double total = 0;
		
		if(from.equalsIgnoreCase("milimetro")) {
			if(to.equalsIgnoreCase("centimetro")) {
				total = value / 10;
			}else if(to.equalsIgnoreCase("metro")) {
				total = value / 1000;
			}else {
				total = value / 1000000;
			}
		}
		if(from.equalsIgnoreCase("centimetro")) {
			if(to.equalsIgnoreCase("milimetro")) {
				total = value * 10;
			}else if(to.equalsIgnoreCase("metro")) {
				total = value / 100;
			}else {
				total = value / 100000;
			}
		}
		if(from.equalsIgnoreCase("metro")) {
			if(to.equalsIgnoreCase("milimetro")) {
				total = value * 1000;
			}else if(to.equalsIgnoreCase("centimetro")) {
				total = value * 100;
			}else {
				total = value / 1000;
			}
		}
		if(from.equalsIgnoreCase("kilometro")) {
			if(to.equalsIgnoreCase("milimetro")) {
				total = value * 1000000;
			}else if(to.equalsIgnoreCase("centimetro")) {
				total = value * 100000;
			}else {
				total = value * 1000;
			}
		}
		String valueFormat = format.format(value);
		String totalFormat = format.format(total);
		return String.format("%s %s equivale a %s %s", valueFormat, from, totalFormat, to);
	}
}