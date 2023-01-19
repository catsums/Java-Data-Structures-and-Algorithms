import java.util.ArrayList;

public class GraphDB {
    private ArrayList<User> users = new ArrayList<>();

    public User addUser(String userName, int ID){
        for(int i=0; i<users.size(); i++){
            User user = users.get(i);
            if(user.userID == ID){
                return user;
            }
        }

        User newUser = new User(userName, ID);
        users.add(newUser);

        return newUser;
    }

    public User getUser(int userID){
        for(int i=0; i<users.size(); i++){
            User user = users.get(i);
            if(user.userID == userID){
                return user;
            }
        }
        return null;
    }

    public User getUser(String userName){
        for(int i=0; i<users.size(); i++){
            User user = users.get(i);
            if(user.userName == userName){
                return user;
            }
        }
        return null;
    }

    public Relationship addFriendship(int frienteeID, int friendedID, double relationshipValue){
        User userA = this.getUser(frienteeID);
        User userB = this.getUser(friendedID);

        if((userA == null) || (userB == null)){
            return null;
        }

        Relationship rela = userA.addFriend(userB, relationshipValue);

        return rela;
    }

    public User[][] clusterUsers(){
        ArrayList<Integer> colors = BrelazAlgo();

        ArrayList<User>[] lists = new ArrayList[colors.size()];

        for(int i=0; i<users.size(); i++){
            User user = users.get(i);
            int color = (int) colors.get(user.color);

            if(lists[color]==null)
                lists[color] = new ArrayList<User>();
            lists[color].add(user);
        }

        User[][] cluster = new User[lists.length][];

        for(int i=0; i<lists.length; i++){
            User[] _list = bubblySortUsers(lists[i].toArray(new User[0]));

            cluster[i] = _list;
        }

        return cluster;

    }

    public Relationship[] minSpanningTree(){
        return KruskalAlgo();
    }

    public User[] getUsersAtDistance(User fromUser, int distance){
        if(fromUser==null || distance<0) return new User[0];

        unweightedLabelCorrectingAlgo(fromUser);

        ArrayList<User> distUsers = new ArrayList<>();

        for(int i=0;i<users.size();i++){
            User toUser = users.get(i);
            ArrayList<User> path = new ArrayList<>();
            buildPath(toUser, fromUser, path);

            My.cout("Path: "+pathAsString(path.toArray(new User[0])) + "Dist: " +(path.size()-1));
            
            if(path.size()-1 == distance){
                distUsers.add(toUser);
            }
        }

        return distUsers.toArray(new User[0]);

    }

    ///////HELPER FUNCTIONS/////////////

    protected String pathAsString(User[] path){
        String out = "";
        for(int c=0;c<path.length;c++){
            out += path[c].userName;
            if(c<path.length-1){
                out += " -> ";
            }
        }
        return out;
    }

    class myQueue{
        User[] arr;

        myQueue(){
            arr = new User[1];
        }

        public void push(User vert){
            arr[arr.length-1] = vert;
            User[] newArr = new User[arr.length + 1];
            for(int i=0;i<arr.length;i++)
                newArr[i] = arr[i];
            arr = newArr;
        }
        public User pop(){
            if(this.isEmpty()) return null;

            User vert = arr[0];
            User[] newArr = new User[arr.length - 1];
            for(int i=1;i<newArr.length;i++){
                newArr[i-1] = arr[i];
            }
            arr = newArr;

            return vert;
        } 
        public User shift(){
            //[0,1,2,null]
            if(this.isEmpty()) return null;

            User vert;
            if(arr.length>2)
                vert = arr[arr.length-2];
            else
                vert = arr[0];

            User[] newArr = new User[arr.length - 1];
            for(int i=0;i<newArr.length;i++){
                newArr[i] = arr[i];
            }
            arr = newArr;

            return vert;
        } 
        public boolean isEmpty(){
            if(arr.length<1) return true;
            if(arr[0] == null) return true;
            return false;
        }
        public boolean contains(User vert){
            for(User item:arr){
                if(item==null) continue;
                if(item == vert) return true;
            }
            return false;
        }
        public int size(){
            return arr.length - 1;
        }
    }

    // protected double[][] FWAlgo(){

    //     double[][] weight = new double[users.size()][];
    //     for(int i=0; i<users.size();i++){
    //         weight[i] = new double[users.size()];
    //         for(int v=0; v<users.size();v++){
    //             weight[i][v] = Double.POSITIVE_INFINITY;
    //         }
    //     }

    //     for(int i=0; i<users.size();i++){
    //         weight[i][i] = 0;
    //         for(int j=0; j<users.size();j++){
    //             Relationship rela = users.get(i).getRelationshipWith(users.get(j));
    //             if(rela!=null){
    //                 weight[i][j] = rela.friendshipValue;
    //             }
    //         }
    //     }

    //     for(int i=1; i<users.size();i++){
    //         for(int j=1; j<users.size();j++){
    //             for(int k=1; k<users.size();k++){
    //                 // Relationship relaJK = users.get(j).getRelationshipWith(users.get(k));
    //                 // Relationship relaJI = users.get(j).getRelationshipWith(users.get(i));
    //                 // Relationship relaIK = users.get(i).getRelationshipWith(users.get(k));
    //                 if(weight[j][k] > weight[j][i] + weight[i][k]){
    //                     weight[j][k] = weight[j][i] + weight[i][k];
    //                 }
    //             }
    //         }
    //     }

    //     return weight;
    // }

    protected void unweightedLabelCorrectingAlgo(User start){
        this.users.forEach((user)->{
            if(user==null) return;
            user.dist = Double.POSITIVE_INFINITY;
            user.prev = null;
        });

        start.dist = 0;
        myQueue queue = new myQueue();

        queue.push(start);
        User curr = null;

        while(!queue.isEmpty()){
            curr = queue.pop();
            // My.cout("curr:" + curr);
            Relationship[] edges = curr.friends.toArray(new Relationship[0]);
            for(Relationship edge:edges){
                if(edge==null) continue;
                User vert = null;

                if(edge.friendA == curr){
                    vert = edge.friendB;
                }else if(edge.friendB == curr){
                    vert = edge.friendA;
                }else continue;

                // My.cout("vert:" + vert);

                // double newDist = curr.dist + (double) edge.friendshipValue;
                double newDist = curr.dist + (double) 1;
                
                if(newDist < vert.dist){
                    vert.dist = newDist;
                    vert.prev = curr;
                    if(!queue.contains(vert)){
                        queue.push(vert);
                    }
                }
            }
        }

        // this.users.forEach((user)->{
        //     My.cout("user: "+user+" prev: "+user.prev+" dist: "+user.dist);
        // });

    }

    protected void buildPath(User back, User front, ArrayList<User> path){
        if(back==null) return;
        else{
            path.add(back);
            if(back != front){
                buildPath(back.prev, front, path);
            }
        }
    }

    protected ArrayList<Integer> BrelazAlgo(){
        ArrayList<Integer> colors = new ArrayList<>();

        // ArrayList<User> _users = new ArrayList<>();

        // User[] sortedUsers = bubblySortUsers(this.users.toArray(new User[0]));

        // for(int i=0;i<sortedUsers.length;i++){
        //     _users.add(sortedUsers[i]);
        // }

        users.forEach((user)->{
            user.color = null;
        });

        User unprocessed = getHighestDegreeUncoloredVertex(this.users);
        while(unprocessed!=null){
            for(int c=0; c<colors.size();c++){
                Integer color = colors.get(c);
                if(!unprocessed.friendsHaveColor(color)){
                    unprocessed.color = color;
                    break;
                }
            }
            if(unprocessed.color==null){
                Integer color = colors.size();
                unprocessed.color = color;
                colors.add(color);
            }
            // My.cout("Vert:"+unprocessed+" Color:"+unprocessed.color);

            unprocessed = getHighestDegreeUncoloredVertex(this.users);
        }

        return colors;

    }

    protected User getHighestDegreeUncoloredVertex(ArrayList<User> users){
        ArrayList<User> uncolored = new ArrayList<>();
        ArrayList<User> maxSatColors = new ArrayList<>();

        int maxSatDeg = -1;

        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            if(user.color==null){
                uncolored.add(user);
                int satDeg = user.getSaturationDegree();
                if(satDeg>maxSatDeg){
                    maxSatDeg = satDeg;
                }
            }
        }

        if(uncolored.size()<=0) return null;
        if(uncolored.size()==1) return uncolored.get(0);
        //get highest degree of saturation

        int maxFriends = -1;
        User highestUser = null;
        
        for(int i=0; i<uncolored.size(); i++){
            User user = uncolored.get(i);
            if(user.getSaturationDegree() >= maxSatDeg){
                maxSatColors.add(user);
                //get highest degree of adjacent users(vertexes)
                int numFriends = user.getUncoloredFriendCount();
                if(numFriends>maxFriends){
                    maxFriends = numFriends;
                    highestUser = user;
                }
            }
        }

        return highestUser;

    }

    protected User getUncoloredUser(ArrayList<User> users){
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            if(user.color<0){
                return user;
            }
        }
        return null;
    }

    protected Relationship[] KruskalAlgo(){
        ArrayList<Relationship> tree = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();

        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            user.node = new Node(user);
            nodes.add(user.node);
        }

        Relationship[] edges = bubblySortEdges(getAllRelationships());

        for(int i=0; i<edges.length; i++){
            Relationship edge = edges[i];

            if(edge==null||edge.isIncomplete()) continue;

            Node nodeA = edge.friendA.node;
            Node nodeB = edge.friendB.node;

            Link link = nodeA.createLink(nodeB);
            // // My.cout("Link: "+link.ref);

            boolean check = DFSCycleNode(tree, nodes);

            if(check){
                tree.add(edge);
                // My.cout("- added: "+edge);
            }else{
                link.disconnect();
                // My.cout("- disconnect: "+edge);
            }
            
        }

        return tree.toArray(new Relationship[0]);

    }

    protected boolean DFSCycleNode(ArrayList tree, ArrayList nodes){
        resetNodeVisits(nodes);
        Node unvisited = getUnvistedNode(nodes);
        // My.cout("unvisited: "+unvisited.ref);
        boolean check = true;
        while(unvisited!=null){
            boolean _check = cycleDetectNode(unvisited);
            if(!_check){
                unvisited = null;
            }else{
                unvisited = getUnvistedNode(nodes);
            }
            check = _check;
        }
        // My.cout("-------------");
        return check;

    }

    protected boolean cycleDetectNode(Node start){
        start.num++;
        // My.cout("start: "+start.ref);

        for(int i=0; i<start.edges.size(); i++){
            Link link = start.edges.get(i);
            Relationship edge = link.ref;
            Node vert  = null;

            if(edge.friendA == start.ref){
                vert = edge.friendB.node;
            }else if(edge.friendB == start.ref){
                vert = edge.friendA.node;
            }else continue;
            // My.cout("-- vert:"+vert.ref);

            if(vert.num == 0){
                vert.pred = start;
                boolean check = cycleDetectNode(vert);
                if(!check) return false;
            }else if(start.pred != vert){
                //cycle detected
                // My.cout("CYCLE");
                return false;
            }
        }

        return true;
    }

    protected void resetNodeVisits(ArrayList<Node> nodes){
        for(int i=0;i<nodes.size();i++){
            Node node = nodes.get(i);
            if(node==null) continue;
            node.num = 0;
            node.pred = null;
            node.prev = null;
            node.dist = 0;
        }
    }

    protected Node getUnvistedNode(ArrayList<Node> nodes){
        for(int i=0;i<nodes.size();i++){
            Node node = nodes.get(i);
            if(node.num == 0){
                return node;
            }
        }
        return null;
    }

    protected void resetVisits(){
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            user.num = 0;
            user.pred = null;
        }
    }

    class Node{
        User ref = null;
        ArrayList<Link> edges = new ArrayList<>();

        public double dist = 0;
        public double color = 0;
        public Node prev = null;
        public Node pred = null;
        public double num = Double.POSITIVE_INFINITY;

        Node(User user){
            this.ref = user;
            if(this.ref!=null){
                this.ref.node = this;
            }
        }

        Link createLink(Node other){
            User userA = this.ref;
            User userB = other.ref;

            Relationship rela = userA.getRelationshipWith(userB);

            for(int c=0;c<edges.size();c++){
                if(rela == edges.get(c).ref){
                    return edges.get(c);
                }
            }

            if(rela!=null){
                Link newLink = new Link(rela);
                this.edges.add(newLink);
                other.edges.add(newLink);
                return newLink;
            }

            return null;
        }

        void removeLink(Link link){
            for(int c=0;c<edges.size();c++){
                if(edges.get(c)==link){
                    edges.remove(link);
                    return;
                }
            }
        }

    }

    class Link{
        Relationship ref = null;

        Link(Relationship rela){
            this.ref = rela;
            if(this.ref!=null){
                this.ref.link = this;
            }
        }

        boolean contains(Node node){
            return this.ref.contains(node.ref);
        }

        void disconnect(){
            Node nodeA = this.ref.friendA.node;
            Node nodeB = this.ref.friendB.node;

            nodeA.removeLink(this);
            nodeB.removeLink(this);
        }
    }

    protected User getUnvistedVertex(){
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            if(user.num == 0){
                return user;
            }
        }
        return null;
    }

    protected Relationship[] getAllRelationships(){
        ArrayList<Relationship> edges = new ArrayList<>();

        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            for(int j=0;j<user.friends.size();j++){
                Relationship rela = user.friends.get(j);
                if(rela == null || rela.isIncomplete()){
                    continue;
                }
                boolean exists = false;
                for(int k=0;k<edges.size();k++){
                    if(edges.get(k).equals(rela)){
                        exists = true; break;
                    }
                }
                if(!exists){
                    edges.add(rela);
                }
            }
        }

        return (Relationship[]) edges.toArray(new Relationship[0]);

    }

    //ascending order
    protected User[] bubblySortUsers(User[] array){
        User[] arr = cloneArray(array);

        for(int i=0;i<arr.length-1;i++){
            for(int j=arr.length-1;j>=i+1;j--){
                User curr = (User) arr[j];
                User prev = (User) arr[j-1];

                if(curr==null || prev==null) continue;

                if( curr.userID < prev.userID ){
                    User temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }

        return arr;
    }

    protected static Relationship[] bubblySortEdges(Relationship[] edges){
        Relationship[] arr = edges;

        for(int i=0;i<arr.length-1;i++){
            for(int j=arr.length-1;j>=i+1;j--){
                Relationship curr = arr[j];
                Relationship prev = arr[j-1];

                if(curr==null || prev==null) continue;

                if(curr.friendshipValue < prev.friendshipValue){
                    Relationship temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    // <Relationship>swap(arr, j, j-1);
                }
            }
        }

        return arr;
    }

    //ARRAY FUNCTIONS

    // protected static <T> void swap(T[] arr, int a, int b){
    //     T temp = arr[a];
    //     arr[a] = arr[b];
    //     arr[b] = temp;
    // }

    // protected static Object[] arrayPush(Object[] arr, Object item){
    //     Object[] newArr = resizeArray(arr,1);
    //     newArr[newArr.length-1] = item;
    //     return newArr;
    // }protected static User[] arrayPush(User[] arr, User item){
    //     User[] newArr = resizeArray(arr,1);
    //     newArr[newArr.length-1] = item;
    //     return newArr;
    // }
    // protected static Object[] arrayPop(Object[] arr){
    //     Object[] newArr = resizeArray(arr,-1);
    //     return newArr;
    // }protected static User[] arrayPop(User[] arr){
    //     User[] newArr = resizeArray(arr,-1);
    //     return newArr;
    // }
    // protected static User[] arrayUnshift(User[] arr, User item){
    //     User[] newArr = new User[arr.length+1];

    //     for(int i=1;i<=arr.length;i++){
    //         newArr[i] = arr[i-1];
    //     }

    //     newArr[0] = item;

    //     return newArr;
    // }

    // protected static Object[] reverseArray(Object[] arr){
    //     Object[] newArr = new Object[arr.length];

    //     int c = arr.length-1;
    //     for(int i=0; i<arr.length; i++){
    //         newArr[i] = arr[c];
    //         c--;
    //     }
    //     return newArr;
    // }protected static User[] reverseArray(User[] arr){
    //     User[] newArr = new User[arr.length];

    //     int c = arr.length-1;
    //     for(int i=0; i<arr.length; i++){
    //         newArr[i] = arr[c];
    //         c--;
    //     }
    //     return newArr;
    // }

    protected static Object[] resizeArray(Object[] arr, int resize){
        int len = arr.length + resize;
        Object[] newArr = new Object[len];
        for(int i=0;i<arr.length;i++){
            if(i>=len) break;
            newArr[i] = arr[i];
        }
        return newArr;
    }protected static User[] resizeArray(User[] arr, int resize){
        int len = arr.length + resize;
        User[] newArr = new User[len];
        for(int i=0;i<arr.length;i++){
            if(i>=len) break;
            newArr[i] = arr[i];
        }
        return newArr;
    }

    protected static Object[] cloneArray(Object[] arr){
        return resizeArray(arr,0);
    }protected static User[] cloneArray(User[] arr){
        return resizeArray(arr,0);
    }

    protected static boolean isInArray(Object[] arr, Object item){
        if(item==null) return false;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == item){
                return true;
            }
        }
        return false;
    }protected static boolean isInArray(User[] arr, User item){
        if(item==null) return false;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == item){
                return true;
            }
        }
        return false;
    }


}
