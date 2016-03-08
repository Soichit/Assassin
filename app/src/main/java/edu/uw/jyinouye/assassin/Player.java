package edu.uw.jyinouye.assassin;

import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by colec on 3/6/2016.
 */
public class Player {

    private Firebase playerRef;

    private String uid;
    private String email;
    private String groupName;
    private Location location;
    private int kills;
    private int deaths;
    private int currency;

    private OnPlayerUpdatedListener mPlayerUpdatedListener;

    public Player() {}

    public Player(String uid, String email, String groupName) {

        this.uid = uid;
        this.email = email;
        this.groupName = groupName;
        this.kills = 0;
        this.deaths = 0;
        this.currency = 0;
    }

    public void setRef(Firebase ref) {
        playerRef = ref;
    }

    public void incKill() {
        kills = kills + 1;
        currency = currency + 5;
    }

    public void incDeath() { deaths = deaths + 1; }

    public int getCurrency() { return currency; }

    public int getKills() { return kills; }

    public int getDeaths() { return deaths; }

    public String getUid() { return uid; }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Location getLocation() { return this.location; }

    public void setLocation(Location location) {
        Log.v("PlayerObject", "set location");
        Map<String, Object> loc = new HashMap<>();
        loc.put("lat", location.getLatitude());
        loc.put("lng", location.getLongitude());
        playerRef.child("players").child(uid).child("location").updateChildren(loc);
        this.location = location;
    }

    //interface allows Assassin class to listen for changes in player location, send them to firebase
    public interface OnPlayerUpdatedListener {
        void onPlayerLocationChanged(Location location);
    }
}
