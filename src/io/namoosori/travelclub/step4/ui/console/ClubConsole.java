package io.namoosori.travelclub.step4.ui.console;

import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.step4.logic.ClubCoordinator;
import io.namoosori.travelclub.util.exception.ClubDuplicationException;
import io.namoosori.travelclub.util.exception.NoSuchClubException;
import io.namoosori.travelclub.util.util.ConsoleUtil;
import io.namoosori.travelclub.util.util.Narrator;
import io.namoosori.travelclub.util.util.TalkingAt;

import java.util.HashMap;
import java.util.Map;

public class ClubConsole {

    private ClubCoordinator clubCoordinator;

    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public ClubConsole(ClubCoordinator clubCoordinator){

        this.clubCoordinator = clubCoordinator;
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    public TravelClub register(){
        TravelClub newClub = null;

        while (true){
            String name = consoleUtil.getValueOf("\t club name(0.Club menu)");
            if(name.equals("0")){
                break;
            }

            if(clubCoordinator.existByname(name)){
                narrator.sayln("Club with this name already exists. --> " + name );
                continue;
            }

            String intro = consoleUtil.getValueOf(" club intro");

            try {
                newClub = new TravelClub(name, intro);
                clubCoordinator.register(newClub);

                narrator.say("Registered club :" + newClub.toString());
            } catch (IllegalArgumentException | ClubDuplicationException e){
                narrator.say(e.getMessage());
            }

        }
        return newClub;
    }

    public TravelClub find(){

        TravelClub clubFound = null;

        if(!clubCoordinator.hasClubs()){
            narrator.sayln("<?> No clubs int the storage.");
            return null;
        }

        while (true){
            String name = consoleUtil.getValueOf("\n club name to find(0.Club menu) ");
            if(name.equals("0"))
                break;

            if(clubCoordinator.existByname(name)){
                clubFound = clubCoordinator.find(name);
                narrator.sayln("\t > Found club: " + clubFound);
            } else {
                narrator.sayln("\t > No such club in the storage --> " + name);
            }
        }
        return clubFound;
    }

    public TravelClub findOne(){

        TravelClub clubFound = null;

        if(!clubCoordinator.hasClubs()){
            narrator.sayln("<?> No clubs in the storage.");
            return null;
        }

        while (true){
            String name = consoleUtil.getValueOf(" Club name to find(0.Club menu) ");
            if(name.equals("0")){
                break;
            }

            if(clubCoordinator.existByname(name)){
                clubFound = clubCoordinator.find(name);
                narrator.sayln("\t > Found club: " + clubFound);
                break;
            } else{
                narrator.sayln("\t > No such club in the storage --> " + name);
            }
        }
        return clubFound;
    }

    public TravelClub modify(){

        TravelClub targetClub = findOne();
        if(targetClub == null){
            return null;
        }

        Map<String, String> nameValueMap = new HashMap<>();

        String newIntro = consoleUtil.getValueOf(" new intro(0.Club menu, Enter. no change)");
        if(newIntro.equals("0")){
            return targetClub;
        }
        if(newIntro.equals("")){
            newIntro = targetClub.getIntro();
        }

        nameValueMap.put("intro", newIntro);

        try {
            clubCoordinator.modify(targetClub.getName(), nameValueMap);
            narrator.sayln("Modified club :" + targetClub.toString());
        } catch (IllegalArgumentException | NoSuchClubException e){
            narrator.sayln(e.getMessage());
        }

        return targetClub;
    }

    public void remove(){
        TravelClub targetClub = findOne();
        if(targetClub == null)
            return;

        String confirmStr = consoleUtil.getValueOf("Remove this club? (Y:yes, N;no)");
        if(confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")){
            try{
                clubCoordinator.remove(targetClub.getName());
                narrator.sayln("Club removed --> " + targetClub.getName());
            }catch (IllegalArgumentException | NoSuchClubException e){
                narrator.sayln(e.getMessage());
            }
        } else {
            narrator.sayln("Remove cancelled, your club is safe. --> " + targetClub.getName());
        }
    }
}
