/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterInfo;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Job {
    
    private String name;
    private int strBon;
    private int dexBon;
    private int conBon;
    private int intBon;
    private int wisBon;
    private int chaBon;
    private int lucBon;
    private int magBon;
    private List<Integer> jobLevel = new ArrayList<>();
    private List<Integer> strHolder = new ArrayList<>();
    private List<Integer> dexHolder = new ArrayList<>();
    private List<Integer> conHolder = new ArrayList<>();
    private List<Integer> intHolder = new ArrayList<>();
    private List<Integer> wisHolder = new ArrayList<>();
    private List<Integer> chaHolder = new ArrayList<>();
    private List<Integer> lucHolder = new ArrayList<>();
    private List<Integer> magHolder = new ArrayList<>();
    private List<Integer> strPrmHolder = new ArrayList<>();
    private List<Integer> dexPrmHolder = new ArrayList<>();
    private List<Integer> conPrmHolder = new ArrayList<>();
    private List<Integer> intPrmHolder = new ArrayList<>();
    private List<Integer> wisPrmHolder = new ArrayList<>();
    private List<Integer> chaPrmHolder = new ArrayList<>();
    private List<Integer> lucPrmHolder = new ArrayList<>();
    private List<Integer> magPrmHolder = new ArrayList<>();
    
    public Job(String name){
        this.name = name;
    }
    
}
