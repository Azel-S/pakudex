import java.util.Scanner;

public class PakuriProgram
{
    public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);

        int maxCapacity, userInt = 0;
        String userString;

        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        // Get capacity for Pakudex
        maxCapacity = getCapacity(scnr);
        System.out.println("The Pakudex can hold " + maxCapacity + " species of Pakuri.");
        System.out.println();

        Pakudex pakudexList = new Pakudex(maxCapacity);     // Main Pakudex object

        while(userInt != 6)
        {
            printMenu();
            System.out.println();

            System.out.print("What would you like to do? ");
            userString = scnr.next();

            // If the input is not a digit between 0 and 7 (non-inclusive), then print unrecognized message
            // and start loop again.
            if(isDigit(userString) && Integer.parseInt(userString) > 0 && Integer.parseInt(userString) < 7)
            {
                userInt = Integer.parseInt(userString);
            }
            else
            {
                System.out.println("Unrecognized menu selection!");
                System.out.println();
                continue;
            }

            switch(userInt)
            {
                case 1: // List Pakuri
                {
                    printPakuriList(pakudexList);
                    System.out.println();
                    break;
                }
                case 2: // Show Pakuri
                {
                    printPakuri(scnr,pakudexList);
                    System.out.println();
                    break;
                }
                case 3: // Add Pakuri
                {
                    addPakuri(scnr, pakudexList);
                    System.out.println();
                    break;
                }
                case 4: // Evolve Pakuri
                {
                    evolvePakuri(scnr, pakudexList);
                    System.out.println();
                    break;
                }
                case 5: // Sort Pakuri
                {
                    pakudexList.sortPakuri();
                    System.out.println("Pakuri have been sorted!");
                    System.out.println();
                    break;
                }
                case 6: // Exit
                {
                    System.out.println("Thanks for using Pakudex! Bye!");
                    break;
                }
            }
        }
    }

    public static void printMenu()
    {
        System.out.println("Pakudex Main Menu");
        System.out.println("-----------------");
        System.out.println("1. List Pakuri");
        System.out.println("2. Show Pakuri");
        System.out.println("3. Add Pakuri");
        System.out.println("4. Evolve Pakuri");
        System.out.println("5. Sort Pakuri");
        System.out.println("6. Exit");
    }

    // Determines if a string is made of digits only
    // Return true if so, false otherwise
    public static boolean isDigit(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    // Keeps asking for a valid size until one is given
    public static int getCapacity(Scanner scnr)
    {
        while(true)
        {
            System.out.print("Enter max capacity of the Pakudex: ");
            String userCapacity = scnr.next();

            if(isDigit(userCapacity))
            {
                // Parse string to int when a valid digit is found
                return Integer.parseInt(userCapacity);
            }
            else
            {
                System.out.println("Please enter a valid size.");
            }
        }
    }

    // Prints a list of the filled in Pakuri(s)
    public static void printPakuriList(Pakudex list)
    {
        if(list.getSize() <= 0) // If there are no Pakuris, print an appropriate message
        {
            System.out.println("No Pakuri in Pakudex yet!");
        }
        else
        {
            // Make array with species names only
            String[] speciesList = list.getSpeciesArray();

            System.out.println("Pakuri In Pakudex:");

            // Iterate and print each name
            for (int i = 0; i < list.getSize(); i++)
            {
                System.out.println((i + 1) + ". " + speciesList[i]);
            }
        }
    }

    // Prints the stats of a Pakuri if it has already been registered
    // Prints an error message otherwise.
    public static void printPakuri(Scanner scnr, Pakudex list)
    {
        System.out.print("Enter the name of the species to display: ");
        String species = scnr.next();
        System.out.println();

        // Get stats as an array
        int[] statsList = list.getStats(species);

        if(statsList != null)
        {
            System.out.println("Species: " + species);
            System.out.println("Attack: " + statsList[0]);
            System.out.println("Defense: " + statsList[1]);
            System.out.println("Speed: " + statsList[2]);
        }
        else
        {
            System.out.println("Error: No such Pakuri!");
        }
    }

    // Adds Pakuri to Pakuridex if there is still space left
    public static void addPakuri(Scanner scnr, Pakudex list)
    {
        if(list.getSize() >= list.getCapacity())
        {
            System.out.println("Error: Pakudex is full!");
        }
        else
        {
            System.out.print("Enter the name of the species to add: ");
            String species = scnr.next();

            if(list.addPakuri(species)) // Depending on the result, print a message
            {
                System.out.println("Pakuri species " + species + " successfully added!");
            }
            else
            {
                System.out.println("Error: Pakudex already contains this species!");
            }
        }
    }

    // Evolves Pakuri if it has already been registered
    public static void evolvePakuri(Scanner scnr, Pakudex list)
    {
        System.out.print("Enter the name of the species to evolve: ");
        String species = scnr.next();

        if(list.evolveSpecies(species)) // Depending on the result, print a message
        {
            System.out.println(species + " has evolved!");
        }
        else
        {
            System.out.println("Error: No such Pakuri!");
        }
    }
}
