package cl.certificacion.appcanvas;
import cl.certificacion.graphicshape.Circle;

class Canvas {
	void getArea() {
		Circle circle = new Circle();
		circle.area(); // call to public method area(), outside package
		}
}