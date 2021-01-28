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
    private JTextField n;
    private JTextField u;
    private JTextField tauxEchange;
    private JTextField tauxVaccinationParJour;

    private JTextField dateDebConfinement;
    private JTextField dateFinConfinement;
    private JTextField dateDebPortMasque;
    private JTextField dateFinPortMasque;
    private JTextField dateDebQuarantaine;
    private JTextField dateFinQuaranataine;
    private JTextField dateDebVaccination;

    private JLabel lbl_tailleGrille;
    private JLabel lbl_nbPerosonnesParSimu;
    private JLabel lbl_nbInteractParPers;
    private JLabel lbl_alpha;
    private JLabel lbl_beta;
    private JLabel lbl_gamma;
    private JLabel lbl_tempsSimulation;
    private JLabel lbl_n;
    private JLabel lbl_u;
    private JLabel lbl_tauxEchange;
    private JLabel lbl_tauxVaccinationParJour;

    private JLabel lbl_dateDebConfinement;
    private JLabel lbl_dateFinConfinement;
    private JLabel lbl_dateDebPortMasque;
    private JLabel lbl_dateFinPortMasque;
    private JLabel lbl_dateDebQuarantaine;
    private JLabel lbl_dateFinQuaranataine;
    private JLabel lbl_dateDebVaccination;

    private JLabel lbl_chargement;


    public App(){
        super();
        build();
    }
    private void build(){
        setTitle("Simulation Evolution d'une Epidemie"); //On donne un titre à l'application
        setSize(420,550); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setContentPane(buildContentPane());
        setVisible(true);
    }

    private void init() {
        this.tailleGrille.setText("10");
        this.nbPerosonnesParSimu.setText("1200");
        this.nbInteractParPers.setText("5");
        this.alpha.setText("0.1f");
        this.beta.setText("0.1f");
        this.gamma.setText("0.05f");
        this.tempsSimulation.setText("365");
        this.n.setText("3.21f");
        this.u.setText("2.50f");
        this.tauxEchange.setText("0.1f");
        this.tauxVaccinationParJour.setText("0.2f");
        this.dateDebConfinement.setText("-1");
        this.dateFinConfinement.setText("-1");
        this.dateDebPortMasque.setText("-1");
        this.dateFinPortMasque.setText("-1");
        this.dateDebQuarantaine.setText("-1");
        this.dateFinQuaranataine.setText("-1");
        this.dateDebVaccination.setText("-1");
    }

    private JPanel buildContentPane(){
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.setLayout(new FlowLayout());
        tailleGrille = new JTextField();
        nbPerosonnesParSimu = new JTextField();
        nbInteractParPers = new JTextField();
        alpha = new JTextField();
        beta = new JTextField();
        gamma = new JTextField();
        tempsSimulation = new JTextField();
        n = new JTextField();
        u = new JTextField();
        tauxEchange = new JTextField();
        tauxVaccinationParJour = new JTextField();

        dateDebConfinement = new JTextField();
        dateFinConfinement = new JTextField();
        dateDebPortMasque = new JTextField();
        dateFinPortMasque = new JTextField();
        dateDebQuarantaine = new JTextField();
        dateFinQuaranataine = new JTextField();
        dateDebVaccination = new JTextField();

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
        lbl_n = new JLabel();
        lbl_u = new JLabel();
        lbl_tauxEchange = new JLabel();
        lbl_tauxVaccinationParJour = new JLabel();

        lbl_dateDebConfinement = new JLabel();
        lbl_dateFinConfinement = new JLabel();
        lbl_dateDebPortMasque = new JLabel();
        lbl_dateFinPortMasque = new JLabel();
        lbl_dateDebQuarantaine = new JLabel();
        lbl_dateFinQuaranataine = new JLabel();
        lbl_dateDebVaccination = new JLabel();

        lbl_tailleGrille.setText("Taille de la grille :");
        lbl_nbPerosonnesParSimu.setText("Nombre de personnes par simulation");
        lbl_nbInteractParPers.setText("Nombre d'interractions par personnes et par jour");
        lbl_alpha.setText("Probabilite de passer de Incubation a Infecte");
        lbl_beta.setText("Probabilite d'infecter la personne rencontre");
        lbl_gamma.setText("Probabilite d'etre gueri");
        lbl_tempsSimulation.setText("Temps total de la simulation");
        lbl_n.setText("Nombre d'enfant par jour    ");
        lbl_u.setText("Nombre de deces par jour    ");
        lbl_tauxEchange.setText("Taux d'echange avec la case d'a cote   ");
        lbl_tauxVaccinationParJour.setText("Taux de vaccination par jour   ");

        lbl_dateDebConfinement.setText("Jour de debut du confinement");
        lbl_dateFinConfinement.setText("Jour de fin du confinement");
        lbl_dateDebPortMasque.setText("Jour de debut du port du masque");
        lbl_dateFinPortMasque.setText("Jour de fin du port du masque");
        lbl_dateDebQuarantaine.setText("Jour de debut de la quarantaine");
        lbl_dateFinQuaranataine.setText("Jour de fin de la quarantaine");
        lbl_dateDebVaccination.setText("Jour de debut de la vaccination");

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
        panel.add(lbl_n);
        panel.add(n);
        panel.add(lbl_u);
        panel.add(u);
        panel.add(lbl_tauxEchange);
        panel.add(tauxEchange);
        panel.add(lbl_tauxVaccinationParJour);
        panel.add(tauxVaccinationParJour);

        panel.add(lbl_dateDebConfinement);
        panel.add(dateDebConfinement);
        panel.add(lbl_dateFinConfinement);
        panel.add(dateFinConfinement);
        panel.add(lbl_dateDebPortMasque);
        panel.add(dateDebPortMasque);
        panel.add(lbl_dateFinPortMasque);
        panel.add(dateFinPortMasque);
        panel.add(lbl_dateDebQuarantaine);
        panel.add(dateDebQuarantaine);
        panel.add(lbl_dateFinQuaranataine);
        panel.add(dateFinQuaranataine);
        panel.add(lbl_dateDebVaccination);
        panel.add(dateDebVaccination);

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
        String strN = this.n.getText();
        String strU = this.u.getText();
        String strTauxEchange = this.tauxEchange.getText();
        String strTauxVaccinationParJour = this.tauxVaccinationParJour.getText();

        Integer dateDebConfinement = Integer.parseInt(this.dateDebConfinement.getText());
        Integer dateFinConfinement = Integer.parseInt(this.dateFinConfinement.getText());
        Integer dateDebPortMasque = Integer.parseInt(this.dateDebPortMasque.getText());
        Integer dateFinPortMasque = Integer.parseInt(this.dateFinPortMasque.getText());
        Integer dateDebQuarantaine = Integer.parseInt(this.dateDebQuarantaine.getText());
        Integer dateFinQuaranataine = Integer.parseInt(this.dateFinQuaranataine.getText());
        Integer dateDebVaccination = Integer.parseInt(this.dateDebVaccination.getText());

        ArrayList<ArrayList<Integer>> listeMesures = new ArrayList<>();
        ArrayList<Integer> confinement = new ArrayList<>();
        confinement.add(dateDebConfinement);
        confinement.add(dateFinConfinement);
        ArrayList<Integer> masque = new ArrayList<>();
        masque.add(dateDebPortMasque);
        masque.add(dateFinPortMasque);
        ArrayList<Integer> quarantaine = new ArrayList<>();
        quarantaine.add(dateDebQuarantaine);
        quarantaine.add(dateFinQuaranataine);
        ArrayList<Integer> vaccination = new ArrayList<>();
        vaccination.add(dateDebVaccination);
        listeMesures.add(confinement);
        listeMesures.add(masque);
        listeMesures.add(quarantaine);
        listeMesures.add(vaccination);
        Grille uneGrille = new Grille(this, Integer.parseInt(strTaille), Integer.parseInt(strNbPers), Integer.parseInt(strNbrInter), Float.parseFloat(strAlpha), Float.parseFloat(strBeta), Float.parseFloat(strGamma), Integer.parseInt(strTempsSimu), Float.parseFloat(strN), Float.parseFloat(strU), listeMesures, Float.parseFloat(strTauxEchange), Float.parseFloat(strTauxVaccinationParJour));
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
