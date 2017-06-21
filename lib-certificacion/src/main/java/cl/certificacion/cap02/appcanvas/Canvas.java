package cl.certificacion.cap02.appcanvas;
import cl.certificacion.cap02.graphicshape.Circle;

class Canvas {
	void getArea() {
		Circle circle = new Circle();
		circle.area(); // call to public method area(), outside package
		}
}