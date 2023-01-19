import java.util.ArrayList;

public class User {
    String userName;
    int userID;
    ArrayList<Relationship> friends = new ArrayList<>();

    @Override
    public String toString() {
        return userName + "[" + userID + "]";
    }
    
    public User(String userName, int userID){
        this.userName = userName;
        this.userID = userID;
        friends = new ArrayList<>();
    }

    public Relationship[] getFriends(){
        return friends.toArray(new Relationship[0]);
    }

    public Relationship addFriend(User friend, double friendshipValue){
        if(friend == null) return null;

        for(int i=0;i<friends.size();i++){
            Relationship rela = friends.get(i);
            if((rela.friendA == this && rela.friendB == friend)|| (rela.friendB == this && rela.friendA == friend)){
                return rela;
            }
        }

        Relationship newRela = new Relationship(this, friend, friendshipValue);

        this.addFriend(newRela);
        friend.addFriend(newRela);

        return newRela;
    }

    public void addFriend(Relationship relationship){
        if(relationship == null) return;

        if(relationship.friendA != this && relationship.friendB != this) return;

        for(int i=0;i<friends.size();i++){
            Relationship rela = friends.get(i);
            if(rela.equals(relationship)) return;
        }

        friends.add(relationship);
        return; 
    }

    ////HELPERS///////

    public double dist = 0;
    public Integer color = null;
    public User prev = null;
    public User pred = null;
    public double num = Double.POSITIVE_INFINITY;

    public GraphDB.Node node = null;

    public Relationship getRelationshipWith(User friend){
        if(friend==null) return null;

        for(int i=0;i<friends.size();i++){
            Relationship rela = friends.get(i);
            if((rela.friendA == this && rela.friendB == friend)|| (rela.friendB == this && rela.friendA == friend)){
                return rela;
            }
        }

        return null;
    }

    public int getUncoloredFriendCount(){
        int count = 0;
        for(int i=0;i<friends.size();i++){
            Relationship rela = friends.get(i);
            if(rela==null||rela.isIncomplete()) continue;
            User friend = null;
            if(rela.friendA == this) friend = rela.friendB;
            else if(rela.friendB == this) friend = rela.friendA;
            else continue;

            if(friend.color==null){
                count ++;
            }
        }
        return count;
    }

    public int getSaturationDegree(){
        ArrayList<Integer> colors = new ArrayList<>();
        for(int i=0;i<friends.size();i++){
            Relationship rela = friends.get(i);
            if(rela==null||rela.isIncomplete()) continue;
            User friend = null;
            if(rela.friendA == this) friend = rela.friendB;
            else if(rela.friendB == this) friend = rela.friendA;
            else continue;

            if(friend.color==null) continue;

            boolean exists = false;

            for(int c=0;c<colors.size();c++){
                Integer color = colors.get(c);
                if(color == friend.color){
                    exists = true; break;
                }
            }

            if(!exists){
                colors.add(friend.color);
            }
        }
        return colors.size();
    }

    public boolean friendsHaveColor(Integer color){
        int col = (int) color;

        for(int i=0;i<friends.size();i++){
            Relationship rela = friends.get(i);
            if(rela==null||rela.isIncomplete()) continue;
            User friend = null;
            if(rela.friendA == this) friend = rela.friendB;
            else if(rela.friendB == this) friend = rela.friendA;
            else continue;

            if(friend.color==null) continue;
            int frendCol = (int) friend.color;

            if(frendCol == col) return true;
        }

        return false;
    }

}
