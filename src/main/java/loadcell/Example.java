package loadcell;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.GpioUtil;

public class Example {
	final GpioPinDigitalInput pinHXDAT;
	final GpioPinDigitalOutput pinHXCLK;
	final HX711 hx;

	public Example() {
		GpioUtil.enableNonPrivilegedAccess();
		GpioController gpio = GpioFactory.getInstance();
		pinHXDAT = gpio.provisionDigitalInputPin(RaspiPin.GPIO_21, "HX_DAT", PinPullResistance.OFF);
		pinHXCLK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "HX_CLK", PinState.LOW);

		hx = new HX711(pinHXDAT, pinHXCLK, 128);

		while (true) {
			hx.read();
			System.out.println(hx.value + "_valor");
			System.out.println(hx.weight + "_peso");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}