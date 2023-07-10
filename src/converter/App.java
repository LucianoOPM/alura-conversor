package converter;
import javax.swing.JOptionPane;

/**
 * La aplicación permite la conversión de monedas, temperatura y longitud
 */
public class App {
	/**
	 * Método principal que ejecuta un menú para poder seleccionar la conversión que quiere realizar.s
	 */
	public static void main(String[] args) {
		try {		
			Object[] operationSelector = {"Conversor de Moneda", "Conversor de Temperatura", "Conversor de Logitud", "Almacenamiento digital"};
			
			Object initialOperation = JOptionPane.showInputDialog(null,
		             "Qué operación desea realizar?", "Seleccione la operación deseada.",
		             JOptionPane.PLAIN_MESSAGE, null,
		             operationSelector, operationSelector[0]);
			
			String operationResult = "";
			
			if(initialOperation.toString().contains(operationSelector[0].toString())) {
				operationResult = CurrencyConverter.startConverter();
			}
			if(initialOperation.toString().contains(operationSelector[1].toString())) {
				operationResult = TemperatureConverter.startConverter();
			}
			if(initialOperation.toString().contains(operationSelector[2].toString())) {
				operationResult = LengthConverter.startConverter();
			}
			if(initialOperation.toString().contains(operationSelector[3].toString())) {
				operationResult = StorageConverter.startConverter();
			}
			
			JOptionPane.showMessageDialog(null, operationResult);
			int nextOperation = JOptionPane.showConfirmDialog(null, "Desea continuar con alguna otra operacion?");
			
			if(nextOperation == 1 || nextOperation == 2) {
				JOptionPane.showMessageDialog(null, "Se terminó la operación");
			}else {
				main(null);
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un valor", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un valor numerico", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
