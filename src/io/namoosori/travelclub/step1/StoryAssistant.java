package io.namoosori.travelclub.step1;

import io.namoosori.travelclub.step1.entity.ClubMember;
import io.namoosori.travelclub.step1.entity.RolelnClub;
import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.util.exception.InvalidEmailException;

public class StoryAssistant {

    public static void main(String[] args){

        StoryAssistant storyAssistant = new StoryAssistant();
        storyAssistant.start();
    }


    public void start(){

        String name = "JavaTravelClub";
        String intro = "Travel club to the java island.";

        TravelClub club = new TravelClub(name, intro);
        club.getMembers().add(inviteLeader());
        club.getMembers().add(inviteMember());
        System.out.println(club);

        try {
            club.getMembers().add(inviteBadEmailMember());
        } catch ( InvalidEmailException e){
            System.out.println(e.getMessage());
        }
    }

    private ClubMember inviteLeader() {
        ClubMember leader = new ClubMember("hello@nextree.io", "hello Kim", "010-0001-0001");
        leader.setRole(RolelnClub.President);

        return leader;
    }

    private ClubMember inviteMember(){
        return new ClubMember("mymy@nextree.io", "Mymy Lee", "010-0001-1002");
    }
    private ClubMember inviteBadEmailMember(){
        String invalidEmail ="^^_^^@@nextree.io";
        return new ClubMember(invalidEmail, "Mymy Lee","010-0001-1003");
    }
}
