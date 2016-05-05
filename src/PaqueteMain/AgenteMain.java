/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteMain;

import JPGCompare.utils.*;
import MazeMatriz.*;
import static MazeMatriz.Direcciones.ABAJO;
import static MazeMatriz.Direcciones.ARRIBA;
import static MazeMatriz.Direcciones.DERECHA;
import static MazeMatriz.Direcciones.IZQUIERDA;
import MazeTree.*;
import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.util.LinkedList;

/**
 *
 * @author julio
 */
public class AgenteMain extends Agent {

    public int j = 0;
    public Matriz matriz = new Matriz();
    public Hoja arbol = new Hoja(Direcciones.ABAJO);
    LinkedList<Hoja> cola = new LinkedList<>();
    String[] pasos = null;
    Boolean regresando = false;
    Boolean marcando = false;
    Boolean saliendo = false;
    @Override
    public void setup() {
        System.out.println("Main iniciado");
        
        ACLMessage msginicio = blockingReceive();
        if (msginicio != null && "iniciar".equals(msginicio.getContent())) {
            addBehaviour(new TickerBehaviour(this, 2000) {
                @Override
                protected void onTick() {
                    Direcciones hacia = null;
                    Direcciones orientacion = matriz.getOrientacion();
                    String paraenviar2 = "";
                    Boolean parar = false;
                    
                    System.out.println('\n'+"empiezaTick...");
                    if (!explorar(orientacion)) {
                        System.out.println("voy a explorar");
                        switch (orientacion) {
                            case ARRIBA:
                                investigarCuadro(Direcciones.IZQUIERDA, Direcciones.ARRIBA, Direcciones.DERECHA);
                                break;
                            case IZQUIERDA:
                                investigarCuadro(Direcciones.ABAJO, Direcciones.IZQUIERDA, Direcciones.ARRIBA);
                                break;
                            case DERECHA:
                                investigarCuadro(Direcciones.ARRIBA, Direcciones.DERECHA, Direcciones.ABAJO);
                                break;
                            case ABAJO:
                                investigarCuadro(Direcciones.DERECHA, Direcciones.ABAJO, Direcciones.IZQUIERDA);
                                break;
                        }
                    }
                    //if encontre al robot
                    //saliendo = true
                    //else
                    String paraenviar = getDatosMatriz();
                    if(saliendo){
                        if (arbol.getpadre() == null) {
                            //if (arbol.hayHijos()) {
                                //arbol = arbol.getSiguiente();
                                hacia = arbol.getProcedencia();
                                System.out.println("voy a salir por " + hacia);
                                matriz.Avanzar(hacia);
                                paraenviar2 = "" + hacia;
                            //} else {
                                //error
                            //}
                        } else {
                            switch (arbol.getProcedencia()) {
                                case ARRIBA:
                                    hacia = Direcciones.ABAJO;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case IZQUIERDA:
                                    hacia = Direcciones.DERECHA;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case DERECHA:
                                    hacia = Direcciones.IZQUIERDA;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case ABAJO:
                                    if (arbol.getpadre() != null) {
                                        hacia = Direcciones.ARRIBA;
                                        System.out.println("me regreso por" + hacia);
                                        matriz.Avanzar(hacia);
                                        paraenviar2 = "" + hacia;
                                    }
                                    break;
                            }
                            //avisarle a aquellos
                            arbol=arbol.getpadre();
                        }
                    }else if (arbol.hayHijos() && marcando == false && regresando == false) {
                        arbol = arbol.getSiguiente();
                        hacia = arbol.getProcedencia();
                        System.out.println("me voy a ir por el hijo " + hacia);
                        matriz.Avanzar(hacia);
                        paraenviar2 = "" + hacia;
                    } else if (arbol.hayHijos() && marcando == true) {
                        if (arbol.getpadre() == null) {
                            arbol = arbol.getSiguiente();
                            hacia = arbol.getProcedencia();
                            System.out.println("me voy a ir por el hijo " + hacia);
                            matriz.Avanzar(hacia);
                            paraenviar2 = "" + hacia;
                            marcando = false;
                        } else {
                            switch (arbol.getProcedencia()) {
                                case ARRIBA:
                                    hacia = Direcciones.ABAJO;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case IZQUIERDA:
                                    hacia = Direcciones.DERECHA;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case DERECHA:
                                    hacia = Direcciones.IZQUIERDA;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case ABAJO:
                                    if (arbol.getpadre() != null) {
                                        hacia = Direcciones.ARRIBA;
                                        System.out.println("me regreso por" + hacia);
                                        matriz.Avanzar(hacia);
                                        paraenviar2 = "" + hacia;
                                    }
                                    break;
                            }
                            arbol = arbol.getpadre();
                            marcando = false;
                            regresando = true;
                        }
                    } else if (regresando == true) {
                        if (arbol.getpadre() == null) {
                            regresando = false;
                            if (arbol.hayHijos()) {
                                arbol = arbol.getSiguiente();
                                hacia = arbol.getProcedencia();
                                System.out.println("me voy a ir por el hijo " + hacia);
                                matriz.Avanzar(hacia);
                                paraenviar2 = "" + hacia;
                            } else {
                                //error
                            }
                        } else {
                            switch (arbol.getProcedencia()) {
                                case ARRIBA:
                                    hacia = Direcciones.ABAJO;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case IZQUIERDA:
                                    hacia = Direcciones.DERECHA;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case DERECHA:
                                    hacia = Direcciones.IZQUIERDA;
                                    System.out.println("me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                    break;
                                case ABAJO:
                                    if (arbol.getpadre() != null) {
                                        hacia = Direcciones.ARRIBA;
                                        System.out.println("me regreso por" + hacia);
                                        matriz.Avanzar(hacia);
                                        paraenviar2 = "" + hacia;
                                    }
                                    break;
                            }
                            arbol=arbol.getpadre();
                        }
                    } else if (regresando == false) {
                        marcando = true;
                        arbol.setVisitado(true);
                        switch (arbol.getProcedencia()) {
                            case ARRIBA:
                                hacia = Direcciones.ABAJO;
                                System.out.println("no hay hijos, me regreso por" + hacia);
                                matriz.Avanzar(hacia);
                                paraenviar2 = "" + hacia;
                                break;
                            case IZQUIERDA:
                                hacia = Direcciones.DERECHA;
                                System.out.println("no hay hijos, me regreso por" + hacia);
                                matriz.Avanzar(hacia);
                                paraenviar2 = "" + hacia;
                                break;
                            case DERECHA:
                                hacia = Direcciones.IZQUIERDA;
                                System.out.println("no hay hijos, me regreso por" + hacia);
                                matriz.Avanzar(hacia);
                                paraenviar2 = "" + hacia;
                                break;
                            case ABAJO:
                                if (arbol.getpadre() != null) {
                                    hacia = Direcciones.ARRIBA;
                                    System.out.println("no hay hijos, me regreso por" + hacia);
                                    matriz.Avanzar(hacia);
                                    paraenviar2 = "" + hacia;
                                }
                                break;
                        }
                        if (arbol.getpadre() == null) {
                            System.out.println("era la raiz, No encontre  al otro robot, fin");
                            parar = true;
                            stop();
                        } else {
                            arbol = arbol.getpadre();
                        }
                    } else {
                        //error
                    }
                    if (!parar) {
                        enviarMsg("receptor", paraenviar + "," + paraenviar2);
                        avanzar(hacia, orientacion);
                    } else {
                        System.out.println("mapeo listo");
                    }
                }

                @Override
                public int onEnd() {
                    ACLMessage msgresolve = blockingReceive();
                    if (msgresolve != null && "resolver".equals(msgresolve.getContent())) {
                        addBehaviour(new TickerBehaviour(this.myAgent, 2000) {
                            @Override
                            public void onStart() {
                                cola.add(arbol);
                                while (!cola.isEmpty()) {
                                    String salida = resolver(cola.poll());
                                    if (!"".equals(salida)) {
                                        pasos = salida.split("->");
                                        break;
                                    }
                                }
                                if (pasos == null) {
                                    System.out.println("no hay salida");
                                    doDelete();
                                }
                            }

                            @Override
                            protected void onTick() {
                                Direcciones orientacion = matriz.getOrientacion();
                                System.out.println(orientacion);
                                if (j < pasos.length) {
                                    System.out.println(pasos[j]);
                                    avanzar(Direcciones.valueOf(pasos[j]), orientacion);
                                    enviarMsg("receptor", pasos[j]);
                                    matriz.Avanzar(Direcciones.valueOf(pasos[j++]));
                                } else {
                                    System.out.println("salida");
                                    if (matriz.isSalida(Direcciones.ARRIBA)) {
                                        avanzar(Direcciones.ARRIBA, orientacion);
                                    } else if (matriz.isSalida(Direcciones.IZQUIERDA)) {
                                        avanzar(Direcciones.IZQUIERDA, orientacion);
                                    } else if (matriz.isSalida(Direcciones.DERECHA)) {
                                        avanzar(Direcciones.DERECHA, orientacion);
                                    } else if (matriz.isSalida(Direcciones.ABAJO)) {
                                        avanzar(Direcciones.ABAJO, orientacion);
                                    }
                                    stop();
                                }
                            }

                            @Override
                            public int onEnd() {
                                doDelete();
                                return 0;
                            }
                        });
                    }
                    return 0;
                }
            });
        } else if (msginicio != null && "inutil".equals(msginicio.getContent())) {
            //escuchar
            //moverse
            //si llego a salida celebrar
            //sino regresar a escuchar
        }
    }

    @Override
    public void takeDown() {
        System.out.println("Main terminado");
    }

    public String resolver(Hoja nodo) {
        String retorno = "";
        if (nodo.isSalida()) {
            System.out.println("encontre la salida");
            while (nodo.getpadre() != null) {
                retorno = nodo.getProcedencia() + "->" + retorno;
                nodo = nodo.getpadre();
            }
        } else {
            for (int k = 0; k < nodo.getNumeroHijos(); k++) {
                cola.add(nodo.getHijo(k));
            }
        }
        return retorno;
    }

    public void enviarMsg(String receptor, String mensaje) {
        System.out.println("Enviare un mensaje... " + mensaje);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(receptor, AID.ISLOCALNAME));
        msg.setContent(mensaje);
        send(msg);
    }

    public void mapear(Direcciones dire) {
        ACLMessage msg = new ACLMessage();
        msg.addReceiver(new AID("sensor", AID.ISLOCALNAME));
        msg.setContent("que es");
        send(msg);

        ACLMessage msg2 = blockingReceive();
        if (msg2 != null) {
            Escenario esc = Escenario.valueOf(msg2.getContent());

            System.out.println("encontre un" + esc);
            switch (esc) {
                case ESPACIO:
                    matriz.insertar(dire, TiposCamino.CAMINO);
                    arbol.insertar(new Hoja(dire));
                    break;
                case TOPE:
                    matriz.insertar(dire, TiposCamino.PARED);
                    break;
                case SALIDA:
                    matriz.insertar(dire, TiposCamino.SALIDA);
                    arbol.setSalida(true);
                    break;
                default:
                    break;
            }
        }


    }

    public Boolean explorar(Direcciones dir) {
        System.out.println("la orientacion es " + dir);
        Boolean retorno = true;
        switch (dir) {
            case ARRIBA:
                retorno = (matriz.hayNodo(Direcciones.ARRIBA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.IZQUIERDA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.DERECHA)) && retorno;
                break;
            case IZQUIERDA:
                retorno = (matriz.hayNodo(Direcciones.ARRIBA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.IZQUIERDA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.ABAJO)) && retorno;
                break;
            case DERECHA:
                retorno = (matriz.hayNodo(Direcciones.ARRIBA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.DERECHA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.ABAJO)) && retorno;
                break;
            case ABAJO:
                retorno = (matriz.hayNodo(Direcciones.IZQUIERDA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.DERECHA)) && retorno;
                retorno = (matriz.hayNodo(Direcciones.ABAJO)) && retorno;
                break;
        }
        return retorno;
    }

    public String getDatosMatriz() {
        String paraenviar = "";
        if (matriz.getTipo(Direcciones.IZQUIERDA) == TiposCamino.PARED) {
            paraenviar += "1";
        } else if (matriz.getTipo(Direcciones.IZQUIERDA) == TiposCamino.SALIDA) {
            paraenviar += "2";
        } else if (matriz.getTipo(Direcciones.IZQUIERDA) == TiposCamino.CAMINO) {
            paraenviar += "0";
        }

        if (matriz.getTipo(Direcciones.ARRIBA) == TiposCamino.PARED) {
            paraenviar += ",1";
        } else if (matriz.getTipo(Direcciones.ARRIBA) == TiposCamino.SALIDA) {
            paraenviar += ",2";
        } else if (matriz.getTipo(Direcciones.ARRIBA) == TiposCamino.CAMINO) {
            paraenviar += ",0";
        }
        if (matriz.getTipo(Direcciones.DERECHA) == TiposCamino.PARED) {
            paraenviar += ",1";
        } else if (matriz.getTipo(Direcciones.DERECHA) == TiposCamino.SALIDA) {
            paraenviar += ",2";
        } else if (matriz.getTipo(Direcciones.DERECHA) == TiposCamino.CAMINO) {
            paraenviar += ",0";
        }
        if (matriz.getTipo(Direcciones.ABAJO) == TiposCamino.PARED) {
            paraenviar += ",1";
        } else if (matriz.getTipo(Direcciones.ABAJO) == TiposCamino.SALIDA) {
            paraenviar += ",2";
        } else if (matriz.getTipo(Direcciones.ABAJO) == TiposCamino.CAMINO) {
            paraenviar += ",0";
        }
        return paraenviar;
    }

    public void investigarCuadro(Direcciones dir1, Direcciones dir2, Direcciones dir3) {
        Moverse("izquierda");
        mapear(dir1);
        Moverse("derecha");
        mapear(dir2);
        Moverse("derecha");
        mapear(dir3);
        Moverse("izquierda");
    }

    public void Moverse(String direccion) {
        enviarMsg("motor", direccion);
        ACLMessage msg = blockingReceive(3000);
        if (msg == null) {
            System.out.println("sin respuesta de los motores");
            doDelete();
        }
    }

    public void avanzar(Direcciones hacia, Direcciones orientacion) {
        if (hacia != null) {
            switch (orientacion) {
                case ARRIBA:
                    switch (hacia) {
                        case ARRIBA:
                            Moverse("adelante");
                            break;
                        case IZQUIERDA:
                            Moverse("izquierda");
                            Moverse("adelante");
                            break;
                        case DERECHA:
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                        case ABAJO:
                            Moverse("derecha");
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                    }
                    break;
                case IZQUIERDA:
                    switch (hacia) {
                        case ARRIBA:
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                        case IZQUIERDA:
                            Moverse("adelante");
                            break;
                        case DERECHA:
                            Moverse("derecha");
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                        case ABAJO:
                            Moverse("izquierda");
                            Moverse("adelante");
                            break;
                    }
                    break;
                case DERECHA:
                    switch (hacia) {
                        case ARRIBA:
                            Moverse("izquierda");
                            Moverse("adelante");
                            break;
                        case IZQUIERDA:
                            Moverse("derecha");
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                        case DERECHA:
                            Moverse("adelante");
                            break;
                        case ABAJO:
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                    }
                    break;
                case ABAJO:
                    switch (hacia) {
                        case ARRIBA:
                            Moverse("derecha");
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                        case IZQUIERDA:
                            Moverse("derecha");
                            Moverse("adelante");
                            break;
                        case DERECHA:
                            Moverse("izquierda");
                            Moverse("adelante");
                            break;
                        case ABAJO:
                            Moverse("adelante");
                            break;
                    }
                    break;
            }
        }
    }
}