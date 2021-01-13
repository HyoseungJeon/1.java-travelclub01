package io.namoosori.travelclub.step4.logic;

import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.step4.storage.ClubStore;
import io.namoosori.travelclub.util.exception.ClubDuplicationException;
import io.namoosori.travelclub.util.exception.InvalidArguementException;
import io.namoosori.travelclub.util.exception.NoSuchClubException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ClubCoordinator {

    public static final int MIN_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 20;

    private MemberHelper memberHelper;
    private ClubStore clubStore;

    public ClubCoordinator(){
        this.memberHelper = new MemberHelper();
        this.clubStore = new ClubStore();
    }

    public MemberHelper getMemberHelper() { return memberHelper ;}

    public boolean hasClubs(){

        return clubStore.count() != 0;
    }

    public boolean existByname(String name){
        return clubStore.existbyName(name);
    }

    public void register(TravelClub newClub){
        if(clubStore.existbyName(newClub.getName())){
            throw new ClubDuplicationException(newClub.getName());
        }

        clubStore.create(newClub);
    }

    public TravelClub find(String name){
        return clubStore.retrieve(name);
    }

    public List<TravelClub> findAll(){
        return clubStore.retrieveAll();
    }

    public Collection<TravelClub> findAll(int offset, int pageSize) throws InvalidArguementException{

        if(!(pageSize == MIN_PAGE_SIZE || pageSize == MAX_PAGE_SIZE)){
            throw new InvalidArguementException("Page size should be 10 or 20");
        }
        return clubStore.retrieveAll(offset, pageSize);
    }

    public void modify(String clubName, Map<String, String> nameValueMap){

        if(!clubStore.existbyName(clubName)){
            throw new NoSuchClubException("No such club with name " + clubName);
        }

        TravelClub club = clubStore.retrieve(clubName);

        /*for(String name : nameValueMap.keySet()){
            String value = nameValueMap.get(name);

            switch (name){
                case "intro":
                    club.setIntro(value);
                    break;
                case "name":
                    club.setName(value);
                    break;
            }
        }*/

        //club.setIntro(nameValueMap.get(nameValueMap.keySet().stream().filter(name -> nameValueMap.get(name).equals("intro")).findFirst().get()));
        nameValueMap.entrySet().stream().forEach(map -> {
            switch (map.getKey()){
                case("intro"):
                    club.setIntro(map.getValue());
                    break;
            }
        });

        clubStore.update(club);
    }

    public void remove(String name){
        if(!clubStore.existbyName(name)){
            throw new NoSuchClubException("No such club with name " + name);
        }

        clubStore.delete(name);
    }
}
