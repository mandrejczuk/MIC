package pz1.MIC.Responses;

import org.springframework.security.core.GrantedAuthority;
import pz1.MIC.model.Authority;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private String userName;
    private ArrayList<GrantedAuthority> roleList;
    private long characterId;

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<GrantedAuthority> getRoleList() {
        return roleList;
    }

    public void setRoleList(ArrayList<GrantedAuthority> roleList) {
        this.roleList = roleList;
    }
}
