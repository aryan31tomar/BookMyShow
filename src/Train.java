public class Train{
    private int trainid;
    private String name;
    private String source;
    private String destination;
    private int availableseats;
    private int totalseats;

    Train(int trainid,String name,String source , String destination, int totalseats){
        this.trainid=trainid;
        this.name=name;
        this.source=source;
        this.destination=destination;
        this.availableseats=totalseats;
        this.totalseats=totalseats;
    }

    public int getTrainid(){
        return trainid;
    }
    public void setTrainid(int trainid){
        this.trainid=trainid;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getsource(){
        return source;
    }
    public void setsource(String source){
        this.source=source;
    }

    public String getDestination(){
        return destination;
    }
    public void setDestination(String destination){
        this.destination=destination;
    }

    public int getAvailableseats(){
        return availableseats;
    }
    public void setAvailableseats(int availableseats){
        this.availableseats=availableseats;
    }

    public int getTotalseats(){
        return totalseats;
    }
    public void setTotalseats(int totalseats){
        this.totalseats=totalseats;
    }

    public boolean bookSeats(int count){
        if(count<=availableseats){
            availableseats-=count;
            return true;
        }
        return false;
    }

    public void cancelSeats(int count){
        availableseats+=count;
    }

    public String toString(){
        return trainid + " | " + name + " | " +source + " -> "+ destination + " | Seats Available :" + availableseats;
    }
     

}