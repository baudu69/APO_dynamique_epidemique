package Nombre;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class App extends JFrame {
    private JTextField tailleGrille;
    private JTextField nbPerosonnesParSimu;
    private JTextField nbInteractParPers;
    private JTextField alpha;
    private JTextField beta;
    private JTextField gamma;
    private JTextField tempsSimulation;

    private JLabel lbl_tailleGrille;
    private JLabel lbl_nbPerosonnesParSimu;
    private JLabel lbl_nbInteractParPers;
    private JLabel lbl_alpha;
    private JLabel lbl_beta;
    private JLabel lbl_gamma;
    private JLabel lbl_tempsSimulation;

    private JLabel lbl_chargement;


    public App(){
        super();
        build();
    }
    private void build(){
        setTitle("Simulation Evolution d'une Epidemie"); //On donne un titre à l'application
        setSize(420,300); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setContentPane(buildContentPane());
        setVisible(true);
    }

    private void init() {
        this.tailleGrille.setText("30");
        this.nbPerosonnesParSimu.setText("1200");
        this.nbInteractParPers.setText("5");
        this.alpha.setText("0.1f");
        this.beta.setText("0.1f");
        this.gamma.setText("0.05f");
        this.tempsSimulation.setText("365");
    }

    private JPanel buildContentPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        tailleGrille = new JTextField();
        nbPerosonnesParSimu = new JTextField();
        nbInteractParPers = new JTextField();
        alpha = new JTextField();
        beta = new JTextField();
        gamma = new JTextField();
        tempsSimulation = new JTextField();
        tailleGrille.setColumns(3);
        nbPerosonnesParSimu.setColumns(5);
        nbInteractParPers.setColumns(5);
        alpha.setColumns(4);
        beta.setColumns(4);
        gamma.setColumns(4);
        tempsSimulation.setColumns(3);

        lbl_tailleGrille = new JLabel();
        lbl_nbPerosonnesParSimu = new JLabel();
        lbl_nbInteractParPers = new JLabel();
        lbl_alpha = new JLabel();
        lbl_beta = new JLabel();
        lbl_gamma = new JLabel();
        lbl_tempsSimulation = new JLabel();

        lbl_tailleGrille.setText("Taille de la grille :");
        lbl_nbPerosonnesParSimu.setText("Nombre de personnes par simulation");
        lbl_nbInteractParPers.setText("Nombre d'interractions par personnes et par jour");
        lbl_alpha.setText("Probabilite de passer de Incubation a Infecte");
        lbl_beta.setText("Probabilite d'infecter la personne rencontre");
        lbl_gamma.setText("Probabilite d'etre gueri");
        lbl_tempsSimulation.setText("Temps total de la simulation");

        panel.add(lbl_tailleGrille);
        panel.add(tailleGrille);
        panel.add(lbl_nbPerosonnesParSimu);
        panel.add(nbPerosonnesParSimu);
        panel.add(lbl_nbInteractParPers);
        panel.add(nbInteractParPers);
        panel.add(lbl_alpha);
        panel.add(alpha);
        panel.add(lbl_beta);
        panel.add(beta);
        panel.add(lbl_gamma);
        panel.add(gamma);
        panel.add(lbl_tempsSimulation);
        panel.add(tempsSimulation);

        JButton bouton = new JButton(new GetAction(this, "Lancer la simulation"));

        panel.add(bouton);

        setLbl_chargement(new JLabel());
        panel.add(getLbl_chargement());

        init();

        return panel;
    }

    public void lancerSimu() {
        String strTaille = this.tailleGrille.getText();
        String strNbPers = this.nbPerosonnesParSimu.getText();
        String strNbrInter = this.nbInteractParPers.getText();
        String strAlpha = this.alpha.getText();
        String strBeta = this.beta.getText();
        String strGamma = this.gamma.getText();
        String strTempsSimu = this.tempsSimulation.getText();
        ArrayList<ArrayList<Integer>> listeMesures = new ArrayList<>();
        ArrayList<Integer> confinement = new ArrayList<>();
        ArrayList<Integer> masque = new ArrayList<>();
        ArrayList<Integer> quarantaine = new ArrayList<>();
        ArrayList<Integer> vaccination = new ArrayList<>();
        listeMesures.add(confinement);
        listeMesures.add(masque);
        listeMesures.add(quarantaine);
        listeMesures.add(vaccination);
        Grille uneGrille = new Grille(this, Integer.parseInt(strTaille), Integer.parseInt(strNbPers), Integer.parseInt(strNbrInter), Float.parseFloat(strAlpha), Float.parseFloat(strBeta), Float.parseFloat(strGamma), Integer.parseInt(strTempsSimu), listeMesures);
        uneGrille.LancerSimulationComplete();
        ArrayList<ArrayList<Integer>> lesResultats = uneGrille.getLesResultats();
        Graphique chart = new Graphique("Evo" , "Evo", lesResultats);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    public JLabel getLbl_chargement() {
        return lbl_chargement;
    }

    public void setLbl_chargement(JLabel lbl_chargement) {
        this.lbl_chargement = lbl_chargement;
    }
}

class GetAction extends AbstractAction {
    private App fenetre;

    public GetAction(App fenetre, String texte){
        super(texte);

        this.fenetre = fenetre;
    }

    public void actionPerformed(ActionEvent e) {
        fenetre.lancerSimu();
    }

}
