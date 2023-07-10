package converter;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class CurrencyConverter {
	/**
	 * 
	 * @param value <int> Valor numerico que indica cuanto quiere convertir el usuario
	 * @param exchangeValue <double> Valor numerico que es la tasa de cambio en base del pais y hacía que pais quiere convertir la moneda
	 * @param exchangeCurrency <String> String que que recibe De que pais quiere convertir y hacía que pais quiere convertir
	 * @return <String> String con el mensaje establecido y la conversión ya realizada.
	 */
	public static String converter(double value, double exchangeValue, String exchangeCurrency) {
		String[] exchange = exchangeCurrency.split(" ");
		String from = exchange[0];
		String to = exchange[2];
		
		return String.format("$" + value + "%s equivalen a: $" + value * exchangeValue + "%s \nAl día 07/07/2023", from, to);
	}
	
	
	/**
	 * El método "startConverter" no recibe parametros ya que se ejecuta atraves de el selector principal en el método main
	 * @return Retorna un string con el valor de la conversión de la moneda ya establecida
	 * @throws NumberFormatException si el valor otorgado es menor a cero o no recibe valores numericos.
	 */
	public static String startConverter() {
		try {
			double value = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la cantidad que desea convertir", "Ingrese los valores", JOptionPane.PLAIN_MESSAGE));
			if(value <= 0) {
				throw new NumberFormatException("No se aceptan valores negativos o menores a 0");
			}
			Map<String, Double> possibleValues = new HashMap<String, Double>();
			possibleValues.put("MXN a USD", 0.0583);
			possibleValues.put("MXN a EUR", 0.0532);
			possibleValues.put("USD a MXN", 17.1475);
			possibleValues.put("USD a EUR", 0.9117);
			possibleValues.put("EUR a MXN", 18.8017);
			possibleValues.put("EUR a USD", 1.0969);
			
			String selectedValue = (String) JOptionPane.showInputDialog(null,
					"¿A que moneda desea convertirlo?", "Seleccionar opción",
					JOptionPane.PLAIN_MESSAGE, null,
					possibleValues.keySet().toArray(), possibleValues.keySet().toArray()[0]);
			
			String mensaje = converter(value, possibleValues.get(selectedValue), selectedValue);

			return mensaje;
		} catch (NumberFormatException e) {
			throw new NumberFormatException("No se pudo validar el tipo de dato, se esperaba un valor numerico.");
		} catch (NullPointerException e) {
			throw new NullPointerException("Ingrese un valor válido.");
		}
	}
	
}
