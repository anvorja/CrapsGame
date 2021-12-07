package juegoCraps;

/**
 * ModelCraps applies the Crap rules:
 * estado 1 = Natural winner
 * estado 2 = Craps loser
 * estado 3 = Establish point
 * estado 4 = Natural winner
 * estado 5 = Loser point
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.1 date: 07/12/2021
 */
public class ModelCraps {
    private Dado dado1, dado2;

    private int tiro, punto, estado, bandera;
    private String[] estadoToString;
    private int[] caras;

    /**
     * Class Constructor
     */
    public ModelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
        estadoToString = new String[2];
        bandera = 0;
    }

    /**
     * Establish the tiro value according to each dice
     */
    public void calcularTiro(){
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0] + caras[1];
    }

    /**
     * Establish game state according to Crap rules:
     * estado 1 = Natural winner
     * estado 2 = Craps loser
     * estado 3 = Establish point
     */
    public void determinarJuego(){
        if(bandera == 0){
            //jugador gana (Natural)
            if(tiro == 7 || tiro == 11){
                estado = 1;
            }else{
                //jugador pierde
                if(tiro == 3 || tiro == 2 || tiro == 12){
                    estado = 2;
                }else{
                    estado = 3;
                    punto = tiro;
                    bandera = 1;
                }
            }
        }else{
            //ronda punto
            rondaPunto();

        }

    }

    /**
     * Establish game state according to estado atribute value:
     * estado 4 = Natural winner
     * estado 5 = Loser point
     */
    private void rondaPunto() {
        //Gana
        if(tiro == punto){
            estado = 4;
            bandera = 0;
        }else{
            //Pierde
            if(tiro == 7){
                estado = 5;
                bandera = 0;
            }else{
                estado = 6;
            }
        }
    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message game state according to estado atribute value:
     * @return message for the View class
     */
    public String[] getEstadoToString() {

        switch (estado) {
            case (1) -> {
                estadoToString[0] = "Tiro de Salida es " + tiro;
                estadoToString[1] = "Sacaste Natural, has ganado!!!";
            }
            case (2) -> {
                estadoToString[0] = "Tiro de Salida es " + tiro;
                estadoToString[1] = "Sacaste Craps, has perdido!!!";
            }
            case (3) -> {
                estadoToString[0] = "Tiro de Salida es " + tiro +
                        "\nPunto: " + punto;
                estadoToString[1] = "Estableciste punto en " + punto + ". " +
                        "\nSigue lanzando, pero si sacas 7 antes que " + punto + " perderás.";
            }
            case (4) -> {
                estadoToString[0] = "Tiro de Salida es " + punto
                        + "\nValor del nuevo tiro es: " + tiro;
                estadoToString[1] = "Volviste a sacar " + punto + ", has ganado!!!";
            }
            case (5) -> {
                estadoToString[0] = "Tiro de Salida es " + punto + " \nPunto: " + punto +
                        "\nValor del nuevo tiro: " + tiro;
                estadoToString[1] = "Sacaste 7 antes de " + punto + ", has perdido!!!";
            }
            case (6) -> {
                estadoToString[0] = "Tiro de Salida es " + punto + " \nPunto: " + punto +
                        "\nValor del nuevo tiro: " + tiro;
                estadoToString[1] = "Estás en \"Punto\" y debes seguir lanzando, \npero si sacas 7 antes que " + punto + " perderás.";
            }
        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}
