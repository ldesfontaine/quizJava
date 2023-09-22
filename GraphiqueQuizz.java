import java.util.Scanner;

public class GraphiqueQuizz {
	/*TODO : reprendre la classe ConsoleQuizz en ne modifiant 
	que les lignes permettant l'affichage et l'interaction utilisateur*/

    private final ConsoleQuizz consoleQuizz;

    public GraphiqueQuizz(int nbreQuestion) {
        consoleQuizz = new ConsoleQuizz(nbreQuestion);
    }

    public void start() {
        System.out.println("Combien de questions voulez-vous ?");
        Scanner clavier = new Scanner(System.in);
        int nbreQuestion = clavier.nextInt();
        consoleQuizz.start();
    }
}