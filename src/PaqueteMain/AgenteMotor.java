/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteMain;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import jade.core.AID;

/**
 *
 * @author user1
 */
public class AgenteMotor extends Agent {

    int tiempoadelante = 600;
    int tiempogiro = 375;

    @Override
    public void setup() {
        /*final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput izq1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "IZQ1", PinState.LOW);
        final GpioPinDigitalOutput izq2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "IZQ2", PinState.LOW);
        final GpioPinDigitalOutput der1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "DER1", PinState.LOW);
        final GpioPinDigitalOutput der2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "DER2", PinState.LOW);
        izq1.setShutdownOptions(true, PinState.LOW);
        izq2.setShutdownOptions(true, PinState.LOW);
        der1.setShutdownOptions(true, PinState.LOW);
        der2.setShutdownOptions(true, PinState.LOW);*/
        System.out.println("Motores activos");
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    switch (msg.getContent()) {
                        case "adelante":
                            /*izq2.low();
                            der2.low();
                            izq1.pulse(tiempoadelante);
                            der1.pulse(tiempoadelante, true);
                            */break;
                        case "izquierda":
                            /*izq1.low();
                            der2.low();
                            izq2.pulse(tiempogiro);
                            der1.pulse(tiempogiro, true);
                            */break;
                        case "derecha":
                            /*izq2.low();
                            der1.low();
                            izq1.pulse(tiempogiro);
                            der2.pulse(tiempogiro, true);
                            */break;
                    }
                    doWait(300);
                    ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                    msg2.addReceiver(new AID("main", AID.ISLOCALNAME));
                    msg2.setContent("ya");
                    send(msg2);
                } else {
                    block();
                }
            }
        });
    }

    @Override
    public void takeDown() {
        System.out.println("Motores apagados");
    }
}
