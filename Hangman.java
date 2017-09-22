import java.util.Scanner;

public class Hangman {
    
    String secretWord;
    StringBuffer word;
    StringBuffer lettersGuessed;
    int guessCounter;
    boolean isPlaying;
    int lives;
    Scanner scanner = new Scanner(System.in);
    public Hangman(StringBuffer word, boolean isPlaying, StringBuffer lettersGuessed, int lives){
        this.word = word;
        this.isPlaying = isPlaying;
        this.lettersGuessed = lettersGuessed;
        this.lives = lives;
    }
   
    
    public void compute(){
        
        while(isPlaying){
            System.out.print("\033[H\033[2J");
            newGame();
            setStars();
            System.out.printf("The word is %d characters long.\n", secretWord.length());

            while(!(word.toString().equalsIgnoreCase(secretWord)) && guessCounter <= lives){
                System.out.printf("You have %d lives left\n",lives-guessCounter);
                System.out.println("Guess a letter!");

                char input = scanner.nextLine().charAt(0);
                for(int i = 0; i < secretWord.length(); i++){
                    word.setCharAt(i,input == secretWord.charAt(i) ? input : word.charAt(i));
                }
                System.out.print("\033[H\033[2J");

            if(!lettersGuessed.toString().contains(Character.toString(input))){
                lettersGuessed.append(String.format("%s ",input));
                guessCounter = secretWord.contains(Character.toString(input))  ? guessCounter : guessCounter+1;
            } else{
                System.out.println("You have already guessed that letter!");
            }

                System.out.printf("The letters guessed are: %s \n%s\n",lettersGuessed,word);
            }
            System.out.println(!(word.toString().equalsIgnoreCase(secretWord)) || lives == guessCounter ? "You lose" : "You win");
            playAgain();
        }

    
    }

    public void playAgain(){
        System.out.println("Would you like to play again? (y/n)" +
                "");
        String playAgain = scanner.nextLine();
        if(playAgain.toLowerCase().contains("n")){
            System.out.println("Thanks for playing");
            isPlaying = false;
        } else {
            System.out.println("Playing again.");

        }
    }

    public void setStars(){
        for(char i: secretWord.toCharArray()){
            word.append("*");
        }
    }
    
    public void newGame(){
        System.out.println("Choose a new word to play hangman with.");
        secretWord = scanner.nextLine().toLowerCase();
        word.setLength(0);
        guessCounter = 0;
        lettersGuessed.setLength(0);
    }
   
    
}
