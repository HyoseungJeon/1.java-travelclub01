package io.namoosori.travelclub.step3.logic;

import io.namoosori.travelclub.step1.entity.ClubMember;
import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.util.exception.MemberDuplicationException;

import java.util.ArrayList;
import java.util.List;

public class MemberHelper {

    public MemberHelper(){


    }

    public List<ClubMember> getMembers(TravelClub club) { return club.getMembers();}

    public boolean hasMembers(TravelClub club){

        return club.getMembers().size() != 0;
    }

    public boolean existByEmail(TravelClub club, String email){

        for(ClubMember member :  club.getMembers()){
            if(member.getEmail().equals(email)){
                return true;
            }
        }

        return false;
    }

    public String register(TravelClub club, ClubMember  newMember){
        String email = newMember.getEmail();

        if(existByEmail(club, email)){
            throw new MemberDuplicationException("Member already exists with email " + email);
        }

        club.getMembers().add(newMember);

        return newMember.getEmail();
    }

    public ClubMember find(TravelClub club, String email){
        for(ClubMember member : club.getMembers()){
            if(member.getEmail().equals(email)){
                return member;
            }
        }
        return null;
    }

    public void modify(TravelClub club, String memberEmail, ClubMember newClubMember){
        List<ClubMember> clubMembers = new ArrayList<>();

        for(ClubMember clubMember : club.getMembers()){
            ClubMember targetMember= clubMember;

            if(clubMember.getEmail().equals(memberEmail)){
                targetMember = newClubMember;
            }

            clubMembers.add(targetMember);
        }

        club.setMembers(clubMembers);
    }

    public void remove(TravelClub club, ClubMember clubMember){
        club.getMembers().remove(clubMember);
    }

}
