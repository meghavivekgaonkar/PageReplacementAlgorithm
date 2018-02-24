/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
