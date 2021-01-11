package io.namoosori.travelclub.step3.ui.console;

import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.step3.logic.ClubCoordinator;
import io.namoosori.travelclub.util.exception.ClubDuplicationException;
import io.namoosori.travelclub.util.util.ConsoleUtil;
import io.namoosori.travelclub.util.util.Narrator;
import io.namoosori.travelclub.util.util.TalkingAt;

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
            String name = consoleUtil.getValuOf("\n club name(0.Club menu)");
            if(name.equals("0")){
                break;
            }

            if(clubCoordinator.existByname(name)){
                narrator.sayln("Club with this name already exists. --> " + name);
                continue;
            }

            String intro = consoleUtil.getValuOf(" club intro");

            try{
                newClub = new TravelClub(name, intro);
                clubCoordinator.register(newClub);
                narrator.say("Registered club :" + newClub.toString());
            }catch (IllegalArgumentException | ClubDuplicationException e) {
                narrator.say(e.getMessage());
            }
        }

        return newClub;
    }

    public TravelClub find(){
        TravelClub clubFound = null;

        if(!clubCoordinator.hasClubs()){
            narrator.sayln("<?> No clubs in the storage.");
            return null;
        }

        while (true){
            String name = consoleUtil.getValuOf("\n club name to find(0.Club menu) ");
            if(name.equals("0")){
                break;
            }

            if(clubCoordinator.existByname(name)){
                clubFound = clubCoordinator.find(name);
                narrator.sayln("\t > Found club: ");
            }
        }
        return clubFound;
    }
}
