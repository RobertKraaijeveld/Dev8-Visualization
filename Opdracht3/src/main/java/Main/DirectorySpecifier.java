package Main;

import java.util.Scanner;

/**
 *
 * @author gover_000
 */
public class DirectorySpecifier 
{
    public String getSpecifiedPathFromCommandLine()
    {
        displayWelcomeMessageOnCommandLine();
        
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();

        if (answer == null || answer.equals(""))
            return getSpecifiedPathFromCommandLine();
        else
            return answer;
    }
    
    private void displayWelcomeMessageOnCommandLine()
    {
        System.out.println("Welcome! Please specify the path to the CSV file of your choice.");
    }
}
