package converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TemperatureConverter {
	/**
	 * Método que comienza con el convertidor de temperaturas, toma los datos del usuario y valida que los datos sean correctos para su proceso.
	 * @return Retorna un string que es el mensaje a mostrar en pantalla
	 * @throws NumberFormatException Si el tipo de dato recibido no es un numero, arroja este error.
	 * @throws NullPointerException Si los valores numericos no se ingresan correctamente o se cancela un cuadro de dialogo, arroja error 
	 */
	public static String startConverter() {
		try {
			ArrayList<String> temperatures = new ArrayList<String>(4);
			temperatures.add("Celsius");
			temperatures.add("Kelvin");
			temperatures.add("Fahrenheit");
			temperatures.add("Rankine");
			
			Object[] tempValues = temperatures.toArray();
			
			
			String selectedValue =(String) JOptionPane.showInputDialog(
					null,
					"Ingrese la unidad a convertir",
					"Ingresar temperatura", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					tempValues, 
					tempValues[0]);
			
			for (int i = 0; i < temperatures.size(); i++) {
				if(selectedValue.contains(temperatures.get(i))) {
					temperatures.remove(i);
				}
			}
			
			Object[] toAnotherValue = temperatures.toArray();
			
			String toValues = (String) JOptionPane.showInputDialog(
					null, 
					"A que unidad de medida quiere convertirla", 
					"Ingrese un valor", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					toAnotherValue, 
					toAnotherValue[0]);
			
			Double tempValue = Double.parseDouble(JOptionPane.showInputDialog(
					null, 
					"Convertir de " + selectedValue + " a " + toValues, 
					"Ingresar valores", 
					JOptionPane.PLAIN_MESSAGE));
			
			return converter(selectedValue, toValues, tempValue);	
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
	}
	/**
	 * Método que se encarga de las operaciones de converción y de generar el mensaje con el resultado final.
	 * @param Recibe el valor que quiere transformar el valor
	 * @param Recibe hacía que escala se tranformará el dato
	 * @param Recibe un valor double que es el total a convertir
	 * @return {@link String} Retorna un mensaje para mostrar en pantalla el valor ya convertido
	 */
	private static String converter (String from, String to, double value) {
		
		double total = 0;
		DecimalFormat doubleFormat = new DecimalFormat("#.######");
		
		if(from.equalsIgnoreCase("Celsius")) {
			if(to.equalsIgnoreCase("Kelvin")) {
				total = value + 273.15;
			}else if(to.equalsIgnoreCase("Fahrenheit")) {
				total = value * 1.8 + 32;
			}else {
				total = value * 1.8 + 32 + 459.67;				
			}
		}
		if(from.equalsIgnoreCase("Kelvin")) {
			if(to.equalsIgnoreCase("Celsius")) {
				total = value - 273.15;
			}else if(to.equalsIgnoreCase("Fahrenheit")) {
				total = value * 1.8 - 459.67;
			}else {
				total = value * 1.8;
			}
		}
		if(from.equalsIgnoreCase("Fahrenheit")) {
			if(to.equalsIgnoreCase("Celsius")) {
				total = (value - 32) / 1.8;
			}else if(to.equalsIgnoreCase("Kelvin")) {
				total = (value + 459.67) / 1.8;
			}else {
				total = value + 459.67;
			}
		}
		if(from.equalsIgnoreCase("Rankine")) {
			if(to.equalsIgnoreCase("Celsius")) {
				total = (value - 32 - 459.67) / 1.8;
			}else if(to.equalsIgnoreCase("Kelvin")) {
				total = value / 1.8;
			}else {
				total = value - 459.67;
			}
		}
		String valueFormat = doubleFormat.format(value);
		String operationFormat = doubleFormat.format(total);
		return String.format("%s %s equivalen a %s %s", valueFormat, from, operationFormat, to);
	}
}
