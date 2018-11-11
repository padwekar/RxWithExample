package model;

import java.util.ArrayList;
import java.util.List;

public class Bugs implements Comparable<Bugs>{
    public int id = 0;
    public boolean isFromActive;
    public int assignedTo = 0;
    public String title = "";

    public Bugs(int id ,String title,int assignedTo,boolean isFromActive){
        this.id = id;
        this.title = title;
        this.assignedTo = assignedTo;
        this.isFromActive = isFromActive;
    }

    public Bugs(int id ,String title,int assignedTo){
        this.id = id;
        this.title = title;
        this.assignedTo = assignedTo;
    }




    @Override
    public int compareTo(Bugs o) {
        if(id == o.id){
            return 0;
        }
        return id > o.id ? 1 : -1;
    }


    public static List<Bugs> bugsFromBackLock(){
        List<Bugs> bugsList = new ArrayList<>();
        bugsList.add(new Bugs(11,"Button not working",19));
        bugsList.add(new Bugs(98,"TextView not working",12));
        bugsList.add(new Bugs(31,"Wrong Text Displayed",12));
        bugsList.add(new Bugs(52,"Crashes on launch",10));
        bugsList.add(new Bugs(8,"Login Takes lot of time",10));
        return  bugsList;
    }

    public static List<Bugs> activeBugs(){
        List<Bugs> bugsList = new ArrayList<>();
        bugsList.add(new Bugs(99,"Button Color not proper",11,true));
        bugsList.add(new Bugs(76,"Crash in production build",19,true));
        bugsList.add(new Bugs(88,"Scrolling crashes the application",19,true));
        bugsList.add(new Bugs(25,"Payment option not displayed",10,true));
        bugsList.add(new Bugs(34,"No option to logout",10,true));
        return  bugsList;
    }

    @Override
    public String toString() {
        return "Bugs{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}