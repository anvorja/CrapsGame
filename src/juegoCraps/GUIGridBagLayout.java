package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {

    private static final String MENSAJE_INICIO = "Bienvenido a Craps \n"+
            "Oprime el botón lanzar para iniciar el juego." +
            "\nSi tu tiro de salida es 7 u 11 ganas con Natural." +
            "\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps." +
            "\nSi sacas cualquier otro valor establecerás el \"Punto\"." +
            "\nEstando en \"Punto\" podrás seguir lanzando los dados," +
            "\npero ahora ganarás si sacas nuevamente el valor"+
            "\ndel \"Punto\" sin que previamente hayas sacado 7.";


    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar, ayuda, salida;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea mensajesSalida, resultadoDados;
    private Escucha escucha;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUIGridBagLayout class
     */
    public GUIGridBagLayout(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Craps Game");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUIGridBagLayout class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //obtenemos el contenedor y se modifica
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        //Create Listener Object and Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();

        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);

        //Set up constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints);


        //Set up boton ayuda
        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        //Set up constraints ayuda
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda, constraints);


        //Set up boton salida
        salida = new JButton("Salir");
        salida.addActionListener(escucha);
        //Set up constraints salida
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salida, constraints);


        //Imagen del dado
        imageDado = new ImageIcon(getClass().getResource("/recursos/dado_1 (2) - copia-edt.png"));
        //etiquetas de cada dado
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);
        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        //Set up constraints dado
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDados, constraints);

        //Area de texto resultados
        resultadoDados = new JTextArea(4,31);
        resultadoDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadoDados.setText("Debes lanzar los dados");
        //Set up constraints area texto resultados
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(resultadoDados, constraints);

        //botón lanzar
        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        //Set up constraints botón lanzar
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(lanzar, constraints);

        //mensajes de salida
        mensajesSalida = new JTextArea(3,26);
        mensajesSalida.setText("Usa el boton de ayuda \"?\" de la izquierda superior\npara las instrucciones del juego.");
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Instruciones"));
        //Set up constraints mensajes de salida
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(mensajesSalida, constraints);



    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUIGridBagLayout class
     */
    private class Escucha implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == lanzar){

                modelCraps.calcularTiro();
                int[] caras = modelCraps.getCaras();
                imageDado = new ImageIcon(getClass().getResource("/recursos/120x120/"+caras[0]+"dado 120-120.png"));
                dado1.setIcon(imageDado);
                imageDado = new ImageIcon(getClass().getResource("/recursos/120x120/"+caras[1]+"dado 120-120.png"));
                dado2.setIcon(imageDado);
                modelCraps.determinarJuego();
                resultadoDados.setText(modelCraps.getEstadoToString()[0]);
                mensajesSalida.setText(modelCraps.getEstadoToString()[1]);

            }else{
                if(e.getSource() == ayuda){
                    JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
            }






        }
    }
}
