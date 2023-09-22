import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleQuizz {
    private int score;
    private long timeElapsed;
    private boolean done = false;
    private int nbreQuestion;
    Scanner clavier = new Scanner(System.in);

    /**
     * Constructeur de la classe ConsoleQuizz
     *
     * @param nbreQuestion : Le nombre de questions � poser au joueur
     */
    public ConsoleQuizz(int nbreQuestion) {
        /*TODO*/
    }

    /**
     * Coeur du quizz, pose les questions, v�rifie les r�ponses et compte le score
     */
    public void start() {
        try {
            long startTime = System.currentTimeMillis();
            ArrayList<Question> questions = generate(nbreQuestion);
            for (int i = 0; i < nbreQuestion; i++) {
                System.out.println(questions.get(i).getPays());
                String userAnswer = clavier.nextLine();
//TOMODIFY : faire en sorte que la case ne soit pas prise en compte dans la comparaison ci-dessous
                if (userAnswer.equals(questions.get(i).getVille())) {
                    score++;
                    System.out.println("Bonne Reponse");
                } else {
                    System.out.println("Mauvaise Reponse");
                    System.out.println("la bonne reponse etait: " + questions.get(i).getVille());
                }
            }
            done = true;
            long endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
        } catch (IllegalArgumentException e) {
            done = false;
            System.out.println(e.getMessage());
        }

    }

    /**
     * Permet de convertir le temps de milliseconde � seconde
     *
     * @param timeInMilliSeconds : temps en milliseconde
     * @return : temps en seconde
     */
    private int getTimeElapsedInSeconds(long timeInMilliSeconds) {
        return (int) (timeInMilliSeconds / 1000);
    }

    /**
     * Affiche le r�sultat au joueur, un message d'erreur sinon
     */
    public void displayResultats() {
        if (done) {
            displayScore();
            displayTimeElapsed();
        } else {
            System.out.println("Vous n'avez rien r�pondu");
        }
    }

    /**
     * Affiche le temps utilis� par le joueur pour r�pondre aux questions
     */
    private void displayTimeElapsed() {
        System.out.printf("Il vous a fallu environ %d secondes pour repondre aux %d questions",
                getTimeElapsedInSeconds(timeElapsed), nbreQuestion);
    }

    /**
     * Affiche le score final
     */
    private void displayScore() {
        System.out.printf("votre score final est de: %d/%d\n ", score, nbreQuestion);

    }

    /**
     * Permet de cr�er un tableau de x questions-r�ponses, x �tant choisit au lancement du jeu
     *
     * @param nbreQuestions : nombre de question choisit au lancement du jeu
     * @return : un tableau de questions
     */
    public ArrayList<Question> generate(int nbreQuestions) {
        String[][] data = getData();
        int index = 0;
        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Integer> indexesAlreadyTaken = new ArrayList<Integer>();

        if (nbreQuestions > data.length) {
            throw new IllegalArgumentException("On ne peut generer que: " + data.length + " questions maximun");
        }
        indexesAlreadyTaken.clear();
        /*TODO : Expliquer ce que font les lignes 104 � 113*/
        for (int i = 0; i < nbreQuestions; i++) { // boucle qui permet de g�n�rer le nombre de question choisi par
            // l'utilisateur
            do {
                Random random = new Random(); //on cr�e un objet de type Random on g�n�re un nombre al�atoire
                index = random.nextInt(data.length); // on g�n�re un nombre al�atoire entre 0 et la taille du tableau
                // data
            } while (indexesAlreadyTaken.contains(index)); // on v�rifie si l'index g�n�r� n'existe pas d�j�
            indexesAlreadyTaken.add(index); // on ajoute l'index g�n�r� � la liste des index d�j� pris
            String pays = data[index][0]; // on r�cup�re le pays correspondant � l'index g�n�r�
            String capitale = data[index][1]; // on r�cup�re la capitale correspondant � l'index g�n�r�
            String questionText = String.format("Quelle est la capitale de ce pays: %s? ", pays); // on cr�e la question
            // � partir du pays r�cup�r�
            questions.add(new Question(questionText, capitale)); // on ajoute la question au tableau de question
        }
        return questions;
    }

    /**
     * Base de donn�es non persistantes
     *
     * @return un tableau � deux dimensions contenant les couples pays-capitale au format brut
     */
    private static String[][] getData() {
        String[][] data = {{"Senegal", "Dakar"}, {"France", "Paris"}, {"Haiti", "Paup"},
                {"Argemtine", "Brenos.A"}, {"Brasil", "brasilia"}, {"Itali", "Rome"}, {"Perou", "Lima"},
                {"Allemagne", "Berlin"}, {"Canada", "Otawa"}};
        return data;
    }

}
