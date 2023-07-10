package converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class CurrencyConverter {
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
			};
			
			ArrayList<String> currencies = new ArrayList<>();
			currencies.add("MXN");
			currencies.add("USD");
			currencies.add("EUR");
			currencies.add("JPY");
			currencies.add("KRW");
			currencies.add("GBP");
			
			Object[] currencyValues = currencies.toArray(); 
			
			String firstCurrency = (String) JOptionPane.showInputDialog(
					null,
					"Ingrese de que unidad quiere convertir su dinero",
					"Seleccionar opcion",
					JOptionPane.PLAIN_MESSAGE,
					null,
					currencyValues,
					currencyValues[0]);
			
			for (int i = 0; i < currencies.size(); i++) {
				if(firstCurrency.contains(currencies.get(i))){
					currencies.remove(i);
				}
			};
			
			currencyValues = currencies.toArray();
			
			String secondCurrency = (String) JOptionPane.showInputDialog(
					null,
					"Ingrese a que unidad desea convertir el monto",
					"Seleccione una opción",
					JOptionPane.PLAIN_MESSAGE,
					null,
					currencyValues,
					currencyValues[0]);

			return converter(value, firstCurrency, secondCurrency);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("No se pudo validar el tipo de dato, se esperaba un valor numerico.");
		} catch (NullPointerException e) {
			throw new NullPointerException("Ingrese un valor válido.");
		}
	}
	
	/**
	 * Método que realiza la conversión de valores del valor escogido
	 * @param Valor numerico a convertir a la siguiente moneda
	 * @param String del que es el valor base a convertir
	 * @param String que es el valor al cual se va a convertir
	 * @return {@link String} Retorna un mensaje con el valor ya transformado a la moneda escogida
	 */
	public static String converter(double value, String fromCurrency, String toCurrency) {
		
		DecimalFormat doubleFormat = new DecimalFormat("#.#####");
		
		Map<String, Double> exchangeRate = new HashMap<>();
		//MXN
		exchangeRate.put("MXN USD", 0.0583);
		exchangeRate.put("MXN EUR", 0.0532);
		exchangeRate.put("MXN KRW", 75.7377);
		exchangeRate.put("MXN JPY", 8.2862);
		exchangeRate.put("MXN GBP", 0.0454);
		//USD
		exchangeRate.put("USD MXN", 17.1475);
		exchangeRate.put("USD EUR", 0.9117);
		exchangeRate.put("USD KRW", 1298.37);
		exchangeRate.put("USD JPY", 142.09);
		exchangeRate.put("USD GBP", 0.779);
		//EUR
		exchangeRate.put("EUR MXN", 18.8017);
		exchangeRate.put("EUR USD", 1.0969);
		exchangeRate.put("EUR KRW", 1424.18);
		exchangeRate.put("EUR JPY", 155.86);
		exchangeRate.put("EUR GBP", 0.8544);
		//KRW
		exchangeRate.put("KRW MXN", 0.0132);
		exchangeRate.put("KRW USD", 0.0008);
		exchangeRate.put("KRW EUR", 0.0007);
		exchangeRate.put("KRW JPY", 0.1094);
		exchangeRate.put("KRW GBP", 0.0006);
		//JPY
		exchangeRate.put("JPY MXN", 0.1207);
		exchangeRate.put("JPY USD", 0.007);
		exchangeRate.put("JPY EUR", 0.0064);
		exchangeRate.put("JPY KRW", 9.1403);
		exchangeRate.put("JPY GBP", 0.0055);
		//GBP
		exchangeRate.put("GBP MXN", 22.0138);
		exchangeRate.put("GBP USD", 1.2838);
		exchangeRate.put("GBP EUR", 1.1704);
		exchangeRate.put("GBP JPY", 182.41);
		exchangeRate.put("GBP KRW", 1667.2727);
		
		String concatedCurrencies = fromCurrency.concat(" " + toCurrency);
		double total = value * exchangeRate.get(concatedCurrencies);
		String totalFormat = doubleFormat.format(total);
		
		return String.format("%s %s equivalen a %s %s", value, fromCurrency, totalFormat, toCurrency);
	}	
}
