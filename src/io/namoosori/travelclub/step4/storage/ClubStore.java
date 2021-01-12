package io.namoosori.travelclub.step4.storage;

import io.namoosori.travelclub.step1.entity.TravelClub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClubStore {

    private Map<String, TravelClub> clubMap;

    public ClubStore(){

        this.clubMap = MapStorage.getInstance().getClubMap();
    }

    public String create(TravelClub club){
        clubMap.put(club.getName(), club);

        return club.getName();
    }

    public TravelClub retrieve(String name){
        return clubMap.get(name);
    }

    public List<TravelClub> retrieveAll(){
        return  new ArrayList<>(clubMap.values());
    }

    public List<TravelClub> retrieveAll(int offset, int pageSize){
        List<TravelClub> resultClub = new ArrayList<>();
        Iterator<TravelClub> clubIter = clubMap.values().iterator();

        int index = 0;

        while (clubIter.hasNext()){
            if(index == offset){
                break;
            }
            clubIter.next();
        }

        while (clubIter.hasNext()){
            TravelClub club = clubIter.next();
            resultClub.add(club);
        }

        return resultClub;
    }

    public void update(TravelClub club){
        clubMap.put(club.getName(), club);
    }

    public void delete(String name){
        TravelClub club = retrieve(name);

        clubMap.remove(club.getName());
    }

    public int count(){
        return clubMap.size();
    }

    public boolean existbyName(String name){
        return clubMap.get(name) != null;
    }
}
