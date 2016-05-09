/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteMain;

import JPGCompare.utils.Diferenciator;
import JPGCompare.utils.Escenario;
import JPGCompare.utils.compareResult;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import java.io.IOException;

/**
 *
 * @author user1
 */
public class AgenteSensor extends Agent {

    public int julio;
    public Diferenciator diff = new Diferenciator();

    @Override
    public void setup() {
        System.out.println("Sensores activos");

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {

                ACLMessage msg = receive();
                if (msg != null) {
                    Escenario retorno = null;
                    /*try {
                        Process p;
                        p = java.lang.Runtime.getRuntime().exec("wget 192.168.2.100:8080/photoaf.jpg -O shot.jpg");
                        p.waitFor();
                        compareResult resultado = diff.getMasProbableTurbo("shot.jpg");
                        retorno = resultado.getEscenario();
                    } catch (IOException | InterruptedException | NullPointerException ex) {
                        System.out.println(ex.getMessage());
                    }*/
                    switch (julio++) {
                     case 0:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 1:
                     retorno = Escenario.TOPE;
                     break;
                     case 2:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 3:
                     retorno = Escenario.TOPE;
                     break;
                     case 4:
                     retorno = Escenario.TOPE;
                     break;
                     case 5:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 6:
                     retorno = Escenario.TOPE;
                     break;
                     case 7:
                     retorno = Escenario.TOPE;
                     break;
                     case 8:
                     retorno = Escenario.TOPE;
                     break;
                     case 9:
                     retorno = Escenario.TOPE;
                     break;
                     case 10:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 11:
                     retorno = Escenario.TOPE;
                     break;
                     case 12:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 13:
                     retorno = Escenario.TOPE;
                     break;
                     case 14:
                     retorno = Escenario.TOPE;
                     break;
                     case 15:
                     retorno = Escenario.TOPE;
                     break;
                     case 16:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 17:
                     retorno = Escenario.TOPE;
                     break;
                     case 18:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 19:
                     retorno = Escenario.TOPE;
                     break;
                     case 20:
                     retorno = Escenario.TOPE;
                     break;
                     case 21:
                     retorno = Escenario.TOPE;
                     break;
                     case 22:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 23:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 24:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 25:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 26:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 27:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 28:
                     retorno = Escenario.TOPE;
                     break;
                     case 29:
                     retorno = Escenario.TOPE;
                     julio = 33;
                     break;
                     case 30:
                     retorno = Escenario.TOPE;
                     break;
                     case 31:
                     retorno = Escenario.TOPE;
                     break;
                     case 32:
                     retorno = Escenario.TOPE;
                     break;
                     case 33:
                     retorno = Escenario.TOPE;
                     break;
                     case 34:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 35:
                     retorno = Escenario.TOPE;
                     break;
                     case 36:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 37:
                     retorno = Escenario.TOPE;
                     break;
                     case 38:
                     retorno = Escenario.TOPE;
                     break;
                     case 39:
                     retorno = Escenario.TOPE;
                     break;
                     case 40:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 41:
                     retorno = Escenario.TOPE;
                     break;
                     case 42:
                     retorno = Escenario.TOPE;
                     break;
                     case 43:
                     retorno = Escenario.PERDIDO;
                     break;
                     case 44:
                     retorno = Escenario.TOPE;
                     break;
                     case 45:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 46:
                     retorno = Escenario.TOPE;
                     break;
                     case 47:
                     retorno = Escenario.TOPE;
                     break;
                     case 48:
                     retorno = Escenario.TOPE;
                     break;
                     case 49:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 50:
                     retorno = Escenario.TOPE;
                     break;
                     case 51:
                     retorno = Escenario.TOPE;
                     break;
                     case 52:
                     retorno = Escenario.TOPE;
                     break;
                     case 53:
                     retorno = Escenario.TOPE;
                     break;
                     case 54:
                     retorno = Escenario.TOPE;
                     break;
                     case 55:
                     retorno = Escenario.TOPE;
                     break;
                     case 56:
                     retorno = Escenario.ESPACIO;
                     break;
                     case 57:
                     retorno = Escenario.TOPE;
                     break;
                     case 58:
                     retorno = Escenario.SALIDA;
                     break;
                     case 59:
                     retorno = Escenario.TOPE;
                     break;
                     }

                    ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                    msg2.addReceiver(new AID("main", AID.ISLOCALNAME));
                    msg2.setContent(retorno + "");
                    send(msg2);
                } else {
                    block();
                }
            }
        });
    }

    @Override
    public void takeDown() {
        System.out.println("Sensores apagados");
    }
}
