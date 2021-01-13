package io.namoosori.travelclub.step3.logic;

import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.util.exception.ClubDuplicationException;
import io.namoosori.travelclub.util.exception.InvalidArguementException;
import io.namoosori.travelclub.util.exception.NoSuchClubException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClubCoordinator {

    public static final int MIN_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 20;

    private MemberHelper memberHelper;
    private List<TravelClub> clubList;

    public ClubCoordinator() {

        this.memberHelper = new MemberHelper();
        this.clubList = new ArrayList<>();
    }

    public MemberHelper getMemberHelper() {  return memberHelper;}

    public boolean hasClubs(){
        return clubList.size() != 0;
    }

    public boolean existByname(String name){
        /*for( TravelClub club : clubList){
            if (club.getName().equals(name)){
                return true;
            }
        }
        return false;*/
        return clubList.stream().anyMatch(club -> club.getName().equals(name));
    }

    public String register(TravelClub newClub){
        String name = newClub.getName();

        if(existByname(name)){
            throw new ClubDuplicationException("Club name already exists. --> " + name);
        }
        clubList.add(newClub);

        return newClub.getName();
    }

    public TravelClub find(String name){
        /*for(TravelClub club :  clubList){
            if(club.getName().equals(name)){
                return club;
            }
        }
        return null;*/
        return clubList.stream().filter(club -> club.getName().equals(name)).findFirst().orElse(null);
    }

    public List<TravelClub> findAll(){
        return clubList;
    }

    public List<TravelClub> finaAll(int offset, int pagesize) throws InvalidArguementException{
        if(!(pagesize == MIN_PAGE_SIZE || pagesize == MAX_PAGE_SIZE)){
            throw new InvalidArguementException("Page size should be 10 or 20.");
        }

        List<TravelClub> resultClub = new ArrayList<>();

        int clubCount = clubList.size();
        int index = 0;
        for(int i = 0 ; i < pagesize ; i++){
            index = offset + i;
            if (clubCount <= index)
                break;
            resultClub.add(clubList.get(offset + i));
        }

        return resultClub;
    }

    public void modify(String clubname, Map<String, String> nameValueMap){
        TravelClub club = find(clubname);

        if(club == null){
            throw new NoSuchClubException("No such club with name " + clubname);
        }

        /*for( String name : nameValueMap.keySet()){
            String value = nameValueMap.get(name);

            switch (name){
                case "intro":
                    club.setIntro(value);
                    break;
            }
        }*/
        nameValueMap.entrySet().stream().forEach(map -> {
            switch (map.getKey()){
                case("intro"):
                    club.setIntro(map.getValue());
                    break;
            }
        });
    }

    public void remove(TravelClub club){
        clubList.remove(club);
    }

    public void remove(String name){
        TravelClub club = find(name);
        if(club == null){
            throw new NoSuchClubException("No such club with name " + name);
        }
        remove(club);
    }
}
