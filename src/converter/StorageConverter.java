package converter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Clase que hace la validación y el retorno de los mensajes respecto a las converciones de datos.
 */
public class StorageConverter {
	/**
	 * Método que permite la inserción de los datos para la conversión
	 * @return {@link String} Retorna un mensaje respecto a las conversiones elegidas
	 * @throws {@link NullPointerException} | {@link NumberFormatException} Puede arrojar errores si no se ingresan valores o si se ingresan valores no numericos.
	 */
	public static String startConverter() {
		try {
			ArrayList<String> storageValues = new ArrayList<>();
			storageValues.add("Kilobyte");
			storageValues.add("Megabyte");
			storageValues.add("Gigabyte");
			storageValues.add("Terabyte");
			
			Object[] storage = storageValues.toArray();
			
			String firstValue = (String) JOptionPane.showInputDialog(
					null,
					"Seleccione una unidad",
					"Seleccione un valor",
					JOptionPane.PLAIN_MESSAGE,
					null,
					storage, 
					storage[0]);
			
			for (int i = 0; i < storageValues.size(); i++) {
				if(firstValue.contains(storageValues.get(i))) {
					storageValues.remove(i);
				}
			}
			
			storage = storageValues.toArray();
			
			String secondValue = (String) JOptionPane.showInputDialog(
					null,
					"Ingrese la unidad al que desea convertir el dato", 
					"Seleccione una unidad",
					JOptionPane.INFORMATION_MESSAGE,
					null,
					storage, 
					storage[0]);
			
			double value = Double.parseDouble(JOptionPane.showInputDialog(
					null,
					"Ingrese el valor que desea convertir",
					"Ingrese un valor", 
					JOptionPane.PLAIN_MESSAGE));
			
			return convert(firstValue, secondValue, value);
		} catch (NullPointerException e) {
			throw new NullPointerException();
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
	}
	
	/**
	 * Método que realiza las operaciones de conversión de datos y retorna un mensaje con los datos ya convertidos.
	 * @param <String> El tipo de dato del que se reciben los valores numericos
	 * @param <String> El tipo de dato al que se van a convertir los valores numericos.
	 * @param <double> Valor numerico que se va a convertir al tipo de dato escogido
	 * @return {@link String} Retorna un mensaje para mostrar por consola, entregando el resultado.
	 */
	private static String convert(String from, String to, double value) {
			
		double total = 0;
		DecimalFormat doubleFormat = new DecimalFormat("#.######");
				
		if(from.equalsIgnoreCase("kilobyte")) {
			if(to.equalsIgnoreCase("megabyte")) {
				total = value / 1000;
			}else if(to.equalsIgnoreCase("gigabyte")) {
				total = value / 1000000;
			}else {
				total = value / 1.0000e+9;
			}
		}
		if(from.equalsIgnoreCase("megabyte")) {
			if(to.equalsIgnoreCase("kilobyte")) {
				total = value * 1000;
			}else if(to.equalsIgnoreCase("gigabyte")) {
				total = value / 1000;
			}else {
				total = value / 1000000;
			}
		}
		if(from.equalsIgnoreCase("gigabyte")) {
			if(to.equalsIgnoreCase("kilobyte")) {
				total = value * 1000000; 
			}else if(to.equalsIgnoreCase("megabyte")) {
				total = value * 1000;
			}else {
				total = value / 1000;
			}
		}
		if(from.equalsIgnoreCase("terabyte")) {
			if(to.equalsIgnoreCase("kilobyte")) {
				total = value * 1.0000e+9;
			}else if(to.equalsIgnoreCase("megabyte")) {
				total = value * 1000000;
			}else {
				total = value * 1000;
			}
		}
		String valueFormat = doubleFormat.format(value);
		String operationFormat = doubleFormat.format(total);
		return String.format("%s %s equivalen a %s %s", valueFormat, from, operationFormat, to);
	}
}
