package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is designed in order to view class ModelCraps
 * @author Carlos Andrés Borja - Univalle
 * @version v.1.0.0 date:07/12/2021
 */
public class GUI extends JFrame {

    //creamos constante de clase
    private static final String MENSAJE_INICIO = "Bienvenido a Craps \n"+
            "Oprime el botón lanzar para iniciar el juego." +
            "\nSi tu tiro de salida es 7 u 11 ganas con Natural." +
            "\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps." +
            "\nSi sacas cualquier otro valor establecerás el \"Punto\"." +
            "\nEstado en \"Punto\" podrás seguir lanzando los dados," +
            "\npero ahora ganarás si sacas nuevamente el valor"+
            "\ndel \"Punto\" sin que previamente hayas sacado 7.";


    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, penelresultados;
    private ImageIcon imageDado;
    private JTextArea resultados;
    private Escucha escucha;
    private ModelCraps modelCraps;



    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Game Craps");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();

        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        imageDado = new ImageIcon(getClass().getResource("/recursos/dado.png"));
        //etiquetas de cada dado
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER);


        resultados = new JTextArea(7,31);
        resultados.setText(MENSAJE_INICIO);
        resultados.setBorder(BorderFactory.createTitledBorder("Instruciones"));
        JScrollPane scroll = new JScrollPane(resultados);
        this.add(scroll,BorderLayout.EAST);


    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/recursos/"+caras[0]+".png"));
            dado1.setIcon(imageDado);

            imageDado = new ImageIcon(getClass().getResource("/recursos/"+caras[1]+".png"));
            dado2.setIcon(imageDado);

            modelCraps.determinarJuego();
            resultados.setText(modelCraps.getEstadoToString());





        }
    }
}
