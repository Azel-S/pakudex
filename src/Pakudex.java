public class Pakudex
{
    private int size, capacity;
    Pakuri[] PakuriList;

    // Default Constructor
    public Pakudex()
    {
        capacity = 20;

        PakuriList = new Pakuri[capacity];
        size = 0;
    }

    public Pakudex(int capacity)
    {
        this.capacity = capacity;

        PakuriList = new Pakuri[capacity];
        size = 0;
    }

    public int getSize()
    {
        return size;
    }

    public int getCapacity()
    {
        return  capacity;
    }

    // Makes an array based on the number of filled in Pakuris
    // Fills them with their respective name
    public String[] getSpeciesArray()
    {
        if (size == 0)
        {
            return null;
        }
        else
        {
            String[] speciesList = new String[size];

            // Iterate through list of names in Pakuridex
            for(int i = 0; i < size; i++)
            {
                speciesList[i] = PakuriList[i].getSpecies();
            }

            return speciesList;
        }
    }

    // Makes an array of size 3, and puts attack, defense, and speed in each index respectively.
    public int[] getStats(String species)
    {
        String[] speciesList = getSpeciesArray();

        // Iterate through list of names in Pakuridex
        for (int i = 0; i < size; i++)
        {
            if(species.equals(speciesList[i]))
            {
                return new int[] {  PakuriList[i].getAttack(),
                                    PakuriList[i].getDefense(),
                                    PakuriList[i].getSpeed()    };
            }
        }

        return null;
    }

    // Based off of insertion sort, uses species name to sort
    public void sortPakuri()
    {
        int j; // Used to do swaps until currentP is at it's designated spot

        for (int i = 1; i < size; i++)  // Starts at index 1, as 0 doesn't matter
        {
            j = i - 1;  // Sets j to the left element so swaps can be done

            Pakuri currentP = PakuriList[i];    // Holds the current Pakari at i'th index

            // While currentP is shorter, keep swapping values at (j) and (j + 1). Once swapped, lower j by 1
            while (j >= 0 && currentP.getSpecies().compareTo(PakuriList[j].getSpecies()) < 0)
            {
                Pakuri temp = PakuriList[j + 1];
                PakuriList[j + 1] = PakuriList[j];
                PakuriList[j] = temp;
                j--;
            }
        }
    }

    // Adds Pakuri if it there is still space in pakuridex
    // and a duplicate doesn't exist.
    public boolean addPakuri(String species)
    {
        if(size >= capacity)
        {
            return false;
        }
        else
        {
            // Make array with names of filled in Pakuris
            String[] speciesList = getSpeciesArray();

            // Iterate through list of names in Pakuridex
            for (int i = 0; i < size; i++)
            {
                // When a duplicate is found, return false
                if(species.equals(speciesList[i]))
                {
                    return false;
                }
            }

            // If no duplicates, add to Pakuridex and increase size.
            PakuriList[size] = new Pakuri(species);
            size++;
            return true;
        }
    }

    // If Pakuri exists in Pakuridex, evolve it
    public boolean evolveSpecies(String species)
    {
        String[] speciesList = getSpeciesArray();

        // Iterate through list of names in Pakuridex
        for (int i = 0; i < size; i++)
        {
            // When a Pakuri is found, evolve it
            if(species.equals(speciesList[i]))
            {
                PakuriList[i].evolve();
                return true;
            }
        }

        // If no Pakuri is found, return false
        return false;
    }
}
