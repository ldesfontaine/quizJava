
public class StartGame {
/**
 * La fonction main est le point d'entr�e du programme, elle est unique, lanc�e au d�but et obligatoire
 * @param args : arguments pass�s lors de l'appel depuis une invite de commandes
 */
	public static void main(String[] args) {
		System.out.println("Combien de questions voulez-vous ?");
		ConsoleQuizz quiz = new ConsoleQuizz(new java.util.Scanner(System.in).nextInt());
		quiz.start();
	}
}
