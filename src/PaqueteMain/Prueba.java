/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteMain;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author user1
 */
public class Prueba extends Agent{
    @Override
    public void setup(){
        addBehaviour(new TickerBehaviour(this,1000){

            @Override
            protected void onTick() {
                ACLMessage msg = new ACLMessage();
                msg.addReceiver(new AID("comunicador",AID.ISLOCALNAME));
                msg.setContent("hola fer");
                send(msg);
            }
            
        });
    }
}
