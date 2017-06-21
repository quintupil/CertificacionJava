package cl.certificacion.cap02.numero;

public class TestNumero {
	
	// Tomo una serie de numero y los suma
	public static double sum(Number []nums) {
		double sum = 0.0;
		for(Number num : nums) {
			sum += num.doubleValue();
		}
		return sum;
	}
	public static void main(String []s) {
		// Crea el Array de Numeros
		Number []nums = new Number[4];
		// assign derived class objects
		nums[0] = new Byte((byte)10);
		nums[1] = new Integer(10);
		nums[2] = new Float(10.0f);
		nums[3] = new Double(10.0f);
		// Pasa el arreglo de numero a suma e imprime el resultado
		System.out.println("The sum of numbers is: " + sum(nums));
	}
}
