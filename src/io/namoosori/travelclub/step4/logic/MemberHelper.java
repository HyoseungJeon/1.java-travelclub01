package io.namoosori.travelclub.step4.logic;

import io.namoosori.travelclub.step1.entity.ClubMember;
import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.step4.storage.ClubStore;
import io.namoosori.travelclub.util.exception.MemberDuplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberHelper {

    private ClubStore clubStore;

    public MemberHelper(){

        this.clubStore = new ClubStore();
    }

    public boolean existByEmail(TravelClub club, String email){
        /*for(ClubMember member : club.getMembers()){
            if(member.getEmail().equals(email))
                return true;
        }
        return true;*/
        return club.getMembers().stream().anyMatch(member -> member.getEmail().equals(email));
    }

    public boolean hasMembers(TravelClub club){
        return club.getMembers().size() != 0;
    }

    public String register(TravelClub club, ClubMember newMember){
        if(existByEmail(club, newMember.getEmail())){
            throw new MemberDuplicationException("Member already exists with email " + newMember.getEmail());
        }

        club.getMembers().add(newMember);

        clubStore.update(club);

        return newMember.getEmail();
    }

    public ClubMember find(TravelClub club, String email){
        /*for(ClubMember member : club.getMembers()){
            if(member.getEmail().equals(email)){
                return member;
            }
        }
        return null;*/
        return club.getMembers().stream().filter(member -> member.getEmail().equals(email)).findAny().orElse(null);
    }

    public void modify(TravelClub club, String memberEmail, ClubMember newClubMember){
        /*List<ClubMember> clubMembers = new ArrayList<>();

        for(ClubMember clubMember : club.getMembers()){
            ClubMember targetMember = clubMember;

            if(clubMember.getEmail().equals(memberEmail)){
                targetMember = newClubMember;
            }

            clubMembers.add(targetMember);
        }*/

        club.setMembers(club.getMembers().stream().filter(member -> member.getEmail().equals(memberEmail)).collect(Collectors.toList()));

        clubStore.update(club);
    }

    public void remove(TravelClub club, String memberEmail){
        /*List<ClubMember> clubMembers = new ArrayList<>();

        for(ClubMember clubMember : club.getMembers()){
            if(!clubMember.getEmail().equals(memberEmail)){
                clubMembers.add(clubMember);
            }
        }

        club.setMembers(clubMembers);*/
        club.setMembers(club.getMembers().stream().filter(member -> !(member.getEmail().equals(memberEmail))).collect(Collectors.toList()));

        clubStore.update(club);
    }
}
