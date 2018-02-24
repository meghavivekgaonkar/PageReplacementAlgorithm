
package pagereplacementalgorithm;

/**
 *
 * @author meghagaonkar
 */
public class Process {
    int processId;
    int processLifeTime;
    int processSize;
    //Default Constructor
    public Process()
    {}
    //Parameterized constructor to initialize member variables
    public Process(int i,int p, int s)
    {
        this.processId=i;
        this.processLifeTime=p;
        this.processSize=s;
    }
    
}
